package com.dcmd.service.demand.inter.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcmd.common.core.enums.DeamndConstants;
import com.dcmd.common.core.utils.*;
import com.dcmd.common.core.utils.DateUtils;
import com.dcmd.service.demand.config.Constants;
import com.dcmd.service.demand.entity.Demand;
import com.dcmd.service.demand.entity.Features;
import com.dcmd.service.demand.entity.Menu;
import com.dcmd.service.demand.entity.Version;
import com.dcmd.service.demand.inter.DemandService;
import com.dcmd.service.demand.mapper.*;
import com.dcmd.service.demand.user.inter.IUserInfoHystric;
import com.dcmd.service.demand.utils.Result;
import com.dcmd.service.demand.utils.VersionUtils;
import com.dcmd.service.demand.vo.DemandVo;
import com.dcmd.service.demand.vo.FeaturesVo;
import com.dcmd.service.demand.vo.ParameterVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DemandServiceImpl implements DemandService {
    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(com.dcmd.service.demand.inter.impl.DemandServiceImpl.class);
    @Autowired
    private Constants constants;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private DemandMapper demandMapper;

    @Autowired
    private VersionMapper versionMapper;

    @Autowired
    private IUserInfoHystric iUserInfoHystric;

    @Autowired
    private LabelMapper labelMapper;

    @Autowired
    private FeaturesMapper featuresMapper;

    @Autowired
    private SysParameterMapper parameterMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(Long id) throws Exception {
        // 删除需求的时候一并删除该需求所携带的所有版本描述
        List<Long> list = versionMapper.findAllByDemandId(id);
        if (CollectionUtils.isNotEmpty(list)) {
            versionMapper.deleteAll(list);
        }
        return demandMapper.deleteByPrimaryKey(id);
    }


    @Override
    public Demand selectByPrimaryKey(String id) throws Exception {
        return demandMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateByPrimaryKey(JSONObject jsonObject) throws Exception {
        FeaturesVo vo = null;
        JSONObject versionJson = jsonObject.getJSONObject("version");
        JSONObject featuresVoJson = jsonObject.getJSONObject("featuresVo");

        if (null != featuresVoJson) {
            vo = JSON.toJavaObject(featuresVoJson, FeaturesVo.class);
        }
        Version version = JSON.toJavaObject(versionJson, Version.class);
        version.setUpdateTime(com.dcmd.service.demand.utils.DateUtils.getDateTime());

        jsonObject.remove("version");
        jsonObject.remove("featuresVo");

        Demand demand = JSON.toJavaObject(jsonObject, Demand.class);
        demand.setUpdateTime(com.dcmd.service.demand.utils.DateUtils.getDateTime());
        demand.setEntry(Long.valueOf(jsonObject.getString("entry")));


        if (StringUtils.isNotBlank(version.getMarkDownContent()) && StringUtils.isNotBlank(version.getDemandDescribe())) {
            if (!version.getDemandDescribe().equals(version.getMarkDownContent())) {
                // 拿到当前需求的最新版本号
                String versionNum = versionMapper.queryMaxVersionNum(version.getDemandId());
                Version version1 = new Version();
                version1.setChangeStaffName(version.getChangeStaffName());
                version1.setDemandId(version.getDemandId());
                version1.setVersionNum(VersionUtils.changeVersionNumber(versionNum));
                version1.setDemandDescribe(version.getMarkDownContent());
                version1.setCreateTime(com.dcmd.service.demand.utils.DateUtils.getDateTime());
                versionMapper.insert(version1);
                // 需求变更的时候需求描述里面是否有 功能点名称
//                List<String> subUtil = VersionUtils.getSubUtil(version.getMarkDownContent(), VersionUtils.REGX);
//                if(CollectionUtils.isNotEmpty(subUtil)){
//                    List<Features> featuresList = generateFeatures(subUtil, vo);
//                    if(CollectionUtils.isNotEmpty(featuresList)){
//                        featuresMapper.insertBatch(featuresList);
//                    }
//                }
            }
        }
        versionMapper.updateByPrimaryKey(version);
        demandMapper.updateByPrimaryKey(demand);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteIds(Long[] ids) throws Exception {
        List<Long> longs = Arrays.asList(ids);
        longs.forEach(a -> {
            versionMapper.deleteAll(versionMapper.findAllByDemandId(a));
        });
        // 拿到所有的需求版本一并删除
        demandMapper.deleteIds(ids);
    }


    public static Date getexpResTime(String str) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(DateUtils.getDateFormatDatetime(str));
    }

    @Override
    public String queryKeyById(String id) throws Exception {
        return demandMapper.queryKeyById(id);
    }

    @Override
    public String queryByMaxId(String name) throws Exception {
        StringBuilder s = new StringBuilder();
        //获取日期
        String num = DateUtils.getReqDateyyyyMMdd(new Date());
        Object xqKey2 = redisTemplate.opsForValue().get("XQKey");
        Object xwsq = redisTemplate.opsForValue().get("YWSQKey");
        Object trKey = redisTemplate.opsForValue().get("TRKey");
        String xq = "XQ";
        String ywsq = "YWSQ";
        String tr = "TR";
        switch (name) {
            case (DeamndConstants.XQ):
                if (xqKey2 == null) {
                    //数据库查询最大
                    String s1 = demandMapper.queryByMaxId(xq);
                    int substring = Integer.valueOf(StringUtils.substringAfterLast(s1, "-"));
                    redisTemplate.opsForValue().set("XQKey", substring);
                    return s1;
                } else {
                    int xqKey = (int) redisTemplate.opsForValue().get("XQKey");
                    int i = xqKey + 1;
                    s = s.append("XQ").append("-").append(num).append("-").append(i);
                    return s.toString();
                }
            case (DeamndConstants.YWSQ):
                if (xwsq == null) {
                    String s1 = demandMapper.queryByMaxId(ywsq);
                    int substring = Integer.valueOf(StringUtils.substringAfterLast(s1, "-"));
                    redisTemplate.opsForValue().set("YWSQKey", substring);
                    return s1;
                } else {
                    int xqKey1 = (int) redisTemplate.opsForValue().get("YWSQKey");
                    int i = xqKey1 + 1;
                    s = s.append("YWSQ").append("-").append(num).append("-").append(i);
                    return s.toString();
                }
            case (DeamndConstants.TR):
                if (trKey == null) {
                    String s1 = demandMapper.queryByMaxId(tr);
                    int substring = Integer.valueOf(StringUtils.substringAfterLast(s1, "-"));
                    redisTemplate.opsForValue().set("TRKey", substring);
                    return s1;
                } else {
                    int xqKey1 = (int) redisTemplate.opsForValue().get("TRKey");
                    int i = xqKey1 + 1;
                    s = s.append("TR").append("-").append(num).append("-").append(i);
                    return s.toString();
                }
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> queryByNameAll() throws Exception {
        return demandMapper.queryByNameAll();
    }

    @Override
    public List queryProductAll() {
        return demandMapper.queryProductAll();
    }

    @Override
    public List queryParentModule() {
        return demandMapper.queryParentModule();
    }

    @Override
    public String Base64MultipartFileIpload(JSONObject jsonObject) throws Exception {
        //拿到图片上传的固定位置
        String filePath = constants.getFilePath();
        String image = jsonObject.getString("base64Image");
        MultipartFile multipartFile = BASE64DecodedMultipartFile.base64ToMultipart(image);
        MultipartFile[] file = {multipartFile};
        Map<String, String> map = ImageOploadAndDownloadUtils.executeUpload(file, filePath);
        String fileString = null;
        for (Map.Entry<String, String> m : map.entrySet()) {
            System.out.println("key:" + m.getKey());
            System.out.println("value:" + m.getValue());
            fileString = m.getValue();
        }
        return filePath + fileString;
    }

    @Override
    public String multipartFileIpload(MultipartFile file) throws Exception {
        //拿到图片上传的固定位置
        String filePath = constants.getFilePath();
        String downloadPath = constants.getDownloadPath();
        return downloadPath + ImageOploadAndDownloadUtils.executeUpload(file, filePath);
    }

    @Override
    public String selectProductModuleName(String[] productModuleId) throws Exception {
        return demandMapper.selectProductModuleName(productModuleId);
    }

    @Override
    public List<DemandVo> findAllByacceptStaffIdOrreviewStaffIdOrputStaffId() throws Exception {
       /* Map<String, Object> parramMap = JSON.toJavaObject(jsonObject, Map.class);
        if (parramMap.isEmpty()) {
            throw new ErrorInfo("请求体为空", 500);
        }*/
        return demandMapper.findAllByacceptStaffIdOrreviewStaffIdOrputStaffId();
    }

    @Override
    public List<Menu> queryProductModelById(String productId) {
        return demandMapper.queryProductModelById(productId);
    }

    /**
     * 导入
     *
     * @param file
     * @return
     * @throws Exception
     */
    @Override
    public Result multipleFilesUpload(MultipartFile file) throws Exception {
        Result<String> resData = new Result<String>();
        //上传路径 /data/upload/images/tmp/
        String fileUpload = constants.getFileUpload();
        //移动路径 /data/upload/images/
        String filePath = constants.getFilePath();
        if (file != null) {
            //获取源文件名
            String orgName = file.getOriginalFilename();
            String uuidName = UUID.randomUUID() + orgName.substring(orgName.indexOf(".docx"));
            System.out.println("===:"+uuidName);
            File f = new File(fileUpload +uuidName);
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdir();
            }
            file.transferTo(f);
            //随机数
            String filePath1 = UUID.randomUUID() + StringUtils.substringBefore(uuidName, ".docx");
            //图片路径  /data/upload/images/tmp/ssssss/media/
            String uploadImagePath = fileUpload + filePath1 + "/media/";
            //随机生成的文件夹
            File file1 = new File(fileUpload + filePath1);
            String str = "pandoc " + uuidName + " -f docx -t markdown-simple_tables-multiline_tables-grid_tables --atx-headers --wrap=none --toc --extract-media=" + filePath1 + " -o " + uuidName.replace("docx", "md");
            //执行命令
            ProcessBuilder processBuilder = new ProcessBuilder("/bin/sh", "-c", str);
            //指定路径
            processBuilder.directory(new File(fileUpload));
            Process process = processBuilder.start();
            process.waitFor();
            //是否执行结束
            if (process.exitValue() == 0) {
                process.destroy();
                //yidong图片
                List<String> list = StringPulationUtils.moveTotherFolders(uploadImagePath, filePath);
                //获取名称
                String string1 = UploadFileUtils.fileTemplateName(fileUpload);
                //输出格式内容
                String string = StringPulationUtils.file2String(fileUpload, string1, list);
                logger.info("dddddddddddddddddddddddddddddd");
                if (string.contains(filePath1)) {
                    String s1 = string.replace(filePath1, "");
                    logger.info("eeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
                    f.delete();
                    if (file1.exists()) {
                        file1.delete();
                    }
                    resData.setCode(200);
                    resData.setData(s1);
                    resData.setMessage("sucess");
                    return resData;
                }
                f.delete();
                if(file1.exists()){
                    file1.delete();
                }
                resData.setCode(200);
                resData.setData(string);
                resData.setMessage("sucess");
                return resData;
            }
        }
        resData.setCode(500);
        resData.setMessage("导入失败。。。");
        return resData;
    }

    @Override
    public List<ParameterVo> getEntryLevelInfo() throws Exception {
        return parameterMapper.getEntryLevelInfo();
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map demandOnline(JSONObject jsonObject) throws Exception {
        JSONObject versionJson = jsonObject.getJSONObject("version");
        String onLine = jsonObject.getString("onLine");
        jsonObject.remove("version");
        jsonObject.remove("onLine");
        Version version = JSON.toJavaObject(versionJson, Version.class);
        version.setUpdateTime(com.dcmd.service.demand.utils.DateUtils.getDateTime());
        Demand demand = JSON.toJavaObject(jsonObject, Demand.class);
        demand.setEntry(Long.valueOf(jsonObject.getString("entry")));

        // 需求上线改变需求版本号切改变需求状态
        demand.setUpdateTime(com.dcmd.service.demand.utils.DateUtils.getDateTime());
        demand.setStatus(onLine);

        Version v = new Version();
        v.setVersionNum(VersionUtils.onlineVersionNumber(version.getVersionNum()));
        v.setCreateTime(com.dcmd.service.demand.utils.DateUtils.getDateTime());
        v.setDemandId(version.getDemandId());
        v.setDemandDescribe(version.getDemandDescribe());
        v.setChangeStaffName(version.getChangeStaffName());

        versionMapper.insert(v);
        versionMapper.updateByPrimaryKey(version);
        demandMapper.updateByPrimaryKey(demand);

        return new HashMap(5) {{
            put("message", "上线成功!");
            put("code", 200);
        }};
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(JSONObject jsonObject) throws Exception {
        Features features = null;
        JSONObject versionJson = jsonObject.getJSONObject("version");
        JSONObject featuresVoJson = jsonObject.getJSONObject("featuresVo");
        Version version = JSON.toJavaObject(versionJson, Version.class);
        FeaturesVo featuresVo = JSON.toJavaObject(featuresVoJson, FeaturesVo.class);
        jsonObject.remove("version");
        jsonObject.remove("featuresVo");

        Demand demand = JSON.toJavaObject(jsonObject, Demand.class);
        demand.setEntry(Long.valueOf(jsonObject.getString("entry")));
        demand.setCreateTime(com.dcmd.service.demand.utils.DateUtils.getDateTime());

        if (demand.getType().equals(VersionUtils.TASK)) {
            version.setVersionNum(VersionUtils.geterateV());
        } else if (demand.getType().equals(VersionUtils.PROJECT)) {
            version.setVersionNum(VersionUtils.generatePV());
        }
        version.setCreateTime(com.dcmd.service.demand.utils.DateUtils.getDateTime());
        //获取需求描述,拿到其中的功能点名称
        String demandDescribe = version.getMarkDownContent();
        String subUtil = VersionUtils.getSubUtilSimple(demandDescribe, VersionUtils.REGX);
        if (StringUtils.isNotBlank(subUtil)) {
            features = generateFeatures(subUtil, featuresVo);
            featuresMapper.insert(features);
        }
        demand.setFunctionId(String.valueOf(features.getId()));
        demandMapper.insert(demand);
        Long DemandId = demand.getId();
        version.setDemandId(DemandId);
        versionMapper.inserto(version);
    }

    /**
     * 组装功能点
     *
     * @param subUtil
     * @param vo
     * @return
     */
    public Features generateFeatures(String subUtil, FeaturesVo vo) {
        if (StringUtils.isNotBlank(subUtil)) {
            Features features = new Features();
            features.setName(subUtil);
            features.setBusinessId(Long.valueOf(vo.getBusinessId()));
            features.setFeaturesBusiness(vo.getFeaturesBusiness());
            features.setSoftwareNum(vo.getSoftwareNum());
            return features;
        }
        return null;
    }
}

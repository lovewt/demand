package com.dcmd.service.demand.controller;

import com.alibaba.fastjson.JSONObject;
import com.dcmd.common.core.utils.AjaxResultUtil;
import com.dcmd.common.core.utils.TreeUtils;
import com.dcmd.common.core.vo.BasParameterVo;
import com.dcmd.common.core.vo.SoftwareProductVo;
import com.dcmd.common.core.vo.SysUserSelecte;
import com.dcmd.service.demand.arch.product.ProductService;
import com.dcmd.service.demand.config.Constants;
import com.dcmd.service.demand.entity.Demand;
import com.dcmd.service.demand.entity.Institution;
import com.dcmd.service.demand.entity.Label;
import com.dcmd.service.demand.entity.Menu;
import com.dcmd.service.demand.inter.DemandService;
import com.dcmd.service.demand.inter.InstitutionService;
import com.dcmd.service.demand.inter.LabelService;
import com.dcmd.service.demand.inter.VersionService;
import com.dcmd.service.demand.user.inter.IUserInfoHystric;
import com.dcmd.service.demand.utils.Result;
import com.dcmd.service.demand.utils.VersionUtils;
import com.dcmd.service.demand.vo.DemandVo;
import com.dcmd.service.demand.vo.ParameterVo;
import com.dcmd.service.demand.vo.VersionVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Api("原始需求接口")
@RestController
@RequestMapping("/demandController")
public class DemandController {
    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(com.dcmd.service.demand.controller.DemandController.class);

    @Autowired
    private Constants constants;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private  DemandService demandService;

    @Autowired
    private LabelService labelService;

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private VersionService versionService;

    @Autowired
    private IUserInfoHystric iUserInfoHystric;

    @Autowired
    private ProductService productService;

    /**
     * 根据我提交的，我受理的，我评审的分别查询三类需求
     *
     * @return 需求整体
     */

    @GetMapping("/findAllByacceptStaffIdOrreviewStaffIdOrputStaffId")
    List<DemandVo> findAllByacceptStaffIdOrreviewStaffIdOrputStaffId() throws Exception {
        List<DemandVo> demandList = demandService.findAllByacceptStaffIdOrreviewStaffIdOrputStaffId();
        List<SysUserSelecte> userList = iUserInfoHystric.findReviewAcceptor();
        List<SoftwareProductVo> product = productService.getProduct();
        List<BasParameterVo> productLine = productService.getProductLine();
        if (CollectionUtils.isNotEmpty(userList) && CollectionUtils.isNotEmpty(demandList) && CollectionUtils.isNotEmpty(product) && CollectionUtils.isNotEmpty(productLine)) {
            demandList.parallelStream().forEach(vo -> {
                String productLineId = vo.getProductModuleId().split(VersionUtils.COMMA)[0];
                String productId = vo.getProductModuleId().split(VersionUtils.COMMA)[1];
               userList.forEach(user->{
                   if( user.getUserId().equals(vo.getDemandLeader())){
                       vo.setDemandLeaderName(user.getRealName());
                   }
               });
                productLine.parallelStream().forEach(a -> {
                    if (productLineId.equals(String.valueOf(a.getId()))){
                        vo.setProductLineId(productLineId);
                        vo.setProductLineName(a.getValue());
                    }
                });
                product.parallelStream().forEach(b -> {
                    if (productId.equals(String.valueOf(b.getSoftwareNum()))) {
                        vo.setProductModuleId(productId);
                        vo.setProductModuleName(b.getSoftwareName());
                    }
                });
            });
        }
        return demandList;
    }

    /**
     * 获取需求提出方人员
     *
     * @return
     */
    @GetMapping("/findProposePerson")
    List<SysUserSelecte> findProposePerson() {
        return iUserInfoHystric.findProposePerson();
    }

    /**
     * 获取需求评审及受理人员
     *
     * @return
     */
    @GetMapping("/findReviewAcceptor")
    List<SysUserSelecte> findReviewAcceptor() {
        return iUserInfoHystric.findReviewAcceptor();
    }

    /**
     * 查询单条信息
     *
     * @return
     */
    @ApiOperation(value = "获取详细信息", notes = "根据url的id来获取详细信息")
    @ApiImplicitParam(name = "id", value = "需求ID", required = true, dataType = "Long")
    @GetMapping("/queryOne")
    public Demand queryById(String id) throws Exception {
        return demandService.selectByPrimaryKey(id);
    }

    /**
     * 单条删除
     *
     * @return
     */
    @ApiOperation(value = "删除需求", notes = "根据ID删除需求")
    @ApiImplicitParam(name = "id", value = "需求ID", required = true, dataType = "Integer", paramType = "path")
    @PostMapping("/deleteOne/{id}")
    public AjaxResultUtil deleteOne(@PathVariable Long id) throws Exception {
        demandService.deleteByPrimaryKey(id);
        return new AjaxResultUtil();
    }

    /**
     * 多条删除
     *
     * @param
     * @return
     */
    @ApiOperation(value = "删除需求", notes = "多条删除")
    @PostMapping("/deleteMore")
    public AjaxResultUtil deletes(@RequestBody Long[] ids) throws Exception {
        demandService.deleteIds(ids);
        return new AjaxResultUtil();
    }

    /**
     * 新增需求
     *
     * @param
     * @return
     */
    @ApiOperation(value = "添加需求", notes = "根据Demand添加需求")
    @PostMapping("/insertDemand")
    public AjaxResultUtil insertDemand(@RequestBody JSONObject jsonObject) throws Exception {
        demandService.insert(jsonObject);
        return new AjaxResultUtil();
    }

    /**
     * 修改需求
     *
     * @param jsonObject
     * @return
     */
    @ApiOperation(value = "更新需求", notes = "根据Demand更新需求")
    @PostMapping("/updateDemand")
    public AjaxResultUtil updateDemand(@RequestBody JSONObject jsonObject) throws Exception {
        demandService.updateByPrimaryKey(jsonObject);
        return new AjaxResultUtil();
    }


    /**
     * 机构号、名称接口
     *
     * @return
     */
    @ApiOperation(value = "获取当前机构、名称列表", notes = "获取列表详细信息")
    @GetMapping(value = "/queryInstitution")
    public List<Institution> queryInstitution() throws Exception {
        List<Institution> institutionList = institutionService.queryInstitionNoAndName();
        return institutionList;
    }

    /**
     * 标签下拉框
     *
     * @return
     */
    @ApiOperation(value = "获取当前标签列表", notes = "获取列表详细信息")
    @GetMapping("/indexlabel")
    public List<Label> queryByAll() throws Exception {
        List<Label> lsitlabel = labelService.selectAll();
        return lsitlabel;
    }

    /**
     * 评审状态
     *
     * @return
     */
    @ApiOperation(value = "获取当前评审状态列表", notes = "获取列表详细信息")
    @GetMapping("/queryReviewState")
    public List<Label> queryReviewState() throws Exception {
        List<Label> lsitReviewState = labelService.queryReviewState();
        return lsitReviewState;
    }

    /**
     * 获取需求状态
     *
     * @return
     */
    @ApiOperation(value = "获取需求状态列表", notes = "获取列表详细信息")
    @GetMapping("/getDemandStatusInfo/{parameterType}")
    public List<ParameterVo> getDemandStatusInfo(@PathVariable String parameterType) throws Exception {
        return labelService.getDemandStatusInfo(parameterType);
    }


    /**
     * 获取历史需求版本描述
     *
     * @param demandId 需求ID
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取历史版本信息", notes = "根据url的id来获取详细信息")
    @ApiImplicitParam(name = "demandId", value = "需求ID", required = true, dataType = "String")
    @GetMapping("/findHistoryDemandVersionDescription/{demandId}")
    public List<VersionVo> findDemandHistoryVersionDescription(@PathVariable("demandId") Long demandId) throws Exception {
        return versionService.findDemandHistoryVersionDescription(demandId);
    }


    /**
     * 产品编号、名称接口
     *
     * @return
     */
    @ApiOperation(value = "获取产品编号信息", notes = "根据名称获取产品编号信息")
    @GetMapping("/queryproduct")
    public List<Menu> productAndModel() throws Exception {
        List<Menu> dateAll = demandService.queryProductAll();
        List<Menu> product = demandService.queryParentModule();
        for (Menu menu : product) {
            TreeUtils.createTree(dateAll, menu, "value", "pid", "children");
        }
        return product;
    }

    /**
     * 根据需求类型查询需求描述的MarkDown模板
     *
     * @return
     */
    @ApiOperation(value = "获取需求描述的MarkDown模板", notes = "根据url的type来获取详细信息")
    @ApiImplicitParam(name = "type", value = "类型", required = true, dataType = "Long")
    @GetMapping("/findMarkdownByDemandType/{type}")
    public List<Map<String, Object>> findMarkdownByDemandType(@PathVariable Long type) throws Exception {
        List<Map<String, Object>> templateList = labelService.findMarkdownByDemandType(type);
        for (Map<String, Object> str : templateList) {
            str.put("value", String.valueOf(str.get("value")));
        }
        return templateList;
    }


    /**
     * 根据name查询key生成ID
     *
     * @param name
     * @return
     */
    @ApiOperation(value = "获取demandID的信息", notes = "根据url的name来获取详细信息")
    @ApiImplicitParam(name = "name", value = "名称", required = true, dataType = "String")
    @GetMapping("/queryTypeId")
    public String queryKeyByName(String name) throws Exception {
        return demandService.queryByMaxId(name);

    }


    /**
     * 查询type类型
     *
     * @return
     */
    @ApiOperation(value = "获取类型信息", notes = "根据type来获取详细信息")
    @GetMapping("/queryByNameAll")
    public List<Map<String, Object>> queryByNameAll() throws Exception {
        List<Map<String, Object>> typeList = demandService.queryByNameAll();
        for (Map<String, Object> str : typeList) {
            str.put("value", String.valueOf(str.get("value")));
        }
        return typeList;
    }

    /**
     * 上传Base64为编码图片
     *
     * @param jsonObject 文件编码
     * @return 文件全路径及文件名
     * @throws Exception
     */
    @ApiOperation(value = "上传Base64编码图片", notes = "上传图片")
    @ApiImplicitParam(name = "jsonObject", value = "jsonObject", required = true, dataType = "JSONObject")
    @PostMapping("/base64MultipartFileIpload")
    public String Base64MultipartFileIpload(@RequestBody JSONObject jsonObject) throws Exception {
        return demandService.Base64MultipartFileIpload(jsonObject);
    }

    /**
     * 上传普通文件
     *
     * @param file 文件
     * @return 文件全路径及文件名
     * @throws Exception
     */
    @ApiOperation(value = "上传普通文件", notes = "上传文件")
    @ApiImplicitParam(name = "file", value = "file文件类型", required = true, dataType = "MultipartFile")
    @PostMapping("/multipartFileIpload")
    public String multipartFileIpload(@RequestParam("file") MultipartFile file) throws Exception {
        return demandService.multipartFileIpload(file);
    }

    /**
     * 获取产品列表
     */
    @ApiOperation(value = "获取产品", notes = "获取产品列表")
    @GetMapping("/getProductList")
    public List<Menu> getProductList() {
        return demandService.queryParentModule();
    }

    /**
     * 根据产品获取产品模块
     */
    @ApiOperation(value = "根据产品获取产品模块", notes = "根据productId获取产品模块")
    @ApiImplicitParam(name = "productId", value = "产品productId", required = true, dataType = "String")
    @GetMapping("/getProductModelList")
    public List<Menu> getProductModelList(String productId) throws Exception {
        List<Menu> dateAll = demandService.queryProductAll();
        List<Menu> productModelList = demandService.queryProductModelById(productId);
        for (Menu menu : productModelList) {
            TreeUtils.createTree(dateAll, menu, "value", "pid", "children");
        }
        return productModelList;
    }

    /**
     * 导入
     *
     * @param
     * @return
     */
    @ApiOperation(value = "导入", notes = "导入docx文件")
    @ApiImplicitParam(name = "file", value = "docx file文件", required = true, dataType = "MultipartFile")
    @PostMapping("/multipleFilesUpload")
    public Result multipleFilesUpload(MultipartFile file) throws Exception {
        return demandService.multipleFilesUpload(file);
    }

    /**
     * 获取需求条目层级信息
     * @return
     * @throws Exception
     */
    @GetMapping("/getEntryLevelInfo")
    public List<ParameterVo> getEntryLevelInfo() throws Exception{
        return demandService.getEntryLevelInfo();
    }

    /**
     * 需求上线
     * @param jsonObject
     * @return
     * @throws Exception
     */
    @PostMapping("/demandOnline")
    public Map demandOnline(@RequestBody JSONObject jsonObject) throws Exception{
        return demandService.demandOnline(jsonObject);
    }

}

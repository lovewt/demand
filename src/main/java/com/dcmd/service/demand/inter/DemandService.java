package com.dcmd.service.demand.inter;

import com.alibaba.fastjson.JSONObject;
import com.dcmd.service.demand.entity.Demand;
import com.dcmd.service.demand.entity.Menu;
import com.dcmd.service.demand.utils.Result;
import com.dcmd.service.demand.vo.DemandVo;
import com.dcmd.service.demand.vo.ParameterVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface DemandService {
    int deleteByPrimaryKey(Long id) throws Exception;

    void insert(JSONObject jsonObject) throws Exception;

    Demand selectByPrimaryKey(String id) throws Exception;

    void updateByPrimaryKey(JSONObject jsonObject) throws Exception;

    /**
     * 批量删除
     *
     * @param
     */
    void deleteIds(Long[] ids) throws Exception;

    /**
     * 查询类型参数key
     *
     * @return
     */
    String queryKeyById(String id) throws Exception;

    /**
     * 查询id最大数
     *
     * @return
     */
    String queryByMaxId(String name) throws Exception;

    /**
     * type查询类型
     *
     * @return
     */
    List<Map<String, Object>> queryByNameAll() throws Exception;

    /**
     * 查询所有产品
     *
     * @return
     */
    List queryProductAll();

    /**
     * 查询父类
     *
     * @return
     */
    List queryParentModule();


    String Base64MultipartFileIpload(JSONObject jsonObject) throws Exception;

    String multipartFileIpload(MultipartFile file) throws Exception;

    String selectProductModuleName(String[] productModuleId) throws Exception;

    /**
     * 根据我提交的，我受理的，我评审的分别查询三类需求
     *
     * @return 需求整体
     */
    List<DemandVo> findAllByacceptStaffIdOrreviewStaffIdOrputStaffId() throws Exception;

    List<Menu> queryProductModelById(String productId);


    /**
     * 导入
     *
     * @param file
     * @return
     */
    Result multipleFilesUpload(MultipartFile file) throws Exception;

    /**
     * 获取需求条目层级信息
     * @return
     * @throws Exception
     */
    List<ParameterVo> getEntryLevelInfo() throws Exception;

    /**
     * 需求上线
     * @param jsonObject
     * @return
     */
    Map demandOnline(JSONObject jsonObject) throws Exception;

}

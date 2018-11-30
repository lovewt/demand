package com.dcmd.service.demand.mapper;

import com.dcmd.service.demand.entity.Demand;
import com.dcmd.service.demand.entity.Menu;
import com.dcmd.service.demand.vo.DemandVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DemandMapper{
    int deleteByPrimaryKey(Long id);

    /**
     * 新增需求信息
     * @param demand  需求信息
     * @return
     */
    int insert(Demand demand);

    Demand selectByPrimaryKey(String id);

    List<Demand> selectAll();

    void updateByPrimaryKey(Demand demand);

    /**
     * 批量删除
     * @param
     */
    void deleteIds(Long [] ids);

    /**
     * 查询类型参数key
     * @return
     */
    String queryKeyById(String id);

    /**
     * 查询id最大数
     * @return
     */
    String queryByMaxId(String name);

    /**
     * type查询类型
     * @return
     */
    List<Map<String,Object>> queryByNameAll();

    /**
     * 查询所有产品
     * @return
     */
    List queryProductAll();

    /**
     * 查询父类
     * @return
     */
    List queryParentModule();

    String selectProductModuleName(String[] productModuleId);

    /**
     * 获取所有需求信息
     * @return
     * @throws Exception
     */
    List<DemandVo> findAllByacceptStaffIdOrreviewStaffIdOrputStaffId() throws Exception;

    List<Menu> queryProductModelById(String productId);
}

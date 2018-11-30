package com.dcmd.service.demand.arch.product;/**
 * Created by lenovo on 2018/10/29.
 */

import com.dcmd.common.core.vo.BasParameterVo;
import com.dcmd.common.core.vo.SoftwareProductVo;
import com.dcmd.service.demand.arch.product.impl.ProductServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @InterfaceName ProductService
 * @Author lenovo
 * @Date 2018/10/29 10:20
 * @Version 1.0.0
 */
@FeignClient(value = "dcmd-arch-api",path = "/softwareProductController",fallback = ProductServiceImpl.class)
public interface ProductService {

    /**
     * 获取所有产品线
     */
    @GetMapping("/getProductLine")
    List<BasParameterVo> getProductLine() throws Exception;

    /**
     * 获取所有产品
     */
    @GetMapping("/getProduct")
    List<SoftwareProductVo> getProduct()throws Exception;
}

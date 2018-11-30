package com.dcmd.service.demand.arch.product.impl;

import com.dcmd.common.core.vo.BasParameterVo;
import com.dcmd.common.core.vo.SoftwareProductVo;
import com.dcmd.service.demand.arch.product.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName ProductServiceImpl
 * @Author lenovo
 * @Date 2018/10/29 10:20
 * @Version 1.0.0
 */
@Component
public class ProductServiceImpl implements ProductService {

    private final static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public List<BasParameterVo> getProductLine() throws Exception {
        logger.debug("接口:{/softwareProductController/getProductLine},获取产品线进入熔断,返回null");
        return null;
    }

    @Override
    public List<SoftwareProductVo> getProduct() throws Exception {
        logger.debug("接口:{/softwareProductController/getProduct},获取产品进入熔断,返回null");
        return null;
    }
}

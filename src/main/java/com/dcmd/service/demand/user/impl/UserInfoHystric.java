package com.dcmd.service.demand.user.impl;


import com.dcmd.common.core.vo.SysUserSelecte;
import com.dcmd.common.core.vo.UserVO;
import com.dcmd.service.demand.user.inter.IUserInfoHystric;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UserInfoHystric implements IUserInfoHystric {

    private final static Logger logger = LoggerFactory.getLogger(UserInfoHystric.class);
	
    @Override
    public UserVO getUserInfo(String userId) {
        logger.error("UserInfoHystric.getUserInfo-用户信息接口，进入熔断，返回null");
        return null;
    }

    @Override
    public List<SysUserSelecte> findReviewAcceptor() {
        logger.error("UserInfoHystric.findReviewAcceptor获取需求评审及受理人员信息接口，进入熔断，返回null");
        return null;
    }

    @Override
    public List<SysUserSelecte> findProposePerson() {
        logger.error("UserInfoHystric.findProposePerson获取需求提出方人员信息接口，进入熔断，返回null");
        return null;
    }

    @Override
    public String findRealNameByUserId(String id) {
        logger.error("UserInfoHystric.findRealNameByUserId-用户信息接口，进入熔断，返回null");
        return null;
    }


}

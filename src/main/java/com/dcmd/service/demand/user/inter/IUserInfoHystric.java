package com.dcmd.service.demand.user.inter;


import com.dcmd.common.core.vo.SysUserSelecte;
import com.dcmd.common.core.vo.UserVO;
import com.dcmd.service.demand.user.impl.UserInfoHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "dcit-service-upms",path = "/user",fallback = UserInfoHystric.class)
public interface IUserInfoHystric {

	/**
	 * 根据条件查询
	 * @param username	查询条件实体
	 * @return
	 */
	@RequestMapping(value="/findUserByUsername/{username}",method = RequestMethod.GET)
	UserVO getUserInfo(@RequestParam("username") String username);

	/**
	 * 查询需求提出方
	 * @return
	 */
	@RequestMapping(value="/findProposePerson",method = RequestMethod.GET)
	List<SysUserSelecte> findProposePerson();

	/**
	 * 查询需求评审及受理人员
	 * @return
	 */
	@RequestMapping(value="/findReviewAcceptor",method = RequestMethod.GET)
	List<SysUserSelecte> findReviewAcceptor();

	/**
	 * 根据用户ID拿到其真实姓名
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/findRealNameByUserId" ,method = RequestMethod.GET)
	String findRealNameByUserId( String id);

}

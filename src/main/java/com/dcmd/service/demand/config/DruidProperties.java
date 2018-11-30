package com.dcmd.service.demand.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import javax.sql.DataSource;

import java.sql.SQLException;

import static com.dcmd.common.core.DruidConfig.*;

/**
 * <p>数据库数据源配置</p>
 *
 * @author stylefeng
 * @date 2017-05-21 11:18
 */
@Configuration
public class DruidProperties {

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), LOGIN_URL);
        servletRegistrationBean.addInitParameter(LOGIN_USERNAME_KEY, LOGIN_VALUE);
        servletRegistrationBean.addInitParameter(LOGIN_PASSWORD_KEY, LOGIN_VALUE);
        servletRegistrationBean.addInitParameter(SERVLET_KEY, SERVLET_VALUE);
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns(URL_PATTERN);
        filterRegistrationBean.addInitParameter(FILTER_INIT_PARAMETER_KEY, FILTER_INIT_PARAMETER_VALUE);
        return filterRegistrationBean;
    }
}

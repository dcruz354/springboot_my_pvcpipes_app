/**
 * 
 */
package com.springboot_my_pvcpipes_app.filters;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Dcruz
 * Configure a filter context programmatically.
 * Register the filter as a Bean to be managed by the Spring container.
 * Apply a certain URL pattern to each filter. 
 */
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<RequestResponseLoggingFilter> loggingFilter() {
        FilterRegistrationBean<RequestResponseLoggingFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new RequestResponseLoggingFilter());

        registrationBean.addUrlPatterns("/*");

        return registrationBean;

    }
    
    @Bean
    public FilterRegistrationBean<LoginFilter> loginFilterBean() {
        FilterRegistrationBean<LoginFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new LoginFilter());

        //registrationBean.addUrlPatterns("/user/*");
        registrationBean.addUrlPatterns("/login_success");
        

        return registrationBean;

    }
}

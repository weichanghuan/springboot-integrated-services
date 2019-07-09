package com.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭csrf验证
        http.csrf().disable()
                // 对请求进行认证
                .authorizeRequests()
                // 所有 / 的所有请求 都放行
                .antMatchers("/").permitAll()
                .antMatchers("/favicon.ico").permitAll()
                // 所有 /login 的POST请求 都放行
                .antMatchers("/api/**/login").permitAll()
                .antMatchers("/api/v1/email/**").permitAll()
                .antMatchers("/api/v1/manager/reg").permitAll()
                .antMatchers("/api/v1/user/reg").permitAll()
                .antMatchers("/api/v1/user/forgetpwd").permitAll()
                .antMatchers("/api/v1/user/resetpwd").permitAll()
                .antMatchers("/api/v1/user/inviteLinkVerification").permitAll()
                .antMatchers("/api/v1/user/resetpwdverification").permitAll()
                .antMatchers("/swagger-ui.html**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/configuration/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
//                // 权限检查
//                .antMatchers("/hello").hasAuthority("AUTH_WRITE")
//                // 角色检查
                //.antMatchers("/world").hasRole("ADMIN")
                // 所有请求需要身份认证
                // .anyRequest().authenticated()
                .anyRequest().access("@rbacService.hasPermission(request,authentication)")    //必须经过认证以后才能访问
                .and().cors()
                .and()
                // 添加一个过滤器 所有访问 /login 的请求交给 ApiLoginFilter 来处理 这个类处理所有的JWT相关内容
                .addFilterBefore(new ApiLoginFilter("/demo/login", authenticationManager(), tokenAuthenticationService),
                        UsernamePasswordAuthenticationFilter.class)
                // 添加一个过滤器验证其他请求的Token是否合法
                .addFilterBefore(apiAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class);

        //自定义异常处理
        http.exceptionHandling().authenticationEntryPoint(apiAuthenticationEntryPoint);

        // 禁用session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // ignore swagger resource
        web.ignoring().antMatchers(
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html**",
                "/webjars/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }
}

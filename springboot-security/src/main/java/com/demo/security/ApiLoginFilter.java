package com.demo.security;

import com.demo.contract.ApiCodeEnum;
import com.demo.response.APIResponse;
import com.demo.util.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 登录过滤器
 */
public class ApiLoginFilter extends AbstractAuthenticationProcessingFilter {

    public ApiLoginFilter(String pattern, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(pattern, "POST"));
        setAuthenticationManager(authManager);
    }

    /**
     * 验证
     *
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        //从请求读取对应的登录名和密码,
        ApiCredentials apiCredentials = new ObjectMapper().readValue(request.getInputStream(), ApiCredentials.class);
        return getAuthenticationManager().authenticate(
                new ApiAuthenticationToken(
                        apiCredentials.getUsername(),
                        apiCredentials.getPassword()));
    }


    /**
     * 验证成功
     *
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
    }

    /**
     * 验证失败
     *
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        //不建议使用默认失败的
        // super.unsuccessfulAuthentication(request, response, failed);
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(APIResponse.Codes.REQ_INVALID_PARAM);
        APIResponse resp = APIResponse.error(ApiCodeEnum.INCORRECT_USERNAME_PASSWORD.getCode(), ApiCodeEnum.INCORRECT_USERNAME_PASSWORD.getMsg());
        // response.getOutputStream().println(JSONUtil.toJSonString(resp));
        //解决中文乱码的问题
        response.getWriter().print(JSONUtil.toJSonString(resp));
        response.getWriter().close();
    }
}

package com.jianxilin.vhr_springboot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jianxilin.vhr_springboot.model.Hr;
import com.jianxilin.vhr_springboot.model.ResponseBean;
import com.jianxilin.vhr_springboot.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import java.io.PrintWriter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    HrService hrService;

    @Autowired
    MyFilter myFilter;

    @Autowired
    MyDecisionManager myDecisionManager;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(hrService);
    }

    @Override
    //TODO
    // 解决的问题：未登录，访问错误
    // 为什么能解决？
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/login");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //开启
        http.authorizeRequests()
            .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                @Override
                public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                    object.setAccessDecisionManager(myDecisionManager);
                    object.setSecurityMetadataSource(myFilter);
                    return object;
                }
            })
            .and()
            .formLogin()
            .usernameParameter("username").passwordParameter("password")
            // .loginPage("/login")
            .loginProcessingUrl("/doLogin")
            //登录后的操作
            .successHandler((req, resp, authentication) -> {
                resp.setContentType("application/json;charset=UTF-8");
                PrintWriter respWriter = resp.getWriter();
                Hr hr = (Hr) authentication.getPrincipal();
                hr.setPassword(null);
                ResponseBean bean = ResponseBean.success("登录成功", hr);
                respWriter.write(new ObjectMapper().writeValueAsString(bean));
                respWriter.flush();
                respWriter.close();
            })
            .failureHandler((req, resp, authenticationException) -> {
                    resp.setContentType("application/json;charset=UTF-8");
                    resp.setStatus(401);
                    PrintWriter respWriter = resp.getWriter();
                    ResponseBean respBean = ResponseBean.fail("登录失败");
                    if (authenticationException instanceof LockedException) {
                        respBean.setMsg("用户被锁定");
                    } else  if (authenticationException instanceof CredentialsExpiredException){
                        respBean.setMsg("密码过期");
                    }else if (authenticationException instanceof AccountExpiredException){
                        respBean.setMsg("账号过期");
                    }else if (authenticationException instanceof DisabledException){
                        respBean.setMsg("账号被禁用");
                    }else if (authenticationException instanceof BadCredentialsException){
                        respBean.setMsg("用户名或密码输入错误");
                    }

                    respWriter.write(new ObjectMapper().writeValueAsString(respBean));
                    respWriter.flush();
                    respWriter.close();

            })
            .permitAll()
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessHandler((req, resp, authentication) -> {
                resp.setContentType("application/json;charset=UTF-8");
                PrintWriter respWriter = resp.getWriter();

                ResponseBean bean = ResponseBean.success("退出登录成功");
                respWriter.write(new ObjectMapper().writeValueAsString(bean));
                respWriter.flush();
                respWriter.close();
            })
            .and()
            .csrf().disable();

    }

}

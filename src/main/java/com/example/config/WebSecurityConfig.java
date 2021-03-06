package com.example.config;


import com.example.entity.SysUser;
import com.example.filter.SystemHeaderWriterFilter;
import com.example.service.SysMenuService;
import com.example.util.AppUtil;
import com.example.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.header.HeaderWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启security注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //允许所有用户访问"/"和"/home"
        http.authorizeRequests()
                .antMatchers("/403").permitAll()
                .antMatchers("/expressQuery/**").permitAll() //忽略快递查询请求
                .antMatchers("/weather/**").permitAll() //忽略天气查询请求
                .antMatchers("/common/**").permitAll() //通用工具
                //忽略静态资源
                .antMatchers("/angular/**").permitAll()
                .antMatchers("/bootstrap/**").permitAll()
                .antMatchers("/jquery/**").permitAll()
                .antMatchers("/jqueryui/**").permitAll()
                .antMatchers("/websocket/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/image/**").permitAll()
                //其他地址的访问均需验证权限
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //指定登录页是"/login"
                .loginPage("/login")
                .defaultSuccessUrl("/")//登录成功后默认跳转到"/hello"
                .successHandler(loginSuccessHandler())
                .permitAll()
                .and()
                .logout()
//                .logoutSuccessHandler(new LogoutSuccessHandler() {
//                    @Override
//                    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//                        System.out.print("logout");
//                    }
//                })
                .addLogoutHandler(new LogoutHandler() {
                    @Override
                    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
                        SysMenuService sysMenuService = AppUtil.getBean(SysMenuService.class);
                        SysUser sysUser = (SysUser)authentication.getPrincipal();
                        sysMenuService.remvoeCacheByUser(sysUser);
                    }
                })
                .logoutSuccessUrl("/login")//退出登录后的默认url是"/home"
                .permitAll();

        //允许嵌入iframe
        http.addFilterBefore(this.systemHeaderWriterFilter(),FilterSecurityInterceptor.class);

    }


    //自定义校验方式
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth
            .userDetailsService(customUserDetailsService())
            .passwordEncoder(new PasswordEncoder() {
                @Override
                public String encode(CharSequence charSequence) {
                    return charSequence.toString();
                }

                @Override
                public boolean matches(CharSequence charSequence, String s) {
                    return s.equals(MD5Util.encode(charSequence.toString()));
                }
            });

    }

    /**
     * 设置用户密码的加密方式为MD5加密
     * @return
     */
    @Bean
    public Md5PasswordEncoder passwordEncoder() {
        return new Md5PasswordEncoder();

    }

    /**
     * 自定义UserDetailsService，从数据库中读取用户信息
     * @return
     */
    @Bean
    public CustomUserDetailsService customUserDetailsService(){
        return new CustomUserDetailsService();
    }

//    @Bean
//    public EmbeddedServletContainerCustomizer containerCustomizer(){
//        return new EmbeddedServletContainerCustomizer() {
//            @Override
//            public void customize(ConfigurableEmbeddedServletContainer container) {
//                ErrorPage error401Page = new ErrorPage(HttpStatus.FORBIDDEN, "/403.html");
//                ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
//                ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");
//                    container.addErrorPages(error401Page, error404Page, error500Page);
//            }
//        };
//    }

    @Bean
    public LoginSuccessHandler loginSuccessHandler(){
        return new LoginSuccessHandler();
    }

    public SystemHeaderWriterFilter systemHeaderWriterFilter(){
        /**
         * 自定义HeaderWriter，用以覆盖security默认的Header,
         * 使默认的"X-Frame-Options：DENY"禁止一切iframe调用
         * 转化为"X-Frame-Options：SAMEORIGIN"允许同域下的iframe调用
         */
        HeaderWriter headerWriter = new HeaderWriter() {
            @Override
            public void writeHeaders(HttpServletRequest request, HttpServletResponse response) {
                response.setHeader("X-Frame-Options", "SAMEORIGIN");
            }
        };
        List<HeaderWriter> headerWriters = new ArrayList<HeaderWriter>();
        headerWriters.add(headerWriter);
        return new SystemHeaderWriterFilter(headerWriters);
    }
}
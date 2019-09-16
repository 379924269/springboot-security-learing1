package com.dnp.huazai.config;

import com.dnp.huazai.authority.CustomUserDetailsService;
import com.dnp.huazai.handler.MyAuthenctiationFailureHandler;
import com.dnp.huazai.handler.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by huazai on 2018/12/12.
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private MyAuthenctiationFailureHandler myAuthenctiationFailureHandler;

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.cors();

        http.formLogin().loginPage("/login").permitAll()
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenctiationFailureHandler);

        http.logout().logoutUrl("/logout").permitAll()
                .invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login/logoutHandle");

        http.authorizeRequests()
                .antMatchers("/login/invalidSession", "/login/outLine", "/login/checkVerifyCode","/login/getVerifyCode").permitAll()
                .regexMatchers(".*swagger.*", ".*v2.*", ".*webjars.*").permitAll()
                .anyRequest().authenticated();

        http.exceptionHandling().accessDeniedPage("/login/accessDenied");

        //只允许一个用户登录,如果同一个账户两次登录,那么第一个账户将被踢下线,跳转到处理页面
        http.sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry()).expiredUrl("/login/outLine");
    }


    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }


    //    身份验证管理生成器
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return (String) charSequence;
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return s.equals(charSequence);
            }
        });
    }
}

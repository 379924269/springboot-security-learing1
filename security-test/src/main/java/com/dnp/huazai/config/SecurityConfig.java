package com.dnp.huazai.config;

import com.dnp.huazai.authority.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by huazai on 2018/12/12.
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.cors();

        http.formLogin().loginPage("/login/loginPage").permitAll()
                .successForwardUrl("/login/loginSuccessHandle")
                .failureForwardUrl("/login/loginFailureHandle");

        http.logout().logoutUrl("/logout").permitAll()
                .invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login/logoutHandle");

        http.authorizeRequests()
                .antMatchers("xxxxxx").permitAll()
                .regexMatchers(".*swagger.*", ".*v2.*", ".*webjars.*").permitAll()
                .anyRequest().authenticated();

        http.exceptionHandling().accessDeniedPage("/login/accessDenied");
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

package mcc53.com.config;

import mcc53.com.services.AppUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebConfiguration extends WebSecurityConfigurerAdapter {
    private PasswordEncoder passwordEncoder;
    private AppUserDetailService appUserDetailService;

    @Autowired
    public WebConfiguration(PasswordEncoder passwordEncoder,
                            AppUserDetailService appUserDetailService) {
        this.passwordEncoder = passwordEncoder;
        this.appUserDetailService = appUserDetailService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(appUserDetailService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .authorizeRequests()
//                .antMatchers("/department").hasAnyRole("ADMIN", "EMPLOYEE")
                .antMatchers("/user/**", "/employee/**", "/project/**", "/email/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
}

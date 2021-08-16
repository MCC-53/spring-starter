package mcc53.com.security;

import mcc53.com.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private AppUserService appUserService;
    private BcryptEncode bcryptEncode;

    @Autowired
    public WebSecurityConfig(AppUserService appUserService, BcryptEncode bcryptEncode) {
        this.appUserService = appUserService;
        this.bcryptEncode = bcryptEncode;
    }

    public WebSecurityConfig(boolean disableDefaults, AppUserService appUserService, BcryptEncode bcryptEncode) {
        super(disableDefaults);
        this.appUserService = appUserService;
        this.bcryptEncode = bcryptEncode;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {//Configurasi page http yang diizinkan tanpa harus auth
        http.csrf().disable() //Disable auth untuk halaman yg ditunjuk
                .cors().and()
                .authorizeRequests().antMatchers("/auth/register").permitAll() //Berikan izin ke halaman /auth/register
                .antMatchers("/department").permitAll()
                .antMatchers("/project").permitAll()
                .antMatchers("/department/{id}").permitAll()
                .antMatchers("/email").permitAll()
                .antMatchers("/auth/**").permitAll()
                .anyRequest().fullyAuthenticated() //Selain halaman /auth/register seluruhnya harus melakukan authentikasi penuh
                .and().httpBasic(); //Dengan method http basic => Mungkin auth nya harus Basic Auth
    }

    @Bean
    public WebMvcConfigurer corsConfig(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("GET","POST","DELETE","PUT").allowedHeaders("*").allowedOrigins("http://localhost:8088");
            }
        };
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){ //Mengenalkan class service ke spring security nya
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bcryptEncode.bcryptEncoder()); //Agar spring security tau Pasword menggunakan Encoder
        provider.setUserDetailsService(appUserService); //Implementasi UserDetailService adalah kelas appUserService
        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { //Panggil DaoAuthenticationProvider
        auth.authenticationProvider(daoAuthenticationProvider()); //kita set authentionProvider nya dengan objek daoAuthenticationProvider

    }
}

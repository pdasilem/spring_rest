package ru.pdasilem.spring_rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.pdasilem.spring_rest.config.handler.LoginSuccessHandler;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final LoginSuccessHandler loginSuccessHandler;

    @Autowired
    public SecurityConfig(@Qualifier("userDetailServiceImp") UserDetailsService userDetailsService
            , LoginSuccessHandler loginSuccessHandler){
        this.userDetailsService = userDetailsService;
        this.loginSuccessHandler = loginSuccessHandler;
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                // указываем страницу с формой логина
                .loginPage("/login")
                //указываем логику обработки при логине
                .successHandler(loginSuccessHandler)
                // указываем action с формы логина
                .loginProcessingUrl("/login")
                // Указываем параметры логина и пароля с формы логина
                .usernameParameter("email")
                .passwordParameter("passw")
                // даем доступ к форме логина всем
                .permitAll();

        http.logout()
                // разрешаем делать логаут всем
                .permitAll()
                // указываем URL логаута
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                // указываем URL при удачном логауте
                .logoutSuccessUrl("/login?logout")
                //выклчаем кроссдоменную секьюрность (на этапе обучения неважна)
                .and().csrf().disable();

        http
                // делаем страницу регистрации недоступной для авторизированных пользователей
                .authorizeRequests()
                //страницы аутентификаци доступна всем
                .antMatchers("/login").anonymous()
                // защищенные URL
                .antMatchers("/user/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/favicon.ico").permitAll()
                .anyRequest().authenticated();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public FilterRegistrationBean hiddenHttpMethodFilter() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new HiddenHttpMethodFilter());
//        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
//        return filterRegistrationBean;
//    }

}

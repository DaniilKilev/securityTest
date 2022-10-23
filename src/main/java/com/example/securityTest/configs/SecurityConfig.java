package com.example.securityTest.configs;

import com.example.securityTest.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/auth/**").authenticated()
                .antMatchers("/only_for_admins/**").hasRole("ADMIN")
                .antMatchers("/read_profile/**").hasAuthority("READ_PROFILE")
                .and()
                .formLogin()
                .and()
                .logout().logoutSuccessUrl("/");
    }
    //todo InMemory (1 ВАРИАНТ)
//    @Bean
//    public UserDetailsService users(){
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{bcrypt}$2a$10$6Wu8u.gDgUs3BVJeoC8emOh.RD/mf/ktlhVY7Y2pFE82jwUPlMYLa")
//                .roles("USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("ADMIN")
//                .password("{bcrypt}$2a$10$6Wu8u.gDgUs3BVJeoC8emOh.RD/mf/ktlhVY7Y2pFE82jwUPlMYLa")
//                .roles("USER","ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user,admin);
//    }

    //todo JDBCAuthentication (2 ВАРИАНТ)
//    @Bean
//    public JdbcUserDetailsManager users(DataSource dataSource){
////                UserDetails user = User.builder()
////                .username("user")
////                .password("{bcrypt}$2a$10$6Wu8u.gDgUs3BVJeoC8emOh.RD/mf/ktlhVY7Y2pFE82jwUPlMYLa")
////                .roles("USER")
////                .build();
////        UserDetails admin = User.builder()
////                .username("ADMIN")
////                .password("{bcrypt}$2a$10$6Wu8u.gDgUs3BVJeoC8emOh.RD/mf/ktlhVY7Y2pFE82jwUPlMYLa")
////                .roles("USER","ADMIN")
////                .build();
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
////        if(jdbcUserDetailsManager.userExists(user.getUsername())){
////            jdbcUserDetailsManager.deleteUser(user.getUsername());
////        }
////        if(jdbcUserDetailsManager.userExists(admin.getUsername())){
////            jdbcUserDetailsManager.deleteUser(admin.getUsername());
////        }
////        jdbcUserDetailsManager.createUser(user);
////        jdbcUserDetailsManager.createUser(admin);
//        return jdbcUserDetailsManager;
//    }
    //todo DAOAuthenticationProvider(3 ВАРИАНТ)

    //<a href = 'https://youtu.be/HvovW6Uh1yU?t=5161'>
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }

}

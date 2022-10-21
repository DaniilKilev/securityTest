package com.example.securityTest.configs;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

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

    //<a href = 'https://youtu.be/HvovW6Uh1yU?t=3751'>


}

package com.example.job4j_url_shortcut.config;

import com.example.job4j_url_shortcut.security.filter.JWTAuthenticationFilter;
import com.example.job4j_url_shortcut.security.filter.JWTAuthorizationFilter;
import lombok.AllArgsConstructor;
    import org.springframework.context.annotation.Bean;
    import org.springframework.http.HttpMethod;
    import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
    import org.springframework.security.config.http.SessionCreationPolicy;
    import org.springframework.security.crypto.password.*;
    import org.springframework.web.cors.CorsConfiguration;
    import org.springframework.web.cors.CorsConfigurationSource;
    import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import static com.example.job4j_url_shortcut.security.filter.JWTAuthenticationFilter.SIGN_UP_URL;

    @EnableWebSecurity
    @AllArgsConstructor
    public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        private final UserDetailsServiceImpl userDetailsService;
        private final PasswordEncoder delegatingPasswordEncoder;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.cors().and().csrf().disable().authorizeRequests()
                    .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                    .antMatchers(HttpMethod.GET, "/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                    .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        }

        @Override
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(delegatingPasswordEncoder);
        }

        @Bean
        CorsConfigurationSource corsConfigurationSource() {
            final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
            return source;
        }
    }
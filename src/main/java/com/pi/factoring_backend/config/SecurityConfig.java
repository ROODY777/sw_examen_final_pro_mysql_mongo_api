package com.pi.factoring_backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.pi.factoring_backend.security.CustomUserDetailsService;
import com.pi.factoring_backend.security.JwtAuthenticationEntryPoint;
import com.pi.factoring_backend.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	private AuthenticationManager authenticationManager;
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	@Bean
	public JwtAuthenticationFilter jwtFilter() {
		return new JwtAuthenticationFilter();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
		builder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
		
		authenticationManager = builder.build();
		http.authenticationManager(authenticationManager);
		
		http.csrf().disable();
		http.cors();
		http.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.authorizeHttpRequests((auth) -> auth
			.requestMatchers(
					new AntPathRequestMatcher("/**"),
					new AntPathRequestMatcher("/media/**"),
					new AntPathRequestMatcher("/usuario"),
					new AntPathRequestMatcher("/usuario/login"),
					new AntPathRequestMatcher("/usuario/bcrypt/**"),
					new AntPathRequestMatcher("/usuario/correo/**")
			).permitAll()
			.anyRequest().authenticated()
		);
		
		http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);
		http.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
		//http.addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

		
		return http.build();
	}
	
}

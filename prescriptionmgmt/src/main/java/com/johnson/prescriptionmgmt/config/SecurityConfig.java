package com.johnson.prescriptionmgmt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.johnson.prescriptionmgmt.service.CustomUserDetailsService;

/**
 * Spring boot 3.0 security configuration with authority levels set through filter chain
 * @author Hillary
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Bean
	public static PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();

	}

	/**Set privlidges based on authority, 
	 * set custom login page, 
	 * redirect to home, 
	 * and manage  number of active sessions
	 * @param http
	 * @return
	 * @throws Exception
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeHttpRequests((authorize) -> authorize
			.requestMatchers("/index", "/register/**").permitAll()
			.requestMatchers("/styles/**", "/script/**", "/images/**").permitAll()
			.requestMatchers("/addrx/**", "/pharmacy/**").hasAnyAuthority("USER", "ADMIN")
			.requestMatchers("/viewissues/**", "/users/**", "/refills/**").hasAuthority("ADMIN")
			.requestMatchers("/home/**").authenticated()
			.anyRequest().authenticated())
		.formLogin(form -> form
			.loginPage("/login")
			.loginProcessingUrl("/login")
			.defaultSuccessUrl("/home", true).permitAll())		
		.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.and()
			.exceptionHandling().accessDeniedPage("/noaccess")
		.and()
			.sessionManagement()				
			.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
			.maximumSessions(2);

		return http.build();
	}

	
	/**
	 * Point auth manager to the user details service and password encoder used
	 * @param authenticationManagerBuilder
	 * @throws Exception
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		System.out.println("Authentication manager builder just ran");
	    authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(encoder());
	}

}
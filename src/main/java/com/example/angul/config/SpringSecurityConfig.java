package com.example.angul.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("surya").password("{noop}password").roles("USER");
	}

	// security for all api

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors();
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/**").fullyAuthenticated().and().httpBasic();
	}

	// Security based on url
	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * http.csrf().disable();
	 * http.authorizeRequests().antMatchers("/application/**").fullyAuthenticated().
	 * and().httpBasic(); }
	 */

	// security based on role
	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * http.csrf().disable();
	 * http.authorizeRequests().antMatchers("/application/**").hasAnyRole("ADMIN").
	 * anyRequest().fullyAuthenticated().and().httpBasic(); }
	 */

	/*
	 * @Bean public static NoOpPasswordEncoder passwordEncoder() { return
	 * (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance(); }
	 */
}

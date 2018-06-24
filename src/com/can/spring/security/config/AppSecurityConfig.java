package com.can.spring.security.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

// step 2 -> WebSecurityConfigurerAdapter extends et
// enableWebSecurity annotation kullanilmali
// hangi ozelligi kullanmak istersek metodu override etmeliyiz
@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource securityDataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// add our users for in memory authentication
		/*@SuppressWarnings("deprecation")
		UserBuilder userBuilder = User.withDefaultPasswordEncoder();

		auth.inMemoryAuthentication().withUser(userBuilder.username("can").password("test123").roles("EMPLOYEE"))
				.withUser(userBuilder.username("cem").password("test123").roles("MANAGER", "EMPLOYEE"))
				.withUser(userBuilder.username("ahmet").password("test123").roles("ADMIN", "EMPLOYEE"));*/
		
		// jdbc authentication
		auth.jdbcAuthentication().dataSource(securityDataSource);
		
	}

	
	// configure security of web paths in application, login, logout
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http.authorizeRequests() // restrict access based on HttpServletRequest -> erisimi "HttpServletRequest" ile kisitlayin
			.antMatchers("/").hasRole("EMPLOYEE")
			.antMatchers("/leaders/**").hasRole("MANAGER")
			.antMatchers("/systems/**").hasRole("ADMIN")
			// .anyRequest().authenticated() // any request to the app must be authenticated(be logged in) 
			.and()
			.formLogin() // Customizing the form login process 
			.loginPage("/showMyLoginPage")  // show our custom form at the request mapping 
			.loginProcessingUrl("/authenticateTheUser") // login form should POST to the url for processing
			.permitAll() // allow everyone to see login page. No need to be logged in
			.and().logout() // add logout support for default URL /logout
			.permitAll() // permitAll -> herhangi biri bu sayfaya ulasabilir
			.and().exceptionHandling().accessDeniedPage("/access-denied"); // yetkisiz sayfaya gidilirse bu link calisacak
		
	}
	
	

}

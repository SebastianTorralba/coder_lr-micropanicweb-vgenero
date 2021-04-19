package ar.com.coder.micropanicweb.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
//@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Value("${spring.queries.users-query}")
//    private String usersQuery;
//
//    @Value("${spring.queries.roles-query}")
//    private String rolesQuery;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery(usersQuery)
//                .authoritiesByUsernameQuery(rolesQuery)
//                .passwordEncoder(bCryptPasswordEncoder);

        auth.inMemoryAuthentication()
                .withUser("twoboot@gmail.com").password(bCryptPasswordEncoder.encode("twoboot")).roles("ADMIN").and()
                .withUser("ncerezo.utn@gmail.com").password(bCryptPasswordEncoder.encode("123456")).roles("ADMIN").and()
                .withUser("leo.garay9@gmail.com").password(bCryptPasswordEncoder.encode("420513")).roles("ADMIN").and()
                .withUser("jorgtledo@gmail.com").password(bCryptPasswordEncoder.encode("123456")).roles("ADMIN").and()
                .withUser("eduardo.aldeco1992@gmail.com").password(bCryptPasswordEncoder.encode("123456")).roles("ADMIN");
        
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.
//                authorizeRequests()
//                .antMatchers("/**").permitAll()
//                .antMatchers("/auth/**").permitAll()
//                .antMatchers("/login").permitAll()
//                .antMatchers("/api/**").permitAll()
//                .antMatchers("/swagger-resources/**").permitAll().anyRequest().fullyAuthenticated()
//                .antMatchers("/a/**").hasAuthority("ADMIN").anyRequest()
//                .authenticated().and().csrf().disable().formLogin()
//                .loginPage("/login").failureUrl("/login?error=true")
//                .defaultSuccessUrl("/")
//                .usernameParameter("username")
//                .passwordParameter("clave")
//                .and().logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/login").and().exceptionHandling()
//                .accessDeniedPage("/access-denied");
        http.csrf().disable()
                .authorizeRequests()
                //.antMatchers("/**").permitAll()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/resetPwd").permitAll()
                .antMatchers("/api/usuarios/**").permitAll()
                .antMatchers("/swagger-resources/**").hasAnyRole("ADMIN")
                .antMatchers("/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").failureUrl("/login?error=true")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").and().exceptionHandling()
                .accessDeniedPage("/access-denied")
                // .permitAll()
                .and()
                .exceptionHandling().
                accessDeniedPage("/access-denied");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/dist/**", "/bower_components/**", "/css/**", "/js/**", "/img/**");
    }

}

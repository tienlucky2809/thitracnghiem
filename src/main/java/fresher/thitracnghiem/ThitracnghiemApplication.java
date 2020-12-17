package fresher.thitracnghiem;

import fresher.thitracnghiem.service.impl.UserDetailsServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@SpringBootApplication
@EnableJpaAuditing
public class ThitracnghiemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThitracnghiemApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }

    @EnableWebMvc
    public class WebConfig extends WebMvcConfigurerAdapter {

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler(
                    "/admin/vendor/**"
                    , "/admin/webfonts/**",
                    "/admin/css/**",
                    "/admin/js/**",
                    "/member/images/**",
                    "/member/img/**",
                    "/member/css/**",
                    "/member/js/**",
                    "/member/fonts/**")
                    .addResourceLocations(
                            "classpath:/static/admin/vendor/",
                            "classpath:/static/admin/vendor/fontawesome-free/webfonts/",
                            "classpath:/static/admin/css/",
                            "classpath:/static/admin/js/",
                            "classpath:/static/member/images/",
                            "classpath:/static/member/img/",
                            "classpath:/static/member/css/",
                            "classpath:/static/member/js/",
                            "classpath:/static/member/fonts/");

        }

    }

    @EnableWebSecurity
    public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        @Autowired
        private UserDetailsServiceImpl userDetailsService;

        @Autowired
        private DataSource dataSource;

        @Bean
        public BCryptPasswordEncoder passwordEncoder() {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            return bCryptPasswordEncoder;
        }


        @Bean
        public AuthenticationManager customAuthenticationManager() throws Exception {
            return authenticationManager();
        }

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

            // Sét đặt dịch vụ để tìm kiếm User trong Database.
            // Và sét đặt PasswordEncoder.
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http.csrf().disable();

            // Các trang không yêu cầu login
            http.authorizeRequests().antMatchers("/", "/login", "/logout").permitAll();

            // Trang /userInfo yêu cầu phải login với vai trò ROLE_USER hoặc ROLE_ADMIN.
            // Nếu chưa login, nó sẽ redirect tới trang /login.
            http.authorizeRequests().antMatchers("/userInfo", "/result/**", "/doing-test/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");

            // Trang chỉ dành cho ADMIN
            http.authorizeRequests().antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')");

            // Khi người dùng đã login, với vai trò XX.
            // Nhưng truy cập vào trang yêu cầu vai trò YY,
            // Ngoại lệ AccessDeniedException sẽ ném ra.
            http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

            // Cấu hình cho Login Form.
            http.authorizeRequests().and().formLogin()//
                    // Submit URL của trang login
                    .loginProcessingUrl("/j_spring_security_check") // Submit URL
                    .loginPage("/login")//
                    .defaultSuccessUrl("/home")//
                    .failureUrl("/login?error=true")//
                    .usernameParameter("username")//
                    .passwordParameter("password")
                    // Cấu hình cho Logout Page.
                    .and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
        }


    }
}

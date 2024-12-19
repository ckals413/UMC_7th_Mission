package umc.spring.config.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
              //  .csrf(csrf -> csrf.disable())  // CSRF 보호 비활성화 (테스트를 위해)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/home", "/signup", "/members/signup", "/css/**", "/login").permitAll()  // URL 패턴에 대한 접근권한 설정
                        .requestMatchers("/admin/**").hasRole("ADMIN") //ADMIN 역할을 가진 사용자만 접근가능
                        .anyRequest().authenticated() //그외 모든 요청에 대해 인증요구
                )
                .formLogin((form) -> form
                        .loginPage("/login") //커스텀 로그인페이지를 로그인 경로로 지정
                        .defaultSuccessUrl("/home", true)//로그인 성공시 home으로 리 다이렉트
                        .permitAll() //모든 사용자가 접근가능
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout") //로그아웃 성공시 리 다이랙트
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); //비밀번호를 암호화해 저장하기 위해
    }
}
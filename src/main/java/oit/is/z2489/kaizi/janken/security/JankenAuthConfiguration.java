package oit.is.z2489.kaizi.janken.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class JankenAuthConfiguration {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .formLogin(login -> login // ログインフォームの設定
            .permitAll()) // 全ユーザーにログインフォームを許可
        .logout(logout -> logout
            .logoutUrl("/logout") // ログアウトのURL設定
            .logoutSuccessUrl("/")) // ログアウト後にリダイレクト
        .authorizeHttpRequests(authz -> authz
            .requestMatchers("/janken").authenticated() // /jankenにアクセスするには認証が必要
            .anyRequest().permitAll()) // その他のリクエストは認証不要
        .csrf(csrf -> csrf.disable()); // CSRF保護の無効化（テスト用）
    return http.build();
  }

  @Bean
  public InMemoryUserDetailsManager userDetailsService() {
    UserDetails user1 = User.withUsername("user1")
        .password("{bcrypt}$2y$05$J3JqhETodj6mNscCS3Km9OnqY.AWyFAHEkWbpbJipmXNYwhc.V6zO") // isdevのbcryptハッシュ
        .roles("USER").build();
    UserDetails user2 = User.withUsername("user2")
        .password("{bcrypt}$2y$05$RZcC5nK0DkaOpPVSidS9cObKWHtMfbt7NipYNleBVEWFR1qnpKImO") // isdevのbcryptハッシュ
        .roles("USER").build();
    return new InMemoryUserDetailsManager(user1, user2);
  }
}

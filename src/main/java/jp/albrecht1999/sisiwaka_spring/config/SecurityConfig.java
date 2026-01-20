package jp.albrecht1999.sisiwaka_spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http
				// ===== 認可ルール =====
				.authorizeHttpRequests(auth -> auth
						// 公開ページ
						.requestMatchers(
								"/",
								"/works/**",
								"/css/**",
								"/scripts/**",
								"/error",
								"/favicon.ico",
								"/images/**")
						.permitAll()

						// 管理ページ（ログイン必須）
						.requestMatchers("/admin/**").authenticated()

						// それ以外は全部拒否（事故防止）
						.anyRequest().denyAll())

				// ===== ログイン設定（標準ログイン画面を使用） =====
				.formLogin(form -> form
						.defaultSuccessUrl("/admin", true))

				// ===== ログアウト =====
				.logout(logout -> logout
						.logoutSuccessUrl("/"));

		return http.build();
	}

	// ===== パスワードの暗号化 =====
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// ===== 学習用ユーザー定義（メモリ上） =====
	@Bean
	UserDetailsService userDetailsService(PasswordEncoder encoder) {

		String pw = System.getenv("ADMIN_PASSWORD");
		if (pw == null || pw.isBlank()) {
			throw new IllegalStateException("ADMIN_PASSWORD is not set");
		}

		UserDetails admin = User.withUsername("sisiwaka_admin")
				.password(encoder.encode(pw))
				.roles("ADMIN")
				.build();

		return new InMemoryUserDetailsManager(admin);
	}
}

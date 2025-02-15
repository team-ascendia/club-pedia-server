package clubpedia.in.net.clubpedia.global.config;

import clubpedia.in.net.clubpedia.global.filter.JwtAuthenticationFilter;
import clubpedia.in.net.clubpedia.global.util.JwtUtil;
import clubpedia.in.net.clubpedia.repository.MemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final MemberRepository memberRepository;

    public SecurityConfig(JwtUtil jwtUtil, MemberRepository memberRepository) {
        this.jwtUtil = jwtUtil;
        this.memberRepository = memberRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        List<String> excludedEndpoints = List.of(
                "/v3/api-docs/**",
                "/swagger-ui/**",
                "/api-docs",
                "/auth/social/**",
                "/error"
        );
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(excludedEndpoints.toArray(new String[0])).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterAfter(new JwtAuthenticationFilter(jwtUtil, memberRepository, excludedEndpoints), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

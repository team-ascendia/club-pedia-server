package clubpedia.in.net.clubpedia.global.filter;


import clubpedia.in.net.clubpedia.global.util.JwtUtil;
import clubpedia.in.net.clubpedia.member.domain.Member;
import clubpedia.in.net.clubpedia.member.repository.MemberRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final MemberRepository memberRepository;
    private final List<String> excludedEndpoints;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, MemberRepository memberRepository, List<String> excludedEndpoints) {
        this.jwtUtil = jwtUtil;
        this.memberRepository = memberRepository;
        this.excludedEndpoints = excludedEndpoints;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        // SecurityConfig에서 `permitAll()`로 지정된 엔드포인트는 자동으로 필터를 건너뛴다.
        if (excludedEndpoints.stream().anyMatch(endpoint -> requestURI.startsWith("/api" + endpoint.replace("/**", "")))) {
            chain.doFilter(request, response);
            return;
        }

        String token = resolveToken(request);

        if (token != null && jwtUtil.validateToken(token)) {
            String userId = jwtUtil.getUserIdFromToken(token);
            Optional<Member> optionalMember = memberRepository.findById(Long.parseLong(userId));
            if (optionalMember.isPresent()) {
                Member member = optionalMember.get();

                // Spring Security의 UserDetails 생성
                UserDetails userDetails = User.withUsername(member.getEmail())
                        .authorities(Collections.singletonList(new SimpleGrantedAuthority("USER")))
                        .password("")
                        .build();

                Authentication auth = new UsernamePasswordAuthenticationToken(member, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        chain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}

package org.bookstore.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
// Configuración de seguridad para la aplicación de la librería
@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {

    private final Oauth2AuthenticationEntryPoint authenticationEntryPoint;
    private final Oauth2AccessDeniedHandler accessDeniedHandler;
    private final JwtAuthenticationConverter jwtAuthenticationConverter;

    public SecurityConfig(Oauth2AuthenticationEntryPoint authenticationEntryPoint, Oauth2AccessDeniedHandler accessDeniedHandler, JwtAuthenticationConverter jwtAuthenticationConverter) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.accessDeniedHandler = accessDeniedHandler;
        this.jwtAuthenticationConverter = jwtAuthenticationConverter;
    }
// Configuración del filtro de seguridad
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity){

        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                        .httpBasic(AbstractHttpConfigurer::disable)
                                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authorizeHttpRequests(authorize->authorize.requestMatchers(HttpMethod.POST,"/usuario/crearUsuario").permitAll().anyRequest().authenticated())
                                .oauth2ResourceServer(resource-> resource.jwt(jwt->jwt
                                        .jwtAuthenticationConverter(jwtAuthenticationConverter))
                                        .authenticationEntryPoint(authenticationEntryPoint).accessDeniedHandler(accessDeniedHandler));

        return httpSecurity.build();
    }

}

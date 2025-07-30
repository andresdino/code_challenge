package com.ms.prueba.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * Clase de configuración de seguridad para Spring Security.
 * Se encarga de definir los filtros, rutas públicas y protegidas,
 * así como el proveedor de autenticación personalizado y el manejo de tokens JWT.
 */
@Configuration
@EnableWebSecurity
@EnableCaching 
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    /**
     * Define la cadena de filtros de seguridad de Spring Security.
     *
     * @param http            objeto HttpSecurity para configurar las reglas de seguridad
     * @param authProvider    proveedor de autenticación personalizado
     * @param successHandler  manejador de autenticación exitosa para el login
     * @param jwtFilter       filtro personalizado para validar tokens JWT
     * @return la cadena de filtros configurada
     * @throws Exception en caso de error en la configuración
     */
    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http,
            CustomAuthenticationProvider authProvider,
            JwtAuthenticationSuccessHandler successHandler,
            JwtAuthenticationFilter jwtFilter
    ) throws Exception {
        http
                
                .csrf(csrf -> csrf.disable())

                
                .authenticationProvider(authProvider)

                
                .addFilterBefore(jwtFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class)

                
                .authorizeHttpRequests(auth -> auth
                        
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/**").hasAnyRole("ADMIN", "USER","MODERATOR")

                        .requestMatchers(
                                "/login",
                                "/css/**",
                                "/js/**",
                                "/api/v1/customer/createUser",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-token",
                                "/prueba/swagger-token",
                                "/actuator"
                        ).permitAll()
                       
                        
                        .anyRequest().authenticated()
                )


                
                .formLogin(form -> form
                        .successHandler(successHandler) 
                        .permitAll()
                )

                
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout") 
                        .permitAll()
                );

        return http.build(); 
    }

    /**
     * Configura y expone el AuthenticationManager a partir del AuthenticationConfiguration.
     *
     * @param config configuración de autenticación automática de Spring
     * @return instancia de AuthenticationManager
     * @throws Exception si falla la inicialización
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}

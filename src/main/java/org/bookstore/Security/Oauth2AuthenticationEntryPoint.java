package org.bookstore.Security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
// Punto de entrada personalizado para manejar errores de autenticación en OAuth2
@Component
public class Oauth2AuthenticationEntryPoint implements AuthenticationEntryPoint {
// Método para manejar las excepciones de autenticación
    @Override
    public void commence(@NonNull HttpServletRequest  request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
// Configuramos la respuesta HTTP con el estado 401 y el tipo de contenido JSON
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        Map<String,String>mensaje=new HashMap<>();

        mensaje.put("tipoError", String.valueOf(401));
        mensaje.put("error","UNAUTHORIZED");
        mensaje.put("mensaje",authException.getLocalizedMessage());
        mensaje.put("fecha", String.valueOf(LocalDateTime.now()));
// Convertimos el mapa de mensaje a JSON y lo escribimos en la respuesta
        String json= new ObjectMapper().writeValueAsString(mensaje);
// Escribimos el JSON en la respuesta HTTP
        response.getWriter().write(json);

    }
}

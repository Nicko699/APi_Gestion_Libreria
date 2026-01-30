package org.bookstore.Security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
// Manejador personalizado para respuestas de acceso denegado en OAuth2
@Component
public class Oauth2AccessDeniedHandler implements AccessDeniedHandler {
// Método para manejar las excepciones de acceso denegado
    @Override
    public void handle(@NonNull HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
// Configuramos la respuesta HTTP con el estado 403 y el tipo de contenido JSON
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        Map<String,String>mensaje=new HashMap<>();

        mensaje.put("tipoError", String.valueOf(403));
        mensaje.put("error","FORBIDDEN");
        mensaje.put("mensaje", accessDeniedException.getLocalizedMessage());
        mensaje.put("fecha", String.valueOf(LocalDateTime.now()));
// Convertimos el mapa de mensaje a JSON y lo escribimos en la respuesta
        String json=new ObjectMapper().writeValueAsString(mensaje);
// Escribimos el JSON en la respuesta HTTP
        response.getWriter().write(json);
    }
}

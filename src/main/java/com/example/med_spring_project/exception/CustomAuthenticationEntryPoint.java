// Définit le package auquel appartient cette classe.
package com.example.med_spring_project.exception;

// Importe ta classe Response utilisée pour construire la réponse JSON.
import com.example.med_spring_project.res.Response;

// Imports liés à la gestion des requêtes/réponses HTTP avec Jakarta Servlet.
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Génère automatiquement le constructeur nécessaire pour les champs final.
import lombok.RequiredArgsConstructor;

// Permet d'utiliser les différents codes HTTP, comme 401 Unauthorized.
import org.springframework.http.HttpStatus;

// Interface de Spring Security permettant de gérer les erreurs d'authentification.
import org.springframework.security.web.AuthenticationEntryPoint;

// Indique à Spring que cette classe est un composant géré automatiquement.
import org.springframework.stereotype.Component;

// ObjectMapper permet de transformer un objet Java en JSON.
import tools.jackson.databind.ObjectMapper;

// Exception utilisée par Spring Security lors d'un problème d'authentification.
import org.springframework.security.core.AuthenticationException;
import java.io.IOException;

// Indique que Spring doit créer automatiquement une instance de cette classe.
@Component

// Lombok génère automatiquement un constructeur avec les champs final.
@RequiredArgsConstructor

// Cette classe permet de personnaliser la réponse lorsqu'un utilisateur
// n'est pas authentifié ou que son authentification échoue.
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    // ObjectMapper sera injecté automatiquement par Spring grâce
    // à @RequiredArgsConstructor.
    private final ObjectMapper objectMapper;

    // Méthode appelée automatiquement par Spring Security
    // lorsqu'une requête nécessite une authentification
    // mais que l'utilisateur n'est pas correctement authentifié.
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authenticationException) throws IOException, ServletException {

        // Création de notre réponse personnalisée.
        //
        // Response<?> signifie que la réponse peut contenir
        // n'importe quel type de données.
        Response<?> responseResponse = Response.builder()
                // Définit le code HTTP : 401 Unauthorized.
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                // Récupère le message de l'exception générée
                // par Spring Security.
                .message(authenticationException.getMessage())
                // Termine la construction de l'objet Response.
                .build();
        // Transforme notre objet Java responseResponse en JSON
        // puis écrit ce JSON dans la réponse HTTP.
        response.setContentType("application/json");
        // Définit le statut HTTP de la réponse à 401.
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        // Transforme notre objet Java responseResponse en JSON
        // puis écrit ce JSON dans la réponse HTTP.
        response.getWriter().write(objectMapper.writeValueAsString(responseResponse));

    }
}

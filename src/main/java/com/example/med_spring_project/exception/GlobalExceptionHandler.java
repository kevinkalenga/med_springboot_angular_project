package com.example.med_spring_project.exception;

import com.example.med_spring_project.res.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    /*
     * 1. Exception générale / inattendue
     *
     * Capture toutes les exceptions qui ne sont pas gérées
     * par un handler plus spécifique.
     *
     * Retourne une réponse HTTP 500 (Internal Server Error).
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<?>> handleAllUnknownExceptions(Exception ex){
        Response<?> response = Response.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*
     * 2. Ressource introuvable
     *
     * Utilisée lorsqu'une ressource demandée n'existe pas
     * ou n'a pas été trouvée dans l'application.
     *
     * Retourne une réponse HTTP 404 (Not Found).
     */

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response<?>> handleAllNotFoundExceptions(NotFoundException ex){
        Response<?> response = Response.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


    /*
     * 3. Requête invalide
     *
     * Utilisée lorsque les données envoyées par le client
     * sont invalides ou ne respectent pas les règles attendues
     * par l'application.
     *
     * Retourne une réponse HTTP 400 (Bad Request).
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Response<?>> handleAllBadRequestExceptions(BadRequestException ex){
        Response<?> response = Response.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}

/**
 * Celina Wright
 * ckwright@sfu.ca
 * 301380112
 */

package ca.cmpt213.a4.onlinehangman.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class for representing the exception: game not found
 */

@ResponseStatus(
        value = HttpStatus.NOT_FOUND,
        reason = "Game Not Found."
)
public class GameNotFoundException extends RuntimeException{
    public void GameNotFoundException(){
        //Nothing needed here
    }
}

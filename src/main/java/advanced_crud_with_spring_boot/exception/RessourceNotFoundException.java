package advanced_crud_with_spring_boot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RessourceNotFoundException extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "No records for this id";

    public RessourceNotFoundException(){
        super(DEFAULT_MESSAGE);
    }
    public RessourceNotFoundException(String message){
        super(message);
    }
}

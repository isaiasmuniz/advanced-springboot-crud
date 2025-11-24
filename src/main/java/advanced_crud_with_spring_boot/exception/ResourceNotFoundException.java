package advanced_crud_with_spring_boot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "No records for this id";

    public ResourceNotFoundException(){
        super(DEFAULT_MESSAGE);
    }
    public ResourceNotFoundException(String message){
        super(message);
    }
}

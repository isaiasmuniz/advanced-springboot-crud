package advanced_crud_with_spring_boot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestEcpetion extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Bad request";

    public BadRequestEcpetion() {
        super(DEFAULT_MESSAGE);
    }
}

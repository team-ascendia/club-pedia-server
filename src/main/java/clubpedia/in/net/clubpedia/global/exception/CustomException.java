package clubpedia.in.net.clubpedia.global.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final ExceptionType exceptionType;
    private final Object details;

    public CustomException(ExceptionType exceptionType, Object details) {
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
        this.details = details;
    }

    public CustomException(ExceptionType exceptionType) {
        this(exceptionType, null);
    }
}

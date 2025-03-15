package clubpedia.in.net.clubpedia.global.exception;


import org.springframework.http.HttpStatus;
import lombok.Getter;

@Getter
public enum ExceptionType {
    // 400 BAD_REQUEST 관련
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    VALIDATION_FAILED(HttpStatus.BAD_REQUEST, "유효성 검증에 실패했습니다."),
    DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, "이미 가입된 이메일입니다."),
    INVALID_CODE(HttpStatus.BAD_REQUEST, "코드가 유효하지 않습니다."),

    // 401 UNAUTHORIZED 관련
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증이 필요합니다."),

    // 403 FORBIDDEN 관련
    FORBIDDEN(HttpStatus.FORBIDDEN, "접근 권한이 없습니다."),

    // 404 NOT_FOUND 관련
    NOT_FOUND(HttpStatus.NOT_FOUND, "요청한 리소스를 찾을 수 없습니다."),

    // 500 SERVER_ERROR 관련
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버에 예기치 못한 오류가 발생했습니다.");

    private final HttpStatus status;
    private final String message;

    ExceptionType(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}

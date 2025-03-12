package clubpedia.in.net.clubpedia.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;


@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. @Valid 유효성 검증 실패 시 (BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            fieldErrors.put(error.getField(), error.getDefaultMessage());
        }

        return createErrorResponse(ExceptionType.VALIDATION_FAILED, fieldErrors);
    }

    // 2. 커스텀 예외 발생 시
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Map<String, Object>> handleCustomException(CustomException ex) {
        return createErrorResponse(ex.getExceptionType(), ex.getDetails());
    }

    // 3. 예기치 못한 서버 에러 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGlobalException(Exception ex) {
        HashMap<Integer, ExceptionType> statusMap = new HashMap<>();
        statusMap.put(400, ExceptionType.BAD_REQUEST);
        statusMap.put(401, ExceptionType.UNAUTHORIZED);
        statusMap.put(403, ExceptionType.FORBIDDEN);
        statusMap.put(500, ExceptionType.SERVER_ERROR);

        int statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value(); // 기본값 500
        String message = ex.getMessage();
        if (ex instanceof ResponseStatusException) {
            statusCode = ((ResponseStatusException) ex).getStatusCode().value();
            message = ((ResponseStatusException) ex).getReason();
        }
        return createErrorResponse(statusMap.get(statusCode), message);
    }


    // 3. IllegalArgumentException 처리
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        return createErrorResponse(ExceptionType.BAD_REQUEST, ex.getMessage());
    }

    // 4. @DateTimeFormat이 잘못된 경우 (예: "yyyy-MM-dd"가 아닌 값 입력)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, Object>> handleTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return createErrorResponse(ExceptionType.BAD_REQUEST, ex.getMessage());
    }

    // 공통 에러 응답 생성 메서드
    private ResponseEntity<Map<String, Object>> createErrorResponse(ExceptionType exceptionType, Object details) {
        Map<String, Object> errorResponse = new LinkedHashMap<>();
        Map<String, Object> errorDetails = new LinkedHashMap<>();

        errorDetails.put("type", exceptionType.name());
        errorDetails.put("message", exceptionType.getMessage());

        if (details != null) {
            if (details instanceof List) {
                errorDetails.put("details", details);
            } else {
                List<String> detailList = new ArrayList<>();
                detailList.add(details.toString());
                errorDetails.put("details", detailList);
            }
        } else {
            errorDetails.put("details", new ArrayList<>());
        }

        errorResponse.put("error", errorDetails);
        return ResponseEntity.status(exceptionType.getStatus()).body(errorResponse);
    }
}

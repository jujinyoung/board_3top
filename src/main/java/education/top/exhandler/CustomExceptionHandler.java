package education.top.exhandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Spring AOP를 사용하여 MethodArgumentNotValidException이 발생할 때마다 API 처리기 메서드를 가로채야 함
 */
@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class CustomExceptionHandler {

    @Autowired
    private MessageSourceAccessor messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity validation(MethodArgumentNotValidException ex) {
        MultiValueMap<String, Object> responseBody = new LinkedMultiValueMap<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            String code = fieldError.getCodes()[0];
            log.info("레벨1 코드 값={}", code);
            String message = messageSource.getMessage(code, fieldError.getDefaultMessage());
            responseBody.add(fieldError.getField(), message);
        }
        return ResponseEntity.status(201).body(responseBody);
    }
}

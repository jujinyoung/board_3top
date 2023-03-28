package education.top.exhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Spring AOP를 사용하여 MethodArgumentNotValidException이 발생할 때마다 API 처리기 메서드를 가로채야 함
 * 해결link = https://www.codejava.net/frameworks/spring-boot/rest-api-request-validation-examples
 */
@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        MultiValueMap<String, Object> responseBody = new LinkedMultiValueMap<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            responseBody.add(fieldError.getField(), Arrays.asList(fieldError.getDefaultMessage()));
            log.info("검증에러메세지 = {}", fieldError.getDefaultMessage());
        }

        return new ResponseEntity<>(responseBody, headers, HttpStatus.CREATED);
    }
}

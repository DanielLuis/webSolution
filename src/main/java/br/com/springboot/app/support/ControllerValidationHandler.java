package br.com.springboot.app.support;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.AllArgsConstructor;

@ControllerAdvice
@AllArgsConstructor
public class ControllerValidationHandler {

  private MessageSource messageSource;

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public MensagemDTO processValidationError(MethodArgumentNotValidException ex) {
    BindingResult result = ex.getBindingResult();
    FieldError error = result.getFieldError();

    return processFieldError(error);
  }

  private MensagemDTO processFieldError(FieldError error) {
	  MensagemDTO message = null;
   
	if (error != null) {
      String msg = messageSource.getMessage(error.getDefaultMessage(), error.getArguments(), LocaleContextHolder.getLocale());
      message = MensagemDTO.erro(405,"Validation Error: "+ msg);
    }
    return message;
  }
}

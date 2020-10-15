package br.com.springboot.app.exceptions;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import br.com.springboot.app.support.MessageDTO;
import lombok.AllArgsConstructor;

@ControllerAdvice
@AllArgsConstructor
public class ControllerValidationHandler {

  private MessageSource messageSource;

  private MessageDTO getMessage(HttpStatus status, List<String> messages) {
		MessageDTO message = MessageDTO.builder()
	    		  				.timestamp(LocalDate.now())
	    		  				.statusCode(status.value())
	    		  				.messages(messages)
	    		  				.build();
		return message;
	}

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  protected ResponseEntity<MessageDTO> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,HttpStatus status, WebRequest request) {
      List<String> messages = ex.getBindingResult()
              .getFieldErrors()
              .stream()
              .map(error -> messageSource.getMessage(error.getDefaultMessage(), error.getArguments(), LocaleContextHolder.getLocale()))
              .collect(Collectors.toList());

      return new ResponseEntity<MessageDTO>(getMessage(status, messages), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UserNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  protected ResponseEntity<MessageDTO> userNotFoundHandler(UserNotFoundException ex) {
	  List<String> messages = Arrays.asList(ex.getMessage());
	  
	  return new ResponseEntity<MessageDTO>(getMessage(HttpStatus.NOT_FOUND,messages) , HttpStatus.NOT_FOUND);
  }
}

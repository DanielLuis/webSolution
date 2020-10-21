package br.com.springboot.app.support;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ResponseBodyMatchers {
	private ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
	
	public static ResponseBodyMatchers responseBody(){
		return new ResponseBodyMatchers();
	}

	public <T> ResultMatcher containsObjectAsJson(
			Object expectedObject, 
			Class<T> targetClass) {
		return mvcResult -> {
			String json = mvcResult.getResponse().getContentAsString();
			T actualObject = objectMapper.readValue(json, targetClass);
			assertThat(actualObject).isEqualToComparingFieldByField(expectedObject);
		};
	}public <T> ResultMatcher containsObjectAsResponseEntity(
			Object expectedObject, 
			Class<T> targetClass) {
		return mvcResult -> {
			String json = mvcResult.getResponse().getContentAsString();
			
			JSONObject jsonObject = new JSONObject(json);
			jsonObject.remove("_links");
			
			T actualObject = objectMapper.readValue(jsonObject.toString(), targetClass);
			assertThat(actualObject).isEqualToComparingFieldByField(expectedObject);
			
		};
	}
	
	public ResultMatcher containsError(String expectedMessage) {
		return mvcResult -> {
			String json = mvcResult.getResponse().getContentAsString();
			MessageError errorResult = objectMapper.readValue(json, MessageError.class);
			List<String> messages = errorResult.getMessages().stream()
					.filter(message -> message.equals(expectedMessage))
					.collect(Collectors.toList());

			assertThat(messages)
			.hasSize(1)
			.withFailMessage("expecting exactly 1 error message '%s'",expectedMessage);
		};
	}


}
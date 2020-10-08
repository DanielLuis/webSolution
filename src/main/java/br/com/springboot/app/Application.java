package br.com.springboot.app;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootApplication
public class Application {

	public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private static final Integer TIME_OUT = Integer.valueOf(500000);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	@Primary
	public ObjectMapper serializingObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		JavaTimeModule javaTimeModule = new JavaTimeModule();
		javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer());
		javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer());
		objectMapper.registerModule(javaTimeModule);
		return objectMapper;
	}

	public class LocalDateSerializer extends JsonSerializer<LocalDate> {
		@Override
		public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
			gen.writeString(value.format(FORMATTER));
		}
	}
	public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {
		@Override
		public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
			return LocalDate.parse(p.getValueAsString(), FORMATTER);
		}
	}


	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate(clientHttpRequestFactory());
	}

	private ClientHttpRequestFactory clientHttpRequestFactory() {
		HttpClient httpClient = HttpClients.createDefault();

		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
		factory.setReadTimeout(TIME_OUT);
		factory.setConnectTimeout(TIME_OUT);
		return factory;
	}

}



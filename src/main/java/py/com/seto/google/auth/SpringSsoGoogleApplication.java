package py.com.seto.google.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@SpringBootApplication
@RestController
public class SpringSsoGoogleApplication {

	@GetMapping
	public String Welcome(OAuth2AuthenticationToken token) throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());

		// Accede a los atributos del usuario
		Map<String, Object> attributes = token.getPrincipal().getAttributes();
		String name = (String) attributes.get("given_name");
		String lastName = (String) attributes.get("family_name");
		String email = (String) attributes.get("email");

		// Construye la respuesta
		String response = "Welcome to Google " + name + " " + lastName;
		response += " (" + email + ")";

		return response;
	}

	@GetMapping("/user")
	public Principal user (Principal principal){
		return principal;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringSsoGoogleApplication.class, args);
	}

}

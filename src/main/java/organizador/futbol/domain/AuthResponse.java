package organizador.futbol.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    String token;
    String code;
    String message;
    
    public static AuthResponse userAlreadyExistsResponse() {
        AuthResponse response = new AuthResponse();
        response.setCode("USER_ALREADY_EXISTS");
        response.setMessage("El nombre de usuario ya está en uso");
        return response;
    }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	} 

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}

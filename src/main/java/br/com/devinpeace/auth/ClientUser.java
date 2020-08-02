package br.com.devinpeace.auth;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ClientUser {

	@ApiModelProperty(required = true, example = "username")
	private String username;

	@ApiModelProperty(required = true, example = "password")
	private String password;

	@ApiModelProperty(required = true, dataType = "List", example = "[ADMIN, USER]")
	private List<Role> roles;

	public enum Role {
		USER, ADMIN
	}
}

package br.com.devinpeace.auth;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@ConfigurationProperties(prefix = "devinpeace.user", ignoreUnknownFields = true)
@Component
@Data
public class ServerUser {
	private String username;
    private String password;
}

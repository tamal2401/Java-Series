package com.example.docker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.util.Collection;
import java.util.Collections;

@SpringBootApplication
public class DockerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DockerApplication.class, args);
	}

}

@RestController
class Controller{

	@PostMapping(value = "/msg", produces = MediaType.APPLICATION_JSON_VALUE)
	public MessegeResponse getMessege(@RequestBody MessegeRequest request){
		return new MessegeResponse(request.getMsg().concat(" :: lala"));
	}
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class MessegeRequest{
	String msg;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class MessegeResponse{
	String msg;
}
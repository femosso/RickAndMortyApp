package com.example.rickandmorty;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class RickAndMortyApplication {

	@Value("${rick-and-morty-api.url}")
	private String url;

	public static void main(String[] args) {
		SpringApplication.run(RickAndMortyApplication.class, args);
	}

	@Bean
	public WebClient getRickAndMortyApi() {
		return WebClient.create(url);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}

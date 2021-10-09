package com.majka.betterreadsdataloader;

import java.nio.file.Path;

import javax.annotation.PostConstruct;

import com.majka.betterreadsdataloader.author.AuthorRepository;
import com.majka.betterreadsdataloader.connector.DataStaxAstraProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(DataStaxAstraProperties.class)
public class BetterReadsDataLoaderApplication {

	@Autowired AuthorRepository authorRepository;

	public static void main(String[] args) {
		SpringApplication.run(BetterReadsDataLoaderApplication.class, args);
	}


	@PostConstruct
	public void start(){
		System.out.println("Application started");
	}











	/**
	 * This is necessary to have the Spring Boot app use the Astra secure bundle to
	 * connect to the database
	 */
	@Bean
	public CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataStaxAstraProperties astraProperties) {
		Path bundle = astraProperties.getSecureConnectBundle().toPath();
		return builder -> builder.withCloudSecureConnectBundle(bundle);
	}

}

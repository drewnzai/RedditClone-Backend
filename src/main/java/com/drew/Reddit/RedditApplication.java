package com.drew.Reddit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
//@EnableAsync
//@ComponentScan("com.drew.Reddit.mapper")

// I have commented out the above component scan since it is
// causing the app to use default security config rather than our own implementation
// Also, @ComponentScan is already implemented in the SpringbootApplication class

public class RedditApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedditApplication.class, args);
	}

}

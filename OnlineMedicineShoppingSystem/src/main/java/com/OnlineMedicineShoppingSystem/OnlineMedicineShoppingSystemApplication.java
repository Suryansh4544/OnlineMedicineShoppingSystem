package com.OnlineMedicineShoppingSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan("com.*")
@EnableJpaRepositories(basePackages = "com.OnlineMedicineShoppingSystem.repository")
@EnableTransactionManagement
@EntityScan(basePackages = "com.OnlineMedicineShoppingSystem.model")
public class OnlineMedicineShoppingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineMedicineShoppingSystemApplication.class, args);
	}

}

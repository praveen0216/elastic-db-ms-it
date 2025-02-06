package com.poc.elastic_db_ms_it;

import org.springframework.boot.SpringApplication;

public class TestElasticDbMsItApplication {

	public static void main(String[] args) {
		SpringApplication.from(ElasticDbMsItApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}

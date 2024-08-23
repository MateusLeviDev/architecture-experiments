package br.com.madlib.madlib_gcp_template;

import org.springframework.boot.SpringApplication;

public class TestMadlibGcpTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.from(MadlibGcpTemplateApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}

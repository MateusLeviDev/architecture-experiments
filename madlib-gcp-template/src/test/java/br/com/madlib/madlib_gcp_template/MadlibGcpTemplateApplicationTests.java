package br.com.madlib.madlib_gcp_template;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class MadlibGcpTemplateApplicationTests {

	@Test
	void contextLoads() {
	}

}

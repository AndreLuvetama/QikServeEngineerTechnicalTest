package com.qikserve;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

@SpringBootTest
@AutoConfigureWireMock(port = 3001)
public class QikServeEngineerTechnicalTestApplicationTests {

	@Test
	void contextLoads() {
	}

}

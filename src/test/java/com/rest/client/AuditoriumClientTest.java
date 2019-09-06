package com.rest.client;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.rest.client.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@RestClientTest(AuditoriumClient.class)
public class AuditoriumClientTest {

	AuditoriumClient client = new AuditoriumClient();
	
	@Test
	public void test() {
		ResponseEntity<String> response = client.getAuditoriums();
		assertEquals(response.getStatusCode(), (HttpStatus.OK));
	}
}

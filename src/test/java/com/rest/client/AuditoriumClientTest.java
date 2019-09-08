package com.rest.client;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rest.model.Auditorium;

@RunWith(SpringRunner.class)
@RestClientTest(AuditoriumClient.class)
public class AuditoriumClientTest {

	AuditoriumClient client = new AuditoriumClient();
	
	@Test
	public void test() {
		List<Auditorium> response = client.getAll();
	}
}

package adiitya.mojang4j.http;

import adiitya.mojang4j.http.response.HandlerTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class RequestLimiterTest {

	private RequestLimiter limiter;

	@Test
	void shouldPassWithDefaultSettings() {

		limiter = new RequestLimiter(600, TimeUnit.MINUTES.toMillis(10));

		for (int i = 0; i < 599; i++)
			limiter.request();

		assertTrue(limiter.canRequest());
		limiter.request();
		assertFalse(limiter.canRequest());
	}
}
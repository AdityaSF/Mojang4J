package adiitya.mojang4j.http.response;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.*;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class HandlerTests {

	@Nested
	public class UUIDTests {

		private JsonObject json;
		private UUIDHandler handler;

		@BeforeEach
		public void before() {
			json = new JsonObject();
			handler = new UUIDHandler();
		}

		@Test
		public void shouldFailWithInvalidJson() {
			assertThrows(NullPointerException.class, () -> handler.handle(200, json));
		}

		@Test
		public void shouldPassWithValidUUID() {
			json.addProperty("id", UUID.randomUUID().toString());
			assertDoesNotThrow(() -> handler.handle(200, json));
		}

		@Test
		public void shouldPassWithInvalidUUID() {
			json.addProperty("id", "fkjnvwejfnglwer");
			assertThrows(IllegalArgumentException.class, () -> handler.handle(200, json));
		}
	}
}
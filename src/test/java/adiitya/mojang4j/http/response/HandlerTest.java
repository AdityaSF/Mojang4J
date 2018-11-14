package adiitya.mojang4j.http.response;

import adiitya.mojang4j.http.ResponseHandler;
import com.google.gson.JsonElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class HandlerTest<T, J extends JsonElement> {

	protected J json;
	protected ResponseHandler<T> handler;

	@BeforeEach
	public abstract void beforeEach();

	protected abstract JsonElement getValidJson();
	protected abstract JsonElement getInvalidJson();

	@Test
	protected abstract void shouldPassWithValidJson();

	@Test
	protected abstract void shouldFailWithInvalidJson();
}

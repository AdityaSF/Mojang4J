package adiitya.mojang4j.http;

import com.google.gson.JsonElement;

import java.util.Optional;

@FunctionalInterface
public interface ResponseHandler<T> {

	Optional<T> handle(int code, JsonElement json);
}

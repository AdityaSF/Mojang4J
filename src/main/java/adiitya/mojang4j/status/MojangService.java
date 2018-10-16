package adiitya.mojang4j.status;

import lombok.Getter;

/**
 * This class represents a Mojang service and provides the name and status of the service.
 * {@code MojangService} is immutable as the data it represents is static.
 */
public final class MojangService {

	@Getter private final String name;
	@Getter private final ServiceStatus status;

	public MojangService(String name, ServiceStatus status) {
		this.name = name;
		this.status = status;
	}
}

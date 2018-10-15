package adiitya.mojang4j.status;

import lombok.Getter;

public final class MojangService {

	@Getter private final String name;
	@Getter private final ServiceStatus status;

	public MojangService(String name, ServiceStatus status) {
		this.name = name;
		this.status = status;
	}
}

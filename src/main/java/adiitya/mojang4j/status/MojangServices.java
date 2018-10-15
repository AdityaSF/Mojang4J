package adiitya.mojang4j.status;

import java.util.*;
import java.util.function.Consumer;

public final class MojangServices implements Iterable<MojangService> {

	private Map<String, MojangService> services;

	public MojangServices(List<MojangService> services) {

		this.services = services.stream()
				.collect(HashMap::new,
						(map, service) -> map.put(service.getName(), service),
						Map::putAll);
	}

	@Override
	public Iterator<MojangService> iterator() {
		return services.values().iterator();
	}

	@Override
	public void forEach(Consumer<? super MojangService> action) {
		services.values().forEach(action);
	}

	@Override
	public Spliterator<MojangService> spliterator() {
		return services.values().spliterator();
	}
}

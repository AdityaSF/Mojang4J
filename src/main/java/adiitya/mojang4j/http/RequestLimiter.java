package adiitya.mojang4j.http;

import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.ArrayList;
import java.util.List;

public final class RequestLimiter {

	private final int requestLimit;
	private final long limitPeriod;

	private final List<Long> requestTimestamps = new ArrayList<>();

	public RequestLimiter(int requestLimit, long limitPeriod) {
		this.requestLimit = requestLimit;
		this.limitPeriod = limitPeriod;
	}

	public boolean canRequest() {
		updateRequests();
		return requestTimestamps.size() < requestLimit;
	}

	public void request(CloseableHttpResponse response) {
		updateRequests();
		if (response != null) requestTimestamps.add(System.currentTimeMillis());
	}

	private void updateRequests() {
		requestTimestamps.removeIf(timestamp -> System.currentTimeMillis() - timestamp > limitPeriod);
	}
}

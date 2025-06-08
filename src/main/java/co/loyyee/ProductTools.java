package co.loyyee;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

public class ProductTools {
	final static Logger log = Logger.getLogger("ProductTools");
	final	HttpClient httpClient = HttpClient.newHttpClient();
	
	public static void main(String[] args) {
		new ProductTools().fetchProducts();
	}
	
	public String fetchProducts() {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://dummyjson.com/products?limit=10"))
				.build();
		try {
			
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			log.info(response.body());
			return response.body();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}

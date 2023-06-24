import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class AsynchronousExample {
    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.naver.com"))
                .build();

        CompletableFuture<HttpResponse<String>> future =
                client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        System.out.printf("[%s] This is Next... %n", Thread.currentThread().getName());

        future.thenApply(HttpResponse::body)
                .thenAccept(i -> System.out.printf("[%s] %s\n", Thread.currentThread().getName(), i))
                .join();
    }
}

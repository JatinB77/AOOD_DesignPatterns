import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.stream.Collectors;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

public class Request {

    private Headers headers;
    private String method;
    private String body;
    private URI uri;

    public Request(HttpExchange exchange) {
        headers = exchange.getRequestHeaders();
        method = exchange.getRequestMethod();

        body = new BufferedReader(new InputStreamReader(
            exchange.getRequestBody()))
            .lines().collect(Collectors.joining("\n"));

        uri = exchange.getRequestURI();
    }

    public Headers getHeaders() {
        return headers;
    }

    public String getMethod() {
        return method;
    }

    public String getBody() {
        return body;
    }

    public URI getRequestURI() {
        return uri;
    }
}

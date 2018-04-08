import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.util.function.Supplier;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

/**
 * A simple HTTP server.
 */
public class Server {

    private static final String host = "localhost";
    private static final int port = 8000;

    /**
     * Creates and runs an HTTP server instance.
     */
    public static void main(String... args) throws IOException {
        HttpServer server = HttpServer.create(
            new InetSocketAddress(host, port), 0);

        assignContext(server, new Resource());
        server.setExecutor(null);

        server.start();
        System.out.println(String.format("Server running on http://%s:%d",
            host, port));

        // Type Ctrl-d to stop server.
        BufferedReader stdin = new BufferedReader(
            new InputStreamReader(System.in));
        while (stdin.readLine() != null);
        server.stop(0);
    }

    /**
     * Log an incoming HTTP request.
     */
    private static void log(Request req) {
        System.out.println(String.format("[%s] %s",
            req.getRequestMethod(), req.getRequestURI()));
    }

    /**
     * Send response data to the client.
     */
    private static void handleExchange(HttpExchange ex, Response res)
            throws IOException {
        if (res == null)
            res = Response
                .serverError()
                .entity("Server error: no response")
                .build();

        ex.getResponseHeaders().putAll(res.getHeaders());
        ex.sendResponseHeaders(res.getStatus(), res.getLength());
        ex.getResponseBody().write(res.getEntity());
        ex.close();
    }

    /**
     * Assign handlers to server endpoints present in the specified Resource
     * instance.
     */
    private static void assignContext(HttpServer server, Resource resource) {
        Method[] methods = resource.getClass().getMethods();

        for (Method method : methods) {
            Path path = method.getAnnotation(Path.class);

            if (path != null && method.getReturnType().equals(Response.class))
                server.createContext(path.value(), exchange -> {
                    // Delegate 'exchange' behavior to separate 'request'
                    // and 'response' objects for demonstration purposes.
                    Request req = new Request(exchange);
                    Response res = null;

                    log(req);

                    try {
                        res = (Response) method.invoke(resource, req);
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    handleExchange(exchange, res);
                });
        }
    }
}

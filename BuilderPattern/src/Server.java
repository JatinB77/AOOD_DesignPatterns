import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.util.function.Supplier;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

public class Server {

    private static final int port = 8000;
    private static BufferedReader stdin = new BufferedReader(
        new InputStreamReader(System.in));

    public static void main(String... args) throws IOException {
        HttpServer server = HttpServer.create(
            new InetSocketAddress("localhost", port), 0);

        assignContext(server, new Resource());
        server.setExecutor(null);

        server.start();
        System.out.println("Server running on http://localhost:" + port);

        while (stdin.readLine() != null);
        server.stop(0);
    }

    private static void log(HttpExchange ex) {
        System.out.println(String.format(
            "[%s] %s", ex.getRequestMethod(), ex.getRequestURI()));
    }

    private static void handleExchange(HttpExchange ex, Response res)
            throws IOException {
        log(ex);

        if (res == null)
            res = Response
                .serverError()
                .entity("Server error: no response")
                .build();

        ex.getResponseHeaders().putAll(res.getHeaders());
        ex.sendResponseHeaders(res.getStatus(), res.getLength());
        ex.getResponseBody().write(res.getEntity().getBytes());
        ex.close();
    }

    private static void assignContext(HttpServer server, Resource resource) {
        Method[] methods = resource.getClass().getMethods();

        for (Method method : methods) {
            Path path = method.getAnnotation(Path.class);

            if (path != null && method.getReturnType().equals(Response.class))
                server.createContext(path.value(), exchange -> {
                    Response res = null;
                    try {
                        res = (Response) method.invoke(resource,
                            new Request(exchange));
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    handleExchange(exchange, res);
                });
        }
    }
}

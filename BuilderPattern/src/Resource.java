import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Collectors;

public class Resource {

    @Path("/")
    public Response getRoot(Request req) {
        return Response.ok().entity(
            "<h1>Builder Pattern with Responses</h1>"
        ).build();
    }

    @Path("/echo")
    public Response echoRequest(Request req) {
        return Response.ok().entity(req.getBody()).build();
    }

    @Path("/error")
    public Response noResponse(Request req) {
        return null;
    }

    @Path("/static")
    public Response serveStatic(Request req) {
        Response.Builder response;

        try {
            File file = new File(req
                .getRequestURI()
                .toString()
                .substring(1)
            );

            if (file.isDirectory())
                file = new File(file, "index.html");

            if (file.exists()) {
                response = Response
                    .ok()
                    .entity(readFile(file))
                    .type(Files.probeContentType(file.toPath()));
                System.out.println(Files.probeContentType(file.toPath()));
            }
            else
                response = Response
                    .status(Response.Status.FILE_NOT_FOUND)
                    .entity("Could not find " + file);
        }
        catch (IOException ex) {
            ex.printStackTrace();
            response = Response.serverError();
        }

        return response.build();
    }

    public static String readFile(File file) throws IOException {
        return Files
            .readAllLines(file.toPath())
            .stream()
            .collect(Collectors.joining("\n"));
    }
}

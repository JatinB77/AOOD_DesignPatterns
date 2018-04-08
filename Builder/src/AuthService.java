public class AuthService {

    public Response getLogin() {
        return Response
            .ok()
            .entity("<title>Builder</title><h1>Hello</h1>")
            .build();
    }
}

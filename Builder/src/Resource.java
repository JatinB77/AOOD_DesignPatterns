public class Resource {

    @Path("/aood")
    public Response getLogin() {
        return Response.ok().entity("Hello").build();
    }
}

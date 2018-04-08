import java.util.Date;

import com.sun.net.httpserver.Headers;

public class Response {

    public static enum Status {
        OK (200),
        UNAUTHORIZED (401),
        FILE_NOT_FOUND (404),
        INTERNAL_SERVER_ERROR (500);

        private final int code;

        Status(int code) {
            this.code = code;
        }

        public int getStatusCode() {
            return code;
        }
    }

    public static class Builder {

        private int status;
        private Headers headers = new Headers();
        private byte[] body;

        public Response build() {
            Response res = new Response();

            res.status = status;
            res.headers = headers;
            res.body = body;

            return res;
        }

        public Builder entity(byte[] entity) {
            body = entity;
            return this;
        }

        public Builder entity(Object entity) {
            body = entity.toString().getBytes();
            return this;
        }

        public Builder expires(Date expires) {
            headers.add("Expires", expires.toString());
            return this;
        }

        public Builder header(String name, String value) {
            headers.add(name, value);
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder status(Status status) {
            this.status = status.getStatusCode();
            return this;
        }

        public Builder type(String type) {
            headers.add("Content-type", type);
            return this;
        }
    }

    public static Builder ok() {
        return new Builder().status(Status.OK);
    }

    public static Builder serverError() {
        return new Builder().status(Status.INTERNAL_SERVER_ERROR);
    }

    public static Builder status(int status) {
        return new Builder().status(status);
    }

    public static Builder status(Status status) {
        return new Builder().status(status);
    }

    private int status;
    private Headers headers;
    private byte[] body;

    private Response() { }

    public int getStatus() {
        return status;
    }

    public Headers getHeaders() {
        return headers;
    }

    public byte[] getEntity() {
        return body;
    }

    public boolean hasEntity() {
        return body != null;
    }

    public long getLength() {
        return body.length;
    }

    public String getMediaType() {
        return headers.getFirst("Content-type");
    }
}

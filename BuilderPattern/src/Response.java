import java.util.Date;

import com.sun.net.httpserver.Headers;

/**
 * A simple HTTP response produced by a server with the intention of being
 * sent to a client.
 *
 * This class's purpose is to demonstrate the Builder Pattern.
 *
 * It is a simplified version of the Javax RESTful services Response object:
 * https://docs.oracle.com/javaee/7/api/javax/ws/rs/core/Response.html
 */
public class Response {

    /**
     * Commonly used status codes defined by HTTP.
     */
    public enum Status {
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

    /**
     * A class used to build Response instances that contain metadata instead
     * of or in addition to message body.
     */
    public static class Builder {

        private int status;
        private Headers headers = new Headers();
        private byte[] body;

        /**
         * Create a Response instance from the current ResponseBuilder.
         */
        public Response build() {
            Response res = new Response();

            res.status = status;
            res.headers = headers;
            res.body = body;

            return res;
        }

        /**
         * Write an entity as a byte array to this builder.
         */
        public Builder entity(byte[] entity) {
            body = entity;
            return this;
        }

        /**
         * Write an entity to this builder.
         */
        public Builder entity(Object entity) {
            body = entity.toString().getBytes();
            return this;
        }

        /**
         * Set the response expiration date.
         */
        public Builder expires(Date expires) {
            headers.add("Expires", expires.toString());
            return this;
        }

        /**
         * Write an arbitrary header.
         */
        public Builder header(String name, String value) {
            headers.add(name, value);
            return this;
        }

        /**
         * Set the status on this builder.
         */
        public Builder status(int status) {
            this.status = status;
            return this;
        }

        /**
         * Set the status on this builder.
         */
        public Builder status(Status status) {
            this.status = status.getStatusCode();
            return this;
        }

        /**
         * Set the message entity media type.
         */
        public Builder type(String type) {
            headers.add("Content-type", type);
            return this;
        }
    }

    //=========================================================================
    // Builder instances may only be created using static Response methods.
    // In the case of this class, this enforces the fact that a Response
    // must have a status code.
    //=========================================================================

    /**
     * Create a new ResponseBuilder with an OK status.
     */
    public static Builder ok() {
        return new Builder().status(Status.OK);
    }

    /**
     * Create a new ResponseBuilder with a server error status.
     */
    public static Builder serverError() {
        return new Builder().status(Status.INTERNAL_SERVER_ERROR);
    }

    /**
     * Create a new ResponseBuilder with the supplied status.
     */
    public static Builder status(int status) {
        return new Builder().status(status);
    }

    /**
     * Create a new ResponseBuilder with the supplied status.
     */
    public static Builder status(Status status) {
        return new Builder().status(status);
    }

    /** Status code for this response. */
    private int status;
    /** Content header mapping for this response. */
    private Headers headers;
    /** Message entity or body of this response. */
    private byte[] body;

    /**
     * A private constructor enforces the Builder Pattern.
     * Only the Builder may create instances of this complex object.
     */
    private Response() { }

    /**
     * Returns this response's status code.
     */
    public int getStatus() {
        return status;
    }


    /**
     * Returns this response's header mapping.
     */
    public Headers getHeaders() {
        return headers;
    }

    /**
     * Returns this response's message entity.
     */
    public byte[] getEntity() {
        return body;
    }

    /**
     * Returns true if this response contains a message entity.
     */
    public boolean hasEntity() {
        return body != null;
    }

    /**
     * Returns the length of this response's message entity.
     */
    public long getLength() {
        return body.length;
    }

    /**
     * Returns the media type (also known as MIME or content type) of this
     * response's message entity.
     */
    public String getMediaType() {
        return headers.getFirst("Content-type");
    }
}

# SMS Microservice

Sending a text message requires a [Twilio](https://www.twilio.com/) account.

## Environment Variables

Before running the server, make sure you have the following environment
variables defined:

    * `TWILIO_SID`: Your Twilio account sid.
    * `TWILIO_TOKEN`: Your Twilio authentication token.
    * `TWILIO_POHONE`: A Twilio phone number from which sms can be sent.

If using this service with Docker, the environment variables must be
forwarded into the container. The variables are already identified in
the `env.list`. If the variables are defined in your system, run the docker
container with:

```bash
docker run -p 8080:8080 --env-file env.list sms
```

## Send a message!

Send a message by making a POST request to `/send?to=<number>`, where
`<number>` is the sms destination.

The body of the request will be the contents of the message.

## Phone Number Format

The phone number must be in **E.164 format**.

A US-based number in standard local formatting: **(415) 555-2671**

The same number in E.164 format: **+14155552671**

A plus sign in a URL query parameter represents a space. Since this is the way
in which the number is given to the service, the plus sign
should be encoded to avoid problems.

To do this in Java,

```java
import java.net.URLEncoder;
...
String phone = "+14155552671";
String queryParam = URLEncoder.encode(phone, "UTF-8");
return "/send?to=" + queryParam;
```

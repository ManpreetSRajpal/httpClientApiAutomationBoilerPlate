import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ResponseHeaders extends BaseClass {

    @Test
    public void contentTypeInJson() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT);
        response = client.execute(get);
        ContentType contentType = ContentType.getOrDefault(response.getEntity());
        assertEquals(contentType.getMimeType(), "application/json");
    }

    @Test
    public void serverIsGithub() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT);
        response = client.execute(get);
        String headerValue = ResponseUtils.getHeader1(response, "Server");
        assertEquals(headerValue, "GitHub.com");
    }

    @Test
    public void xRateLimitIsSixty() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT);
        response = client.execute(get);
        String limitValue = ResponseUtils.getHeader(response, "x-RateLimit-Limit");
        assertEquals(limitValue, "60");
    }

    @Test
    public void headerIsPresent() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT);
        response = client.execute(get);
        String limitValue = ResponseUtils.getHeader(response, "x-RateLimit-Limit");
        assertEquals(limitValue, "60");
    }

    @Test
    public void eTagIsPresent() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT);
        response = client.execute(get);
        boolean tagIsPresent = ResponseUtils.headerIsPresent(response, "ETAG");
        assertTrue(tagIsPresent);
    }
}

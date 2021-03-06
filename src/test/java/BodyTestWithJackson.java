import entities.NotFound;
import entities.RateLimit;
import entities.User;
import org.apache.http.client.methods.HttpGet;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;


public class BodyTestWithJackson extends BaseClass {

    @Test
    public void returnCorrectLogin() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/andrejss88");
        response = client.execute(get);
        User user = ResponseUtils.unmarshall(response, User.class);

        assertEquals(user.getLogin(), "andrejss88");

    }

    @Test
    public void returnCorrectId() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/andrejss88");
        response = client.execute(get);
        User user = ResponseUtils.unmarshall(response, User.class);
        assertEquals(user.getId(), "11834443");
    }

    @Test
    public void notFoundMessageIsCorrect() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/nonexistingurl");
        response = client.execute(get);
        NotFound notFound = ResponseUtils.unmarshall(response, NotFound.class);
        assertEquals(notFound.getMessage(), "Not Found");
    }

    @Test
    public void correctRateLimitAreSet() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/rate_limit");
        response = client.execute(get);
        RateLimit rateLimit = ResponseUtils.unmarshall(response, RateLimit.class);
        assertEquals(rateLimit.getCoreLimit(), 60);
        assertEquals(rateLimit.getSearchLimit(), "60");
    }


}

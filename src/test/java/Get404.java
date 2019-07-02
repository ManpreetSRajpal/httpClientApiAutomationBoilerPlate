import org.apache.http.client.methods.HttpGet;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class Get404 extends BaseClass {

    @Test
    public void userReturns404() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/nonexistingurl");
        response = client.execute(get);

        int actualStatus = response.getStatusLine().getStatusCode();
        Assert.assertEquals(actualStatus, 404);
    }

}

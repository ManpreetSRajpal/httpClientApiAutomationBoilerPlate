import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.IOException;

import static entities.User.ID;
import static entities.User.LOGIN;
import static org.testng.Assert.assertEquals;

public class BodyTestWithSimpleMap extends BaseClass {

    @Test
    public void returnCorrectLogin() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/andrejss88");
        response = client.execute(get);

        String jsonBody = EntityUtils.toString(response.getEntity());
        JSONObject jsonObject = new JSONObject(jsonBody);
        String loginValue = (String) getValueFor(jsonObject, LOGIN);
        assertEquals(loginValue, "andrejss88");

    }

    @Test
    public void returnCorrectId() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/andrejss88");
        response = client.execute(get);

        String jsonBody = EntityUtils.toString(response.getEntity());
        JSONObject jsonObject = new JSONObject(jsonBody);
        Integer loginValue = (Integer) getValueFor(jsonObject, ID);
        assertEquals(loginValue, Integer.valueOf("11834443"));

    }

    private Object getValueFor(JSONObject jsonObject, String key) {
        return jsonObject.get(key);
    }

}

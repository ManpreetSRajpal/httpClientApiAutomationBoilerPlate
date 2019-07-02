import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ResponseUtils {

    public static String getHeader1(CloseableHttpResponse response, String headerName) {
        Header[] headers = response.getAllHeaders();
        List<Header> httpHeaders = Arrays.asList(headers);
        String returnHeader = "";

        for (Header header : httpHeaders) {
            if (headerName.equalsIgnoreCase(header.getName())) {
                returnHeader = header.getValue();
            }
        }
        if (returnHeader.isEmpty()) {
            throw new RuntimeException("Didn't found the header" + headerName);
        }

        return returnHeader;
    }

    public static String getHeader(CloseableHttpResponse response, final String headerName) {
        List<Header> headers = Arrays.asList(response.getAllHeaders());
        Header matchHeader = headers.stream()
                .filter(header -> headerName.equalsIgnoreCase(header.getName()))
                .findFirst().orElseThrow(() -> new RuntimeException("Didn't found the header" + headerName));

        return matchHeader.getValue();

    }

    public static boolean headerIsPresent(CloseableHttpResponse response, String headerName) {
        List<Header> headers = Arrays.asList(response.getAllHeaders());
        return headers.stream()
                .anyMatch(header -> header.getName().equalsIgnoreCase(headerName));
    }

    public static <T> T unmarshall(CloseableHttpResponse response, Class<T> userClass) throws IOException {
        String jsonBody = EntityUtils.toString(response.getEntity());

        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(jsonBody, userClass);
    }


}

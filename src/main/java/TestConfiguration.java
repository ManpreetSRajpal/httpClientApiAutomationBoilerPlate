import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestConfiguration {

    public static String getProperties(String prop) {
        Properties properties = new Properties();

        try {
            InputStream inputStream = new FileInputStream("/Users/manpreetrajpal/Personal/Study/Spring/course-api-with-jpa/webApiAutomation/src/main/resources/config.properties");
            properties.load(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties.getProperty(prop);
    }
}

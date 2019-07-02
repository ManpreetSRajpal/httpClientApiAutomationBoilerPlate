package entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Map;

public class RateLimit {

    @Getter
    private int coreLimit;

    @Getter
    private String searchLimit;


    @JsonProperty("resources")
    public void unmarshellNested(Map<String, Object> resources) {
        Map<String, Integer> core = (Map<String, Integer>) resources.get("core");
        coreLimit = core.get("limit");

        Map<String, String> search = (Map<String, String>) resources.get("core");
        searchLimit = String.valueOf(search.get("limit"));


    }
}

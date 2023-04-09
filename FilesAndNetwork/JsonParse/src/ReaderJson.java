import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ReaderJson {
    private String path;
    private ObjectMapper mapper;

    public ReaderJson(String path) {
        this.path = path;
    }

    public void readFile() {
        File file = new File(path);
        mapper = new ObjectMapper();

        try {
            List<Station> stationList = mapper.readValue(file, new TypeReference<>() {
            });
            System.out.println(stationList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

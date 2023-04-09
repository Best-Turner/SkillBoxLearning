import com.fasterxml.jackson.core.JsonProcessingException;

public class Main {
    private static final String PATH = "https://skillbox-java.github.io/";
    private final static String ALL_STATION = "allStation.json";
    private final static String MAP_METRO = "mapMetro.json";

    public static void main(String[] args) {
        Writer writer = new Writer(PATH);
        ReaderJson reader;
        try {
            writer.writeAllStationsToJson(ALL_STATION);
            writer.writeMapMetroToJson(MAP_METRO);

            reader = new ReaderJson(ALL_STATION);
            reader.readFile();

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

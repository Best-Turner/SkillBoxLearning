import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Writer {
    private ObjectMapper mapper;
    private final String url;

    public Writer(String url) {
        this.url = url;
    }

    private Element getHtml() {
        Element element;
        try {
            Document document = Jsoup.connect(url).get();
            element = document.getElementById("metrodata");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return element;
    }

    private List<Line> getAllLine(Element element) {
        List<Line> lineList = new ArrayList<>();
        Elements elements = element.getElementsByClass("js-metro-line");
        elements.forEach(line ->
                lineList.add(new Line(line.attr("data-line"), line.text())));
        return lineList;
    }

    private List<Station> getAllStation(Element element) {
        List<Station> allStation = new ArrayList<>();
        Elements infoAboutStations = element.getElementsByClass("js-metro-stations");
        infoAboutStations.forEach(infoStation -> {
            String numberLine = infoStation.attr("data-line");
            infoStation.getElementsByClass("single-station").forEach(name -> {
                String nameStation = name.getElementsByClass("name").text();
                Station station = new Station(numberLine, nameStation);
                name.getElementsByClass("t-icon-metroln").forEach(connect -> station.addConnectingStation(connect.attr("title")));
                allStation.add(station);
            });
        });
        return allStation;
    }

    private List<Line> addStationToLine(Element element) {
        List<Line> lineWithStation = new ArrayList<>();
        List<Line> lineList = getAllLine(element);
        List<Station> station = getAllStation(element);
        for (Line line : lineList) {
            for (Station value : station) {
                if (line.getNumber().equals(value.getNumberLine())) {
                    line.addStation(value.getName());
                }
            }
            lineWithStation.add(line);
        }
        return lineWithStation;
    }

    public void writeMapMetroToJson(String filePathToWrite) {
        Element html = getHtml();
        List<Line> lines = addStationToLine(html);
        toWrite(filePathToWrite, lines);
    }

    public void writeAllStationsToJson(String filePathToWrite) throws JsonProcessingException {
        Element html = getHtml();
        List<Station> allStation = getAllStation(html);
        toWrite(filePathToWrite, allStation);
    }

    private <T> void toWrite(String filePathToWrite, List<T> object) {
        if (mapper == null) {
            mapper = new ObjectMapper();
        }
        try (FileWriter writer = new FileWriter(filePathToWrite)) {
            String result = mapper.writeValueAsString(object);
            writer.write(result);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

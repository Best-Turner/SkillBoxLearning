import java.util.ArrayList;
import java.util.List;

public class Station {
    private String numberLine;
    private String name;

    private List<String> connectionStation;

    public Station() {
    }

    public Station(String numberLine, String name) {
        this.numberLine = numberLine;
        this.name = name;

    }

    public String getNumberLine() {
        return numberLine;
    }

    public void setNumberLine(String numberLine) {
        this.numberLine = numberLine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getConnectionStation() {
        return connectionStation;
    }

    public void addConnectingStation(String stationToConnect) {
        if (connectionStation == null) {
            connectionStation = new ArrayList<>();
        }
        connectionStation.add(stationToConnect);
    }

    @Override
    public String toString() {
        return numberLine + " " + name + " " + (connectionStation == null ? "Нет пересадки" : connectionStation) + "\n";
    }
}

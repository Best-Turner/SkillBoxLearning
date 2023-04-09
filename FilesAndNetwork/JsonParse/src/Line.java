import java.util.ArrayList;
import java.util.List;

public class Line {
    private String number;
    private String name;
    private List<String> nameStation;

    public Line() {
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNameStation(List<String> nameStation) {
        this.nameStation = nameStation;
    }

    public Line(String number, String name) {
        this.name = name;
        this.number = number;
        nameStation = new ArrayList<>();
    }

    public void addStation(String name) {
        nameStation.add(name);
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }


    public List<String> getNameStation() {
        return nameStation;
    }

    @Override
    public String toString() {
        return name + "\n" + nameStation;
    }
}

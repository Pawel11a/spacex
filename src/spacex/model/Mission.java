package spacex.model;

import java.util.ArrayList;
import java.util.List;

public class Mission {
    private String name;
    private MissionStatus status;
    private List<Rocket> rockets = new ArrayList<>();

    public Mission(String name) {
        this.name = name;
        this.status = MissionStatus.SCHEDULED;
    }

    public String getName() {
        return name;
    }

    public MissionStatus getStatus() {
        return status;
    }

    public void setStatus(MissionStatus status) {
        this.status = status;
    }

    public List<Rocket> getRockets() {
        return rockets;
    }
}

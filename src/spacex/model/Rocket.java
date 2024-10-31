package spacex.model;

public class Rocket {

    private String name;
    private RocketStatus status;
    private Mission mission;

    public Rocket(String name) {
        this.name = name;
        this.status = RocketStatus.ON_GROUND;
    }

    public String getName() {
        return name;
    }

    public RocketStatus getStatus() {
        return status;
    }

    public Mission getMission() {
        return mission;
    }

    public void setStatus(RocketStatus status) {
        this.status = status;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }
}

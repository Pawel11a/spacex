package spacex.rocket.model;

import spacex.mission.model.Mission;

public class Rocket implements RocketSpec {
    private String name;
    private RocketStatus status;
    private Mission mission;

    public Rocket(String name) {
        this.name = name;
        this.status = RocketStatus.ON_GROUND;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public RocketStatus getStatus() {
        return status;
    }

    @Override
    public Mission getMission() {
        return mission;
    }

    @Override
    public void setStatus(RocketStatus status) {
        this.status = status;
    }

    @Override
    public void setMission(Mission mission) {
        this.mission = mission;
    }
}
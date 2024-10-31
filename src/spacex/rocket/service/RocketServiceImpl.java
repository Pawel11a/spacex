package spacex.rocket.service;

import spacex.mission.model.Mission;
import spacex.rocket.model.Rocket;
import spacex.rocket.model.RocketStatus;

import java.util.ArrayList;
import java.util.List;

public class RocketServiceImpl implements RocketService {
    private final List<Rocket> rockets = new ArrayList<>();

    public void addRocket(Rocket rocket) {
        rockets.add(rocket);
    }

    @Override
    public void assignRocketToMission(Rocket rocket, Mission mission) {

    }

    @Override
    public void changeRocketStatus(Rocket rocket, RocketStatus status) {

    }

    public List<Rocket> getRockets() {
        return rockets;
    }
}

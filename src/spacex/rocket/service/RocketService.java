package spacex.rocket.service;

import spacex.mission.model.Mission;
import spacex.rocket.model.Rocket;
import spacex.rocket.model.RocketStatus;

import java.util.List;

public interface RocketService {
    void addRocket(Rocket rocket);
    void assignRocketToMission(Rocket rocket, Mission mission);
    void changeRocketStatus(Rocket rocket, RocketStatus status);
    List<Rocket> getRockets();
}

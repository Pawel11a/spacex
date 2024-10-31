package spacex.mission.service;

import spacex.mission.model.Mission;
import spacex.mission.model.MissionStatus;
import spacex.rocket.model.Rocket;

import java.util.List;

public interface MissionService {
    void addMission(Mission mission);
    void assignRocketToMission(Mission mission, Rocket rocket);
    String printSummary();
    void changeMissionStatus(Mission mission, MissionStatus status);
    List<Mission> getMissionsSummary();
}

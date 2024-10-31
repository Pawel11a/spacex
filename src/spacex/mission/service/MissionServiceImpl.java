package spacex.mission.service;

import spacex.mission.model.Mission;
import spacex.mission.model.MissionStatus;
import spacex.rocket.model.Rocket;

import java.util.ArrayList;
import java.util.List;

public class MissionServiceImpl implements MissionService {

    private final List<Mission> missions = new ArrayList<>();
    public void addMission(Mission mission) {
        missions.add(mission);
    }

    @Override
    public void assignRocketToMission(Mission mission, Rocket rocket) {

    }

    @Override
    public String printSummary() {
        return null;
    }

    @Override
    public void changeMissionStatus(Mission mission, MissionStatus status) {

    }

    public List<Mission> getMissionsSummary() {
        return missions;
    }
}

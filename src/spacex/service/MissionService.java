package spacex.service;

import spacex.model.Mission;

import java.util.ArrayList;
import java.util.List;

public class MissionService {

    private final List<Mission> missions = new ArrayList<>();
    public void addMission(Mission mission) {
        missions.add(mission);
    }

    public List<Mission> getMissionsSummary() {
        return missions;
    }
}

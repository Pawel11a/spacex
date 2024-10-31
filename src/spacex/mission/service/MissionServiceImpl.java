package spacex.mission.service;

import spacex.mission.model.Mission;
import spacex.mission.model.MissionStatus;
import spacex.rocket.model.Rocket;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static spacex.mission.model.MissionStatus.*;
import static spacex.rocket.model.RocketStatus.*;

public class MissionServiceImpl implements MissionService {

    private static final String MISSION_BULLET = "\u25CF ";
    private static final String ROCKET_BULLET = "    \u25CF ";

    private final List<Mission> missions = new ArrayList<>();

    @Override
    public void addMission(Mission mission) {
        missions.add(mission);
    }

    @Override
    public void assignRocketToMission(Mission mission, Rocket rocket) {
        if (ENDED.equals(mission.getStatus())) {
            throw new IllegalStateException("Cannot assign rocket to an ended mission.");
        }
        if (rocket.getMission() != null) {
            throw new IllegalStateException("Rocket is already assigned to a mission.");
        }
        rocket.setMission(mission);
        mission.getRockets().add(rocket);
        rocket.setStatus(IN_SPACE);
        mission.setStatus(calculateMissionStatus(mission));
    }


    @Override
    public void changeMissionStatus(Mission mission, MissionStatus status) {
        if (ENDED.equals(status)) {
            mission.getRockets().forEach(rocket -> rocket.setStatus(ON_GROUND));
            mission.getRockets().clear();
        }
        mission.setStatus(status);
    }

    @Override
    public List<Mission> getMissionsSummary() {

        missions.sort(
                Comparator.comparingInt((Mission mission) -> mission.getRockets().size()).reversed()
                        .thenComparing(Comparator.comparing(Mission::getName).reversed())
        );

        return missions;
    }

    @Override
    public String printSummary() {
        StringBuilder summary = new StringBuilder("Having below data in the system:\n");

        for (Mission mission : getMissionsSummary()) {
            summary.append(MISSION_BULLET)
                    .append(mission.getName())
                    .append(" – ")
                    .append(mission.getStatus())
                    .append(" – Dragons: ")
                    .append(mission.getRockets().size())
                    .append("\n");

            for (Rocket rocket : mission.getRockets()) {
                summary.append(ROCKET_BULLET)
                        .append(rocket.getName())
                        .append(" – ")
                        .append(rocket.getStatus())
                        .append("\n");
            }
        }
        return summary.toString();
    }
    private MissionStatus calculateMissionStatus(Mission mission) {
        boolean anyInRepair = mission.getRockets().stream().anyMatch(r -> r.getStatus() == IN_REPAIR);
        boolean allInSpace = mission.getRockets().stream().allMatch(r -> r.getStatus() == IN_SPACE);

        if (anyInRepair) {
            return PENDING;
        } else if (allInSpace) {
            return IN_PROGRESS;
        }

        return mission.getStatus();
    }
}
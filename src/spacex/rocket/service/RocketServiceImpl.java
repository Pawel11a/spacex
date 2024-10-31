package spacex.rocket.service;

import spacex.mission.model.Mission;
import spacex.mission.model.MissionStatus;
import spacex.rocket.model.Rocket;
import spacex.rocket.model.RocketStatus;

import java.util.ArrayList;
import java.util.List;

import static spacex.mission.model.MissionStatus.IN_PROGRESS;
import static spacex.mission.model.MissionStatus.PENDING;
import static spacex.rocket.model.RocketStatus.IN_REPAIR;
import static spacex.rocket.model.RocketStatus.IN_SPACE;

public class RocketServiceImpl implements RocketService {
    private final List<Rocket> rockets = new ArrayList<>();

    @Override
    public void addRocket(Rocket rocket) {
        rockets.add(rocket);
    }

    @Override
    public void assignRocketToMission(Rocket rocket, Mission mission) {
        if (rocket.getMission() != null) {
            throw new IllegalStateException("Rocket is already assigned to a mission.");
        }
        rocket.setMission(mission);
        mission.getRockets().add(rocket);
        rocket.setStatus(IN_SPACE);
        mission.setStatus(calculateMissionStatus(mission));
    }

    @Override
    public void changeRocketStatus(Rocket rocket, RocketStatus status) {
        rocket.setStatus(status);
        if (rocket.getMission() != null) {
            Mission mission = rocket.getMission();
            MissionStatus newMissionStatus = calculateMissionStatus(mission);
            mission.setStatus(newMissionStatus);
        }
    }

    @Override
    public List<Rocket> getRockets() {
        return rockets;
    }

    private MissionStatus calculateMissionStatus(Mission mission) {
        boolean anyInRepair = mission.getRockets().stream().anyMatch(rocket -> rocket.getStatus() == IN_REPAIR);
        boolean allInSpace = mission.getRockets().stream().allMatch(rocket -> rocket.getStatus() == IN_SPACE);

        if (anyInRepair) {
            return PENDING;
        } else if (allInSpace && !mission.getRockets().isEmpty()) {
            return IN_PROGRESS;
        }

        return mission.getStatus();
    }
}

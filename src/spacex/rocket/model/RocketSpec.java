package spacex.rocket.model;

import spacex.mission.model.Mission;

public interface RocketSpec {
    String getName();
    RocketStatus getStatus();
    Mission getMission();
    void setStatus(RocketStatus status);
    void setMission(Mission mission);
}

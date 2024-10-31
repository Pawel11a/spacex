package test;

import org.junit.jupiter.api.Test;
import spacex.mission.model.Mission;
import spacex.mission.service.MissionService;
import spacex.mission.service.MissionServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MissionServiceTest {

    @Test
    void testAddMission() {
        MissionService missionService = new MissionServiceImpl();

        Mission mission = new Mission("Test Mission");
        missionService.addMission(mission);

        assertEquals(1, missionService.getMissionsSummary().size());
        assertEquals("Test Mission", missionService.getMissionsSummary().get(0).getName());
    }
}

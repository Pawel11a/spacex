package test;

import org.junit.jupiter.api.Test;
import spacex.model.Mission;
import spacex.service.MissionService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MissionServiceTest {

    @Test
    void testAddMission() {
        MissionService missionService = new MissionService();

        Mission mission = new Mission("Test Mission");
        missionService.addMission(mission);

        assertEquals(1, missionService.getMissionsSummary().size());
        assertEquals("Test Mission", missionService.getMissionsSummary().get(0).getName());
    }
}

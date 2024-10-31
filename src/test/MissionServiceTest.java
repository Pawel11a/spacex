package test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MissionServiceTest {

    @Test
    void testAddMission() {
        MissionServiceImpl missionService = new MissionServiceImpl();

        Mission mission = new Mission("Test Mission");
        missionService.addMission(mission);

        assertEquals(1, missionService.getMissionsSummary().size());
        assertEquals("Test Mission", missionService.getMissionsSummary().get(0).getName());
    }
}

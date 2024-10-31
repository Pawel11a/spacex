package test.spacex.mission;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spacex.mission.model.Mission;
import spacex.mission.model.MissionStatus;
import spacex.mission.service.MissionService;
import spacex.mission.service.MissionServiceImpl;
import spacex.rocket.model.Rocket;
import spacex.rocket.model.RocketStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MissionServiceImplTest {

    private MissionService missionService;
    private Mission mission;
    private Rocket rocket;

    @BeforeEach
    void setUp() {
        missionService = new MissionServiceImpl();
        mission = new Mission("Test Mission");
        rocket = new Rocket("Falcon 1");
    }

    @Test
    void should_add_mission_increase_mission_count() {
        missionService.addMission(mission);

        assertEquals(1, missionService.getMissionsSummary().size());
        assertEquals("Test Mission", missionService.getMissionsSummary().get(0).getName());
    }

    @Test
    void should_assign_rocket_to_mission_set_rocket_status_in_space() {
        missionService.addMission(mission);
        missionService.assignRocketToMission(mission, rocket);

        assertEquals(1, mission.getRockets().size());
        assertEquals("Falcon 1", mission.getRockets().get(0).getName());
        assertEquals(RocketStatus.IN_SPACE, rocket.getStatus());
    }

    @Test
    void should_throw_exception_when_assign_rocket_to_ended_mission() {
        mission.setStatus(MissionStatus.ENDED);
        missionService.addMission(mission);

        assertThrows(IllegalStateException.class, () -> missionService.assignRocketToMission(mission, rocket));
    }

    @Test
    void should_change_mission_status_to_ended_reset_rocket_status_and_clear_mission() {
        missionService.addMission(mission);
        missionService.assignRocketToMission(mission, rocket);

        missionService.changeMissionStatus(mission, MissionStatus.ENDED);

        assertEquals(MissionStatus.ENDED, mission.getStatus());
        assertEquals(RocketStatus.ON_GROUND, rocket.getStatus());
        assertEquals(0, mission.getRockets().size());
    }

    @Test
    void should_return_sorted_missions_in_get_missions_summary() {
        Mission mission1 = new Mission("Mars");
        Mission mission2 = new Mission("Luna");

        missionService.addMission(mission1);
        missionService.addMission(mission2);

        Rocket rocket1 = new Rocket("Dragon 1");
        Rocket rocket2 = new Rocket("Dragon 2");

        missionService.assignRocketToMission(mission1, rocket1);
        missionService.assignRocketToMission(mission1, rocket2);

        assertEquals(2, missionService.getMissionsSummary().size());
        assertEquals("Mars", missionService.getMissionsSummary().get(0).getName());
        assertEquals("Luna", missionService.getMissionsSummary().get(1).getName());
    }

    @Test
    void should_generate_correct_summary_in_print_summary() {
        missionService.addMission(mission);
        missionService.assignRocketToMission(mission, rocket);

        String expectedSummary = "Having below data in the system:\n" +
                "\u25CF Test Mission – IN_PROGRESS – Dragons: 1\n" +
                "    \u25CF Falcon 1 – IN_SPACE\n";

        assertEquals(expectedSummary, missionService.printSummary());
    }
}

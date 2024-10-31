package test.spacex.rocket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spacex.mission.model.Mission;
import spacex.rocket.model.Rocket;
import spacex.rocket.model.RocketStatus;
import spacex.rocket.service.RocketService;
import spacex.rocket.service.RocketServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RocketServiceImplTest {

    private RocketService rocketService;
    private Mission testMission;

    @BeforeEach
    void setUp() {
        rocketService = new RocketServiceImpl();
        testMission = new Mission("Test Mission");
    }

    @Test
    void should_add_rocket_increase_rocket_count() {
        Rocket rocket = new Rocket("Falcon 1");
        rocketService.addRocket(rocket);

        assertEquals(1, rocketService.getRockets().size());
        assertEquals("Falcon 1", rocketService.getRockets().get(0).getName());
    }

    @Test
    void should_assign_rocket_to_mission_set_mission_and_status_in_space() {
        Rocket rocket = new Rocket("Falcon 9");
        rocketService.addRocket(rocket);

        rocketService.assignRocketToMission(rocket, testMission);

        assertEquals(testMission, rocket.getMission());
        assertEquals(RocketStatus.IN_SPACE, rocket.getStatus());
    }

    @Test
    void should_throw_exception_when_assign_rocket_already_assigned_to_mission() {
        Rocket rocket = new Rocket("Falcon Heavy");
        rocketService.addRocket(rocket);
        rocketService.assignRocketToMission(rocket, testMission);

        Mission anotherMission = new Mission("Another Mission");
        assertThrows(IllegalStateException.class, () -> rocketService.assignRocketToMission(rocket, anotherMission));
    }

    @Test
    void should_change_rocket_status() {
        Rocket rocket = new Rocket("Starship");
        rocketService.addRocket(rocket);

        rocketService.changeRocketStatus(rocket, RocketStatus.IN_REPAIR);

        assertEquals(RocketStatus.IN_REPAIR, rocket.getStatus());
    }
}

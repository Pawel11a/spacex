package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RocketServiceTest {

    private RocketServiceImpl rocketService;
    private Mission testMission;

    @BeforeEach
    void setUp() {
        rocketService = new RocketServiceImpl();
        testMission = new Mission("Test Mission");
    }

    @Test
    void testAddRocket() {
        Rocket rocket = new Rocket("Falcon 1");
        rocketService.addRocket(rocket);

        assertEquals(1, rocketService.getRockets().size());
        assertEquals("Falcon 1", rocketService.getRockets().get(0).getName());
    }
}

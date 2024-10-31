package spacex.service;

import spacex.model.Rocket;

import java.util.ArrayList;
import java.util.List;

public class RocketService {
    private final List<Rocket> rockets = new ArrayList<>();

    public void addRocket(Rocket rocket) {
        rockets.add(rocket);
    }

    public List<Rocket> getRockets() {
        return rockets;
    }
}

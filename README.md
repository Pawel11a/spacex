# SpaceX Dragon Rockets Repository

A simple project in Java 17 simulating a repository for SpaceX Dragon rockets, designed for managing missions and rockets. The project was developed using Test-Driven Development (TDD) techniques, adhering to SOLID principles and clean code practices.

## Installation and Creating a JAR Library

### Method 1: Using IntelliJ IDEA

1. **Open Project Structure**: In the menu, select `File > Project Structure...`.
2. **Go to the Artifacts tab**: In the left menu, select `Artifacts`.
3. **Add a new JAR Artifact**:
    - Click the `+` icon and choose `JAR > From modules with dependencies`.
    - Select the module you want to build as a library (if it's a library without a main entry point, leave it blank).
4. **Set the JAR destination**: Specify where IntelliJ should save the generated JAR file.
5. **Configure contents**: Ensure all required files and dependencies are included in the JAR. IntelliJ typically includes project classes and resources automatically.
6. **Build the Artifact**: After configuration, click `OK`. Then, go to `Build > Build Artifacts...`, select your JAR, and click `Build`.

After following these steps, the generated JAR file will be ready in the specified location.

### Method 2: Using the Terminal

1. **Compile `.java` files to the `build` directory**:
   ```bash
   javac -d ./build *.java
   ```
2. **Create the JAR file**:
   ```bash
   jar cvf spacex-library.jar -C build .
   ```

## Project Structure and Code Organization

The project is organized into packages for clarity and modularity:

- `spacex.mission.model` – mission models.
- `spacex.mission.service` – business logic related to missions.
- `spacex.rocket.model` – rocket models.
- `spacex.rocket.service` – business logic related to rockets.

## Functionality

- **Add a new rocket** – initial status: On ground.
- **Assign a rocket to a mission** (one mission per rocket).
- **Change rocket status** (On ground, In space, In repair).
- **Add a new mission** – initial status: Scheduled.
- **Assign rockets to a mission** (one mission can have multiple rockets).
- **Change mission status**:
    - Scheduled – initial state when no rockets are assigned.
    - Pending – at least one rocket is assigned and in the In repair status.
    - In Progress – at least one rocket is assigned, and none are in In repair.
    - Ended – final stage of the mission; rockets are detached.
- **Generate a mission summary** with the number of assigned rockets in descending order.

## Usage Examples

```java
RocketService rocketService = new RocketServiceImpl();
MissionService missionService = new MissionServiceImpl();

Mission mars = new Mission("Mars");
Mission luna1 = new Mission("Luna1");
Mission doubleLanding = new Mission("Double Landing");
Mission transit = new Mission("Transit");
Mission luna2 = new Mission("Luna2");
Mission verticalLanding = new Mission("Vertical Landing");

missionService.addMission(mars);
missionService.addMission(luna1);
missionService.addMission(doubleLanding);
missionService.addMission(transit);
missionService.addMission(luna2);
missionService.addMission(verticalLanding);

Rocket dragon1 = new Rocket("Dragon 1");
Rocket dragon2 = new Rocket("Dragon 2");
Rocket redDragon = new Rocket("Red Dragon");
Rocket dragonXL = new Rocket("Dragon XL");
Rocket falconHeavy = new Rocket("Falcon Heavy");

rocketService.addRocket(dragon1);
rocketService.addRocket(dragon2);
rocketService.addRocket(redDragon);
rocketService.addRocket(dragonXL);
rocketService.addRocket(falconHeavy);

rocketService.assignRocketToMission(dragon1, luna1);
rocketService.assignRocketToMission(dragon2, luna1);
missionService.changeMissionStatus(luna1, luna1.getStatus());

rocketService.assignRocketToMission(redDragon, transit);
rocketService.assignRocketToMission(dragonXL, transit);
rocketService.assignRocketToMission(falconHeavy, transit);
missionService.changeMissionStatus(transit, transit.getStatus());

rocketService.changeRocketStatus(dragon1, RocketStatus.ON_GROUND);
rocketService.changeRocketStatus(dragon2, RocketStatus.ON_GROUND);
rocketService.changeRocketStatus(redDragon, RocketStatus.ON_GROUND);
rocketService.changeRocketStatus(dragonXL, RocketStatus.IN_SPACE);
rocketService.changeRocketStatus(falconHeavy, RocketStatus.IN_SPACE);

missionService.changeMissionStatus(luna1, MissionStatus.PENDING);
missionService.changeMissionStatus(transit, MissionStatus.IN_PROGRESS);

System.out.println(missionService.printSummary());
```

### Example Output

Having the following data in the system:

- **Transit – IN_PROGRESS** – Dragons: 3
    - Red Dragon – ON_GROUND
    - Dragon XL – IN_SPACE
    - Falcon Heavy – IN_SPACE
- **Luna1 – PENDING** – Dragons: 2
    - Dragon 1 – ON_GROUND
    - Dragon 2 – ON_GROUND
- **Vertical Landing – SCHEDULED** – Dragons: 0
- **Mars – SCHEDULED** – Dragons: 0
- **Luna2 – SCHEDULED** – Dragons: 0
- **Double Landing – SCHEDULED** – Dragons: 0

## Assumptions and Design Decisions

- All rockets and missions are stored in memory (no persistent storage).
- Each rocket can be assigned to only one mission at a time.
- Mission and rocket statuses automatically update according to business logic rules.

## Test-Driven Development (TDD)

The project was developed using TDD. All core functions are unit-tested, ensuring stability and alignment with requirements.
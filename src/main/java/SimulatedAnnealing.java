import entities.Route;
import entities.World;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class SimulatedAnnealing {

    World world;
    Route initialRoute;
    private ArrayList<Route> allRoutes = new ArrayList<>();

    public SimulatedAnnealing(World world, Route route) {
        this.world = world;
        this.initialRoute = route;
        run();
    }

    public ArrayList<Route> fetchAllRoutes() {
        return allRoutes;
    }


    private void run() {
        double currentTemperature = world.startingTemperature;
        int citySwap = world.maxCitySwap;

        allRoutes.add(initialRoute);
        Route route = new Route(initialRoute);

        double previousDistance;
        double currentDistance;
        double distanceDifference;

        while (currentTemperature >= world.finalTemperature) {
            for (int iteration = 0; iteration < world.iterationsPerTemperature; iteration++) {
                previousDistance = route.getTotalDistance();
                route.swapCitiesInRoute(citySwap);
                route.calculateTotalDistance(world.distanceMatrix);
                currentDistance = route.getTotalDistance();
                distanceDifference = Math.abs(previousDistance - currentDistance);

                if (currentDistance < previousDistance) {
                    allRoutes.add(new Route(route));
                    currentTemperature = currentTemperature * world.coolingFactor;
                    System.out.println("Improved");
                } else {
                    if (ThreadLocalRandom.current().nextDouble(1.0)
                            < Math.exp(-distanceDifference / currentTemperature)) {
                        allRoutes.add(new Route(route));
                        System.out.println("Taken still");
                    } else {
                        System.out.println("Failed");
                    }
                }
                citySwap = (int) Math.ceil(citySwap - (citySwap * (1 / currentTemperature)));
            }
        }
    }

}

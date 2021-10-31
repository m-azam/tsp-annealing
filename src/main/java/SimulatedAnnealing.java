import entities.Route;
import entities.World;

import java.util.ArrayList;

public class SimulatedAnnealing {

    World world;
    Route initialRoute;
    ArrayList<Route> allRoutes = new ArrayList<>();

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
                currentDistance = route.getTotalDistance();
                distanceDifference = Math.abs(previousDistance - currentDistance);

                if (currentDistance < previousDistance) {
                    allRoutes.add(new Route(route));
                    currentDistance = currentTemperature * world.coolingFactor;
                } else {

                }
                citySwap = (int) Math.ceil(citySwap * (1- 1/currentTemperature));
            }
            currentTemperature = world.coolingFactor * currentTemperature;
        }
    }

}

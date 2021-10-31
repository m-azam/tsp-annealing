import entities.Route;
import entities.World;

import java.util.Objects;
import java.util.Scanner;

public class TravelingSalesmanApplication {
    public static void main(String[] args) {
        int numberOfCities = 100;
        double startingTemperature = 30000000.0;
        double coolingFactor = 0.3;
        int iterationsPerTemperature = 10;
        double finalTemperature = 10000.0;
        int maxCitySwap = 98;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Press enter for default values.");
        System.out.println("Enter number of cities, default is 100:");
        String citiesInput = scanner.nextLine();
        if (!Objects.equals(citiesInput, "")) {
            numberOfCities = Integer.parseInt(citiesInput);
        }
        System.out.println("Enter starting temperature, default is 30000000.0:");
        String startingTemperatureInput = scanner.nextLine();
        if (!Objects.equals(startingTemperatureInput, "")) {
            startingTemperature = Double.parseDouble(startingTemperatureInput);
        }
        System.out.println("Enter cooling factor, default is 0.3:");
        String coolingFactorInput = scanner.nextLine();
        if (!Objects.equals(coolingFactorInput, "")) {
            coolingFactor = Double.parseDouble(coolingFactorInput);
        }
        System.out.println("Enter iterations per Temperature, default is 10000.0:");
        String iterationsPerTemperatureInput = scanner.nextLine();
        if (!Objects.equals(iterationsPerTemperatureInput, "")) {
            iterationsPerTemperature = Integer.parseInt(iterationsPerTemperatureInput);
        }
        System.out.println("Enter max pair of cities to swap, default is number of cities - 2:");
        String maxCitySwapInput = scanner.nextLine();
        if (!Objects.equals(maxCitySwapInput, "")) {
            maxCitySwap = Integer.parseInt(maxCitySwapInput);
        } else {
            maxCitySwap = numberOfCities - 2;
        }
        System.out.println("Enter final temperature, default is :");
        String finalTemperatureInput = scanner.nextLine();
        if (!Objects.equals(finalTemperatureInput, "")) {
            finalTemperature = Double.parseDouble(finalTemperatureInput);
        }

        World world = new World(numberOfCities, startingTemperature, coolingFactor, iterationsPerTemperature
                , finalTemperature, maxCitySwap);
        Route route = new Route(numberOfCities, world.distanceMatrix);
        SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing(world, route);
    }
}

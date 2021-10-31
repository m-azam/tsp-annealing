package entities;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Route {

    ArrayList<Integer> sequence = new ArrayList<>();
    double totalDistance = 0.0;

    public Route(int numberOfCities, double[][] distanceMatrix) {
        sequence.addAll(ThreadLocalRandom.current().ints(0, numberOfCities).distinct().limit(numberOfCities).boxed().collect(Collectors.toList()));
        calculateTotalDistance(distanceMatrix);
    }

    public Route(Route route) {
        this.sequence.addAll(route.getSequence());
        this.totalDistance = route.getTotalDistance();
    }

    public void calculateTotalDistance(double[][] distanceMatrix) {
        totalDistance = 0.0;
        for (int city = 1; city < sequence.size(); city++) {
            totalDistance = totalDistance + distanceMatrix[sequence.get(city - 1)][sequence.get(city)];
        }
        totalDistance = totalDistance + distanceMatrix[sequence.get(sequence.size() - 1)][sequence.get(0)];
    }

    public void swapCitiesInRoute(int numberOfCitiesToSwap) {
        for (int iteration = 0; iteration < numberOfCitiesToSwap; iteration++) {
            int cityIndexA = ThreadLocalRandom.current().nextInt(sequence.size());
            int cityIndexB = ThreadLocalRandom.current().nextInt(sequence.size());
            int tempCity = sequence.get(cityIndexA);
            sequence.set(cityIndexA, sequence.get(cityIndexB));
            sequence.set(cityIndexB, tempCity);
        }
    }

    public ArrayList<Integer> getSequence() {
        return sequence;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

}

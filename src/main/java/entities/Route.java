package entities;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Route {

    ArrayList<Integer> sequence = new ArrayList<>();
    double totalDistance = 0.0;
    ArrayList<Double> distances = new ArrayList<>();
    double fitness;

    public Route(int numberOfCities, double[][] distanceMatrix) {
        sequence.addAll(ThreadLocalRandom.current().ints(0, numberOfCities).distinct().limit(numberOfCities).boxed().collect(Collectors.toList()));
        calculateTotalDistance(distanceMatrix);
        calculateFitness();
    }

    private void calculateTotalDistance(double[][] distanceMatrix) {
        for (int city = 1; city < sequence.size(); city++) {
            totalDistance = totalDistance + distanceMatrix[sequence.get(city - 1)][sequence.get(city)];
        }
        totalDistance = totalDistance + distanceMatrix[sequence.get(sequence.size() - 1)][sequence.get(0)];
    }

    public void calculateFitness() {
        this.fitness = 1 / totalDistance;
    }

    public ArrayList<Integer> getSequence() {
        return sequence;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public double getFitness() {
        return fitness;
    }

}

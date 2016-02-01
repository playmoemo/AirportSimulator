package com.trinborg;

import java.text.DecimalFormat;

/**
 * Keeps track of airport activity, saves data during the simulation,
 * and generates a statistical summary at the end of the simulation.
 * Created by tborg(Øyvind Trinborg) on 21.01.16.
 * @author Øyvind Trinborg
 */
public class Statistics {
    /**
     * The time step that set by the user.
     */
    private int globalTimeStep;
    /**
     * The total amount of planes that have landed.
     */
    private int totalArrivedPlanes;
    /**
     * The total amount of planes that have departed.
     */
    private int totalDepartedPlanes;
    /**
     * The total amount of planes that have been rejected from entering landing queue and departure queue.
     */
    private int totalRejectedPlanes;
    /**
     * The amount of planes in landing queue for each time step.
     */
    private int[] landingQueueLengths;
    /**
     * Index variable to keep track of the next free space in landingQueueLengths.
     */
    private int landingIndex;
    /**
     * The amount of planes in departure queue for each time step.
     */
    private int[] departureQueueLengths;
    /**
     * Index variable to keep track of the next free space in departureQueueLengths.
     */
    private int departureIndex;
    /**
     * The amount of time steps each plane needs to wait in landing queue before landing.
     */
    private int[] landingIdleTimes;
    /**
     * Index variable to keep track of the next free space in landingIdleTimes.
     */
    private int landingIdleTimeIndex;
    /**
     * The amount of time steps each plane needs to wait in departure queue before takeoff.
     */
    private int[] departureIdleTimes;
    /**
     * Index variable to keep track of the next free space in departureIdleTimes.
     */
    private int departureIdleTimeIndex;
    /**
     * The amount of planes remaining in landing queue after simulation end.
     */
    private int leftoversInLandingQueue;
    /**
     * The amount of planes remaining in departure queue after simulation end.
     */
    private int leftoversInDepartureQueue;
    /**
     * Limits the number of decimal places to two decimals for a more presentable output of decimal values.
     */
    private DecimalFormat twoDecimalPlaces;

    /**
     * Creates an instance of the class and performs assignment of private fields.
     * @param timeStep the global timestep for the simulation set by the user.
     */
    public Statistics(int timeStep) {
        globalTimeStep = timeStep;
        totalArrivedPlanes = totalDepartedPlanes = totalRejectedPlanes = 0;
        landingIndex = departureIndex = landingIdleTimeIndex = departureIdleTimeIndex = 0;
        landingQueueLengths = new int[timeStep];
        departureQueueLengths = new int[timeStep];
        landingIdleTimes = new int[timeStep];
        departureIdleTimes = new int[timeStep];
        twoDecimalPlaces = new DecimalFormat("#.00");
    }

    /**
     * Adds 1 to the total amount of arrived planes.
     */
    public void incrementTotalArrivedPlanes() {
        totalArrivedPlanes++;
    }

    /**
     * Adds 1 to the total amount of departed planes.
     */
    public void incrementTotalDepartedPlanes() {
        totalDepartedPlanes++;
    }

    /**
     * Accessor method for the sum of private fields totalArrivedPlanes and totalDepartedPlanes.
     * @return the amount of planes that have landed or departed.
     */
    private int getTotalArrivedAndDepartedPlanes() {
        return totalArrivedPlanes + totalDepartedPlanes;
    }

    /**
     * Adds 1 to the total number of rejected planes.
     * Includes intended landings and departures.
     */
    public void incrementTotalRejectedPlanes() {
        totalRejectedPlanes++;
    }

    /**
     * Accessor method for the sum of private fields totalArrivedPlanes,
     * totalDepartedPlanes and totalRejectedPlanes.
     * @return the amount of planes involved in the simulation.
     */
    private int getTotalInvolvedPlanes() {
        return totalArrivedPlanes +
                totalDepartedPlanes +
                totalRejectedPlanes +
                leftoversInLandingQueue +
                leftoversInDepartureQueue;
    }

    /**
     * Mutator method for private field landingQueueLengths.
     * @param length represents the number of elements in the landing queue for 1 timestep.
     */
    public void addLandingQueueLength(int length) {
        landingQueueLengths[landingIndex] = length;
        landingIndex++;
    }

    /**
     * Mutator method for private field departureQueueLengths.
     * @param length represents the number of elements in the departure queue for 1 timestep.
     */
    public void addDepartureQueueLength(int length) {
        departureQueueLengths[departureIndex] = length;
        departureIndex++;
    }

    /**
     * Calculates the average length of the landing queue.
     * @return average length of landing queue.
     */
    private double getAvgLandingQueueLength() {
        double avg = 0.0;
        for (int i = 0; i < landingQueueLengths.length; i++) {
            avg += landingQueueLengths[i];
        }
        avg = avg / landingQueueLengths.length;
        String formatted = twoDecimalPlaces.format(avg);

        return Double.parseDouble(formatted);
    }

    /**
     * Calculates the average length of the departure queue.
     * @return average length of departure queue.
     */
    private double getAvgDepartureQueueLength() {
        double avg = 0.0;
        for (int i = 0; i < departureQueueLengths.length; i++) {
            avg += departureQueueLengths[i];
        }
        avg = avg / departureQueueLengths.length;
        String formatted = twoDecimalPlaces.format(avg);

        return Double.parseDouble(formatted);
    }

    /**
     * Calculates the percentage of rejected planes.
     * @return percentage rejected planes.
     */
    private double getRejectedPercentage() {
        int allPlanes = getTotalInvolvedPlanes();
        double rejectedPercentage = (totalRejectedPlanes * 100) / allPlanes;
        String formatted = twoDecimalPlaces.format(rejectedPercentage);

        return Double.parseDouble(formatted);
    }

    /**
     * Saves the idle time in queue for the planes.
     * @param time the idle time.
     * @param action an identifier for the type of action(Landing or Departure).
     */
    public void addIdleTime(int time, char action) {
        switch (action) {
            case 'L':
                landingIdleTimes[landingIdleTimeIndex] = time;
                landingIdleTimeIndex++;
                break;
            case 'D':
                departureIdleTimes[departureIdleTimeIndex] = time;
                departureIdleTimeIndex++;
                break;
            default:
                System.out.println("Not a valid plane action.");
        }
    }

    /**
     * Calculates the average idle time in a queue.
     * @param idleTimes one of two possible private Array fields: landingIdleTimes or departureIdleTimes.
     * @param planeAmount the amount of planes that have landed or departed.
     * @return the average idle time.
     */
    private double calculateAvgQueueIdleTime(int[] idleTimes, int planeAmount) {
        double avg = 0.0;
        for (int i = 0; i < planeAmount; i++) {
            avg += idleTimes[i];
        }
        avg = avg / planeAmount;
        String formatted = twoDecimalPlaces.format(avg);
        return Double.parseDouble(formatted);
    }

    /**
     * Prints a final summary report of the simulation data.
     */
    public void printStatisticsReport(int leftoverPlanesInLandingQueue, int leftoverPlanesInDepartureQueue) {
        leftoversInLandingQueue = leftoverPlanesInLandingQueue;
        leftoversInDepartureQueue = leftoverPlanesInDepartureQueue;
        System.out.println("\nAirport closed!\n\nFlight summary:" +
                "\n----------------------------------------------------" +
                "\n| Time step for simulation: " + globalTimeStep +
                "\n----------------------------------------------------" +
                "\n| Total planes involved: " + getTotalInvolvedPlanes() +
                "\n----------------------------------------------------" +
                "\n| Total arrivals and departures: " + getTotalArrivedAndDepartedPlanes() +
                "\n----------------------------------------------------" +
                "\n| Total arrived planes: " + totalArrivedPlanes +
                "\n----------------------------------------------------" +
                "\n| Total departed planes: " + totalDepartedPlanes +
                "\n----------------------------------------------------" +
                "\n| Total rejected planes: " + totalRejectedPlanes +
                "\n----------------------------------------------------" +
                "\n| Percent rejected planes: " + getRejectedPercentage() + " %" +
                "\n----------------------------------------------------" +
                "\n| Avg. length of landing queue: " + getAvgLandingQueueLength() +
                "\n----------------------------------------------------" +
                "\n| Avg. idle time in landing queue: " + calculateAvgQueueIdleTime(landingIdleTimes, totalArrivedPlanes) +
                " timesteps" +
                "\n----------------------------------------------------" +
                "\n| Avg. length of departure queue: " + getAvgDepartureQueueLength() +
                "\n----------------------------------------------------" +
                "\n| Avg. idle time in departure queue " + calculateAvgQueueIdleTime(departureIdleTimes, totalDepartedPlanes) +
                " timesteps" +
                "\n----------------------------------------------------" +
                "\n| Planes left in landing queue: " + leftoversInLandingQueue + " plane(s)" +
                "\n----------------------------------------------------" +
                "\n| Planes left in departure queue: " + leftoversInDepartureQueue + " plane(s)");
    }
}
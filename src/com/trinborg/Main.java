package com.trinborg;

/**
 * Program entry point
 */
public class Main {
    /**
     * Handles user interaction during the first part of program execution.
     */
    private static UserData userData;
    /**
     * Handles everything concerning the airport simulation.
     */
    private static Airport airport;

    /**
     * Execution entry point.
     * @param args NOT IN USE
     */
    public static void main(String[] args) {
        userData = new UserData();
        userData.startUserInteraction();
        airport = new Airport(userData.getTimeStep(),
                userData.getLandingFrequency(),
                userData.getDepartureFrequency());
        airport.startSimulation();
    }
}

package com.trinborg;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Handles and represents the input data from user.
 * Created by tborg(Øyvind Trinborg) on 29.01.16.
 * @author Øyvind Trinborg
 */
public class UserData {
    /**
     * Handles user input.
     */
    private Scanner scan;
    /**
     * The timestep provided by user.
     */
    private int timeStep;
    /**
     * The desired landing frequency provided by user.
     */
    private double landingFrequency;
    /**
     * The desired departure frequency provided by user.
     */
    private double departureFrequency;

    /**
     * Generates an instance of the class, and handles assignment of private fields.
     */
    public UserData() {
        timeStep = 0;
        landingFrequency = departureFrequency = 0.0;
        scan = new Scanner(System.in);
    }

    /**
     * Triggers the first part of the program where communication with user,
     * and necessary input from user is collected.
     */
    public void startUserInteraction() {
        greetUser();
        collectTimeStep();
        collectLandingFrequency();
        collectDepartureFrequency();
        System.out.println("Timestep: " + timeStep +
                "\nLanding frequenzy: " + landingFrequency +
                "\nDeparture frequenzy: " + departureFrequency);
    }

    /**
     * The initial "start screen".
     */
    private void greetUser() {
        System.out.println("#####################################################\n" +
                "############ Welcome to the Mini-Airport ############\n" +
                "#####################################################\n");
    }

    /**
     * Accessor method for private field timeStep.
     * @return the global timestep.
     */
    public int getTimeStep() {
        return timeStep;
    }

    /**
     * Accessor method for private field landingFrequency.
     * @return the landing frequency.
     */
    public double getLandingFrequency() {
        return landingFrequency;
    }

    /**
     * Accessor method for private field departureFrequency.
     * @return the departure frequency.
     */
    public double getDepartureFrequency() {
        return departureFrequency;
    }

    /**
     * Collects the desired timestep from user.
     */
    private void collectTimeStep() {
        timeStep = 0;
        System.out.println("Please provide the time step for this simulation:");
        try {
            timeStep = scan.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Exception: " + e.toString() + "\nInput is not valid. Please provide an integer");
            //resetting the  scanner
            scan.nextLine();
            collectTimeStep();
        }
    }

    /**
     * Collects the desired landing frequency from user.
     */
    private void collectLandingFrequency() {
        landingFrequency = 0.0;
        System.out.println("Please provide the landing frequency:");
        try {
            landingFrequency = scan.nextDouble();
            if (landingFrequency < 0.1) {
                System.out.println("The input is out of bounds. Please try again:");
                //resetting the  scanner
                scan.nextLine();
                collectLandingFrequency();
            }
        } catch (InputMismatchException e) {
            System.out.println("Exception: " + e.toString() + "\nInput is not valid. Please provide a double");
            scan.nextLine();
            collectLandingFrequency();
        }
    }

    /**
     * Collects the desired departure frequency from user.
     */
    private void collectDepartureFrequency() {
        departureFrequency = 0.0;
        System.out.println("Please provide the departure frequency:");
        try {
            departureFrequency = scan.nextDouble();
            if (departureFrequency < 0.1) {
                System.out.println("The input is out of bounds. Please try again:");
                //resetting the  scanner
                scan.nextLine();
                collectDepartureFrequency();
            }
        } catch (InputMismatchException e) {
            System.out.println("Exception: " + e.toString() + "\nInput is not valid. Please provide a double");
            scan.nextLine();
            collectDepartureFrequency();
        }
    }
}
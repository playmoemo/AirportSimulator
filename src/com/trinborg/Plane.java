package com.trinborg;

/**
 * Represents a plane in the airport simulation.
 * Created by tborg(Øyvind Trinborg) on 21.01.16.
 * @author Øyvind Trinborg
 */
public class Plane {
    /**
     * Unique identifier for a plane instance.
     */
    private int planeID;
    /**
     * The time when the plane is instantiated.
     */
    private int timeStepStart;
    /**
     * The time when the plane leaves the queue.
     */
    private int timeStepStop;

    /**
     * Creates a plane instance and assigns private fields.
     * @param currentTimeStep sets the time of instantiation.
     * @param id is a unique identifier determined for the plane instance.
     */
    public Plane(int currentTimeStep, int id) {
        timeStepStart = currentTimeStep;
        planeID = id;
    }

    /**
     * Accessor method for private field planeID.
     * @return the unique ID of the plane instance.
     */
    public int getPlaneID() {
        return planeID;
    }

    /**
     * Accessor method for the calculated time the plane has been idle in the queue.
     * @return the idle time.
     */
    public int getIdleTime() {
        return timeStepStop - timeStepStart;
    }

    /**
     * Mutator method for private field timeStepStop.
     * @param timeStep represents the global time step when the plane leaves the queue.
     */
    public void setTimeStepStop(int timeStep) {
        timeStepStop = timeStep;
    }
}
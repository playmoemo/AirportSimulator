package com.trinborg;

/**
 * A queue implementation based on code from course literature(Lewis and Chase).
 * Modified by tborg(Øyvind Trinborg) on 21.01.16.
 * @author Lewis and Chase, modified by Øyvind Trinborg
 * @version 1.0
 */
public class CustomQueue<T> {
    private int front, rear, count;
    private final int CAPACITY = 10;
    private T[] queue;

    /**
     * Creates an instance of the class with a default capacity.
     */
    public CustomQueue() {
        front = rear = count = 0;
        queue = (T[]) new Object[CAPACITY];
    }

    //not in use
    /*public CustomQueue(int capacity) {
        front = rear = count = 0;
        queue = (T[]) new Object[capacity];
    }*/

    /**
     * Adds the specified element to the rear of this queue.
     * @param element is the element to the rear of queue.
     */
    public void enqueue(T element) {
        if (getCount() == queue.length) {
            return;
        } else {
            queue[rear] = element;
            rear = (rear + 1) % queue.length;
            count++;
        }
    }

    /**
     * Removes the element at front of queue.
     * @return the element removed from front of queue.
     * @throws EmptyCollectionException if the queue is empty.
     */
    public T dequeue() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("CustomQueue");
        }

        T removedFromQueue = queue[front];
        queue[front] = null;
        front = (front + 1) % queue.length;
        count--;

        return removedFromQueue;
    }

    /**
     * Returns the element in front of queue, but does not remove it.
     * @return the element in front of queue.
     * @throws EmptyCollectionException if the queue is empty.
     */
    public T inspectFront() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("CustomQueue");
        }
        return queue[front];
    }

    /**
     * Checks if queue is empty.
     * @return boolean value true if queue is empty, false if not.
     */
    public boolean isEmpty() {
        if (getCount() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Checks if queue is full.
     * @return boolean value true if queue is full, false if not.
     */
    public boolean isFull() {
        if (count == CAPACITY) {
            return true;
        }
        return false;
    }

    /**
     * Provides the number of elements in queue.
     * @return count as number of elements in queue.
     */
    public int getCount() {
        return count;
    }
}

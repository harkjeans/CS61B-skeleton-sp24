package gh2;

import deque.*;

//Note: This file will not compile until you complete the Deque61B implementations
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
     private Deque61B<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        // Calculate the capacity (buffer size) based on frequency
        int capacity = (int) Math.round(SR / frequency);

        // Initialize the buffer with calculated capacity
        buffer = new ArrayDeque61B<>();

        // Fill the buffer with zeros initially
        for (int i = 0; i < capacity; i++) {
            buffer.addLast(0.0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        // Remove all existing elements from the buffer
        int size = buffer.size();
        buffer = new ArrayDeque61B<>();

        // Fill with new random numbers between -0.5 and 0.5
        for (int i = 0; i < size; i++) {
            double r = Math.random() - 0.5;
            buffer.addLast(r);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        // Dequeue the front sample
        double first = buffer.removeFirst();

        // Get the new front sample (which is now at the front after removal)
        double second = buffer.get(0);

        // Calculate the new sample: average of the two multiplied by DECAY
        double newSample = ((first + second) / 2.0) * DECAY;

        // Enqueue the new sample at the back
        buffer.addLast(newSample);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        if (buffer.size() == 0) {
            return 0.0;
        }
        return buffer.get(0);
    }
}

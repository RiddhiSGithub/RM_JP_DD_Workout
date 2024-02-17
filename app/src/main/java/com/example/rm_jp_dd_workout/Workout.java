package com.example.rm_jp_dd_workout;

// Class to represent a workout session
public class Workout {

    int steps; // Number of steps taken
    int weight; // Weight of the person (in kg)
    int height; // Height of the person (in cm)
    float calories; // Calories burned during the workout session

    final float MET = 3.5f; // Metabolic equivalent of task (MET) constant

    // Constructor to initialize the Workout object with steps, weight, and height
    public Workout(int steps, int weight, int height) {
        this.steps = steps;
        this.weight = weight;
        this.height = height;
    }

    // Method to calculate the calories burned during the workout session
    public float calculateCalories() {
        float stride = height * 0.414f; // Calculate the stride length
        float distance = stride * steps; // Calculate the total distance covered
        float time = distance / getSpeed(steps); // Calculate the total time taken (in minutes)
        // Calculate the calories burned using the formula: calories = time * MET * weight / (200 * 60)
        calories = time * MET * weight / (200 * 60);
        return calories; // Return the calculated calories
    }

    // Method to get the speed based on the number of steps
    private float getSpeed(int steps) {
        if (steps >= 0 && steps <= 79)
            return 0.9f; // Low speed (0.9 meters per second)
        else if (steps >= 80 && steps <= 99)
            return 1.34f; // Medium speed (1.34 meters per second)
        else
            return 1.79f; // High speed (1.79 meters per second)
    }
}

package com.example.rm_jp_dd_workout;

public class Workout {

    int steps;
    int weight;
    int height;
    float calories;

    final float MET = 3.5f;

    public Workout(int steps, int weight, int height) {
        this.steps = steps;
        this.weight = weight;
        this.height = height;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public float calculateCalories()
    {
        float stride = height * 0.414f;
        float distance = stride * steps;
        float time = distance / getSpeed(steps);
        calories = time * MET * weight/ (200*60);
        return calories;
    }

    private float getSpeed(int steps){
        if(steps >= 0 && steps <= 79)
            return 0.9f;
        else if(steps >= 80 && steps <= 99)
            return 1.34f;
        else
            return 1.79f;
    }
}
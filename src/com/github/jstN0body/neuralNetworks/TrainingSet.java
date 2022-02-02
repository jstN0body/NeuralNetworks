package com.github.jstN0body.neuralNetworks;

public class TrainingSet {

    private final double[] in, out;

    public TrainingSet(double[] inputValues, double[] outputValues) {
        in = inputValues;
        out = outputValues;
    }

    public double[] getInput() {
        return in;
    }

    public double[] getOutput() {
        return out;
    }
}

package com.github.jstN0body.neuralNetworks.training;

public class TrainingSet {

    private final double[] input, output;

    public TrainingSet(double[] in, double[] out) {
        input = in;
        output = out;
    }

    public double[] getInput() {
        return input;
    }

    public double[] getOutput() {
        return output;
    }
}

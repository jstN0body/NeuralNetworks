package com.github.jstN0body.neuralNetworks.layers;

public class InputLayer extends Layer {

    private final double[] neuronActivations;

    public InputLayer(double... inputValues) {
        super(inputValues.length);
        neuronActivations = inputValues;
    }

    @Override
    public double[] getActivations() {
        return neuronActivations;
    }
}

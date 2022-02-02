package com.github.jstN0body.neuralNetworks.layers;

public class OutputLayer extends Layer {

    private final double[] neuronBiases;
    private final double[][] neuronWeights;
    private final Layer m_prevLayer;

    private double[] neuronActivations;

    public OutputLayer(int neuronAmount, Layer prevLayer) {
        super(neuronAmount);

        neuronBiases = new double[neuronAmount];
        neuronActivations = new double[neuronAmount];
        m_prevLayer = prevLayer;

        neuronWeights = new double[neuronAmount][prevLayer.m_neuronAmount];
        for (int r = 0; r < neuronAmount; r++) {
            for (int c = 0; c < prevLayer.m_neuronAmount; c++) {
                neuronWeights[r][c] = Math.random();
            }
        }
    }

    @Override
    public double[] getActivations() {
        return neuronActivations;
    }

    public void setActivations(double[] activations) {
        neuronActivations = activations;
    }

    @Override
    public double[][] getWeights() {
        return neuronWeights;
    }

    @Override
    public Layer getPrevLayer() {
        return m_prevLayer;
    }

    public double[] getBiases() {
        return neuronBiases;
    }
}

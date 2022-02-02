package com.github.jstN0body.neuralNetworks.layers;


public class HiddenLayer extends Layer {

    private final double[] neuronBiases;
    private final double[][] neuronWeights;
    private final Layer m_prevLayer;
    private Layer m_nextLayer = null;

    private double[] neuronActivations;

    public HiddenLayer(int neuronAmount, Layer prevLayer) {
        super(neuronAmount);

        m_prevLayer = prevLayer;
        neuronBiases = new double[neuronAmount];
        neuronActivations = new double[neuronAmount];

        neuronWeights = new double[neuronAmount][prevLayer.m_neuronAmount];
        for (int r = 0; r < neuronAmount; r++) {
            for (int c = 0; c < prevLayer.m_neuronAmount; c++) {
                neuronWeights[r][c] = Math.random();
            }
        }
    }

    public double[] getBiases() {
        return neuronBiases;
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

    @Override
    public Layer getNextLayer() {
      return m_nextLayer;
    }
    
    @Override
    public void setNextLayer(Lyaer next) {
      if (m_nextLayer == null) m_nextLayer = next;
    }
}

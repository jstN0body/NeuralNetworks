package com.github.jstN0body.neuralNetworks.layers;

public class Layer {
    public final int m_neuronAmount;

    public Layer(int neuronAmount) {
        m_neuronAmount = neuronAmount;
    }

    public double[] getActivations() {
        return new double[0];
    }

    public double[][] getWeights() {
        return new double[0][0];
    }

    public Layer getPrevLayer() {
        return new Layer(0);
    }

    public Layer getNextLayer() {
      return new Layer(0);
    }

    public void setNextLayer(Layer next) {}
}

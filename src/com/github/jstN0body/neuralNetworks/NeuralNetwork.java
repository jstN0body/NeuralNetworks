package com.github.jstN0body.neuralNetworks;

public class NeuralNetwork {

    private final int m_inputSize;
    private final Layer[] layers;
    private final Layer outputLayer;

    public NeuralNetwork(int inputSize, int layerAmount, int... layerSizes) {
        m_inputSize = inputSize;
        layers = new Layer[layerAmount];

        for (int i = 0; i < layerAmount; i++) {
            layers[i] = new Layer(layerSizes[i], i, this);
        }

        outputLayer = layers[layerAmount-1];
    }

    public int getInputSize() {
        return m_inputSize;
    }

    public Layer getLayer(int layer) {
        return layers[layer];
    }

    public Layer getOutputLayer() {
        return outputLayer;
    }

    public void forwardProp(double[] inputArray) {
        Matrix input = Matrix.fromArray(inputArray);
        for (int i = 0; i < layers.length; i++) {
            Layer layer = layers[i];
            Matrix prevActivations = i == 0 ? input : getLayer(i-1).activations;
            layer.activations = Matrix.multiply(layer.weights, prevActivations);
            layer.activations.add(layer.biases);
            layer.activations.sigmoid();
        }
    }
}

package com.github.jstN0body.neuralNetworks;

public class Layer {

    public final Matrix weights, biases;
    public Matrix activations;

    private final int m_neurons, m_index;
    private final NeuralNetwork m_network;

    public Layer(int neurons, int index, NeuralNetwork network) {
        m_neurons = neurons;
        m_index = index;
        m_network = network;

        if (m_index == 0) {
            weights = new Matrix(m_neurons, m_network.getTrainingSet().getInput().length);
        } else {
            weights = new Matrix(m_neurons, m_network.getLayer(m_index-1).getSize());
        }

        biases = new Matrix(m_neurons, 1);
        activations = Matrix.fromArray(new double[m_neurons]);
    }

    public int getSize() {
        return m_neurons;
    }

    public int getIndex() {
        return m_index;
    }
}

package com.github.jstN0body.neuralNetworks;

import com.github.jstN0body.neuralNetworks.layers.HiddenLayer;
import com.github.jstN0body.neuralNetworks.layers.Layer;
import com.github.jstN0body.neuralNetworks.layers.OutputLayer;

import java.util.Arrays;

public class NeuralNetwork {

    private final TrainingSet m_trainingSet;
    private final double m_learningRate;
    private final OutputLayer m_outputLayer;
    private final HiddenLayer[] m_hiddenLayers;

    public NeuralNetwork(double learningRate, TrainingSet trainingSet, OutputLayer outputLayer, HiddenLayer... hiddenLayers) {
        m_learningRate = learningRate;
        m_trainingSet = trainingSet;
        m_hiddenLayers = hiddenLayers;
        m_outputLayer = outputLayer;
    }

    public void forwardProp() {
        for (HiddenLayer hiddenLayer : m_hiddenLayers) {
            double[] layerActivations = new double[hiddenLayer.m_neuronAmount];
            double[] prevActivations = hiddenLayer.getPrevLayer().getActivations();
            double[][] weights = hiddenLayer.getWeights();

            for (int i = 0; i < weights.length; i++) {
                double[] a = MathUtil.dotProduct(weights[i], prevActivations);
                double weightedSum = MathUtil.summateArray(a) + hiddenLayer.getBiases()[i];

                layerActivations[i] = MathUtil.sigmoid(weightedSum);
            }

            hiddenLayer.setActivations(layerActivations);
        }

        double[] layerActivations = new double[m_outputLayer.m_neuronAmount];
        double[] prevActivations = m_outputLayer.getPrevLayer().getActivations();
        double[][] weights = m_outputLayer.getWeights();

        for (int i = 0; i < weights.length; i++) {
            double[] a = MathUtil.dotProduct(weights[i], prevActivations);
            double weightedSum = MathUtil.summateArray(a) + m_outputLayer.getBiases()[i];
            layerActivations[i] = MathUtil.sigmoid(weightedSum);
        }

        m_outputLayer.setActivations(layerActivations);
    }

    public void backwardProp() {
        double[][] netError = MathUtil.fromArray(MathUtil.subtract(trainingSet.getOutput(), m_outputLayer.getActivations()));
    }

    public void train(int iterations) {
        for (int i = 0; i < iterations; i++) {
            forwardProp();
            backwardProp();
        }
        System.out.println(Arrays.toString(m_outputLayer.getActivations()));
        System.out.println(Arrays.toString(m_trainingSet.getOutput()));
    }

    public double cost() {
        double[] actualOut = m_outputLayer.getActivations();
        double[] expectedOut = m_trainingSet.getOutput();
        int n = expectedOut.length;
        double cost = 0;
        for (int i = 0; i < n; i++) {
            cost += Math.pow((expectedOut[i] - actualOut[i]), 2);
        }
        cost /= n;

        return cost;
    }
}

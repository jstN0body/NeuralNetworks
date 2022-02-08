package com.github.jstN0body.neuralNetworks;

import com.github.jstN0body.neuralNetworks.training.TrainingSet;
import com.github.jstN0body.neuralNetworks.NetworkUtil;
import scala.Tuple2;

import java.io.File;
import java.security.KeyPair;
import java.util.Arrays;
import java.util.List;

public class NeuralNetwork {

    private TrainingSet m_trainingSet;

    private final double m_learningRate;
    private final Layer[] layers;
    private final Layer outputLayer;
    private final Tuple2<List<File>, List<File>> saveFiles;


    public NeuralNetwork(TrainingSet trainingSet, double learningRate, int layerAmount, int... hiddenLayerSizes) {
        m_trainingSet = trainingSet;
        m_learningRate = learningRate;
        layers = new Layer[layerAmount];

        for (int i = 0; i < layerAmount-1; i++) {
            layers[i] = new Layer(hiddenLayerSizes[i], i, this);
        }

        outputLayer = new Layer(trainingSet.getOutput().length, layerAmount-1, this);
        layers[layerAmount-1] = outputLayer;

        saveFiles = NetworkUtil.generateSaveFiles(layerAmount);
    }

    public Layer getLayer(int layer) {
        return layers[layer];
    }

    public Layer getOutputLayer() {
        return outputLayer;
    }

    public TrainingSet getTrainingSet() {
        return m_trainingSet;
    }

    public void setTrainingSet(TrainingSet set) {
        m_trainingSet = set;
    }

    public void forwardProp() {
        Matrix input = Matrix.fromArray(m_trainingSet.getInput());
        for (int i = 0; i < layers.length; i++) {
            Layer layer = layers[i];
            Matrix prevActivations = i == 0 ? input : getLayer(i-1).activations;
            layer.activations = Matrix.multiply(layer.weights, prevActivations);
            layer.activations.add(layer.biases);
            layer.activations.sigmoid();
        }
    }

    public void backwardProp() {
        Matrix input = Matrix.fromArray(m_trainingSet.getInput());
        Matrix target = Matrix.fromArray(m_trainingSet.getOutput());
        Matrix netError = Matrix.subtract(target, getOutputLayer().activations);
        for (int i = 0; i < layers.length; i++) {
            int layerIndex = layers.length-i-1;
            Layer previousLayer = layerIndex != 0 ? getLayer(layerIndex - 1) : null;
            Layer currentLayer = getLayer(layerIndex);
            Matrix error;
            if (i == 0) {
                error = netError;
            } else {
                Layer nextLayer = getLayer(layerIndex + 1);
                Matrix wT = Matrix.transpose(nextLayer.weights);
                error = Matrix.multiply(wT, netError);
            }

            Matrix gradient = currentLayer.activations.dsigmoid();
            gradient.multiply(error);
            gradient.multiply(m_learningRate);

            Matrix prevActivations = layerIndex == 0 ?
                    Matrix.transpose(input) : Matrix.transpose(previousLayer.activations);
            Matrix delta = Matrix.multiply(gradient, prevActivations);
            currentLayer.weights.add(delta);
            currentLayer.biases.add(gradient);
        }
    }

    public void train() {
        forwardProp();
        backwardProp();
    }

    public void predict(double[] input) {
        forwardProp();
        System.out.println(Arrays.toString(input));
        System.out.println(getOutputLayer().activations.toArray());
    }

    public void saveValues() {
        for (int i = 0; i < layers.length; i++) {
            NetworkUtil.saveWeights(layers[i], saveFiles._1.get(i));
            NetworkUtil.saveBiases(layers[i], saveFiles._2.get(i));
        }
        System.out.println("Weights and biases successfully saved.");
    }

    public void loadValues() {
        for (int i = 0; i < layers.length; i++) {
            Layer current = layers[i];
            File weights = saveFiles._1.get(i), biases = saveFiles._1.get(i);
            Matrix w = NetworkUtil.loadWeights(weights), b = NetworkUtil.loadBiases(biases);
            current.weights.set(w.data);
            current.biases.set(b.data);
        }
    }

    public double meanSqError() {
        double sum = 0;
        List<Double> actualOut = getOutputLayer().activations.toArray();
        double[] expectedOut = m_trainingSet.getOutput();
        for (int i = 0; i < actualOut.size(); i++) {
            sum += Math.pow((expectedOut[i] - actualOut.get(i)), 2);
        }

        sum /= actualOut.size();
        return sum;
    }

}

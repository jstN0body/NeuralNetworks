package com.github.jstN0body.neuralNetworks;

import com.github.jstN0body.neuralNetworks.layers.HiddenLayer;
import com.github.jstN0body.neuralNetworks.layers.InputLayer;
import com.github.jstN0body.neuralNetworks.layers.OutputLayer;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        InputLayer input = new InputLayer(1, 3, 2);
        HiddenLayer hidden = new HiddenLayer(3, input);
        OutputLayer out = new OutputLayer(3, hidden);
        double[] expectedOutput = {2, 6, 4};

        TrainingSet trainingSet = new TrainingSet(input.getActivations(), expectedOutput);

        System.out.println(Arrays.deepToString(hidden.getWeights()));
        NeuralNetwork network = new NeuralNetwork(0.25, trainingSet, out, hidden);
        network.train(100);
        System.out.println(Arrays.deepToString(hidden.getWeights()));
    }
}

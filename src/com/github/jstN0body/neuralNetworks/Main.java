package com.github.jstN0body.neuralNetworks;

//import com.github.jstN0body.neuralNetworks.training.ScalaStudent;
import com.github.jstN0body.neuralNetworks.training.Student;
import scala.reflect.internal.pickling.UnPickler;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static final boolean LOAD_VALUES = true; // whether to attempt to load weights and biases from file
    public static final boolean TRAIN = true; // whether to train the neural network
    public static final int TRAINING_ITERATIONS = 5000000; // how many training iterations to perform
    public static final double[] TEST_INPUT = {0, 4, 2, 1, 0}; // the test input to be run after training

    public static void main(String[] args) {
        ArrayList<Student> students = parseData();
        NeuralNetwork network = new NeuralNetwork(students.get(0), 0.25, 5, 5, 3, 3, 3);

        if(LOAD_VALUES) network.loadValues();

        if (TRAIN) {
            for (int i = 0; i < TRAINING_ITERATIONS; i++) {
                network.train();
                int x = new Random().nextInt(students.size());
                network.setTrainingSet(students.get(x));
            }
        }

        network.predict(TEST_INPUT);
        network.saveValues();
    }

    /*public static TrainingSet randomTrainingSet() {
        double[][] possibleInput = {{0, 1}, {0, 1}, {1, 0}, {0, 0}, {1, 1}, {0, 0}, {1, 0}, {1, 1}};
        double[][] possibleTargetOut = {{1}, {1}, {1}, {0}, {0}, {0}, {1}, {0}};
        int i = new Random().nextInt(possibleInput.length);
        return new TrainingSet(possibleInput[i], possibleTargetOut[i]);
    }*/

    public static ArrayList<Student> parseData() {
        ArrayList<Student> students = new ArrayList<>();
        try {
            File data = new File("StudentsPerformance.csv");
            Scanner scanner = new Scanner(data);
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                String[] input = new String[5];
                double[] output = new double[3];
                for (int i = 0; i < values.length; i++) {
                    String value = values[i].replaceAll("\"", ""); // remove all quotation marks
                    if (i < 5) {
                        input[i] = value;
                    } else {
                        output[i-5] = Double.parseDouble(value) / 100;
                    }
                }
                students.add(new Student(Student.parseValues(input), output));
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }
}

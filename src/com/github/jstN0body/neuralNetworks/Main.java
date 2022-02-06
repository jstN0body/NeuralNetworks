package com.github.jstN0body.neuralNetworks;

import com.github.jstN0body.neuralNetworks.training.ScalaStudent;
import com.github.jstN0body.neuralNetworks.training.Student;
import com.github.jstN0body.neuralNetworks.training.TrainingSet;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Student> students = parseData();
        NeuralNetwork network = new NeuralNetwork(students.get(0), 0.25, 5, 5, 3, 3, 3);

        for (int i = 0; i < 5000000; i++) {
            network.train(1);
            int x = new Random().nextInt(students.size());
            network.setTrainingSet(students.get(x));
        }

        network.predict(new double[] {0, 4, 2, 1, 0});
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
                    if (i < 5) {
                        input[i] = values[i].replaceAll("\"", "");
                    } else {
                        output[i-5] = Double.parseDouble(values[i].replaceAll("\"", "")) / 100;
                    }
                }
                students.add(new Student(ScalaStudent.parseValues(input), output));
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }
}

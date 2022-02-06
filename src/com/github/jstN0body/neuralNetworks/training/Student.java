package com.github.jstN0body.neuralNetworks.training;

public class Student extends TrainingSet {

    public Student(double[] in, double[] out) {
        super(in, out);
    }

    public static double[] parseValues(String[] values) {
        double[] parsedValues = new double[5];
        parsedValues[0] = values[0].equalsIgnoreCase("male") ? 0 : 1;
        parsedValues[3] = values[3].equalsIgnoreCase("standard") ? 1 : 0;
        parsedValues[4] = values[4].equalsIgnoreCase("none") ? 0 : 1;

        switch (values[1]) {
            case "group A":
                parsedValues[1] = 0;
                break;
            case "group B":
                parsedValues[1] = 1;
                break;
            case "group C":
                parsedValues[1] = 2;
                break;
            case "group D":
                parsedValues[1] = 3;
                break;
            case "group E":
                parsedValues[1] = 4;
        }

        switch (values[2]) {
            case "some high school":
                parsedValues[2] = 0;
                break;
            case "high school":
                parsedValues[2] = 1;
                break;
            case "some college":
                parsedValues[2] = 2;
                break;
            case "associate's degree":
                parsedValues[2] = 3;
                break;
            case "bachelor's degree":
                parsedValues[2] = 4;
        }
        return parsedValues;
    }
}

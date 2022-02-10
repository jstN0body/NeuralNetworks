package com.github.jstN0body.neuralNetworks

import java.io.File
import java.io.BufferedWriter
import java.io.FileWriter
import java.util
import java.util.Scanner

object NetworkUtil {
  def saveWeights(layer: Layer, file: File): Unit = {
    val writer = new BufferedWriter(new FileWriter(file))
    val rows = layer.weights.rows
    val columns = layer.weights.cols
    writer.write(s"$rows $columns \n")
    
    val weights: util.List[java.lang.Double] = layer.weights.toArray
    for (w <- weights.toArray) {
      writer.write(s"$w ")
    }
    writer.close()
  }

  def saveBiases(layer: Layer, file: File): Unit = {
    val writer = new BufferedWriter(new FileWriter(file))
    writer.write(s"${layer.biases.rows} \n")

    val biases: util.List[java.lang.Double] = layer.biases.toArray
    for (b <- biases.toArray) {
      writer.write(s"$b ")
    }
    writer.close()
  }

  def loadWeights(file: File): Matrix = {
    val scanner = new Scanner(file)
    val rows = scanner.nextInt
    val cols = scanner.nextInt

    val values = new util.ArrayList[java.lang.Double]
    while (scanner.hasNextDouble) {
      values.add(scanner.nextDouble)
    }

    val weights = new Matrix(rows, cols)
    weights.addFromList(values)

    scanner.close()
    weights
  }

  def loadBiases(file: File): Matrix = {
    val scanner = new Scanner(file)
    val length = scanner.nextInt

    val values = new util.ArrayList[java.lang.Double]
    while (scanner.hasNextDouble) {
      values.add(scanner.nextDouble)
    }

    val biases = new Matrix(length, 1)
    biases.addFromList(values)

    scanner.close()
    biases
  }

  def generateSaveFiles(totalLayers: Int): (util.List[File], util.List[File]) = {
    val weightsList = new util.ArrayList[File]
    val biasesList = new util.ArrayList[File]
    for (i <- Range(0, totalLayers)) {
      try {
        val weights = new File(s"src/saveFiles/weights_$i.txt")
        weightsList.add(weights)

        val biases = new File(s"src/saveFiles/biases_$i.txt")
        biasesList.add(biases)
        if (weights.createNewFile) println(s"Created: $weights")
        if (biases.createNewFile) println(s"Created: $biases")
      }
    }

    (weightsList, biasesList)
  }
}

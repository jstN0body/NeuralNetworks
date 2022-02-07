package com.github.jstN0body.neuralNetworks

import com.github.jstN0body.neuralNetworks.Layer
import java.io.File
import java.io.BufferedWriter
import java.io.FileWriter
import java.util.Scanner

object NetworkUtil {
  def saveWeights(layer: Layer, file: File): Unit = {
    val writer = new BufferedWriter(new FileWriter(file))
    val rows = layer.weights.rows
    val columns = layer.weights.cols
    val index = layer.getIndex
    writer.write(s"$index $rows $columns \n")
    
    val weights: List[Double] = layer.weights.toArray
    for (w <- weights) {
      writer.write(s"$w ")
    }
    writer.close
  }

  def saveBiases(layer: Layer, file: File): Unit = {
    val writer = new BufferedWriter(new FileWriter(file))
    val length = layer.biases.getRows
    val index = layer.getIndex
    writer.write(s"$index $length \n")

    val biases: List[Double] = layer.biases.toArray
    for (b <- biases) {
      writer.write(s"$b")
    }
    writer.close
  }

  def loadWeights(file: File): Matrix = {
    val scanner = new Scanner(file)
    val rows = scanner.nextInt
    val cols = scanner.nextInt

    val values = ArrayBuffer[Double]()
    while (scanner.hasNextDouble) {
      values.append(scanner.nextDouble)
    }

    val weights = new Matrix(rows, cols)
    weights.addFromList(values.toList)
    
    scanner.close
    weights
  }

  def loadBiases(file: File): Matrix = {
    val scanner = new Scanner(file)
    val length = scanner.nextInt

    val values = ArrayBuffer[Double]()
    while (scanner.hasNextDouble) {
      values.append(scanner.nextDouble)
    }

    val biases = new Matrix(length, 1)
    biases.addFromList(values.toList)

    scanner.close
    biases
  }

  def generateSaveFiles(totalLayers: Int): Unit = {
    for (i <- 0 to totalLayers) {
      try {
        val weights = new File(s"weightsBiasesFiles/weights_$i.txt")
        val biases = new File(s"weightsBiasesFiles/biases_$i.txt")
        if (weights.createNewFile) println(s"Created: $weights")
        if (biases.createNewFile) println(s"Created: $biases")
      } catch (e: Exception) {
        // ignore exception
      }
    }
  }
}

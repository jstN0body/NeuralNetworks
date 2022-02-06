package com.github.jstN0body.neuralNetworks.training

import scala.collection.mutable.ArrayBuffer

class ScalaStudent(private val in: Array[Double], private val out: Array[Double]) extends ScalaTrainingSet(in, out) {}

object ScalaStudent {
  def parseValues(values: Array[String]): Array[Double] = {
    val parsedData: ArrayBuffer[Double] = ArrayBuffer.empty

    parsedData.addOne(if (values(0).equalsIgnoreCase("male")) 0 else 1)

    parsedData.addOne(values(1) match {
      case "group A" => 0
      case "group B" => 1
      case "group C" => 2
      case "group D" => 3
      case "group E" => 4
    })

    parsedData.addOne(values(2) match {
      case "some high school" => 0
      case "high school" => 1
      case "some college" => 2
      case "associate's degree" => 3
      case "bachelor's degree" => 4
    })

    parsedData.addOne(if (values(3).equalsIgnoreCase("standard")) 1 else 0)
    parsedData.addOne(if (values(4).equalsIgnoreCase("none")) 0 else 1)
    parsedData.toArray
  }
}
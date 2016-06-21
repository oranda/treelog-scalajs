package treelog

import treelog.LogTreeSyntaxWithoutAnnotations._
import scalaz._
import Scalaz._

object QuadraticRootsExample extends App {

  private def showDescription(label: LogTreeLabel[Nothing]) = label.fold(_.description, _ ⇒ "No Description")

  case class Parameters(a: Double, b: Double, c: Double)

  // Roots are real
  println("Success case:")

  // Roots are complex
  println("Failure case:")

  def root(parameters: Parameters) = {
    "Extracting root" ~< {
      for {
        num ← numerator(parameters) ~> "Calculating Numerator"
        den ← denominator(parameters) ~> "Calculating Denominator"
        root ← (num / den) ~> ("Got root = numerator / denominator: " + _)
      } yield root
    }
  }

  private def numerator(parameters: Parameters) =
    for {
      det ← determinant(parameters)
      sqrtDet ← sqrtDeterminant(det)
      b ← parameters.b ~> ("Got b: " + _)
      minusB ← -b ~> ("Got -b: " + _)
      sum ← (minusB + sqrtDet) ~> ("Got -b + sqrt(determinant): " + _)
    } yield sum

  private def sqrtDeterminant(det: Double) =
    "Calculating sqrt(determinant)" ~< {
      for {
        _ ← if (det >= 0) det ~> (d ⇒ s"Determinant ($d) is >= 0") else det ~>! (d ⇒ s"Determinant ($d) is < 0")
        sqrtDet ← Math.sqrt(det) ~> ("Got sqrt(determinant): " + _)
      } yield sqrtDet
    }

  private def denominator(parameters: Parameters) =
    for {
      a ← parameters.a ~> ("Got a: " + _)
      twoA ← (2 * a) ~> ("Got 2a: " + _)
    } yield twoA

  private def determinant(parameters: Parameters) =
    "Calculating Determinant" ~< {
      for {
        bSquared ← bSquared(parameters)
        fourac ← fourac(parameters)
        determinant ← (bSquared - fourac) ~> ("Got b^2 - 4ac: " + _)
      } yield determinant
    }

  private def bSquared(parameters: Parameters) =
    "Calculating b^2" ~< {
      for {
        b ← parameters.b ~> ("Got b: " + _)
        bSquared ← (b * b) ~> ("Got b^2: " + _)
      } yield bSquared
    }

  private def fourac(parameters: Parameters) =
    "Calculating 4ac" ~< {
      for {
        a ← parameters.a ~> ("Got a: " + _)
        c ← parameters.c ~> ("Got c: " + _)
        fourac ← (4 * a * c) ~> ("Got 4ac: " + _)
      } yield fourac
    }
}
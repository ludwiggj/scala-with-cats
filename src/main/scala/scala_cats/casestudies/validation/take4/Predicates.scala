package scala_cats.casestudies.validation.take4

import Errors.Errors

object Predicates {

  def longerThan[A](n: Int, extractInput: A => String, fieldName: Option[String] = None): Predicate[Errors, A] =
    Predicate.lift(
      Errors.mustBeLongerThan(n, fieldName),
      input => extractInput(input).length > n
    )

  def longerThan(n: Int): Predicate[Errors, String] =
    longerThan[String](n, identity, Option.empty[String])

  def alphaNumeric: Predicate[Errors, String] =
    Predicate.lift(
      Errors.mustBeAlphaNumeric,
      str => str.forall(_.isLetterOrDigit)
    )

  def contains[A](char: Char, extractInput: A => String, fieldName: Option[String] = None): Predicate[Errors, A] =
    Predicate.lift(
      Errors.mustContainCharacter(char, fieldName),
      input => extractInput(input).contains(char)
    )

  def contains(char: Char): Predicate[Errors, String] =
    contains[String](char, identity, Option.empty[String])

  def containsOnce(char: Char): Predicate[Errors, String] =
    Predicate.lift(
      Errors.mustContainCharacterOnce(char),
      str => str.count(_ == char) == 1
    )
}
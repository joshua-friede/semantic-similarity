/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uiowa.cs.similarity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author Josh
 */
public class WordVector {

    private final String name;
    private static HashSet<String> uniqueWords = new HashSet<>();
    private HashMap<String, Double> vector;

    public WordVector(String name, HashMap<String, Double> vector) {
        this.name = name;
        uniqueWords.addAll(vector.keySet());
        this.vector = vector;
    }

    public WordVector(String name) {
        this.name = name;
        this.vector = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return (name + ": " + vector.toString());
    }

    public void put(String word, Double value) {
        uniqueWords.add(word);
        vector.put(word, value);
    }

    public Double get(String word) {
        return vector.get(word);
    }

    public boolean containsWord(String w) {
        return vector.containsKey(w);
    }

    public double magnitude() {
        Iterator<Double> i = vector.values().iterator();
        Double m = 0.0;

        while (i.hasNext()) {
            m += Math.pow(i.next(), 2);
        }
        return Math.sqrt(m);
    }

    public WordVector minus(WordVector other) {
        Iterator<String> i = uniqueWords.iterator();
        WordVector newWordVector = new WordVector(name + " minus " + other.name);

        while (i.hasNext()) {

            String w = i.next();

            if (vector.containsKey(w) && other.containsWord(w)) {
                newWordVector.put(w, vector.get(w) - other.get(w));
            } else if (this.containsWord(w)) {
                newWordVector.put(w, this.get(w));
            } else if (other.containsWord(w)) {
                newWordVector.put(w, -other.get(w));
            }
        }
        return newWordVector;
    }

    public WordVector plus(WordVector other) {
        Iterator<String> i = uniqueWords.iterator();
        WordVector result = new WordVector(name);

        while (i.hasNext()) {

            String w = i.next();

            if (vector.containsKey(w) && other.containsWord(w)) {
                result.put(w, vector.get(w) + other.get(w));
            } else if (this.containsWord(w)) {
                result.put(w, this.get(w));
            } else if (other.containsWord(w)) {
                result.put(w, other.get(w));
            }
        }
        return result;
    }

    public WordVector divide(Double d) {
        WordVector result = new WordVector(name, vector);
        Iterator<String> i = result.vector.keySet().iterator();

        if (d == 0.0) {
            while (i.hasNext()) {
                result.vector.computeIfPresent(i.next(), (k, v) -> (0.0));
            }
        } else {
            while (i.hasNext()) {
                result.vector.computeIfPresent(i.next(), (k, v) -> (v / d));
            }
        }
        return result;
    }

    public WordVector normalized() {
        WordVector result = new WordVector(name, vector);
        return result.divide(result.magnitude());
    }

    public double cosineCompareTo(WordVector other) {

        Iterator<String> i = uniqueWords.iterator();

        Double dotProduct = 0.0;
        Double thisMagnitude = this.magnitude();
        Double otherMagnitude = other.magnitude();

        while (i.hasNext()) {
            String w = i.next();
            if (this.containsWord(w) && other.containsWord(w)) {
                dotProduct += (this.get(w) * other.get(w));
            }
        }

        //prevent divison by zero
        if (thisMagnitude * otherMagnitude == 0.0) {
            return 0.0;
        }

        return (dotProduct / (thisMagnitude * otherMagnitude));
    }

    public double euclidianCompareTo(WordVector other) {
        WordVector subtracted = this.minus(other);
        return -subtracted.magnitude();
    }

    public double normalizedEuclidianCompareTo(WordVector other) {
        WordVector subtracted = this.normalized().minus(other.normalized());
        return -subtracted.magnitude();
    }

}

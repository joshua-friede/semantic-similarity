/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uiowa.cs.similarity;

import java.util.*;

/**
 *
 * @author Cameron
 */
public class WordCluster {

    private final String name;
    private final WordVector center;
    private HashMap<String, WordVector> wordVectors;

    public WordCluster(String name, WordVector center) {
        this.name = name;
        this.center = center;
        wordVectors = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public WordVector getCenter() {
        return center;
    }

    @Override
    public String toString() {
        return ("Cluster: " + name + "\n" + wordVectors.keySet() + "\n\n");
    }

    public boolean contains(String word) {
        return wordVectors.containsKey(word);
    }

    public void put(String name, WordVector v) {
        wordVectors.put(name, v);
    }

    public WordVector remove(String name) {
        return wordVectors.remove(name);
    }

    public WordVector getCentroid() {
        WordVector result = new WordVector("centroid");
        for (WordVector v : wordVectors.values()) {
            result = result.plus(v);
        }
        result = result.divide((double) wordVectors.size());

        return result;

    }

}

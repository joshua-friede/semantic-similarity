/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uiowa.cs.similarity;

/**
 *
 * @author Josh
 */
public class SimilarityPair implements Comparable<SimilarityPair> {

    String word;
    Double value;

    public SimilarityPair(String word, Double value) {
        this.word = word;
        this.value = value;
    }

    @Override
    public int compareTo(SimilarityPair other) {
        return this.value.compareTo(other.value);
    }
}

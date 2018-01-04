/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uiowa.cs.similarity;

import java.io.FileInputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 * @author Josh
 */
public class Book {

    private final List<List<String>> book;
    private final HashSet<String> uniqueWordStems;
    private final HashMap<String, WordVector> wordVectors;
    private final HashSet<String> stopWords;

    public Book(FileInputStream bookFile, FileInputStream stopWordsFile) {
        stopWords = TextTools.wordListFileToSet(stopWordsFile);
        book = TextTools.bookFileToListOfStemmedWordLists(stopWords, bookFile);
        uniqueWordStems = TextTools.uniqueWordsInBook(book);

        wordVectors = new HashMap<>();
        Iterator<String> i = uniqueWordStems.iterator();
        while (i.hasNext()) {
            String currentWord = i.next();
            WordVector v = TextTools.generateSemanticDescriptorVector(currentWord, book);
            wordVectors.put(currentWord, v);
        }
    }

    public List<List<String>> getBook() {
        return book;
    }

    public HashSet<String> getUniqueWordStems() {
        return uniqueWordStems;
    }

    public HashSet<String> getStopWords() {
        return stopWords;
    }

    public HashMap<String, WordVector> getAllWordVectors() {
        return wordVectors;
    }

    public WordVector getWordVector(String word) {
        if (wordVectors.containsKey(word)) {
            return wordVectors.get(word);
        } else {
            throw new java.lang.IllegalStateException("Word not in book.");
        }
    }
    
    public WordVector getRandomWordVector() {
        return TextTools.random(wordVectors.values());
    }

    public PriorityQueue<SimilarityPair> similarWordsTo(String q, String method) {
        WordVector qv = getWordVector(q);

        PriorityQueue<SimilarityPair> topJ = new PriorityQueue<>(Collections.reverseOrder());

        Iterator<WordVector> i = wordVectors.values().iterator();

        while (i.hasNext()) {
            WordVector v = i.next();

            if (v != qv) {

                switch (method) {
                    case "euc":
                        topJ.add(new SimilarityPair(v.getName(), qv.euclidianCompareTo(v)));
                        break;
                    case "eucnorm":
                        topJ.add(new SimilarityPair(v.getName(), qv.normalizedEuclidianCompareTo(v)));
                        break;
                    case "cosine":
                        topJ.add(new SimilarityPair(v.getName(), qv.cosineCompareTo(v)));
                        break;
                    default:
                        throw new java.lang.IllegalArgumentException("Inalid comparison method specified.");
                }
            }
        }

        return topJ;

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uiowa.cs.similarity;

import java.io.FileInputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import opennlp.tools.stemmer.PorterStemmer;

/**
 *
 * @author Josh
 */
public class TextTools {

    public static String stem(HashSet<String> stopWords, String word) {

        if (stopWords.contains(word)) {
            return "";
        }
        PorterStemmer stemmer = new PorterStemmer();
        return stemmer.stem(word);

    }

    public static List<String> stem(HashSet<String> stopWords, List<String> sentence) {
        List<String> stemmedSentence = new LinkedList<>();
        for (String w : sentence) {
            String stemmedWord = stem(stopWords, w);
            if (!stemmedWord.isEmpty()) {
                stemmedSentence.add(stemmedWord);
            }

        }

        return stemmedSentence;
    }

    public static List<String> sentenceToWordList(String line) {

        String[] wordArray = line.split(" ");

        List<String> wordList = new LinkedList<>();

        for (String w : wordArray) {

            wordList.add(w.trim());

        }

        return wordList;

    }

    public static HashSet<String> uniqueWordsInSentence(List<String> sentence) {
        HashSet<String> uniqueWords = new HashSet<>();
        for (String w : sentence) {
            uniqueWords.add(w);
        }
        return uniqueWords;
    }

    public static HashSet<String> uniqueWordsInBook(List<List<String>> book) {
        HashSet<String> uniqueWords = new HashSet<>();
        for (List<String> sentence : book) {
            for (String word : sentence) {
                uniqueWords.add(word);
            }
        }
        return uniqueWords;
    }

    public static List<String> bookFileToSentences(FileInputStream rawTextFile) {
        List<String> listOfSentences = new LinkedList();
        Scanner lines = new Scanner(rawTextFile);
        lines.useDelimiter("[.?!]");

        while (lines.hasNext()) {
            String line = lines.next();
            line = line.replace("\n", " ");
            line = line.replaceAll("[-]", " ");
            line = line.replaceAll("[^a-zA-Z\' ]", " ");
            line = line.toLowerCase().trim();
            listOfSentences.add(line);
        }

        return listOfSentences;
    }

    public static List<List<String>> bookFileToListOfWordLists(FileInputStream rawTextFile) {
        List<List<String>> listOfWordLists = new LinkedList<List<String>>();
        List<String> sentences = bookFileToSentences(rawTextFile);
        for (String s : sentences) {

            List<String> wordList = sentenceToWordList(s);

            if (!wordList.isEmpty()) {
                listOfWordLists.add(wordList);
            }
        }
        return listOfWordLists;
    }

    public static List<List<String>> bookFileToListOfStemmedWordLists(HashSet<String> stopWords, FileInputStream rawTextFile) {
        List<List<String>> listOfStemmedWordLists = new LinkedList<List<String>>();
        List<String> sentences = bookFileToSentences(rawTextFile);
        for (String s : sentences) {

            List<String> stemmedWordList = stem(stopWords, sentenceToWordList(s));

            if (!stemmedWordList.isEmpty()) {
                listOfStemmedWordLists.add(stemmedWordList);
            }
        }
        return listOfStemmedWordLists;
    }

    public static HashSet<String> wordListFileToSet(FileInputStream rawTextFile) {
        HashSet<String> wordSet = new HashSet<>();
        Scanner sc = new Scanner(rawTextFile);
        while (sc.hasNext()) {
            wordSet.add(sc.next());
        }
        return wordSet;
    }

    public static WordVector generateSemanticDescriptorVector(String word, List<List<String>> book) {
        WordVector v = new WordVector(word);
        for (List<String> s : book) {
            if (s.contains(word)) {
                for (String w : s) {

                    if (v.containsWord(w)) {
                        Double oldValue = v.get(w);
                        v.put(w, (oldValue + 1));
                    } else if (!word.equals(w)) {
                        v.put(w, 1.0);
                    }
                }
            }
        }

        return v;
    }

    public static <T> T random(Collection<T> coll) {
        int num = (int) (Math.random() * coll.size());
        for (T t : coll) {
            if (--num < 0) {
                return t;
            }
        }
        throw new AssertionError();
    }

}

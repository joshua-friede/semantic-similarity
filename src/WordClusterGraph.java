/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uiowa.cs.similarity;

import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Josh
 */
public class WordClusterGraph {

    WordCluster[] clusters;
    private final HashMap<String, WordVector> wordVectors;
    private int iters;

    public WordClusterGraph(Book book, Integer k) {
        this.wordVectors = book.getAllWordVectors();
        clusters = new WordCluster[k];
        iters = 0;

        // make k new clusters
        for (Integer i = 0; i < k; i++) {
            WordVector rand = book.getRandomWordVector();
            //System.out.println(rand);
            clusters[i] = new WordCluster(i.toString(), rand);
        }
        populateClusters();

    }

    public void Iterate() {
        iters++;
        for (Integer i = 0; i < clusters.length; i++) {
            WordVector v = clusters[i].getCentroid();
            clusters[i] = new WordCluster(i.toString(), v);
        }
        populateClusters();
        
        /*for (Integer i = 0; i < clusters.length; i++) {
            System.out.println(iters + "," + i + clusters[i].getCenter());
        }*/
    }

    @Override
    public String toString() {
        String result = "";
        for (WordCluster c : clusters) {

            result = result + c.toString();
        }
        return result;
    }

    private void populateClusters() {

        Iterator<WordVector> i = wordVectors.values().iterator();
        while (i.hasNext()) {

            WordVector currentWord = i.next();

            Double maxSimilarity = null;
            int closestClusterIndex = 0;

            for (Integer j = 0; j < clusters.length; j++) {
                Double currentSimilarity = currentWord.euclidianCompareTo(clusters[j].getCenter());

                if (maxSimilarity == null || currentSimilarity >= maxSimilarity) {
                    maxSimilarity = currentSimilarity;
                    closestClusterIndex = j;
                }
            }

            clusters[closestClusterIndex].put(currentWord.getName(), currentWord);
        }
    }

}

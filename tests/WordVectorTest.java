/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uiowa.cs.similarity;

import junit.framework.TestCase;

/**
 *
 * @author Josh
 */
public class WordVectorTest extends TestCase {
    
    public WordVectorTest(String testName) {
        super(testName);
    }

    /**
     * Test of getName method, of class WordVector.
     */
    public void testGetName() {
    }

    /**
     * Test of toString method, of class WordVector.
     */
    public void testToString() {
    }

    /**
     * Test of put method, of class WordVector.
     */
    public void testPut() {
    }

    /**
     * Test of get method, of class WordVector.
     */
    public void testGet() {
    }

    /**
     * Test of containsWord method, of class WordVector.
     */
    public void testContainsWord() {
    }

    /**
     * Test of magnitude method, of class WordVector.
     */
    public void testMagnitude() {
    }

    /**
     * Test of minus method, of class WordVector.
     */
    public void testMinus() {
        System.out.println("minus");
        WordVector v1 = new WordVector("v1");
        v1.put("cat", 1.0);
        v1.put("dog", 2.0);
        v1.put("fish", 1.0);
        WordVector v2 = new WordVector("v1");
        v2.put("cat", 5.0);
        v2.put("fish", 1.0);
        System.out.println(v1.minus(v2));
    }

    /**
     * Test of plus method, of class WordVector.
     */
    public void testPlus() {
        WordVector v1 = new WordVector("v1");
        v1.put("cat", 1.0);
        v1.put("dog", 2.0);
        v1.put("fish", 1.0);
        WordVector v2 = new WordVector("v1");
        v2.put("cat", 5.0);
        v2.put("fish", 1.0);
        System.out.println(v1.plus(v2));
    }

    /**
     * Test of divide method, of class WordVector.
     */
    public void testDivide() {
    }

    /**
     * Test of normalized method, of class WordVector.
     */
    public void testNormalized() {
    }

    /**
     * Test of cosineCompareTo method, of class WordVector.
     */
    public void testCosineCompareTo() {
    }

    /**
     * Test of euclidianCompareTo method, of class WordVector.
     */
    public void testEuclidianCompareTo() {
        WordVector v1 = new WordVector("v1");
        v1.put("cat", 1.0);
        v1.put("dog", 2.0);
        v1.put("fish", 1.0);
        WordVector v2 = new WordVector("v1");
        v2.put("cat", 5.0);
        v2.put("fish", 1.0);
        assertEquals(-4.47, v1.euclidianCompareTo(v2));
    }

    /**
     * Test of normalizedEuclidianCompareTo method, of class WordVector.
     */
    public void testNormalizedEuclidianCompareTo() {
        
        WordVector v1 = new WordVector("v1");
        v1.put("cat", 1.0);
        v1.put("dog", 2.0);
        v1.put("fish", 1.0);
        WordVector v2 = new WordVector("v1");
        v2.put("cat", 5.0);
        v2.put("fish", 1.0);
        assertEquals(-4.47, v1.normalizedEuclidianCompareTo(v2));
    }
    
}

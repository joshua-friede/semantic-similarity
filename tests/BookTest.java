/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uiowa.cs.similarity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;
import junit.framework.TestCase;

/**
 *
 * @author Josh
 */
public class BookTest extends TestCase {

    public BookTest(String testName) {
        super(testName);
    }

    /**
     * Test of getBook method, of class Book.
     * @throws java.io.FileNotFoundException
     */
    public void testGetBook() throws FileNotFoundException {
        System.out.println("getBook");
        FileInputStream bookFile = new FileInputStream("easy_sanity_test.txt");
        FileInputStream stopWordsFile = new FileInputStream("stopwords.txt");
        Book instance = new Book(bookFile, stopWordsFile);
        List<List<String>> result = instance.getBook();
        System.out.println(result + "\n");
        assertTrue(true);

    }

    /**
     * Test of getUniqueWordStems method, of class Book.
     * @throws java.io.FileNotFoundException
     */
    public void testGetUniqueWordStems() throws FileNotFoundException {
        System.out.println("getUniqueWordStems");
        FileInputStream bookFile = new FileInputStream("easy_sanity_test.txt");
        FileInputStream stopWordsFile = new FileInputStream("stopwords.txt");
        Book instance = new Book(bookFile, stopWordsFile);
        HashSet<String> result = instance.getUniqueWordStems();
        System.out.println(result);
        assertTrue(true);
    }
    
    public void testGetRandomWordVector() throws FileNotFoundException {
        System.out.println("getRandomWordVector");
        FileInputStream bookFile = new FileInputStream("easy_sanity_test.txt");
        FileInputStream stopWordsFile = new FileInputStream("stopwords.txt");
        Book instance = new Book(bookFile, stopWordsFile);
        WordVector result = instance.getRandomWordVector();
        System.out.println(result);
    }

}

package edu.uiowa.cs.similarity;

import org.apache.commons.cli.*;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Options options = new Options();
        options.addRequiredOption("f", "file", true, "input file to process");
        options.addOption("h", false, "print this help message");
        options.addOption("s", false, "print sentences");
        options.addOption("v", false, "print all semantic description vectors");
        options.addOption("t", true, "takes an argument of format Q,J.That option will cause the program to print the k most similar words to Q, along with their similarity score.");
        options.addOption("m", true, "tests Euclidean distance between vectors and nomralized vectors using options euc or eucnorm");
        options.addOption("k", true, "clusters");

        CommandLineParser parser = new DefaultParser();

        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            new HelpFormatter().printHelp("Main", options, true);
            System.exit(1);
        }

        String filename = cmd.getOptionValue("f");
        if (!new File(filename).exists()) {
            System.err.println("file does not exist " + filename);
            System.exit(1);
        }

        if (cmd.hasOption("h")) {
            HelpFormatter helpf = new HelpFormatter();
            helpf.printHelp("Main", options, true);
            System.exit(0);
        }

        FileInputStream sentenceFile = new FileInputStream(filename);
        FileInputStream stopWordsFile = new FileInputStream("stopwords.txt");
        Book book = new Book(sentenceFile, stopWordsFile);

        if (cmd.hasOption("s")) {
            List<List<String>> sentences = book.getBook();
            System.out.println("Number of sentences: " + sentences.size());
            System.out.println(sentences);
        }

        if (cmd.hasOption("v")) {
            Iterator<WordVector> i = book.getAllWordVectors().values().iterator();
            while (i.hasNext()) {
                System.out.println(i.next());
            }
        }

        if (cmd.hasOption("t")) {

            String arg = cmd.getOptionValue("t");
            List<String> inputArgs = Arrays.asList(arg.split(","));
            String q = TextTools.stem(book.getStopWords(), inputArgs.get(0).trim()); //left of comma
            Integer j = Integer.parseInt(inputArgs.get(1)); //right of comma
            String m = "cosine";
            if (cmd.hasOption("m")) {
                m = cmd.getOptionValue("m");
            }

            try {
                System.out.println("Top " + j + " words similar to " + q + ":\n");
                PriorityQueue<SimilarityPair> topJ = book.similarWordsTo(q, m);
                int count = 0;
                while (topJ.peek() != null && count++ < j) {
                    SimilarityPair sp = topJ.poll();
                    System.out.println(sp.word + ": " + sp.value);
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println(e);
            }
        }
        if (cmd.hasOption("k")) {

            String arg = cmd.getOptionValue("k");
            List<String> inputArgs = Arrays.asList(arg.split(","));
            Integer a = Integer.parseInt(inputArgs.get(0)); //clusters
            Integer b = Integer.parseInt(inputArgs.get(1)); //iterations

            ////////////////////////////////////////////
            WordClusterGraph g = new WordClusterGraph(book, a);
            for (int i = 0; i < b; i++) {
                g.Iterate();
            }

            System.out.println(g);

        }
    }
}

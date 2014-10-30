package exercise3;

import common.html.GazetaHtmlDocument;
import common.html.HtmlDocument;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) throws Exception {
        HtmlDocument rootDocument = new GazetaHtmlDocument("http://wiadomosci.gazeta.pl/");
        Set<String> links = rootDocument.getLinks();
        String wordToFound = "Mariusz";
        
        ExecutorService executor = Executors.newCachedThreadPool();
        // TODO: Create ExecutorService

        List<Future<Integer>> results = new ArrayList<>();
        // TODO: Create list of results of type List<Future<Integer>>

        for (String link : links) {
        	System.out.println("link: " + link);
        	
        	WordCounter counter = new WordCounter(link, wordToFound);
        	Future<Integer> future = executor.submit(counter);
        	results.add(future);
            // TODO: Create new WordCounter and submit it to executorService
            // TODO: Store Future object in list of results
        }
        
        executor.shutdown();
        // TODO: shutdown executor

        int numberOfWords = 0;
        for(Future<Integer> future: results)
        	numberOfWords += future.get();
        // TODO: Iterate over list of results and for each Future invoke get() method
        // TODO: add value returned from get() method to numberOfWords variable

        System.out.printf("Number of words '%s': %d", wordToFound, numberOfWords);
    }
}

package readFile;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

@Component
public class ParallelFileProcessor implements FileProcessor, Runnable {

    //file reader
    Map<String, Integer> countOfWords = new HashMap<>();
    Queue<String> queue = new LinkedList<>();

    @Override
    public Map<String, Integer> countWordsInFile(String name) throws IOException {
        readWordsInFile(name);
        countOfWords.clear();
        countWordsFromQueue(countOfWords, queue);
        System.out.println(countOfWords);
        return countOfWords;
    }

    public  Queue<String> readWordsInFile(String name) throws IOException {
        String thisLine;

        // read file by other line
        FileReader reader = new FileReader(name);
        try (BufferedReader br = new BufferedReader(reader)) {

            while ((thisLine = br.readLine()) != null) {
                // split "String" line to words with regex
                String[] wordsInLine = thisLine.split("\\W+");
                for (String word : wordsInLine) {
                    int length = word.length();
                    // if length of word !=0 - add ...
                    if (length != 0) {
                        // create new word with upper letters and cut first letter of word for search
                        // folder
                        String newWord = word.toUpperCase();
                        String letter = newWord.substring(0, 1);
                        queue.offer(newWord);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Don't read file!!!");
            e.printStackTrace();
        } finally {
            System.out.println("End of read file!!!");
            System.out.println(countOfWords);
        }
        return queue;
    }

    private void countWordsFromQueue(Map<String, Integer> countOfWords, Queue<String> queue) {
        while (!queue.isEmpty()) {


            String newWord = queue.poll();
            String letter = newWord.substring(0, 1);

            boolean isLetterInText = false;

            for (String key : countOfWords.keySet()) {
                //System.out.println("Key: " + key);

                if (letter.equals(key)) {
                    int value = countOfWords.get(key);
                    countOfWords.put(key, value + 1);
                    isLetterInText = true;
                }
            }

            if (!isLetterInText) {
                countOfWords.put(letter, 1);
            }

        }
    }

    @Override
    public void run() {
        //New code!!!

    }
}

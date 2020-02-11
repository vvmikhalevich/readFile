package readFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class ReadFile {

    public static Map<String, Integer> readFile() throws IOException {
        String thisLine = null;
        Map<String, Integer> CountOfWords = new HashMap<String, Integer>();

        // read file by other line
        FileReader reader = new FileReader("c:/SampleText.txt");
        try (BufferedReader br = new BufferedReader(reader);) {

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

                        //New code!!!
                        boolean isLetterInText = false;

                        for (String key : CountOfWords.keySet()) {
                            System.out.println("Key: " + key);

                            if (letter.equals(key)) {
                                int value = CountOfWords.get(key);
                                CountOfWords.put(key, value + 1);
                                isLetterInText = true;
                            }
                        }

                        if (isLetterInText == false) {
                            CountOfWords.put(letter, 1);
                        }


                    }
                }

            }
        } catch (IOException e) {
            System.out.println("Don't read file!!!");
            e.printStackTrace();
        } finally {
            System.out.println("End of work java!!!");
            System.out.println(CountOfWords);
        }
        return CountOfWords;
    } // end


}

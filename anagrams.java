import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

/*
 * Author: Nishith Avalani
 * File: PA12Main
 * Course: CSc 210-001, Fall 2021, Professor Claveau
 * Purpose: This program prints out the anagrams of a provided word
 * by recursing through a dictionary of words and forming lists of anagrams
 * restricted by the integer provided in the command line argument. The integer
 * 0 indicates no limit on the number of anagrams while integer greater than or
 * equal to 1 indicates the number of words that can be used to form an anagram.
 */

public class PA12Main {
    public static void main(String[] args) throws FileNotFoundException
        { ArrayList<String> dict = new ArrayList<String>();
          Scanner sc = new Scanner(new File(args[0]));
          while(sc.hasNextLine()) {
            dict.add(sc.nextLine());
            }
        int num1 = Integer.valueOf(args[2]);
        System.out.println("Phrase to scramble: " + args[1] + "\n");
        choices(args[1], num1, dict);
        sc.close();
        }
       
       /*
        * This method generates a list of words that may be a part of the anagrams of the word.
        * The program breaks the word down into its consisting letters and iterates through the
        * dictionary to find a list of choices that can be anagrams. This method also calls
        * another method that recurses onto the list to find anagrams.
        * @Param word is the phrase for which anagrams have to be formed
        * @Param num1 is the number of words that can make an anagram
        * @Param dict is the dictionary from which the code searches for choices
        */
        public static void choices(String word, int num1, ArrayList<String>dict) {
            ArrayList<String> words = new ArrayList<>();
            LetterInventory letters = new LetterInventory(word);
            for(String phrase: dict) {
                if(letters.contains(phrase)) {
                    words.add(phrase);
                }
            }  
            String largest_word = "";
            for(String str: words) {
                if(str.length() > largest_word.length()) {
                    largest_word = str;
                }
            }
            System.out.println("All words found in " + word + ":");
            System.out.println(words + "\n");
            System.out.println("Anagrams for " + word + ":");
            find_anagrams(words, letters, new ArrayList<String>(), num1, largest_word);
        }
   
    /*
     * This method implements recursive backtracking to find a list of anagrams from a list of
     * choices for anagrams. If the list of anagrams forms the word and is less than or equal
     * to the limit, it is printed and removed  from choices and letters. If it doesn't form the
     * under the limit, the code backtracks and takes another route.
     * @Param words is the list of all possible choices that can form anagram
     * @Param letters is all the letters contained in the word
     * @Param chosen is the list of words that may make an anagram of the word
     * @Param limit is the number of words that can make an anagram
     */
        public static void find_anagrams(ArrayList<String> words, LetterInventory letters, ArrayList<String> chosen, int limit, String largest_word) {


            if(letters.isEmpty() && chosen.size() <= limit || letters.isEmpty() && limit == 0) {
                System.out.println(chosen);
                return;
            }
            /*
             * Pruning condition: if the largest word in choice is used in 
             * chosen and is larger than limit, you can go into the for loop. 
             */
            else if(limit == 0 || largest_word.length() * (limit - chosen.size()) > limit ) {
                for(int i = 0; i < words.size(); i++) {
                    String choice = words.get(i);
                    if(letters.contains(choice)) {
                        letters.subtract(choice);
                        chosen.add(choice);
                        find_anagrams(words, letters, chosen, limit, largest_word);
                        letters.add(choice);
                        chosen.remove(chosen.size() - 1);
                    }
                }
            }
        }
    }

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner fileInput = new Scanner(new File("src/words.txt"));
        Scanner userInput = new Scanner(System.in);
        List<Character> guesses = new ArrayList<>();
        List<String> availableWords = new ArrayList<>();
        String word, tempWord;
        int lives;
        Random random = new Random();

        //Fill availableWords list with all words from words.txt
        while (fileInput.hasNext()){
            availableWords.add(fileInput.nextLine());
        }

        //Select a word at random
        word = availableWords.get(random.nextInt(availableWords.size()));
        lives = word.length();
        while(guesses.size() < lives) {
            System.out.println(word);
            printCurrentWord(word, guesses);
            System.out.println("Please guess a letter!");

            tempWord = userInput.nextLine();

            if(tempWord.length() == 1 && !guesses.contains(tempWord) && word.contains(tempWord)) {
                guesses.add(tempWord.charAt(0));
                System.out.println("That letter was in the string!");
            } else if(tempWord.length() == 1 && !guesses.contains(tempWord)) {
                guesses.add(tempWord.charAt(0));
                System.out.println("That letter is not in the string!");
                System.out.println(lives + " Lives left!");
                lives--;
            } else if(tempWord.length() == 1) {
                System.out.println("You have already guessed " + tempWord + "!");
            } else {
                System.out.println("You must enter a character!");
            }
        }


    }

    public static void printCurrentWord(String word, List<Character> guesses){
        for(int i = 0; i < word.length(); i++){
            if(guesses.contains(word.charAt(i))){
                System.out.print(word.charAt(i));
            } else {
                System.out.print("_");
            }
            System.out.print(" ");
        }
        System.out.println("");
    }
}

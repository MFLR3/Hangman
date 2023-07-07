import java.util.Scanner;
public class Hangman {
    public static String[] words = {"aardvark", "albatross", "alpaca", "ant", "baboon", "badger",
            "banana cinnamon ball python", "bandicoot", "barracuda", "bat", "bear", "beaver",
            "bat", "bear", "black german shepherd", "brown tree snake", "bulldog", "camel",
            "camel spider", "cat", "clam", "cobra", "cougar", "coyote", "crow", "cucumber beetle",
            "deer", "diamondback moth", "dog", "donkey", "duck", "dwarf hamster", "dragonfly",
            "eagle", "earthworm", "eel", "elephant", "emu", "ferret", "flounder", "fox", "frog", "goat",
            "golden retriever", "goose", "gorilla", "haddock", "hawk", "humpback whale",
            "labrador retriever", "labradoodle", "lion", "lizard", "llama", "macaw", "mole", "monkey",
            "moose", "mouse", "mule", "newt", "otter", "owl", "panda", "parrot", "pigeon",
            "python", "rabbit", "ram", "rat", "raven","rhino", "salmon", "seal",
            "shark", "sheep", "skunk", "sloth", "snake", "spider", "stork", "swan", "syrian hamster",
            "tiger", "toad", "trout", "turkey", "turtle", "weasel", "whale", "wolf",
            "wombat", "zebra"};
    public static String[] gallows = {"+---+\n" +
            "|   |\n" +
            "    |\n" +
            "    |\n" +
            "    |\n" +
            "    |\n" +
            "=========\n",

            "+---+\n" +
                    "|   |\n" +
                    "O   |\n" +
                    "    |\n" +
                    "    |\n" +
                    "    |\n" +
                    "=========\n",

            "+---+\n" +
                    "|   |\n" +
                    "O   |\n" +
                    "|   |\n" +
                    "    |\n" +
                    "    |\n" +
                    "=========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|   |\n" +
                    "     |\n" +
                    "     |\n" +
                    " =========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|\\  |\n" + //if you were wondering, the only way to print '\' is with a trailing escape character, which also happens to be '\'
                    "     |\n" +
                    "     |\n" +
                    " =========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|\\  |\n" +
                    "/    |\n" +
                    "     |\n" +
                    " =========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|\\  |\n" +
                    "/ \\  |\n" +
                    "     |\n" +
                    " =========\n"};

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        // Select difficulty
        System.out.println("Pick your difficulty: ");
        System.out.println("For Easy - e");
        System.out.println("For medium - m");
        System.out.println("For Easy - h");

        char difficulty = scan.next().charAt(0);

        int errors = 0;
        int correct = 0;
        StringBuilder misses = new StringBuilder(6);
        // Randomly select secret word
        double randomNumber = Math.random() * words.length + 1;
        String secretWord = words[(int) randomNumber];

        switch(difficulty){
            case 'e':
                while(secretWord.length() > 5){
                    randomNumber = Math.random() * words.length + 1;
                    secretWord = words[(int) randomNumber];
                }; break;
            case 'm':
                while(secretWord.length() <= 5 || secretWord.length() > 10){
                    randomNumber = Math.random() * words.length + 1;
                    secretWord = words[(int) randomNumber];
                }; break;
            case 'h':
                while(secretWord.length() < 11){
                    randomNumber = Math.random() * words.length + 1;
                    secretWord = words[(int) randomNumber];
                }; break;
            default:
                System.out.println("Sorry, this doesn't exist.");
        }
        // Duplicate secret word - set all characters to '_'
        StringBuilder guessingWord = new StringBuilder(secretWord);
        for (int i = 0; i < secretWord.length(); i++) {
            guessingWord.setCharAt(i, '_');
            // Alter whitespace, add to correct
            if(secretWord.charAt(i) == ' '){
                guessingWord.setCharAt(i, '-');
                correct++;
            }
        }
        // While true - Only break when player wins or loses
        while(true){
            // Exit game with win text if number of correct guesses equals secret word length
            if(correct == secretWord.length()){
                System.out.println("You win");
                System.out.println("Secret word is: " + secretWord);
                System.exit(0);
            }
            // Ask user for next guess
            System.out.println("Guess: " );
            char letter = scan.next().charAt(0);
            // If user guess is present in secret && hasn't been guessed already, correct++ and count++
            int count = 0;
            for (int i = 0; i < guessingWord.length(); i++) {
                if(letter == secretWord.charAt(i) && guessingWord.charAt(i) == '_'){
                    guessingWord.setCharAt(i, letter);
                    count++;
                    correct++;
                }
            }
            // 0 correct matches is an error
            if(count == 0) {
                misses.append(letter);
                errors++;
            }
            // Each error produces a more complete hangman. 6 errors, game lost.
            switch(errors){
                case 0, 1, 2, 3, 4, 5:
                    System.out.println(gallows[errors]);
                    System.out.println("Word: " + guessingWord);
                    System.out.println("Misses: " + misses);
                    System.out.println("Correct: " + correct);
                    System.out.println("secret length: " + secretWord.length());
                    System.out.println("guessing length: " + guessingWord.length());
                    continue;
                case 6:
                    System.out.println(gallows[errors]);
                    System.out.println("Sorry, you lose");
                    System.out.println("The secret word was: " + secretWord);
                    System.exit(0);
                default:
                    System.out.println("Default"); continue;
            }
        }
    }
}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Gamestate {
    private String word;
    private char[] board;
    private int tries;
    private List<Character> guessedLetters;

    public Gamestate(String word, int tries) {
        this.word = word;
        this.board = new char[word.length()];
        Arrays.fill(board, '_');
        this.guessedLetters = new ArrayList<>();
        this.tries = tries;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public char[] getBoard() {
        return board;
    }

    public void setBoard(char[] board) {
        this.board = board;
    }

    public int getTries() {
        return tries;
    }

    public void setTries(int tries) {
        this.tries = tries;
    }

    public void reduceTries(){
        this.tries--;
    }

    public List<Character> getGuessedLetters() {
        return guessedLetters;
    }

    public void setGuessedLetters(List<Character> guessedLetters) {
        this.guessedLetters = guessedLetters;
    }

    public boolean validGuess(char buchstabe) {
        return !guessedLetters.contains(buchstabe);
    }

    public boolean isIn(char buchstabe){
        return word.toLowerCase().contains(String.valueOf(buchstabe));
    }

    public void addToGuessedLetters(char buchstabe) {
        guessedLetters.add(buchstabe);
    }

    public int[] getPositions(char buchstabe) {
        int[] positions = new int[0];
        for (int i = 0; i < word.length(); i++) {
            if (word.toLowerCase().charAt(i) == buchstabe) {
                positions = Arrays.copyOf(positions, positions.length + 1);
                positions[positions.length - 1] = i;
            }
        }
        return positions;
    }

    public void revealLetters(int[] positions) {
        for (int position : positions) {
            board[position] = word.charAt(position);
        }
    }

    public boolean isWon() {
        return Arrays.equals(board, word.toCharArray());
    }

    public boolean isLost() {
        return tries <= 0;
    }


}

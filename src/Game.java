import java.util.Scanner;

public class Game {
    private int maxtries = 10;
    Gamestate gs;
    public Game() {
    }

    public Game(int tries) {
        this.maxtries = tries;
    }

    public void start() {
        this.gs = new Gamestate(RandomWord.get(), maxtries);
        if (gs.getWord().equals("Test")) System.out.println("Keine Wordlist gefunden. Debugbetrieb.");
        System.out.println("Das Wort hat " + gs.getWord().length() + " Buchstaben.");
        while (!gs.isLost() && !gs.isWon()) {
            System.out.println("Du hast " + gs.getTries() + " Versuche, um das gesuchte Wort zu erraten.");
            System.out.println(gs.getBoard());
            char buchstabe = getBuchstabe();
            if (gs.validGuess(buchstabe)) {
                gs.addToGuessedLetters(buchstabe);
                if (gs.isIn(buchstabe)) {
                    rightGuess(buchstabe);
                } else {
                    System.out.println("Der gewählte Buchstabe ist leider nicht im Wort enthalten.");
                    gs.reduceTries();
                }
            } else {
                System.out.println("Du hast diesen Buchstaben bereits getippt.");
            }
            printGuesses();
        }
        gameEnd();
    }

    private void gameEnd() {
        if (gs.isWon()) {
            System.out.println("Du hast gewonnen!");
        } else {
            System.out.println("Game over.");
        }
        System.out.println("Das gesuchte Wort war " + gs.getWord());
        System.out.println("Möchtest du noch einmal starten?");
        System.out.println("[J] für ja, [N] für nein.");
        if (getBuchstabe() == 'j') {
            start();
        }
    }

    private char getBuchstabe() {
        Scanner s = new Scanner(System.in);
        return s.nextLine().toLowerCase().toCharArray()[0];
    }

    private void rightGuess(char buchstabe) {
        System.out.println("Du hast richtig geraten!");
        int[] positions = gs.getPositions(buchstabe);
        gs.revealLetters(positions);
    }

    private void printGuesses() {
        System.out.print("Deine bisher geratenen Buchstaben sind: ");
        for (char tipp : gs.getGuessedLetters()) {
            System.out.print(tipp + " ");
        }
        System.out.println();
    }
}

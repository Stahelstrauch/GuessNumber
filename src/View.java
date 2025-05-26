import java.util.List;
import java.util.Scanner;

/**
 * Kõik mida kasutaja näeb konsoolis
 */
public class View {
    private final Scanner scanner = new Scanner(System.in);


    /**
     * Mängu menüü näitamine
     */
    public void showMenu() { //Näita menüüd
        System.out.println("1. Mängima");
        System.out.println("2. Edetabel");
        System.out.println("3. Välju");
        System.out.print("Tee valik: "); //Et kursor jääks õige kohapeale, siis ei tee reavahetust
    }

    /**
     * Tagastab kasutaja sisestuse
     * @return kasutaja sisestus
     */
    public int getMenuChoice() { //Küsitakse valitut menüüst
        return Integer.parseInt(scanner.nextLine());
    }

    /**
     * Väljastab etteantud teate konsooli
     * @param message teade mida väljastada
     */
    public void showMessage(String message) { //Näitab teksti, et ei peaks kirjutama koguaeg sout-i
        System.out.println(message);
    }

    /**
     * Küsib kasutajalt numbrit
     * @return kasutaja sisestatud number
     */
    public int askGuess() {
        System.out.print("Sisesta number: ");
        return Integer.parseInt(scanner.nextLine()); //Kasutaja sisestus üritatakse teha täisarvuks, kui õnnestub siis väljastab
    }

    /**
     * Küsib kasutajalt nime mängu lõpus
     * @return sisestatud nimi
     */
    public String askName() {
        System.out.print("Sisesta oma nimi: ");
        return scanner.nextLine();
    }

    /**
     * Näita konsooli edetabelit
     * @param scores faili sisu listina
     */
    public void showScoreboard(List<Content> scores) {
        System.out.println("EDETABEL");
        System.out.println("-----------------------------------------");
        for (Content c : scores) {
            System.out.println(c.formattedData());
        }
        System.out.println(); // Tühi rida
    }
}

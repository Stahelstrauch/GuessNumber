import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Kogu mängu loogika asub siin.
 */
public class Model {
    private final int MINIMUM = 1; // constant on läbiv suurtäht
    private final int MAXIMUM = 100; // 1 ja 100 on kaasa arvatud
    private final String filename = "scoreboard.txt";
    private final List<Content> scoreboard = new ArrayList<>(); // Edetabeli faili sisu

    //Siin loome muutuja, väärtuse anname initGame-s
    private int pc_number; // arvuti võetud number
    private int steps; // Käikude lugeja
    private boolean game_over; // Kas mäng on läbi (yes or no)
    private boolean cheater; // Kas mängija on petis jah või ei



    /**
     * UUe mängu loomine
     */
    public void initGame() {
        pc_number = new Random().nextInt(MAXIMUM - MINIMUM + 1) + MINIMUM;
        game_over = false;
        steps = 0;
        cheater = false;
    }


    // GETTERS

    /**
     * Arvuti mõeldud number
     * @return arvuti juhuslik number vahemikus 1-100
     */
    public int getPc_number() {
        return pc_number;
    }



    /**
     * Kas mäng on läbi?
     * @return true on läbi, false ei ole mäng läbi
     */
    public boolean isGame_over() {
        return game_over;
    }

    /**
     * Kas mängija on pettur?
     * @return true on pettur, false ei ole
     */
    public boolean isCheater() {
        return cheater;
    }

    /**
     * Kontrollib kasutaja sisestust ja tagastab sobiva teksti
     * @param guess number mida kontrollida
     * @return tekst mida näidatakse kasutajale
     */
    public String checkGuess(int guess) {
        steps++; //  Käikude arv kasvab
        if(guess == pc_number) {
            game_over = true;
            return "Sa võitsid " + steps + " sammuga!";
        } else if (guess == 1000) {
            game_over = true;
            cheater = true;
            return "Leidsid mu nõrga koha. Õige number oli " + pc_number;
        } else if (guess < pc_number) {
            return "Liiga väike!";
        } else {
            return "Liiga suur!";
        }
    }

    /**
     * Salvestab listi sisu (edetabel) uuesti faili (kirjutab üle)
     * @param name mängija nimi
     */
    public void saveScore(String name, long time, LocalDateTime datetime) {
        loadScores(); //Enne laed andmed alla eelnevad
        scoreboard.add(new Content(name, steps, datetime, time)); // Lisa nimi ja sammude arv listi
        Collections.sort(scoreboard); // Sorteerib listi (Content compareTo() omaga)
        try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
            for(Content c : scoreboard) {
                out.println(c.getName() + ";" + c.getSteps() + ";" + c.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + ";" + c.getTime());//Semikoolin jutumärkides! //Kirjutab faili nimi;sammud
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Loeb edetabeli faili sisu ja lisab sisu listi
     * @return edetabeli list
     */
    public List<Content> loadScores() {
        scoreboard.clear(); // Tühjenda list
        File file = new File(filename); // loome failiobjekti
        if(!file.exists()) return scoreboard; // Kui faili ei ole tagastab listi

        try(Scanner sc = new Scanner(file)) {
            while(sc.hasNextLine()) { // Kui failis on järgmine rida
                String[] parts = sc.nextLine().split(";"); // Semikoolonist tükeldama
                if(parts.length == 4) {
                    String name = parts[0];
                    int steps = Integer.parseInt(parts[1]);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime datetime = LocalDateTime.parse(parts[2], formatter);
                    long time = Long.parseLong(parts[3]);
                    scoreboard.add(new Content(name, steps, datetime, time));
                }

            }
            Collections.sort(scoreboard); //Sorteerib listi (ilmselt siin üleliigne)
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return scoreboard;
    }
}
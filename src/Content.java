import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Klass mis on mõeldud edetabeli faili sisu majandamiseks
 */
public class Content implements Comparable<Content> {

    private final String name; // Mängija nimi
    private final int steps; // Sammude arv
    private LocalDateTime datetime; // Mängimise aeg kuupäev ja kell
    private long time; // Mänguaeg

    /**
     * Objekti loomise konstruktor
     * @param name mängija nimi
     * @param steps sammude arv
     */
    public Content(String name, int steps, LocalDateTime datetime, long time) {
        this.name = name; // this on klassisisene (lilla)
        this.steps = steps;
        this.datetime = datetime;
        this.time = time;
    }

    /**
     * Tagastab mängija nime failist
     * @return mängijanimi
     */
    public String getName() {
        return name;
    }

    /**
     * tagastab sammude arvu
     * @return sammude arv
     */
    public int getSteps() {
        return steps;
    }

    public long getTime() {
        return time;
    }

    public LocalDateTime getDateTime() {
        return datetime;
    }

    /**
     * Sorteerimine sammude järgi kahanevalt
     * @param o objekt mida võrrelda.
     * @return täisarv
     */
    @Override
    public int compareTo(Content o) {
        // return Integer.compare(steps, o.steps); // o on selle sama objekti sammud //Kahanevalt (teistpidi (o.steps, steps) on kahanevalt
        int compareSteps = Integer.compare(steps, o.steps);
        if (compareSteps != 0) {
            return compareSteps;
        } else {
            if (name.equals(o.name)) {
                return datetime.compareTo(o.datetime);
            } else {
                return compareSteps;
            }
        }
    }

    /**
     * Vormindatud edetabel konsooli näitamiseks
     * @return vormindatud rida
     */
    public String formattedData() {
        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        String formattedDatetime = datetime.format(displayFormatter);
        String displayName = name.length() > 15 ? name.substring(0, 15) : String.format("%-15s", name); // Kontrollitakse kas nime pikkus 15, võetakse esimesed 15 märki. Muuljuhul vormistatakse kohe ärta
        String n = String.format("%-15s", displayName); // %- kummale poole pannakse tühikuid
        String s = String.format("%3d", steps); // s on stringi vormistamine, d on täisarv numbri vormistamine
        return formattedDatetime+"   "+ n + s;


    }
}
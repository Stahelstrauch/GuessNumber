import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Klass mis on mõeldud edetabeli faili sisu majandamiseks
 */
public class Content implements Comparable<Content> {

    private final String name; // Mängija nimi
    private final int steps; // Sammude arv
    private final LocalDateTime datetime; // Mängimise aeg kuupäev ja kell
    private final long time; // Mänguaeg

    /**
     * Objekti loomise konstruktor
     * @param name mängija nimi
     * @param steps sammude arv
     * @param datetime mängimise kuupäev ja kellaaeg
     * @param time mängimise pikkus
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

    /**
     * tagastab mängimise aja(pikkuse)
     * @return mängimise aeg
     */
    public long getTime() {
        return time;
    }

    /**
     * tagastab mängimise kellaaja ja kuupäeva
     * @return kellaaeg ja kuupäev
     */
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
            if (name.equals(o.name)) { //Kui nimed on samad, siis võrdleb mängimise aeg
                return datetime.compareTo(o.datetime); //Võtab aluseks mängimise aja
            } else {
                return 0; // Muul juhul võtab aluseks sammud
            }
        }
    }

    /**
     * Vormindatud edetabel konsooli näitamiseks
     * @return vormindatud rida
     */
    public String formattedData() {
        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"); //Millisel kujul aega näidata 22.05.2025 10:13:45
        String formattedDatetime = datetime.format(displayFormatter);
        String displayName = name.length() > 15 ? name.substring(0, 15) : String.format("%-15s", name); // Kontrollitakse kas nime pikkus 15, võetakse esimesed 15 märki. Muuljuhul vormistatakse kohe ärta
        String n = String.format("%-15s", displayName); // %- kummale poole pannakse tühikuid
        String s = String.format("%3d", steps); // s on stringi vormistamine, d on täisarv numbri vormistamine
        return formattedDatetime+"   "+ n + s;


    }
}
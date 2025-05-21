/**
 * Klass mis on mõeldud edetabeli faili sisu majandamiseks
 */
public class Content implements Comparable<Content> {

    private final String name; // Mängija nimi
    private final int steps; // Sammude arv

    /**
     * Objekti loomise konstruktor
     * @param name mängija nimi
     * @param steps sammude arv
     */
    public Content(String name, int steps) {
        this.name = name; // this on klassisisene (lilla)
        this.steps = steps;
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
     * Sorteerimine sammude järgi kahanevalt
     * @param o objekt mida võrrelda.
     * @return täisarv
     */
    @Override
    public int compareTo(Content o) {
        return Integer.compare(steps, o.steps); // o on selle sama objekti sammud //Kahanevalt (teistpidi (o.steps, steps) on kahanevalt
    }

    /**
     * Vormindatud edetabel konsooli näitamiseks
     * @return vormindatud rida
     */
    public String formattedData() {
        String displayName = name.length() > 15 ? name.substring(0, 15) : String.format("%-15s", name); // Kontrollitakse kas nime pikkus 15, võetakse esimesed 15 märki. Muuljuhul vormistatakse kohe ärta
        String n = String.format("%-15s", displayName); // %- kummale poole pannakse tühikuid
        String s = String.format("%3d", steps); // s on stringi vormistamine, d on täisarv numbri vormistamine
        return n + s;


    }
}

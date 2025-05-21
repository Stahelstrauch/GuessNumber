/**
 * Mõõdab taustaks mängu aega
 */
public class Stopwatch {
    private long startTime;
    private long elapsedTime;
    private boolean running; // Ei näe seda runningut, mis teises klassis on


    /**
     * Käivitab stopperi
     */
    public void start() {
        if(!running) {
            startTime = System.currentTimeMillis(); // Käivitusaeg
            running = true;
        }

        }

    /**
     * Peatab stopperi
      */
    public void stop() {
        if (running) {
            elapsedTime = System.currentTimeMillis() - startTime;
            running = false;
        }
    }

    /**
     * Tagastab vormindatud mänguaja 00:00:00
     * @return mänguaeg
     */
    public String getElapsedTime() {
        long totalMillis = elapsedTime;
        if(running) {
            totalMillis = System.currentTimeMillis() - startTime;
        }
        long seconds = totalMillis / 1000;
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long secs = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, secs); //00:00:00
        }

    /**
     * Tagastab mänguaja millisekundites
      * @return mänguaeg
     */
    public long getElapsedMillis() {
        long totalMillis = elapsedTime;
        if(running) {
            totalMillis = System.currentTimeMillis() - startTime;
        }
        return totalMillis;
    }
    }


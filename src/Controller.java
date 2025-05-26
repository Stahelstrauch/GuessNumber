import java.time.LocalDateTime;

/**
 * Käivitab mängu
 */
public class Controller {
    private final Model model;
    private final View view;

    /**
     * Controlleri konstruktor
     * @param model App failis loodud mudel
     * @param view App failis loodud view
     */
    public Controller(Model model, View view) { // Loodud view ja model on kättesaadavad controlleris
        this.model = model;
        this.view = view;
    }

    /**
     * Käivitab mängu loogika
     */
    public void start() { //Mängu loogika
        boolean running = true; //Mäng hakkab tööle
        while (running) {
            view.showMenu(); //Näita menüüd
            int choice = view.getMenuChoice(); // Küsi kasutajalt menüü valikut (1,2 või 3)
            switch (choice) {
                case 1:
                    // TEST view.showMessage("Valisid mängimise");
                    model.initGame(); // Loo uus mäng
                    LocalDateTime now = LocalDateTime.now();
                    // õpetaja variant: view.showMessage(String.valueof(model.getPc_number()));
                    Stopwatch stopwatch = new Stopwatch(); // Loome stopperi
                    stopwatch.start(); //Käivitame stopperi
                    while(!model.isGame_over()) { // Kui mäng pole läbi
                        //System.out.println(model.getPc_number()); // TEST et näha pc numbrit
                        int guess = view.askGuess(); // Küsi kasutajalt numbrit
                        view.showMessage(model.checkGuess(guess)); // Kõigepealt tehakse sulgudes olev kontroll ja siis väljastab tulemuse
                    }
                    stopwatch.stop(); // Peata stopper
                    // Näita edetabelit kujul 00:00:00 (0000)
                    view.showMessage(("Mängu aeg: " + stopwatch.getElapsedTime() + " (" + stopwatch.getElapsedMillis() +")"));
                    // view.showMessage("Mängu aeg: " + stopwatch.getElapsedTime()); // TEST
                    //view.showMessage("Mängu aeg: " + stopwatch.getElapsedMillis() + " millisekundit"); // TEST
                    if (model.isCheater()) { // Kui on petja
                        System.out.println("Sohitegijaid edetabelisse ei lisata!");
                        break;
                    }else { //muudel juhtudel
                        String name = view.askName(); // Küsi nime
                        long time = stopwatch.getElapsedMillis(); // Stopwatchist millisekundite kätte saamine
                        model.saveScore(name, time, now);
                        break;
                    }
                case 2:
                    // List<Content> myScores = model.loadScores(); view.showScoreboard(mySCores);  nii saab kahe reaga
                    view.showScoreboard(model.loadScores()); // showScoreboard tahab saada kaasa Listi ja listi saab modelist loadScores tagastab listi
                    break;
                case 3:
                    running = false;
                    view.showMessage("Head aega!");
                    break;
                default:
                    view.showMessage("Vigane valik!");
            }
        }
    }
}
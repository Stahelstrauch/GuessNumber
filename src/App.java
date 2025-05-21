/**
 * Põhi fail rakenduse käivitamiseks
 */
public class App {
    /**
     * Sellega käivitatakse rakendus
     * @param args käsurea argumendid
     */
    public static void main(String[] args) {
        Model model = new Model();
        // TEST model.initGame(); //algväärtustab
        // TEST System.out.println(model.getPc_number());
        View view = new View();
        // TEST view.showMenu();
        // TEST view.getMenuChoice();
        // TEST view.askGuess();
        // TEST view.showMessage("Mida ma siia kirjutan?");
        Controller controller = new Controller(model, view);
        controller.start(); //Käivita mäng
    }
}

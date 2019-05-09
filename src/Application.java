/**
 * The application class that we run from
 * @author Lynda Thomas and Chris Loftus
 * @version 2.0, 26th February 2017
 */
import java.util.Scanner;

public class Application {
    private Monster monster1;
    private Monster monster2;
    private Treasure treasure1;
    private Treasure treasure2;
    private Scanner in;

    /**
     * Initialise the application with two empty monsters
     * and two empty treasures and a Scanner.
     */
    public Application() {
        monster1 = new Monster();
        treasure1 = new Treasure();
        in = new Scanner(System.in);
    }

    private void runApp() {
        String choice;
        char a;
        System.out.println("*** HELLO - WELCOME TO MONSTER LAND ");
        do {
            printMenu();
            choice = in.nextLine().toUpperCase();
            switch (choice) {
                // Read in information about the two monsters
                case "M":
                    System.out.println("enter info about monster 1");
                    monster1.readKeyboard();
                    break;

                case "H":
                    System.out.println("Change details about the monster:");

                    monster1.changeAspect(Monster.Aspect.HAIR);
                    monster1.changeAspect(Monster.Aspect.FACE);
                    break;

                case "S":
                  System.out.println("Saving monster.");
                  monster1.save();
                  break;

                case "L":
                  System.out.println("Loading monster.");
                  monster1.load();
                  break;

                // Give treasure1 to monster1 and treasure2 to monster2
                case "G":
                    monster1.addTreasure(treasure1);
                    System.out.println("Done adding treasure to monster!");
                    break;
                // Display the current state of our monsters
                case "P":
                    System.out.println(this.toString());
                    break;

                case "Q":
                    System.out.println("*** THANK YOU FOR USING MONSTER LAND");
                    break;

                default:
                    System.out.println("not a valid choice");
            }
        } while (!choice.equals("Q"));
    }

    private void printMenu() {
        System.out.println("lots of menu lines \n h - assign hair and face \n m - read monsters info \n g - give treasures to monsters \n p - print \n s = save \n l = load \n q = quit");
    }

    /**
     * Currently this just returns information about our two monsters
     * @return Info about the two monsters
     */
    public String toString() {
        // If you leave off .toString then .toString is called automatically.
        // If the values are null then they are converted to "null" string
        return "" + monster1;
    }


    public static void main(String args[]) {
        Application app = new Application();
        app.runApp();
    }
}

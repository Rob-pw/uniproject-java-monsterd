/**
 * A basic Treasure class that is missing many methods but
 * has a readKeyboard method to allow populating the class with data
 * @author Lynda Thomas and Chris Loftus
 * @version 2.0, 26th February 2017
 */

import java.util.Scanner;

public class Treasure {
    private String name;
    private int value;

    /**
     * A default constructor. If you don't include this you
     * will get one for free (an implicit constructor), but you
     * would have to assign "no weapon" directly to the instance
     * variable.
     */
    public Treasure() {
        name = "no weapon";
    }

    /**
     * Reads the treasure name and its value from
     * the keyboard.
     */
    public void readKeyboard() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter treasure info: ");
        name = in.nextLine();
        System.out.print("enter value: ");
        value = in.nextInt();
        // This clears the end of line characters ready for the
        // next character input, otherwise you'll get an empty
        // string returned.
        in.nextLine();
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " worth " + value;
    }
}

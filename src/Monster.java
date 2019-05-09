/**
 * A basic Monster class that is missing many methods but
 * has a readKeyboard method to allow populating the class with data
 * @author Lynda Thomas and Chris Loftus
 * @version 3.0, 20th February 2019
 */
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.io.PrintWriter;
import java.io.File;

public class Monster {
    public static class Aspect {
      private String type = null;

      public Aspect(String type) {
        this.type = type;
      }

      public static final Aspect HAIR = new Aspect("hair");
      public static final Aspect FACE = new Aspect("face");
      public static final Aspect TYPE = new Aspect("type");
    }

    // *****************instance variables are the 'attributes' of a thing
    private Map<Aspect, String> aspects;

    private ArrayList<Treasure> loot = new ArrayList<Treasure>();
    private int powerPoints;

    /**
     * A default constructor.
     */
    public Monster() {
      this.aspects = new HashMap<Aspect, String>(
        Map.ofEntries(
          Map.entry(Aspect.HAIR, ""), 
          Map.entry(Aspect.FACE, ""), 
          Map.entry(Aspect.TYPE, "unknown")
        )
      );
    }

    /**
     * This constructor allows us to add the features of the monster.
     * @param startType The type of monster
     * @param startHair The hair colour
     * @param startFace The face colour
     */
    public Monster(ArrayList<Aspect> aspects) {
      this();
      powerPoints = 100;
    }

    /**
     * We can set the treasure object
     * @param newTreasure the treasure we wish to give the monster
     */
    public void addTreasure(Treasure newTreasure) {
        loot.add(newTreasure);
    }

    public void changeAspect(Aspect targetAspect) {
      Scanner in = new Scanner(System.in);

      Boolean containsAspect = aspects.containsKey(targetAspect);
      if (!containsAspect) {
        System.out.println("Bollocks.");
        return;
      }

      System.out.print("enter " + targetAspect.type + ":");
      String value = in.nextLine();

      aspects.put(targetAspect, value);
    }

    /**
     * Reads the monster type, hair colour, face colour and power points from
     * the keyboard.
     */
    public void readKeyboard() {
        Scanner in = new Scanner(System.in);
        
        changeAspect(Aspect.TYPE);
        changeAspect(Aspect.HAIR);
        changeAspect(Aspect.FACE);

        System.out.print("enter power: ");
        powerPoints = in.nextInt();
        // This clears the end of line characters ready for the
        // next character input, otherwise you'll get an empty
        // string returned.
        in.nextLine();
    }

    public String toString() {
      String type = aspects.get(Aspect.TYPE);
      String hair = aspects.get(Aspect.HAIR);
      String face = aspects.get(Aspect.FACE);

      String ret = "This scary monster is of type " + type + " with " + hair
              + " hair and " + face + " face and " + powerPoints
              + " points";
      if (loot.size() == 0) {
        for (Treasure t : loot) {
          ret += " and treasures: " + loot.toString() + "\n";
        }
      } else {
          ret += " and no treasure\n";
      }
      return ret;
    }

    public void save() {
      try {
        File file = new File("monsters.txt");

        if (!file.exists()) file.createNewFile();

        PrintWriter writer = new PrintWriter(file);

        writer.println("points:" + powerPoints);

        for(Map.Entry<Aspect, String> aspectEntry: aspects.entrySet()) {
          Aspect aspect = aspectEntry.getKey();
          String value = aspectEntry.getValue();

          writer.println(aspect.type + ":" + value);
        }

        writer.close();
      } catch (Exception ex) {
        System.out.println(ex);
      }
    }

    public void load() {
      try {
        File file = new File("monsters.txt");
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
          String line = scanner.nextLine();
          String[] parts = line.split(":");
          String type = parts[0];
          String value = parts[1];

          if (type.equals("points")) {
            powerPoints = Integer.parseInt(value);
            continue;
          }
          
          for (Map.Entry<Aspect, String> aspectPair: aspects.entrySet()) {
            Aspect aspect = aspectPair.getKey();
            
            if(aspect.type.equals(type)) {
              aspects.put(aspect, value);
              continue;
            }
          }
        }
      } catch (Exception ex) {
        System.out.println(ex);
      }
    }
}
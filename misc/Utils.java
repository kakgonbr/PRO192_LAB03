
// JDK 17.0.10

package misc;

public class Utils {
    public enum validations {
        vInt,
        vLong,
        vFloat,
        vDouble,
        vBool
    }
    
    public static String getLine(String message, validations val){
        System.out.print(message);
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        switch(val){
            case vInt:
                if (scanner.hasNextInt()) return scanner.nextLine().trim();
                return "";
            case vLong:
                if (scanner.hasNextLong()) return scanner.nextLine().trim();
                return "";
            case vFloat:
                if (scanner.hasNextFloat()) return scanner.nextLine().trim();
                return "";
            case vDouble:
                if (scanner.hasNextDouble()) return scanner.nextLine().trim();
                return "";
            case vBool:
                if (scanner.hasNextBoolean()) return scanner.nextLine().trim();
                return "";
            default:
                return scanner.nextLine();
        }
    }
    
    public static String getLine(String message){
        System.out.print(message);
        return (new java.util.Scanner(System.in)).nextLine();
    }

    public static String normalizeName(String name){ // name is a copy of a reference to a String object in memory
        name = name.trim(); // Remove leading and trailing whitespaces
        name = name.replaceAll("[^a-zA-Z0-9\\s]", "");
        name = name.replaceAll("\\s+", " "); // Remove redundant spaces
        String[] words = name.split("[\\s]"); // Split by space

        for (int i = 0; i < words.length; i++){
            // Capitalizing the words individually
            // left-inclusive, right-exclusive
            if (!words[i].isBlank())
                words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();
        }

        return String.join(" ", words);
    }
}

package lab03;

import java.util.ArrayList;

public abstract class Menu {
    // ------------------------------------------------------------- Fields -------------------------------------------------------------------
    protected boolean stopped;
    ArrayList<String> choices;
    protected String title;

    // ------------------------------------------------------------- Constructors -------------------------------------------------------------------
    Menu(ArrayList<String> _choices, boolean once, String _title){
        choices = _choices;
        stopped = once;
        title = _title;
    }

    // ------------------------------------------------------------- Methods -------------------------------------------------------------------
    private int getChoice(){
        System.out.println("\n" + "-".repeat(20) + title + "-".repeat(20));

        int i = 0;
        String inp;
        for (final String item : choices)
            System.out.println(++i + ". " + item);

        if ((inp = misc.Utils.getLine("Enter choice: ", misc.Utils.validations.vInt)).isBlank())
            return -1;
        else return Integer.parseInt(inp);
    }

    protected abstract void execute(int choice);

    public void start(){
        do {
            execute(getChoice());
        } while (!stopped);
    }
}

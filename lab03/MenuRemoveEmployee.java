package lab03;

import java.util.ArrayList;

public class MenuRemoveEmployee extends Menu{
    private ArrayList<Employee> employees;
    private ArrayList<Integer> tempEmployeesIndex;
    private ArrayList<Integer> matchIndexes;
    private ArrayList<String> choices;

    public MenuRemoveEmployee(ArrayList<Employee> _employees,
                            ArrayList<Integer> indexes, ArrayList<Integer> matches, ArrayList<String> names){
        super(new ArrayList<>(names), true, "Choose which to remove");

        employees = _employees;
        tempEmployeesIndex = indexes;
        matchIndexes = matches;
        choices = names;
    }

    @Override
    protected void execute(int choice) {
        if (choice < 1 || choice > choices.size()) System.out.println("\u001B[31mInvalid choice. Cancelling\u001B[0m");

        else if (choice == choices.size() - 1) { // remove all or only one available
            for (int i = matchIndexes.size() - 1; i >= 0; i--){
                Employee.removeID(employees.get(matchIndexes.get(i)).getID());
                employees.remove(matchIndexes.get(i).intValue());
                tempEmployeesIndex.remove(matchIndexes.get(i));
            }

        } else if (choice < choices.size()) {
            Employee.removeID(employees.get(matchIndexes.get(choice - 1)).getID());
            employees.remove(matchIndexes.get(choice - 1).intValue());
            tempEmployeesIndex.remove(matchIndexes.get(choice - 1));
        }

    }
}

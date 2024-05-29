package lab03;

import java.util.ArrayList;

public class MenuEmployeeList extends Menu{
    private ArrayList<Employee> employees;
    private ArrayList<Integer> tempEmployeeIndexes;

    public MenuEmployeeList(ArrayList<Employee> _employees, ArrayList<Integer> tempIndexes){
        super(new ArrayList<>(java.util.Arrays.asList("Temporary",
                                                            "Official",
                                                            "All",
                                                            "Cancel")), true, "Choose employee type");
        employees = _employees;
        tempEmployeeIndexes = tempIndexes;
    }

    @Override
    protected void execute(int choice) {
        switch (choice){
            case 1: // Temp
                for (final int index : tempEmployeeIndexes)
                    employees.get(index).printInfo();
                break;
            case 2: // Official
                for (int i = 0; i < employees.size(); i++){
                    if (tempEmployeeIndexes.contains(Integer.valueOf(i))) continue;
                    employees.get(i).printInfo();
                }
                break;
            case 3: // all
                for (final Employee employee : employees)
                    employee.printInfo();
        }
    }
}

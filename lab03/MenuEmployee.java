package lab03;

import java.util.ArrayList;

public class MenuEmployee extends Menu{
    private ArrayList<Employee> employees; // DO NOT MODIFY THIS REFERENCE
    private ArrayList<Integer> tempEmployeesIndex;

    public MenuEmployee(ArrayList<String> _choices,
                         boolean once, ArrayList<Employee> _employees,
                         ArrayList<Integer> indexes){
        super(_choices, once, "Menu");
        employees = _employees;
        tempEmployeesIndex = indexes;
    }

    @Override
    protected void execute(int choice) {
        switch (choice) {
            case 1:
                (new MenuAddEmployee(employees, tempEmployeesIndex)).start();
                break;
            case 2:
                if (employees.size() == 0){
                    System.out.println("\u001B[31mThere is no employee in the database.\u001B[0m");
                    return;
                }
                (new MenuEmployeeList(employees, tempEmployeesIndex)).start();
                break;

            case 3:
                if (employees.size() == 0){
                    System.out.println("\u001B[31mThere is no employee in the database.\u001B[0m");
                    return;
                }
                ArrayList<Employee> sortedEmployees = new ArrayList<>(employees);
                
                sortedEmployees.sort((a, b) -> {return Long.compare(b.getSalary(), a.getSalary());});

                for (final Employee employee : sortedEmployees)
                    employee.printInfo();
                break;

            case 4:
                if (employees.size() == 0){
                    System.out.println("\u001B[31mThere is no employee in the database.\u001B[0m");
                    return;
                }
                String name;
                if ((name = misc.Utils.normalizeName(misc.Utils.getLine("Find an employee by name: ")).toLowerCase()).isBlank()){
                    System.out.println("\u001B[31mCannot search for this employee, name might be blank or contain only special characters.\u001B[0m");
                    break;
                }

                ArrayList<Integer> matches = new ArrayList<>();
                for (int i = 0; i < employees.size(); i++){
                    if (employees.get(i).getFullName().toLowerCase().contains(name)) matches.add(i);
                }
                ArrayList<String> names = new ArrayList<>();
                switch (matches.size()) {
                    case 0:
                        System.out.println("\u001B[31mCannot find employee with the name \"" + name + "\"\u001B[0m");
                        break;
                    case 1:
                        names.add(employees.get(matches.get(0)).getFullName());
                        names.add("Cancel");
                        (new MenuRemoveEmployee(employees, tempEmployeesIndex, matches, names)).start();
                        break;
                    default:
                        for (final int match : matches)
                            names.add(employees.get(match).getFullName());

                        names.add("Remove all");
                        names.add("Cancel");
                        (new MenuRemoveEmployee(employees, tempEmployeesIndex, matches, names)).start();
                }
                break;

            case 5:
                if (employees.size() == 0){
                    System.out.println("\u001B[31mThere is no employee in the database.\u001B[0m");
                    return;
                }
                long averageSalary = 0;

                for (final Employee employee : employees)
                    averageSalary += employee.getSalary();
                
                System.out.println("Average salary: " + (averageSalary / employees.size()));

                break;
                
            case 6:
                System.out.println("Exiting.");
                stopped = true;
                break;
            default:
                System.err.println("\u001B[31mInvalid input, enter 6 to exit.\u001B[0m");
        }
    }
    
}

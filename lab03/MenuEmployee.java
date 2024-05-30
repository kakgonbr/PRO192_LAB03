package lab03;

import java.util.ArrayList;

public class MenuEmployee extends Menu{
    private ArrayList<Employee> employees; // DO NOT MODIFY THIS REFERENCE
    private ArrayList<Integer> tempEmployeesIndex;

    public MenuEmployee(ArrayList<Employee> _employees,
                         ArrayList<Integer> indexes){
        super(new ArrayList<>(java.util.Arrays.asList("Add an employee", 
                                                            "Get employee list",
                                                            "Sort employee by salary",
                                                            "Find and remove an employee entry by name",
                                                            "Get average salary of employees",
                                                            "Exit")), false, "Menu");
        employees = _employees;
        tempEmployeesIndex = indexes;
    }

    @Override
    protected void execute(int choice) {
        switch (choice) {
            case 1 -> // Add an employee
                (new MenuAddEmployee(employees, tempEmployeesIndex)).start(); // Menu to add
            case 2 -> {
                // Get a list of employees by type
                if (employees.isEmpty()){
                    System.out.println("\u001B[31mThere is no employee in the database.\u001B[0m");
                    return;
                }
                if (employees.size() == tempEmployeesIndex.size() || tempEmployeesIndex.isEmpty())
                    for (final Employee employee : employees)
                        employee.printInfo();
                else (new MenuEmployeeList(employees, tempEmployeesIndex)).start(); // choose which type
            }

            case 3 -> {
                // get a sorted list of employees by salary
                if (employees.isEmpty()){
                    System.out.println("\u001B[31mThere is no employee in the database.\u001B[0m");
                    return;
                }
                ArrayList<Employee> sortedEmployees = new ArrayList<>(employees);
                
                sortedEmployees.sort((a, b) -> {return Long.compare(b.getSalary(), a.getSalary());});

                for (final Employee employee : sortedEmployees)
                    employee.printInfo();
            }

            case 4 -> {
                // Find and remove employee
                if (employees.isEmpty()){
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
                    case 0 -> System.out.println("\u001B[31mCannot find employee with the name \"" + name + "\"\u001B[0m");
                    case 1 -> {
                        names.add(employees.get(matches.get(0)).getFullName());
                        names.add("Cancel");
                        (new MenuRemoveEmployee(employees, tempEmployeesIndex, matches, names)).start();
                }
                    default -> {
                        for (final int match : matches)
                            names.add(employees.get(match).getFullName());

                        names.add("Remove all");
                        names.add("Cancel");
                        (new MenuRemoveEmployee(employees, tempEmployeesIndex, matches, names)).start();
                }
                }
            }

            case 5 -> {
                // Get average salary
                if (employees.isEmpty()){ // Avoid dividing by 0
                    System.out.println("\u001B[31mThere is no employee in the database.\u001B[0m");
                    return;
                }
                long averageSalary = 0;

                for (final Employee employee : employees)
                    averageSalary += employee.getSalary();
                
                System.out.println("Average salary: " + (averageSalary / employees.size()));
            }
                
            case 6 -> {
                System.out.println("Exiting.");
                stopped = true;
            }
            default -> System.err.println("\u001B[31mInvalid input, enter 6 to exit.\u001B[0m");
        }
    }
    
}

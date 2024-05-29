package lab03;

import java.util.ArrayList;

public class Test {
    private static Menu mainMenu;
    private static ArrayList<Employee> employees = new ArrayList<>();
    private static ArrayList<Integer> tempEmployeesIndex = new ArrayList<>();
    public static void main(String[] args) {
        TempEmployee employee = new TempEmployee(1, "yes sir", "23/02/2005", 3);
        employee.printInfo();
        employees.add(employee);
        tempEmployeesIndex.add(0);

        OfficialEmployee employee2 = new OfficialEmployee(1, "no sir", "4/6/2004", 30, 3);
        employee2.printInfo();
        employees.add(employee2);

        mainMenu = new MenuEmployee(new ArrayList<>(java.util.Arrays.asList("Add an employee", 
                                                                                "Get employee list",
                                                                                "Sort employee by salary",
                                                                                "Find and remove an employee entry by name",
                                                                                "Get average salary of employees",
                                                                                "Exit")), false, employees, tempEmployeesIndex);

        mainMenu.start();
    }
}
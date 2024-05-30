package lab03;

import java.util.ArrayList;

public class Test {
    private static ArrayList<Employee> employees = new ArrayList<>();
    private static ArrayList<Integer> tempEmployeesIndex = new ArrayList<>();
    public static void main(String[] args) {
        TempEmployee employee = new TempEmployee(1, "nguyen van a", "23/02/2005", 3);
        employee.printInfo();
        employees.add(employee);
        tempEmployeesIndex.add(0);

        OfficialEmployee employee2 = new OfficialEmployee(1, "sndwja njsnajkdnwkjadas ds", "4/6/2004", 30, 3);
        employee2.printInfo();
        employees.add(employee2);

        (new MenuEmployee(employees, tempEmployeesIndex)).start();
    }
}
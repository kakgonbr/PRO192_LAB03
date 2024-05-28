package lab03;

import java.util.ArrayList;

public class MenuAddEmployee extends Menu {
    private ArrayList<Employee> employees;
    private ArrayList<Integer> tempEmployeesIndex;

    public MenuAddEmployee(ArrayList<Employee> _employees,
                            ArrayList<Integer> indexes){
        super(new ArrayList<>(java.util.Arrays.asList("Add a temporary employee",
                                                                "Add an official employee",
                                                                "Cancel")), true, "Add an employee");
        employees = _employees;
        tempEmployeesIndex = indexes;
    }

    @Override
    protected void execute(int choice) {
        switch (choice) {
            case 1:
                TempEmployee tempEmployee = new TempEmployee();
                tempEmployee.updateInfo();
                tempEmployeesIndex.add(employees.size());
                employees.add(tempEmployee);
                break;
            case 2:
                OfficialEmployee employee = new OfficialEmployee();
                employee.updateInfo();
                employees.add(employee);
                break;
        }
    }
    
}

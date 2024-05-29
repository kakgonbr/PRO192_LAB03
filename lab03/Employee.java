package lab03;

import java.util.ArrayList;

public abstract class Employee {
    // ------------------------------------------------------------- Fields -------------------------------------------------------------------
    protected int ID;
    protected String firstName;
    protected String lastName = ""; // Last name can be blank
    protected String type = "Normal";
    protected int age;
    protected long dateOfBirth;
    protected int noWorkDays;
    protected static int employeeCount = 0;
    protected static ArrayList<Integer> occupiedID = new ArrayList<>();
    
    // ------------------------------------------------------------- Constructors -------------------------------------------------------------------
    protected Employee(){
        int temp = 0;
        while (!setID(temp++));
        setName("Employee No " + ++employeeCount);
        setDateOfBirth(0);
        setNoWorkDays(7);
    }

    protected Employee(int _ID, String _first, String _last, String _dob, int _workdays){
        while (!setID(_ID++));
        if (!setFirstName(_first)) throw new misc.InvalidParameter("Invalid first name.");
        setLastName(_last);
        if (!setDateOfBirth(_dob)) throw new misc.InvalidParameter("Invalid date of birth.");
        if (!setNoWorkDays(_workdays)) throw new misc.InvalidParameter("Invalid number of work days.");
        employeeCount++;
    }

    protected Employee(int _ID, String _name, String _dob, int _workdays){
        while (!setID(_ID++));
        if (!setName(_name)) throw new misc.InvalidParameter("Invalid name.");
        if (!setDateOfBirth(_dob)) throw new misc.InvalidParameter("Invalid day of birth.");
        if (!setNoWorkDays(_workdays)) throw new misc.InvalidParameter("Invalid number of work days.");
        employeeCount++;
    }
    
    // ------------------------------------------------------------- Getters -------------------------------------------------------------------
    public int getAge() {
        return age;
    }

    public String getDateOfBirth(){
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("dd/MM/yyyy"); 
        // format.setTimeZone(java.util.TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        
        return format.format(new java.util.Date(dateOfBirth));
    }

    public String getFirstName() {
        return firstName;
    }
    
    public int getID() {
        return ID;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName(){
        if (lastName.isBlank()) return firstName;
        return lastName + " " + firstName;
    }

    public int getNoWorkDays() {
        return noWorkDays;
    }

    protected abstract long getSalary();

    // ------------------------------------------------------------- Setters -------------------------------------------------------------------

    private boolean setAge() {
        long currentTime = System.currentTimeMillis();
        int yearDiff = (int) ((currentTime - dateOfBirth) / (1000 * 60 * 60 * 24 * 365.25)); // to sec, to min, to hour, to day, to year

        if (122 < yearDiff || yearDiff < 15) return false;
        age = yearDiff;
        return true;
    }

    public boolean setDateOfBirth(String _dob) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
        // sdf.setTimeZone(java.util.TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        java.util.Date dob;
        try {
             dob = sdf.parse(_dob);
        } catch (java.text.ParseException exc) {
            return false;
        }
        dateOfBirth = dob.getTime();
        
        return setAge();
    }

    public boolean setDateOfBirth(long _dob) {
        dateOfBirth = _dob;

        return true;
    }
    
    public boolean setFirstName(String _first) {
        if ((_first = misc.Utils.normalizeName(_first)).isBlank()) return false;
        firstName = _first;

        return true;
    }

    public boolean setLastName(String _last) {
        if ((_last = misc.Utils.normalizeName(_last)).isBlank()) return false;
        lastName = _last;

        return true;
    }

    public boolean setName(String _name){
        if (_name.isBlank()) return false;
        ArrayList<String> names = new ArrayList<>(java.util.Arrays.asList(_name.split(" ")));

        switch(names.size()){
            case 0:
                return false;
            case 1:
                firstName = misc.Utils.normalizeName(names.get(0));
                return !firstName.isBlank();
            default:
                lastName = misc.Utils.normalizeName(names.get(0));
                names.remove(0);
                firstName = misc.Utils.normalizeName(String.join(" ", names));
        }
        return !firstName.isBlank() && !lastName.isBlank();
    }

    public boolean setID(int _ID) {
        if (_ID == ID) return true; // alr set
        if (occupiedID.contains(_ID)) return false;

        occupiedID.remove(Integer.valueOf(ID));
        ID = _ID;
        occupiedID.add(_ID);

        return true;
    }

    public boolean setNoWorkDays(int _workdays) {
        if (_workdays <= 0) return false;
        noWorkDays = _workdays;

        return true;
    }

    // ------------------------------------------------------------- Methods -------------------------------------------------------------------
    @Override
    public String toString(){
        return "\n"
                + "-".repeat(50)
                + "\nEmployee ID: " + getID()
                + "\nEmployee type: " + type
                + "\nName: " + getFullName()
                + "\nAge: " + getAge()
                + "\nDate of birth: " + getDateOfBirth()
                + "\nNumber of workdays: " + getNoWorkDays()
                ;
    }
    public void printInfo(){
        System.out.println(this);
    }

    public void updateInfo(){
        String inp;
        while ((inp = misc.Utils.getLine("Enter ID: ", misc.Utils.validations.vInt)).isBlank() || !setID(Integer.parseInt(inp)))
        System.out.println("ID is taken or is invalid.");
        while ((inp = misc.Utils.getLine("Enter name: ")).isBlank() || !setName(inp))
            System.out.println("Name cannot be blank or be comprised entirely of special symbols");
        // while ((inp = misc.Utils.getLine("Enter first name: ")).isBlank() || setFirstName(inp))
        //     System.out.println("First name cannot be blank or be comprised entirely of special symbols");
        // while ((inp = misc.Utils.getLine("Enter last name: ")).isBlank() || setLastName(inp))
        //     System.out.println("Last name cannot be blank or be comprised entirely of special symbols");
        // while ((inp = misc.Utils.getLine("Enter age: ", misc.Utils.validations.vInt)).isBlank() || !setAge(Integer.parseInt(inp)))
        //     System.out.println("Age cannot be negative or be greater than 122.");
        while ((inp = misc.Utils.getLine("Enter date of birth: ")).isBlank() || !setDateOfBirth(inp))
            System.out.println("Invalid date of birth.");
        while ((inp = misc.Utils.getLine("Enter number of workdays: ", misc.Utils.validations.vInt)).isBlank() || !setNoWorkDays(Integer.parseInt(inp)))
            System.out.println("Number of workdays must be greater than zero.");
    }

    public static void removeID(int ID){
        occupiedID.remove(Integer.valueOf(ID));
    }
}


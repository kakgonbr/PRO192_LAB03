package lab03;

public final class OfficialEmployee extends Employee{
    // ------------------------------------------------------------- Fields -------------------------------------------------------------------
    private int hourOverTime;

    // ------------------------------------------------------------- Constructors -------------------------------------------------------------------
    public OfficialEmployee(){
        super();
        super.type = "Official";
        setOverTime(0);
    }

    public OfficialEmployee(int _ID, String _first, String _last, String _dob, int _workdays, int overTime){
        super(_ID, _first, _last, _dob, _workdays);
        super.type = "Official";
        if (!setOverTime(overTime)) throw new IllegalArgumentException("Invalid over time.");
    }

    public OfficialEmployee(int _ID, String _name, String _dob, int _workdays, int overTime){
        super(_ID, _name, _dob, _workdays);
        super.type = "Official";
        if (!setOverTime(overTime)) throw new IllegalArgumentException("Invalid over time.");
    }

    // ------------------------------------------------------------- Setters -------------------------------------------------------------------
    public boolean setOverTime(int hours){
        if (hours < 0) return false;

        hourOverTime = hours;
        return true;
    }

    // ------------------------------------------------------------- Overrides -------------------------------------------------------------------
    @Override
    protected long getSalary() {
        return noWorkDays * 1_000_000 + hourOverTime * 100_000;
    }

    @Override
    public String toString(){
        return super.toString() 
                + "\nSalary: " + getSalary()
                + "\n" + "-".repeat(50)
                ;
    }

    @Override 
    public void updateInfo(){
        super.updateInfo();
        String inp;
        while ((inp = misc.Utils.getLine("Enter hours of over time: ", misc.Utils.validations.vInt)).isBlank() || !setOverTime(Integer.parseInt(inp)))
            System.out.println("Please enter a valid positive number.");
    }
}

package lab03;

public final class TempEmployee extends Employee{
    // ------------------------------------------------------------- Fields -------------------------------------------------------------------
    private static double seasonalPayRate = .5d;
    
    // ------------------------------------------------------------- Constructors -------------------------------------------------------------------
    public TempEmployee(){
        super();
        super.type = "Temporary";
    }

    public TempEmployee(int _ID, String _first, String _last, String _dob, int _workdays){
        super(_ID, _first, _last, _dob, _workdays);
        super.type = "Temporary";
    }

    public TempEmployee(int _ID, String _name, String _dob, int _workdays){
        super(_ID, _name, _dob, _workdays);
        super.type = "Temporary";
    }

    // ------------------------------------------------------------- Overrides -------------------------------------------------------------------
    @Override
    protected long getSalary() {
        return (long) (noWorkDays * 1_000_000 * seasonalPayRate);
    }

    @Override
    public String toString(){
        return super.toString() 
                + "\nSalary: " + getSalary()
                + "\n" + "-".repeat(50)
                ;
    }
}

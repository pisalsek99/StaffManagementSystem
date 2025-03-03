import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

public class HourlySalaryEmployee extends StaffMember {
    private int hoursWorked;
    private double rate;

    public HourlySalaryEmployee(int id, String name, String address, int hoursWorked, double rate) {
        super(id, name, address);
        this.hoursWorked = hoursWorked;
        this.rate = rate;
    }
    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }
    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public double pay() {
        return hoursWorked * rate;
    }
    public double getRate() {
        return rate;
    }
    public int getHoursWorked() {
        return hoursWorked;
    }

}
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

public class SalariedEmployee extends StaffMember {
    private double salary;
    private double bonus;

    public SalariedEmployee(int id, String name, String address, double salary, double bonus) {
        super(id, name, address);
        this.salary = salary;
        this.bonus = bonus;
    }

    @Override
    public double pay() {
        return salary + bonus;
    }
    public void setSalary(double newSalary) {
        this.salary = newSalary;
    }
    public double getSalary() {
        return salary;
    }
    public double getBonus() {
        return bonus;
    }
    // setBonus
    public void setBonus(double newBonus) {
        this.bonus = newBonus;
    }


}
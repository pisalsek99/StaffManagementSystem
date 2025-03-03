import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.*;

public class Main {
    public static final String RESET = "\033[0m";
    public static final String YELLOW = "\033[0;33m";  // COLOR YELLOW
    public static final String BLUE = "\033[0;34m";    // COLOR BLUE
    public static final String CYAN = "\033[0;36m";    // COLOR CYAN
    public static final String GREEN = "\033[0;32m";   // COLOR GREEN
    public static final String PURPLE = "\033[0;35m";  // COLOR PURPLE
    public static final String RED = "\033[0;31m";     // COLOR RED

    private static List<StaffMember> staffMembers = new ArrayList<>();
    private static int nextId = 8;
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        initializeDefaultEmployees();
        View.menu();
    }

    public static void initializeDefaultEmployees() {
        staffMembers.add(new Volunteer(1, "Sek Pisal", "Kep", 900.0));
        staffMembers.add(new SalariedEmployee(2, "Chan Kimheng", "KP", 300.0, 20.0));
        staffMembers.add(new HourlySalaryEmployee(3, "Sokha Chea", "BTB", 60, 12.0));
        staffMembers.add(new Volunteer(4, "Pich Sreysros", "PP", 500.0));
        staffMembers.add(new SalariedEmployee(5, "Chea Vannak", "TK", 350.0, 25.0));
        staffMembers.add(new HourlySalaryEmployee(6, "Mina keo", "KT", 70, 10.0));
        staffMembers.add(new SalariedEmployee(7, "Mey Ly", "KD", 800.0, 10.0));
    }

    public static void insertEmployee() {

        Table table = new Table(4, BorderStyle.UNICODE_BOX_HEAVY_BORDER, ShownBorders.ALL);
        table.addCell(Main.PURPLE + " Insert Employee " +  Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center), 4);
        table.addCell(Main.CYAN + "1. Volunteer" + Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
        table.addCell(Main.CYAN + "2. Salaried Employee" + Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
        table.addCell(Main.CYAN + "3. Hourly Employee" + Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
        table.addCell(Main.CYAN + "4. BACK" + Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center));

        table.setColumnWidth(0, 37, 65);
        table.setColumnWidth(1, 37, 65);
        table.setColumnWidth(2, 37, 65);
        table.setColumnWidth(3, 37, 65);
        System.out.println(table.render());

        System.out.print( YELLOW + "+ What type number do you want to create? : " + RESET);
        int type = ValidateInput.getIntInput();
        Table table2 = new Table(1,  BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
        table2.addCell(" INSERT EMPLOYEE INFORMATION " , new CellStyle(CellStyle.HorizontalAlign.center), 1);
        table2.setColumnWidth(0, 50, 50);
        System.out.println( table2.render() );
        System.out.print(BLUE +"Enter Name: " +RESET);
        String name = ValidateInput.getStringInput(sc, RED + "Name cannot be empty please input again." +RESET);
        System.out.print(BLUE + "Enter Address: " +RESET);
        String address = ValidateInput.getStringInput(sc,RED + "Address cannot be empty." +RESET);
        StaffMember employee = null;
        switch (type) {
            // Volunteer
            case 1:
                System.out.print(BLUE + "Enter Salary: " +RESET);
                double salary = ValidateInput.getDoubleInput();
                employee = new Volunteer(nextId++, name, address, salary);
                break;
            // Salary Employee
            case 2:
                System.out.print("Enter Salary: ");
                double salary2 = ValidateInput.getDoubleInput();
                System.out.print("Enter Bonus: ");
                double bonus = ValidateInput.getDoubleInput();
                employee = new SalariedEmployee(nextId++, name, address, salary2, bonus);
                break;
            // Hourly Employee
            case 3:
                System.out.print("Enter Hours Worked: ");
                int hours = ValidateInput.getIntInput();
                System.out.print("Enter Rate: ");
                double rate = ValidateInput.getDoubleInput();
                employee = new HourlySalaryEmployee(nextId++, name, address, hours, rate);
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid option");
                return;
        }

        staffMembers.add(employee);
        System.out.println("You added " + name + " of type " + (type == 1 ? "Volunteer" : (type == 2 ? "Salaried Employee" : "Hourly Employee")) + " successfully");
    }

    public static void updateEmployee() {
        System.out.println( YELLOW + "========== Update Employee ==========" + RESET);
        System.out.print(BLUE + "Enter or search by ID but cannot update id: " + RESET);
        int id = ValidateInput.getIntInput();

        Optional<StaffMember> staff = staffMembers.stream().filter(s -> s.id == id).findFirst();

        if (staff.isEmpty()) {
            System.out.println(RED + "Employee not found." + RESET);
            return;
        }

        StaffMember employee = staff.get();

        int option = -1;
        if (employee instanceof Volunteer) {
            System.out.println(YELLOW + "Choose option to update\n1. Name 2. Address 3. Salary 4. Cancel" + RESET);
            System.out.print( CYAN + "Select an option to update: " + RESET);
            option = ValidateInput.getIntInput();
        } else if (employee instanceof HourlySalaryEmployee) {
            System.out.println(YELLOW + "Choose option to update\n1. Name 2. Address 3. Hours Worked 4. Rate 0. Cancel" + RESET);
            System.out.print(CYAN + "Select an option to update: " + RESET);
            option = ValidateInput.getIntInput();
        } else if (employee instanceof SalariedEmployee) {
            System.out.println(YELLOW + "Choose option to update\n1. Name 2. Address 3. Salary 4. Bonus 0. Cancel" + RESET);
            System.out.print(CYAN + "Select an option to update: " + RESET);
            option = ValidateInput.getIntInput();
        }

        switch (option) {
            case 1:
                System.out.print( YELLOW + "Change name to: " + RESET);
                String newName = ValidateInput.getStringInput(sc,"Name cannot be empty please input again.");
                employee.name = newName;
                System.out.println(BLUE + "Name has been updated successfully." + RESET);
                break;
            case 2:
                System.out.print( YELLOW + "Change address to: " + RESET);
                String newAddress = ValidateInput.getStringInput(sc,"Address cannot be empty.");
                employee.address = newAddress;
                System.out.println( BLUE + "Address has been updated successfully." + RESET);
                break;
            case 3:
                if (employee instanceof Volunteer) {
                    System.out.print( YELLOW + "Change salary for Volunteer to: " + RESET);
                    double newSalary = ValidateInput.getDoubleInput();
                    ((Volunteer) employee).setSalary(newSalary);
                    System.out.println(BLUE + "Salary has been updated successfully for Volunteer." + RESET);
                } else if (employee instanceof HourlySalaryEmployee) {
                    System.out.print(YELLOW + "Change hours worked to: " + RESET);
                    int newHoursWorked = ValidateInput.getIntInput();
                    ((HourlySalaryEmployee) employee).setHoursWorked(newHoursWorked);
                    System.out.println( BLUE + "Hours worked has been updated successfully." + RESET);
                } else if (employee instanceof SalariedEmployee) {
                    System.out.print("Change salary to: ");
                    double newSalary = ValidateInput.getDoubleInput();
                    ((SalariedEmployee) employee).setSalary(newSalary);
                    System.out.println(BLUE + "Salary has been updated successfully." + RESET);
                }
                break;

            case 4:
                if (employee instanceof HourlySalaryEmployee) {
                    System.out.print( YELLOW + "Change rate to: " + RESET);
                    double newRate = ValidateInput.getDoubleInput();
                    ((HourlySalaryEmployee) employee).setRate(newRate);
                    System.out.println( BLUE + "Rate has been updated successfully." + RESET);
                } else if (employee instanceof SalariedEmployee) {
                    System.out.print("Change bonus to: ");
                    double newBonus = ValidateInput.getDoubleInput();
                    ((SalariedEmployee) employee).setBonus(newBonus);
                    System.out.println (BLUE + "Bonus has been updated successfully." + RESET);
                } else {
                    System.out.println( RED + "No rate or bonus field to update for this employee type." + RESET);
                }
                break;
            case 0:
                System.out.println( YELLOW + "Update cancelled." + RESET);
                break;
            default:
                System.out.println( RED + "Invalid option." + RESET);
        }

    }
    public static void displayEmployee() {
        int pageSize = 3;
        int currentPage = 0;
        int totalPages = (int) Math.ceil(staffMembers.size() / (double) pageSize);

        do {
            int start = currentPage * pageSize;
            int end = Math.min(start + pageSize, staffMembers.size());

            Table table = new Table(9, BorderStyle.UNICODE_BOX_HEAVY_BORDER, ShownBorders.ALL);
            table.setColumnWidth(0, 20, 20);
            table.setColumnWidth(1, 10, 30);
            table.setColumnWidth(2, 20, 30);
            table.setColumnWidth(3, 15, 20);
            table.setColumnWidth(4, 15, 25);
            table.setColumnWidth(5, 15, 25);
            table.setColumnWidth(6, 15, 25);
            table.setColumnWidth(7, 15, 25);
            table.setColumnWidth(8, 15, 25);
            CellStyle center = new CellStyle(CellStyle.HorizontalAlign.center);
            // Add header row with color formatting if desired.
            table.addCell(Main.CYAN + " Display All Employees " +  Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center), 9);

            table.addCell(Main.BLUE + "Type" + Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
            table.addCell(Main.BLUE + "ID" + Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
            table.addCell(Main.BLUE + "Name" + Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
            table.addCell(Main.BLUE + "Address" + Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
            table.addCell(Main.BLUE + "Salary" + Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
            table.addCell(Main.BLUE + "Bonus" + Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
            table.addCell(Main.BLUE + "Hour" + Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
            table.addCell(Main.BLUE + "Rate" + Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
            table.addCell(Main.BLUE + "Payment" + Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
            for (int i = start; i < end; i++) {
                StaffMember staff = staffMembers.get(i);
                if (staff instanceof Volunteer) {
                    table.addCell(Main.RED + "Volunteer" + Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(Main.YELLOW + staff.id + Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(Main.YELLOW + staff.name + Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(Main.YELLOW + staff.address + Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(Main.YELLOW + ((Volunteer) staff).getSalary() + Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(Main.YELLOW + "N/A" + Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(Main.YELLOW + "N/A" + Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(Main.YELLOW + "N/A" + Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(Main.YELLOW + staff.pay() + Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                } else if (staff instanceof SalariedEmployee) {
                    SalariedEmployee se = (SalariedEmployee) staff;
                    table.addCell(Main.RED + "Salaried" + Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(Main.YELLOW + staff.id + Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(Main.YELLOW + staff.name + Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(Main.YELLOW + staff.address + Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(Main.YELLOW + se.getSalary() + Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(Main.YELLOW + se.getBonus() + Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(Main.YELLOW + "N/A" + Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(Main.YELLOW + "N/A" + Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(Main.YELLOW + se.pay() + Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                } else if (staff instanceof HourlySalaryEmployee) {
                    HourlySalaryEmployee he = (HourlySalaryEmployee) staff;
                    table.addCell(Main.RED + "Hourly" + Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(Main.YELLOW + staff.id + Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(Main.YELLOW + staff.name + Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(Main.YELLOW + staff.address + Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(Main.YELLOW + "N/A" + Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(Main.YELLOW + "N/A" + Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(Main.YELLOW + he.getHoursWorked() + Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(Main.YELLOW + he.getRate() + Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(Main.YELLOW + he.pay() + Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                } else {
                    table.addCell(Main.RED + "Unknown" + Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(Main.YELLOW + "N/A" + Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(Main.YELLOW + "N/A" + Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(Main.YELLOW + staff.pay() + Main.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                }
            }

            // Render the table and display it.
            System.out.println(table.render());
            System.out.println("Page " + (currentPage + 1) + " / " + totalPages + "\t\t\t  1. First page  2. Next page  3. Previous page  4. Last page  5. Exit");
            System.out.print("Choose: ");
            int choice = ValidateInput.getIntInput();

            switch (choice) {
                case 1:
                    currentPage = 0;
                    break;
                case 2:
                    if (currentPage < totalPages - 1) {
                        currentPage++;
                    }
                    break;
                case 3:
                    if (currentPage > 0) {
                        currentPage--;
                    }
                    break;
                case 4:
                    currentPage = totalPages - 1;
                    break;
                case 5:
                    return;
                default:
                    System.out.println( RED + "Invalid choice!" + RESET);
            }
        } while (true);
    }

    public static void removeEmployee() {
        System.out.println( YELLOW + "========== Remove Employee ==========" + RESET);
        System.out.print( BLUE + "Enter ID to remove: " + RESET);
        int id = ValidateInput.getIntInput();

        System.out.print( YELLOW + "Are you sure you want to delete this account? (Y/N): ");
        String confirmation = sc.nextLine().trim().toUpperCase();

        if (confirmation.equals("Y")) {
            Optional<StaffMember> staff = staffMembers.stream().filter(s -> s.id == id).findFirst();

            if (staff.isPresent()) {
                staffMembers.remove(staff.get());
                System.out.println( BLUE + "Removed successfully" + RESET);
            } else {
                System.out.println( RED + "Employee not found." + RESET);
            }
        } else if (confirmation.equals("N")) {
            System.out.println( YELLOW + "Operation cancelled." + RESET);
        } else {
            System.out.println( RED + "Invalid input, please enter Y or N." + RESET);
        }
    }



}
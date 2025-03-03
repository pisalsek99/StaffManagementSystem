import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.*;

public class StaffManagementSystem {
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
        table.addCell(StaffManagementSystem.PURPLE + " Insert Employee " +  StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center), 4);
        table.addCell(StaffManagementSystem.CYAN + "1. Volunteer" + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
        table.addCell(StaffManagementSystem.CYAN + "2. Salaried Employee" + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
        table.addCell(StaffManagementSystem.CYAN + "3. Hourly Employee" + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
        table.addCell(StaffManagementSystem.CYAN + "4. BACK" + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));

        table.setColumnWidth(0, 37, 65);
        table.setColumnWidth(1, 37, 65);
        table.setColumnWidth(2, 37, 65);
        table.setColumnWidth(3, 37, 65);
        System.out.println(table.render());

        System.out.print(BLUE + "[+] What type number do you want to create? : " + RESET);
        int type = ValidateInput.getIntInput();

        // table 2
        Table table2 = new Table(1,  BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);

        table2.addCell( " INSERT EMPLOYEE INFORMATION " , new CellStyle(CellStyle.HorizontalAlign.center), 1);

        table2.setColumnWidth(0, 100, 100);

        System.out.println(PURPLE+ table2.render() +RESET);

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
        System.out.println("===== Update Employee =====");
        System.out.print("Enter or search by ID but cannot update id: ");
        int id = ValidateInput.getIntInput();

        Optional<StaffMember> staff = staffMembers.stream().filter(s -> s.id == id).findFirst();

        if (staff.isEmpty()) {
            System.out.println("Employee not found.");
            return;
        }

        StaffMember employee = staff.get();

        int option = -1;
        if (employee instanceof Volunteer) {
            System.out.println("Choose option to update\n1. Name 2. Address 3. Salary 4. Cancel");
            System.out.print("Select an option to update: ");
            option = ValidateInput.getIntInput();
        } else if (employee instanceof HourlySalaryEmployee) {
            System.out.println("Choose option to update\n1. Name 2. Address 3. Hours Worked 4. Rate 0. Cancel");
            System.out.print("Select an option to update: ");
            option = ValidateInput.getIntInput();
        } else if (employee instanceof SalariedEmployee) {
            System.out.println("Choose option to update\n1. Name 2. Address 3. Salary 4. Bonus 0. Cancel");
            System.out.print("Select an option to update: ");
            option = ValidateInput.getIntInput();
        }

        switch (option) {
            case 1:
                System.out.print("Change name to: ");
                String newName = ValidateInput.getStringInput(sc,"Name cannot be empty please input again.");
                employee.name = newName;
                System.out.println("Name has been updated successfully.");
                break;
            case 2:
                System.out.print("Change address to: ");
                String newAddress = ValidateInput.getStringInput(sc,"Address cannot be empty.");
                employee.address = newAddress;
                System.out.println("Address has been updated successfully.");
                break;
            case 3:
                if (employee instanceof Volunteer) {
                    System.out.print("Change salary for Volunteer to: ");
                    double newSalary = ValidateInput.getDoubleInput();
                    ((Volunteer) employee).setSalary(newSalary);  // Allow salary change for Volunteer
                    System.out.println("Salary has been updated successfully for Volunteer.");
                } else if (employee instanceof HourlySalaryEmployee) {
                    System.out.print("Change hours worked to: ");
                    int newHoursWorked = ValidateInput.getIntInput();
                    ((HourlySalaryEmployee) employee).setHoursWorked(newHoursWorked);
                    System.out.println("Hours worked has been updated successfully.");
                } else if (employee instanceof SalariedEmployee) {
                    System.out.print("Change salary to: ");
                    double newSalary = ValidateInput.getDoubleInput();
                    ((SalariedEmployee) employee).setSalary(newSalary);
                    System.out.println("Salary has been updated successfully.");
                }
                break;

            case 4:
                if (employee instanceof HourlySalaryEmployee) {
                    System.out.print("Change rate to: ");
                    double newRate = ValidateInput.getDoubleInput();
                    ((HourlySalaryEmployee) employee).setRate(newRate);
                    System.out.println("Rate has been updated successfully.");
                } else if (employee instanceof SalariedEmployee) {
                    System.out.print("Change bonus to: ");
                    double newBonus = ValidateInput.getDoubleInput();
                    ((SalariedEmployee) employee).setBonus(newBonus);
                    System.out.println("Bonus has been updated successfully.");
                } else {
                    System.out.println("No rate or bonus field to update for this employee type.");
                }
                break;
            case 0:
                System.out.println("Update cancelled.");
                break;
            default:
                System.out.println("Invalid option.");
        }
        // System.out.println(employee);
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
            table.addCell(StaffManagementSystem.PURPLE + " Display All Employees " +  StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center), 9);

            table.addCell(StaffManagementSystem.CYAN + "Type" + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
            table.addCell(StaffManagementSystem.CYAN + "ID" + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
            table.addCell(StaffManagementSystem.CYAN + "Name" + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
            table.addCell(StaffManagementSystem.CYAN + "Address" + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
            table.addCell(StaffManagementSystem.CYAN + "Salary" + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
            table.addCell(StaffManagementSystem.CYAN + "Bonus" + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
            table.addCell(StaffManagementSystem.CYAN + "Hour" + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
            table.addCell(StaffManagementSystem.CYAN + "Rate" + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
            table.addCell(StaffManagementSystem.CYAN + "Payment" + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));

            // Add each staff member's data as a row.
            for (int i = start; i < end; i++) {
                StaffMember staff = staffMembers.get(i);
                // Type-specific fields
                if (staff instanceof Volunteer) {
                    table.addCell(StaffManagementSystem.YELLOW + "Volunteer" + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    // Common field
                    table.addCell(StaffManagementSystem.YELLOW + staff.id + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(StaffManagementSystem.YELLOW + staff.name + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(StaffManagementSystem.YELLOW + staff.address + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(StaffManagementSystem.YELLOW + ((Volunteer) staff).getSalary() + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(StaffManagementSystem.YELLOW + "N/A" + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(StaffManagementSystem.YELLOW + "N/A" + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(StaffManagementSystem.YELLOW + "N/A" + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(StaffManagementSystem.YELLOW + staff.pay() + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                } else if (staff instanceof SalariedEmployee) {
                    SalariedEmployee se = (SalariedEmployee) staff;
                    table.addCell(StaffManagementSystem.YELLOW + "Salaried" + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(StaffManagementSystem.YELLOW + staff.id + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(StaffManagementSystem.YELLOW + staff.name + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(StaffManagementSystem.YELLOW + staff.address + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(StaffManagementSystem.YELLOW + se.getSalary() + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(StaffManagementSystem.YELLOW + se.getBonus() + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(StaffManagementSystem.YELLOW + "N/A" + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(StaffManagementSystem.YELLOW + "N/A" + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(StaffManagementSystem.YELLOW + se.pay() + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                } else if (staff instanceof HourlySalaryEmployee) {
                    HourlySalaryEmployee he = (HourlySalaryEmployee) staff;
                    table.addCell(StaffManagementSystem.YELLOW + "Hourly" + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(StaffManagementSystem.YELLOW + staff.id + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(StaffManagementSystem.YELLOW + staff.name + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(StaffManagementSystem.YELLOW + staff.address + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(StaffManagementSystem.YELLOW + "N/A" + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(StaffManagementSystem.YELLOW + "N/A" + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(StaffManagementSystem.YELLOW + he.getHoursWorked() + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(StaffManagementSystem.YELLOW + he.getRate() + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    // Common fields
                    table.addCell(StaffManagementSystem.YELLOW + he.pay() + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                } else {
                    // Fallback for any unknown type
                    table.addCell(StaffManagementSystem.YELLOW + "Unknown" + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(StaffManagementSystem.YELLOW + "N/A" + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(StaffManagementSystem.YELLOW + "N/A" + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
                    table.addCell(StaffManagementSystem.YELLOW + staff.pay() + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
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
                    System.out.println("Invalid choice!");
            }
        } while (true);
    }

    public static void removeEmployee() {
        System.out.println("===== Remove Employee =====");
        System.out.print("Enter ID to remove: ");
        int id = ValidateInput.getIntInput();

        System.out.print("Are you sure you want to delete this account? (Y/N): ");
        String confirmation = sc.nextLine().trim().toUpperCase();

        if (confirmation.equals("Y")) {
            Optional<StaffMember> staff = staffMembers.stream().filter(s -> s.id == id).findFirst();

            if (staff.isPresent()) {
                staffMembers.remove(staff.get());
                System.out.println("Removed successfully");
            } else {
                System.out.println("Employee not found.");
            }
        } else if (confirmation.equals("N")) {
            System.out.println("Operation cancelled.");
        } else {
            System.out.println("Invalid input, please enter Y or N.");
        }
    }



}
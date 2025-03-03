import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

public class View {
    public static void menu(){
        while (true) {

            Table table = new Table(3, BorderStyle.UNICODE_BOX_HEAVY_BORDER, ShownBorders.ALL);
            table.addCell(StaffManagementSystem.PURPLE + " STAFF MANAGEMENT SYSTEM " +  StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center), 3);
            table.addCell(StaffManagementSystem.CYAN + "1. INSERT EMPLOYEE " + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
            table.addCell(StaffManagementSystem.CYAN + "2. UPDATE EMPLOYEE" + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
            table.addCell(StaffManagementSystem.CYAN + "3. DISPLAY EMPLOYEE" + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
            table.addCell(StaffManagementSystem.CYAN + "4. REMOVE EMPLOYEE" + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
            table.addCell(StaffManagementSystem.CYAN + "5. Exit" + StaffManagementSystem.RESET, new CellStyle(CellStyle.HorizontalAlign.center));
            table.setColumnWidth(0, 50, 65);
            table.setColumnWidth(1, 50, 65);
            table.setColumnWidth(2, 50, 65);
            System.out.println(table.render());
            System.out.print(StaffManagementSystem.PURPLE + "[+] Insert option(1-5): " + StaffManagementSystem.RESET);
            int option = ValidateInput.getIntInput();

            switch (option) {
                case 1 -> StaffManagementSystem.insertEmployee();
                case 2 -> StaffManagementSystem.updateEmployee();
                case 3 -> StaffManagementSystem.displayEmployee();
                case 4 -> StaffManagementSystem.removeEmployee();
                case 5 -> {
                    System.out.print(StaffManagementSystem.GREEN + "\n\uD83E\uDD14 Are you sure you want to exit? (Y/N): " + StaffManagementSystem.RESET);
                    String confirmExit = StaffManagementSystem.sc.nextLine();
                    if (confirmExit.equalsIgnoreCase("Y")) {
                        System.out.println(StaffManagementSystem.BLUE + "\uD83D\uDD1A Exiting the system. Thank You \uD83D\uDE0A❣\uFE0F" + StaffManagementSystem.RESET);
                        System.exit(0);
                    }
                }
                default ->
                        System.out.println(StaffManagementSystem.RED +" Invalid choice, please choose a valid option." +StaffManagementSystem.RESET);
            }
        }
    }
    public static void displayStaff(){

    }
}
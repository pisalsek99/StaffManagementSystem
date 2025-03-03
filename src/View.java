import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

public class View {
    public static void menu(){
        while (true) {

            Table table = new Table(1, BorderStyle.UNICODE_ROUND_BOX, ShownBorders.ALL);

            table.setColumnWidth(0, 50, 50);


            table.addCell(Main.CYAN +"STAFF MANAGEMENT SYSTEM" + Main.RESET,new CellStyle(CellStyle.HorizontalAlign.center),1);
            table.addCell(Main.GREEN +"1. Add Employee" + Main.RESET,new CellStyle(CellStyle.HorizontalAlign.center));
            table.addCell(Main.GREEN +"2. Update Employee"+ Main.RESET,new CellStyle(CellStyle.HorizontalAlign.center));
            table.addCell(Main.GREEN +"3. Display Employee"+ Main.RESET,new CellStyle(CellStyle.HorizontalAlign.center));
            table.addCell(Main.GREEN +"4. Remove Employee"+ Main.RESET,new CellStyle(CellStyle.HorizontalAlign.center));
            table.addCell(Main.GREEN +"5. Exit"+ Main.RESET,new CellStyle(CellStyle.HorizontalAlign.center));
            System.out.println(table.render());
            System.out.print(Main.PURPLE + "[+] Insert option(1-5): " + Main.RESET);
            int option = ValidateInput.getIntInput();

            switch (option) {
                case 1 -> Main.insertEmployee();
                case 2 -> Main.updateEmployee();
                case 3 -> Main.displayEmployee();
                case 4 -> Main.removeEmployee();
                case 5 -> {
                    System.out.print(Main.GREEN + "\n\uD83E\uDD14 Are you sure you want to exit? (Y/N): " + Main.RESET);
                    String confirmExit = Main.sc.nextLine();
                    if (confirmExit.equalsIgnoreCase("Y")) {
                        System.out.println(Main.BLUE + "\uD83D\uDD1A Exiting the system. Thank You \uD83D\uDE0A❣\uFE0F" + Main.RESET);
                        System.exit(0);
                    }
                }
                default ->
                        System.out.println(Main.RED +" Invalid choice, please choose a valid option." + Main.RESET);
            }
        }
    }
    public static void displayStaff(){

    }
}
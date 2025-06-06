import java.util.ArrayList;
import javax.swing.JOptionPane;


public class SalesPersonTest {

    private static int SALES_PEOPLE_ARRAY_LENGTH = 10;
    private static ArrayList<SalesPerson> sales_people_array = new ArrayList<>(SALES_PEOPLE_ARRAY_LENGTH);
    private static int RANGE_ARRAY_LENGTH = 9;
    private static int[] range_array;


    private static void noSalePersonAdded () {
        JOptionPane.showMessageDialog(null,"There is no sales person inputted in the app");
    }

    private static void addSalesPerson () {

        int sales_person_number = Integer.parseInt(JOptionPane.showInputDialog("How many sales person are you going to enter ? "));
        int sales_person_entered = 0;


        while (sales_person_entered < sales_person_number) {

            int gross_salary = Integer.parseInt(JOptionPane.showInputDialog("Remaining sales person to input: "+(sales_person_number-sales_person_entered)+"\nEnter the gross salary: "));

            SalesPerson a_sale_person = new SalesPerson(gross_salary);
            sales_people_array.add(a_sale_person);

            sales_person_entered ++;
        }
    }

    private static void printSalesPeople () {
        if (sales_people_array.size() == 0) {
            noSalePersonAdded();
            return;
        }

        String displayOutput = "Sale Person Salary             Sale Person Commission\n";

        for (SalesPerson person : sales_people_array) {
             displayOutput += person.getGross_sales()+"                                           "+person.getCommission_fee()+"\n";
        }
        JOptionPane.showMessageDialog(null,displayOutput);
    }

    private static void getSalesPeopleAnalytics () {
        if (sales_people_array.size() == 0) {
            noSalePersonAdded();
            return;
        }

        String title = "Commission Fee Table\n";
        String title_under_line = "----------------------------------------\n";

        if (range_array != null) {
            range_array = null;
        }

        range_array = new int[RANGE_ARRAY_LENGTH];

        for (SalesPerson person : sales_people_array) {

            if (person.getCommission_fee() >= 200 && person.getCommission_fee() <= 299)
                range_array[0] ++;
            else if (person.getCommission_fee() >= 300 && person.getCommission_fee() <= 399)
                range_array[1] ++;
            else if (person.getCommission_fee() >= 400 && person.getCommission_fee() <= 499)
                range_array[2] ++;
            else if (person.getCommission_fee() >= 500 && person.getCommission_fee() <= 599)
                range_array[3] ++;
            else if (person.getCommission_fee() >= 600 && person.getCommission_fee() <= 699)
                range_array[4] ++;
            else if (person.getCommission_fee() >= 700 && person.getCommission_fee() <= 799)
                range_array[5] ++;
            else if (person.getCommission_fee() >= 800 && person.getCommission_fee() <= 899)
                range_array[6] ++;
            else if (person.getCommission_fee() >= 900 && person.getCommission_fee() <= 999)
                range_array[7] ++;
            else if (person.getCommission_fee() >= 1000)
                range_array[8] ++;
        }

        String range_string = "";
        int k = 2;

        for (int i = 0; i < range_array.length; i++) {

            String star = "";
            for (int j = 0; j < range_array[i]; j++) {
                star += "* ";
            }

            if (k != 10) {
                range_string +=  ("$" + (k * 100) + " - " + ((k * 100) + 99) + "   --->   " + star + "\n");
            }
            else {
                range_string += ("$" + (k * 100) + "-above --->   " + star + "\n");
            }
            k ++;
        }

        String displayOutput = title + title_under_line + range_string;

        JOptionPane.showMessageDialog(null,displayOutput);
    }

    private static void controlProgramFlowMethod () {

        String userTouchDisplayMessage = "Press 1 to add sales person\nPress 2 to view the sales peron data\nPress 3 to get the data analytics of the sales people commissions\nPress 0 to end the app";
        int use_app = Integer.parseInt(JOptionPane.showInputDialog(userTouchDisplayMessage));

        while (use_app != 0) {

            switch (use_app) {

                case 1:
                    addSalesPerson();
                break;
                case 2:
                    printSalesPeople();
                break;
                case 3:
                    getSalesPeopleAnalytics();
                break;
                default:
                JOptionPane.showMessageDialog(null,"You have made a wrong press!");
            }

            use_app = Integer.parseInt(JOptionPane.showInputDialog(userTouchDisplayMessage));
        }
    }


    public static void main (String[] args) {

        JOptionPane.showMessageDialog(null,"Hey welcome to our Sales commission app");
        controlProgramFlowMethod();
        JOptionPane.showMessageDialog(null,"Thank you for using our app!");

        if (range_array != null)
            range_array = null;
        if (sales_people_array != null)
            sales_people_array = null;
    }
}

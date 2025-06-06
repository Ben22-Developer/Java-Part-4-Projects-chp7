import java.security.SecureRandom;

public class TotalSales {

    private static int[][] sales_array_monthly;
    private static int[][][] sales_array_daily;
    private static int[] sales_person_PIN_array;
    private static int[] sales_product_PIN_array;
    private static final int TOTAL_RECORDS = 5;
    private static final int PRODUCT_TYPE_SOLD_PER_DAY = 4;
    private static final int SALES_PERSON = 4;
    private static final int ALL_PRODUCTS = 5;
    private static final int TOTAL_DAYS = 30;

    private static final SecureRandom randomize = new SecureRandom();

    private static void generateSalesPersonORProductPIN (int[] array, int lower_bound, int range_difference) {

        for (int i = 0; i < array.length; i++) {
            int a_PIN = lower_bound + range_difference * (1 + randomize.nextInt(125));
            array[i] = a_PIN;
        }
    }

    private static int generateSoldProductPrice () {
        int price = 500 + 50 * randomize.nextInt(10);
        return price;
    }

    private static void generateSoldProductsPerDay () {
        for (int k = 0; k < TOTAL_DAYS; k++) {
            for (int i = 0; i < sales_array_monthly.length; i++) {
                for (int j = 0; j < sales_array_monthly[i].length; j++) {
                    int sales = generateSoldProductPrice();
                    sales_array_monthly[i][j] += sales;
                    sales_array_daily[k][i][j] = sales;
                }
            }
        }
    }

    private static void randomArraysFill () {
        generateSalesPersonORProductPIN(sales_person_PIN_array,1000,75);
        generateSalesPersonORProductPIN(sales_product_PIN_array,1000,50);
        printSalesPersonPINS();
        generateSoldProductsPerDay();
    }

    private static void printSalesPersonPINS() {

        int i;

        System.out.print("\t\t\t\t\t");
        for (i = 0; i < sales_person_PIN_array.length; i++) {
            System.out.print(sales_person_PIN_array[i]+"\t\t");
        }

        System.out.print("TOTAL"+"\t\t");

        System.out.println();
    }

    private static void printArraysData () {
        int i,j;

        String a_zero= "0";

        for (int day = 0; day < sales_array_daily.length; day ++) {
            for (i = 0; i < sales_array_daily[day].length; i++) {
                System.out.print("Day "+((day < 10) ? (a_zero+(day+1)) : (day+1) )+" "+sales_product_PIN_array[i]);
                int product_total_sales_on_curr_day = 0;
                for (j = 0; j < sales_array_daily[day][i].length; j++) {
                    System.out.print("\t\t\t"+sales_array_daily[day][i][j]);
                    product_total_sales_on_curr_day += sales_array_daily[day][i][j];
                }
                System.out.print("\t\t\t"+product_total_sales_on_curr_day);
                System.out.println();
            }
            System.out.println();
        }
        System.out.print("EACH SALES PERSON\t");
        for (j = 0; j < PRODUCT_TYPE_SOLD_PER_DAY; j++) {
            int total_sales_person_sales = 0;
            for (i = 0; i < TOTAL_RECORDS; i++) {
                total_sales_person_sales += sales_array_monthly[i][j];
            }
            System.out.print(total_sales_person_sales+"\t\t");
        }
    }

    private static void initializeArrays () {
        sales_array_monthly = new int[TOTAL_RECORDS][PRODUCT_TYPE_SOLD_PER_DAY];
        sales_array_daily = new int[TOTAL_DAYS][TOTAL_RECORDS][PRODUCT_TYPE_SOLD_PER_DAY];
        sales_person_PIN_array = new int[SALES_PERSON];
        sales_product_PIN_array = new int[ALL_PRODUCTS];
    }

    private static void deleteArrays () {
        sales_array_monthly = null;
        sales_person_PIN_array = null;
        sales_product_PIN_array = null;
        sales_array_daily = null;
    }

    public static void startSimulation () {
        initializeArrays();
        randomArraysFill();
        printArraysData();
        deleteArrays();
    }
}

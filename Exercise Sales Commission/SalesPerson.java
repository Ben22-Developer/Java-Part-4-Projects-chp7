public class SalesPerson {

    private static int fixed_commission_fee = 200;
    private int commission_fee, gross_sales;

    public SalesPerson (int gross_sales) {
        setGross_sales(gross_sales);
    }

    public void setGross_sales(int gross_sales) {
        this.gross_sales = gross_sales;
        setCommission_fee();
    }

    private void setCommission_fee () {
        this.commission_fee = (int) (this.gross_sales * 0.09) + fixed_commission_fee;
    }

    public double getGross_sales () {
        return this.gross_sales;
    }

    public int getCommission_fee () {
        return this.commission_fee;
    }
}

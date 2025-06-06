public class Main {

    private static int product (int...nums) {

        int product = 1;
        for (int i = 0; i < nums.length; i++)
            product *= nums[i];
        return product;
    }

    public static void main (String[] args) {
        System.out.println("Product --> "+product(2,3,4,5));
    }
}

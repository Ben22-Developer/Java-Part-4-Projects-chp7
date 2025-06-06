public class CommandsOperations {

    public static int toRead () {

        String display = "Enter the digit";
        return UI.showInputIntegerDialog(display);
    }

    public static void toWrite (int[] memory, int index_to_access) {

        String display = "Data at index: "+index_to_access+"\nData: ";
        display += memory[index_to_access];
        UI.showMessageDialog(display);
    }

    public static int toLoad (int[] memory, int index_to_access) {
        return memory[index_to_access];
    }
    public static int toAdd (int[] memory, int index_to_access, int accumulator) {
        return accumulator + memory[index_to_access];
    }
    public static int toSubtract (int[] memory, int index_to_access, int accumulator) {
        return accumulator - memory[index_to_access];
    }
    public static int toDivide (int[] memory, int index_to_access, int accumulator) {
        int quotient;
        try {
            quotient = accumulator / memory[index_to_access];
        }
        catch (ArithmeticException e) {
            return 0;
        }
        return quotient;
    }

    public static int toMultiply (int[] memory, int index_to_access, int accumulator) {
        return accumulator * memory[index_to_access];
    }

    public static int getRemainder (int[] memory, int index_to_access, int accumulator) {
        return accumulator % memory[index_to_access];
    }

    public static int getExponential (int[] memory, int index_to_access, int accumulator) {

        double value = (double) memory[index_to_access];
        double accumulator_value = (double) accumulator;

        return (int) (accumulator_value * Math.pow(10,value));
    }

    public static boolean toCheckBranchNeg (int accumulator) {
         return accumulator < 0;
    }

    public static boolean toCheckBranchZero (int accumulator) {
        return accumulator == 0;
    }
}

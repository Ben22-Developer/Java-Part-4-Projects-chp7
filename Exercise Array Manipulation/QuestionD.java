public class QuestionD {
    private static int[] arrayA;
    private static int[] arrayB;
    private static int ARRAY_A_LENGTH = 11;
    private static int ARRAY_B_LENGTH = 34;

    public static void initializeArrayA () {
        arrayA = new int[ARRAY_A_LENGTH];
        int evens = 30;
        for (int i = 0; i < arrayA.length; i++) {
            arrayA[i] = evens;
            evens += 20;
        }
    }

    public static void initializeArrayB () {
        arrayB = new int[ARRAY_B_LENGTH];
        System.arraycopy(arrayA, 0,arrayB,0,arrayA.length);
        int even_neg = -10;
        for (int i = 11; i < arrayB.length; i++) {
            arrayB[i] = even_neg;
            even_neg -= 10;
        }
    }

    public static void printArrayA () {
        System.out.println("Array A");
        for (int elt : arrayA)
            System.out.print(elt+", ");
    }
    public static void printArrayB () {
        System.out.println();
        System.out.println("Array B");
        for (int elt : arrayB)
            System.out.print(elt+", ");
    }
}

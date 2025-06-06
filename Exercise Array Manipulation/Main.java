import java.awt.*;

public class Main {

    private static void questionE () {
        System.out.println("\n\nSub question E");
        System.out.println("------------------");
        QuestionE.initializeArray();;
        QuestionE.print();
        System.out.printf("\nSmallest element : %.2f",QuestionE.getSmallestElement());
    }

    private static void questionD () {
        System.out.println("\n\nSub question D");
        System.out.println("------------------");
        QuestionD.initializeArrayA();
        QuestionD.initializeArrayB();
        QuestionD.printArrayA();
        QuestionD.printArrayB();
    }

    private static void questionC() {
        System.out.println("\n\nSub question c");
        System.out.println("------------------");
        QuestionC.setLength();
        QuestionC.initializeArray();
        QuestionC.printArray();
        System.out.printf("\nThe total of the array: %.2f",QuestionC.getTotalOfArray());
    }

    private static void questionB () {
        System.out.println("\n\nSub question B");
        System.out.println("------------------");

        QuestionB.setLength();
        QuestionB.initializeArray();
        QuestionB.printArray();
    }

    private static void questionA () {

        System.out.println("Sub question A");
        System.out.println("------------------");

        QuestionA obj = new QuestionA();
        obj.printAllArrayElements();
        int element_6 = obj.getSixthArrayElt();

        System.out.println("The sixth element is: "+element_6);
    }

    public static void main (String[] args) {

        questionA();
        questionB();
        questionC();
        questionD();
        questionE();
    }
}

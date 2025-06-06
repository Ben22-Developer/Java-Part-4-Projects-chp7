import java.security.SecureRandom;

public class QuestionA {

    private int arrayF[];
    private static int total_array_elements = 10;

    public QuestionA() {
        SecureRandom randomize = new SecureRandom();
        arrayF = new int[total_array_elements];
        for (int i = 0; i < arrayF.length; i++){
            int random_int = 1 + 2 * randomize.nextInt(50);
            arrayF[i] = random_int;
        }
    }

    public void printAllArrayElements() {
        System.out.println("The full array");
        for (int i = 0; i < this.arrayF.length; i++) {
            System.out.println("Element "+(i + 1)+" : "+this.arrayF[i]);
        }
        System.out.println();
    }

    public int getSixthArrayElt () {
        return arrayF[5];
    }

}

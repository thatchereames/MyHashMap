import java.util.Scanner;

public class HashCodeTester {
    
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        String obj;
        boolean contin = true;
        int limit = 10;
        while(contin) {
            System.out.print("Enter your string to be hased: ");
            obj = myScanner.nextLine();
            if (obj.equals("esc")) {
                contin = false;
            }
            int objHash = obj.hashCode();
            System.out.println("The hash of your String is " + objHash);
            System.out.println("The hash of your String %10 is " + objHash % 10);
            System.out.println("The hash of your String %5 is " + objHash % 5);
        }
        
    }
}

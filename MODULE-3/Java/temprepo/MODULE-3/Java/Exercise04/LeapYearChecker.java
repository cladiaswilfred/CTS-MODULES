import java.util.Scanner;

public class LeapYearChecker {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.print("Year: ");
        int year = scan.nextInt();

        boolean leapFlag = false;
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                leapFlag = (year % 400 == 0);
            } else {
                leapFlag = true;
            }
        }

        if (leapFlag) {
            System.out.println(year + " is a leap year.");
        } else {
            System.out.println(year + " is not a leap year.");
        }

        scan.close();
    }
}

/*
 * Name: Elaf Yousef Aloufi
 * ID: 1911265
 * Email: Ealoufi0015@stu.kau.edu.sa
 * Course name: CPCS204
 * Section: BBR
 * Dr.Huda Aljaloud
 * Date: 21th Oct 2020
 * Assignment#02: Recursion
 */
package bbr_1911265_p2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

// Main class
public class BBR_1911265_P2 {

    static Scanner input;
    static PrintWriter output;

    public static void main(String[] args) throws FileNotFoundException {

        // Read the data from two text files
        // Generate a text file as output
        File InputFile = new File("input.in.txt");
        File outputfile = new File("input.out.txt");

        // Check if the files exist and display an error message if it does not exist
        if (!InputFile.exists()) {
            System.out.println("***** File is not exist *****");
            System.exit(0);
        }

        // Create a scanner objects
        input = new Scanner(InputFile);

        // Create a printwriter object
        output = new PrintWriter(outputfile);

        // Read number of commands from the file 
        int commandsNumber = input.nextInt();
        input.nextLine();
        int counter = 0;
        output.println();

        // Read commands and invoke the method
        while (counter < commandsNumber) {
            String commands = input.next();

            switch (commands.toLowerCase()) {

                case "descarraycheck":
                    String readLine = input.nextLine();
                    String[] numbers = readLine.split(";");
                    ArrayList<Integer> arrayOfnum = new ArrayList<>();
                    for (String x : numbers) {
                        arrayOfnum.add(Integer.parseInt(x.trim()));
                    }

                    int n = arrayOfnum.size();

                    if (isSorted(arrayOfnum, n) == 1) {
                        output.print(commands + "" + readLine);
                        output.println("\nResult: 1 (Array elements are sorted in descending order)\n");
                    } else {
                        output.print(commands + " " + readLine);
                        output.println("\nResult: 0 (Array elements are not sorted in descending order)\n");
                    }

                    counter++;
                    break;

                case "dectohex":
                    String dec = input.next();
                    output.println("DecToHex " + dec + " (Decimal)");
                    output.println("Result: " + decimalToHexa(dec) + "  (Hexadecimal)\n");

                    counter++;
                    break;

                case "noccurrences":
                    String str = input.next().trim();
                    String sub = input.next().trim();
                    int parameter = input.nextInt();

                    output.println("Noccurrences " + str + "  " + sub + "  " + parameter);

                    if (occurrenceNumber(sub, str) > parameter) {
                        output.println("Result: true (\"" + str + "\" contains more than " + parameter + " occurrences of \"" + sub + "\")\n");
                    } else {
                        output.println("Result: false (\"" + str + "\" contains less than " + parameter + " occurrences of \"" + sub + "\")\n");
                    }

                    counter++;
                    break;
            }

        }
        Quit();
    }

    // Checks whether an array of integers is sorted in descending order or not
    public static int isSorted(ArrayList<Integer> arrayOfnum, int n) {

        // If the array has one or no elements
        if (n == 1 || n == 0) {
            return 1;
        }

        // Pairs are not sorted
        if (arrayOfnum.get(n - 1) > (arrayOfnum.get(n - 2))) {
            return 0;

        } else {

            // Last pair was sorted
            // Keep on checking
            return isSorted(arrayOfnum, n - 1);
        }
    }

    // Receives a decimal number and converts it to a hexadecimal number
    public static String decimalToHexa(String dec) {
        int decimal = Integer.parseInt(dec);
        int remainder = decimal % 16;
        String result = "";

        if (decimal == 0) {
            return "";
        } else {
            switch (remainder) {
                case 10:
                    result = "A";
                    break;
                case 11:
                    result = "B";
                    break;
                case 12:
                    result = "C";
                    break;
                case 13:
                    result = "D";
                    break;
                case 14:
                    result = "E";
                    break;
                case 15:
                    result = "F";
                    break;
                default:
                    result = remainder + result;
                    break;
            }
            return decimalToHexa(Integer.toString(decimal / 16)) + result;

        }

    }

    // Calculates recursively if at least n occurrences of a sub-string exist in string
    public static int occurrenceNumber(String sub, String str) {

        int subLen = sub.length();
        if (str.length() < subLen || str.length() == 0) {
            return 0;
        }

        if (str.substring(0, subLen).equalsIgnoreCase(sub)) {
            return occurrenceNumber(sub, str.substring(1)) + 1;
        }

        return occurrenceNumber(sub, str.substring(1));
    }

    // Exit from the System
    public static void Quit() {

        // Close the file
        input.close();
        output.close();
        System.exit(0);
    }

}

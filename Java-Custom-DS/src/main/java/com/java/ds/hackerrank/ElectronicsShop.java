/**
 * A person wants to determine the most expensive computer keyboard and USB drive that can be purchased with a give budget. Given price lists for keyboards and USB drives and a budget, find the cost to buy them. If it is not possible to buy both items, return
 *
 * .
 *
 * Example
 *
 *
 * The person can buy a , or a . Choose the latter as the more expensive option and return
 *
 * .
 *
 * Function Description
 *
 * Complete the getMoneySpent function in the editor below.
 *
 * getMoneySpent has the following parameter(s):
 *
 *     int keyboards[n]: the keyboard prices
 *     int drives[m]: the drive prices
 *     int b: the budget
 *
 * Returns
 *
 *     int: the maximum that can be spent, or
 *
 *     if it is not possible to buy both items
 *
 * Input Format
 *
 * The first line contains three space-separated integers
 * , , and , the budget, the number of keyboard models and the number of USB drive models.
 * The second line contains space-separated integers , the prices of each keyboard model.
 * The third line contains space-separated integers
 *
 * , the prices of the USB drives.
 *
 * Constraints
 *
 * The price of each item is in the inclusive range
 *
 *     .
 *
 * Sample Input 0
 *
 * 10 2 3
 * 3 1
 * 5 2 8
 *
 * Sample Output 0
 *
 * 9
 *
 * Explanation 0
 *
 * Buy the
 * keyboard and the USB drive for a total cost of
 *
 * .
 *
 * Sample Input 1
 *
 * 5 1 1
 * 4
 * 5
 *
 * Sample Output 1
 *
 * -1
 *
 * Explanation 1
 *
 * There is no way to buy one keyboard and one USB drive because 4+5>5
 * , so return -1.
 */

package com.java.ds.hackerrank;

import java.io.*;
import java.util.*;

public class ElectronicsShop {

    static int getMoneySpent(int[] keyboards, int[] drives, int b) {
        int max = -1;
        for(int i=0; i<keyboards.length; i++){
            for(int j=0; j<drives.length; j++){
                int keyboardVal = keyboards[i];
                int driveVal = drives[j];
                int total = keyboardVal+driveVal;
                if(total>max && total<=b){
                    max = total;
                }
            }
        }
        return max;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] bnm = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        int b = Integer.parseInt(bnm[0]);

        int n = Integer.parseInt(bnm[1]);

        int m = Integer.parseInt(bnm[2]);

        int[] keyboards = new int[n];

        String[] keyboardsItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        for (int keyboardsItr = 0; keyboardsItr < n; keyboardsItr++) {
            int keyboardsItem = Integer.parseInt(keyboardsItems[keyboardsItr]);
            keyboards[keyboardsItr] = keyboardsItem;
        }

        int[] drives = new int[m];

        String[] drivesItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        for (int drivesItr = 0; drivesItr < m; drivesItr++) {
            int drivesItem = Integer.parseInt(drivesItems[drivesItr]);
            drives[drivesItr] = drivesItem;
        }

        /*
         * The maximum amount of money she can spend on a keyboard and USB drive, or -1 if she can't purchase both items
         */

        int moneySpent = getMoneySpent(keyboards, drives, b);

        bufferedWriter.write(String.valueOf(moneySpent));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

/**
 * An avid hiker keeps meticulous records of their hikes. During the last hike that took exactly steps, for every step it was noted if it was an uphill, , or a downhill, step. Hikes always start and end at sea level, and each step up or down represents a
 * <p>
 * unit change in altitude. We define the following terms:
 * <p>
 * A mountain is a sequence of consecutive steps above sea level, starting with a step up from sea level and ending with a step down to sea level.
 * A valley is a sequence of consecutive steps below sea level, starting with a step down from sea level and ending with a step up to sea level.
 * <p>
 * Given the sequence of up and down steps during a hike, find and print the number of valleys walked through.
 * <p>
 * Example
 * <p>
 * The hiker first enters a valley units deep. Then they climb out and up onto a mountain
 * <p>
 * units high. Finally, the hiker returns to sea level and ends the hike.
 * <p>
 * Function Description
 * <p>
 * Complete the countingValleys function in the editor below.
 * <p>
 * countingValleys has the following parameter(s):
 * <p>
 * int steps: the number of steps on the hike
 * string path: a string describing the path
 * <p>
 * Returns
 * <p>
 * int: the number of valleys traversed
 * <p>
 * Input Format
 * <p>
 * The first line contains an integer
 * , the number of steps in the hike.
 * The second line contains a single string , of
 * <p>
 * characters that describe the path.
 * <p>
 * Constraints
 * <p>
 * Sample Input
 * <p>
 * 8
 * UDDDUDUU
 * <p>
 * Sample Output
 * <p>
 * 1
 * <p>
 * Explanation
 * <p>
 * If we represent _ as sea level, a step up as /, and a step down as \, the hike can be drawn as:
 * <p>
 * _/\      _
 * \    /
 * \/\/
 * <p>
 * The hiker enters and leaves one valley.
 */
package com.java.ds.hackerrank;

public class CountingValleys {
    public static int countingValleys(int steps, String path) {
        char[] valleyArr = path.toCharArray();
        int stepLevel = 0;
        int valleyNum = 0;
        for (int i = 0; i < valleyArr.length; i++) {
            String val = String.valueOf(valleyArr[i]);
            if (val.equalsIgnoreCase("d")) {
                stepLevel--;
            } else if (val.equalsIgnoreCase("u")) {
                stepLevel++;
                if(stepLevel==0){
                    valleyNum++;
                }
            }
        }
        return valleyNum;
    }

    public static void main(String[] args) {
        System.out.println(countingValleys(8, "UDDDUDUU"));
    }
}

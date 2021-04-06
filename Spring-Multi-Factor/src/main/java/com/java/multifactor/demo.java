package com.java.multifactor;

import java.util.ArrayList;
import java.util.List;

public class demo {

    public static String getSmallestAndLargest(String main, int k) {
        int initialCounter = 0;
        int finalCounter = k;
        String smallest = main.substring(initialCounter,finalCounter);
        String largest = main.substring(initialCounter,finalCounter);
        int len = main.length();
        while(finalCounter<=len){
            int result = largest.compareTo(main.substring(initialCounter,finalCounter));
            if(result<0){
                if(largest.compareTo(smallest)<0){
                    smallest = largest;
                }
                largest = main.substring(initialCounter,finalCounter);
            }else{
                int smallResult = smallest.compareTo(main.substring(initialCounter,finalCounter));
                if(smallResult>0){
                    smallest = main.substring(initialCounter,finalCounter);
                }
            }
            initialCounter = initialCounter+1;
            finalCounter = finalCounter+1;
        }
        System.out.println(largest);
        System.out.println(smallest);
        return smallest + "\n" + largest;
    }
    public static void main(String[] args) {

    }
}

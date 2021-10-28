package com.java.ds.hackerrank;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class BracketPairs {

    public static void main(String[] args) {
        char[] ipArr = "(".toCharArray();
        List<String> pairs = Arrays.asList("(", "{", "[");

        Stack<String> queue = new Stack();
        queue.push(String.valueOf(ipArr[0]));
        for(int i=1; i<ipArr.length; i++){
            String val = String.valueOf(ipArr[i]);
            if(pairs.contains(val)){
                queue.add(val);
                continue;
            }
            String lastPair = queue.peek();
            if(null != lastPair && isSecondPair(val)){
                if(getValidPair(val).equals(lastPair)){
                    queue.pop();
                }else{
                    break;
                }
            }
        }

        if (queue.isEmpty()){
            System.out.println("valid series");
        }else{
            System.out.println("invalid series");
        }
    }

    private static boolean isSecondPair(String val) {
        return val.equals("}") || val.equals(")") || val.equals("]");
    }

    private static String getValidPair(String val) {
        if(val.equals("}")){
            return "{";
        }else if(val.equals(")")){
            return "(";
        }else if(val.equals("]")){
            return "[";
        }
        return null;
    }

}

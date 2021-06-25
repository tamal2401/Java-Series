package com.java.ds.comparator;

import java.util.*;
import java.util.stream.Collectors;

public class Demo {

    public static int migratoryBirds(List<Integer> arr) {
        // Write your code here
        Map<Integer, Integer> birdMap = new HashMap<>();
        for(int i=0; i<arr.size(); i++){
            if(birdMap.get(arr.get(i))==null){
                birdMap.put(arr.get(i), 1);
            }else{
                int val = birdMap.get(arr.get(i));
                val++;
                birdMap.put(arr.get(i), val);
            }
        }
        int minKey = arr.get(0);
        int maxCount = birdMap.get(minKey);
        for(Integer key : birdMap.keySet()){
            Integer value = birdMap.get(key);
            if(value>maxCount){
                minKey = key;
                maxCount = value;
            }else if(value == maxCount && key<minKey){
                minKey = key;
                maxCount = value;
            }
        }
        System.out.println(minKey);
        return minKey;
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,4,4,4,5,3);
        migratoryBirds(list);
    }


}

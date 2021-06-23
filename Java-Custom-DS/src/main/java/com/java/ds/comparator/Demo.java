package com.java.ds.comparator;

public class Demo {

    public static String timeConversion(String s) {
        // Write your code here
        StringBuilder time = new StringBuilder();
        if(s.contains("AM")){
            String currentTime = s.split("AM")[0];
            String[] timeArr = currentTime.split(":");
            int hour = Integer.parseInt(timeArr[0]);
            int min = Integer.parseInt(timeArr[1]);
            int sec = Integer.parseInt(timeArr[2]);
            if(hour==12){
                time.append("00");
            }else{
                time.append(hour<10?"0"+hour:hour);
            }
            time.append(":");
            time.append(min<10?"0"+min:min);
            time.append(":");
            time.append(sec<10?"0"+sec:sec);
            time.append("AM");
        }else{
            String currentTime = s.split("PM")[0];
            String[] timeArr = currentTime.split(":");
            int hour = Integer.parseInt(timeArr[0]);
            int min = Integer.parseInt(timeArr[1]);
            int sec = Integer.parseInt(timeArr[2]);
            if(hour==12){
                time.append("12");
            }else{
                time.append(12+hour);
            }
            time.append(":");
            time.append(min<10?"0"+min:min);
            time.append(":");
            time.append(sec<10?"0"+sec:sec);
            time.append("PM");
        }
        return time.toString();
    }

    public static void main(String[] args) {
        System.out.println(timeConversion("07:05:45PM"));
    }
}

package com.spring.jpa;

import com.spring.jpa.domain.UserProfile;
import com.spring.jpa.domain.User;
import com.spring.jpa.repository.UserRepo;
import com.spring.jpa.repository.UserProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class InitializeData {
    public static void main(String[] args){
        int[][] arr = {{1,2,3}, {2,3,4}, {2,4,6}};

        List<Date> dateList = new ArrayList<>();

        for(int i=0; i<arr.length; i++){
            dateList.add(new Date(arr[i][0],arr[i][1],arr[i][2]));
        }
        Collections.sort(dateList);

        System.out.println(dateList.get(0));
    }

    // public static int calculateDays(int[] dateElement){
    //   return int[0]*365+int[1]*30+int[2];
    // }
    static class Date implements Comparable<Date>{
        public int year;
        public int month;
        public int day;

        public Date(int year, int month, int day){
            this.year = year;
            this.month = month;
            this.day = day;
        }

        public int getYear(){
            return this.year;
        }
        public int getMonth(){
            return this.month;
        }
        public int getDay(){
            return this.day;
        }

        public int getDay(Date d){
            return d.year*365+d.month*30+d.day;
        }

        public int compareTo(Date d){
            if(getDay(this)>getDay(d)){
                return 1;
            }else if(getDay(this)==getDay(d)){
                return 0;
            }else{
                return -1;
            }
        }
    }

}

package com.java.jobscraper.pojo;


import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JobDetailsRequest {
    private String skillSets;
    private String location;
    private String exp;

    public String getLocation() {
        return location;
    }

    public String getExp() {
        return exp;
    }

    public String getSkillSets(){
        /*if(null==skillSets && !StringUtils.isBlank(skillSets)){
            String[] skills = skillSets.split(",");
            return Arrays.asList(skills);
        }*/
        return skillSets;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSkillSets(String skillSets) {
        this.skillSets = skillSets;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "JobDetailsRequest{" +
                "location='" + location + '\'' +
                ", skillSets='" + skillSets + '\'' +
                ", exp='" + exp + '\'' +
                '}';
    }
}

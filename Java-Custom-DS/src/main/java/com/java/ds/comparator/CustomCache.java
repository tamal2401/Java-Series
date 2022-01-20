package com.java.ds.comparator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CustomCache {
    Map<String, TimeExpiredObject> customCache = new HashMap<>();

    long INTERVAL = 10000;
    long lastCacheCheck;

    public CustomCache() throws InterruptedException {
        lastCacheCheck = System.currentTimeMillis();
        Thread th = new Thread(()-> {
            Set<String> keySet = customCache.keySet();
            for(String each : keySet){
                TimeExpiredObject value = customCache.get(each);
                long interval = System.currentTimeMillis()-value.getStoreTime();
                if(interval>value.getExpTimeInMilis()){
                    customCache.remove(each);
                }
            }
            try {
                Thread.sleep(INTERVAL);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        while(true){
            th.start();
        }
    }

    public void put(String key, Object value, int time) {
        // check key present or not
        customCache.put(key, new TimeExpiredObject(value, time));
    }

    public Object get(String key){
        TimeExpiredObject value = customCache.get(key);
        if(null==value){
            return null;
        }
        long interval = System.currentTimeMillis()-value.getStoreTime();
        if(interval>value.getExpTimeInMilis()){
            customCache.remove(key);
            return null;
        }
        return value.getValue();
    }
}

class TimeExpiredObject {
    Object value;
    int expTimeInMilis=1000;
    long storeTime;

    public TimeExpiredObject(Object key, int expTimeInMilis) {
        this.value = key;
        this.expTimeInMilis = expTimeInMilis;
        this.storeTime = System.currentTimeMillis();
    }

    public TimeExpiredObject(Object key) {
        this.value = key;
        this.storeTime = System.currentTimeMillis();
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public int getExpTimeInMilis() {
        return expTimeInMilis;
    }

    public void setExpTimeInMilis(int expTimeInMilis) {
        this.expTimeInMilis = expTimeInMilis;
    }

    public long getStoreTime() {
        return storeTime;
    }

    public void setStoreTime(long storeTime) {
        this.storeTime = storeTime;
    }
}

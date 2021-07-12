/**
 * Custom Hashmap Implementation in JAVA8
 */
package com.java.ds.hashmap.custom;

public class CustomHashMap<k, v> {

    public Entry<k, v>[] table;
    public int defaultSize = 4;

    /**
     * Each key value pair is store in this class structure
     *
     * @param <k> -> defines key for each pair
     * @param <v> -> defines value for each pair
     */
    static class Entry<k, v> {
        k key;
        v value;
        Entry<k, v> next;

        public Entry(k key, v value, Entry<k, v> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public CustomHashMap() {
        table = new Entry[defaultSize];
    }

    /**
     * To put key and value in a Map
     *
     * @param newKey
     * @param newvalue
     */
    public void put(k newKey, v newvalue) {
        int hash = hash(newKey);
        Entry<k, v> newEntry = new Entry<k, v>(newKey, newvalue, null);

        if (null == table[hash]) {
            table[hash] = newEntry;
        } else {
            Entry<k, v> previous = null;
            Entry<k, v> current = table[hash];
            while (current != null) { //we have reached last entry of bucket.
                if (current.key.equals(newKey)) {
                    if (previous == null) {  //node has to be insert on first of bucket.
                        newEntry.next = current.next;
                        table[hash] = newEntry;
                        return;
                    } else {
                        newEntry.next = current.next;
                        previous.next = newEntry;
                        return;
                    }
                }
                previous = current;
                current = current.next;
            }
            previous.next = newEntry;
        }
    }

    /**
     * To get Value based on an Key input
     *
     * @param key
     * @return
     */
    public v get(k key) {
        int hash = hash(key);
        if (null == table[hash]) {
            return null;
        } else {
            Entry<k, v> entry = table[hash];

            while (entry != null) {
                if (key.equals(entry.key)) {
                    return entry.value;
                }
                entry = entry.next;
            }
        }
        return null;
    }

    /**
     * To delete EntrySet based on the provided Key
     *
     * @param deleteKey
     * @return
     */
    public boolean remove(k deleteKey){
        int hash = hash(deleteKey);

        if(null==table[hash]){
            return false;
        }else{
            Entry<k, v> previous = null;
            Entry<k, v> current = table[hash];
            while(current!=null) {
                if (current.key.equals(deleteKey)) {
                    if(previous==null){
                        current = current.next;
                    }else{
                        previous.next = current.next;
                    }
                    return true;
                }
                previous = current;
                current = current.next;
            }
        }
        return false;
    }

    /**
     * To display all the EntrySet in the HashMap
     */
    public void display(){
        for(Entry<k, v> eachEntry : table) {
            if (eachEntry!=null) {
                while (eachEntry != null) {
                    System.out.println(eachEntry.toString());
                    eachEntry = eachEntry.next;
                }
            }
        }
    }

    /**
     * Generates the hashcode based ont eh passed Key object
     *
     * @param key
     * @return hashcode
     */
    private int hash(k key) {
        return Math.abs(key.hashCode() % defaultSize);
    }
}

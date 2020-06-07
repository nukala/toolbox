package org.ravi.udemy.dsa;

import lombok.AllArgsConstructor;
import lombok.Getter;

// inspired from the class
// https://www.udemy.com/master-the-coding-interview-data-structures-algorithms/learn/lecture/12312672#overview
//
// JavaScript uses String->Object not type safe
public class MyHashMap implements TheHashMap {
    private int size;
    private Entry[] entries;

    public MyHashMap() {
        this(10);
    }

    public MyHashMap(int length) {
        this.entries = new Entry[length];
    }

    private int hash(String key) {
        int hash = 0;

        for (char ch : key.toCharArray()) {
            hash = hash + (int) ch;
        }
        return hash % entries.length;
    }

    @Override
    public boolean containsKey(String key) {
        // TODO how about collissions?
        return entries[hash(key)] != null;
    }

    @Override
    public int countCollissions() {
        int count = 0;
        for (Entry e : entries) {
            if (e == null)
                continue;
            count += e.getCount() - 1;
        }
        return count;
    }

    @Override
    public void put(String key, Object value) {
        int hash = hash(key);
        if (entries[hash] == null) {
            entries[hash] = new Entry();
        }
        entries[hash].push(key, value);
        size++;
    }

    @Override
    public Object get(String key) {
        int hash = hash(key);
        Entry entry = entries[hash];
        if (entry == null) {
            return null;
        }

        int numKvs = entry.getCount();
        for (int i = 0; i < numKvs; i++) {
            KV kv = entry.elementAt(i);
            if (kv.getKey().equals(key)) {
                return kv.getValue();
            }
        }

        return null;
    }

    @Override
    public int getSize() {
        return size;
    }
    
    @Override
    public MyJavaScriptArray<String> keys() {
        MyJavaScriptArray<String> keys = new MyJavaScriptArray<>();
        for (Entry entry : entries) {
            if (entry == null) {
                continue;
            }

            for (int i = 0; i < entry.getCount(); i++) {
                KV kv = entry.elementAt(i);

                keys.push(kv.getKey());
            }
        }

        return keys;
    }

    @Getter
    @AllArgsConstructor
    private static class KV {
        private String key;
        private Object value;

        public String toString() {
            return key + "=" + value;
        }
    }

    private static class Entry {
        private MyJavaScriptArray<KV> kvs;

        Entry() {
            this.kvs = new MyJavaScriptArray<>(1);
        }

        void push(String key, Object value) {
            kvs.push(new KV(key, value));
        }

        int getCount() {
            return kvs.getCount();
        }

        KV elementAt(int index) {
            return kvs.elementAt(index);
        }
    }
}

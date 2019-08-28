package org.ravi.udemy.dsa;

public interface TheHashMap {
    boolean containsKey(String key);

    int countCollissions();

    void put(String key, Object value);

    Object get(String key);

    MyJavaScriptArray keys();

    int getSize();
}

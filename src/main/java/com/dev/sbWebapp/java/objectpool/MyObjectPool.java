package com.dev.sbWebapp.java.objectpool;

import java.util.LinkedList;
import java.util.Queue;

public class MyObjectPool {

    private final Queue<MyObject> pool = new LinkedList<>();
    private int counter = 0;
    private final int maxSize;

    public MyObjectPool(int maxSize) {
        this.maxSize = maxSize;
    }

    public MyObject borrowObject() {
        if(!pool.isEmpty()){
            return pool.poll();
        }
        if(counter < maxSize){
            return new MyObject(++counter);
        }
        throw new RuntimeException("풀에 더 이상 객체를 만들 수 없음");
    }

    public void returnObject(MyObject object) {
        pool.offer(object);
    }
}

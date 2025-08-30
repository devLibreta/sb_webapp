package com.dev.sbWebapp.java.objectpool;

import org.junit.jupiter.api.Test;

public class MyObjectPoolTest {

    @Test
    public void test() {
        MyObjectPool pool = new MyObjectPool(2);

        MyObject obj1 = pool.borrowObject();
        System.out.println("obj1 = " + obj1);

        pool.returnObject(obj1);

        MyObject obj2 = pool.borrowObject();

        System.out.println("obj2 = " + obj2);

        if(obj1 == obj2) {
            System.out.println("obj1 == obj2");
        } else {
            System.out.println("obj1 != obj2");
        }
    }
}

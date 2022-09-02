package com.example.userservice;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class LamdbaTest {

    @Test
    void consumer() {
        List<String> list = List.of("aaa","bbbb","ccc"); // 불변한 리스트 생성

        // 람다
        list.forEach(val -> System.out.println(val));

        // Method Reference
        list.forEach(System.out::println);
    }

    @Test
    void hello() {
        // 1. 익명클래스
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("LambdaTest.run");
            }
        });
        t.start();

        Thread t2 = new Thread(() -> System.out.println("Lambda Expression"));
        t2.start();
    }
}

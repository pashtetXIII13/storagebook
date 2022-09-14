package com.example.storebook.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author pashtet
 */
@SpringBootTest
class CounterTest {

    @Autowired
    Counter counter;

    @Test
    void getCountReaders() {
        System.out.println(counter.getCountReaders(345L));
    }
}
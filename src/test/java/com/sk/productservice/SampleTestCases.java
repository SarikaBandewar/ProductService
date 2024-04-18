package com.sk.productservice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SampleTestCases {
    @Test
    void testOnePlusOneIsTwo() {
        // arrange, act, assert

        int i = 1+1;
        System.out.println(i);
        assert i==2;
    }

    @Test
    void testMultiplication() {
        int i = 1*2;
        assert i==2;

        assertEquals(3, i, "ha ha, wrong answer");

    }
}

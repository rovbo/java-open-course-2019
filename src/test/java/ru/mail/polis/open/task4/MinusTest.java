package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//todo
class MinusTest {

    @Test
    void test() {
        assertEquals(
                -5,
                new Minus(
                        5
                ).evaluate()
        );
    }

}
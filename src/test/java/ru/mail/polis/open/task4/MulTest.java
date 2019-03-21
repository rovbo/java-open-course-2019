package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//todo
class MulTest {

    @Test
    void test() {
        assertEquals(
                10,
                new Mul(
                        new Const(5),
                        new Const(2)
                ).evaluate()
        );
    }

}
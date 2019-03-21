package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//todo
class PowTest {

    @Test
    void test() {
        assertEquals(
                32,
                new Pow(
                        new Const(2),
                        new Const(5)
                ).evaluate()
        );
    }

}
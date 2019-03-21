package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//todo
class SubTest {

    @Test
    void test() {
        assertEquals(
                -1,
                new Sub(
                        new Const(1),
                        new Const(2)
                ).evaluate()
        );
    }

}
package ru.mail.polis.open.task4;

public final class Minus implements Expr {

    private final int value;

    public Minus(int value) {
        this.value = -value;
    }

    @Override
    public int evaluate() {
        return value;
    }
}

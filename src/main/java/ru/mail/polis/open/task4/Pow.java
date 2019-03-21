package ru.mail.polis.open.task4;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class Pow implements Expr {

    private final @NotNull Expr left;
    private final @NotNull Expr right;

    public Pow(final @NotNull Expr left, final @NotNull Expr right) {
        this.left = Objects.requireNonNull(left);
        this.right = Objects.requireNonNull(right);
    }

    @Override
    public int evaluate() {
        return (int)Math.pow(left.evaluate(),right.evaluate());
    }
}
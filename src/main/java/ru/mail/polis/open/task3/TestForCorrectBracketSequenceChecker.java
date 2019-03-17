package ru.mail.polis.open.task3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestForCorrectBracketSequenceChecker {
    @Test
    void testCheckSequenceTrue() {
        assertTrue(CorrectBracketSequenceChecker.checkSequence(""));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("()"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("{}"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("[]"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("[]()(){}"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("[]{()}{{()}}()"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("({})"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("[{}]"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("[()]"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("{()}"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("({{[]}})"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("({{[]}})"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("({{[]{}}()})"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("({[](){{}{}()()}})"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("({[]([]){{}{[]}()()}}){}{()}"));
    }

    @Test
    void testCheckSequenceFalse() {
        assertFalse(CorrectBracketSequenceChecker.checkSequence("({}"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("(({}"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("(}"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("(]"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("({}{{({)}{)[(])"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("([[[{}"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("([[[]]]))"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("([[[{}({}]{)"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("([{}"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("}"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("([{)]}"));
    }

    @Test
    void testThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, ()->CorrectBracketSequenceChecker.checkSequence("(a)"));

        assertThrows(IllegalArgumentException.class, ()->CorrectBracketSequenceChecker.checkSequence("()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()"));
    }

    @Test
    void testGetSuccessChecksCount() {
        CorrectBracketSequenceChecker.checkSequence("(())");
        assertEquals(2, CorrectBracketSequenceChecker.getSuccessChecksCount());

        CorrectBracketSequenceChecker.checkSequence("{(({}[]))}");
        assertEquals(5, CorrectBracketSequenceChecker.getSuccessChecksCount());

        CorrectBracketSequenceChecker.checkSequence("{}");
        assertEquals(1, CorrectBracketSequenceChecker.getSuccessChecksCount());

        CorrectBracketSequenceChecker.checkSequence("({{[]}})");
        assertEquals(4, CorrectBracketSequenceChecker.getSuccessChecksCount());

        CorrectBracketSequenceChecker.checkSequence("");
        assertEquals(1, CorrectBracketSequenceChecker.getSuccessChecksCount());
    }

    @Test
    void testGetFailChecksCount() {
        CorrectBracketSequenceChecker.checkSequence("(}");
        assertEquals(1, CorrectBracketSequenceChecker.getFailChecksCount());

        CorrectBracketSequenceChecker.checkSequence("(}}");
        assertEquals(1, CorrectBracketSequenceChecker.getFailChecksCount());

        CorrectBracketSequenceChecker.checkSequence("(((}}");
        assertEquals(1, CorrectBracketSequenceChecker.getFailChecksCount());

        CorrectBracketSequenceChecker.checkSequence("()}}");
        assertEquals(2, CorrectBracketSequenceChecker.getFailChecksCount());

        CorrectBracketSequenceChecker.checkSequence("[{(])}}");
        assertEquals(1, CorrectBracketSequenceChecker.getFailChecksCount());
    }

    @Test
    void testGetLastSuccessSequence() {
        CorrectBracketSequenceChecker.checkSequence("()");
        assertEquals("()", CorrectBracketSequenceChecker.getLastSuccessSequence());

        CorrectBracketSequenceChecker.checkSequence("()}");
        assertEquals("()", CorrectBracketSequenceChecker.getLastSuccessSequence());
    }
}
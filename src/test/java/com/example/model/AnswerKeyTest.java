package com.example.model;

import org.example.model.AnswerKey;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnswerKeyTest {

    @Test
    void testParameterizedConstructor() {
        AnswerKey answerKey = new AnswerKey(1, "A");
        assertEquals(1, answerKey.getQuestionNumber());
        assertEquals("A", answerKey.getAnswer());
    }

    @Test
    void testSetAnswer() {
        AnswerKey answerKey = new AnswerKey(1, "A");
        answerKey.setAnswer("B");
        assertEquals("B", answerKey.getAnswer());
    }

    @Test
    void testSetAnswerNull() {
        AnswerKey answerKey = new AnswerKey(1, "A");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            answerKey.setAnswer(null);
        });
        assertEquals("Ответ не может быть null", exception.getMessage());
    }

    @Test
    void testToString() {
        AnswerKey answerKey = new AnswerKey(1, "A");
        String expected = "AnswerKey{questionNumber=1, answer='A'}";
        assertEquals(expected, answerKey.toString());
    }

    @Test
    void testEquals() {
        AnswerKey answerKey1 = new AnswerKey(1, "A");
        AnswerKey answerKey2 = new AnswerKey(1, "A");
        AnswerKey answerKey3 = new AnswerKey(2, "B");

        assertEquals(answerKey1, answerKey2);
        assertNotEquals(answerKey1, answerKey3);
        assertNotEquals(answerKey1, null);
        assertNotEquals(answerKey1, new Object());
    }

    @Test
    void testHashCode() {
        AnswerKey answerKey1 = new AnswerKey(1, "A");
        AnswerKey answerKey2 = new AnswerKey(1, "A");

        assertEquals(answerKey1.hashCode(), answerKey2.hashCode());
    }
}
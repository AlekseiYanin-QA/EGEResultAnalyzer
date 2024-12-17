package com.example.processor;

import org.example.model.AnswerKey;
import org.example.processor.ResultsProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ResultsProcessorTest {

    @Spy
    private ResultsProcessor resultsProcessor = new ResultsProcessor(10, 20, 30);

    @Mock
    private AnswerKey answerKey1, answerKey2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Мокируем поведение для ключей ответов
        when(answerKey1.getQuestionNumber()).thenReturn(1);
        when(answerKey1.getAnswer()).thenReturn("A");
        when(answerKey2.getQuestionNumber()).thenReturn(2);
        when(answerKey2.getAnswer()).thenReturn("B");
    }

    @ParameterizedTest
    @CsvSource({
            "A, B, 20", // Оба ответа правильные
            "B, A, 0",  // Оба ответа неправильные
            "A, D, 10"  // Один правильный, один неправильный
    })
    public void testCalculateTotalScore_Parameterized(String answer1, String answer2, int expectedScore) {
        // Arrange
        List<AnswerKey> expectedKeys = Arrays.asList(answerKey1, answerKey2);
        List<AnswerKey> actualAnswers = Arrays.asList(
                new AnswerKey(1, answer1),
                new AnswerKey(2, answer2)
        );

        // Act
        int totalScore = resultsProcessor.calculateTotalScore(expectedKeys, actualAnswers);

        // Assert
        assertEquals(expectedScore, totalScore);
    }

    @Test
    public void testCalculateTotalScore_UnansweredQuestions() {
        // Arrange
        List<AnswerKey> expectedKeys = List.of(answerKey1);
        List<AnswerKey> actualAnswers = List.of(); // Нет ответов

        // Act
        int totalScore = resultsProcessor.calculateTotalScore(expectedKeys, actualAnswers);

        // Assert
        assertEquals(0, totalScore); // Нет предоставленных ответов
    }

    @Test
    public void testCalculateTotalScore_AllIncorrectAnswers() {
        // Arrange
        List<AnswerKey> expectedKeys = Arrays.asList(answerKey1, answerKey2);
        List<AnswerKey> actualAnswers = Arrays.asList(
                new AnswerKey(1, "C"), // Неправильный ответ
                new AnswerKey(2, "D")  // Неправильный ответ
        );

        // Act
        int totalScore = resultsProcessor.calculateTotalScore(expectedKeys, actualAnswers);

        // Assert
        assertEquals(0, totalScore); // Все ответы неправильные
    }

    @Test
    public void testCalculateTotalScore_PartiallyCorrectAnswers() {
        // Arrange
        List<AnswerKey> expectedKeys = Arrays.asList(answerKey1, answerKey2);
        List<AnswerKey> actualAnswers = Arrays.asList(
                new AnswerKey(1, "A"), // Правильный ответ
                new AnswerKey(2, "C")  // Неправильный ответ
        );

        // Act
        int totalScore = resultsProcessor.calculateTotalScore(expectedKeys, actualAnswers);

        // Assert
        assertEquals(10, totalScore); // Только первый вопрос правильный
    }
}
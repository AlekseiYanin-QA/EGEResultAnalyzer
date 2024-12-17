package com.example;

import org.example.EgeProcessingApp;
import org.example.model.AnswerKey;
import org.example.processor.ResultsProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EgeProcessingAppTest {

    @Mock
    private ResultsProcessor processor;

    @InjectMocks
    private EgeProcessingApp app;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testReadFile_ValidFile() throws IOException {
        List<AnswerKey> result = EgeProcessingApp.readFile("answers.txt");
        assertEquals(10, result.size()); // Проверяем, что считано 10 ответов
    }

    @Test
    public void testCalculateTotalScore() {
        // Подготовка данных
        List<AnswerKey> keyList = new ArrayList<>();
        List<AnswerKey> answerList = new ArrayList<>();

        keyList.add(new AnswerKey(1, "A"));
        keyList.add(new AnswerKey(2, "B"));
        answerList.add(new AnswerKey(1, "A"));
        answerList.add(new AnswerKey(2, "C"));

        // Настройка мока
        when(processor.calculateTotalScore(keyList, answerList)).thenReturn(1);

        // Вызов метода
        int totalScore = processor.calculateTotalScore(keyList, answerList);

        // Проверка результата
        assertEquals(1, totalScore);
    }
}
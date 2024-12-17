package org.example.processor;

import org.example.model.AnswerKey;
import org.example.processor.ResultsProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ResultsProcessorTest {
    @Autowired
    private ResultsProcessor resultsProcessor;
    private List keyList;
    private List answerList;

    @BeforeEach
    public void setUp() {
        keyList = new ArrayList<>();
        answerList = new ArrayList<>();
        AnswerKey key1 = new AnswerKey();
        key1.setQuestionNumber(1);
        key1.setAnswer("A");
        keyList.add(key1);
        AnswerKey key2 = new AnswerKey();
        key2.setQuestionNumber(2);
        key2.setAnswer("B");
        keyList.add(key2);
        AnswerKey answer1 = new AnswerKey();
        answer1.setQuestionNumber(1);
        answer1.setAnswer("A");
        answerList.add(answer1);
        AnswerKey answer2 = new AnswerKey();
        answer2.setQuestionNumber(2);
        answer2.setAnswer("C");
        answerList.add(answer2);
    }

    @Test
    public void testCalculateTotalScore() {
        int totalScore = resultsProcessor.calculateTotalScore(keyList, answerList);
        assertEquals(1, totalScore); // Ожидаем 1 балл за правильный ответ на вопрос 1
    }
}

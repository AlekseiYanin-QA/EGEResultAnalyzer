package org.example.processor;

import org.example.model.AnswerKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ResultsProcessor {

    private final int scoreGroup1; // Оценка для заданий 1-4
    private final int scoreGroup2; // Оценка для заданий 5-8
    private final int scoreGroup3; // Оценка для заданий 9-10

    public ResultsProcessor(@Value("${score.group1}") int scoreGroup1,
                            @Value("${score.group2}") int scoreGroup2,
                            @Value("${score.group3}") int scoreGroup3) {
        this.scoreGroup1 = scoreGroup1;
        this.scoreGroup2 = scoreGroup2;
        this.scoreGroup3 = scoreGroup3;
    }

    public int calculateTotalScore(List<AnswerKey> keyList, List<AnswerKey> answerList) {
        if (keyList == null || answerList == null) {
            throw new IllegalArgumentException("Список ключей и список ответов не должны быть равны null.");
        }

        Map<Integer, String> answerMap = answerList.stream()
                .collect(Collectors.toMap(AnswerKey::getQuestionNumber, AnswerKey::getAnswer));

        return keyList.stream()
                .filter(key -> Optional.ofNullable(answerMap.get(key.getQuestionNumber()))
                        .map(answer -> answer.equals(key.getAnswer()))
                        .orElse(false))
                .mapToInt(key -> getScoreForAnswer(key.getQuestionNumber()))
                .sum();
    }

    private int getScoreForAnswer(int questionNumber) {
        switch (questionNumber) {
            case 1: case 2: case 3: case 4:
                return scoreGroup1; // Задания 1-4
            case 5: case 6: case 7: case 8:
                return scoreGroup2; // Задания 5-8
            case 9: case 10:
                return scoreGroup3; // Задания 9-10
            default:
                return 0; // Неизвестный номер задания
        }
    }
}
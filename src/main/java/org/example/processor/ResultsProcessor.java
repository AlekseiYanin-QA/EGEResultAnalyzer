package org.example.processor;

import org.example.model.AnswerKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResultsProcessor {


    @Value("${score.group1}")
    private int scoreGroup1;

    @Value("${score.group2}")
    private int scoreGroup2;

    @Value("${score.group3}")
    private int scoreGroup3;

    public ResultsProcessor(int scoreGroup1, int scoreGroup2, int scoreGroup3) {
        this.scoreGroup1 = scoreGroup1;
        this.scoreGroup2 = scoreGroup2;
        this.scoreGroup3 = scoreGroup3;
    }

    public int calculateTotalScore(List<AnswerKey> keyList, List<AnswerKey> answerList) {
        int totalScore = 0;

        for (AnswerKey key : keyList) {
            for (AnswerKey answer : answerList) {
                if (key.getQuestionNumber() == answer.getQuestionNumber()) {
                    if (key.getAnswer().equals(answer.getAnswer())) {
                        totalScore += getScoreForAnswer(key.getQuestionNumber());
                    }
                }
            }
        }
        return totalScore;
    }

    private int getScoreForAnswer(int questionNumber) {
        switch (questionNumber) {
            case 1: return scoreGroup1;
            case 2: return scoreGroup2;
            case 3: return scoreGroup3;

            default: return 0;
        }
    }
}
package org.example.model;

import java.util.Objects;

public class AnswerKey {
    private int questionNumber;
    private String answer;

    public AnswerKey() {}

    public AnswerKey(int questionNumber, String answer) {
        this.questionNumber = questionNumber;
        this.answer = answer;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        if (answer == null) {
            throw new IllegalArgumentException("Ответ не может быть null");
        }
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "AnswerKey{" +
                "questionNumber=" + questionNumber +
                ", answer='" + answer + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnswerKey that)) return false;
        return questionNumber == that.questionNumber && Objects.equals(answer, that.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionNumber, answer);
    }
}

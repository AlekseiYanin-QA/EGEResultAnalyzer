package org.example.model;

import java.util.Objects;

public class AnswerKey {
    private final int questionNumber;
    private String answer;

    public AnswerKey(int questionNumber, String answer) {
        this.questionNumber = questionNumber;
        setAnswer(answer); // Используем сеттер для валидации
    }

    public int getQuestionNumber() {
        return questionNumber;
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
        return String.format("AnswerKey{questionNumber=%d, answer='%s'}", questionNumber, answer);
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
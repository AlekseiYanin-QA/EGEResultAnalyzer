package org.example;

import org.example.config.AppConfig;
import org.example.model.AnswerKey;
import org.example.processor.ResultsProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class EgeProcessingApp {

    private static final Logger logger = LoggerFactory.getLogger(EgeProcessingApp.class);

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(AppConfig.class, args);
        ResultsProcessor processor = context.getBean(ResultsProcessor.class);

        try {
            List<AnswerKey> keyList = readFile("keys.txt");
            List<AnswerKey> answerList = readFile("answers.txt");

            if (keyList.isEmpty() || answerList.isEmpty()) {
                logger.error("Один из файлов пуст: keys.txt или answers.txt");
                return;
            }

            int totalScore = processor.calculateTotalScore(keyList, answerList);
            logger.info("Общее количество баллов: {}", totalScore);
        } catch (IOException e) {
            logger.error("Ошибка при чтении файла: {}", e.getMessage(), e);
        }
    }

    public static List<AnswerKey> readFile(String fileName) throws IOException {
        List<AnswerKey> result = new ArrayList<>();
        InputStream inputStream = EgeProcessingApp.class.getResourceAsStream("/" + fileName);

        if (inputStream == null) {
            throw new FileNotFoundException("Файл не найден: " + fileName);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // Пропуск пустых строк

                String[] parts = line.split("-");
                if (parts.length != 2) {
                    logger.warn("Неверный формат строки: {}", line);
                    continue;
                }
                try {
                    AnswerKey answerKey = new AnswerKey();
                    answerKey.setQuestionNumber(Integer.parseInt(parts[0].trim()));
                    answerKey.setAnswer(parts[1].trim());
                    result.add(answerKey);
                } catch (NumberFormatException e) {
                    logger.error("Ошибка при парсинге номера вопроса из строки: {}", line, e);
                }
            }
            logger.info("Чтение файла: {}", fileName);
        }
        return result;
    }
}
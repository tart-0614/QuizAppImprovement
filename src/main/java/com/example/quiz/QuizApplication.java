package com.example.quiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Quizアプリケーション：起動クラス
 */
@SpringBootApplication
public class QuizApplication {

    public static void main(String[] args) {
        // Webアプリケーションとして起動
        SpringApplication.run(QuizApplication.class, args);
    }
}
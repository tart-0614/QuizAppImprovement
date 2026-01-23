package com.example.quiz;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.quiz.entity.Quiz;

/**
 * Quizテーブル：リポジトリ
 */
public interface QuizRepository extends CrudRepository<Quiz, Integer> {
    
    /** * クイズをランダムで1件取得する（PostgreSQL用のSQL）
     */
    @Query("SELECT * FROM quiz ORDER BY RANDOM() LIMIT 1")
    Optional<Quiz> findRandomQuiz();
}
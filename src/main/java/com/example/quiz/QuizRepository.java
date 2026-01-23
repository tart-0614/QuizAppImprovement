package com.example.quiz;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.quiz.entity.Quiz;

public interface QuizRepository extends CrudRepository<Quiz, Integer> {
	
	/** クイズ情報をランダムで1件取得する */
	@Query("SELECT * FROM quiz ORDER BY RANDOM() LIMIT 1")
	Quiz getRandomQuiz();
}
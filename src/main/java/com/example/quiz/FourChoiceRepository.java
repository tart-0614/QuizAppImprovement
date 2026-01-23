package com.example.quiz;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.quiz.entity.FourChoiceQuiz;

public interface FourChoiceRepository extends CrudRepository<FourChoiceQuiz, Integer> {
    
    @Query("SELECT * FROM four_choice_quiz ORDER BY RANDOM() LIMIT 1")
    FourChoiceQuiz getRandomQuiz();
}
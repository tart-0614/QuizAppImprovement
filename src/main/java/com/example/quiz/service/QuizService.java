package com.example.quiz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.example.quiz.entity.FourChoiceQuiz;
import com.example.quiz.entity.Quiz;

public interface QuizService {
    List<Object> selectTenRandomQuizzes(String type);

    Iterable<Quiz> selectAll();
    Optional<Quiz> selectOneById(Integer id);
    Optional<Quiz> selectOneRandomQuiz();
    Boolean checkQuiz(Integer id, Boolean myAnswer);
    void insertQuiz(Quiz quiz);
    void updateQuiz(Quiz quiz);
    void deleteQuizById(Integer id);
    Iterable<FourChoiceQuiz> selectAllFourChoice();
    Optional<FourChoiceQuiz> selectOneFourChoiceById(Integer id);
    Optional<FourChoiceQuiz> selectOneFourChoiceRandom();
    void insertFourChoice(FourChoiceQuiz quiz);
    void updateFourChoice(FourChoiceQuiz quiz);
    void deleteFourChoiceById(Integer id);
    void insertByCsv(MultipartFile file);
}
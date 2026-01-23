package com.example.quiz.service;

import java.util.Optional;

import com.example.quiz.entity.Quiz;

public interface QuizService {
    /** クイズ情報を全件取得します */
    Iterable<Quiz> selectAll();
    
    Optional<Quiz> selectOneById(Integer id);
    /** クイズ情報をランダムで1件取得します */
    Optional<Quiz> selectOneRandomQuiz();
    
    Boolean checkQuiz(Integer id, Boolean myAnawer);
    /** クイズ情報を保存（登録・更新）します */
    void insertQuiz(Quiz quiz);
    
    /** クイズ情報を更新します */
    void updateQuiz(Quiz quiz);
    
    /** クイズ情報を削除します */
    void deleteQuizById(Integer id);
}
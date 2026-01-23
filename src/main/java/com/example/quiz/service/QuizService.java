package com.example.quiz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.example.quiz.entity.FourChoiceQuiz;
import com.example.quiz.entity.Quiz;

public interface QuizService {
    /** 2択クイズ：全件取得 */
    Iterable<Quiz> selectAll();
    /** 2択クイズ：1件取得 */
    Optional<Quiz> selectOneById(Integer id);
    /** 2択クイズ：ランダム取得 */
    Optional<Quiz> selectOneRandomQuiz();
    /** 2択クイズ：正解判定 */
    Boolean checkQuiz(Integer id, Boolean myAnswer);
    /** 2択クイズ：登録 */
    void insertQuiz(Quiz quiz);
    /** 2択クイズ：更新 */
    void updateQuiz(Quiz quiz);
    /** 2択クイズ：削除 */
    void deleteQuizById(Integer id);

    /** 4択クイズ：全件取得 */
    Iterable<FourChoiceQuiz> selectAllFourChoice();
    /** 4択クイズ：1件取得 */
    Optional<FourChoiceQuiz> selectOneFourChoiceById(Integer id);
    /** 4択クイズ：ランダム取得 */
    Optional<FourChoiceQuiz> selectOneFourChoiceRandom();
    /** 4択クイズ：登録 */
    void insertFourChoice(FourChoiceQuiz fourChoiceQuiz);
    /** 4択クイズ：更新 */
    void updateFourChoice(FourChoiceQuiz fourChoiceQuiz);
    /** 4択クイズ：削除 */
    void deleteFourChoiceById(Integer id);

    /** 10問連続プレイ用：ランダム選出 */
    List<Object> selectTenRandomQuizzes();

    /** CSVから一括登録（今回追加） */
    void insertByCsv(MultipartFile file);
}
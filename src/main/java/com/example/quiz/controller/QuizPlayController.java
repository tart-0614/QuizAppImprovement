package com.example.quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.quiz.entity.FourChoiceQuiz;
import com.example.quiz.entity.Quiz;
import com.example.quiz.form.PlayForm;
import com.example.quiz.service.QuizService;

@Controller
@RequestMapping("/play")
@SessionAttributes("playForm") // セッションでデータを保持
public class QuizPlayController {

    @Autowired
    QuizService service;

    /** プレイ開始：10問セットして最初の問題へ */
    @GetMapping("/setup")
    public String setup(Model model) {
        PlayForm playForm = new PlayForm();
        playForm.setQuizzes(service.selectTenRandomQuizzes());
        playForm.setCurrentIdx(0);
        playForm.setScore(0);
        
        model.addAttribute("playForm", playForm);
        return "redirect:/play/show";
    }

    /** 問題表示 */
    @GetMapping("/show")
    public String show(PlayForm playForm, Model model) {
        Object quiz = playForm.getQuizzes().get(playForm.getCurrentIdx());
        model.addAttribute("quiz", quiz);
        
        // クラスの種類によって表示するHTMLを切り分ける
        if (quiz instanceof Quiz) {
            return "play_two_choice";
        } else {
            return "play_four_choice";
        }
    }
    
    /** 回答を判定し、次の問題へ進むか結果画面へ行くかを制御します */
    @PostMapping("/answer")
    public String answer(Integer answer, PlayForm playForm, Model model) {
        // 現在の問題を取得
        Object quiz = playForm.getQuizzes().get(playForm.getCurrentIdx());
        
        // 正解判定
        boolean isCorrect = false;
        if (quiz instanceof Quiz) {
            // 2択の場合（trueなら1、falseなら0として送られてくる想定）
            boolean myAns = (answer == 1);
            isCorrect = ((Quiz) quiz).getAnswer().equals(myAns);
        } else {
            // 4択の場合
            isCorrect = ((FourChoiceQuiz) quiz).getAnswerNumber().equals(answer);
        }
        
        // 正解ならスコアを加算
        if (isCorrect) {
            playForm.setScore(playForm.getScore() + 1);
        }
        
        // 次の問題があるかチェック
        playForm.setCurrentIdx(playForm.getCurrentIdx() + 1);
        
        if (playForm.getCurrentIdx() < playForm.getQuizzes().size()) {
            // 次の問題へ
            return "redirect:/play/show";
        } else {
            // 全問終了：リザルト画面へ
            return "play_result";
        }
    }
}
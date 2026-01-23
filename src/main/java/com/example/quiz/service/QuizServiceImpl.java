package com.example.quiz.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.quiz.QuizRepository;
import com.example.quiz.entity.Quiz;

@Service
@Transactional
public class QuizServiceImpl implements QuizService {

    @Autowired
    QuizRepository repository;

    @Override
    public Iterable<Quiz> selectAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Quiz> selectOneById(Integer id) {
        // 更新画面で特定のデータを表示するために必要です
        return repository.findById(id);
    }

    @Override
    public Optional<Quiz> selectOneRandomQuiz() {
        return repository.findRandomQuiz();
    }

    @Override
    public Boolean checkQuiz(Integer id, Boolean myAnswer) {
        // 解答判定機能（図12.20用）
        Optional<Quiz> optQuiz = repository.findById(id);
        if (optQuiz.isPresent()) {
            Quiz quiz = optQuiz.get();
            return quiz.getAnswer().equals(myAnswer);
        }
        return false;
    }

    @Override
    public void insertQuiz(Quiz quiz) {
        repository.save(quiz);
    }

    @Override
    public void updateQuiz(Quiz quiz) {
        repository.save(quiz);
    }

    @Override
    public void deleteQuizById(Integer id) {
        repository.deleteById(id);
    }
}
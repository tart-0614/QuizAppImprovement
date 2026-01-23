package com.example.quiz.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.quiz.FourChoiceRepository;
import com.example.quiz.QuizRepository;
import com.example.quiz.entity.FourChoiceQuiz;
import com.example.quiz.entity.Quiz;

@Service
@Transactional
public class QuizServiceImpl implements QuizService {

    @Autowired
    QuizRepository repository;

    @Autowired
    FourChoiceRepository fourChoiceRepository;

    // --- 2択用の実装 ---
    @Override
    public Iterable<Quiz> selectAll() { return repository.findAll(); }
    @Override
    public Optional<Quiz> selectOneById(Integer id) { return repository.findById(id); }
    @Override
    public Optional<Quiz> selectOneRandomQuiz() { return Optional.ofNullable(repository.getRandomQuiz()); }
    @Override
    public Boolean checkQuiz(Integer id, Boolean myAnswer) {
        Optional<Quiz> optQuiz = repository.findById(id);
        if (optQuiz.isPresent()) {
            return optQuiz.get().getAnswer().equals(myAnswer);
        }
        return false;
    }
    @Override
    public void insertQuiz(Quiz quiz) { repository.save(quiz); }
    @Override
    public void updateQuiz(Quiz quiz) { repository.save(quiz); }
    @Override
    public void deleteQuizById(Integer id) { repository.deleteById(id); }

    // --- 4択用の実装 ---
    @Override
    public Iterable<FourChoiceQuiz> selectAllFourChoice() { return fourChoiceRepository.findAll(); }
    @Override
    public Optional<FourChoiceQuiz> selectOneFourChoiceById(Integer id) { return fourChoiceRepository.findById(id); }
    @Override
    public Optional<FourChoiceQuiz> selectOneFourChoiceRandom() { return Optional.ofNullable(fourChoiceRepository.getRandomQuiz()); }
    @Override
    public void insertFourChoice(FourChoiceQuiz quiz) { fourChoiceRepository.save(quiz); }
    @Override
    public void updateFourChoice(FourChoiceQuiz quiz) { fourChoiceRepository.save(quiz); }
    @Override
    public void deleteFourChoiceById(Integer id) { fourChoiceRepository.deleteById(id); }

    // --- 10問プレイ用のロジック ---
    @Override
    public List<Object> selectTenRandomQuizzes() {
        List<Object> allQuizzes = new ArrayList<>();
        repository.findAll().forEach(allQuizzes::add);
        fourChoiceRepository.findAll().forEach(allQuizzes::add);
        Collections.shuffle(allQuizzes);
        int limit = Math.min(allQuizzes.size(), 10);
        return allQuizzes.subList(0, limit);
    }

    // --- CSV一括登録の実装 (今回追加) ---
    @Override
    public void insertByCsv(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // 空行をスキップ
                if (line.trim().isEmpty()) continue;

                String[] data = line.split(",");
                if (data.length == 6) {
                    FourChoiceQuiz quiz = new FourChoiceQuiz();
                    quiz.setQuestion(data[0]);
                    quiz.setChoice1(data[1]);
                    quiz.setChoice2(data[2]);
                    quiz.setChoice3(data[3]);
                    quiz.setChoice4(data[4]);
                    // 数値変換。空白を考慮してtrim()を使用
                    quiz.setAnswerNumber(Integer.parseInt(data[5].trim()));
                    
                    fourChoiceRepository.save(quiz);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("CSVの読み取り中にエラーが発生しました", e);
        }
    }
}
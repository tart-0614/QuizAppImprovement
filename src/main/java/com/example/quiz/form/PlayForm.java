package com.example.quiz.form;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class PlayForm implements Serializable {
    private List<Object> quizzes; // 2択と4択が混ざるためObject型で保持
    private int currentIdx;       // 現在何問目か (0〜9)
    private int score;            // 合計正解数
}
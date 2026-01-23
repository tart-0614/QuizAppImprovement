package com.example.quiz.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("four_choice_quiz")
public class FourChoiceQuiz {
    @Id
    private Integer id;

    private String question;

    @Column("choice_1")
    private String choice1;

    @Column("choice_2")
    private String choice2;

    @Column("choice_3")
    private String choice3;

    @Column("choice_4")
    private String choice4;

    @Column("answer_number")
    private Integer answerNumber;
}
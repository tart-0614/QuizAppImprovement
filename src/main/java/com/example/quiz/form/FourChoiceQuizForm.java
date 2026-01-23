package com.example.quiz.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FourChoiceQuizForm {
    /** 識別ID */
    private Integer id;

    /** クイズの内容 */
    @NotBlank(message = "問題文を入力してください")
    private String question;

    /** 選択肢1 */
    @NotBlank(message = "選択肢1を入力してください")
    private String choice1;

    /** 選択肢2 */
    @NotBlank(message = "選択肢2を入力してください")
    private String choice2;

    /** 選択肢3 */
    @NotBlank(message = "選択肢3を入力してください")
    private String choice3;

    /** 選択肢4 */
    @NotBlank(message = "選択肢4を入力してください")
    private String choice4;

    /** 正解番号 */
    @NotNull(message = "正解番号を選択してください")
    private Integer answerNumber;
}
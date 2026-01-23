package com.example.quiz.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile; // CSVアップロードに必要
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.quiz.entity.FourChoiceQuiz;
import com.example.quiz.form.FourChoiceQuizForm;
import com.example.quiz.service.QuizService;

@Controller
@RequestMapping("/fourChoiceQuiz")
public class FourChoiceQuizController {

    @Autowired
    QuizService service;

    /** フォームの初期化 */
    @ModelAttribute
    public FourChoiceQuizForm setUpForm() {
        return new FourChoiceQuizForm();
    }

    /** 一覧画面の表示 */
    @GetMapping
    public String index(Model model) {
        model.addAttribute("list", service.selectAllFourChoice());
        return "four_choice_quiz_index";
    }

    /** 1件登録 */
    @PostMapping("/insert")
    public String insert(@Validated FourChoiceQuizForm form, BindingResult result, 
                         Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            // エラーがある場合は一覧表示処理を挟んで戻す
            model.addAttribute("list", service.selectAllFourChoice());
            return "four_choice_quiz_index";
        }
        FourChoiceQuiz quiz = new FourChoiceQuiz();
        quiz.setQuestion(form.getQuestion());
        quiz.setChoice1(form.getChoice1());
        quiz.setChoice2(form.getChoice2());
        quiz.setChoice3(form.getChoice3());
        quiz.setChoice4(form.getChoice4());
        quiz.setAnswerNumber(form.getAnswerNumber());

        service.insertFourChoice(quiz);
        redirectAttributes.addFlashAttribute("complete", "登録が完了しました");
        return "redirect:/fourChoiceQuiz";
    }

    /** CSV一括登録 */
    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "ファイルを選択してください");
            return "redirect:/fourChoiceQuiz";
        }
        
        service.insertByCsv(file);
        redirectAttributes.addFlashAttribute("complete", "CSVからの登録が完了しました");
        return "redirect:/fourChoiceQuiz";
    }

    /** 編集画面の表示 */
    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable Integer id, Model model) {
        Optional<FourChoiceQuiz> opt = service.selectOneFourChoiceById(id);
        if (opt.isPresent()) {
            FourChoiceQuiz quiz = opt.get();
            FourChoiceQuizForm form = new FourChoiceQuizForm();
            form.setId(quiz.getId());
            form.setQuestion(quiz.getQuestion());
            form.setChoice1(quiz.getChoice1());
            form.setChoice2(quiz.getChoice2());
            form.setChoice3(quiz.getChoice3());
            form.setChoice4(quiz.getChoice4());
            form.setAnswerNumber(quiz.getAnswerNumber());
            model.addAttribute("fourChoiceQuizForm", form);
        }
        return "four_choice_quiz_edit";
    }

    /** 更新処理 */
    @PostMapping("/update")
    public String update(@Validated FourChoiceQuizForm form, BindingResult result, 
                         Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "four_choice_quiz_edit";
        }
        FourChoiceQuiz quiz = new FourChoiceQuiz();
        quiz.setId(form.getId());
        quiz.setQuestion(form.getQuestion());
        quiz.setChoice1(form.getChoice1());
        quiz.setChoice2(form.getChoice2());
        quiz.setChoice3(form.getChoice3());
        quiz.setChoice4(form.getChoice4());
        quiz.setAnswerNumber(form.getAnswerNumber());

        service.updateFourChoice(quiz);
        redirectAttributes.addFlashAttribute("complete", "更新が完了しました");
        return "redirect:/fourChoiceQuiz";
    }

    /** 削除処理 */
    @PostMapping("/delete")
    public String delete(@RequestParam Integer id, RedirectAttributes redirectAttributes) {
        service.deleteFourChoiceById(id);
        redirectAttributes.addFlashAttribute("complete", "削除が完了しました");
        return "redirect:/fourChoiceQuiz";
    }
}
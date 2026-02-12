package com.example.quiz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * トップページ用コントローラー
 */
@Controller
public class MainController {

    /**
     * トップ画面を表示
     */
    @GetMapping("/")
    public String index() {
        // templates/index.html を呼び出す
        return "index";
    }
}
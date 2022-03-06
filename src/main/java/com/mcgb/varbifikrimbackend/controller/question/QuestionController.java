package com.mcgb.varbifikrimbackend.controller.question;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/question")
public class QuestionController {

    @PostMapping("/create")
    public ResponseEntity createNewQuestion() {
        return null;
    }

    @PutMapping("/update")
    public ResponseEntity updateQuestion() {
        return null;
    }
}

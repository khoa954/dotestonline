package com.example.dotestonline.controller;

import com.example.dotestonline.dto.*;
import com.example.dotestonline.service.*;
import com.example.dotestonline.utils.SD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@PreAuthorize("hasRole('USER')")
@RequestMapping("user")
public class UserController {
    private QuestionService questionService;
    private AccountService accountService;
    private QuizService quizService;
    private AccountQuizService accountQuizService;
    private QuestionQuizService questionQuizService;

    @Autowired
    public UserController(QuestionService questionService, AccountService accountService, QuizService quizService, AccountQuizService accountQuizService, QuestionQuizService questionQuizService) {
        this.questionService = questionService;
        this.accountService = accountService;
        this.quizService = quizService;
        this.accountQuizService = accountQuizService;
        this.questionQuizService = questionQuizService;
    }


    @GetMapping("")
    public String UserPage() {
        return "UserHome";
    }

    @PostMapping("quiz/generate")
    public String GenerateQuiz(@RequestParam("duration") String duration, @RequestParam("userName") String userName, @RequestParam("subject") String subject, Model model) {
        QuizDTO newQuizDTO = createQuiz(duration, userName, subject);

        QuizDTO quizDTO = quizService.createQuiz(newQuizDTO);

        List<QuestionDTO> newListQuestion = getQuestion(duration, subject);

        AccountDTO accountDTO = accountService.getOneAccountByUserName(userName);
        List<AccountQuizDTO> accountQuizDTOS = new ArrayList<>();
        AccountQuizDTO accountQuizDTO = new AccountQuizDTO();

        accountQuizDTO.setAccount(accountDTO);
        accountQuizDTO.setQuiz(quizDTO);
        accountQuizDTOS.add(accountQuizDTO);


        List<QuizQuestionDTO> quizQuestionDTOList = new ArrayList<>();

        for (QuestionDTO item : newListQuestion
        ) {
            QuizQuestionDTO quizQuestionDTO = new QuizQuestionDTO();
            quizQuestionDTO.setQuestion(item);
            quizQuestionDTO.setQuizQ(quizDTO);
            quizQuestionDTOList.add(quizQuestionDTO);

        }


        accountQuizService.createAccountQuiz(accountQuizDTO);
        questionQuizService.createMultipleQuizQuestion(quizQuestionDTOList);


        model.addAttribute("newQuiz", quizDTO);
        model.addAttribute("listQuestion", newListQuestion);
        return "Quiz";
    }

    public QuizDTO createQuiz(String duration, String userName, String subject) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        LocalDateTime now = LocalDateTime.now();
        String quizName = passwordEncoder.encode(now + "-" + userName + "-" + duration);
        QuizDTO newQuizDTO = new QuizDTO();
        newQuizDTO.setName(quizName);
        newQuizDTO.setDuration(duration);
        newQuizDTO.setSubject(subject);
        return newQuizDTO;
    }

    public List<QuestionDTO> getQuestion(String duration, String subject) {
        switch (duration) {
            case SD.QUIZ_DURATION_SHORT:
                return questionService.getQuestionWithShortDuration(subject);
            case SD.QUIZ_DURATION_MEDIUM:
                return questionService.getQuestionWithMediumDuration(subject);
            case SD.QUIZ_DURATION_LONG:
                return questionService.getQuestionWithLongDuration(subject);
            default:
                throw new IllegalStateException("Duration of Quiz must be short, medium, long");
        }
    }

    @PostMapping("/examine")
    public ResponseEntity<String> toExamine(@RequestBody Map<String, Object> requestData, Model model) {

        String userName = (String) requestData.get("userName");
        AccountDTO accountDTO = accountService.getOneAccountByUserName(userName);

        Long quizId = Long.parseLong((String) requestData.get("quizId"));

        AccountQuizDTO accountQuizDTO = accountQuizService.findAccountQuizByQuizIdAndAccountId(quizId, accountDTO.getId());

        List<String> questionListString = (List<String>) requestData.get("questionArray");
        List<Long> questionListLong = new ArrayList<>();
        for (String item : questionListString
        ) {
            Long temp=Long.parseLong(item);
            questionListLong.add(temp);
        }


        List<String> answerList = (List<String>) requestData.get("answerArray");
        List<QuestionDTO> list = questionService.getMultipleQuestionWithMultipleId(questionListLong);
        int score = 0;
        for (int i = 0; i < list.size(); i++) {
            for (AnswerDTO item : list.get(i).getAnswers()
            ) {
                if (item.isCorrect() && answerList.get(i).contains(item.getAnswerContent())) {
                    {
                        score++;
                    }
                }
            }
        }
        accountQuizDTO.setScore(String.valueOf(score));
        accountQuizService.updateAccountQuiz(accountQuizDTO);
        String finalResult=score+"/"+list.size();
        return ResponseEntity.ok("Your score is : "+finalResult);
    }
}

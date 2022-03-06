package com.mcgb.varbifikrimbackend.controller;

import com.mcgb.varbifikrimbackend.entity.*;
import com.mcgb.varbifikrimbackend.repository.QuestionRepository;
import com.mcgb.varbifikrimbackend.repository.SurveyRepository;
import com.mcgb.varbifikrimbackend.repository.UserMembershipRepository;
import com.mcgb.varbifikrimbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class TestCmdlineRunner implements CommandLineRunner {

    @Autowired
    SurveyRepository surveyRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMembershipRepository userMembershipRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        /*MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion();
        multipleChoiceQuestion.setOrder(1);
        multipleChoiceQuestion.setQuestionType(QuestionTypeEnum.MULTIPLE_CHOICE);
        multipleChoiceQuestion.setChoices(List.of("Choice1", "Choice2", "Choice3"));
        multipleChoiceQuestion.setScale(3);
        multipleChoiceQuestion.setTitle("Multiple Choice Question Title");

        User user = new User();
        user.setName("John");
        user.setSurname("Doe");
        user.setEmail("johndoe@mail.com");
        user.setUsername("johndoe");
        user.setPassword(passwordEncoder.encode("123"));
        user.setStatus(StatusEnum.ACTIVE);
        BasicMembership basicMembership = new BasicMembership();
        basicMembership.setNumberOfMonths(1);
        basicMembership.setMembershipType(MembershipTypeEnum.BASIC);
        UserMembership userMembership = new UserMembership();
        userMembership.setMembership(basicMembership);
        userMembershipRepository.insert(userMembership);
        user.setUserMembershipId(userMembership.getId());
        userRepository.insert(user);

        Survey survey = new Survey();
        survey.setSurveyType(SurveyTypeEnum.X);
        survey.setStartMessage("Hi");
        survey.setFinishMessage("Bye");
        survey.setQuestions(List.of(multipleChoiceQuestion));
        survey.setUserId(user.getId());
        Date today = new Date();
        survey.setRecordTime(today);
        surveyRepository.insert(survey);*/

        /*List<Survey> surveys = surveyRepository.findAllByUserId("61c251ca35233f54c645d836");
        for (Survey s: surveys) {
            System.out.println(s.getId());
            System.out.println(s.getStartMessage());
            System.out.println(s.getFinishMessage());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String date = simpleDateFormat.format(s.getRecordTime());
            System.out.println(date);

            s.getQuestions().stream().map(Question::getTitle).forEach(System.out::println);
        }*/

        /*User user = userRepository.findByUsernameAndStatus("johndoe", StatusEnum.ACTIVE);
        System.out.println(user.getId());*/
    }
}

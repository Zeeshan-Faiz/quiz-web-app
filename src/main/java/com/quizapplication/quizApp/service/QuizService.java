package com.quizapplication.quizApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quizapplication.quizApp.Dao.QuestionDao;
import com.quizapplication.quizApp.Dao.QuizDao;
import com.quizapplication.quizApp.model.Question;
import com.quizapplication.quizApp.model.QuestionWrapper;
import com.quizapplication.quizApp.model.Quiz;
import com.quizapplication.quizApp.model.Response;

@Service
public class QuizService {

	@Autowired
	QuizDao quizDao;
	
	@Autowired
	QuestionDao questionDao;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		
		List<Question> question = questionDao.findRandomQuestionByCategory(category,numQ);
		
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestion(question);
		
		quizDao.save(quiz);
		
		
		return new ResponseEntity<>("Success", HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		
		Optional<Quiz> quiz =  quizDao.findById(id);
		List<Question> questionsFromDb = quiz.get().getQuestion();
		List<QuestionWrapper> questionsForUser = new ArrayList<>();
		
		for(Question q : questionsFromDb) {
			QuestionWrapper qw = new QuestionWrapper(q.getId(), 
						q.getQuestionTitle(),q.getOption1(),q.getOption2(),
							q.getOption3(),q.getOption4());
			
			questionsForUser.add(qw);
		}
		
		return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		
		Quiz quiz = quizDao.findById(id).get();
		List<Question> questions = quiz.getQuestion();
		int i=0, score=0;
		
		for(Response response : responses) {
			if(response.getResponse().equals(questions.get(i).getRightAnswer()))
				score++;
			
			++i;
		}
		
		return new ResponseEntity<>(score, HttpStatus.OK);
	}

}

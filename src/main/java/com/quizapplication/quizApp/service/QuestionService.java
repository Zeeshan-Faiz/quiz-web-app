package com.quizapplication.quizApp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quizapplication.quizApp.Dao.QuestionDao;
import com.quizapplication.quizApp.model.Question;

@Service
public class QuestionService {
	
	@Autowired
	QuestionDao questionDao;

	public ResponseEntity<List<Question>> getAllQuestions() {
		try {
		return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
		
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
		
	}

	public List<Question> getQuestionsByCategory(String category) {
		
		return questionDao.findByCategory(category);
	}

	public ResponseEntity<String> addQuestion(Question question) {

		questionDao.save(question);
		try {
		return new ResponseEntity<>("Question Inserted Successfully", HttpStatus.CREATED);
		
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return new ResponseEntity<>("Something went Wrong!!!", HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> deleteQuestion(Integer id) {
		
		questionDao.deleteById(id);
		try {
		return new ResponseEntity<> ("Question Deleted Successfully", HttpStatus.ACCEPTED);
	
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>("Something went Wrong!!!", HttpStatus.BAD_REQUEST);
		
		
	}
	
}

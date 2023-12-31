package com.quizapplication.quizApp.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizapplication.quizApp.model.Quiz;

public interface QuizDao extends JpaRepository<Quiz, Integer>{

}

package com.quizapplication.quizApp.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Quiz {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	public String title;
	
	@ManyToMany
	public List<Question> question;

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setQuestion(List<Question> question) {
		this.question = question;
	}

	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public List<Question> getQuestion() {
		return question;
	}
	

}

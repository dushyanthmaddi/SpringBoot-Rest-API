package com.springboot.beans;

import java.util.List;

public class QuestionAndChoices {
	
	String question;
	List<String> choices;
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public List<String> getChoices() {
		return choices;
	}
	public void setChoices(List<String> choices) {
		this.choices = choices;
	}

}

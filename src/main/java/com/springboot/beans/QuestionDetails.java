package com.springboot.beans;

import java.util.List;

public class QuestionDetails {

	String question;
	String published_at;
	List<Choices> choices;
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getPublished_at() {
		return published_at;
	}
	public void setPublished_at(String published_at) {
		this.published_at = published_at;
	}
	public List<Choices> getChoices() {
		return choices;
	}
	public void setChoices(List<Choices> choices) {
		this.choices = choices;
	}
	
}

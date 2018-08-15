package com.springboot.service;

import java.util.List;

import org.codehaus.jettison.json.JSONException;

import com.springboot.beans.QuestionAndChoices;
import com.springboot.beans.QuestionDetails;

public interface IQuestionsService {

	List<QuestionDetails> getAllQuestions() throws JSONException;
	QuestionDetails createAQuestion(QuestionAndChoices questionAndChoices);
	QuestionDetails getQuestionById(int id);
}

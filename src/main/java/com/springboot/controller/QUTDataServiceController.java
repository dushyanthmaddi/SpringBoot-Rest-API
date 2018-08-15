package com.springboot.controller;

import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.beans.QuestionAndChoices;
import com.springboot.beans.QuestionDetails;
import com.springboot.service.IQuestionsService;

@RestController
@RequestMapping("/")
public class QUTDataServiceController {

	@Autowired
	private IQuestionsService iQuestionsService;

	//Fetch all questions
	@RequestMapping(value = "/questions", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public List<QuestionDetails> getQuestions() throws JSONException
	{
		return iQuestionsService.getAllQuestions();
	}
	
	//Create a new question
	@RequestMapping(value = "/questions", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public QuestionDetails createQuestions(@RequestBody QuestionAndChoices questionAndChoices) throws JSONException
	{
		QuestionDetails questionDetails = iQuestionsService.createAQuestion(questionAndChoices);
		return questionDetails;
	}
	
	//Fetch a question by Id
	@RequestMapping(value = "/questions/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public QuestionDetails getQuestionById(@PathVariable("id") Integer id)
	{
		return iQuestionsService.getQuestionById(id);
		
	}

}

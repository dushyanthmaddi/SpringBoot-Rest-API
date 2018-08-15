package com.springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.springboot.beans.Choices;
import com.springboot.beans.QuestionAndChoices;
import com.springboot.beans.QuestionDetails;

@Service
public class QuestionsService implements IQuestionsService{
	
	@Autowired
	RestTemplate restTemplate;

	@Override
	public List<QuestionDetails> getAllQuestions() throws JSONException {
		String qstResponse =  restTemplate.getForObject("http://polls.apiblueprint.org/questions", String.class);
		List<QuestionDetails> questionDetailsList = returnQstDetailsList(qstResponse);
		return questionDetailsList;
	}
	
	/**
	 * returnQstDetailsList is used to return the list of question details from the response input.
	 * @param String
	 * @return List<QuestionDetails>
	 * @throws JSONException
	 */
	public List<QuestionDetails> returnQstDetailsList(String qstResponse) throws JSONException{
		
		List<QuestionDetails> questionDetailsList = new ArrayList<QuestionDetails>();
		JSONArray jsonArray = new JSONArray(qstResponse);
		for(int i=0; i < jsonArray.length(); i++){
			JSONObject jsonObject = (JSONObject) jsonArray.get(i);
			QuestionDetails questionDetails = new QuestionDetails();
			questionDetails.setQuestion(jsonObject.getString("question"));
			questionDetails.setPublished_at(jsonObject.getString("published_at"));

			List<Choices> choicesList = new ArrayList<Choices>();
			JSONArray choiceJSONArray = (JSONArray) jsonObject.get("choices");
			for(int j=0; j < choiceJSONArray.length(); j++){
				JSONObject inChoiceJSONObject = (JSONObject) choiceJSONArray.get(j);
				Choices choices = new Choices();
				choices.setChoice(inChoiceJSONObject.getString("choice"));
				choices.setVotes(inChoiceJSONObject.getString("votes"));
				choicesList.add(choices);
			}
			questionDetails.setChoices(choicesList);
			questionDetailsList.add(questionDetails);
		}
		return questionDetailsList;
	}

	@Override
	public QuestionDetails createAQuestion(QuestionAndChoices questionAndChoices) {
		ResponseEntity<QuestionDetails> response = restTemplate.postForEntity("http://polls.apiblueprint.org/questions", questionAndChoices, QuestionDetails.class);
		return response.getBody();
	}

	@Override
	public QuestionDetails getQuestionById(int id) {
		ResponseEntity<QuestionDetails> response = restTemplate.getForEntity("http://polls.apiblueprint.org/questions/"+id+"", QuestionDetails.class);
		return response.getBody();
	}

}

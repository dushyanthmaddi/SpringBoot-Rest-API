package com.springboot.main;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.beans.QuestionAndChoices;
import com.springboot.controller.QUTDataServiceController;
import com.springboot.service.IQuestionsService;

@RunWith(SpringRunner.class)
@WebMvcTest(QUTDataServiceController.class)
public class QUTDataServiceControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IQuestionsService iQuestionsService;
	
	@Test
	public void getAllQuestionsTest() throws Exception{
		mockMvc.perform(get("/questions")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void getQuestionById() throws Exception{
		mockMvc.perform(get("/questions/2"))
				.andExpect(status().isOk());
	}
	
	@Test
	public void createQuestion() throws Exception{
		QuestionAndChoices questionAndChoices = new QuestionAndChoices();
		questionAndChoices.setQuestion("Helllloooo");
		mockMvc.perform(post("/questions")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(questionAndChoices)))
				.andExpect(status().is(200));
	}
	
	/*
     * converts a Java object into JSON
     */
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

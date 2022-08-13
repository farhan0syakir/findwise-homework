package com.findwise.homework;

import com.findwise.homework.service.SearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class WebLayerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SearchService searchService;

	@Test
	public void shouldReturnResult() throws Exception {
		String[] expected = new String[]{"doc3.txt", "doc1.txt"};
		given(searchService.tfidf("fox")).willReturn(Arrays.asList(expected));
		this.mockMvc.perform(get("/search/fox")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("[\"doc3.txt\",\"doc1.txt\"]")));
	}
}

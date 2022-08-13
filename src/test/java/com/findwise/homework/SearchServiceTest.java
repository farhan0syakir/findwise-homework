package com.findwise.homework;

import com.findwise.homework.service.SearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SearchServiceTest {

	@Autowired
	SearchService searchService;

	private List<String> result;
	private String[] expected;

	@Test
	public void testSearchReadResource() {
		expected = new String[]{"doc1.txt", "doc2.txt", "doc3.txt"};
		assertEquals(Arrays.asList(expected),
				Arrays.stream(searchService.getFiles()).map(Resource::getFilename).collect(Collectors.toList()));
	}

	@Test
	public void testSearchServiceLogic() {
		result = searchService.tfidf("brown");
		expected = new String[]{"doc1.txt", "doc2.txt"};
		assertEquals(Arrays.asList(expected), result);

		result = searchService.tfidf("fox");
		expected = new String[]{"doc3.txt", "doc1.txt"};
		assertEquals(Arrays.asList(expected), result);

		result = searchService.tfidf("dog");
		expected = new String[]{"doc3.txt", "doc1.txt", "doc2.txt"};
		assertEquals(Arrays.asList(expected), result);

	}

}
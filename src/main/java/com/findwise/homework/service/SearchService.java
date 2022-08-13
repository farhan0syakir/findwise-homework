package com.findwise.homework.service;

import com.findwise.homework.utils.FileUtils;
import com.findwise.homework.model.WeightedItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class SearchService {

	@Value("${input.folder}")
	private Resource[] files;

	private String[] documentNames;
	private String[] documentContents;

	public List<String> tfidf(String term) {
		refresh();
		ArrayList<WeightedItem> tfidf = new ArrayList<>();
		for (int i = 0; i < documentNames.length; i++) {
			tfidf.add(WeightedItem.builder()
					.weight(df(term, documentContents[i]) * idf(term))
					.fileName(documentNames[i])
					.build()
			);
		}

		tfidf.sort((o1, o2) -> Double.compare(o2.getWeight(), o1.getWeight()));
		return tfidf.stream()
				.filter(weightedItem -> weightedItem.getWeight() > 0)
				.map(WeightedItem::getFileName).collect(Collectors.toList());
	}

	private Double df(String term, String content) {
		String[] words = content.split("\\s+");
		double frequency = Collections.frequency(List.of(words), term);
		return frequency / (double) words.length;
	}

	private double idf(String term) {
		double n = documentNames.length;
		double denom = Arrays.stream(documentContents).filter(s -> s.contains(term)).count();
		denom = 0.1 + denom;
		return Math.abs(Math.log(n / denom));
	}

	public Resource[] getFiles() {
		return files;
	}

	public void refresh() {
		documentNames = new String[files.length];
		documentContents = new String[files.length];
		int counter = 0;
		for (Resource file : files) {
			documentNames[counter] = file.getFilename();
			documentContents[counter] = FileUtils.asString(file);
			counter++;
		}
	}
}
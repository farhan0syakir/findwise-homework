package com.findwise.homework.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WeightedItem {
	double weight;
	String fileName;
}

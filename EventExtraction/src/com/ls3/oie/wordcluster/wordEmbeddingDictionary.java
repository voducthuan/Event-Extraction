package com.ls3.oie.wordcluster;

import java.util.ArrayList;

public class wordEmbeddingDictionary extends wordEmbedding{

	public wordEmbeddingDictionary() {
		super();
	}
	
	public ArrayList<Double> getWordEmbedding(String word) {
		if (word == null) return null;
		if (!this.map.containsKey(word))
			word = word.toLowerCase();
		return this.map.get(word);
	}
}

package com.ls3.oie.wordcluster;

import java.util.HashMap;

public abstract class wordCluster {
	HashMap<String, String> map;
	
	public wordCluster() {
		 this.map = new HashMap<String, String>();
	}

	public void add(String word, String bitstring) {
		this.map.put(word, bitstring);			
	}

}

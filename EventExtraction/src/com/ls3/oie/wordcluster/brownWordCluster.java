package com.ls3.oie.wordcluster;

import java.util.HashMap;
import java.util.Set;


/**
 * Object that holds brown word cluster
 * 
 * bitstring, word, wordcount
 * 
 * bitstring: id of cluster, consisting of a set of words with their count
 * 
 *
 */
public class brownWordCluster extends wordCluster {
	
	HashMap<String, Set<String>> bit2words;
	
	public brownWordCluster() {
		 super();
		 bit2words = new HashMap<String, Set<String>>();
	}

	/**
	 * Return bitstring of cluster word belongs to
	 * @param word
	 * @return bitstring
	 */
	public String getFullClusterId(String word) {
		return this.map.get(word);
	}
	
	/**
	 * Return prefix of cluster id bitstring of a certain length
	 * If word is not in cluster, returns null
	 * If prefixLength is longer than bitstring, return complete bitstring
	 * @param word 
	 * @param prefixLength
	 * @return bitstring
	 */
	public String getPrefixClusterId(String word,int prefixLength) {
		String bitstring = this.map.get(word);
		if (bitstring != null) {
			try {
				String bitstring_short = bitstring.substring(0,prefixLength);
				return bitstring_short;
			} catch (java.lang.StringIndexOutOfBoundsException e) {
				return bitstring;
			}
		}
		else return bitstring;
	}

}

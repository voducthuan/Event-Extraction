package com.ls3.oie.temporalmodel;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class main {

	public static void main(String[] args) throws UnsupportedEncodingException, IOException {
	// TODO Auto-generated method stub

	//***************************************************************
	/**
	 *   read data from File and write data into sentenceList
	 */		
		//String inputFileName = "data/tdata/VnDTv1.CoNLL";
		//WSJ_20130318_731.col
		String inputFileName = "data/tdata/WSJ_20130318_731.col";
		// read data from file and write into List by sentence. 
		// Every line is a sentence and every word of sentence is a KeyWord
		List<Sentence> sentenceList = new ArrayList<Sentence>();
		DataWriter 		Writer      = new DataWriter();
		sentenceList = Writer.writeSentenceList(inputFileName); 
					//the total number of sentences of data file: 10197
		
		for (int i = 0; i < sentenceList.size(); i++) {
			Sentence temp=sentenceList.get(i);
			System.out.println(temp.sentenceContent());
			//temp.printSentenceContent();
			//KeyWord word=temp.get(0);
			//System.out.println(word.getId());
		}
		
		String t1="e1";
		String t2="txm2";
		System.out.println(t2.indexOf("xm"));
				
		
		
	} 
}

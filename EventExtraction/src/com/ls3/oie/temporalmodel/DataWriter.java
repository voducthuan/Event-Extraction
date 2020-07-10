package com.ls3.oie.temporalmodel;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class DataWriter {
	
	/**
	 *  function main use to read data file and write into a List
	 *  @param  data file  
	 *  @return write data into List that every line is content of a sentence and every word is a KeyWord, 
	 *    
	 */
	public List<Sentence> writeSentenceList(String fileName) throws UnsupportedEncodingException, IOException{
		
		List<Sentence> sentenceList = new ArrayList<Sentence>();
		sentenceList = writeSentenceList_FromFile(fileName);
		return sentenceList;		
	}
	
	/**
	 *  read data from file and write into a list. However, it don't have add RelationKeyWord  
	 *  @return  Sentence list  
	 */
	
	/**
	 * @param fileName
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public List<Sentence> writeSentenceList_FromFile(String fileName) throws UnsupportedEncodingException, IOException{
		List<String> dataList = new ArrayList<String>();
				
		DataReader readFile = new DataReader();
		
		//******** read from data file into the List  **************
		dataList = readFile.readFile(fileName);
		//** Write data from List into  SentenceList
		List<Sentence> sentenceList = new ArrayList<Sentence>();
		// For the first sentence
		//DCT_1989-11-02	O	O	O	O	O	O	tmx0	DATE	1989-11-02	O	O	NN1	B-NP	dct_1989-11-02	LS	O	O
		/*
		 
		line = (String) dataList.get(j);			
		wordLine = readFile.ReadWordLine(line);
		word0=wordLine[0];
		id1=wordLine[1];
		sentencePos2=wordLine[2];
		orginalWord3=wordLine[3];
		eventName4=wordLine[4];
		eventStatus5=wordLine[5];
		eventTense6=wordLine[6];
		timeName7=wordLine[7];
		timeStatus8=wordLine[8];
		timeValue9=wordLine[9];
		posTag15=wordLine[15];
		dpStatus16=wordLine[16];
		
		kWord = new KeyWord(word0, id1, sentencePos2, orginalWord3, eventName4, eventStatus5, eventTense6, 
				            timeName7, timeStatus8, timeValue9, posTag15, dpStatus16);
		
		//add the KeyWord in sentence 
		sentence.addKeyWord(kWord);
		 */
		String firstLine=(String)dataList.get(0);
		//System.out.println(firstLine);
		String[] wordLineFirstLine=readFile.ReadWordLine(firstLine);
		String word0FirstLine=wordLineFirstLine[0];
		String id1FirstLine=wordLineFirstLine[1];
		String sentencePos2FirstLine=wordLineFirstLine[2];
		String orginalWord3FirstLine=wordLineFirstLine[3];
		String eventName4FirstLine=wordLineFirstLine[4];
		String eventStatus5FirstLine=wordLineFirstLine[5];
		String eventTense6FirstLine=wordLineFirstLine[6];
		String timeName7FirstLine=wordLineFirstLine[7];
		String timeStatus8FirstLine=wordLineFirstLine[8];
		String timeValue9FirstLine=wordLineFirstLine[9];
		String posTag15FirstLine=wordLineFirstLine[15];
		String dpStatus16FirstLine=wordLineFirstLine[16];
		KeyWord kWordFirstLine;
		
		kWordFirstLine = new KeyWord(word0FirstLine, id1FirstLine, sentencePos2FirstLine, orginalWord3FirstLine, eventName4FirstLine, eventStatus5FirstLine, 
				                     eventTense6FirstLine, timeName7FirstLine, timeStatus8FirstLine, timeValue9FirstLine, posTag15FirstLine, dpStatus16FirstLine);
		Sentence sentenceFirstLine=new Sentence();	
		sentenceFirstLine.addKeyWord(kWordFirstLine);
		sentenceList.add(sentenceFirstLine);
		// Ket thuc first line sentence
		
		int count = 0;       //count the number of lines in a sentence
		int j = 0;           //starting position in a sentence
		
		for(int i=2; i<dataList.size(); i++){ // Duyet het cac dong, bat dau tu*` line=2 theo dat diem cua du lieu col
			if ((dataList.get(i).toString().isEmpty()) && (count>1)){
				Sentence sentence = new Sentence();
				String line = "";
				String[] wordLine;
				//String idWord, token, tag, dependenceId, dependenceType;
				String word0; 
				String id1;
				String sentencePos2;
				String orginalWord3;
				String eventName4;
				String eventStatus5;
				String eventTense6;
				String timeName7;
				String timeStatus8;
				String timeValue9;
				String posTag15;
				String dpStatus16;
				KeyWord kWord;
				
				for (int k=0; k<count; k++){  // for a sentence
					//create new KeyWord from a line
					line = (String) dataList.get(j);
					
					wordLine = readFile.ReadWordLine(line);
					word0=wordLine[0];
					id1=wordLine[1];
					sentencePos2=wordLine[2];
					orginalWord3=wordLine[3];
					eventName4=wordLine[4];
					eventStatus5=wordLine[5];
					eventTense6=wordLine[6];
					timeName7=wordLine[7];
					timeStatus8=wordLine[8];
					timeValue9=wordLine[9];
					posTag15=wordLine[15];
					dpStatus16=wordLine[16];
					
					kWord = new KeyWord(word0, id1, sentencePos2, orginalWord3, eventName4, eventStatus5, eventTense6, 
							            timeName7, timeStatus8, timeValue9, posTag15, dpStatus16);
					
					//add the KeyWord in sentence 
					sentence.addKeyWord(kWord);
					j++;
				}
				sentenceList.add(sentence);
				
				j = 0;
				count = 0;
			}
			else{
				if (count == 0){
					j = i;  
				}
				count++;
			}
		}
		
		// For last sentence
		Sentence finalSentence=new Sentence();
		String line = "";
		String[] wordLine;
		//String idWord, token, tag, dependenceId, dependenceType;
		String word0; 
		String id1;
		String sentencePos2;
		String orginalWord3;
		String eventName4;
		String eventStatus5;
		String eventTense6;
		String timeName7;
		String timeStatus8;
		String timeValue9;
		String posTag15;
		String dpStatus16;
		KeyWord kWord;
		
		if (j<dataList.size()) {
			for (int k = j; k < dataList.size(); k++) {
				line = (String) dataList.get(k);
				//System.out.println(line);
				wordLine = readFile.ReadWordLine(line);
				word0=wordLine[0];				
				id1=wordLine[1];
				sentencePos2=wordLine[2];
				orginalWord3=wordLine[3];
				eventName4=wordLine[4];
				eventStatus5=wordLine[5];
				eventTense6=wordLine[6];
				timeName7=wordLine[7];
				timeStatus8=wordLine[8];
				timeValue9=wordLine[9];
				posTag15=wordLine[15];
				dpStatus16=wordLine[16];
				kWord = new KeyWord(word0, id1, sentencePos2, orginalWord3, eventName4, eventStatus5, eventTense6, 
						            timeName7, timeStatus8, timeValue9, posTag15, dpStatus16);
				//add the KeyWord in sentence 
				finalSentence.addKeyWord(kWord);
			}
		}
		sentenceList.add(finalSentence);

		return sentenceList;
	}
		
	
	
	public void printSentenceList(List<Sentence> sentenceList){
		
		for(int i=0; i<sentenceList.size(); i++){
			System.out.println("\n\n************* Sentence: "+i+"*********************");
			sentenceList.get(i).printSentenceContent();
			//sentenceList.get(i).printSentenceKeyWord();
		}	
		
	}
	
	public static void main(String args[]) throws IOException{
		
		//WSJ_20130318_731.col
		String inputFileName = "data/tdata/wsj_0026.col";
		// read data from file and write into List by sentence. 
		// Every line is a sentence and every word of sentence is a KeyWord
		List<Sentence> sentenceList = new ArrayList<Sentence>();
		DataWriter 		Writer      = new DataWriter();
		sentenceList = Writer.writeSentenceList(inputFileName); 

		Writer.printSentenceList(sentenceList);
	}

}

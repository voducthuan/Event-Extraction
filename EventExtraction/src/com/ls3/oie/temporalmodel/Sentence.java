package com.ls3.oie.temporalmodel;

import java.util.ArrayList;
import java.util.List;

import edu.stanford.nlp.util.TypesafeMap.Key;

public class Sentence{
	
	public List<KeyWord> sentence;

	public Sentence(){
		sentence = new ArrayList<KeyWord>();
	}
	
	public List<KeyWord> getSentence() {
		return sentence;
	}

	public void setSentence(List<KeyWord> _sentence) {
		this.sentence = _sentence;
	}

	public void addKeyWord(KeyWord _kWord){
		sentence.add(_kWord);
	}
	
	public KeyWord getKeyWordById(String _id){
		KeyWord kWord = new KeyWord();
		
		for(int i=0; i<sentence.size(); i++){
			if (sentence.get(i).getId1().contentEquals(_id)){
				 kWord = sentence.get(i);
								 
			}
		}
		return kWord;		 
	}
	
	public int isRelation(){
		int result=0;
		
		for (int i = 0; i < this.sentence.size(); i++) {
			KeyWord pWord=this.sentence.get(i);
			String eventName=pWord.getEventName4();
			String eventTime=pWord.getTimeName7();
			if (eventName.indexOf("e")==0||eventTime.indexOf("tmx")==0) {
				result++;
			}
			
		}
		return result;
	} 

	// update on 3 June 2018
	public KeyWord getKeywordByID(String pID){
		KeyWord result=new KeyWord();
		for (int i = 0; i < this.sentence.size(); i++) {
			KeyWord pkeyword=this.sentence.get(i);
			if (pkeyword.getId1().equals(pID)) {
				result=pkeyword;
				break;
			}
		}
		return result;
	}
	
	public void clear(){
		sentence.clear();
	}

	public int size(){
		return sentence.size();
	}

	//***************************************************************
	/**
	 * 
	 * @param ID1, ID2 is number id in a sentence        
	 * @return  
	 */
	public boolean isCompareRemote2ID(KeyWord kwVerb, KeyWord kWord){
		int id1, id2;
		id1 = Integer.parseInt(kwVerb.getId1());
		id2 = Integer.parseInt(kWord.getId1());
		
		if (id1 < id2){
			if ((id2-id1) <= 2){		
				return true;
			}
		}
		else{
			if ((id1-id2) <= 2){
				return true;
			}
		}
		return false;
	}
		
	public void printSentenceContent(){
		for(int i=0; i<sentence.size(); i++){
			//System.out.print(sentence.get(i).getWord()+"\t");
			System.out.print(sentence.get(i).getWord0()+" ");
		}
		System.out.println("\n");
	}

	public String sentenceContent(){
		String result="";
		for(int i=0; i<sentence.size(); i++){
			result=result+sentence.get(i).getWord0()+" ";
		}
		return result.trim();
	}
	
}

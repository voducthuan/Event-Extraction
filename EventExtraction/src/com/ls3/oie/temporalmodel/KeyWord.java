package com.ls3.oie.temporalmodel;

import java.util.ArrayList;
import java.util.List;

import edu.stanford.nlp.util.TypesafeMap.Key;

public class KeyWord {
	
	private String word0; 
	private String id1;
	private String sentencePos2;
	private String orginalWord3;
	private String eventName4;
	private String eventStatus5;
	private String eventTense6;
	private String timeName7;
	private String timeStatus8;
	private String timeValue9;
	private String posTag15;
	private String dpStatus16;
	
	public KeyWord() {
		this.word0=""; 
		this.id1="";
		this.sentencePos2="";
		this.orginalWord3="";
		this.eventName4="";
		this.eventStatus5="";
		this.eventTense6="";
		this.timeName7="";
		this.timeStatus8="";
		this.timeValue9="";
		this.posTag15="";
		this.dpStatus16="";
	}
	
	public KeyWord(String _word, 
				   String _id,
				   String _sentencePos,
				   String _orginalWord,
				   String _eventName,
				   String _eventStatus,
				   String _eventTense,
				   String _timeName,
				   String _timeStatus,
				   String _timeValue,
				   String _posTag,
				   String _dpStatus){ 
		
		this.word0=_word; 
		this.id1=_id;
		this.sentencePos2=_sentencePos;
		this.orginalWord3=_orginalWord;
		this.eventName4=_eventName;
		this.eventStatus5=_eventStatus;
		this.eventTense6=_eventTense;
		this.timeName7=_timeName;
		this.timeStatus8=_timeStatus;
		this.timeValue9=_timeValue;
		this.posTag15=_posTag;
		this.dpStatus16=_dpStatus;

	}

	public String getWord0() {
		return word0;
	}

	public void setWord0(String _word0) {
		this.word0 = _word0;
	}

	public String getId1() {
		return id1;
	}

	public void setId1(String _id1) {
		this.id1 = _id1;
	}

	public String getSentencePos2() {
		return sentencePos2;
	}

	public void setSentencePos2(String _sentencePos2) {
		this.sentencePos2 = _sentencePos2;
	}

	public String getOrginalWord3() {
		return orginalWord3;
	}

	public void setOrginalWord3(String _orginalWord3) {
		this.orginalWord3 = _orginalWord3;
	}

	public String getEventName4() {
		return eventName4;
	}

	public void setEventName4(String _eventName4) {
		this.eventName4 = _eventName4;
	}

	public String getEventStatus5() {
		return eventStatus5;
	}

	public void setEventStatus5(String _eventStatus5) {
		this.eventStatus5 = _eventStatus5;
	}

	public String getEventTense6() {
		return eventTense6;
	}

	public void setEventTense6(String _eventTense6) {
		this.eventTense6 = _eventTense6;
	}

	public String getTimeName7() {
		return timeName7;
	}

	public void setTimeName7(String _timeName7) {
		this.timeName7 = _timeName7;
	}

	public String getTimeStatus8() {
		return timeStatus8;
	}

	public void setTimeStatus8(String _timeStatus8) {
		this.timeStatus8 = _timeStatus8;
	}

	public String getTimeValue9() {
		return timeValue9;
	}

	public void setTimeValue9(String _timeValue9) {
		this.timeValue9 = _timeValue9;
	}

	public String getPosTag15() {
		return posTag15;
	}

	public void setPosTag15(String _posTag15) {
		this.posTag15 = _posTag15;
	}

	public String getDpStatus16() {
		return dpStatus16;
	}

	public void setDpStatus16(String _dpStatus16) {
		this.dpStatus16 = _dpStatus16;
	}
	
	public int isEvent(){
		int result=0;
		if (this.eventName4.indexOf("e")==0) {
			result=1;
		}
		return result;
	}
	
	public int isTime(){
		int result=0;
		if (this.timeName7.indexOf("tmx")==0) {
			result=1;
		}
		return result;
	}
	
	public int isExistWord0(String text){
		int result=0;
		if (text.toLowerCase().indexOf(this.getWord0().toLowerCase())>-1) {
			result=1;
		}
		return result;
	}
	
	public int isExistInEventTime(String _text){
		int result=0;
		
		String node="";
		
		if (this.isEvent()==1) {
			node=this.getEventName4();
		}else{
			node=this.getTimeName7();
		}
		
		if (node.toLowerCase().equals(_text.toLowerCase())) {
			result=1;
		}
		
		return result;
	}

	public int isNoun(){
		int result=0;
		if (this.posTag15.indexOf("NN")==0){
			result =1;
		}
		return result;
	}
	
	public int isVerb(){
		int result=0;
		if (this.posTag15.indexOf("VB")==0){
			result =1;
		}
		return result;
	}
	// Update on 6 July 2018
	public int isOriginalVerb(){
		int result=0;
		if (this.posTag15.contains("VB")){
			result =1;
		}
		return result;
	}
	
	public int isVerbVBD(){ //went/played
		int result=0;
		if (this.posTag15.contains("VBD")){
			result =1;
		}
		return result;
	}

	public int isVerbVBG(){ //is studying
		int result=0;
		if (this.posTag15.contains("VBG")){
			result =1;
		}
		return result;
	}
	//

	public int isVerbVBN(){
		int result=0;
		if (this.posTag15.contains("VBN")){
			result =1;
		}
		return result;
	}
	
	public String toString(){
		String result="";
		if (this.isEvent()==1) {
			result=this.getWord0()+":"+this.getEventName4();
		}
		if (this.isTime()==1) {
			result=this.getWord0()+":"+this.getTimeName7();
		}
		return result;
	}
	
	public KeyWord getKeywordByContent(String content, Sentence sentence){  // chi nham muc dich lay orgirinal content
		KeyWord result=new KeyWord();
		//System.out.println("----------------------------");
		//System.out.println(content);		
		
		List<KeyWord> keywordList=sentence.getSentence();
		for (int i = 0; i < keywordList.size(); i++) {
			KeyWord pKeyword=keywordList.get(i);
			//System.out.println("-----------------");
			//System.out.println(pKeyword.getWord0());
			String txt=pKeyword.getWord0().toLowerCase();
			if (txt.equals(content.toLowerCase())) {
				result=pKeyword;
				/*
				System.out.println("Testing------------------");
				System.out.println(txt);
				System.out.println(content);
				System.out.println(result.getWord0());
				System.out.println("End testing--------------");
				*/
				break;
			}
			
		}
		return result;
	}
	
	public String getLabel(){
		String result="";
		if (this.isEvent()==1) {
			result=this.getEventName4();
		}else{
			result=this.getTimeName7();
		}
		return result;
	}
	
	
	public static void main(String args[]){
		
	}
}


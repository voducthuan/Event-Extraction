package com.ls3.oie.temporalmodel;

import java.util.ArrayList;
import java.util.List;

import com.ls3.oie5.patternOIE5;

public class extractedPropositionOIE5 {
	
	public patternOIE5 pattern;
	public List<KeyWord> argumentList;
		
	public extractedPropositionOIE5(){
		pattern=new patternOIE5();
		argumentList=new ArrayList<>();
	}
	
	public extractedPropositionOIE5(patternOIE5 pPattern, List<KeyWord> _argmentList){
		pattern=pPattern;
		argumentList=_argmentList;
	}
	
	public patternOIE5 getPattern() {
		return pattern;
	}

	public void setPattern(patternOIE5 pPattern) {
		this.pattern = pPattern;
	}

	public List<KeyWord> getArgumentList() {
		return argumentList;
	}

	public void setArgumentList(List<KeyWord> pArgumentList) {
		this.argumentList = pArgumentList;
	}

	public String toString(){
		String result="";
		String patternContent=pattern.toString();
		String argument="";
		
		for (int i = 0; i < argumentList.size(); i++) {
			KeyWord word=argumentList.get(i);
			if (word.isEvent()==1) {
				argument=argument+" "+word.getEventName4()+":"+word.getWord0()+" ";
			}
			if (word.isTime()==1) {
				argument=argument+" "+word.getTimeName7()+":"+word.getWord0()+" ";
			}
		}
		result= patternContent+" "+argument;
		return result;
	}
	// xay dung cac ham de kiem tra Keyword trong proposition
	public int isKeyWordInSub(KeyWord _keyword){
		int result=0;
		String subject = this.pattern.getSub();
		if (subject.toLowerCase().indexOf(_keyword.getWord0().toLowerCase())>-1) {
			result=1;
		}
		return result;
	}
	
	public int isKeyWordInRel(KeyWord _keyword){
		int result=0;
		String rel = this.pattern.getRel();
		if (rel.toLowerCase().indexOf(_keyword.getWord0().toLowerCase())>-1) {
			result=1;
		}
		return result;
	}
	
	public int isKeyWordInObj(KeyWord _keyword){
		int result=0;
		String obj = this.pattern.getObj();
		if (obj.toLowerCase().indexOf(_keyword.getWord0().toLowerCase())>-1) {
			result=1;
		}
		return result;
	}
	
	public int isExist(relationNodes pRel){
		int result=0;
		
		String lableNode1=pRel.getLabel1().toLowerCase();
		String labelNode2=pRel.getLabel2().toLowerCase();
		
		int condition1=0;
		int condition2=0;
		
		for (int i = 0; i < this.argumentList.size(); i++) {
			KeyWord pkeyword=argumentList.get(i);
			String tempLabel=pkeyword.getLabel().toLowerCase();
			if (tempLabel.equals(lableNode1)) {
				condition1=1;
			}
			if (tempLabel.equals(labelNode2)) {
				condition2=1;
			}
		}
		
		if (condition1==1&&condition2==1) {
			result=1;
		}
		
		return result;
	}
	
	
}

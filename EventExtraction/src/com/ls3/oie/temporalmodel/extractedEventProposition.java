package com.ls3.oie.temporalmodel;

import java.util.ArrayList;
import java.util.List;

import com.ls3.oie.clause.Proposition;

public class extractedEventProposition {
	
	public KeyWord keyword;
	public List<Proposition> propositionList;
	
	public extractedEventProposition(){
		this.keyword=new KeyWord();
		this.propositionList=new ArrayList<>();
	}
	
	public extractedEventProposition(KeyWord pKeyword, List<Proposition> proList){
		this.keyword=pKeyword;
		this.propositionList=proList;
	}
	
	public KeyWord getKeyword() {
		return keyword;
	}
	public void setKeyword(KeyWord _keyword) {
		this.keyword = _keyword;
	}
	public List<Proposition> getPropositionList() {
		return propositionList;
	}
	public void setPropositionList(List<Proposition> _propositionList) {
		this.propositionList = _propositionList;
	}
	
	
	
	
	
}

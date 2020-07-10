package com.ls3.oie.temporalmodel;

import com.ls3.oie5.patternOIE5;

public class KeyWordPropositionOIE5 {
	public KeyWord keyword;
	public patternOIE5 propositionOIE5;
	
	public KeyWordPropositionOIE5(){
		this.keyword=new KeyWord();
		this.propositionOIE5 = new patternOIE5();
	}

	public KeyWordPropositionOIE5(KeyWord _keyword, patternOIE5 _pro){
		this.keyword = _keyword;
		this.propositionOIE5 = _pro;
	}

	public KeyWord getKeyword() {
		return this.keyword;
	}

	public void setKeyword(KeyWord _keyword) {
		this.keyword = _keyword;
	}

	public patternOIE5 getProposition() {
		return this.propositionOIE5;
	}

	public void setProposition(patternOIE5 _proposition) {
		this.propositionOIE5 = _proposition;
	}
	
	
}

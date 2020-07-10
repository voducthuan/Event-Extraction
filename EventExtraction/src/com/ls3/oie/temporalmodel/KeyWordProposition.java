package com.ls3.oie.temporalmodel;

import com.ls3.oie.clause.Proposition;

public class KeyWordProposition {
	public KeyWord keyword;
	public Proposition proposition;
	
	public KeyWordProposition(){
		this.keyword=new KeyWord();
		this.proposition = new Proposition();
	}

	public KeyWordProposition(KeyWord _keyword, Proposition _pro){
		this.keyword = _keyword;
		this.proposition = _pro;
	}

	public KeyWord getKeyword() {
		return this.keyword;
	}

	public void setKeyword(KeyWord _keyword) {
		this.keyword = _keyword;
	}

	public Proposition getProposition() {
		return this.proposition;
	}

	public void setProposition(Proposition _proposition) {
		this.proposition = _proposition;
	}
	
	
}

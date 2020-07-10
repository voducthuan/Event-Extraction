package com.ls3.oie5;

public class patternOIE5 {

	public String sub;
	public String rel;
	public String obj;
	
	public patternOIE5(){
		this.sub="";
		this.rel="";
		this.obj="";
	}
	
	public patternOIE5(String pSub, String pRel){
		this.sub=pSub;
		this.rel=pRel;
		this.obj="";
	}
	
	public patternOIE5(String pSub, String pRel, String pObj){
		this.sub=pSub;
		this.rel=pRel;
		this.obj=pObj;
	}
	
	public String getSub() {
		return sub;
	}
	public void setSub(String pSub) {
		this.sub = pSub;
	}
	public String getRel() {
		return rel;
	}
	public void setRel(String pRel) {
		this.rel = pRel;
	}
	public String getObj() {
		return obj;
	}
	public void setObj(String pObj) {
		this.obj = pObj;
	}
	
	public String toString(){
		return "sub: "+this.sub+" "+"rel: "+this.rel+" "+"obj: "+this.obj;
	}
	
}

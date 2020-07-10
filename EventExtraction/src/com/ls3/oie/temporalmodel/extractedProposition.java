package com.ls3.oie.temporalmodel;



import java.util.ArrayList;
import java.util.List;
import com.ls3.oie.clause.ClausIE;
import com.ls3.oie.clause.Proposition;

public class extractedProposition {
	
	public Proposition proposition;
	public List<KeyWord> argumentList;
		
	public extractedProposition(){
		proposition=new Proposition();
		argumentList=new ArrayList<>();
	}
	
	public extractedProposition(Proposition _pro, List<KeyWord> _argmentList){
		proposition=_pro;
		argumentList=_argmentList;
	}

	public Proposition getProposition() {
		return proposition;
	}

	public void setProposition(Proposition _proposition) {
		this.proposition = _proposition;
	}

	public List<KeyWord> getArgumentList() {
		return this.argumentList;
	}

	public void setArgumentList(List<KeyWord> _argumentList) {
		this.argumentList = _argumentList;
	}
	
	public String toString(){
		String result="";
		String propContent=proposition.toString();
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
		result= propContent+" "+argument;
		return result;
	}
	// xay dung cac ham de kiem tra Keyword trong proposition
	public int isKeyWordInSub(KeyWord _keyword){
		int result=0;
		String subject = this.proposition.subject();
		if (subject.toLowerCase().indexOf(_keyword.getWord0().toLowerCase())>-1) {
			result=1;
		}
		return result;
	}
	
	public int isKeyWordInRel(KeyWord _keyword){
		int result=0;
		String rel = this.proposition.relation();
		if (rel.toLowerCase().indexOf(_keyword.getWord0().toLowerCase())>-1) {
			result=1;
		}
		return result;
	}
	
	public int isKeyWordInPredicate(KeyWord _keyword){
		int result=0;
		String arg = "";
		if (this.getProposition().noArguments()>0) {
			arg=this.getProposition().argument(0);
		}
		
		if (arg.toLowerCase().indexOf(_keyword.getWord0().toLowerCase())>-1) {
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

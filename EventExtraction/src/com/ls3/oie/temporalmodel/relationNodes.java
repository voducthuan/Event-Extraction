package com.ls3.oie.temporalmodel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import com.ls3.oie.tools.txtNormalization;

import javax.rmi.ssl.SslRMIClientSocketFactory;

import com.ls3.oie.clause.Proposition;
import com.ls3.oie.temporalrules.generalRules;
import com.ls3.oie.tools.txtNormalization;
import com.ls3.oie5.patternOIE5;

import edu.stanford.nlp.parser.metrics.LeafAncestorEval;
import edu.stanford.nlp.util.StringUtils;

public class relationNodes {

	public KeyWord Node1;
	public KeyWord Node2;
	public Proposition propositionNode1;
	public Proposition propositionNode2;
	// update 11 June 2018
	public String timeNode1;
	public String timeNode2;
	public String tenseNode1;
	public String tenseNode2;
	public String relaionType;
	
	public relationNodes(){
		this.Node1=new KeyWord();
		this.Node2=new KeyWord();
		this.propositionNode1=new Proposition();
		this.propositionNode2=new Proposition();
		this.timeNode1="";
		this.timeNode2="";
		this.tenseNode1="";
		this.tenseNode2="";
		this.relaionType="NONE";
		
	}

	public relationNodes(KeyWord _node1, KeyWord _node2){
		this.Node1=_node1;
		this.Node2=_node2;
		this.propositionNode1= new Proposition();
		this.propositionNode2= new Proposition();
		this.timeNode1="";
		this.timeNode2="";
		this.tenseNode1="";
		this.tenseNode2="";
		this.relaionType="NONE";
	}
	
	public relationNodes(KeyWord _node1, KeyWord _node2, Proposition _pro1, Proposition _pro2){
		this.Node1=_node1;
		this.Node2=_node2;
		this.propositionNode1=_pro1;
		this.propositionNode2=_pro2;
		this.timeNode1="";
		this.timeNode2="";
		this.tenseNode1="";
		this.tenseNode2="";
		this.relaionType="NONE";
	}

	public relationNodes(KeyWord _node1, KeyWord _node2, patternOIE5 _pattern1, patternOIE5 _pattern2){
		this.Node1=_node1;
		this.Node2=_node2;
		
		System.out.println("Vo Duc Thuan");
		
		System.out.println(_pattern1.sub);
		System.out.println(_pattern1.rel);
		System.out.println(_pattern1.obj);
		
		System.out.println("Vo Duc Thuan");
		
		this.propositionNode1=new Proposition();
		this.propositionNode1.setSubject(_pattern1.sub);
		this.propositionNode1.setRelation(_pattern1.rel);
		this.propositionNode1.setArg(_pattern1.obj);
		
		this.propositionNode2=new Proposition();
		this.propositionNode2.setSubject(_pattern2.sub);
		this.propositionNode2.setRelation(_pattern2.rel);
		this.propositionNode2.setArg(_pattern2.obj);
		
		this.timeNode1="";
		this.timeNode2="";
		this.tenseNode1="";
		this.tenseNode2="";
		this.relaionType="NONE";
	}

	
	public KeyWord getNode1() {
		return this.Node1;
	}

	public void setNode1(KeyWord _node1) {
		Node1 = _node1;
	}

	public KeyWord getNode2() {
		return this.Node2;
	}

	public void setNode2(KeyWord _node2) {
		Node2 = _node2;
	}
	
	public Proposition getPropositionNode1() {
		return this.propositionNode1;
	}

	public void setPropositionNode1(Proposition _propositionNode1) {
		this.propositionNode1 = _propositionNode1;
	}

	public void setPropositionNode1WithOIE5(patternOIE5 _patternOIE5) {
		this.propositionNode1.setSubject(_patternOIE5.sub);
		this.propositionNode1.setRelation(_patternOIE5.rel);
		this.propositionNode1.setArg(_patternOIE5.obj);
	}
	
	public Proposition getPropositionNode2() {
		return this.propositionNode2;
	}

	public void setPropositionNode2WithOIE5(patternOIE5 _patternOIE5) {
		this.propositionNode1.setSubject(_patternOIE5.sub);
		this.propositionNode1.setRelation(_patternOIE5.rel);
		this.propositionNode1.setArg(_patternOIE5.obj);
	}
	
	public void setPropositionNode2(Proposition _propositionNode2) {
		this.propositionNode2 = _propositionNode2;
	}
	
	public String getTimeNode1() {
		return this.timeNode1;
	}

	public void setTimeNode1(String _timeNode1) {
		this.timeNode1 = _timeNode1;
	}

	public String getTimeNode2() {
		return this.timeNode2;
	}

	public void setTimeNode2(String _timeNode2) {
		this.timeNode2 = _timeNode2;
	}

	public String getTenseNode1() {
		return this.tenseNode1;
	}

	public void setTenseNode1(String _tenseNode1) {
		this.tenseNode1 = _tenseNode1;
	}

	public String getTenseNode2() {
		return tenseNode2;
	}

	public void setTenseNode2(String _tenseNode2) {
		this.tenseNode2 = _tenseNode2;
	}
	
	public String getRelaionType() {
		return this.relaionType;
	}

	public String getLabel1(){
		String result="";
		if (this.Node1.isEvent()==1) {
			result=this.Node1.getEventName4();
		}else{
			result=this.Node1.getTimeName7();
		}
		return result;
	}
	
	public String getLabel2(){
		String result="";
		if (this.Node2.isEvent()==1) {
			result=this.Node2.getEventName4();
		}else{
			result=this.Node2.getTimeName7();
		}
		return result;
	}
	
	
	public void setTenseTime(List<Sentence> pSentenceList){
		
		//1.1a-- Future tense
		// will/MD and play/VB (MD-VB)
		
		//1.1b-- Future perfect tense 
		// will/MD be/VB and played/VBN (MD-VB)
		
		
		//1.2-- Future continuous
		/// will/MD be/VB playing/VBG tennis/NN (MD-VB-VBG)
		
		//2.1-- Present tense
		// is/VBZ hien tai - he plays  (VBZ) or (VBP)
		// amVBP I play tennis
		
		//2.2--Present Continuous (VBP-VBG) or (VBZ-VBG)
		// am/VBP playing/VBG
		// is/VBZ playing/VBG
		// are/VBP playing/VBG
		
		//2.3-- Present perfect (VBP-VBN) OR (VBZ-VBN)
		// have/VBP played/VBN
		// has/VBZ played/VBN
		
		//2.4-- Present passive (VBP-VBN) 
		// am/VBP observed/VBN by/IN camera
		
		//2.5-- Past tense (VBD)
		// played/VBD tennis/NN
		
		//2.6-- Past passive (VBD-VBN)
		// was/VBD observed/VBN by/IN camera/NN ./.
		
		//2.7-- Past continuous (VBD-VBG)
		// was/VBD playing/VBG
		
		//2.8-- Past passive (VBD-VBN)
		// was/VBD observed/VBN by/IN camera/NN
		// were/VBD observed/VBN by/IN camera/NN ./.
		
		//2.9-- Past perfect (VBD-VBN)
		// had/VBD played/VBN tennis/NN
		// He/PRP had/VBD played/VBN tennis/NN ./.
		
		// VBD qua khu
		// VBN passived
		// VBG developing
		// VB to grant
		
		//public static List<String> ruleTlinkTypes = Arrays.asList(ruleTlinks);
		
		int senPostion=1;
		if (txtNormalization.isInteger(this.Node1.getSentencePos2())) {
			senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		}
		Sentence pSentence=pSentenceList.get(senPostion);
		// Xu ly khi Node1 va Node 2 in the same proposition
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		
		String[] arrSub=txtNormalization.stringtoArray(sub);
		String[] arrRel=txtNormalization.stringtoArray(rel);
		String[] arrArg=txtNormalization.stringtoArray(arg);

		List<String> relList=Arrays.asList(arrRel);
		List<String> sentence=new ArrayList<>();
		
		List<KeyWord> keywordList=pSentence.getSentence();
		
		for (int i = 0; i < keywordList.size(); i++) {
			KeyWord tempkeyword=keywordList.get(i);
			sentence.add(tempkeyword.getWord0().toLowerCase());
		}
		
		int index= Collections.indexOfSubList(sentence, relList);
		int lastpoint=index+relList.size();
		
		String mainTense="NONE";

		if (index==-1) {
			String pContext=txtNormalization.listtoString(relList);
			if (pContext.contains("have")
				||pContext.contains("has")
				) {
				mainTense="PRESENT";
			}
			if (pContext.contains("had")){
				mainTense="PAST";
			}
			
		}else{
			for (int i = index; i < lastpoint; i++) {
				KeyWord tempKeyWord=keywordList.get(i);
				String pos=tempKeyWord.getPosTag15();
				String content=tempKeyWord.getWord0().toLowerCase();
				// Dieu kien o vi tri. nay
				// For future
				if (pos.indexOf("MD")!=-1) {
					if (i+1<lastpoint&&keywordList.get(i+1).getPosTag15().indexOf("VB")!=-1) {
						mainTense="FUTURE";
					}
					if ((i+2<lastpoint&&keywordList.get(i+1).getPosTag15().indexOf("VB")!=-1&&keywordList.get(i+2).getPosTag15().indexOf("VBG")!=-1)
						||(i+2<lastpoint&&keywordList.get(i+1).getPosTag15().indexOf("VB")!=-1&&keywordList.get(i+2).getPosTag15().indexOf("RB")!=-1&&keywordList.get(i+3).getPosTag15().indexOf("VBG")!=-1)
					   ) {
						mainTense="FUTURE_CONTINUOUS";
					}
					if ((i+2<lastpoint&&keywordList.get(i+1).getPosTag15().indexOf("VB")!=-1&&keywordList.get(i+2).getPosTag15().indexOf("VBN")!=-1)) {
							mainTense="FUTURE_PERFECT";
					}
				}
				
				// For present
				if (pos.indexOf("VBZ")!=-1||pos.indexOf("VBP")!=-1) {
					mainTense="PRESENT";
					// For present continuous
					if ((i+1<lastpoint&&keywordList.get(i+1).getPosTag15().indexOf("VBG")!=-1)
						||(i+1<lastpoint&&keywordList.get(i+1).getPosTag15().indexOf("RB")!=-1&&keywordList.get(i+2).getPosTag15().indexOf("VBG")!=-1)	
						) {
						mainTense="PRESENT_CONTINUOUS";
					}
					if (i+1<lastpoint&&keywordList.get(i+1).getPosTag15().indexOf("VBN")!=-1
					    &&(content.contains("have")||content.contains("has")||content.contains("'ve"))
						) {
						mainTense="PRESENT_PERFECT";
					}
					if (i+1<lastpoint&&keywordList.get(i+1).getPosTag15().indexOf("VBN")!=-1
						&&(content.contains("is")||content.contains("are")||content.contains("be"))
						) {
						mainTense="PRESENT_PASSIVED";
					}
				}
				
				// For Past
				if (pos.indexOf("VBD")!=-1) {
					mainTense="PAST";
					// For present continuous
					if ((i+1<lastpoint&&keywordList.get(i+1).getPosTag15().indexOf("VBG")!=-1)
					   || (i+1<lastpoint&&keywordList.get(i+1).getPosTag15().indexOf("RB")!=-1&&keywordList.get(i+2).getPosTag15().indexOf("VBG")!=-1)
						) {
						mainTense="PAST_CONTINUOUS";
					}
					if (i+1<lastpoint&&keywordList.get(i+1).getPosTag15().indexOf("VBN")!=-1
					    &&(content.contains("had"))
						) {
						mainTense="PAST_PERFECT";
					}
					if (i+1<lastpoint&&keywordList.get(i+1).getPosTag15().indexOf("VBN")!=-1
						&&(content.contains("was")||content.contains("were"))
						) {
						mainTense="PAST_PASSIVED";
					}
				}
			}
		}
		
		this.tenseNode1=mainTense;
		this.tenseNode2=mainTense;
		
		String time="NONE";
		for (int j = 0; j < keywordList.size(); j++) {  // list of keyword sentence
			KeyWord pkeyword=keywordList.get(j);
			String pTime=pkeyword.getTimeStatus8();
			String context=pkeyword.getWord0().toLowerCase();
			if (pTime.contains("DATE")&&
				(sub.contains(context)||arg.contains(context))	
				) {
				time=pkeyword.getTimeValue9(); 
			}
		}
		
		/*
		// for argument
		List<String> arguList=Arrays.asList(arrArg);
		int indexArg= Collections.indexOfSubList(sentence, arguList);
		int lastpointArg=indexArg+arguList.size();
		
		// for subject
		List<String> subList=Arrays.asList(arrSub);
		int indexSub= Collections.indexOfSubList(sentence, subList);
		int lastpointSub=indexSub+subList.size();
		
		if (indexArg!=-1) {
			for (int i = indexArg; i < lastpointArg; i++) {
				KeyWord tempKeyWord=keywordList.get(i);
				String pTime=tempKeyWord.getTimeStatus8();
				if (pTime.contains("DATE")) {
					time=tempKeyWord.getTimeValue9(); 
				}
			}
		}

		if (indexSub!=-1) {
			for (int i = indexSub; i < lastpointSub; i++) {
				KeyWord tempKeyWord=keywordList.get(i);
				String pTime=tempKeyWord.getTimeStatus8();
				if (time.contains("None")&&pTime.contains("DATE")) {
					time=tempKeyWord.getTimeValue9(); 
				}
			}
		}
		*/
		this.timeNode1=time;
		this.timeNode2=time;

	}

	// Can kiem tra lai
	// Update on 21 May 2018
	public int isExist(List<relationNodes> _relationNodeList){
		
		int result=0;
		// gia tri goc
		String orgNode1="";
		String orgNode2="";
		
		if (this.Node1.isEvent()==1) {
			orgNode1=this.Node1.getEventName4();
		} else{
			orgNode1=this.Node1.getTimeName7();
		}
		
		if (this.Node2.isEvent()==1) {
			orgNode2=this.Node2.getEventName4();
		} else{
			orgNode2=this.Node2.getTimeName7();
		}
		
		for (int i = 0; i < _relationNodeList.size(); i++) {
			relationNodes relNodes=_relationNodeList.get(i);
			
			KeyWord _node1=relNodes.getNode1();
			KeyWord _node2=relNodes.getNode2();
			
			String _extNode1="";
			String _extNode2="";
			
			if (_node1.isEvent()==1) {
				_extNode1=_node1.getEventName4();
			}else{ 
				_extNode1=_node1.getTimeName7();
			}
			
			if (_node2.isEvent()==1) {
				_extNode2=_node2.getEventName4();
			}else{ 
				_extNode2=_node2.getTimeName7();
			}
			/*
			if (_extNode1.toLowerCase().equals(_extNode2.toLowerCase())) {
				result=1;
			}
			*/
			if (
				orgNode1.toLowerCase().equals(_extNode1.toLowerCase())&&orgNode2.toLowerCase().equals(_extNode2.toLowerCase())
				||orgNode1.toLowerCase().equals(_extNode2.toLowerCase())&&orgNode2.toLowerCase().equals(_extNode1.toLowerCase())
					//this.Node1.getWord0().toLowerCase().equals(node1.getWord0().toLowerCase())&&this.Node2.getWord0().toLowerCase().equals(node2.getWord0().toLowerCase())
					//||
					//this.Node1.getWord0().toLowerCase().equals(node2.getWord0().toLowerCase())&&this.Node2.getWord0().toLowerCase().equals(node1.getWord0().toLowerCase())
					) {
					result=1;
			}
			
			/*
			if ( //phan nay sai
				this.Node1.getWord0().toLowerCase().equals(node1.getWord0().toLowerCase())&&this.Node2.getWord0().toLowerCase().equals(node2.getWord0().toLowerCase())
				||
				this.Node1.getWord0().toLowerCase().equals(node2.getWord0().toLowerCase())&&this.Node2.getWord0().toLowerCase().equals(node1.getWord0().toLowerCase())
				) {
				result=1;
			}
			*/
		}
		return result;
	}

	// update 18 March 2019
	
	// Update on 21 May 2018
	public relationNodes isExistReturnRelation(List<relationNodes> _relationNodeList){
		
		relationNodes result=new relationNodes();
		// gia tri goc
		String orgNode1="";
		String orgNode2="";
		
		if (this.Node1.isEvent()==1) {
			orgNode1=this.Node1.getEventName4();
		} else{
			orgNode1=this.Node1.getTimeName7();
		}
		
		if (this.Node2.isEvent()==1) {
			orgNode2=this.Node2.getEventName4();
		} else{
			orgNode2=this.Node2.getTimeName7();
		}
		
		for (int i = 0; i < _relationNodeList.size(); i++) {
			relationNodes relNodes=_relationNodeList.get(i);
			
			KeyWord _node1=relNodes.getNode1();
			KeyWord _node2=relNodes.getNode2();
			
			String _extNode1="";
			String _extNode2="";
			
			if (_node1.isEvent()==1) {
				_extNode1=_node1.getEventName4();
			}else{ 
				_extNode1=_node1.getTimeName7();
			}
			
			if (_node2.isEvent()==1) {
				_extNode2=_node2.getEventName4();
			}else{ 
				_extNode2=_node2.getTimeName7();
			}

			if (
				orgNode1.toLowerCase().equals(_extNode1.toLowerCase())&&orgNode2.toLowerCase().equals(_extNode2.toLowerCase())
				||orgNode1.toLowerCase().equals(_extNode2.toLowerCase())&&orgNode2.toLowerCase().equals(_extNode1.toLowerCase())
					) {
					result=relNodes;
			}
			
		}
		return result;
	}
	
	public int isStringInRelationNode1(KeyWord _keyword){
		int result=0;
		if (this.Node1.getWord0().toLowerCase().equals(_keyword.getWord0().toLowerCase())) {
			result=1;
		}
		return result;
	}
	
	// so sanh dua tren Word0
	public int isStringInRelationNode2(KeyWord _keyword){
		int result=0;
		if (this.Node2.getWord0().toLowerCase().equals(_keyword.getWord0().toLowerCase())) {
			result=1;
		}
		return result;
	}
	
	public int isKeyWordInRelationNode(KeyWord _keyword){
		int result=0;
		if (this.Node1.getWord0().toLowerCase().equals(_keyword.getWord0().toLowerCase())
			|| this.Node2.getWord0().toLowerCase().equals(_keyword.getWord0().toLowerCase())) {
			result=1;
		}
		return result;
	}
	
	
	public int isTextInRelationNode(String _text){ //so sanh Event name va Time name
		int result=0;
		String contextNode1="";
		String contextNode2="";
		
		if (this.Node1.isEvent()==1) {
			contextNode1=this.Node1.getEventName4();
		}else contextNode1=this.Node1.getTimeName7();
		
		if (this.Node2.isEvent()==1) {
			contextNode2=this.Node2.getEventName4();
		}else contextNode2=this.Node2.getTimeName7();
		
		if (contextNode1.toLowerCase().equals(_text.toLowerCase())
			|| contextNode2.toLowerCase().equals(_text.toLowerCase())) {
			result=1;
		}
		return result;
	}

	// Added on 10 June 2018
	public int isNodeEventIN(KeyWord pEvent){
		int result=0;
		String contextNode1="";
		String contextNode2="";
		// Relation Node
		if (this.Node1.isEvent()==1) {
			contextNode1=this.Node1.getEventName4();
		}else contextNode1=this.Node1.getTimeName7();
		
		if (this.Node2.isEvent()==1) {
			contextNode2=this.Node2.getEventName4();
		}else contextNode2=this.Node2.getTimeName7();
		
		// External event
		String pEventcontext="";
		if (pEvent.isEvent()==1) {
			pEventcontext=pEvent.getEventName4();
		}else{ 
			pEventcontext=pEvent.getTimeName7();
		}
		
		if (contextNode1.toLowerCase().equals(pEventcontext.toLowerCase())
			||contextNode2.toLowerCase().equals(pEventcontext.toLowerCase())){
				result=1;
		}
		
		return result;
	}
	
	public int isNodeIN(KeyWord pEvent){
		int result=0;
		// Relation Node1
		String contextNode1="";
		if (this.Node1.isEvent()==1) {
			contextNode1=this.Node1.getEventName4();
		}else contextNode1=this.Node1.getTimeName7();
		
		// Relation Node 2
		String contextNode2="";
		if (this.Node2.isEvent()==1) {
			contextNode2=this.Node2.getEventName4();
		}else contextNode2=this.Node2.getTimeName7();

		// External event
		String pEventcontext="";
		if (pEvent.isEvent()==1) {
			pEventcontext=pEvent.getEventName4();
		}else{ 
			pEventcontext=pEvent.getTimeName7();
		}
		if (contextNode1.toLowerCase().equals(pEventcontext.toLowerCase())
			||contextNode2.toLowerCase().equals(pEventcontext.toLowerCase())	
		   ){
				result=1;
		}
		return result;
	}
	
	public String toString(){
		String result="";
		
		if (this.Node1.isEvent()==1&&this.Node2.isEvent()==1) {
			result=this.Node1.getEventName4()+" "+this.Node2.getEventName4()+ " "+this.propositionNode1+" "+this.propositionNode2;
		}
		
		if (this.Node1.isEvent()==1&&this.Node2.isTime()==1) {
			
			result=this.Node1.getEventName4()+" "+this.Node2.getTimeName7()+ " "+this.propositionNode1+" "+this.propositionNode2;
		}
		
		if (this.Node1.isTime()==1&&this.Node2.isEvent()==1) {
			result=this.Node1.getTimeName7()+" "+this.Node2.getEventName4()+ " "+this.propositionNode1+" "+this.propositionNode2;
		}
		
		if (this.Node1.isTime()==1&&this.Node2.isTime()==1) {
			result=this.Node1.getTimeName7()+" "+this.Node2.getTimeName7()+ " "+this.propositionNode1+" "+this.propositionNode2;
		}
		
		return result+ " "+this.getRelaionType();
	}
	
	public List<relationNodes> loadSource(List<String> resourceRecord, String colID) throws IOException{
		List<relationNodes> result=new ArrayList<>();
		/*
		BufferedReader br = new BufferedReader(new FileReader(fileResource));
		String sentence = null;
		List<String> allString=new ArrayList<>(); 
		while((sentence = br.readLine()) != null){
			allString.add(sentence);
		}
		*/
		List<String> sentences=new ArrayList<String>();
		
		for (int i = 0; i < resourceRecord.size(); i++) {
			String[] pLine=resourceRecord.get(i).split("\t");
			String ID=pLine[0];
			
			String txt1=pLine[1];
			String txt2=pLine[2];
			String txt3=pLine[3];
			
			KeyWord Node1=new KeyWord();
			if (txt1.indexOf("e")>-1) {
				Node1.setEventName4(txt1);
			}
			if (txt1.indexOf("tmx")>-1) {
				Node1.setTimeName7(txt1);
			}
			
			KeyWord Node2=new KeyWord();
			if (txt2.indexOf("e")>-1) {
				Node2.setEventName4(txt2);
			} 
			if (txt2.indexOf("tmx")>-1) {
				Node2.setTimeName7(txt2);
			}
			relationNodes pRelNode=new relationNodes(Node1, Node2);
			pRelNode.relaionType=txt3;
			if (ID.equals(colID)) { //pID tu ben ngoai cho tung document
				result.add(pRelNode);
				System.out.println(pRelNode.toString());
			}
		}
		return result;
	}
	// Update on 16 March 2019
	public List<relationNodes> loadCausalSource(List<String> resourceRecord, String colID) throws IOException{
		List<relationNodes> result=new ArrayList<>();
		/*
		BufferedReader br = new BufferedReader(new FileReader(fileResource));
		String sentence = null;
		List<String> allString=new ArrayList<>(); 
		while((sentence = br.readLine()) != null){
			allString.add(sentence);
		}
		*/
		List<String> sentences=new ArrayList<String>();
		
		for (int i = 0; i < resourceRecord.size(); i++) {
			String[] pLine=resourceRecord.get(i).split("\t");
			String ID=pLine[0];
			
			String txt1=pLine[1];
			String txt2=pLine[2];
			String txt3=pLine[3];
			
			KeyWord Node1=new KeyWord();
			if (txt1.indexOf("e")>-1) {
				Node1.setEventName4(txt1);
			}
			if (txt1.indexOf("tmx")>-1) {
				Node1.setTimeName7(txt1);
			}
			
			KeyWord Node2=new KeyWord();
			if (txt2.indexOf("e")>-1) {
				Node2.setEventName4(txt2);
			} 
			if (txt2.indexOf("tmx")>-1) {
				Node2.setTimeName7(txt2);
			}
			relationNodes pRelNode=new relationNodes(Node1, Node2);
			pRelNode.relaionType=txt3;
			if (txt3.indexOf("CLINK")!=-1||txt3.indexOf("CLINK-R")!=-1) {
				if (ID.equals(colID)) { //pID tu ben ngoai cho tung document
					result.add(pRelNode);
					System.out.println(pRelNode.toString());
				}
			}
			
		}
		return result;
	}

	// Update on 16 March 2019
	public List<relationNodes> loadTemporalSource(List<String> resourceRecord, String colID) throws IOException{
		List<relationNodes> result=new ArrayList<>();
		List<String> sentences=new ArrayList<String>();
		
		for (int i = 0; i < resourceRecord.size(); i++) {
			String[] pLine=resourceRecord.get(i).split("\t");
			String ID=pLine[0];
			
			String txt1=pLine[1];
			String txt2=pLine[2];
			String txt3=pLine[3];
			
			KeyWord Node1=new KeyWord();
			if (txt1.indexOf("e")>-1) {
				Node1.setEventName4(txt1);
			}
			if (txt1.indexOf("tmx")>-1) {
				Node1.setTimeName7(txt1);
			}
			
			KeyWord Node2=new KeyWord();
			if (txt2.indexOf("e")>-1) {
				Node2.setEventName4(txt2);
			} 
			if (txt2.indexOf("tmx")>-1) {
				Node2.setTimeName7(txt2);
			}
			relationNodes pRelNode=new relationNodes(Node1, Node2);
			pRelNode.relaionType=txt3;
			if ((txt3.indexOf("CLINK")==-1)){//||txt3.indexOf("CLINK-R")!=-1)) {
				if (ID.equals(colID)) { //pID tu ben ngoai cho tung document
					result.add(pRelNode);
					System.out.println(pRelNode.toString());
				}
			}
			
		}
		return result;
	}	
	
	// Da check
	public int isIdentity(){
		int result=0;
		
		String[] SIM_verb_initiation = {"say", "says", "said", "see", "saw", "seen"}; // this list will be updated
		List<String> SIM_verb_init_list = Arrays.asList(SIM_verb_initiation);
		
		String[] SIMATT_initiation = {"bombings", "bombing", "attack", "attacks"}; // this list will be updated
		List<String> SIMATT_list = Arrays.asList(SIMATT_initiation);
		
		if (this.Node1.isEvent()==1&&this.Node2.isEvent()==1) {
			
			if (SIM_verb_init_list.contains(this.Node1.getOrginalWord3().toLowerCase())&&SIM_verb_init_list.contains(this.Node2.getOrginalWord3().toLowerCase())) {
				result=1;
				this.relaionType="SIMULTANEOUS/IDENTITY";
			}
			else if (this.Node1.getOrginalWord3().toLowerCase().equals(this.Node2.getOrginalWord3().toLowerCase())
				&&!this.Node1.getEventName4().toLowerCase().equals(this.Node2.getEventName4().toLowerCase())) {
				result=1;
				this.relaionType="IDENTITY";
			}
			else if (SIMATT_list.contains(this.Node1.getWord0().toLowerCase())
					&&SIMATT_list.contains(this.Node2.getWord0().toLowerCase())) {
				result=1;
				this.relaionType="IDENTITY";
			}
		}
		return result;
	}
	// 
	public List<relationNodes> relGeneraitionByKeyWordContentOIE5(List<KeyWordPropositionOIE5> _keyWordList) {
		
		List<relationNodes> result=new ArrayList<>();
		KeyWord pKeyWord1=new KeyWord();
		KeyWord pKeyWord2=new KeyWord();
		
		for (int i = 0; i < _keyWordList.size(); i++) {
			
			pKeyWord1=_keyWordList.get(i).getKeyword();
			for (int j = i+1; j < _keyWordList.size(); j++) {
				
				pKeyWord2=_keyWordList.get(j).getKeyword();
				relationNodes relNodes=new relationNodes(pKeyWord1, pKeyWord2);
				relNodes.setPropositionNode1WithOIE5(_keyWordList.get(i).getProposition());
				relNodes.setPropositionNode2WithOIE5(_keyWordList.get(j).getProposition());
				
				if (relNodes.isIdentity()==1&&relNodes.isExist(result)!=1) {
					result.add(relNodes);
				}
			}
		}
		return result;
	}
	
	public List<relationNodes> relGeneraitionByKeyWordContent(List<KeyWordProposition> _keyWordList) {
		
		List<relationNodes> result=new ArrayList<>();
		KeyWord pKeyWord1=new KeyWord();
		KeyWord pKeyWord2=new KeyWord();
		
		for (int i = 0; i < _keyWordList.size(); i++) {
			
			pKeyWord1=_keyWordList.get(i).getKeyword();
			for (int j = i+1; j < _keyWordList.size(); j++) {
				
				pKeyWord2=_keyWordList.get(j).getKeyword();
				relationNodes relNodes=new relationNodes(pKeyWord1, pKeyWord2);
				relNodes.setPropositionNode1(_keyWordList.get(i).getProposition());
				relNodes.setPropositionNode2(_keyWordList.get(j).getProposition());
				
				if (relNodes.isIdentity()==1&&relNodes.isExist(result)!=1) {
					result.add(relNodes);
				}
			}
		}
		return result;
	}
	
	// Update 2 June
	// is N-N
	public int isNN(){
		int result=0;
		if (this.Node1.isNoun()==1&&this.Node2.isNoun()==1) {
			result=1;
		}
		return result;
	}
	
	// is N-V
	public int isNV(){
		int result=0;
		if (this.Node1.isNoun()==1&&this.Node2.isVerb()==1) {
			result=1;
		}
		return result;
	}
	
	// is V-N
	public int isVN(){
		int result=0;
		if (this.Node1.isVerb()==1&&this.Node2.isNoun()==1) {
			result=1;
		}
		return result;
	}
	
	// is V-V
	public int isVV(){
		int result=0;
		if (this.Node1.isVerb()==1&&this.Node2.isVerb()==1) {
			result=1;
		}
		return result;
	}
	
	// Noun-Noun is in NN-PP-NN
	// co su dung cho Event la verb
	public int isEventNN_PP_EventNN(List<Sentence> _sentenceList){
		int result=0;
		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		String[] LEADABILITY_initiation = {"can lead", "could lead","might lead", "will lead"};
		List<String> LEADABILITY_list = Arrays.asList(LEADABILITY_initiation);
		
		// update if with BEFORE condition, 27 July
		String[] BEFORE_initiation = {"issued", "established", "said", "says","say", "ask","asked","asks", 
				                       "tell", "tells", "told", "try", "tries", "tried"};
		List<String> BEFORE_list = Arrays.asList(BEFORE_initiation);
		if (!BEFORE_list.contains(this.Node1.getWord0())
			&&!BEFORE_list.contains(this.Node2.getWord0().toLowerCase())
			&&!LEADABILITY_list.contains(rel)
			) {

			String idNode1=this.Node1.getId1(); //t138
			String idNode2=this.Node2.getId1();
			
			int noNode1=Integer.parseInt(idNode1.replace("t", "")); 
			System.out.println(noNode1); //138
			
			//int noNode2=Integer.parseInt(idNode1.replace("t", ""));
			
			String tempStr=this.Node1.getDpStatus16().replace("||", "-tab-");
			String[] dpNode1=tempStr.split("-tab-"); // T2, T5, T6 (select Select keywords with IDs T5 and T6)
			//System.out.println(this.Node1.getDpStatus16());
			//String[] dpNode2=this.Node2.getDpStatus16().split("||"); //no need
			
			List<Integer> noTempNodes=new ArrayList<>();
			List<String> noIDNodes=new ArrayList<>();
			
			int INchecking=0;

			for (int i = 0; i < dpNode1.length; i++) {
				//System.out.println(dpNode1[i]);
				String[] dpStatusArr=dpNode1[i].split(":"); //value t6:SBJ
				String tValueNode=dpStatusArr[0];  //value t6
				// Can lay vi tri 6
				int noNode=0;
				if (tValueNode.indexOf("t")!=-1){//!tValueNode.equals("0")) {
					noNode=Integer.parseInt(tValueNode.replace("t", ""));
				}
				
				if (noNode>noNode1) {
					noTempNodes.add(noNode);
					noIDNodes.add(tValueNode);
					//System.out.println(tValueNode);
					//System.out.println(noNode);
					KeyWord KeyWordIN=pSentence.getKeywordByID(tValueNode);
					if (KeyWordIN.getPosTag15().indexOf("IN")==0&&KeyWordIN.getWord0().equals("in")) {
						INchecking=0;
					}else if (KeyWordIN.getPosTag15().indexOf("IN")==0&&!KeyWordIN.getWord0().equals("in")) {
						INchecking=1;
					}
				}
			}
			for (int i = 0; i < noIDNodes.size(); i++) {
				KeyWord pKeyword=pSentence.getKeywordByID(noIDNodes.get(i));
				//System.out.println(pKeyword.getDpStatus16());			
				String _tempStr=pKeyword.getDpStatus16().replace("||", "-tab-");
				String[] _dpNodeTemp=_tempStr.split("-tab-");
				//System.out.println(_tempStr);
				for (int j = 0; j < _dpNodeTemp.length; j++) {
					String[] dpStatusArr=_dpNodeTemp[j].split(":"); //value t6:SBJ
					String tValueNode=dpStatusArr[0];  //value t6
					//System.out.println(tValueNode);
					//System.out.println(idNode2);
					if ((tValueNode.equals(idNode2)&&sub.indexOf("while")!=-1)
						||(tValueNode.equals(idNode2)&&rel.indexOf("while")!=-1)	
						||(tValueNode.equals(idNode2)&&arg.indexOf("while")!=-1)
						) {
						result=1;
						this.relaionType="SIMULTANEOUS";
					}
					// update 29 July
					else if (tValueNode.equals(idNode2)&&INchecking==1&&result==0&&arg.indexOf("until ")!=-1) {
						result=1;
						this.relaionType="ENDED_BY";
					}
					// can than phan nay
					else if (tValueNode.equals(idNode2)&&INchecking==1&&result==0&&arg.indexOf("as ")!=-1) {
						result=1;
						this.relaionType="SIMULTANEOUS";
					}
					else if ((tValueNode.equals(idNode2)&&INchecking==1&&result==0&&arg.indexOf("before ")!=-1)) {
						result=1;
						this.relaionType="BEFORE";
					}
					//-- update 29 July
					// update 31 July
					else if ((tValueNode.equals(idNode2)&&INchecking==1&&result==0&&arg.indexOf("over ")!=-1)) {
						result=1;
						this.relaionType="AFTER";
					}
					else if ((tValueNode.equals(idNode2)&&INchecking==1&&result==0&&arg.indexOf("from ")!=-1)) {
						result=1;
						this.relaionType="BEFORE";
					}
					// update 23 August
					else if ((tValueNode.equals(idNode2)&&INchecking==1&&result==0&&arg.indexOf("at ")!=-1)) {
						result=1;
						this.relaionType="IS_INCLUDED";
					}
					// --update 23 August
					else if (tValueNode.equals(idNode2)&&INchecking==1&&result==0) {
						result=1;
						this.relaionType="AFTER";
					}else if (tValueNode.equals(idNode2)&&INchecking==0&&result==0) {
						result=1;
						this.relaionType="IS_INCLUDED";
					}
				}			
			}
		}
		return result;
	}
	
	// Rule for clause S-V-O
	// Event V(inV)-V(passived in O)
	public int isEventVRel_EventVArgument(){ // Rel-Argument
		int result=0;
		// initial setting
		String[] verbSET_initiation = {"set", "assign"}; // this list will be updated
		List<String> verbSET_init_list = Arrays.asList(verbSET_initiation);
		
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		
		int whilePos=arg.indexOf("while");
		int whenPos=arg.indexOf("when");

		// xu ly before if gap sub and rel
		// UPDATE 28 July
		// Can phai can trong
		if (this.Node1.isVerb()==1&&rel.indexOf(this.Node1.getWord0().toLowerCase())!=-1) {
			if (arg.indexOf("after")!=-1&&arg.indexOf("before")<arg.indexOf(this.Node2.getWord0().toLowerCase())) {
				result=1;
				this.relaionType="AFTER";
			}
		}else if (this.Node2.isVerb()==1&&rel.indexOf(this.Node2.getWord0().toLowerCase())!=-1) {
			if (arg.indexOf("after")!=-1&&arg.indexOf("before")<arg.indexOf(this.Node1.getWord0().toLowerCase())) {
				result=1;
				this.relaionType="BEFORE";
			}
		}
		
		//-- update 28 July
		// update 26 July 2018       
		if (result==0) {
			// update on 12 Sept.
			if ((this.Node1.isVerb()==1&&rel.indexOf(this.Node1.getWord0().toLowerCase())!=-1
				 	  &&whenPos>-1&&arg.indexOf(this.Node2.getWord0().toLowerCase())>whenPos
				 	  &&this.Node1.getWord0().equals("said"))
				) {
				result=1;
				this.relaionType="AFTER";
			}else if ((this.Node2.isVerb()==1&&rel.indexOf(this.Node2.getWord0().toLowerCase())!=-1
				 	  &&whenPos>-1&&arg.indexOf(this.Node1.getWord0().toLowerCase())>whenPos
				 	  &&this.Node2.getWord0().equals("said"))  
					) {
				result=1;
				this.relaionType="BEFORE";
			}
			// update on 12 Sept.
			else if ((this.Node1.isVerb()==1&&rel.indexOf(this.Node1.getWord0().toLowerCase())!=-1
				 	  &&whilePos>-1&&arg.indexOf(this.Node2.getWord0().toLowerCase())>whilePos)
				||	
				(this.Node1.isVerb()==1&&rel.indexOf(this.Node1.getWord0().toLowerCase())!=-1
			 	  &&whenPos>-1&&arg.indexOf(this.Node2.getWord0().toLowerCase())>whenPos)
					) {
				result=1;
				this.relaionType="INCLUDES";
			}else if ((this.Node2.isVerb()==1&&rel.indexOf(this.Node2.getWord0().toLowerCase())!=-1
				 	  &&whilePos>-1&&arg.indexOf(this.Node1.getWord0().toLowerCase())>whilePos)  
					 ||
					 (this.Node2.isVerb()==1&&rel.indexOf(this.Node2.getWord0().toLowerCase())!=-1
				 	  &&whenPos>-1&&arg.indexOf(this.Node1.getWord0().toLowerCase())>whenPos)
					) {
				result=1;
				this.relaionType="INCLUDES";
			}
			//-- update 26 July
			else if (this.Node1.isVerb()==1&&this.Node2.isVerbVBN()==1) {
				if (rel.indexOf(this.Node1.getWord0().toLowerCase())!=-1&&arg.indexOf(this.Node2.getWord0().toLowerCase())!=-1) {
					//String verbNode1=this.Node1
					result=1;
					this.relaionType="AFTER";
				}	
			}else if(this.Node1.isVerb()==1&&this.Node2.isVerb()==1){
					if (verbSET_init_list.contains(this.Node1.getOrginalWord3().toLowerCase())) {
					result=1;
					this.relaionType="BEFORE";
				  }
			}else if (this.Node1.isVerb()==1&&rel.indexOf(this.Node1.getWord0().toLowerCase())!=-1
				 	  &&whilePos>-1&&arg.indexOf(this.Node2.getWord0().toLowerCase())>whilePos  
					 ) {
				result=1;
				this.relaionType="INCLUDES";
			}else if (this.Node2.isVerb()==1&&rel.indexOf(this.Node2.getWord0().toLowerCase())!=-1
				 	  &&whilePos>-1&&arg.indexOf(this.Node1.getWord0().toLowerCase())>whilePos  
					 ) {
				result=1;
				this.relaionType="INCLUDES";
			}

		}
		
		return result;
	}
	// update on 7 July
	// Event V(in Argument)-N(in Argument)
	public int isEventVArg_EventNArg(List<Sentence> _sentenceList){ // Rel-Argument
		int result=0;
		
		String[] verbEND_initiation = {"ended", "end", "finished", "finish", "closed", "close"}; // this list will be updated
		List<String> verbEND_init_list = Arrays.asList(verbEND_initiation);
		
		String[] DURING_initiation = {"hold", "held", "holds"}; // this list will be updated
		List<String> DURING_list = Arrays.asList(DURING_initiation);
		
		
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		String node1Word=this.Node1.getWord0().toLowerCase();
		String node2Word=this.Node2.getWord0().toLowerCase();
		
		// update 19 august
		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		List<KeyWord> keywordSenList=pSentence.getSentence();
		for (int i = 0; i < keywordSenList.size()-2; i++) {
			if (keywordSenList.get(i).getWord0().toLowerCase().equals(this.Node1.getWord0().toLowerCase())
				&&keywordSenList.get(i+1).getWord0().toLowerCase().equals("will")
				&&keywordSenList.get(i+2).getWord0().toLowerCase().equals(this.Node2.getWord0().toLowerCase())		
				) {
				result=1;
				this.relaionType="BEFORE";
			}else if (keywordSenList.get(i).getWord0().toLowerCase().equals(this.Node2.getWord0().toLowerCase())
					&&keywordSenList.get(i+1).getWord0().toLowerCase().equals("will")
					&&keywordSenList.get(i+2).getWord0().toLowerCase().equals(this.Node1.getWord0().toLowerCase())		
					) {
					result=1;
					this.relaionType="AFTER";
			}
		}
		// --update 19 august
		//int node1Pos=arg.indexOf(this.Node1.getWord0().toLowerCase());
		//int untiltxtPos=arg.indexOf("until");
		//int node2Pos=arg.indexOf(this.Node2.getWord0().toLowerCase());
		
		if (result==0) {
			if (this.Node1.isVerb()==1&&this.Node2.isNoun()==1) {
				if (arg.indexOf(this.Node1.getWord0().toLowerCase())!=-1&&arg.indexOf(this.Node2.getWord0().toLowerCase())!=-1
					&&verbEND_init_list.contains(node1Word)) {
					result=1;
					this.relaionType="ENDED_BY";
				}
				// update on 20 August
				else if (arg.indexOf(this.Node1.getWord0().toLowerCase())!=-1&&arg.indexOf(this.Node2.getWord0().toLowerCase())!=-1
						&&DURING_list.contains(node1Word)) {
						result=1;
						this.relaionType="DURING";
				}
				else if (arg.indexOf(this.Node1.getWord0().toLowerCase())!=-1&&arg.indexOf(this.Node2.getWord0().toLowerCase())!=-1) {
					result=1;
					this.relaionType="SIMULTANEOUS";
				}	
			}else if (this.Node2.isVerb()==1&&this.Node1.isNoun()==1) {
					if (arg.indexOf(this.Node2.getWord0().toLowerCase())!=-1&&arg.indexOf(this.Node1.getWord0().toLowerCase())!=-1
						&&verbEND_init_list.contains(node2Word)) {
						result=1;
						this.relaionType="ENDED_BY";
					}
				    // update on 20 August
					else if (arg.indexOf(this.Node2.getWord0().toLowerCase())!=-1&&arg.indexOf(this.Node1.getWord0().toLowerCase())!=-1
							&&DURING_list.contains(node2Word)) {
							result=1;
							this.relaionType="DURING";
					}
				    else if (arg.indexOf(this.Node2.getWord0().toLowerCase())!=-1&&arg.indexOf(this.Node1.getWord0().toLowerCase())!=-1) {
						result=1;
						this.relaionType="SIMULTANEOUS";
					}	
				}
		}
		return result;
	}
	
	public int isEventV_EventVCausalAtFinal(){ // Rel-Argument (in the same position, put it in the final)
		int result=0;
		String[] REASON_initiation = {"causes", "cause","caused", "causing", "making", "made", "make", "makes"}; // this list will be updated
		List<String> REASON_list = Arrays.asList(REASON_initiation);
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		
		String idNode1=this.Node1.getId1(); //t138
		String idNode2=this.Node2.getId1();
		
		int noNode1Pos=Integer.parseInt(idNode1.replace("t", "")); 
		int noNode2Pos=Integer.parseInt(idNode2.replace("t", ""));
		
		
		String content="";
		if (sub.indexOf("because")!=-1||sub.indexOf("due to")!=-1
		   ||rel.indexOf("because")!=-1||rel.indexOf("due to")!=-1
		   ||arg.indexOf("because")!=-1||arg.indexOf("due to")!=-1) {
		
			if (noNode1Pos>noNode2Pos) {
				result=1;
				this.relaionType="BEFORE";
			}else{
				result=1;
				this.relaionType="BEFORE";
			}
		}
		// update on 30 July 2018
		if (REASON_list.contains(this.Node1.getWord0().toLowerCase())
			||
			REASON_list.contains(this.Node2.getWord0().toLowerCase())
				) {
			result=1;
			this.relaionType="SIMULTANEOUS";
		}
		//--update on 30 July 2018
		return result;
	}
	// --update on 7 July
	
	// update on 17 July
	public int isEventS_EventFutureV(){ // Rel-Argument (in the same position, put it in the final)
		int result=0;
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		
		if (this.getTenseNode1()=="FUTURE"||this.getTenseNode1()=="FUTURE_CONTINUOUS") {
			if (sub.indexOf(this.Node1.getWord0().toLowerCase())!=-1
				&&this.Node2.isVerb()==1
				&&rel.indexOf(this.Node2.getWord0().toLowerCase())!=-1
			   ){
				result=1;
				this.relaionType="BEFORE";
				
			}else if (sub.indexOf(this.Node2.getWord0().toLowerCase())!=-1
				&&this.Node1.isVerb()==1
				&&rel.indexOf(this.Node1.getWord0().toLowerCase())!=-1) {
				result=1;
				this.relaionType="AFTER";
			}
		}
		
		return result;
	}
	// -- update 17 July
	
	// update on 17 July
	public int isDeclaredEvent(){ // Rel-Argument (in the same position, put it in the final)
		int result=0;
		String[] DECLARE_initiation = {"said", "say", "report", "reported", "says", "reports"};
		List<String> DECLARE_init_list = Arrays.asList(DECLARE_initiation);
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		
		if (this.Node1.isEvent()==1&&DECLARE_init_list.contains(this.Node1.getWord0().toLowerCase())
			&&this.Node2.isVerbVBD()==1	
		   ) {
			result=1;
			this.relaionType="AFTER";
		}else if (this.Node2.isEvent()==1&&DECLARE_init_list.contains(this.Node2.getWord0().toLowerCase())
			&&this.Node1.isVerbVBD()==1) {
			result=1;
			this.relaionType="BEFORE";
		}
		
		return result;
	}
	// -- update 17 July

	// update on 17 July
	public int isIssueEvent(){ // Rel-Argument (in the same position, put it in the final)
		int result=0;
		String[] ISSUE_initiation = {"issue", "issued"};
		List<String> ISSUE_init_list = Arrays.asList(ISSUE_initiation);
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		
		if (this.Node1.isEvent()==1&&ISSUE_init_list.contains(this.Node1.getWord0().toLowerCase())				) {
			result=1;
			this.relaionType="AFTER";
		}else if (this.Node2.isEvent()==1&&ISSUE_init_list.contains(this.Node2.getWord0().toLowerCase())) {
			result=1;
			this.relaionType="BEFORE";
		}
		
		return result;
	}
	// -- update 17 July

	// update on 25 July
	public int isPredictSIMEvent(){ // Rel-Argument (in the same position, put it in the final)
		int result=0;
		String[] PREDICT_initiation = {"predict", "think","thought","thinks", "predicted", "predicts"};
		List<String> PREDICT_list = Arrays.asList(PREDICT_initiation);
		
		String[] CURRENT_initiation = {"please", "pleased", "like", "liked"};
		List<String> CURRENT_list = Arrays.asList(CURRENT_initiation);
		
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		
		if (this.Node1.isVerb()==1
			&&rel.indexOf(this.Node1.getWord0().toLowerCase())!=-1
			&&PREDICT_list.contains(this.Node1.getWord0().toLowerCase())) {
			result=1;
			this.relaionType="BEFORE";
		}else if (this.Node1.isVerb()==1
			&&rel.indexOf(this.Node1.getWord0().toLowerCase())!=-1
			&&CURRENT_list.contains(this.Node1.getWord0().toLowerCase())) {
			result=1;
			this.relaionType="SIMULTANEOUS";
		}else if (this.Node2.isVerb()==1
			&&rel.indexOf(this.Node2.getWord0().toLowerCase())!=-1
			&&PREDICT_list.contains(this.Node2.getWord0().toLowerCase())) {
			result=1;
			this.relaionType="AFTER";
		}else if (this.Node2.isVerb()==1
			&&rel.indexOf(this.Node2.getWord0().toLowerCase())!=-1
			&&CURRENT_list.contains(this.Node2.getWord0().toLowerCase())) {
			result=1;
			this.relaionType="SIMULTANEOUS";
		}
		
		return result;
	}
	// -- update 25 July

	// update on 25 July
	public int isLEADEvent(){ 
		int result=0;
		String[] LEAD_initiation = {"lead", "leads","led"};
		List<String> LEAD_list = Arrays.asList(LEAD_initiation);
		
		String[] LEADABILITY_initiation = {"can lead", "could lead","might lead", "will lead"};
		List<String> LEADABILITY_list = Arrays.asList(LEADABILITY_initiation);
		
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		
		if ((this.Node1.isVerb()==1
			&&rel.indexOf(this.Node1.getWord0().toLowerCase())!=-1
			&&LEADABILITY_list.contains(rel))
			) {
			result=1;
			this.relaionType="BEFORE";
		}else if ((this.Node2.isVerb()==1
				&&rel.indexOf(this.Node1.getWord0().toLowerCase())!=-1
				&&LEADABILITY_list.contains(rel))
				) {
				result=1;
				this.relaionType="AFTER";
		}else if (this.Node1.isVerb()==1
			&&rel.indexOf(this.Node1.getWord0().toLowerCase())!=-1
			&&LEAD_list.contains(this.Node1.getWord0().toLowerCase())) {
			result=1;
			this.relaionType="IS_INCLUDED";
		}else if (this.Node2.isVerb()==1
			&&rel.indexOf(this.Node1.getWord0().toLowerCase())!=-1
			&&LEAD_list.contains(this.Node1.getWord0().toLowerCase())) {
			result=1;
			this.relaionType="IS_INCLUDED";
		}else if (rel.indexOf("have led")!=-1||rel.indexOf("had led")!=-1) {
				result=1;
				this.relaionType="IS_INCLUDED";
			} 
		return result;
	}
	// -- update 25 July	
	
	// update on 25 July
	public int isASSOONASEvent(){ 
		int result=0;
		String[] LEAD_initiation = {"lead", "leads","led"};
		List<String> LEAD_list = Arrays.asList(LEAD_initiation);
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		
		int AsSoonAsPos=arg.indexOf("as soon as");
		
		if (this.Node1.isVerb()==1&&AsSoonAsPos>-1&&arg.indexOf(this.Node2.getWord0().toLowerCase())>AsSoonAsPos) {
			result=1;
			this.relaionType="SIMULTANEOUS";
		}else if (this.Node2.isVerb()==1&&AsSoonAsPos>-1&&arg.indexOf(this.Node1.getWord0().toLowerCase())>AsSoonAsPos) {
			result=1;
			this.relaionType="SIMULTANEOUS";
		}else if (this.Node1.isEvent()==1&&LEAD_list.contains(this.Node1.getWord0().toLowerCase())
				  &&sub.indexOf(this.Node2.getWord0().toLowerCase())!=-1
				) {
			result=1;
			this.relaionType="AFTER";
		}else if (this.Node2.isEvent()==1&&LEAD_list.contains(this.Node2.getWord0().toLowerCase())
				  &&sub.indexOf(this.Node1.getWord0().toLowerCase())!=-1
				) {
			result=1;
			this.relaionType="BEFORE";
		}
		
		return result;
	}
	// -- update 25 July	

	// update on 25 July
	public int isPossibilityEvent(List<Sentence> _sentenceList){ 
		int result=0;
		
		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		List<KeyWord> keywordSenList=pSentence.getSentence();
		
		String[] POSSIBILITY_initiation = {"can", "could","would", "might"};
		List<String> POSSIBILITY_list = Arrays.asList(POSSIBILITY_initiation);
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		
		for (int i = 0; i < keywordSenList.size(); i++) {
			KeyWord tempWord=keywordSenList.get(i);
			if (this.Node1.getWord0().toLowerCase().equals(tempWord.getWord0().toLowerCase())&i-1>-1){
				if (POSSIBILITY_list.contains(keywordSenList.get(i-1).getWord0().toLowerCase())) {
					result=1;
					this.relaionType="AFTER";
				}
			}
			if (this.Node1.getWord0().toLowerCase().equals(tempWord.getWord0().toLowerCase())&i-2>-1){
				if (POSSIBILITY_list.contains(keywordSenList.get(i-2).getWord0().toLowerCase())) {
					result=1;
					this.relaionType="AFTER";
				}
			}
			if (this.Node2.getWord0().toLowerCase().equals(tempWord.getWord0().toLowerCase())&i-1>-1){
				if (POSSIBILITY_list.contains(keywordSenList.get(i-1).getWord0().toLowerCase())) {
					result=1;
					this.relaionType="BEFORE";
				}
			}
			if (this.Node2.getWord0().toLowerCase().equals(tempWord.getWord0().toLowerCase())&i-2>-1){
				if (POSSIBILITY_list.contains(keywordSenList.get(i-2).getWord0().toLowerCase())) {
					result=1;
					this.relaionType="BEFORE";
				}
			}
		}		
		return result;
	}
	// -- update 25 July	
	
	// update on 31 July
	public int isEventAtEvent(List<Sentence> _sentenceList){ 
		int result=0;
		
		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		List<KeyWord> keywordSenList=pSentence.getSentence();
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		
		int node1Pos=arg.indexOf(this.Node1.getWord0().toLowerCase());
		int ATPos=arg.indexOf(" at ");
		int node2Pos=arg.indexOf(this.Node2.getWord0().toLowerCase());

		
		for (int i = 2; i < keywordSenList.size(); i++) {
			KeyWord tempWord=keywordSenList.get(i);
			
			if (this.Node1.isVerb()==1&&rel.indexOf(this.Node1.getWord0().toLowerCase())!=-1
				&&arg.indexOf(this.Node2.getWord0().toLowerCase())!=-1
				&&tempWord.getWord0().toLowerCase().equals(this.Node2.getWord0().toLowerCase())) {
				if (keywordSenList.get(i-1).getWord0().toLowerCase().equals("at")
					||
					keywordSenList.get(i-2).getWord0().toLowerCase().equals("at")
					) {
					result=1;
					this.relaionType="INCLUDES";
				}
			}else if (this.Node2.isVerb()==1&&rel.indexOf(this.Node2.getWord0().toLowerCase())!=-1
					&&arg.indexOf(this.Node1.getWord0().toLowerCase())!=-1
					&&tempWord.getWord0().toLowerCase().equals(this.Node1.getWord0().toLowerCase())) {
					if (keywordSenList.get(i-1).getWord0().toLowerCase().equals("at")
						||
						keywordSenList.get(i-2).getWord0().toLowerCase().equals("at")
						) {
						result=1;
						this.relaionType="INCLUDES";
					}
			} 
		}
		// update 3 September
		if (result==0) {
			if (this.Node1.isNoun()==1&&this.Node2.isNoun()==1) {
				if (sub.indexOf(this.Node1.getWord0().toLowerCase())!=-1
					&&sub.indexOf(this.Node2.getWord0().toLowerCase())!=-1	
				) {
					if ((node1Pos>-1&&node1Pos<ATPos&&node2Pos>ATPos)
						||(node2Pos>-1&&node2Pos<ATPos&&node1Pos>ATPos)	
					   ) {
						result=1;
						this.relaionType="INCLUDES";
					}
			}else if (arg.indexOf(this.Node1.getWord0().toLowerCase())!=-1
						&&arg.indexOf(this.Node2.getWord0().toLowerCase())!=-1	
						) {
							if ((node1Pos>-1&&node1Pos<ATPos&&node2Pos>ATPos)
								||(node2Pos>-1&&node2Pos<ATPos&&node1Pos>ATPos)	
							   ) {
								result=1;
								this.relaionType="INCLUDES";
							}
					}
			}
		}
		
		return result;
	}
	// -- update 31 July	
	// update 16 August
	public int isVerbByVerb(List<Sentence> _sentenceList){ 
		int result=0;
		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		List<KeyWord> keywordSenList=pSentence.getSentence();
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		
		if (this.Node1.isVerb()==1&&rel.indexOf(this.Node1.getWord0().toLowerCase())!=-1) {
			if (this.Node2.isVerb()==1&&arg.indexOf(this.Node2.getWord0().toLowerCase())!=-1) {
				for (int i = 1; i < keywordSenList.size(); i++) {
					KeyWord tempWord=keywordSenList.get(i);
					if (tempWord.getWord0().toLowerCase().equals(this.Node2.getWord0().toLowerCase())
						&&keywordSenList.get(i-1).getWord0().toLowerCase().equals("by")	
						) {
						result=1;
						this.relaionType="SIMULTANEOUS";
					}
				}
			}
		}else if (this.Node2.isVerb()==1&&rel.indexOf(this.Node2.getWord0().toLowerCase())!=-1) {
			if (this.Node1.isVerb()==1&&arg.indexOf(this.Node1.getWord0().toLowerCase())!=-1) {
				for (int i = 1; i < keywordSenList.size(); i++) {
					KeyWord tempWord=keywordSenList.get(i);
					if (tempWord.getWord0().toLowerCase().equals(this.Node1.getWord0().toLowerCase())
						&&keywordSenList.get(i-1).getWord0().toLowerCase().equals("by")	
						) {
						result=1;
						this.relaionType="SIMULTANEOUS";
					}
				}
			}
		}
		
		return result;
	}	
	// --update 16 August
	
	// update 17 August
	public int isVerbWithVerb(List<Sentence> _sentenceList){ 
		int result=0;
		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		List<KeyWord> keywordSenList=pSentence.getSentence();
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		for (int i = 0; i < keywordSenList.size()-1; i++) {
			if (keywordSenList.get(i).getWord0().equals(this.Node1.getWord0().toLowerCase())
				&& keywordSenList.get(i+1).getWord0().equals(this.Node2.getWord0().toLowerCase())	
				) {
				if (this.Node1.isVerb()==1&&this.Node2.isVerbVBG()==1) {
					result=1;
					this.relaionType="AFTER";
				}
			}else if (keywordSenList.get(i).getWord0().equals(this.Node2.getWord0().toLowerCase())
					&& keywordSenList.get(i+1).getWord0().equals(this.Node1.getWord0().toLowerCase())	
					) {
					  if (this.Node2.isVerb()==1&&this.Node1.isVerbVBG()==1) {
							result=1;
							this.relaionType="BEFORE";
					  }
			}
		}
		return result;
	}	
	// --update 17 August

	// update 19 August
	public int isVerbInfinitive(List<Sentence> _sentenceList){ 
		int result=0;
		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		List<KeyWord> keywordSenList=pSentence.getSentence();
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		if ((this.Node1.isVerb()==1&&sub.indexOf(this.Node1.getWord0().toLowerCase())!=-1)
		   ||(this.Node1.isVerb()==1&&rel.indexOf(this.Node1.getWord0().toLowerCase())!=-1)	
		   ) {
				if (this.Node2.getEventTense6().equals("INFINITIVE+NONE+POS")&&arg.indexOf(this.Node2.getWord0().toLowerCase())!=-1) {
					result=1;
					this.relaionType="BEFORE";
			}
		}else if ((this.Node2.isVerb()==1&&sub.indexOf(this.Node2.getWord0().toLowerCase())!=-1)
				 ||(this.Node2.isVerb()==1&&rel.indexOf(this.Node2.getWord0().toLowerCase())!=-1)
				 ) {
					if (this.Node1.getEventTense6().equals("INFINITIVE+NONE+POS")&&arg.indexOf(this.Node1.getWord0().toLowerCase())!=-1) {
						result=1;
						this.relaionType="AFTER";
			}
		}
		return result;
	}	
	// --update 19 August

	// update 20 August
	public int isEventForEvent(List<Sentence> _sentenceList){ 
		int result=0;
		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		List<KeyWord> keywordSenList=pSentence.getSentence();
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		if (sub.indexOf(this.Node1.getWord0().toLowerCase())!=-1
			&&sub.indexOf(this.Node2.getWord0().toLowerCase())!=-1	
			&&this.Node1.isNoun()==1
			&&this.Node2.isNoun()==1
			) {
			int node1Pos=sub.indexOf(this.Node1.getWord0().toLowerCase());
			int forPos=sub.indexOf("for");
			int node2Pos=sub.indexOf(this.Node2.getWord0().toLowerCase());
			if ((node1Pos<forPos&&forPos<node2Pos&&node1Pos!=-1&&forPos!=-1&&node2Pos!=-1)
				||(node2Pos<forPos&&forPos<node1Pos&&node1Pos!=-1&&forPos!=-1&&node2Pos!=-1)
				) {
				result=1;
				this.relaionType="SIMULTANEOUS";
			}
			
		}else if (arg.indexOf(this.Node1.getWord0().toLowerCase())!=-1
			&&arg.indexOf(this.Node2.getWord0().toLowerCase())!=-1	
			&&this.Node1.isNoun()==1
			&&this.Node2.isNoun()==1
			) {
			int node1Pos=arg.indexOf(this.Node1.getWord0().toLowerCase());
			int forPos=arg.indexOf("for");
			int node2Pos=arg.indexOf(this.Node2.getWord0().toLowerCase());
			if ((node1Pos<forPos&&forPos<node2Pos&&node1Pos!=-1&&forPos!=-1&&node2Pos!=-1)
				||(node2Pos<forPos&&forPos<node1Pos&&node1Pos!=-1&&forPos!=-1&&node2Pos!=-1)
				) {
				result=1;
				this.relaionType="SIMULTANEOUS";
			}
		}else if (this.Node1.isVerb()==1&&rel.indexOf(this.Node1.getWord0().toLowerCase())!=-1) {
			int forPos=arg.indexOf("for");
			int node2Pos=arg.indexOf(this.Node2.getWord0().toLowerCase());
			if (this.Node2.isNoun()==1
				&&(forPos>-1&&node2Pos>forPos)	
				) {
				result=1;
				this.relaionType="AFTER";
			}
		}else if (this.Node2.isVerb()==1&&rel.indexOf(this.Node2.getWord0().toLowerCase())!=-1) {
			int forPos=arg.indexOf("for");
			int node1Pos=arg.indexOf(this.Node1.getWord0().toLowerCase());
			if (this.Node1.isNoun()==1
				&&(forPos>-1&&node1Pos>forPos)	
				) {
				result=1;
				this.relaionType="BEFORE";
			}
		} 
		
		return result;
	}	
	// --update 20 August
	
	// update on 26 July
	public int isApplyTime(List<Sentence> _sentenceList){ 
		int result=0;

		// update 24 Sept
		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		List<KeyWord> keywordSenList=pSentence.getSentence();
		KeyWord IFKeyWord=new KeyWord();
		KeyWord COMMAKeyWord=new KeyWord();
	
		int node1Pos=0;
		int node2Pos=0;
		int commaPos=0;
		
		for (int i = 0; i < keywordSenList.size(); i++) {
			if (keywordSenList.get(i).getWord0().toLowerCase().equals("if")) {
				IFKeyWord=keywordSenList.get(i);
			}	
			if (keywordSenList.get(i).getWord0().toLowerCase().equals(",")) {
				COMMAKeyWord=keywordSenList.get(i);
				commaPos=i;
			}			
			if (keywordSenList.get(i).getId1().equals(this.Node1.getId1())) {
				node1Pos=i;				
			}
			if (keywordSenList.get(i).getId1().equals(this.Node2.getId1())) {
				node2Pos=i;				
			}			
		}
		
		for (int i = 0; i < keywordSenList.size(); i++) {
			if (keywordSenList.get(i).getWord0().toLowerCase().equals(",")) {
				if ((i>node1Pos&&i<node2Pos)
					||i>node2Pos&&i<node1Pos
					) {
					COMMAKeyWord=keywordSenList.get(i);
					commaPos=i;						
				}
			}					
		}
		
		String[] IFDPArr=IFKeyWord.getDpStatus16().split(":");
		KeyWord WILLKeyword=pSentence.getKeyWordById(IFDPArr[0]);
		String WILLdp=WILLKeyword.getDpStatus16();
		
		if (WILLdp.indexOf(this.Node1.getId1())!=-1) {
			if (commaPos>0&&node2Pos>commaPos) {
				result=1;
				this.relaionType="BEFORE";
			}
		}else if (WILLdp.indexOf(this.Node2.getId1())!=-1) {
			if (commaPos>0&&node1Pos>commaPos) {
				result=1;
				this.relaionType="AFTER";				
			}
		}
		// -- update 24 Sept if clause
		
		
		List<String> TimeList=new ArrayList<>();
		TimeList.add("PAST");
		TimeList.add("PRESENT");
		TimeList.add("FUTURE");
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		String tenseNode1=this.Node1.getEventTense6();
		String tenseNode2=this.Node2.getEventTense6();
		System.out.println("------------------------");
		System.out.println(tenseNode1);
		System.out.println(tenseNode2);
		System.out.println("------------------------");
		int scoreNode1=-1;
		int scoreNode2=-1;
		for (int i = 0; i < TimeList.size(); i++) {
			int score=i;
			if (tenseNode1.indexOf(TimeList.get(i))!=-1) {
				scoreNode1=score;
			}
			if (tenseNode2.indexOf(TimeList.get(i))!=-1) {
				scoreNode2=score;
			}
		}
		
		if (result==0) {
			if (scoreNode1>-1&&scoreNode2>-1) {
				if (scoreNode1>scoreNode2) { //order past->present->future
					System.out.println("Tense path 1 > Tense path 2");
					result=1;
					this.relaionType="AFTER";
				}else if (scoreNode1<scoreNode2) { //order past->present->future
					System.out.println("Tense path 1 < Tense path 2");
					result=1;
					this.relaionType="BEFORE";
				}
			}
			// Update on 29 July
			if (result==0) {
				if (tenseNode1.indexOf("FUTURE")!=-1&&tenseNode2.indexOf("NONE")!=-1) {
					result=1;
					this.relaionType="AFTER";
				}else if (tenseNode2.indexOf("FUTURE")!=-1&&tenseNode1.indexOf("NONE")!=-1) {
					result=1;
					this.relaionType="BEFORE";
				}
			}
		}
		//--update 29 July
		return result;
	}
	// -- update 26 July	
	
	// update on 25 July
	public int isAfterEvent(List<Sentence> _sentenceList){ 
		int result=0;
		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		List<KeyWord> keywordList=pSentence.getSentence();
		
		String[] AFTER_initiation = {"after", "previous"};
		List<String> AFTER_list = Arrays.asList(AFTER_initiation);
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		
		for (int i = 0; i < keywordList.size(); i++) {
			KeyWord tempWord=keywordList.get(i);
			
			if (this.Node1.equals(tempWord)&&i-1>-1){
				if (AFTER_list.contains(keywordList.get(i-1).getWord0().toLowerCase())) {
					result=1;
					this.relaionType="BEFORE";
				}
			}
			if (this.Node1.equals(tempWord)&&i-2>-1){
				if (AFTER_list.contains(keywordList.get(i-2).getWord0().toLowerCase())) {
					result=1;
					this.relaionType="BEFORE";
				}
			}
			if (this.Node2.equals(tempWord)&&i-1>-1){
				if (AFTER_list.contains(keywordList.get(i-1).getWord0().toLowerCase())) {
					result=1;
					this.relaionType="AFTER";
				}
			}
			if (this.Node2.equals(tempWord)&&i-2>-1){
				if (AFTER_list.contains(keywordList.get(i-2).getWord0().toLowerCase())) {
					result=1;
					this.relaionType="AFTER";
				}
			}
		}
		
		return result;
	}
	// -- update 25 July
	
	// update on 25 July
	public int isBeforeEvent(List<Sentence> _sentenceList){ 
		int result=0;
		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		List<KeyWord> keywordList=pSentence.getSentence();
		
		String[] SAID_initiation = {"issued", "established", "said", "says","say"};
		List<String> SAID_list = Arrays.asList(SAID_initiation);
		
		String[] BEFORE_initiation = {"according","before"};
		List<String> BEFORE_list = Arrays.asList(BEFORE_initiation);
		
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		
		// update 27 September
		if (this.Node1.isEvent()==1&&SAID_list.contains(this.Node1.getWord0().toLowerCase())
			&& this.Node2.getEventTense6().equals("PAST+NONE+POS")	
				) {
			this.relaionType="AFTER";
			result=1;
		}else if (this.Node2.isEvent()==1&&SAID_list.contains(this.Node2.getWord0().toLowerCase())
			&& this.Node1.getEventTense6().equals("PAST+NONE+POS")	
				) {
			this.relaionType="BEFORE";
			result=1;
		}
		// --update 27 September
		else if (this.Node1.isEvent()==1&&SAID_list.contains(this.Node1.getWord0().toLowerCase())) {
			this.relaionType="BEFORE";
			result=1;
		}else if (this.Node2.isEvent()==1&&SAID_list.contains(this.Node2.getWord0().toLowerCase())){
			this.relaionType="AFTER";
			result=1;
		}
		// update on 4 Sept
		if (result==0) {
			for (int i = 0; i < keywordList.size()-1; i++) {
				if (keywordList.get(i).getWord0().toLowerCase().indexOf(this.Node1.getWord0().toLowerCase())!=-1
					&&keywordList.get(i+1).getWord0().toLowerCase().indexOf(this.Node2.getWord0().toLowerCase())!=-1
					&&keywordList.get(i+1).getWord0().toLowerCase().indexOf("made")!=-1
					&&keywordList.get(i+1).getWord0().toLowerCase().indexOf("by")!=-1
					) {
					this.relaionType="IDENTITY";
					result=1;
				}else if (keywordList.get(i).getWord0().toLowerCase().indexOf(this.Node2.getWord0().toLowerCase())!=-1
						&&keywordList.get(i+1).getWord0().toLowerCase().indexOf(this.Node1.getWord0().toLowerCase())!=-1
						&&keywordList.get(i+1).getWord0().toLowerCase().indexOf("made")!=-1
						&&keywordList.get(i+1).getWord0().toLowerCase().indexOf("by")!=-1
						) {
						this.relaionType="IDENTITY";
						result=1;
					} 
			}
		}
		// --update on 4 Sept
		
		if (result==0) {
			for (int i = 0; i < keywordList.size(); i++) {
				KeyWord tempWord=keywordList.get(i);
				
				if (this.Node1.equals(tempWord)&&i-1>-1){
					if (BEFORE_list.contains(keywordList.get(i-1).getWord0().toLowerCase())) {
						result=1;
						this.relaionType="AFTER";
					}
				}
				if (this.Node1.equals(tempWord)&&i-2>-1){
					if (BEFORE_list.contains(keywordList.get(i-2).getWord0().toLowerCase())) {
						result=1;
						this.relaionType="AFTER";
					}
				}
				if (this.Node2.equals(tempWord)&&i-1>-1){
					if (BEFORE_list.contains(keywordList.get(i-1).getWord0().toLowerCase())) {
						result=1;
						this.relaionType="BEFORE";
					}
				}
				if (this.Node2.equals(tempWord)&&i-2>-1){
					if (BEFORE_list.contains(keywordList.get(i-2).getWord0().toLowerCase())) {
						result=1;
						this.relaionType="BEFORE";
					}
				}
			}
		}
		
		if (result==0) {
			// xu ly before if gap sub and rel
			if (this.Node1.isVerb()==1&&rel.indexOf(this.Node1.getWord0().toLowerCase())!=-1) {
				if (arg.indexOf("before")!=-1&&arg.indexOf("before")<arg.indexOf(this.Node2.getWord0().toLowerCase())) {
					result=1;
					this.relaionType="BEFORE";
				}
			}else if (this.Node2.isVerb()==1&&rel.indexOf(this.Node2.getWord0().toLowerCase())!=-1) {
				if (arg.indexOf("before")!=-1&&arg.indexOf("before")<arg.indexOf(this.Node1.getWord0().toLowerCase())) {
					result=1;
					this.relaionType="AFTER";
				}
			}
		}
		
		return result;
	}
	// -- update 25 July

	// update on 27 July
	public int isVerbToVerb(List<Sentence> _sentenceList){ 
		int result=0;
		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		List<KeyWord> keywordList=pSentence.getSentence();
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		
		for (int i = 0; i < keywordList.size()-2; i++) {
			KeyWord tempWord=keywordList.get(i);
			if (this.Node1.isVerb()==1&&this.Node2.isVerb()==1
				&&this.Node1.getWord0().toLowerCase().equals(tempWord.getWord0().toLowerCase())
				&&keywordList.get(i+1).getWord0().toLowerCase().equals("to")
				&&this.Node2.getWord0().toLowerCase().equals(keywordList.get(i+2).getWord0().toLowerCase())
				) {
				result=1;
				this.relaionType="BEFORE";
			}else if (this.Node1.isVerb()==1&&this.Node2.isVerb()==1
					&&this.Node2.getWord0().toLowerCase().equals(tempWord.getWord0().toLowerCase())
					&&keywordList.get(i+1).getWord0().toLowerCase().equals("to")
					&&this.Node1.getWord0().toLowerCase().equals(keywordList.get(i+2).getWord0().toLowerCase())
					) {
					result=1;
					this.relaionType="AFTER";
				}
		}
		return result;
	}
	// --update 27 July
	
	// Rule for N-V
	// Event N (in S) - V (in Rel)
	public int isEventNSub_EventVRel(){
		int result=0;
		
		String[] aspectual_initiation = {"begin", "start", "initiate", "commence", "launch", "come", "go", "will"}; // this list will be updated
		List<String> asp_init_list = Arrays.asList(aspectual_initiation);
		//asp_init_list.contains(doc.getTokens().get(e1.getStartTokID()).getLemma())
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		
		if (this.Node1.isNoun()==1&&this.Node2.isVerb()==1) {
			if (sub.indexOf(this.Node1.getWord0().toLowerCase())!=-1&&rel.indexOf(this.Node2.getWord0().toLowerCase())!=-1) {
				if (asp_init_list.contains(this.Node2.getOrginalWord3().toLowerCase())) {
					result=1;
					this.relaionType="BEGUN";
				}
			}
		}
		
		return result;
	}

	// Rule for N-V
	// Event N (in S) - V (passived in Argument)
	public int isEventNSub_EventVArgument(){
		int result=0;

		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}

		int whilePos=arg.indexOf("while");
		int whenPos=arg.indexOf("when");
		int duringPos=arg.indexOf("during");
		
		if (this.Node1.isNoun()==1&&this.Node2.isVerbVBN()==1) {
			if (sub.indexOf(this.Node1.getWord0().toLowerCase())!=-1&&arg.indexOf(this.Node2.getWord0().toLowerCase())!=-1) {
				result=1;
				this.relaionType="AFTER";
			}
		}
		// update 26 July 2018
		else if ((sub.indexOf(this.Node1.getWord0().toLowerCase())!=-1
			 	  &&whilePos>-1&&arg.indexOf(this.Node2.getWord0().toLowerCase())>whilePos)
			||	
			(sub.indexOf(this.Node1.getWord0().toLowerCase())!=-1
		 	  &&whenPos>-1&&arg.indexOf(this.Node2.getWord0().toLowerCase())>whenPos)
			||
			(sub.indexOf(this.Node1.getWord0().toLowerCase())!=-1
		 	  &&duringPos>-1&&arg.indexOf(this.Node2.getWord0().toLowerCase())>duringPos)
				) {
			result=1;
			this.relaionType="INCLUDES";
		}else if ((sub.indexOf(this.Node2.getWord0().toLowerCase())!=-1
			 	  &&whilePos>-1&&arg.indexOf(this.Node1.getWord0().toLowerCase())>whilePos)  
				 ||
				 (sub.indexOf(this.Node2.getWord0().toLowerCase())!=-1
			 	  &&whenPos>-1&&arg.indexOf(this.Node1.getWord0().toLowerCase())>whenPos)
				 ||
				 (sub.indexOf(this.Node2.getWord0().toLowerCase())!=-1
			 	  &&duringPos>-1&&arg.indexOf(this.Node1.getWord0().toLowerCase())>duringPos)
				) {
			result=1;
			this.relaionType="INCLUDES";
		}
		
		
		return result;
	}

	
	// Rule for clause S-V-O
	// Event V(inV with passive)-O(N in O) was denied by Event
	public int isEventVRel_EventNArgument(){ // Rel-Argument (rule 5)
		int result=0;
		
		String[] end_initiation = {"complete", "finish", "end"}; // this list will be updated
		List<String> end_init_list = Arrays.asList(end_initiation);
		
		
		String[] INCLUDES_initiation = {"expect"}; // this list will be updated
		List<String> INCLUDES_list = Arrays.asList(INCLUDES_initiation);

		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		
		
		if (this.Node1.isVerbVBN()==1&&this.Node2.isNoun()==1) {
			if (rel.indexOf(this.Node1.getWord0().toLowerCase())!=-1&&arg.indexOf(this.Node2.getWord0().toLowerCase())!=-1) {
				//String verbNode1=this.Node1
				result=1;
				this.relaionType="AFTER";
			}		
		}else if (this.Node1.isVerb()==1&&this.Node2.isNoun()==1) {
			
			if (end_init_list.contains(this.Node1.getOrginalWord3())) {
				System.out.println("ENDED_BY");
				this.relaionType="ENDED_BY";
				result=1;
			}else if (INCLUDES_list.contains(this.Node1.getOrginalWord3())
					 &&rel.indexOf(this.Node1.getWord0().toLowerCase())!=-1
					 ) {
				this.relaionType="BEFORE";
				result=1;
			}
		}else if (this.Node2.isVerb()==1&&this.Node1.isNoun()==1) {
			
			if (end_init_list.contains(this.Node2.getOrginalWord3())) {
				System.out.println("ENDED_BY");
				this.relaionType="ENDED_BY";
				result=1;
			}else if (INCLUDES_list.contains(this.Node2.getOrginalWord3())
					 &&rel.indexOf(this.Node2.getWord0().toLowerCase())!=-1
					 ) {
				this.relaionType="AFTER";
				result=1;
			}
		}
		/*
		else if (this.Node1.isVerb()==1) {
			if (INCLUDES_list.contains(this.Node1.getOrginalWord3())
					 &&rel.indexOf(this.Node1.getWord0().toLowerCase())!=-1
					 ) {
				this.relaionType="INCLUDES";
				result=1;
			}
		}else if (this.Node2.isVerb()==1) {
			if (INCLUDES_list.contains(this.Node2.getOrginalWord3())
					 &&rel.indexOf(this.Node2.getWord0().toLowerCase())!=-1
					 ) {
				this.relaionType="INCLUDES";
				result=1;
			}
		}	
		*/
		return result;
	}
	
	// Update on 6 July 2018
	// Rule for clause S-V-O
	// Event V(Normal V wih make, made, create,...)-O(N in O) was denied by Event
	public int isEventEdentityVerbRel_EventNArgument(){ // Rel-Argument (rule 10)
		int result=0;
		
		String[] aspectual_initiation = {"made", "make", "create", "establish"}; // this list will be updated
		List<String> asp_init_list = Arrays.asList(aspectual_initiation);

		String[] find_initiation = {"find", "seek"}; // this list will be updated
		List<String> find_init_list = Arrays.asList(find_initiation);
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		
		if (this.Node1.isVerb()==1&&this.Node2.isNoun()==1) {
			if (rel.indexOf(this.Node1.getWord0().toLowerCase())!=-1&&arg.indexOf(this.Node2.getWord0().toLowerCase())!=-1) {
				//String verbNode1=this.Node1
				for (int i = 0; i < asp_init_list.size(); i++) {
					String temp=asp_init_list.get(i).toLowerCase();
					if (rel.indexOf(temp)!=-1) {
						result=1;
						this.relaionType="IDENTITY";
					}
				}
			}
			// ("Di Giorgio", "is seeking", "alternatives to an unsolicited $ 32-a-share tender offer of DIG Acquisition Corp.") ("Di Giorgio", "is seeking", "alternatives to an unsolicited $ 32-a-share tender offer of DIG Acquisition Corp.")
			if (find_init_list.contains(this.Node1.getOrginalWord3().toLowerCase())) {
				result=1;
				this.relaionType="AFTER";
			}
		}	
		
		return result;
	}
	//("severance payments to those executives not staying with the company", "will reduce"
	public int isEventSub_EventV(){ // Sub-Rel (rule 10)
		int result=0;
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		
		String tense=this.tenseNode1;
		
		if (this.Node1.isNoun()==1&&this.Node2.isVerb()==1) {
			if (tense.equals("FUTURE")) {
				result=1;
				this.relaionType="BEFORE";
			}
		}
		return result;
	}
	
	//("severance payments to those executives not staying with the company", "will reduce"
	public int isFirstEventwithBYsecondEvent(List<Sentence> _sentenceList){ // Sub-Rel (rule 10)
		int result=0;
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		
		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);

		if (this.Node2.isNoun()==1||this.Node2.isVerbVBG()==1) {
			List<KeyWord> keywordList=pSentence.getSentence();
			for (int i = 1; i < keywordList.size(); i++) {
				
				if (keywordList.get(i).equals(this.Node2)){
					String temp=keywordList.get(i-1).getWord0().toLowerCase();
					if (temp.equals("by")) {
						result=1;
						this.relaionType="AFTER";
					}
				}
				
			}
		}
		return result;
	}
	
	// Rule for Node1 = Node 2
	// Used for Indentity
	public int isEqualEvents(){
		int result = 0;
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		
		if (this.Node1.getWord0().toLowerCase().equals(this.Node2.getWord0().toLowerCase())) {
			result=1;
			this.relaionType="IDENTITY";
			System.out.println("IDENTITY");
		}else{
			if (rel.equals("has")||rel.equals("have")
				||rel.equals("is")||rel.equals("are")
				||rel.equals("had")||rel.equals("was")||rel.equals("were")) {
				this.relaionType="IDENTITY";
			}
		}
		
		// update 18 June
		// Cap nhat them S "has/have/had or to be is/are/was/were"
		
		return result;
	}
	
	// Event-Time (Time Begin) 
	public int isEvent_BeginTime(List<Sentence> _sentenceList){
		int result=0;
		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		//List<KeyWord> plist=pSentence.getSentence();
		String[] aspectual_initiation = {"begin", "start", "initiate", "commence", "launch", "come", "go", "will"}; // this list will be updated
		List<String> asp_init_list = Arrays.asList(aspectual_initiation);
		//asp_init_list.contains(doc.getTokens().get(e1.getStartTokID()).getLemma())
		//String idNode1=this.Node1.getId1(); //t138
		//String idNode2=this.Node2.getId1();
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		String[] arrSub=txtNormalization.stringtoArray(sub);
		String[] arrRel=txtNormalization.stringtoArray(rel);
		//String[] arrArg=txtNormalization.stringtoArray(arg);
		//System.out.println(rel);
		
		for (int i = 0; i < arrRel.length; i++) {
			KeyWord pKeyword=new KeyWord();
			//System.out.println(arrRel[i]);
			KeyWord pKeywordTemp=pKeyword.getKeywordByContent(arrRel[i].toLowerCase(), pSentence);
			/*
			System.out.println("Testing------------------");
			System.out.println(arrRel[i]);
			System.out.println(pSentence.getSentence().get(0).getWord0());
			System.out.println(pKeywordTemp.getWord0());
			System.out.println("End testing--------------");
			*/
			String context=pKeywordTemp.getWord0().toLowerCase();
			if (asp_init_list.contains(context)) {
				System.out.println("BEGUN");
				this.relaionType="BEGUN";
				result=1;
				break;
			}
		}
		return result;
	}
	
	// Event-Time (Time END SIMULTANEOUS) 
	public int isEvent_ENDTime(List<Sentence> _sentenceList){
		int result=0;
		
		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		List<KeyWord> keyWordSenList=pSentence.sentence;
		
		String[] END_initiation = {"end","ended", "stop","stopped", "finish","finished","extend","extended","extends"}; // this list will be updated
		List<String> END_init_list = Arrays.asList(END_initiation);
		
		String[] sim_initiation = {"last day","last weak", "last month","last year",
				                   "last hour","last minute"}; // this list will be updated
		List<String> sim_init_list = Arrays.asList(sim_initiation);
		
		
		// update 26 Sept
		
		String[] INCLUDE_initiation = {"speech"}; // this list will be updated
		List<String> INCLUDE_list = Arrays.asList(INCLUDE_initiation);
		if (this.Node1.isTime()==1){
			if(INCLUDE_list.contains(this.Node2.getWord0().toLowerCase())){
				this.relaionType="INCLUDES";
				result=1;
			}
		}else if (this.Node2.isTime()==1){
			if(INCLUDE_list.contains(this.Node1.getWord0().toLowerCase())){
				this.relaionType="INCLUDES";
				result=1;
			}
		}
		
		//-- udpate 26 Sept
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		
		String node1Word=this.Node1.getWord0().toLowerCase();
		String node2Word=this.Node2.getWord0().toLowerCase();
	    if (result==0){
	    	if (this.Node1.isEvent()==1) {
				if (rel.indexOf(node1Word)!=-1&&END_init_list.contains(node1Word)) {
					if (sub.indexOf(node2Word)!=-1) {
						System.out.println("END"); // Can bo sung dieu kien cho is end/ends
						this.relaionType="END";
						result=1;
					}else if (arg.indexOf(node2Word)!=-1) {
						System.out.println("SIMULTANEOUS"); // Can bo sung dieu kien cho is end/ends
						this.relaionType="SIMULTANEOUS";
						result=1;
					}
				}
			}else if (this.Node2.isEvent()==1) {
				if (rel.indexOf(node2Word)!=-1&&END_init_list.contains(node2Word)) {
					if (sub.indexOf(node1Word)!=-1) {
						System.out.println("END"); // Can bo sung dieu kien cho is end/ends
						this.relaionType="END";
						result=1;
					}else if (arg.indexOf(node1Word)!=-1) {
						System.out.println("SIMULTANEOUS"); // Can bo sung dieu kien cho is end/ends
						this.relaionType="SIMULTANEOUS";
						result=1;
					}
				}
			}
	    }
		
		if (result==0) {
			if (this.Node1.isTime()==1) {
				if (sub.indexOf(node1Word)!=-1&&sub.indexOf("ago")!=-1) {
					System.out.println("END"); // Can bo sung dieu kien cho is end/ends
					this.relaionType="END";
					result=1;
				}else if(arg.indexOf(node1Word)!=-1&&arg.indexOf("ago")!=-1){
					System.out.println("END"); // Can bo sung dieu kien cho is end/ends
					this.relaionType="END";
					result=1;
				}
			}else if (this.Node2.isTime()==1) {
				if (sub.indexOf(node2Word)!=-1&&sub.indexOf("ago")!=-1) {
					System.out.println("END"); // Can bo sung dieu kien cho is end/ends
					this.relaionType="END";
					result=1;
				}else if(arg.indexOf(node2Word)!=-1&&arg.indexOf("ago")!=-1){
					System.out.println("END"); // Can bo sung dieu kien cho is end/ends
					this.relaionType="END";
					result=1;
				}
			}
		}
		if (result==0) {
			if (sub.indexOf(node1Word)!=-1&&sub.indexOf(node2Word)!=-1) {
				for (int i = 0; i < sim_init_list.size(); i++) {
					if (sub.indexOf(sim_init_list.get(i))!=-1
						||arg.indexOf(sim_init_list.get(i))!=-1
					   ){
						System.out.println("SIMULTANEOUS"); 
						this.relaionType="SIMULTANEOUS";
						result=1;
					}
				}
			}else if ((arg.indexOf(node1Word)!=-1&&arg.indexOf(node2Word)!=-1)) {
				for (int i = 0; i < sim_init_list.size(); i++) {
					if (arg.indexOf(sim_init_list.get(i))!=-1){
						System.out.println("SIMULTANEOUS"); 
						this.relaionType="SIMULTANEOUS";
						result=1;
					}
				}
			}
		}
		if (result==0) {
			
			for (int i = 1; i < keyWordSenList.size(); i++) {
				KeyWord tempWord=keyWordSenList.get(i);
				
				if ((this.Node1.isTime()==1&&tempWord.getTimeName7().equals(this.Node1.getTimeName7())
					&&END_init_list.contains(keyWordSenList.get(i-1).getWord0().toLowerCase())
					&&sub.indexOf(node1Word)!=-1&&sub.indexOf(node2Word)!=-1)
					||
					(this.Node1.isTime()==1&&tempWord.getTimeName7().equals(this.Node1.getTimeName7())
					&&END_init_list.contains(keyWordSenList.get(i-1).getWord0().toLowerCase())
					&&arg.indexOf(node1Word)!=-1&&arg.indexOf(node2Word)!=-1)	
					) {
					System.out.println("ENDED_BY"); 
					this.relaionType="ENDED_BY";
					result=1;
				}else if ((this.Node2.isTime()==1&&tempWord.getTimeName7().equals(this.Node2.getTimeName7())
						&&END_init_list.contains(keyWordSenList.get(i-1).getWord0().toLowerCase())
						&&sub.indexOf(node1Word)!=-1&&sub.indexOf(node2Word)!=-1)
						||
						(this.Node2.isTime()==1&&tempWord.getTimeName7().equals(this.Node2.getTimeName7())
						&&END_init_list.contains(keyWordSenList.get(i-1).getWord0().toLowerCase())
						&&arg.indexOf(node1Word)!=-1&&arg.indexOf(node2Word)!=-1)
						) {
					System.out.println("ENDED_BY"); 
					this.relaionType="ENDED_BY";
					result=1;
				}
			}
		}		
		return result;
	}
	
	// update on 7 July
	// Event-Time (Now) 
	public int isEvent_NowTime(){
		int result=0;
		String tense="";
		String time="";
		if (this.Node1.isEvent()==1) {
			tense=this.getTenseNode1();
			time= this.getTimeNode1();
			System.out.println(this.Node2.getWord0());
			if (tense.contains("PRESENT")&&time.contains("PRESENT_REF")&&this.Node2.getWord0().equals("already")) {
				this.relaionType="SIMULTANEOUS";
				result=1;
			}else if (tense.contains("PRESENT")&&time.contains("PRESENT_REF")) {
				this.relaionType="INCLUDES";
				result=1;
			}
		
		}else{
			tense=this.getTenseNode2();
			time= this.getTimeNode2();
			System.out.println(this.Node1.getWord0());
			if (tense.contains("PRESENT")&&time.contains("PRESENT_REF")&&this.Node1.getWord0().equals("already")) {
				this.relaionType="SIMULTANEOUS";
				result=1;
			}else if (tense.contains("PRESENT")&&time.contains("PRESENT_REF")) {
				this.relaionType="INCLUDES";
				result=1;
			}
		}
		
		return result;
	}
	//-- update 7
	
	// update on 16 July
	// Event-Time (Now) 
	public int isEvent_AgoTime(){
		int result=0;
		
		return result;
	}
	
	
	// Event - Time ON/IN/FROM (In 2018->IS_Included) 
	public int isEvent_INTime(List<Sentence> _sentenceList){
		int result=0;
		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		List<KeyWord> KeyWordSenList=pSentence.getSentence();
		
		KeyWord timeNode=new KeyWord();
		
		if (this.Node1.isTime()==1) {
			timeNode=this.Node1;
		}else{
			timeNode=this.Node2;
		}
		
		String idTimeNode=timeNode.getId1(); //t138
		String timeNodeWord=timeNode.getWord0();
		// update on 14 July
		String[] INCLUDED_initiation = {"being", "according", "including", "beloging", "period",
				                        "this day", "this year","this month","this week", "this hour",
				                        "this time", "century", "decade", "melinium"}; // this list will be updated
		List<String> INCLUDED_init_list = Arrays.asList(INCLUDED_initiation);
		
		String[] END_initiation = {"complete", "completed", "completes",
									"end", "ended", "ends",
									"finish", "finished", "finishes"
									}; // this list will be updated
		List<String> END_init_list = Arrays.asList(END_initiation);
		
		
		String[] AFTER_initiation = {"after", "over"}; // this list will be updated
		List<String> AFTER_list = Arrays.asList(AFTER_initiation);
		
		String[] BEGIN_initiation = {"until", "begin", "began"}; // this list will be updated
		List<String> BEGIN_list = Arrays.asList(BEGIN_initiation);

		
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		String node1Word=this.Node1.getWord0().toLowerCase();
		String node2Word=this.Node2.getWord0().toLowerCase();
				
		if (sub.indexOf(timeNodeWord)!=-1) {
			for (int i = 0; i < INCLUDED_initiation.length; i++) {
				if (sub.indexOf(INCLUDED_initiation[i])!=-1) {
					this.relaionType="IS_INCLUDED";
					result=1;
				}
			}
		}else if (rel.indexOf(timeNodeWord)!=-1) {
			for (int i = 0; i < INCLUDED_initiation.length; i++) {
				if (rel.indexOf(INCLUDED_initiation[i])!=-1) {
					this.relaionType="IS_INCLUDED";
					result=1;
				}
			}
		}else if (arg.indexOf(timeNodeWord)!=-1) {
			for (int i = 0; i < INCLUDED_initiation.length; i++) {
				if (arg.indexOf(INCLUDED_initiation[i])!=-1) {
					this.relaionType="IS_INCLUDED";
					result=1;
				}
			}
		}
		/////////////////
		if (result==0) {
			for (int i = 1; i < KeyWordSenList.size(); i++) {
				KeyWord temp=KeyWordSenList.get(i);
				if (this.Node1.isTime()==1&&temp.getLabel().equals(this.Node1.getLabel())
					&&AFTER_list.contains(KeyWordSenList.get(i-1).getWord0().toLowerCase())	
					) {
					this.relaionType="BEFORE";
					result=1;
				}else if (this.Node2.isTime()==1&&temp.getLabel().equals(this.Node2.getLabel())
						&&AFTER_list.contains(KeyWordSenList.get(i-1).getWord0().toLowerCase())	
						) {
						this.relaionType="AFTER";
						result=1;
					}
			}
		}
		/////////////////
		if (result==0) {
			
			for (int i = 1; i < KeyWordSenList.size(); i++) {
				KeyWord temp=KeyWordSenList.get(i);
				if (this.Node1.isTime()==1&&temp.getLabel().equals(this.Node1.getLabel())
					&&BEGIN_list.contains(KeyWordSenList.get(i-1).getWord0().toLowerCase())	
					) {
					this.relaionType="BEGUN_BY";
					result=1;
				}else if (this.Node2.isTime()==1&&temp.getLabel().equals(this.Node2.getLabel())
						  &&BEGIN_list.contains(KeyWordSenList.get(i-1).getWord0().toLowerCase())
						) {
					this.relaionType="BEGUN_BY";
					result=1;
				}
			}
		}
		
		
		if (result==0) {
			//("net income (event) for the (time) six months ended Oct. 1", "slipped", "5 % to # 89.7 million $ 141.9 million or 16 pence a share from # 94.8 million $ 149.9 million or 17.3 pence a share")
			if ((sub.indexOf(node1Word)!=-1&&sub.indexOf(node2Word)!=-1)
				||(arg.indexOf(node1Word)!=-1&&arg.indexOf(node2Word)!=-1)	
				) {
				if (this.Node1.isTime()==1&&this.Node1.getTimeStatus8().equals("DURATION")) {
					this.relaionType="SIMULTANEOUS/DURING";// co the thay the DURING here
					result=1;
				}else if (this.Node2.isTime()==1&&this.Node2.getTimeStatus8().equals("DURATION")) {
					this.relaionType="SIMULTANEOUS/DURING";// co the thay the DURING here
					result=1;
				}
			}else if(result==0) {
				List<KeyWord> kwSentenceList=pSentence.getSentence();
				for (int i = 0; i < kwSentenceList.size(); i++) {
					KeyWord kw=kwSentenceList.get(i);
					String tempStr=kw.getDpStatus16().replace("||", "-tab-");
					String[] dpNodeTemp=tempStr.split("-tab-");
				
					for (int j = 0; j < dpNodeTemp.length; j++) {
						String[] dpStatusArr=dpNodeTemp[j].split(":"); //value t6:SBJ
						String tValueNode=dpStatusArr[0];  //value t6
						if (tValueNode.equals(idTimeNode)&&kw.getPosTag15().indexOf("IN")==0) {
							//KeyWordIN.getPosTag15().indexOf("IN")
							this.relaionType="IS_INCLUDED";
							result=1;
						}
					}
				}
			}
		}
		// Event in Time e.g., charge in the 1988
		if (result==0) {
			
			if ((this.Node1.isEvent()==1&&sub.indexOf(node1Word)!=-1&&sub.indexOf(node2Word)!=-1)
				||(this.Node1.isEvent()==1&&arg.indexOf(node1Word)!=-1&&arg.indexOf(node2Word)!=-1)
				) {
				for (int i = 0; i < KeyWordSenList.size()-2; i++) {
					if ((node1Word.equals(KeyWordSenList.get(i).getWord0().toLowerCase())
						&&KeyWordSenList.get(i+1).getWord0().toLowerCase().equals("in"))	
						||
						(node1Word.equals(KeyWordSenList.get(i).getWord0().toLowerCase())
						&&KeyWordSenList.get(i+1).getWord0().toLowerCase().equals("of"))	
						) {
						KeyWord tempTime=KeyWordSenList.get(i+2);
						String timeGroupWord=this.Node2.getDpStatus16();
						if (timeGroupWord.indexOf(tempTime.getId1())!=-1) {
							this.relaionType="IS_INCLUDED";
							result=1;
						}
					}
				}				
			}else if ((this.Node2.isEvent()==1&&sub.indexOf(node2Word)!=-1&&sub.indexOf(node1Word)!=-1)
					||(this.Node2.isEvent()==1&&arg.indexOf(node2Word)!=-1&&arg.indexOf(node1Word)!=-1)
					) {
				for (int i = 0; i < KeyWordSenList.size()-2; i++) {
					if ((node2Word.equals(KeyWordSenList.get(i).getWord0().toLowerCase())
						&&KeyWordSenList.get(i+1).getWord0().toLowerCase().equals("in"))	
						||
						(node2Word.equals(KeyWordSenList.get(i).getWord0().toLowerCase())
						&&KeyWordSenList.get(i+1).getWord0().toLowerCase().equals("of"))	
						) {
						KeyWord tempTime=KeyWordSenList.get(i+2);
						String timeGroupWord=this.Node1.getDpStatus16();
						if (timeGroupWord.indexOf(tempTime.getId1())!=-1) {
							this.relaionType="IS_INCLUDED";
							result=1;
						}
					}
				}				
			}	
		}
		if (result==0) {
			
			for (int i = 0; i < KeyWordSenList.size()-1; i++) {
				KeyWord tempPrefixTime=KeyWordSenList.get(i);
				if (this.Node1.isTime()==1) {
					if ((tempPrefixTime.getWord0().toLowerCase().equals("in")&&KeyWordSenList.get(i+1).getTimeName7().equals(this.Node1.getTimeName7())&&END_init_list.contains(node2Word))
					    ||(tempPrefixTime.getWord0().toLowerCase().equals("on")&&KeyWordSenList.get(i+1).getTimeName7().equals(this.Node1.getTimeName7())&&END_init_list.contains(node2Word))
					    ||(tempPrefixTime.getWord0().toLowerCase().equals("by")&&KeyWordSenList.get(i+1).getTimeName7().equals(this.Node1.getTimeName7())&&END_init_list.contains(node2Word))
					    ) {
						this.relaionType="ENDED_BY";
						result=1;
					}
				}else if (this.Node2.isTime()==1) {
					if (
						  (tempPrefixTime.getWord0().toLowerCase().equals("in")&&KeyWordSenList.get(i+1).getTimeName7().equals(this.Node2.getTimeName7())&&END_init_list.contains(node1Word))
						||(tempPrefixTime.getWord0().toLowerCase().equals("on")&&KeyWordSenList.get(i+1).getTimeName7().equals(this.Node2.getTimeName7())&&END_init_list.contains(node1Word))
						||(tempPrefixTime.getWord0().toLowerCase().equals("by")&&KeyWordSenList.get(i+1).getTimeName7().equals(this.Node2.getTimeName7())&&END_init_list.contains(node1Word))
					    ) {
							this.relaionType="ENDED_BY";
							result=1;
					}
				}
			}
		}
		
		// day la dieu kien cuoi cung tren Time, khi ko co`n cac evident khac
		if (result==0) {
			for (int i = 0; i < KeyWordSenList.size()-1; i++) {
				KeyWord tempPrefixTime=KeyWordSenList.get(i);
				if (this.Node1.isTime()==1) {
					if ((tempPrefixTime.getWord0().toLowerCase().equals("in")&&KeyWordSenList.get(i+1).getTimeName7().equals(this.Node1.getTimeName7()))
					    ||(tempPrefixTime.getWord0().toLowerCase().equals("on")&&KeyWordSenList.get(i+1).getTimeName7().equals(this.Node1.getTimeName7()))
					    ||(tempPrefixTime.getWord0().toLowerCase().equals("by")&&KeyWordSenList.get(i+1).getTimeName7().equals(this.Node1.getTimeName7()))
					    ) {
						this.relaionType="IS_INCLUDED";
						result=1;
					}
				}else if (this.Node2.isTime()==1) {
					if ((tempPrefixTime.getWord0().toLowerCase().equals("in")&&KeyWordSenList.get(i+1).getTimeName7().equals(this.Node2.getTimeName7()))
						||(tempPrefixTime.getWord0().toLowerCase().equals("on")&&KeyWordSenList.get(i+1).getTimeName7().equals(this.Node2.getTimeName7()))
						||(tempPrefixTime.getWord0().toLowerCase().equals("by")&&KeyWordSenList.get(i+1).getTimeName7().equals(this.Node2.getTimeName7()))
					    ) {
							this.relaionType="IS_INCLUDED";
							result=1;
					}
				}
			}
		}
		
		return result;
	}
	// Update 7 July 2018
	// Event-Time (Time Now) 
	public int isEventSetBeforeTime(List<Sentence> _sentenceList){
		int result=0;
		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		String[] aspectual_initiation = {"set", "assign"}; // this list will be updated
		List<String> asp_init_list = Arrays.asList(aspectual_initiation);
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		String[] arrSub=txtNormalization.stringtoArray(sub);
		String[] arrRel=txtNormalization.stringtoArray(rel);
		
		for (int i = 0; i < arrRel.length; i++) {
			KeyWord pKeyword=new KeyWord();
			KeyWord pKeywordTemp=pKeyword.getKeywordByContent(arrRel[i].toLowerCase(), pSentence);
			String context=pKeywordTemp.getWord0().toLowerCase();
			if (asp_init_list.contains(context)) {
				System.out.println("BEFORE");
				this.relaionType="BEFORE";
				result=1;
				break;
			}
		}
		return result;
	}
	//-- Update 7 July 2018

	// Update 30 Sept. 2018
	public int isEventBetweenTime(List<Sentence> _sentenceList){
		int result=0;
		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		List<KeyWord> keyWordList=pSentence.getSentence();
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		String[] arrSub=txtNormalization.stringtoArray(sub);
		String[] arrRel=txtNormalization.stringtoArray(rel);

		KeyWord betweenKey=new KeyWord();
		KeyWord time1=new KeyWord();
		KeyWord time2=new KeyWord();
		for (int i = 0; i < keyWordList.size()-3; i++) {
			if (keyWordList.get(i).getWord0().toLowerCase().contains("between")) {
				betweenKey=keyWordList.get(i);
				time1=keyWordList.get(i+1);
				time2=keyWordList.get(i+3);
			}
		}
		
		if (this.Node1.isTime()==1
			&&this.Node1.equals(time1)	
			) {
			this.relaionType="BEGINS";
			result=1;
		}else if (this.Node2.isTime()==1
				&&this.Node2.equals(time1)	
				) {
				this.relaionType="BEGINS";
				result=1;
		}else if (this.Node1.isTime()==1
				&&this.Node1.equals(time2)	
				) {
				this.relaionType="ENDS";
				result=1;
		}else if (this.Node2.isTime()==1
				&&this.Node2.equals(time2)	
				) {
				this.relaionType="ENDS";
				result=1;
		} 
		return result;
	}
	//-- Update 30 Sept. 2018

		
	// Update 15 July 2018
	// Event-Time (Event Time segmentation e.g., doing yesterday, uploading tomorrow) 
	public int isEventTimeSeg(List<Sentence> _sentenceList){
		int result=0;
		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		
		Sentence pSentence=_sentenceList.get(senPostion);
		String[] aspectual_initiation = {"expire", "expired", "expires"}; // this list will be updated
		List<String> asp_init_list = Arrays.asList(aspectual_initiation);
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		String node1Word=this.Node1.getWord0().toLowerCase();
		String node2Word=this.Node2.getWord0().toLowerCase();
		
		List<KeyWord> keyWordList=pSentence.getSentence();
		for (int i = 0; i < keyWordList.size()-1; i++) {
			KeyWord tempKeyWord=keyWordList.get(i);
			
			if (this.Node1.isEvent()==1&&tempKeyWord.equals(this.Node1)&&keyWordList.get(i+1).equals(this.Node2)
				&&asp_init_list.contains(node1Word)) {	
				System.out.println("SIMULTANEOUS");
				this.relaionType="SIMULTANEOUS";
				result=1;
		   }else if (this.Node1.isEvent()==1&&tempKeyWord.equals(this.Node1)&&keyWordList.get(i+1).equals(this.Node2)) {	
				System.out.println("IS_INCLUDED");
				this.relaionType="IS_INCLUDED";
				result=1;
			}else if (this.Node2.isEvent()==1&&tempKeyWord.equals(this.Node2)&&keyWordList.get(i+1).equals(this.Node1)
					  &&asp_init_list.contains(node2Word)) {
				System.out.println("SIMULTANEOUS");
				this.relaionType="SIMULTANEOUS";
				result=1;
			}else if (this.Node2.isEvent()==1&&tempKeyWord.equals(this.Node2)&&keyWordList.get(i+1).equals(this.Node1)) {
				System.out.println("IS_INCLUDED");
				this.relaionType="IS_INCLUDED";
				result=1;
			}
		}
		return result;
	}
	//-- Update 16 July 2018
	// "fell-V", "59 % in the-Argument latest quarter" 
	public int isEventRelTimeArg(List<Sentence> _sentenceList){
		int result=0;
		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		
		Sentence pSentence=_sentenceList.get(senPostion);
		List<KeyWord> keyWordList=pSentence.getSentence();
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		String node1Word=this.Node1.getWord0().toLowerCase();
		String node2Word=this.Node2.getWord0().toLowerCase();
		
		if (this.Node1.isEvent()==1&&rel.indexOf(node1Word)!=-1) {			
			if (sub.indexOf(node2Word)!=-1||arg.indexOf(node2Word)!=-1) {
				for (int i = 1; i < keyWordList.size(); i++) {
					if ((node2Word.indexOf(keyWordList.get(i).getWord0().toLowerCase())!=-1&&keyWordList.get(i-1).getWord0().equals("in"))
						||(node2Word.indexOf(keyWordList.get(i).getWord0().toLowerCase())!=-1&&keyWordList.get(i-1).getWord0().equals("on"))
						) {
						System.out.println("IS_INCLUDED");
						this.relaionType="IS_INCLUDED";
						result=1;
					}
				}
			}
		}else if (this.Node2.isEvent()==1&&rel.indexOf(node2Word)!=-1) {
			if (sub.indexOf(node1Word)!=-1||arg.indexOf(node1Word)!=-1) {
				for (int i = 1; i < keyWordList.size(); i++) {
					if ((node1Word.indexOf(keyWordList.get(i).getWord0().toLowerCase())!=-1&&keyWordList.get(i-1).getWord0().equals("in"))
						||(node1Word.indexOf(keyWordList.get(i).getWord0().toLowerCase())!=-1&&keyWordList.get(i-1).getWord0().equals("on"))
						) {
						System.out.println("IS_INCLUDED");
						this.relaionType="IS_INCLUDED";
						result=1;
					}
				}
			}
		}
		return result;
	}
	//-- Update 16 July 2018

	// Update 16 July 2018
	// ("Travelers Corp. 's third-quarter (time) net income", "roseV", "11 %") 
	public int isTimeSubEventRel(List<Sentence> _sentenceList){
		int result=0;
		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		List<KeyWord> keyWordList=pSentence.getSentence();
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		String node1Word=this.Node1.getWord0().toLowerCase();
		String node2Word=this.Node2.getWord0().toLowerCase();
		
		if (this.Node1.isTime()==1&&sub.indexOf(node1Word)!=-1&&rel.indexOf(node2Word)!=-1) {
			System.out.println("IS_INCLUDED");
			this.relaionType="IS_INCLUDED";
			result=1;
		} else if (this.Node2.isTime()==1&&sub.indexOf(node2Word)!=-1&&rel.indexOf(node1Word)!=-1) {
			System.out.println("IS_INCLUDED");
			this.relaionType="IS_INCLUDED";
			result=1;
		}
		return result;
	}	
	//-- Update 16 July 2018

	// Update 30 Sept. 2018
	public int isTimeWithIncludes(List<Sentence> _sentenceList){
		int result=0;
		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		List<KeyWord> keyWordList=pSentence.getSentence();
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		String node1DP=this.Node1.getDpStatus16();
		String node2DP=this.Node2.getDpStatus16();
		
		KeyWord IncludeKeyword=new KeyWord();
		for (int i = 0; i < keyWordList.size(); i++) {
			if (keyWordList.get(i).getWord0().toLowerCase().equals("including")) {
				IncludeKeyword=keyWordList.get(i);
			}
		}
		String IncludeDP=IncludeKeyword.getDpStatus16();
		if (this.Node1.isEvent()==1) {
			if (IncludeDP.indexOf(this.Node1.getId1())!=-1
				&&node1DP.indexOf(this.Node2.getId1())!=-1	
			   ) {
				this.relaionType="INCLUDES";
				result=1;
			}
		} else if (this.Node2.isEvent()==1) {
			if (IncludeDP.indexOf(this.Node2.getId1())!=-1
					&&node2DP.indexOf(this.Node1.getId1())!=-1	
				   ) {
					this.relaionType="INCLUDES";
					result=1;
			}
		}
		
		return result;
	}	
	//-- Update 30 Sept. 2018
	

	// included verb (need)
	// ("the latest stock offering", "added", "it will need to seek additional financing either through bank borrowings or debt and equity offerings at a later date") ("the latest stock offering", "added", "it will need to seek additional financing either through bank borrowings or debt and equity offerings at a later date") 
	public int isEventTimeWithINCVerb(){
		int result=0;
		String[] INCLUDED_verb_initiation = {"need", "needs", "needed"}; // this list will be updated
		List<String> INCLUDES_verb_init_list = Arrays.asList(INCLUDED_verb_initiation);
		if (this.Node1.isEvent()==1&&INCLUDES_verb_init_list.contains(this.Node1.getWord0().toLowerCase())) {
        	result=1;
			this.relaionType="IS_INCLUDED";
		}else if (this.Node2.isEvent()==1&&INCLUDES_verb_init_list.contains(this.Node2.getWord0().toLowerCase())) {
			result=1;
			this.relaionType="IS_INCLUDED";
		}
		return result;
	}
	
	//-- Update 18 July 2018
	// Reported event -> report will be after with the time
	// "the company", "reported", "sales of $ 59.5 million and pretax profit of $ 2.9 million In the fiscal year ended Jan. 31") ("the company", "reported", "sales of $ 59.5 million and pretax profit of $ 2.9 million In the fiscal year ended Jan. 31") 
	public int isReportEvent(){
		int result=0;
		String[] REPORT_initiation = {"report", "reported", "reports"}; // this list will be updated
		List<String> REPORT_init_list = Arrays.asList(REPORT_initiation);

		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		String node1Word=this.Node1.getWord0().toLowerCase();
		String node2Word=this.Node2.getWord0().toLowerCase();

		if ((this.Node1.isEvent()==1&&rel.indexOf(node1Word)!=-1&&REPORT_init_list.contains(node1Word)&&sub.indexOf(node2Word)!=-1)
			|| (this.Node1.isEvent()==1&&rel.indexOf(node1Word)!=-1&&REPORT_init_list.contains(node1Word)&&arg.indexOf(node2Word)!=-1)	
			) {
			result=1;
			this.relaionType="AFTER";
		}else if ((this.Node2.isEvent()==1&&rel.indexOf(node2Word)!=-1&&REPORT_init_list.contains(node2Word)&&sub.indexOf(node1Word)!=-1)
				|| (this.Node2.isEvent()==1&&rel.indexOf(node2Word)!=-1&&REPORT_init_list.contains(node2Word)&&arg.indexOf(node1Word)!=-1)	
				) {
			result=1;
			this.relaionType="AFTER";
		}
		return result;
	}

	//-- Update 19 July 2018
	// Due To event -> due to will "IS_INCLUDED" with the time
	// ("The offer", "was", "due to expire yesterday")
	public int isDueToEvent(){
		int result=0;
		String[] DUE_initiation = {"due"}; // this list will be updated
		List<String> DUE_init_list = Arrays.asList(DUE_initiation);

		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		String node1Word=this.Node1.getWord0().toLowerCase();
		String node2Word=this.Node2.getWord0().toLowerCase();

		if ((this.Node1.isEvent()==1&&sub.indexOf(node1Word)!=-1&&DUE_init_list.contains(node1Word))
			|| (this.Node1.isEvent()==1&&arg.indexOf(node1Word)!=-1&&DUE_init_list.contains(node1Word))	
			) {
			result=1;
			this.relaionType="IS_INCLUDED";
		}else if ((this.Node2.isEvent()==1&&sub.indexOf(node2Word)!=-1&&DUE_init_list.contains(node2Word))
				|| (this.Node2.isEvent()==1&&arg.indexOf(node2Word)!=-1&&DUE_init_list.contains(node2Word))	
				) {
			result=1;
			this.relaionType="IS_INCLUDED";
		}
		return result;
	}
	
	//-- Update 27 July 2018
	public int isTimePossisive(List<Sentence> _sentenceList){
		int result=0;

		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		List<KeyWord> keyWordList=pSentence.getSentence();

		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		
		for (int i = 0; i < keyWordList.size()-2; i++) {
			KeyWord tempWord = keyWordList.get(i);
			if (this.Node1.isTime()==1) {
				if (tempWord.getWord0().toLowerCase().equals(this.Node1.getWord0().toLowerCase())
					&&keyWordList.get(i+1).getPosTag15().equals("POS")
					&&keyWordList.get(i+2).getWord0().toLowerCase().equals(this.Node2.getWord0().toLowerCase())
					) {
					result=1;
					this.relaionType="IS_INCLUDED";
				}
			}else if (this.Node2.isTime()==1) {
				if (tempWord.getWord0().toLowerCase().equals(this.Node2.getWord0().toLowerCase())
						&&keyWordList.get(i+1).getPosTag15().equals("POS")
						&&keyWordList.get(i+2).getWord0().toLowerCase().equals(this.Node1.getWord0().toLowerCase())
						) {
						result=1;
						this.relaionType="IS_INCLUDED";
					}
			}
		}
		
		// update 17 August 2018 t43:NMOD||t44:NMOD||t45:NMOD PRP$
		//KeyWord KeyWordIN=pSentence.getKeywordByID(tValueNode);
		//String tempStr=this.Node1.getDpStatus16().replace("||", "-tab-");
		//String[] dpNode1=tempStr.split("-tab-"); // T2, T5, T6 (select Select keywords with IDs T5 and T6)

		
		if (result==0) {
			if (this.Node1.isTime()!=1) {
				String dpText=this.Node1.getDpStatus16().replace("||", "-tab-");
				String[] dpArr=dpText.split("-tab-");
				List<String> newDPList=new ArrayList<>();
				for (int i = 0; i < dpArr.length; i++) {
					String[] temp=dpArr[i].split(":");
					newDPList.add(temp[0]);
				}
				KeyWord PRPkeyword=pSentence.getKeywordByID(newDPList.get(0));
				if (PRPkeyword.getPosTag15().equals("PRP$")) {
					for (int i = 0; i < newDPList.size(); i++) {
						KeyWord tempKeyWord=pSentence.getKeyWordById(newDPList.get(i));
						if (tempKeyWord.getTimeName7().equals(this.Node2.getTimeName7())) {
							result=1;
							this.relaionType="IS_INCLUDED";
						}
					}
				}
			}else if (this.Node2.isTime()!=1) {
				String dpText=this.Node2.getDpStatus16().replace("||", "-tab-");
				String[] dpArr=dpText.split("-tab-");
				List<String> newDPList=new ArrayList<>();
				for (int i = 0; i < dpArr.length; i++) {
					String[] temp=dpArr[i].split(":");
					newDPList.add(temp[0]);
				}
				KeyWord PRPkeyword=pSentence.getKeywordByID(newDPList.get(0));
				if (PRPkeyword.getPosTag15().equals("PRP$")) {
					for (int i = 0; i < newDPList.size(); i++) {
						KeyWord tempKeyWord=pSentence.getKeyWordById(newDPList.get(i));
						if (tempKeyWord.getTimeName7().equals(this.Node1.getTimeName7())) {
							result=1;
							this.relaionType="IS_INCLUDED";
						}
					}
				}
			}
		}
		return result;
	}

	// Update 22 August 2018
	public int isEventPossisive(List<Sentence> _sentenceList){
		int result=0;

		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		List<KeyWord> keyWordList=pSentence.getSentence();

		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}

		
		if (this.Node1.isNoun()==1) {
			String dpText=this.Node1.getDpStatus16().replace("||", "-tab-");
			String[] dpArr=dpText.split("-tab-");
			List<String> newDPList=new ArrayList<>();
			for (int i = 0; i < dpArr.length; i++) {
				String[] temp=dpArr[i].split(":");
				newDPList.add(temp[0]);
			}
			KeyWord PRPkeyword=pSentence.getKeywordByID(newDPList.get(0));
			if (PRPkeyword.getPosTag15().equals("PRP$")) {
				for (int i = 0; i < newDPList.size(); i++) {
					KeyWord tempKeyWord=pSentence.getKeyWordById(newDPList.get(i));
					if (tempKeyWord.getTimeName7().equals(this.Node2.getTimeName7())) {
						result=1;
						this.relaionType="IS_INCLUDED";
					}
				}
			}
		}else if (this.Node2.isNoun()==1) {
			String dpText=this.Node2.getDpStatus16().replace("||", "-tab-");
			String[] dpArr=dpText.split("-tab-");
			List<String> newDPList=new ArrayList<>();
			for (int i = 0; i < dpArr.length; i++) {
				String[] temp=dpArr[i].split(":");
				newDPList.add(temp[0]);
			}
			KeyWord PRPkeyword=pSentence.getKeywordByID(newDPList.get(0));
			if (PRPkeyword.getPosTag15().equals("PRP$")) {
				for (int i = 0; i < newDPList.size(); i++) {
					KeyWord tempKeyWord=pSentence.getKeyWordById(newDPList.get(i));
					if (tempKeyWord.getTimeName7().equals(this.Node1.getTimeName7())) {
						result=1;
						this.relaionType="IS_INCLUDED";
					}
				}
			}
		}
		return result;
	}
	// --Update 22 August 2018
	
	// Update 22 August 2018
	public int isEventWithAS(List<Sentence> _sentenceList){
		int result=0;

		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		List<KeyWord> keyWordList=pSentence.getSentence();

		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		
		int node1Pos=arg.indexOf(this.Node1.getWord0().toLowerCase());
		int ASPos=arg.indexOf("as ");
		int node2Pos=arg.indexOf(this.Node2.getWord0().toLowerCase());
		
		if (rel.indexOf(this.Node1.getWord0().toLowerCase())!=-1) {
			if (ASPos>-1&&ASPos<node2Pos) {
				result=1;
				this.relaionType="SIMULTANEOUS";
			}
		}else if (rel.indexOf(this.Node2.getWord0().toLowerCase())!=-1) {
			if (ASPos>-1&&ASPos<node1Pos) {
				result=1;
				this.relaionType="SIMULTANEOUS";
			}
		}
		// update on 23 August
		if (result==0) {
			for (int i = 0; i < keyWordList.size(); i++) {
				if (keyWordList.get(i).getWord0().equals("as")) {
					String[] temp=keyWordList.get(i).getDpStatus16().split(":");
					KeyWord pKeyWrord=pSentence.getKeyWordById(temp[0]);
					if (pKeyWrord.getWord0().toLowerCase().equals(this.Node1.getWord0().toLowerCase())
						|| pKeyWrord.getWord0().toLowerCase().equals(this.Node2.getWord0().toLowerCase())	
							) {
						result=1;
						this.relaionType="SIMULTANEOUS";
					}
				}
			}
		}
		// --update on 23 August		
		return result;
	}
	// --Update 22 August 2018

	// Update 22 August 2018
	public int isEventException(List<Sentence> _sentenceList){
		int result=0;

		String[] SIM_initiation = {"taking","being"};
		List<String> SIM_list = Arrays.asList(SIM_initiation);

		String[] BEFORE_initiation = {"echo","echoed"};
		List<String> BEFORE_list = Arrays.asList(BEFORE_initiation);
		
		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		List<KeyWord> keyWordList=pSentence.getSentence();

		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		
		if (SIM_list.contains(this.Node1.getWord0().toLowerCase())
			||SIM_list.contains(this.Node2.getWord0().toLowerCase())	
				) {
			result=1;
			this.relaionType="SIMULTANEOUS";
		}
		if (result==0
			&&this.Node1.getEventTense6().equals("PRESENT+PERFECTIVE+POS")	
			&&this.Node1.getEventTense6().equals("PRESENT+PERFECTIVE+POS")
				) {
			result=1;
			this.relaionType="SIMULTANEOUS";
		}
		if (result==0) {
			if (sub.indexOf(this.Node1.getWord0().toLowerCase())!=-1
				&&rel.indexOf(this.Node2.getWord0().toLowerCase())!=-1
				&&BEFORE_list.contains(this.Node2.getWord0().toLowerCase())
					) {
				result=1;
				this.relaionType="BEFORE";			
			}else if (sub.indexOf(this.Node2.getWord0().toLowerCase())!=-1
					&&rel.indexOf(this.Node1.getWord0().toLowerCase())!=-1
					&&BEFORE_list.contains(this.Node1.getWord0().toLowerCase())
						) {
					result=1;
					this.relaionType="AFTER";			
				} 
		}
		if (result==0) {
			if (arg.indexOf(this.Node1.getWord0().toLowerCase())!=-1
				&&arg.indexOf(this.Node2.getWord0().toLowerCase())!=-1) {	
				int node1Pos=arg.indexOf(this.Node1.getWord0().toLowerCase());
				int node2Pos=arg.indexOf(this.Node2.getWord0().toLowerCase());
				int finish=arg.indexOf("finished");
				int begin=arg.indexOf("begun");
			
				if ((finish!=-1&&begin!=-1&node1Pos!=-1&&node2Pos!=-1)) {
					if (node1Pos<node2Pos) {
						result=1;
						this.relaionType="BEFORE";
					}else if (node1Pos>node2Pos) {
						result=1;
						this.relaionType="AFTER";
					}
					
				}
				
			}
		}
		
		return result;
	}
	// --Update 22 August 2018

	// update 24 September 2018
	public int isEventPriorityException(List<Sentence> _sentenceList){
		int result=0;

		String[] SIM_initiation = {"taking","being", "willing","making", "mark", "marked", "marks", "calling", "fighting"};
		List<String> SIM_list = Arrays.asList(SIM_initiation);

		String[] BEFORE_initiation = {"echo","echoed","put","consider","considered","considerss"};
		List<String> BEFORE_list = Arrays.asList(BEFORE_initiation);
		
		String[] INCLUDE_initiation = {"speech"};
		List<String> INCLUDE_list = Arrays.asList(INCLUDE_initiation);
		
		String[] ACCIDENT_initiation = {"accident", "accidents", "bombing", "bombings", "attack", "attacks"};
		List<String> ACCIDENT_list = Arrays.asList(ACCIDENT_initiation);
		
		String[] ACCresult_initiation = {"killed", "kills", "kill", "injury", "injured", "injuries","allege","alleged","alleges"};
		List<String> ACCresult_list = Arrays.asList(ACCresult_initiation);
		
		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		List<KeyWord> keyWordList=pSentence.getSentence();

		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		
		if (SIM_list.contains(this.Node1.getWord0().toLowerCase())
			||SIM_list.contains(this.Node2.getWord0().toLowerCase())	
				) {
			result=1;
			this.relaionType="SIMULTANEOUS";
		}
		if (result==0){
			if (INCLUDE_list.contains(this.Node1.getWord0().toLowerCase())
					||INCLUDE_list.contains(this.Node2.getWord0().toLowerCase())	
						) {
					result=1;
					this.relaionType="INCLUDES";
				}
		}
		
		if (result==0) {
			if (ACCIDENT_list.contains(this.Node1.getWord0().toLowerCase())
				&&ACCresult_list.contains(this.Node2.getWord0().toLowerCase())	
			) {
				result=1;
				this.relaionType="IBEFORE";
			}else if (ACCIDENT_list.contains(this.Node2.getWord0().toLowerCase())
					&&ACCresult_list.contains(this.Node1.getWord0().toLowerCase())	
					) {
						result=1;
						this.relaionType="IAFTER";
			}
		}
		
		if (result==0) {
			for (int i = 0; i < keyWordList.size(); i++) {
				if (ACCIDENT_list.contains(keyWordList.get(i).getWord0().toLowerCase())
					&&ACCresult_list.contains(this.Node1.getWord0().toLowerCase())	
					&&ACCresult_list.contains(this.Node2.getWord0().toLowerCase())	
					) {
					result=1;
					this.relaionType="SIMULTANEOUS";
				}
			}
		}

		// said at event => INCLUDES
		if (result==0) {
			for (int i = 0; i < keyWordList.size()-1; i++) {
				if (keyWordList.get(i).getWord0().equals("said")
					&&keyWordList.get(i).getWord0().equals(this.Node1.getWord0().toLowerCase())	
					&&keyWordList.get(i+1).getWord0().equals("at")
					) {
					String ATdp=keyWordList.get(i+1).getDpStatus16();
					if (ATdp.indexOf(this.Node2.getId1())!=-1) {
						result=1;
						this.relaionType="INCLUDES";
					}
				}else if (keyWordList.get(i).getWord0().equals("said")
						&&keyWordList.get(i).getWord0().equals(this.Node2.getWord0().toLowerCase())	
						&&keyWordList.get(i+1).getWord0().equals("at")
						) {
						String ATdp=keyWordList.get(i+1).getDpStatus16();
						if (ATdp.indexOf(this.Node1.getId1())!=-1) {
							result=1;
							this.relaionType="INCLUDES";
						}
				}
			}
		}
		
		
		// update on 27 September
		if (result==0) {
			if (this.Node1.isVerb()==1&&BEFORE_list.contains(this.Node1.getWord0().toLowerCase())
				&&this.Node2.isNoun()==1) {
				result=1;
				this.relaionType="BEFORE";
			}else if (this.Node2.isVerb()==1&&BEFORE_list.contains(this.Node2.getWord0().toLowerCase())
				&&this.Node1.isNoun()==1) {
				result=1;
				this.relaionType="AFTER";
			}
		}
		
		if (result==0) {
			if (this.Node1.isVerb()==1&&this.Node1.getWord0().toLowerCase().equals("consider")
				||this.Node1.isVerb()==1&&this.Node1.getWord0().toLowerCase().equals("considered")
				||this.Node1.isVerb()==1&&this.Node1.getWord0().toLowerCase().equals("considers")	
				){
				if (rel.indexOf(this.Node1.getWord0().toLowerCase())!=-1
					&&arg.indexOf(this.Node2.getWord0().toLowerCase())!=-1) {
					result=1;
					this.relaionType="BEFORE";
				}
			}else if (this.Node2.isVerb()==1&&this.Node1.getWord0().toLowerCase().equals("consider")
					||this.Node2.isVerb()==1&&this.Node1.getWord0().toLowerCase().equals("considered")
					||this.Node2.isVerb()==1&&this.Node1.getWord0().toLowerCase().equals("considers")	
					){
					if (rel.indexOf(this.Node2.getWord0().toLowerCase())!=-1
						&&arg.indexOf(this.Node1.getWord0().toLowerCase())!=-1) {
						result=1;
						this.relaionType="AFTER";
					}
			} 
		}
		//-- update on 27 September
		
		if (result==0) {
			if ((this.Node1.isVerb()==1&&rel.indexOf(this.Node1.getWord0().toLowerCase())!=-1)
				&&this.Node2.getWord0().equals("resuming")	
				&&arg.indexOf("in resuming")!=-1	
				) {
				result=1;
				this.relaionType="BEFORE";
			}else if ((this.Node2.isVerb()==1&&rel.indexOf(this.Node2.getWord0().toLowerCase())!=-1)
				&&this.Node1.getWord0().equals("resuming")	
				&&arg.indexOf("in resuming")!=-1	
				) {
				result=1;
				this.relaionType="AFTER";
			}else if ((this.Node1.isVerb()==1)
				&&(this.Node2.getWord0().equals("remarks")||this.Node2.getWord0().equals("remark"))	
				&&arg.indexOf("in remark")!=-1	
				) {
				result=1;
				this.relaionType="INCLUDES";
			}else if ((this.Node2.isVerb()==1)
				&&(this.Node1.getWord0().equals("remarks")||this.Node1.getWord0().equals("remark"))	
				&&arg.indexOf("in remark")!=-1	
				) {
				result=1;
				this.relaionType="INCLUDES";
			}
		}
		
		if (result==0) {
			for (int i = 0; i < keyWordList.size()-1; i++) {
				if (keyWordList.get(i).equals(this.Node1)
					&&this.Node1.getWord0().toLowerCase().equals("bombing")) {
					if (keyWordList.get(i+1).getWord0().toLowerCase().indexOf("victim")!=-1) {
						result=1;
						this.relaionType="BEFORE";
					}
				}else if (keyWordList.get(i).equals(this.Node2)
						&&this.Node2.getWord0().toLowerCase().equals("bombing")) {
						if (keyWordList.get(i+1).getWord0().toLowerCase().indexOf("victim")!=-1) {
							result=1;
							this.relaionType="AFTER";
						}
				} 
			}
		}
		
		return result;
	}
	// --update 24 September 2018
	
	//-- Update 18 August 2018
	//outputResult=generalRules.twoNodesRelationTimeTime(pRelNodes, keywordTMX0, sentenceList);
	public int isReferenceTimeTime(KeyWord pKeyWordTMX0, List<Sentence> _sentenceList){
		int result=0;

		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		List<KeyWord> keyWordList=pSentence.getSentence();

		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		if (this.Node1.isTime()!=-1) {
			String dpText=this.Node1.getDpStatus16().replace("||", "-tab-");
			String[] dpArr=dpText.split("-tab-");
			List<String> newDPList=new ArrayList<>();
			for (int i = 0; i < dpArr.length; i++) {
				String[] temp=dpArr[i].split(":");
				newDPList.add(temp[0]);
			}
			KeyWord timeKeyWord=new KeyWord();
			for (int i = 0; i < newDPList.size(); i++) {
				KeyWord pKeyword=pSentence.getKeywordByID(newDPList.get(i));
				if (pKeyword.isTime()==1) {
					timeKeyWord=pKeyword;
				}
			}
			this.setNode1(timeKeyWord);
			if (this.isTimeTimeRel(pKeyWordTMX0)==1) {
				result=1;
			}
			
		}else if (this.Node2.isTime()!=-1) {
			String dpText=this.Node2.getDpStatus16().replace("||", "-tab-");
			String[] dpArr=dpText.split("-tab-");
			List<String> newDPList=new ArrayList<>();
			for (int i = 0; i < dpArr.length; i++) {
				String[] temp=dpArr[i].split(":");
				newDPList.add(temp[0]);
			}
			KeyWord timeKeyWord=new KeyWord();
			for (int i = 0; i < newDPList.size(); i++) {
				KeyWord pKeyword=pSentence.getKeywordByID(newDPList.get(i));
				if (pKeyword.isTime()==1) {
					timeKeyWord=pKeyword;
				}
			}
			this.setNode2(timeKeyWord);
			if (this.isTimeTimeRel(pKeyWordTMX0)==1) {
				result=1;
			}
		}
		return result;
	}
	
	
	public int isfiscalYear(List<Sentence> _sentenceList){
		int result=0;
		
		int senPostionNode1=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentenceNode1=_sentenceList.get(senPostionNode1);
		List<KeyWord> keyWordNode1List=pSentenceNode1.getSentence();
		
		String[] FISCALYEAR_initiation = {"this fiscal year", "that fiscal year", "the fiscal year",
                "a fiscal year", "fiscal year"};
		List<String> FISCALYEAR_list = Arrays.asList(FISCALYEAR_initiation);
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		
		String fiscalyearNode1="";
		if (this.Node1.isTime()==1) {
			for (int i = 0; i < keyWordNode1List.size(); i++) {
				KeyWord tempWord = keyWordNode1List.get(i);
				if (tempWord.getLabel().equals(this.Node1.getLabel())) {
					fiscalyearNode1=fiscalyearNode1+" "+tempWord.getWord0().toLowerCase();
				}
			}
		}else if (this.Node2.isTime()==1) {
			for (int i = 0; i < keyWordNode1List.size(); i++) {
				KeyWord tempWord = keyWordNode1List.get(i);
				if (tempWord.getLabel().equals(this.Node2.getLabel())) {
					fiscalyearNode1=fiscalyearNode1+" "+tempWord.getWord0().toLowerCase();
				}
			}
		}
		String tempNode1=fiscalyearNode1.trim();
		if (FISCALYEAR_list.contains(tempNode1)) {
			result=1;
			this.relaionType="IS_INCLUDED";
		}
		return result;
	}
	
	
	//-- Update 27 July 2018
	public int isfiscalYearTwoTime(List<Sentence> _sentenceList){
		int result=0;

		int senPostionNode1=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentenceNode1=_sentenceList.get(senPostionNode1);
		List<KeyWord> keyWordNode1List=pSentenceNode1.getSentence();

		int senPostionNode2=Integer.parseInt(this.Node2.getSentencePos2());
		Sentence pSentenceNode2=_sentenceList.get(senPostionNode2);
		List<KeyWord> keyWordNode2List=pSentenceNode2.getSentence();
		
		
		
		String[] FISCALYEAR_initiation = {"this fiscal year", "that fiscal year", "the fiscal year",
                "a fiscal year", "fiscal year"};
		List<String> FISCALYEAR_list = Arrays.asList(FISCALYEAR_initiation);
		

		
		String fiscalyearNode1="";
		String fiscalyearNode2="";
		if (this.Node1.isTime()==1) {
			for (int i = 0; i < keyWordNode1List.size(); i++) {
				KeyWord tempWord = keyWordNode1List.get(i);
				if (tempWord.getLabel().equals(this.Node1.getLabel())) {
					fiscalyearNode1=fiscalyearNode1+" "+tempWord.getWord0().toLowerCase();
				}
			}
		}
		if (this.Node2.isTime()==1) {
			for (int i = 0; i < keyWordNode2List.size(); i++) {
				KeyWord tempWord = keyWordNode2List.get(i);
				if (tempWord.getLabel().equals(this.Node2.getLabel())) {
					fiscalyearNode2=fiscalyearNode2+" "+tempWord.getWord0().toLowerCase();
				}
			}
		}

		String tempNode1=fiscalyearNode1.trim();
		String tempNode2=fiscalyearNode2.trim();
		if (FISCALYEAR_list.contains(tempNode1)
			||FISCALYEAR_list.contains(tempNode2)
				) {
			result=1;
			this.relaionType="IS_INCLUDED";
		}
		return result; 
	}
	
	// update 28 July
	public int isDurationTime(List<Sentence> _sentenceList){
		int result=0;
		
		int senPostionNode1=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentenceNode1=_sentenceList.get(senPostionNode1);
		List<KeyWord> keyWordList=pSentenceNode1.getSentence();

		KeyWord EventKeyword=new KeyWord();
		if (this.Node1.isEvent()==1) {
			EventKeyword=this.Node1;
		}else{
			EventKeyword=this.Node2;
		}
		
		// update 30 July
		String[] EVERYTIME_initiation = {"every minute", "every hour", "every day", "every week","every month","every year", 
                                           "every morning", "every afternoon", "every evening","every time",
                                           "this day","this week","this month","this year",
                                           "today","monday","tuesday","wednesday","thursday","friday","saturday","sunday",
                                           "next year","next month","next week","next day","next hour","next minute"}; // update 16 August
		List<String> EVERYTIME_list = Arrays.asList(EVERYTIME_initiation);
		//-- update 30 July

		String[] BEGIN_initiation = {"choose", "chosen", "chooses"}; // update 19 August
        List<String> BEGIN_list = Arrays.asList(BEGIN_initiation);
        
		// update 29 July
		String timeTxt="";
		int timeTxtPos=0;
		if (this.Node1.isTime()==1&&!this.Node1.getLabel().equals("tmx0")) {
			for (int i = 0; i < keyWordList.size(); i++) {
				KeyWord temp=keyWordList.get(i);
				if (temp.getLabel().equals(this.Node1.getLabel())) {
					timeTxt=timeTxt+" "+temp.getWord0().toLowerCase();
					timeTxtPos=i;
				}
			}
		}else if (this.Node2.isTime()==1&&!this.Node2.getLabel().equals("tmx0")) {
			for (int i = 0; i < keyWordList.size(); i++) {
				KeyWord temp=keyWordList.get(i);
				if (temp.getLabel().equals(this.Node2.getLabel())) {
					timeTxt=timeTxt+" "+temp.getWord0().toLowerCase();
					timeTxtPos=i;
				}
			}
		}
		
		String finalTimeTxt=timeTxt.trim();
		
		System.out.println("--time information----");
		System.out.println(finalTimeTxt);
		System.out.println("--time information----");
		
		if(timeTxtPos<keyWordList.size()-1&&timeTxtPos>0){  // UPDATE ON 31 JULY and 1 Oct
			if (keyWordList.get(timeTxtPos+1).getWord0().toLowerCase().equals("before")) {
				KeyWord BEFOREKeyword=keyWordList.get(timeTxtPos+1);
				String BEFOREdp=BEFOREKeyword.getDpStatus16();
				
				if (BEFOREdp.indexOf(EventKeyword.getId1())!=-1) {
					result=1;
					this.relaionType="BEFORE";
				}else{
				result=1;
				this.relaionType="AFTER";
				}
			}
			// update 1 Oct
			
			else if (keyWordList.get(timeTxtPos-1).getWord0().toLowerCase().equals("since")
					||keyWordList.get(timeTxtPos-1).getWord0().toLowerCase().equals("until")
					) {
					result=1;
					this.relaionType="BEGINS";
			}
		}
		
		if (result==0) {
			if (finalTimeTxt.indexOf("less than")!=-1) {
				result=1;
				this.relaionType="ENDS";
			}
			// update on 30 July
			else if (EVERYTIME_list.contains(finalTimeTxt)
					||finalTimeTxt.indexOf("last")!=-1 // update 17 august
					) {
				result=1;
				this.relaionType="IS_INCLUDED";
			} 
			// update 31 July
			// update 22 August
			else if ((finalTimeTxt.indexOf("almost")!=-1) // update 17 august
					||(finalTimeTxt.indexOf("more than")!=-1)
					||(finalTimeTxt.indexOf("nearly")!=-1)
			) {
				result=1;
				this.relaionType="DURING";
			}
			// --update 22 August
			else if (finalTimeTxt.indexOf("annually")!=-1
					||finalTimeTxt.indexOf("annual")!=-1
					) {
				result=1;
				this.relaionType="SIMULTANEOUS";
			}
			//-- update on 30 July
		}
		// update 19 August
		if (result==0) {
			if (this.Node1.isTime()!=1&&BEGIN_list.contains(this.Node1.getWord0().toLowerCase())) {
				result=1;
				this.relaionType="BEGINS";
			}else if (this.Node2.isTime()!=1&&BEGIN_list.contains(this.Node2.getWord0().toLowerCase())) {
				result=1;
				this.relaionType="BEGINS";
			}
		}
		
		if (result==0) {
			for (int i = 1; i < keyWordList.size(); i++) {
				if (this.Node1.isTime()!=1&&keyWordList.get(i).getWord0().toLowerCase().equals(this.Node1.getWord0().toLowerCase())
					&&keyWordList.get(i-1).getWord0().toLowerCase().equals("finally")
					) {	
					result=1;
					this.relaionType="ENDS";
				}else if (this.Node2.isTime()!=1&&keyWordList.get(i).getWord0().toLowerCase().equals(this.Node2.getWord0().toLowerCase())
						&&keyWordList.get(i-1).getWord0().toLowerCase().equals("finally")
						) {	
						result=1;
						this.relaionType="ENDS";
				}else if (this.Node1.isTime()==1&&keyWordList.get(i).getWord0().toLowerCase().equals(this.Node1.getWord0().toLowerCase())
						&&keyWordList.get(i-1).getWord0().toLowerCase().equals("until")
						) {	
						result=1;
						this.relaionType="ENDED_BY";
				}else if (this.Node2.isTime()==1&&keyWordList.get(i).getWord0().toLowerCase().equals(this.Node2.getWord0().toLowerCase())
						&&keyWordList.get(i-1).getWord0().toLowerCase().equals("until")
						) {	
						result=1;
						this.relaionType="ENDED_BY";
				} 
			}	
		}
		//--update 19 August

		//-- update 29 July
		if (result==0) {
			if ((this.Node1.isTime()==1&&this.Node1.getTimeStatus8().equals("DURATION"))
				||	
				//update on 30 July 2018
				(this.Node1.isTime()==1&&this.Node1.getWord0().equals("now"))					
				) {
				result=1;
				this.relaionType="IS_INCLUDED";
			}
			else if ((this.Node2.isTime()==1&&this.Node2.getTimeStatus8().equals("DURATION"))
					||
					//update on 30 July 2018
					(this.Node2.isTime()==1&&this.Node2.getWord0().equals("now"))	
					) {
				result=1;
				this.relaionType="IS_INCLUDED";
			}
		}
		
		return result;
	}
	
	
	// Update 11 June 2018
	public int isExistInList(List<relationNodes> pRelNodesList){ //including reverse

		int result=0;
		String node1="";
		String node2="";
		if (this.Node1.isEvent()==1) {
			node1=this.Node1.getEventName4();
		}else{
			node1=this.Node1.getTimeName7();
		}
		if (this.Node2.isEvent()==1) {
			node2=this.Node2.getEventName4();
		}else{
			node2=this.Node2.getTimeName7();
		}
		for (int i = 0; i < pRelNodesList.size(); i++) {
			relationNodes tempRelNodes=pRelNodesList.get(i);

			String tempNode1="";
			if (tempRelNodes.getNode1().isEvent()==1) {
				tempNode1=tempRelNodes.getNode1().getEventName4();
			}else{
				tempNode1=tempRelNodes.getNode1().getTimeName7();
			}
			
			String tempNode2="";
			if (tempRelNodes.getNode2().isEvent()==1) {
				tempNode2=tempRelNodes.getNode2().getEventName4();
			}else{
				tempNode2=tempRelNodes.getNode2().getTimeName7();
			}

			// comparision here
			if (node1.equals(tempNode1)&&node2.equals(tempNode2)) {
				result = 1;
			}else if (node1.equals(tempNode2)&&node2.equals(tempNode1)) {
				result =1;
			}
		}
		return result;
	}

	// Update
	public relationNodes getExistInList(List<relationNodes> pRelNodesList){ //including reverse
		relationNodes result=new relationNodes();
		String node1="";
		String node2="";
		if (this.Node1.isEvent()==1) {
			node1=this.Node1.getEventName4();
		}else{
			node1=this.Node1.getTimeName7();
		}
		if (this.Node2.isEvent()==1) {
			node2=this.Node2.getEventName4();
		}else{
			node2=this.Node2.getTimeName7();
		}
		for (int i = 0; i < pRelNodesList.size(); i++) {
			relationNodes tempRelNodes=pRelNodesList.get(i);

			String tempNode1="";
			if (tempRelNodes.getNode1().isEvent()==1) {
				tempNode1=tempRelNodes.getNode1().getEventName4();
			}else{
				tempNode1=tempRelNodes.getNode1().getTimeName7();
			}
			
			String tempNode2="";
			if (tempRelNodes.getNode2().isEvent()==1) {
				tempNode2=tempRelNodes.getNode2().getEventName4();
			}else{
				tempNode2=tempRelNodes.getNode2().getTimeName7();
			}

			// comparision here
			if (node1.equals(tempNode1)&&node2.equals(tempNode2)) {
				result = tempRelNodes;
			}else if (node1.equals(tempNode2)&&node2.equals(tempNode1)) {
				result =tempRelNodes;
			}
		}
		return result;
	}
	
	// Update 3 March 20199
	public int isExistInListOnly1KeyWordCausal(List<relationNodes> pRelNodesList){ //including reverse

		int result=0;
		String node1="";
		String node2="";
		if (this.Node1.isEvent()==1) {
			node1=this.Node1.getEventName4();
		}else{
			node1=this.Node1.getTimeName7();
		}
		if (this.Node2.isEvent()==1) {
			node2=this.Node2.getEventName4();
		}else{
			node2=this.Node2.getTimeName7();
		}
		for (int i = 0; i < pRelNodesList.size(); i++) {
			relationNodes tempRelNodes=pRelNodesList.get(i);

			String tempNode1="";
			if (tempRelNodes.getNode1().isEvent()==1) {
				tempNode1=tempRelNodes.getNode1().getEventName4();
			}else{
				tempNode1=tempRelNodes.getNode1().getTimeName7();
			}
			
			String tempNode2="";
			if (tempRelNodes.getNode2().isEvent()==1) {
				tempNode2=tempRelNodes.getNode2().getEventName4();
			}else{
				tempNode2=tempRelNodes.getNode2().getTimeName7();
			}

			// comparision here
			if (node1.equals(tempNode1)||node2.equals(tempNode2)) {
				result = 1;
			}else if (node1.equals(tempNode2)||node2.equals(tempNode1)) {
				result =1;
			}
		}
		return result;
	}

	
	
	// update 20 June 2018
	public int isRelTMX0(List<Sentence> _sentenceList){
		int result=0;
	
		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		List<KeyWord> keyWordList=pSentence.getSentence();
		
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		String node1Word=this.Node1.getWord0().toLowerCase();
		String node2Word=this.Node2.getWord0().toLowerCase();
		
		
		String[] INCLUDED_initiation = {"being", "including", "beloging", "period",
                                        "this day", "this year","this month","this week", "this hour",
                                        "this time", "although", "need", "so far"};
		//{"being", "according", "including", "beloging"}; // this list will be updated
		List<String> INCLUDED_init_list = Arrays.asList(INCLUDED_initiation);

		String[] INCLUDES_initiation = {"being", "would", "might", "will", "says","say"}; // update says, say (29 July)
		List<String> INCLUDES_init_list = Arrays.asList(INCLUDES_initiation);
		
		String[] INCLUDED_verb_initiation = {"need", "needs", "needed", "charged"}; // this list will be updated
		List<String> INCLUDES_verb_init_list = Arrays.asList(INCLUDED_verb_initiation);

		String[] BEFORE_initiation = {"according","before", "issued", "established", "said","found"}; // remove says, say (29 July)
		List<String> BEFORE_init_list = Arrays.asList(BEFORE_initiation);
		
		String[] END_initiation = {"ends", "ended", "ends", "finish", "finishes", "finished"};
		List<String> END_list = Arrays.asList(END_initiation);
		
		String[] POSS_initiation = {"has", "have"}; //possessive at current time
		List<String> POSS_list = Arrays.asList(POSS_initiation);
		
		String[] REPORT_initiation = {"report", "reported", "reports", "claim","claims","claimed"}; // this list will be updated
		List<String> REPORT_init_list = Arrays.asList(REPORT_initiation);
		
		String[] DURING_initiation = {"call", "calls", "called", "occupy","occupies","occupied","face", "faced", "faces"
				                     }; // this list will be updated
		List<String> DURING_list = Arrays.asList(DURING_initiation);
		
		String tense=this.getTenseNode1();
		
		// e1 tmx0 ("The White House", "said", "President Bush has approved duty-free treatment for imports of certain types of watches") )
		String node1="";
		if (this.Node1.isEvent()==1) {
			node1=this.Node1.getEventName4();
		}else{
			node1=this.Node1.getTimeName7();
		}
		
		String node2=this.Node2.getTimeName7();
		String node1Content=this.Node1.getWord0().toLowerCase();
		
		// update 22 August
		for (int i = 1; i < keyWordList.size(); i++) {
			KeyWord temp= keyWordList.get(i);
			if (temp.getWord0().toLowerCase().equals(this.Node1.getWord0().toLowerCase())
				&&keyWordList.get(i-1).getWord0().toLowerCase().equals("still")	
				) {
				this.relaionType="DURING";
            	result=1;
			}
		}
		//  --update 22 August
		// update on 25 September
		if (result==0) {
			if (DURING_list.contains(this.Node1.getWord0().toLowerCase())) {
				this.relaionType="DURING";
	        	result=1;
			}
		}
		// --update on 25 September		
		
		// update on 13 July, 28 July
		if (result==0) { // them dieu kien nay cho update 22 August
			if (node1.indexOf("tmx")!=-1) {
				if (this.isfiscalYear(_sentenceList)==1) {
					System.out.println(this.relaionType);
					result=1;
				}else if (this.isDurationTime(_sentenceList)==1) {
					System.out.println(this.relaionType);
					result=1;
				}else if (this.isTimeTimeRel(this.Node2)==1) {
					System.out.println(this.relaionType);
					result=1;
				}
			}
			// update on 13 July
			else if (node2.equals("tmx0")) {
	            if (rel.indexOf("is included")!=-1||rel.indexOf("are included")!=-1
	            	||rel.indexOf("was included")!=-1||rel.indexOf("were included")!=-1
	            	||rel.indexOf("been included")!=-1
	            	||rel.indexOf("is charged")!=-1||rel.indexOf("are charged")!=-1
	            	||rel.indexOf("was charged")!=-1||rel.indexOf("were charged")!=-1
	               ) {
	            	this.relaionType="IS_INCLUDED";
	            	result=1;
				}else if (rel.indexOf("included")!=-1||rel.indexOf("includes")!=-1||rel.indexOf("include")!=-1
						  ||arg.indexOf("so far")!=-1) { // update 29 July, adding ||arg.indexOf("so far")!=-1
					this.relaionType="INCLUDES";
	            	result=1;
				}
	            // According
				else if ((this.Node1.isEvent()==1&&BEFORE_init_list.contains(this.Node1.getWord0().toLowerCase()))
	            	||(this.Node2.isEvent()==1&&BEFORE_init_list.contains(this.Node2.getWord0().toLowerCase()))	
	            	) {
	            	this.relaionType="BEFORE";
	            	result=1;
	   	 	    }
	            // Possessive rules
				else if (POSS_list.contains(this.Node1.getWord0().toLowerCase())) {
	            	this.relaionType="DURING";
	            	result=1;
				}
	            // for PAST
				else if ((tense.equals("PAST")&&this.Node1.isVerb()==1&&rel.contains(node1Content))
	            	|| (tense.equals("PAST_PERFECT")&&this.Node1.isVerb()==1&&rel.contains(node1Content))
	            	|| (tense.equals("PAST_PASSIVED")&&this.Node1.isVerb()==1&&rel.contains(node1Content))
	            	//update on 25 July
	            	|| (this.Node1.isVerb()==1&&this.Node1.getEventTense6().indexOf("PAST")!=-1)
	               ) {
	            	this.relaionType="BEFORE";
	            	result=1;
	            	System.out.println("BEFORE rule");
	            	System.out.println(tense);
				}
				// for Present //Can phai check lai phan nay, hien tai su dung BEGIN, co the doi thanh AFTER PRESENT+PERFECTIVE
				else if ((tense.equals("PRESENT_PERFECT")&&this.Node1.isVerb()==1&&rel.contains(node1Content))
		            	 //|| (tense.equals("PRESENT_PASSIVED")&&this.Node1.isVerb()==1&&rel.contains(node1Content))
		            	 // update on 25 July
						 || (this.Node1.isVerb()==1&&this.Node1.getEventTense6().indexOf("PRESENT+PERFECTIVE")!=-1)
						 ) {
					this.relaionType="BEFORE"; // phan nay co the dieu chinh
	            	result=1;
	            	System.out.println("BEFORE rule with present perfect tense");
	            	System.out.println(tense);
				} 
				// PRESENT+NONE
				else if ((tense.equals("PRESENT")&&this.Node1.isVerb()==1&&rel.contains(node1Content))
						|| (this.Node1.isVerb()==1&&this.Node1.getEventTense6().indexOf("PRESENT+NONE")!=-1)
						|| (this.Node1.isVerb()==1&&this.Node1.getEventTense6().indexOf("PRESENT+NONE+POS")!=-1)
						|| (tense.equals("PRESENT_PASSIVED")&&this.Node1.isVerb()==1&&rel.contains(node1Content)) // updated 16 august
						) {
	    			 	
	            	this.relaionType="INCLUDES";
	            	result=1;
	            	System.out.println("INCLUDES rule with present tense");
	            	System.out.println(tense);
	    		}
	            // for FUTURE
	            else if ((tense.equals("FUTURE")&&this.Node1.isVerb()==1&&rel.contains(node1Content))
	            		||(this.Node1.isVerb()==1&&this.Node1.getEventTense6().indexOf("FUTURE+NONE")!=-1)
	                    ) {
	    			 	
	            	this.relaionType="AFTER";
	            	result=1;
	            	System.out.println("AFTER rule");
	            	System.out.println(tense);
	    		}
	            // for INCLUDING
	            else if (INCLUDED_init_list.contains(node1Content)
	               ||(tense.equals("PRESENT_CONTINUOUS")&&this.Node1.isVerb()==1&&rel.contains(node1Content))
	           	   || (tense.equals("PAST_CONTINUOUS")&&this.Node1.isVerb()==1&&rel.contains(node1Content))
	           	   || (tense.equals("FUTURE_CONTINUOUS")&&this.Node1.isVerb()==1&&rel.contains(node1Content))
	               ) {
	            	this.relaionType="INCLUDES";
	            	result=1;
	            	System.out.println("INCLUDES rule. noted: double check");
	            	System.out.println(tense);
				}
	            // for time-tmx0 (Now)
	            else if(this.Node1.isTime()==1&&tense.contains("PRESENT")
						||this.Node1.isTime()==1&&tense.contains("PRESENT_REF")
						){
					this.relaionType="INCLUDES";
	            	result=1;
	            	System.out.println("INCLUDES rule");
				}
	            // ("DIG", "is", "the vehicle being used to pursue to acquisition")
	            else if(this.Node1.isVerb()==1){
	            	List<KeyWord> keywordList=pSentence.getSentence();
	    			for (int i = 1; i < keywordList.size(); i++) {
	    				if (keywordList.get(i).equals(this.Node1)){
	    					String temp=keywordList.get(i-1).getWord0().toLowerCase();
	    					if (INCLUDES_init_list.contains(temp)) {
	    						result=1;
	    						this.relaionType="INCLUDES";
	    					}
	    				}
	    			}
	            }else if (INCLUDES_verb_init_list.contains(this.Node1.getWord0().toLowerCase())) {
	            	result=1;
					this.relaionType="IS_INCLUDED";
				}
	 		}
		}
		// tmx0	e2	AFTER, ("DD Acquisition Corp.", "extended", "to Nov. 20"),
		// e2 duoc xac dinh time trong clause
		if (result==0) {
			for (int i = 0; i < keyWordList.size(); i++) {
				KeyWord tempTime= keyWordList.get(i);
				if (tempTime.isTime()==1&&result!=1) {
					relationNodes newRel= new relationNodes();
					newRel.setNode1(tempTime);
					newRel.setNode2(this.Node2);
					newRel.setPropositionNode1(this.getPropositionNode1());
					newRel.setPropositionNode2(this.getPropositionNode2());
					if (newRel.isTimeTimeRel(this.getNode2())==1) {
						result=1;
						this.relaionType=newRel.getRelaionType();
					}	
				}
			}
		}
		// report event with TMX0 => IS_INCLUDED
		if (result==0) {
			if (this.Node1.isVerb()==1&&REPORT_init_list.contains(node1Word)) {
				result=1;
				this.relaionType="IS_INCLUDED";
			}
		}
		// last process, updated on 25 July 2018
		if (result==0){
			if (tense.equals("PAST")||tense.equals("PAST_PASSIVED")) {
				result=1;
				this.relaionType="BEFORE";
			}else if (tense.equals("FUTRE")) {
				result=1;
				this.relaionType="AFTER";
			}else if (tense.equals("PRESENT")) {
				result=1;
				this.relaionType="INCLUDES";
			}else if (tense.equals("PRESENT_CONTINUOUS")
					  ||tense.equals("PAST_CONTINUOUS")
					  ||tense.equals("FUTURE_CONTINUOUS")
					  ){
				result=1;
				this.relaionType="INCLUDES";
			}
		}
		// VBG event => includes update 19 August
		if (result==0) {
			if (this.Node1.isVerbVBG()==1) {
				result=1;
				this.relaionType="INCLUDES";
			}
		}
		// update 20 August
		if (result==0) {
			for (int i = 0; i < keyWordList.size()-1; i++) {
				if (keyWordList.get(i).getWord0().toLowerCase().equals(this.Node1.getWord0().toLowerCase())
					&&keyWordList.get(i+1).getWord0().toLowerCase().equals("before")
					) {	
					result=1;
					this.relaionType="BEFORE";
				}
			}	
		}
		// --update 20 August
		return result;
	}

	// update on 11 July 
	public int isTimeTimeRel(KeyWord pTMX0){
		int result=0;
		//1989-10-31
		String txt0=pTMX0.getTimeValue9();
		String[] arr=txt0.split("-");
		int txm0year=Integer.parseInt(arr[0]);
		int txm0month=Integer.parseInt(arr[1]);
		int txm0day=1;

		if (txtNormalization.isInteger(arr[2])) {
			txm0day=Integer.parseInt(arr[2]);
		}

		Calendar calendarTMX0 = new GregorianCalendar(txm0year,txm0year,txm0day);
		
		String txtTime1=this.Node1.getTimeValue9();
		String txtTime2=this.Node2.getTimeValue9();
		
		// For time event 1
		int txm1year=0;
		int txm1month=0;
		int txm1day=0;
		
		Calendar calendarTime1_min = new GregorianCalendar();
		Calendar calendarTime1_max = new GregorianCalendar();
		
		String[] arrTime1=txtTime1.split("-");
		
		if (StringUtils.isNumeric(txtTime1)) {
			txm1year=Integer.parseInt(txtTime1);
			txm1month=1;
			txm1day=1;
			calendarTime1_min = new GregorianCalendar(txm1year,txm1month,txm1day);
			calendarTime1_max = new GregorianCalendar(txm1year,txm1month,txm1day);
		}else if (txtTime1.contains("PRESENT_REF")) {
			txm1year=txm0year;
			txm1month=txm0month;
			txm1day=txm0day-1; // PRESENT_REF xay ra truoc TMX0
			calendarTime1_min = new GregorianCalendar(txm1year,txm1month,txm1day);
			calendarTime1_max = new GregorianCalendar(txm1year,txm1month,txm1day);
		}else if (txtTime1.contains("PAST_REF")) {
			txm1year=txm0year-1;
			txm1month=txm0month;
			txm1day=txm0day;
			calendarTime1_min = new GregorianCalendar(txm1year,txm1month,txm1day);
			calendarTime1_max = new GregorianCalendar(txm1year,txm1month,txm1day);
		}else if (txtTime1.contains("FUTURE_REF")) {
			txm1year=txm0year;
			txm1month=txm0month;
			txm1day=txm0day+1; 
			calendarTime1_min = new GregorianCalendar(txm1year,txm1month,txm1day);
			calendarTime1_max = new GregorianCalendar(txm1year,txm1month,txm1day);
		}else if (arrTime1.length==3) {
			//1989-10-30
			txm1year=Integer.parseInt(arrTime1[0]);
			txm1month=Integer.parseInt(arrTime1[1]);
			
			if (txtNormalization.isInteger(arrTime1[2])) {
				txm1day=Integer.parseInt(arrTime1[2]);
			}
			
			calendarTime1_min = new GregorianCalendar(txm1year,txm1month,txm1day);
			calendarTime1_max = new GregorianCalendar(txm1year,txm1month,txm1day);
		}else if (arrTime1.length==2) {
			if (StringUtils.isNumeric(arrTime1[1])){
				txm1year=Integer.parseInt(arrTime1[0]);
				txm1month=Integer.parseInt(arrTime1[1]);
				txm1day=1;
				calendarTime1_min = new GregorianCalendar(txm1year,txm1month,txm1day);
				calendarTime1_max = new GregorianCalendar(txm1year,txm1month,txm1day);
			}else{
				txm1year=Integer.parseInt(arrTime1[0]);
				String txm1month_txt=arrTime1[1].toLowerCase();
				txm1day=1;
				if(txm1month_txt.indexOf("q1")!=-1){
					calendarTime1_min = new GregorianCalendar(txm1year,1,txm1day);
					calendarTime1_max = new GregorianCalendar(txm1year,3,0);
				}
				if(txm1month_txt.indexOf("q2")!=-1){
					calendarTime1_min = new GregorianCalendar(txm1year,3,txm1day);
					calendarTime1_max = new GregorianCalendar(txm1year,5,0);
				}
				if(txm1month_txt.indexOf("q3")!=-1){
					calendarTime1_min = new GregorianCalendar(txm1year,6,txm1day);
					calendarTime1_max = new GregorianCalendar(txm1year,8,0);
				}
				if(txm1month_txt.indexOf("q4")!=-1){
					calendarTime1_min = new GregorianCalendar(txm1year,9,txm1day);
					calendarTime1_max = new GregorianCalendar(txm1year,12,0);
				}
			}
			
		}else if (txtTime1.indexOf("1M")!=-1&&arrTime1.length==1) {
			txm1year=txm0year;
			txm1month=1;
			txm1day=1;
			calendarTime1_min = new GregorianCalendar(txm1year,txm1month,txm1day);
			calendarTime1_max = new GregorianCalendar(txm1year,txm1month,txm1day);
		}else if (txtTime1.indexOf("2M")!=-1&&arrTime1.length==1) {
			txm1year=txm0year;
			txm1month=2;
			txm1day=1;
			calendarTime1_min = new GregorianCalendar(txm1year,txm1month,txm1day);
			calendarTime1_max = new GregorianCalendar(txm1year,txm1month,txm1day);
		}else if (txtTime1.indexOf("3M")!=-1&&arrTime1.length==1) {
			txm1year=txm0year;
			txm1month=3;
			txm1day=1;
			calendarTime1_min = new GregorianCalendar(txm1year,txm1month,txm1day);
			calendarTime1_max = new GregorianCalendar(txm1year,txm1month,txm1day);
		}else if (txtTime1.indexOf("4M")!=-1&&arrTime1.length==1) {
			txm1year=txm0year;
			txm1month=4;
			txm1day=1;
			calendarTime1_min = new GregorianCalendar(txm1year,txm1month,txm1day);
			calendarTime1_max = new GregorianCalendar(txm1year,txm1month,txm1day);
		}else if (txtTime1.indexOf("5M")!=-1&&arrTime1.length==1) {
			txm1year=txm0year;
			txm1month=5;
			txm1day=1;
			calendarTime1_min = new GregorianCalendar(txm1year,txm1month,txm1day);
			calendarTime1_max = new GregorianCalendar(txm1year,txm1month,txm1day);
		}else if (txtTime1.indexOf("6M")!=-1&&arrTime1.length==1) {
			txm1year=txm0year;
			txm1month=6;
			txm1day=1;
			calendarTime1_min = new GregorianCalendar(txm1year,txm1month,txm1day);
			calendarTime1_max = new GregorianCalendar(txm1year,txm1month,txm1day);
		}else if (txtTime1.indexOf("7M")!=-1&&arrTime1.length==1) {
			txm1year=txm0year;
			txm1month=7;
			txm1day=1;
			calendarTime1_min = new GregorianCalendar(txm1year,txm1month,txm1day);
			calendarTime1_max = new GregorianCalendar(txm1year,txm1month,txm1day);
		}else if (txtTime1.indexOf("8M")!=-1&&arrTime1.length==1) {
			txm1year=txm0year;
			txm1month=8;
			txm1day=1;
			calendarTime1_min = new GregorianCalendar(txm1year,txm1month,txm1day);
			calendarTime1_max = new GregorianCalendar(txm1year,txm1month,txm1day);
		}else if (txtTime1.indexOf("9M")!=-1&&arrTime1.length==1) {
			txm1year=txm0year;
			txm1month=9;
			txm1day=1;
			calendarTime1_min = new GregorianCalendar(txm1year,txm1month,txm1day);
			calendarTime1_max = new GregorianCalendar(txm1year,txm1month,txm1day);
		}else if (txtTime1.indexOf("10M")!=-1&&arrTime1.length==1) {
			txm1year=txm0year;
			txm1month=10;
			txm1day=1;
			calendarTime1_min = new GregorianCalendar(txm1year,txm1month,txm1day);
			calendarTime1_max = new GregorianCalendar(txm1year,txm1month,txm1day);
		}else if (txtTime1.indexOf("11M")!=-1&&arrTime1.length==1) {
			txm1year=txm0year;
			txm1month=11;
			txm1day=1;
			calendarTime1_min = new GregorianCalendar(txm1year,txm1month,txm1day);
			calendarTime1_max = new GregorianCalendar(txm1year,txm1month,txm1day);
		}else if (txtTime1.indexOf("12M")!=-1&&arrTime1.length==1) {
			txm1year=txm0year;
			txm1month=12;
			txm1day=1;
			calendarTime1_min = new GregorianCalendar(txm1year,txm1month,txm1day);
			calendarTime1_max = new GregorianCalendar(txm1year,txm1month,txm1day);
		}
		
		// For time event 2
		int txm2year=0;
		int txm2month=0;
		int txm2day=0;
		//String txtTime2="1990";
		Calendar calendarTime2_min = new GregorianCalendar();
		Calendar calendarTime2_max = new GregorianCalendar();
		
		String[] arrTime2=txtTime2.split("-");
		
		if (StringUtils.isNumeric(txtTime2)) {
			txm2year=Integer.parseInt(txtTime2);
			txm2month=1;
			txm2day=1;
			calendarTime2_min = new GregorianCalendar(txm2year,txm2month,txm2day);
			calendarTime2_max = new GregorianCalendar(txm2year,txm2month,txm2day);
		}else if (txtTime2.contains("PRESENT_REF")) {
			txm2year=txm0year;
			txm2month=txm0month;
			txm2day=txm0day-1;
			calendarTime2_min = new GregorianCalendar(txm2year,txm2month,txm2day);
			calendarTime2_max = new GregorianCalendar(txm2year,txm2month,txm2day);
		}else if (txtTime2.contains("PAST_REF")) {
			txm2year=txm0year-1;
			txm2month=txm0month;
			txm2day=txm0day;
			calendarTime2_min = new GregorianCalendar(txm2year,txm2month,txm2day);
			calendarTime2_max = new GregorianCalendar(txm2year,txm2month,txm2day);
		}else if (txtTime2.contains("FUTURE_REF")) {
			txm2year=txm0year;
			txm2month=txm0month;
			txm2day=txm0day+1;
			calendarTime2_min = new GregorianCalendar(txm2year,txm2month,txm2day);
			calendarTime2_max = new GregorianCalendar(txm2year,txm2month,txm2day);
		}else if (arrTime2.length==3) {
			txm2year=Integer.parseInt(arrTime2[0]);
			txm2month=Integer.parseInt(arrTime2[1]);
			if (txtNormalization.isInteger(arrTime2[2])) {
				txm2day=Integer.parseInt(arrTime2[2]);
			}
			calendarTime2_min = new GregorianCalendar(txm2year,txm2month,txm2day);
			calendarTime2_max = new GregorianCalendar(txm2year,txm2month,txm2day);
		}else if (arrTime2.length==2) {
			if (StringUtils.isNumeric(arrTime2[1])){
				txm2year=Integer.parseInt(arrTime2[0]);
				txm2month=Integer.parseInt(arrTime2[1]);
				txm2day=1;
				calendarTime2_min = new GregorianCalendar(txm2year,txm2month,txm2day);
				calendarTime2_max = new GregorianCalendar(txm2year,txm2month,txm2day);
			}else{
				txm2year=Integer.parseInt(arrTime2[0]);
				String txm2month_txt=arrTime2[1].toLowerCase();
				txm2day=1;
				if(txm2month_txt.indexOf("q1")!=-1){
					calendarTime2_min = new GregorianCalendar(txm2year,1,txm2day);
					calendarTime2_max = new GregorianCalendar(txm2year,3,0);
				}
				if(txm2month_txt.indexOf("q2")!=-1){
					calendarTime2_min = new GregorianCalendar(txm2year,3,txm2day);
					calendarTime2_max = new GregorianCalendar(txm2year,5,0);
				}
				if(txm2month_txt.indexOf("q3")!=-1){
					calendarTime2_min = new GregorianCalendar(txm2year,6,txm2day);
					calendarTime2_max = new GregorianCalendar(txm2year,8,0);
				}
				if(txm2month_txt.indexOf("q4")!=-1){
					calendarTime2_min = new GregorianCalendar(txm2year,9,txm2day);
					calendarTime2_max = new GregorianCalendar(txm2year,12,0);
				}
			}
			
		}else if (txtTime2.indexOf("1M")!=-1&&arrTime2.length==1) {
			txm2year=txm0year;
			txm2month=1;
			txm2day=1;
			calendarTime2_min = new GregorianCalendar(txm2year,txm2month,txm2day);
			calendarTime2_max = new GregorianCalendar(txm2year,txm2month,txm2day);
		}else if (txtTime2.indexOf("2M")!=-1&&arrTime2.length==1) {
			txm2year=txm0year;
			txm2month=2;
			txm2day=1;
			calendarTime2_min = new GregorianCalendar(txm2year,txm2month,txm2day);
			calendarTime2_max = new GregorianCalendar(txm2year,txm2month,txm2day);
		}else if (txtTime2.indexOf("3M")!=-1&&arrTime2.length==1) {
			txm2year=txm0year;
			txm2month=3;
			txm2day=1;
			calendarTime2_min = new GregorianCalendar(txm2year,txm2month,txm2day);
			calendarTime2_max = new GregorianCalendar(txm2year,txm2month,txm2day);
		}else if (txtTime2.indexOf("4M")!=-1&&arrTime2.length==1) {
			txm2year=txm0year;
			txm2month=4;
			txm2day=1;
			calendarTime2_min = new GregorianCalendar(txm2year,txm2month,txm2day);
			calendarTime2_max = new GregorianCalendar(txm2year,txm2month,txm2day);
		}else if (txtTime2.indexOf("5M")!=-1&&arrTime2.length==1) {
			txm2year=txm0year;
			txm2month=5;
			txm2day=1;
			calendarTime2_min = new GregorianCalendar(txm2year,txm2month,txm2day);
			calendarTime2_max = new GregorianCalendar(txm2year,txm2month,txm2day);
		}else if (txtTime2.indexOf("6M")!=-1&&arrTime2.length==1) {
			txm2year=txm0year;
			txm2month=6;
			txm2day=1;
			calendarTime2_min = new GregorianCalendar(txm2year,txm2month,txm2day);
			calendarTime2_max = new GregorianCalendar(txm2year,txm2month,txm2day);
		}else if (txtTime2.indexOf("7M")!=-1&&arrTime2.length==1) {
			txm2year=txm0year;
			txm2month=7;
			txm2day=1;
			calendarTime2_min = new GregorianCalendar(txm2year,txm2month,txm2day);
			calendarTime2_max = new GregorianCalendar(txm2year,txm2month,txm2day);
		}else if (txtTime2.indexOf("8M")!=-1&&arrTime2.length==1) {
			txm2year=txm0year;
			txm2month=8;
			txm2day=1;
			calendarTime2_min = new GregorianCalendar(txm2year,txm2month,txm2day);
			calendarTime2_max = new GregorianCalendar(txm2year,txm2month,txm2day);
		}else if (txtTime2.indexOf("9M")!=-1&&arrTime2.length==1) {
			txm2year=txm0year;
			txm2month=9;
			txm2day=1;
			calendarTime2_min = new GregorianCalendar(txm2year,txm2month,txm2day);
			calendarTime2_max = new GregorianCalendar(txm2year,txm2month,txm2day);
		}else if (txtTime2.indexOf("10M")!=-1&&arrTime2.length==1) {
			txm2year=txm0year;
			txm2month=10;
			txm2day=1;
			calendarTime2_min = new GregorianCalendar(txm2year,txm2month,txm2day);
			calendarTime2_max = new GregorianCalendar(txm2year,txm2month,txm2day);
		}else if (txtTime2.indexOf("11M")!=-1&&arrTime2.length==1) {
			txm2year=txm0year;
			txm2month=11;
			txm2day=1;
			calendarTime2_min = new GregorianCalendar(txm2year,txm2month,txm2day);
			calendarTime2_max = new GregorianCalendar(txm2year,txm2month,txm2day);
		}else if (txtTime2.indexOf("12M")!=-1&&arrTime2.length==1) {
			txm2year=txm0year;
			txm2month=12;
			txm2day=1;
			calendarTime2_min = new GregorianCalendar(txm2year,txm2month,txm2day);
			calendarTime2_max = new GregorianCalendar(txm2year,txm2month,txm2day);
		}		

		/*
		String txt3="1989-Q3";
		String txt4="1989-10";
		String txt5="1989-Q4";
		String txt6="P9M";
		String txt7="1988-Q2";
		*/
		
		
		int yearTime1 = calendarTime1_min.get(Calendar.YEAR);
		int DayofYearTime1_min = calendarTime1_min.get(Calendar.DAY_OF_YEAR);
		int DayofYearTime1_max = calendarTime1_max.get(Calendar.DAY_OF_YEAR);
		
		int yearTime2 = calendarTime2_min.get(Calendar.YEAR);
		int DayofYearTime2_min = calendarTime2_min.get(Calendar.DAY_OF_YEAR);
		int DayofYearTime2_max = calendarTime2_max.get(Calendar.DAY_OF_YEAR);
		
		System.out.println(txtTime1);
		System.out.println(yearTime1);
		System.out.println(calendarTime1_min.get(Calendar.MONTH));
		System.out.println(DayofYearTime1_min);
		System.out.println(DayofYearTime1_max);
		
		System.out.println();
		System.out.println(txtTime2);
		System.out.println(yearTime2);
		System.out.println(calendarTime2_min.get(Calendar.MONTH));;
		System.out.println(DayofYearTime2_min);
		System.out.println(DayofYearTime2_max);
		
		if (txtTime1.equals(txtTime2)) {
			//result="IDENTITY";
			this.relaionType="INCLUDES/SIMULTANEOUS";
			System.out.println("INCLUDES/SIMULTANEOUS");
			result=1;
		}else if (yearTime1>yearTime2) {
			//result="AFTER";
			this.relaionType="AFTER";
			System.out.println("AFTER");
			result=1;
		}else if(yearTime1<yearTime2) {
			//result="BEFORE";
			this.relaionType="BEFORE";
			System.out.println("BEFORE");
			result=1;
		}else if (DayofYearTime1_min>DayofYearTime2_min) {
			//result="AFTER";
			this.relaionType="AFTER";
			System.out.println("AFTER");
			result=1;
		}else if (DayofYearTime1_min<DayofYearTime2_min) {
			//result="BEFORE";
			this.relaionType="BEFORE";
			System.out.println("BEFORE");
			result=1;
		}else if (DayofYearTime1_min==DayofYearTime2_min) {
			if (DayofYearTime1_max>DayofYearTime2_max) {
				//result="AFTER";
				this.relaionType="AFTER";
				System.out.println("AFTER");
				result=1;
			}else if (DayofYearTime1_max<DayofYearTime2_max) {
				//result="BEFORE";
				this.relaionType="BEFORE";
				System.out.println("BEFORE");
				result=1;
			}
		} 
		return result;
	}
	// --update on 11 July
	
	// update on 27 June
	public int isFound(String node1Label, String node2Label){
		int result=0;
		String node1Content="";
		if (this.Node1.isEvent()==1) {
			node1Content=this.Node1.getEventName4();
		}else{
			node1Content=this.Node1.getTimeName7();
		}
		
		String node2Content="";
		if (this.Node2.isEvent()==1) {
			node2Content=this.Node2.getEventName4();
		}else{
			node2Content=this.Node2.getTimeName7();
		}
		
		if (node1Content.equals(node1Label)&&node2Content.equals(node2Label)) {
			result=1;
		}
		return result;
	}
	
	// update on 3 March
	public int isFoundForCausal(String node1Label, String node2Label){
		int result=0;
		
		String node1Content="";
		if (this.Node1.isEvent()==1) {
			node1Content=this.Node1.getEventName4();
		}
		
		String node2Content="";
		if (this.Node2.isEvent()==1) {
			node2Content=this.Node2.getEventName4();
		}
		
		if ((node1Content.equals(node1Label)||node2Content.equals(node2Label))
			||(node1Content.equals(node2Label)||node2Content.equals(node1Label))	
		   ){
				result=1;
		}
		return result;
	}
	
	public int isReverse(String node1Label, String node2Label){
		int result=0;
		String node1Content="";
		if (this.Node1.isEvent()==1) {
			node1Content=this.Node1.getEventName4();
		}else{
			node1Content=this.Node1.getTimeName7();
		}
		
		String node2Content="";
		if (this.Node2.isEvent()==1) {
			node2Content=this.Node2.getEventName4();
		}else{
			node2Content=this.Node2.getTimeName7();
		}
		
		if (node1Content.equals(node2Label)&&node2Content.equals(node1Label)) {
			result=1;
		}
		return result;
	}

	public void reverse(){
		if (this.relaionType.equals("BEFORE")) {
			this.relaionType="AFTER";
		}else if (this.relaionType.equals("AFTER")) {
			this.relaionType="BEFORE";
		}
	}

	public void reverseCausal(){
		if (this.relaionType.equals("CLINK-C")) {
			this.relaionType="CLINK";
		}else if (this.relaionType.equals("CLINK")) {
			this.relaionType="CLINK-C";
		}
	}

	
	
	// for causal relation only (update on 16 March 2018)
	public int isThis_Causal(List<Sentence> _sentenceList){
		int result=0;
		String[] This_initiation = {"this", "it", "that", "these"}; // this list will be updated
		List<String> This_init_list = Arrays.asList(This_initiation);
		
		String[] TOBE_initiation = {"is", "be", "are", "will", "was", "were"}; // this list will be updated
		List<String> TOBE_init_list = Arrays.asList(TOBE_initiation);

		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		List<KeyWord> keywordList=pSentence.getSentence();
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		String node1Word=this.Node1.getWord0().toLowerCase();
		String node2Word=this.Node2.getWord0().toLowerCase();

		int Node1Pos=0;
		int Node2Pos=0;
		for (int i = 0; i < keywordList.size(); i++) {
			KeyWord tempWord=keywordList.get(i);
			if (tempWord.equals(this.Node1)) {
				Node1Pos=i;
			}
			if (tempWord.equals(this.Node2)) {
				Node2Pos=i;
			}
		}
		
		if (This_init_list.contains(node1Word)&&TOBE_init_list.contains(node2Word)) {
			result=1;
			this.relaionType="CLINK";
		}
		if (result==0) {
			for (int i = 0; i < keywordList.size()-1; i++) {
				KeyWord tempWord=keywordList.get(i);
				if (This_init_list.contains(this.Node1.getWord0().toLowerCase())
					&&tempWord.equals(this.Node1)	
					&&keywordList.get(i+1).getWord0().toLowerCase().equals("will")
					&&Node1Pos<Node2Pos
					) {
					result=1;
					this.relaionType="CLINK";
				}else if (This_init_list.contains(this.Node2.getWord0().toLowerCase())
						&&keywordList.get(i+1).getWord0().toLowerCase().equals("will")
						&&Node2Pos<Node1Pos
						) {
						result=1;
						this.relaionType="CLINK";
					  }
			}
		}

		if (result==0) {
			if (TOBE_init_list.contains(node1Word)) {
				KeyWord tempWord=keywordList.get(Node1Pos+1);
				if (This_init_list.contains(tempWord.getWord0().toLowerCase())) {
					result=1;
					this.relaionType="CLINK";
				}
			}
		}
		
		return result;
	}
	
	public int isCAUSE_Causal(List<Sentence> _sentenceList){
		int result=0;
		String[] CAUSE_initiation = {"will", "cause", "make", "made", "caused", "would", "could", "should", "that","makes","making", "causing", "may"
				                    , "force", "forced", "forcing","blocked", "block", "blocking", "off","reignite", "reignited","reigniting"
				                    , "marked","marks", "mark","marking","give","gaves","given","giving", "allow","allowed","allowing","allowes"
				                    , "help", "helped", "helps", "signal", "signaled","signalling", "signals", "resulted", "results", "result"
				                    , "resulting"
				                    , "restructure", "restructuring", "restructured", "restructures"
				                    , "reflect", "reflected", "reflects","reflecting"
				                    , "block", "blocked", "blockes", "blocking"
				                    , "consequently", "consequent"
				                    , "therefore"
				                    , "thus"
				                    , "send", "sent", "sends", "sending"
				                    , "request", "requests", "requested", "requesting"
				                    , "give", "gives", "given"
				                    , "avoid", "avoids", "avoided", "avoiding"
									}; // this list will be updated
		List<String> CAUSE_init_list = Arrays.asList(CAUSE_initiation);
		
		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		List<KeyWord> keywordList=pSentence.getSentence();
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		String node1Word=this.Node1.getWord0().toLowerCase();
		String node2Word=this.Node2.getWord0().toLowerCase();

		int Node1Pos=-1;
		int Node2Pos=-1;
		int CAUSEPos=-1;
		for (int i = 0; i < keywordList.size(); i++) {
			KeyWord tempWord=keywordList.get(i);
			if (tempWord.equals(this.Node1)) {
				Node1Pos=i;
			}
			if (tempWord.equals(this.Node2)) {
				Node2Pos=i;
			}
			if (CAUSE_init_list.contains(tempWord.getWord0().toLowerCase())) {
				CAUSEPos=i;
			}
		}
		
		for (int i = 0; i < keywordList.size(); i++) {
			KeyWord tempWord=keywordList.get(i);
			if (tempWord.isNoun()!=1) { // Bo sung dieu kien cua Verb, -> loai tru*` cac truong hop result of
				if (CAUSE_init_list.contains(tempWord.getWord0().toLowerCase())) {
					CAUSEPos=i;
					if ((Node1Pos>-1&&Node2Pos>-1&CAUSEPos>-1)
							&&(Node1Pos<CAUSEPos&&Node2Pos>CAUSEPos)	
							) {
							result=1;
							this.relaionType="CLINK";
						}else if ((Node1Pos>-1&&Node2Pos>-1&CAUSEPos>-1)
								&&(Node1Pos==CAUSEPos&&Node2Pos>CAUSEPos)	
								) {
								result=1;
								this.relaionType="CLINK";
						}else if ((Node1Pos>-1&&Node2Pos>-1&CAUSEPos>-1)
								&&(Node1Pos>CAUSEPos&&Node2Pos==CAUSEPos)	
								) {
								result=1;
								this.relaionType="CLINK-C";
						}
	
				}
			}
		}
		return result;
	}
		
	public int isBY_Causal(List<Sentence> _sentenceList){
		int result=0;
		String[] CAUSE_initiation = {"by"}; // this list will be updated
		List<String> CAUSE_init_list = Arrays.asList(CAUSE_initiation);
		
		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		List<KeyWord> keywordList=pSentence.getSentence();
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		String node1Word=this.Node1.getWord0().toLowerCase();
		String node2Word=this.Node2.getWord0().toLowerCase();

		int Node1Pos=-1;
		int Node2Pos=-1;
		int BYPos=-1;
		for (int i = 0; i < keywordList.size(); i++) {
			KeyWord tempWord=keywordList.get(i);
			if (tempWord.equals(this.Node1)) {
				Node1Pos=i;
			}
			if (tempWord.equals(this.Node2)) {
				Node2Pos=i;
			}
			if (CAUSE_init_list.contains(tempWord.getWord0().toLowerCase())) {
				BYPos=i;
			}
		}

		String tempStr="";
		if (BYPos>-1) {
			tempStr=keywordList.get(BYPos).getDpStatus16().replace("||", "-tab-");
		}
		String[] dpBY=tempStr.split("-tab-"); // T2, T5, T6 (select Select keywords with IDs T5 and T6)
		String Tnode1=this.Node1.getId1();
		String Tnode2=this.Node2.getId1();
		
		for (int i = 0; i < dpBY.length; i++) {
			String temp=dpBY[i];
			if (temp.indexOf(Tnode1)!=-1) {
				System.out.println("Note 1");
				result=1;
				this.relaionType="CLINK";
			}else if (temp.indexOf(Tnode2)!=-1) {
				System.out.println("Note 2");
				result=1;
				this.relaionType="CLINK-C";
			}
		}
		
		if (result==0) {
			String tempStr2="";
			String BYID="";
			if (BYPos>-1) {
				BYID=keywordList.get(BYPos).getId1();
			}
			if (Node2Pos>-1) {
				tempStr2=this.Node2.getDpStatus16().replace("||", "-tab-");
			}
			String[] dpNode2=tempStr2.split("-tab-"); // T2, T5, T6 (select Select keywords with IDs T5 and T6)

			for (int i = 0; i < dpNode2.length; i++) {
				String temp=dpNode2[i];
				if (temp.indexOf(BYID)!=-1&&Node1Pos>BYPos
					&&BYPos>-1) {
					System.out.println("Note 3");
					result=1;
					this.relaionType="CLINK";
				}
			}
			
		}
		
		if (result==0) {
			int tempPos=-1;
			for (int i = 0; i < keywordList.size()-1; i++) {
				KeyWord tempWord=keywordList.get(i);
				if (tempWord.equals(this.Node1)&&keywordList.get(i+1).getWord0().toLowerCase().equals("by")
					&&Node2Pos>i+1
					) {
					System.out.println("Note 4");
					result=1;
					this.relaionType="CLINK";
				}else if (tempWord.equals(this.Node2)&&keywordList.get(i+1).getWord0().toLowerCase().equals("by")
						&&Node1Pos>i+1
						) {
						System.out.println("Note 5");
						result=1;
						this.relaionType="CLINK-C";
				}
			}	
		}
		return result;
	}

	public int isBECAUSEOF_Causal(List<Sentence> _sentenceList){
		int result=0;
		String[] CAUSE_initiation = {"because", "due"}; // this list will be updated
		List<String> CAUSE_init_list = Arrays.asList(CAUSE_initiation);
		
		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		List<KeyWord> keywordList=pSentence.getSentence();
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		String node1Word=this.Node1.getWord0().toLowerCase();
		String node2Word=this.Node2.getWord0().toLowerCase();

		int Node1Pos=-1;
		int Node2Pos=-1;
		int BECAUSEPos=-1;
		for (int i = 0; i < keywordList.size(); i++) {
			KeyWord tempWord=keywordList.get(i);
			if (tempWord.equals(this.Node1)) {
				Node1Pos=i;
			}
			if (tempWord.equals(this.Node2)) {
				Node2Pos=i;
			}
			if (CAUSE_init_list.contains(tempWord.getWord0().toLowerCase())) {
				BECAUSEPos=i;
			}
		}
		
		String tempStr="";
		if (BECAUSEPos>-1) {
			tempStr=keywordList.get(BECAUSEPos).getDpStatus16().replace("||", "-tab-");
		}
		String[] dpBECAUSE=tempStr.split("-tab-"); // T2, T5, T6 (select Select keywords with IDs T5 and T6)
		String Tnode1=this.Node1.getId1();
		String Tnode2=this.Node2.getId1();

		for (int i = 0; i < dpBECAUSE.length; i++) {
			String temp=dpBECAUSE[i];
			String tempTO=keywordList.get(BECAUSEPos+1).getDpStatus16();
			
			if ((temp.indexOf(Tnode1)!=-1&&keywordList.get(BECAUSEPos+1).getWord0().contains("of"))
				||(tempTO.indexOf(Tnode1)!=-1&&keywordList.get(BECAUSEPos+1).getWord0().contains("to"))	
			   ){
				result=1;
				this.relaionType="CLINK";
			}else if ((temp.indexOf(Tnode2)!=-1&&keywordList.get(BECAUSEPos+1).getWord0().contains("of"))
					  || (tempTO.indexOf(Tnode2)!=-1&&keywordList.get(BECAUSEPos+1).getWord0().contains("to"))
					 ){
				result=1;
				this.relaionType="CLINK-C";
			}
		}
		return result;
	}
	
	public int isBECAUSE_Causal(List<Sentence> _sentenceList){
		int result=0;
		String[] CAUSE_initiation = {"because"}; // this list will be updated
		List<String> CAUSE_init_list = Arrays.asList(CAUSE_initiation);
		
		String[] VerbDo_initiation = {"do", "does", "did", "are", "is", "were", "was"}; // this list will be updated
		List<String> VerbDo_init_list = Arrays.asList(VerbDo_initiation);
		
		
		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		List<KeyWord> keywordList=pSentence.getSentence();
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		String node1Word=this.Node1.getWord0().toLowerCase();
		String node2Word=this.Node2.getWord0().toLowerCase();

		int Node1Pos=-1;
		int Node2Pos=-1;
		int BECAUSEPos=-1;
		for (int i = 0; i < keywordList.size(); i++) {
			KeyWord tempWord=keywordList.get(i);
			if (tempWord.equals(this.Node1)) {
				Node1Pos=i;
			}
			if (tempWord.equals(this.Node2)) {
				Node2Pos=i;
			}
			if (CAUSE_init_list.contains(tempWord.getWord0().toLowerCase())) {
				BECAUSEPos=i;
			}
		}
		
		String tempStr="";
		if (BECAUSEPos>-1) {
			tempStr=keywordList.get(BECAUSEPos).getDpStatus16().replace("||", "-tab-");
		}
		String[] dpBECAUSE=tempStr.split("-tab-"); // T2, T5, T6 (select Select keywords with IDs T5 and T6)
		String Tnode1=this.Node1.getId1();
		String Tnode2=this.Node2.getId1();

		for (int i = 0; i < dpBECAUSE.length; i++) {
			String temp=dpBECAUSE[i];
			
			//------------------------------ update 23 March
			String VerbID=temp.split(":")[0];
			int VerbPos=-1;
			for (int j = 0; j < keywordList.size(); j++) {
				if (keywordList.get(j).getId1().equals(VerbID)) {
					VerbPos=j;
				}
			}
			KeyWord VerbKeyWord=new KeyWord();
			String VerbDoDP="";
			if (VerbPos>-1) {
				VerbKeyWord=keywordList.get(VerbPos);
				VerbDoDP=keywordList.get(VerbPos).getDpStatus16();
			}
			
			//------------------------------
			
			if (temp.indexOf(Tnode1)!=-1) {
				result=1;
				this.relaionType="CLINK";
			}else if (temp.indexOf(Tnode2)!=-1) {
				result=1;
				this.relaionType="CLINK-C";
			}else if (VerbDo_init_list.contains(VerbKeyWord.getWord0().toLowerCase())&&VerbDoDP.indexOf(Tnode1)!=-1) {
					result=1;
					this.relaionType="CLINK";
				}else if (VerbDo_init_list.contains(VerbKeyWord.getWord0().toLowerCase())&&VerbDoDP.indexOf(Tnode2)!=-1) {
					result=1;
					this.relaionType="CLINK-C";
				}
			}
			
			// Phan nay co the se remove do rule chu*a duoc chat che
			if (result==0&&Node1Pos>-1&&Node2Pos>-1&BECAUSEPos>-1) {
				if (Node1Pos>BECAUSEPos&&BECAUSEPos>Node2Pos) {
					result=1;
					this.relaionType="CLINK";
				}else if (Node1Pos<BECAUSEPos&&BECAUSEPos<Node2Pos) {
					result=1;
					this.relaionType="CLINK-C";
				} 
			}
		
		
		return result;
	}

	public int isAFTER_Causal(List<Sentence> _sentenceList){
		int result=0;
		String[] AFTER_initiation = {"after", "since"}; // this list will be updated
		List<String> AFTER_init_list = Arrays.asList(AFTER_initiation);
		
		String[] VerbDo_initiation = {"do", "does", "did"}; // this list will be updated
		List<String> VerbDo_init_list = Arrays.asList(VerbDo_initiation);
		
		
		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		List<KeyWord> keywordList=pSentence.getSentence();
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		String node1Word=this.Node1.getWord0().toLowerCase();
		String node2Word=this.Node2.getWord0().toLowerCase();

		int Node1Pos=-1;
		int Node2Pos=-1;
		int AFTERPos=-1;
		for (int i = 0; i < keywordList.size(); i++) {
			KeyWord tempWord=keywordList.get(i);
			if (tempWord.equals(this.Node1)) {
				Node1Pos=i;
			}
			if (tempWord.equals(this.Node2)) {
				Node2Pos=i;
			}
			if (AFTER_init_list.contains(tempWord.getWord0().toLowerCase())) {
				AFTERPos=i;
			}
		}
		
		String tempStr="";
		if (AFTERPos>-1) {
			tempStr=keywordList.get(AFTERPos).getDpStatus16().replace("||", "-tab-");
		}
		String[] dpAFTER=tempStr.split("-tab-"); // T2, T5, T6 (select Select keywords with IDs T5 and T6)
		String Tnode1=this.Node1.getId1();
		String Tnode2=this.Node2.getId1();

		for (int i = 0; i < dpAFTER.length; i++) {
			String temp=dpAFTER[i];
			
			//------------------------------ update 23 March
			String VerbID=temp.split(":")[0];
			int VerbPos=-1;
			for (int j = 0; j < keywordList.size(); j++) {
				if (keywordList.get(j).getId1().equals(VerbID)) {
					VerbPos=j;
				}
			}
			KeyWord VerbKeyWord=new KeyWord();
			String VerbDoDP="";
			if (VerbPos>-1) {
				VerbKeyWord=keywordList.get(VerbPos);
				VerbDoDP=keywordList.get(VerbPos).getDpStatus16();
			}
			
			//------------------------------
			
			if (temp.indexOf(Tnode1)!=-1) {
				result=1;
				this.relaionType="CLINK";
			}else if (temp.indexOf(Tnode2)!=-1) {
				result=1;
				this.relaionType="CLINK-C";
			}else if (VerbDo_init_list.contains(VerbKeyWord.getWord0().toLowerCase())&&VerbDoDP.indexOf(Tnode1)!=-1) {
					result=1;
					this.relaionType="CLINK-C";
				}else if (VerbDo_init_list.contains(VerbKeyWord.getWord0().toLowerCase())&&VerbDoDP.indexOf(Tnode2)!=-1) {
					result=1;
					this.relaionType="CLINK";
				}
			}
		return result;
	}
	
	
	public int isLEAD_Causal(List<Sentence> _sentenceList){
		int result=0;
		String[] LEAD_initiation = {"lead", "led","leading", "reignite", "reignited","reigniting","provoked", "provoke", "provoking"
									,"make", "made", "making", "get", "getting", "got", "gotten"
									, "request", "requests", "requested", "requesting"
									, "success", "successed", "successes"
									}; // this list will be updated
		List<String> LEAD_init_list = Arrays.asList(LEAD_initiation);
		
		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		List<KeyWord> keywordList=pSentence.getSentence();
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		String node1Word=this.Node1.getWord0().toLowerCase();
		String node2Word=this.Node2.getWord0().toLowerCase();

		int Node1Pos=-1;
		int Node2Pos=-1;
		int CAUSEPos=-1;
		for (int i = 0; i < keywordList.size(); i++) {
			KeyWord tempWord=keywordList.get(i);
			if (tempWord.equals(this.Node1)) {
				Node1Pos=i;
			}
			if (tempWord.equals(this.Node2)) {
				Node2Pos=i;
			}
			if (LEAD_init_list.contains(tempWord.getWord0().toLowerCase())) {
				CAUSEPos=i;
			}
		}
		if ((Node1Pos>-1&&Node2Pos>-1&CAUSEPos>-1)
			&&(Node1Pos<CAUSEPos&&Node2Pos>CAUSEPos)	
			) {
			result=1;
			this.relaionType="CLINK";
		}else if ((Node1Pos>-1&&Node2Pos>-1&CAUSEPos>-1)
				&&(Node1Pos==CAUSEPos&&Node2Pos>CAUSEPos)	
				) {
				result=1;
				this.relaionType="CLINK";
		}else if ((Node1Pos>-1&&Node2Pos>-1&CAUSEPos>-1)
				&&(Node1Pos>CAUSEPos&&Node2Pos==CAUSEPos)	
				) {
				result=1;
				this.relaionType="CLINK-C";
		}
		return result;
	}

	public int isWillCAUSE_Causal(List<Sentence> _sentenceList){
		int result=0;
		String[] WillCAUSE_initiation = {"will","willing"}; // this list will be updated
		List<String> WillCAUSE_init_list = Arrays.asList(WillCAUSE_initiation);
		
		String[] ResponseCAUSE_initiation = {"response","responsed", "responsing"}; // this list will be updated
		List<String> ResponseCAUSE_init_list = Arrays.asList(ResponseCAUSE_initiation);
		
		
		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		List<KeyWord> keywordList=pSentence.getSentence();
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		String node1Word=this.Node1.getWord0().toLowerCase();
		String node2Word=this.Node2.getWord0().toLowerCase();

		int Node1Pos=-1;
		int Node2Pos=-1;
		int CAUSEPos=-1;
		for (int i = 0; i < keywordList.size(); i++) {
			KeyWord tempWord=keywordList.get(i);
			if (tempWord.equals(this.Node1)) {
				Node1Pos=i;
			}
			if (tempWord.equals(this.Node2)) {
				Node2Pos=i;
			}
		}
		
		if (Node1Pos>2) {
			if ((keywordList.get(Node1Pos-1).getWord0().equals("to")&&WillCAUSE_init_list.contains(keywordList.get(Node1Pos-2).getWord0().toLowerCase()))
				||WillCAUSE_init_list.contains(keywordList.get(Node1Pos-1).getWord0().toLowerCase())	
			   ) {
				result=1;
				this.relaionType="CLINK-C";
			}
		}
		if (result==0&&Node2Pos>2) {
			if ((keywordList.get(Node2Pos-1).getWord0().equals("to")&&WillCAUSE_init_list.contains(keywordList.get(Node2Pos-2).getWord0().toLowerCase()))
					||WillCAUSE_init_list.contains(keywordList.get(Node2Pos-1).getWord0().toLowerCase())	
				   ) {
					result=1;
					this.relaionType="CLINK";
				}
		}
		if (Node1Pos>2&&result==0) {
			if ((keywordList.get(Node1Pos-1).getWord0().equals("to")&&ResponseCAUSE_init_list.contains(keywordList.get(Node1Pos-2).getWord0().toLowerCase()))
			   ) {
				result=1;
				this.relaionType="CLINK";
			}
		}
		if (Node2Pos>2&&result==0) {
			if ((keywordList.get(Node2Pos-1).getWord0().equals("to")&&ResponseCAUSE_init_list.contains(keywordList.get(Node2Pos-2).getWord0().toLowerCase()))
			   ) {
				result=1;
				this.relaionType="CLINK";
			}
		}
		
		return result;
	}
	
	public int isFROM_Causal(List<Sentence> _sentenceList){
		int result=0;
		String[] FROM_initiation = {"from", "followed","follow","follows","following"}; // this list will be updated
		List<String> FROM_init_list = Arrays.asList(FROM_initiation);
		
		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		List<KeyWord> keywordList=pSentence.getSentence();
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		String node1Word=this.Node1.getWord0().toLowerCase();
		String node2Word=this.Node2.getWord0().toLowerCase();

		int Node1Pos=-1;
		int Node2Pos=-1;
		int FROMPos=-1;
		for (int i = 0; i < keywordList.size(); i++) {
			KeyWord tempWord=keywordList.get(i);
			if (tempWord.equals(this.Node1)) {
				Node1Pos=i;
			}
			if (tempWord.equals(this.Node2)) {
				Node2Pos=i;
			}
			if (FROM_init_list.contains(tempWord.getWord0().toLowerCase())) {
				FROMPos=i;
			}
		}
		
		String tempStr="";
		if (FROMPos>-1) {
			tempStr=keywordList.get(FROMPos).getDpStatus16().replace("||", "-tab-");
		}
		String[] dpFROM=tempStr.split("-tab-"); // T2, T5, T6 (select Select keywords with IDs T5 and T6)
		String Tnode1=this.Node1.getId1();
		String Tnode2=this.Node2.getId1();

		for (int i = 0; i < dpFROM.length; i++) {
			String temp=dpFROM[i];
			if (temp.indexOf(Tnode1)!=-1) {
				result=1;
				this.relaionType="CLINK";
			}else if (temp.indexOf(Tnode2)!=-1) {
				result=1;
				this.relaionType="CLINK-C";
			}
		}	
		return result;
	}	
	
	public int isRESULT_Causal(List<Sentence> _sentenceList){
		int result=0;
		String[] RESULT_initiation = {"result", "results", "resulting"
									  }; // this list will be updated
		List<String> RESULT_init_list = Arrays.asList(RESULT_initiation);
		
		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		List<KeyWord> keywordList=pSentence.getSentence();
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		String node1Word=this.Node1.getWord0().toLowerCase();
		String node2Word=this.Node2.getWord0().toLowerCase();

		int Node1Pos=-1;
		int Node2Pos=-1;
		int CAUSEPos=-1;
		int condition=0;
		
		if (RESULT_init_list.contains(this.Node1.getWord0().toLowerCase())) {
			result=1;
			this.relaionType="CLINK-C";	
		}else if (RESULT_init_list.contains(this.Node2.getWord0().toLowerCase())) {
			result=1;
			this.relaionType="CLINK";	
		} 

		if (result==0) {
			for (int i = 0; i < keywordList.size()-1; i++) {
				KeyWord tempWord=keywordList.get(i);
				if (tempWord.equals(this.Node1)) {
					Node1Pos=i;
				}
				if (tempWord.equals(this.Node2)) {
					Node2Pos=i;
				}
				if (RESULT_init_list.contains(tempWord.getWord0().toLowerCase())
				    &&keywordList.get(i+1).getWord0().equals("of")
					) {
					CAUSEPos=i;
					condition=1;
				}
			}
			
			if ((Node1Pos>-1&&Node2Pos>-1&CAUSEPos>-1)
					&&(Node1Pos<CAUSEPos&&Node2Pos>CAUSEPos)	
					&&condition==1
					) {
					result=1;
					this.relaionType="CLINK-C";
			}else if ((Node1Pos>-1&&Node2Pos>-1&CAUSEPos>-1)
						&&(Node1Pos==CAUSEPos&&Node2Pos>CAUSEPos)	
						&&condition==1
						) {
						result=1;
						this.relaionType="CLINK-C";
			}else if ((Node1Pos>-1&&Node2Pos>-1&CAUSEPos>-1)
						&&(Node1Pos>CAUSEPos&&Node2Pos==CAUSEPos)	
						&&condition==1
						) {
						result=1;
						this.relaionType="CLINK";
			}
		}
		return result;
	}	

	public int isBEGIN_Causal(List<Sentence> _sentenceList){
		int result=0;
		String[] BEGIN_initiation = {"begin", "begun", "began", "beginning", "begins"
									 , "continues" 
									}; // this list will be updated
		List<String> BEGIN_init_list = Arrays.asList(BEGIN_initiation);
		
		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		List<KeyWord> keywordList=pSentence.getSentence();
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		String node1Word=this.Node1.getWord0().toLowerCase();
		String node2Word=this.Node2.getWord0().toLowerCase();

		int Node1Pos=-1;
		int Node2Pos=-1;
			
		if (BEGIN_init_list.contains(this.Node1.getWord0().toLowerCase())) {
			result=1;
			this.relaionType="CLINK";	
		}else if (BEGIN_init_list.contains(this.Node2.getWord0().toLowerCase())) {
			result=1;
			this.relaionType="CLINK-C";	
		} 

		return result;
	}	
	
	public int isRESPONSE_Causal(List<Sentence> _sentenceList){
		int result=0;
		String[] RESPONSE_initiation = {"response", "responsed", "responsing", "responses"
										
										}; // this list will be updated
		List<String> RESPONSE_init_list = Arrays.asList(RESPONSE_initiation);
		
		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		List<KeyWord> keywordList=pSentence.getSentence();
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		String node1Word=this.Node1.getWord0().toLowerCase();
		String node2Word=this.Node2.getWord0().toLowerCase();

		int Node1Pos=-1;
		int Node2Pos=-1;
		int RESPONSEPos=-1;
		for (int i = 0; i < keywordList.size(); i++) {
			KeyWord tempWord=keywordList.get(i);
			if (tempWord.equals(this.Node1)) {
				Node1Pos=i;
			}
			if (tempWord.equals(this.Node2)) {
				Node2Pos=i;
			}
			if (RESPONSE_init_list.contains(tempWord.getWord0().toLowerCase())) {
				RESPONSEPos=i;
			}
		}
		
		String tempStr="";
		if (RESPONSEPos>-1&&RESPONSEPos<keywordList.size()) {
			tempStr=keywordList.get(RESPONSEPos+1).getDpStatus16().replace("||", "-tab-");
		}
		String[] dpRESPONSE=tempStr.split("-tab-"); // T2, T5, T6 (select Select keywords with IDs T5 and T6)
		String Tnode1=this.Node1.getId1();
		String Tnode2=this.Node2.getId1();

		for (int i = 0; i < dpRESPONSE.length; i++) {
			String temp=dpRESPONSE[i];
			
			if (temp.indexOf(Tnode1)!=-1) {
				result=1;
				this.relaionType="CLINK";
			}else if (temp.indexOf(Tnode2)!=-1) {
				result=1;
				this.relaionType="CLINK-C";
			}else if (temp.indexOf(Tnode1)!=-1) {
				result=1;
				this.relaionType="CLINK";
			}else if (temp.indexOf(Tnode2)!=-1) {
				result=1;
				this.relaionType="CLINK-C";
			}
			
		}	
		return result;
	}	
	
	// khi evnet la cac action nay, no' se bi. cause tu*` nhung event khac
	
	public int isLAUNCH_Causal(List<Sentence> _sentenceList){
		int result=0;
		String[] LAUNCH_initiation = {"launch", "launched", "launching", "launches"}; // this list will be updated
		List<String> LAUNCH_init_list = Arrays.asList(LAUNCH_initiation);
		
		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		List<KeyWord> keywordList=pSentence.getSentence();
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		String node1Word=this.Node1.getWord0().toLowerCase();
		String node2Word=this.Node2.getWord0().toLowerCase();
		
		if (LAUNCH_init_list.contains(node1Word)) {
			result=1;
			this.relaionType="CLINK-C";
		}else if (LAUNCH_init_list.contains(node2Word)) {
			result=1;
			this.relaionType="CLINK";
		} 
		
		return result;
	}	

	public int isLAST_Causal(List<Sentence> _sentenceList){
		int result=0;
		String[] LAST_initiation = {"last", "previous", "previously", "lastly"}; // this list will be updated
		List<String> LAST_init_list = Arrays.asList(LAST_initiation);
		
		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		List<KeyWord> keywordList=pSentence.getSentence();
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		String node1Word=this.Node1.getWord0().toLowerCase();
		String node2Word=this.Node2.getWord0().toLowerCase();

		int Node1Pos=-1;
		int Node2Pos=-1;
		int LASTPos=-1;
		for (int i = 0; i < keywordList.size(); i++) {
			KeyWord tempWord=keywordList.get(i);
			if (tempWord.equals(this.Node1)) {
				Node1Pos=i;
			}
			if (tempWord.equals(this.Node2)) {
				Node2Pos=i;
			}
			if (LAST_init_list.contains(tempWord.getWord0().toLowerCase())) {
				LASTPos=i;
			}
		}
		
		String tempStr="";
		if (LASTPos>-1&&LASTPos<keywordList.size()) {
			tempStr=keywordList.get(LASTPos).getDpStatus16().replace("||", "-tab-");
		}
		String[] dpLAST=tempStr.split("-tab-"); // T2, T5, T6 (select Select keywords with IDs T5 and T6)
		String Tnode1=this.Node1.getId1();
		String Tnode2=this.Node2.getId1();

		for (int i = 0; i < dpLAST.length; i++) {
			String temp=dpLAST[i];
			
			if (temp.indexOf(Tnode1)!=-1) {
				result=1;
				this.relaionType="CLINK";
			}else if (temp.indexOf(Tnode2)!=-1) {
				result=1;
				this.relaionType="CLINK-C";
			}
		}	
		return result;
	}	
	
	public int isCAUSE_Ex_Causal(List<Sentence> _sentenceList){
		int result=0;
		String[] CAUSE_initiation = {"result", "resulted", "results", "resulting" 
				                    
									}; // this list will be updated
		List<String> CAUSE_init_list = Arrays.asList(CAUSE_initiation);
		
		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		List<KeyWord> keywordList=pSentence.getSentence();
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		String node1Word=this.Node1.getWord0().toLowerCase();
		String node2Word=this.Node2.getWord0().toLowerCase();

		int Node1Pos=-1;
		int Node2Pos=-1;
		int CAUSEPos=-1;
		for (int i = 0; i < keywordList.size(); i++) {
			KeyWord tempWord=keywordList.get(i);
			if (tempWord.equals(this.Node1)) {
				Node1Pos=i;
			}
			if (tempWord.equals(this.Node2)) {
				Node2Pos=i;
			}
			if (CAUSE_init_list.contains(tempWord.getWord0().toLowerCase())) {
				CAUSEPos=i;
			}
		}
		
		for (int i = 0; i < keywordList.size(); i++) {
			KeyWord tempWord=keywordList.get(i);
			if (CAUSE_init_list.contains(tempWord.getWord0().toLowerCase())) {
				CAUSEPos=i;
				if ((Node1Pos>-1&&Node2Pos>-1&CAUSEPos>-1)
						&&(Node1Pos<CAUSEPos&&Node2Pos>CAUSEPos)	
						) {
						result=1;
						this.relaionType="CLINK";
					}else if ((Node1Pos>-1&&Node2Pos>-1&CAUSEPos>-1)
							&&(Node1Pos>CAUSEPos&&Node2Pos==CAUSEPos)	
							) {
							result=1;
							this.relaionType="CLINK-C";
					}
			}
		}
		return result;
	}
	
	public int isSO_Causal(List<Sentence> _sentenceList){
		int result=0;
		String[] CAUSE_initiation = {"so"}; // this list will be updated
		List<String> CAUSE_init_list = Arrays.asList(CAUSE_initiation);
		
		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		List<KeyWord> keywordList=pSentence.getSentence();
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		String node1Word=this.Node1.getWord0().toLowerCase();
		String node2Word=this.Node2.getWord0().toLowerCase();

		int Node1Pos=-1;
		int Node2Pos=-1;
		int SOPos=-1;
		for (int i = 0; i < keywordList.size(); i++) {
			KeyWord tempWord=keywordList.get(i);
			if (tempWord.equals(this.Node1)) {
				Node1Pos=i;
			}
			if (tempWord.equals(this.Node2)) {
				Node2Pos=i;
			}
			if (CAUSE_init_list.contains(tempWord.getWord0().toLowerCase())) {
				SOPos=i;
				System.out.println("Node SO");
				System.out.println(SOPos);
				System.out.println(keywordList.get(SOPos).getId1());
				
			}
		}
		
		String DPNode1="";
		if (Node1Pos>-1) {
			DPNode1=keywordList.get(Node1Pos).getDpStatus16();
		}
		
		String DPNode2="";
		if (Node2Pos>-1) {
			DPNode2=keywordList.get(Node2Pos).getDpStatus16();
		}
		
		String SOID="";
		if (SOPos>-1) {
			SOID=keywordList.get(SOPos).getId1();
		}
		
		if (Node1Pos>-1&&Node2Pos>-1&SOPos>-1) {
			if (DPNode1.indexOf(SOID)!=-1) {
				result=1;
				this.relaionType="CLINK";
			}else if (DPNode2.indexOf(SOID)!=-1) {
				result=1;
				this.relaionType="CLINK-C";
			}
		}
		
		return result;
	}
	
	public int isLOSS_Causal(List<Sentence> _sentenceList){
		int result=0;
		String[] RESULT_initiation = {"loss", "lose", "loses", "approval", "approve", "approving",
				                      "permit", "permits", "permitted", "permitting"
				                      ,"due"}; // this list will be updated
		List<String> RESULT_init_list = Arrays.asList(RESULT_initiation);
		
		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		List<KeyWord> keywordList=pSentence.getSentence();
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		String node1Word=this.Node1.getWord0().toLowerCase();
		String node2Word=this.Node2.getWord0().toLowerCase();

		int Node1Pos=-1;
		int Node2Pos=-1;
			
		if (RESULT_init_list.contains(this.Node1.getWord0().toLowerCase())) {
			result=1;
			this.relaionType="CLINK";	
		}else if (RESULT_init_list.contains(this.Node2.getWord0().toLowerCase())) {
			result=1;
			this.relaionType="CLINK-C";	
		} 

		return result;
	}	

	public int isFOLLOW_Causal(List<Sentence> _sentenceList){
		int result=0;
		String[] CAUSE_initiation = {"follow", "following", "followed", "follows"}; // this list will be updated
		List<String> CAUSE_init_list = Arrays.asList(CAUSE_initiation);
		
		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		List<KeyWord> keywordList=pSentence.getSentence();
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		String node1Word=this.Node1.getWord0().toLowerCase();
		String node2Word=this.Node2.getWord0().toLowerCase();

		int Node1Pos=-1;
		int Node2Pos=-1;
		int SOPos=-1;
		for (int i = 0; i < keywordList.size(); i++) {
			KeyWord tempWord=keywordList.get(i);
			if (tempWord.equals(this.Node1)) {
				Node1Pos=i;
			}
			if (tempWord.equals(this.Node2)) {
				Node2Pos=i;
			}
			if (CAUSE_init_list.contains(tempWord.getWord0().toLowerCase())) {
				SOPos=i;
				System.out.println(SOPos);
				
			}
		}
		
		String IDNode1="";
		if (Node1Pos>-1) {
			IDNode1=keywordList.get(Node1Pos).getId1();
		}
		
		String IDNode2="";
		if (Node2Pos>-1) {
			IDNode2=keywordList.get(Node2Pos).getId1();
		}
		
		String DPFollow="";
		if (SOPos>-1) {
			DPFollow=keywordList.get(SOPos).getDpStatus16();
		}
		
		if (Node1Pos>-1&&Node2Pos>-1&SOPos>-1) {
			if (DPFollow.indexOf(IDNode1)!=-1) {
				result=1;
				this.relaionType="CLINK-C";
			}else if (DPFollow.indexOf(IDNode2)!=-1) {
				result=1;
				this.relaionType="CLINK";
			}
		}
		
		return result;
	}

	public int isFOR_Causal(List<Sentence> _sentenceList){
		int result=0;
		String[] FROM_initiation = {"for"}; // this list will be updated
		List<String> FROM_init_list = Arrays.asList(FROM_initiation);
		
		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		List<KeyWord> keywordList=pSentence.getSentence();
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		String node1Word=this.Node1.getWord0().toLowerCase();
		String node2Word=this.Node2.getWord0().toLowerCase();

		int Node1Pos=-1;
		int Node2Pos=-1;
		int FROMPos=-1;
		for (int i = 0; i < keywordList.size(); i++) {
			KeyWord tempWord=keywordList.get(i);
			if (tempWord.equals(this.Node1)) {
				Node1Pos=i;
			}
			if (tempWord.equals(this.Node2)) {
				Node2Pos=i;
			}
			if (FROM_init_list.contains(tempWord.getWord0().toLowerCase())) {
				FROMPos=i;
			}
		}
		
		String tempStr="";
		if (FROMPos>-1) {
			tempStr=keywordList.get(FROMPos).getDpStatus16().replace("||", "-tab-");
		}
		String[] dpFROM=tempStr.split("-tab-"); // T2, T5, T6 (select Select keywords with IDs T5 and T6)
		String Tnode1=this.Node1.getId1();
		String Tnode2=this.Node2.getId1();

		for (int i = 0; i < dpFROM.length; i++) {
			String temp=dpFROM[i];
			if (temp.indexOf(Tnode1)!=-1) {
				result=1;
				this.relaionType="CLINK";
			}else if (temp.indexOf(Tnode2)!=-1) {
				result=1;
				this.relaionType="CLINK-C";
			}
		}	
		return result;
	}	

	/*
	public int isSUCCESS_Causal(List<Sentence> _sentenceList){
		int result=0;
		String[] RESULT_initiation = {"success", "success", "resulting"
									  }; // this list will be updated
		List<String> RESULT_init_list = Arrays.asList(RESULT_initiation);
		
		int senPostion=Integer.parseInt(this.Node1.getSentencePos2());
		Sentence pSentence=_sentenceList.get(senPostion);
		List<KeyWord> keywordList=pSentence.getSentence();
		
		String sub=this.propositionNode1.subject().toLowerCase();
		String rel=this.propositionNode1.relation().toLowerCase();
		String arg="";
		if (this.propositionNode1.noArguments()>0) {
			arg=this.propositionNode1.argument(0).toLowerCase();
		}
		String node1Word=this.Node1.getWord0().toLowerCase();
		String node2Word=this.Node2.getWord0().toLowerCase();

		int Node1Pos=-1;
		int Node2Pos=-1;
		int CAUSEPos=-1;
		int condition=0;
		
		if (RESULT_init_list.contains(this.Node1.getWord0().toLowerCase())) {
			result=1;
			this.relaionType="CLINK";	
		}else if (RESULT_init_list.contains(this.Node2.getWord0().toLowerCase())) {
			result=1;
			this.relaionType="CLINK-C";	
		} 

		if (result==0) {
			for (int i = 0; i < keywordList.size()-1; i++) {
				KeyWord tempWord=keywordList.get(i);
				if (tempWord.equals(this.Node1)) {
					Node1Pos=i;
				}
				if (tempWord.equals(this.Node2)) {
					Node2Pos=i;
				}
				if (RESULT_init_list.contains(tempWord.getWord0().toLowerCase())
				    &&keywordList.get(i+1).getWord0().equals("of")
					) {
					CAUSEPos=i;
					condition=1;
				}
			}
			
		}
		return result;
	}	
	*/
	public static void main(String args[]){
		System.out.println("Testing here");
	}
}

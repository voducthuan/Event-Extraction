package com.ls3.oie.temporalmodel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import com.ls3.oie.clause.ClausIE;
import com.ls3.oie.clause.Clause;
import com.ls3.oie.clause.Proposition;
import com.ls3.oie.tools.WildCardFileFilter;

public class oieSentence {

	public Sentence sentence;
	public ClausIE clausePattern;
	public List<extractedProposition> extractedPropositionList;
	
	public oieSentence(){
		sentence= new Sentence();
		clausePattern=new ClausIE();
		extractedPropositionList=new ArrayList<>();
	}
	
	public Sentence getSentence() {
		return sentence;
	}

	public void setSentence(Sentence _sentence) {
		this.sentence = _sentence;
	}
	
	public ClausIE getClausePattern() {
		return clausePattern;
	}

	public void setClausePattern(ClausIE _clausePattern) {
		this.clausePattern = _clausePattern;
	}
	
	public List<extractedProposition> getExtractedProposition() {
		return extractedPropositionList;
	}

	public void setExtractedProposition(List<extractedProposition> _extractedPropositionList) {
		this.extractedPropositionList = _extractedPropositionList;
	}
	
	public void loadclausePattern(){
		String pSentence=sentence.sentenceContent();
		clausePattern.initParser();
		clausePattern.parse(pSentence);
		clausePattern.detectClauses();
		clausePattern.generatePropositions();
	}
	
	public void loadExtractedProposition(){
		
		List<extractedProposition> extrPropList=new ArrayList<>();
		
		if (sentence.isRelation()>0) {
			List<Proposition> listPro=new ArrayList<>(); 
			listPro=clausePattern.getPropositions();
			List<KeyWord> keyWordList=sentence.getSentence();

			for (int i = 0; i < listPro.size(); i++) {  // load tung proposition, check keyword
				
				int condition=0;
				extractedProposition tempExtProp=new extractedProposition();
				List<KeyWord> extractedKeyWord=new ArrayList<>();
				
				Proposition prop=listPro.get(i);
				String sub=prop.subject();
				String rel=prop.relation();
				String argu="";
				if (prop.noArguments()>0) {
	            	argu=prop.argument(0);
				}
				//System.out.println(sub+" "+rel+" "+argu);
				
				// Kiem tra va so sach tung keyword voi proposition
				for (int j = 0; j < keyWordList.size(); j++) {
					KeyWord pKeyWord=keyWordList.get(j);
					String event="";
					String time="";
					if (pKeyWord.isEvent()!=0||pKeyWord.isTime()!=0) {
						//Load keyword
						String content=pKeyWord.getWord0();
						// Kiem tra event time trong proposition
						if (sub.indexOf(content)>-1||rel.indexOf(content)>-1||argu.indexOf(content)>-1) {
							condition=1;
							extractedKeyWord.add(pKeyWord);
						
						}
					}
				
				}
				
				if (condition==1) {
					tempExtProp.setProposition(prop);
					tempExtProp.setArgumentList(extractedKeyWord);
					extractedPropositionList.add(tempExtProp);
				}
								
			}
			
		}
	}
	
	// Load all extracted proposition from list of oie sentences
	public List<extractedProposition> loadAllExtPropositionByOIESentences(List<oieSentence> _oieSentencesList){
		List<extractedProposition> result =new ArrayList<>();
		for (int i = 0; i < _oieSentencesList.size(); i++) {
			oieSentence oieSen=_oieSentencesList.get(i);
			List<extractedProposition> exPropList=oieSen.getExtractedProposition();
			for (int j = 0; j < exPropList.size(); j++) {
				extractedProposition tempExProp=exPropList.get(j);
				if (tempExProp.getArgumentList().size()>0) {
					result.add(tempExProp);
				}
			}
		}	
		return result;
	}
	
	// Load all KeyWord events from extracted proposition from list of oie sentences
	public List<KeyWord> loadAllKeyWordEventByOIESentences(List<oieSentence> _oieSentencesList){
	
		List<KeyWord> result=new ArrayList<>();
		
		for (int i = 0; i < _oieSentencesList.size(); i++) {
			oieSentence oieSen=_oieSentencesList.get(i);
			List<extractedProposition> exPropList=oieSen.getExtractedProposition();
			for (int j = 0; j < exPropList.size(); j++) {
				extractedProposition tempExProp=exPropList.get(j);
				List<KeyWord> pWordList=tempExProp.getArgumentList();
						
				if (pWordList.size()>0) {
					for (int k = 0; k < pWordList.size(); k++) {
						KeyWord pWord=pWordList.get(k);
						result.add(pWord);
					}
				}
			}
		}
		return result;
	}

	// Load all KeyWord with Proposition, update on 31 May 2018
	
	public List<KeyWordProposition> loadAllKeyWordPropositionByOIESentences(List<oieSentence> _oieSentencesList){
	
		List<KeyWordProposition> result=new ArrayList<>();
		
		for (int i = 0; i < _oieSentencesList.size(); i++) {
			oieSentence oieSen=_oieSentencesList.get(i);
			List<extractedProposition> exPropList=oieSen.getExtractedProposition();
			for (int j = 0; j < exPropList.size(); j++) {
				extractedProposition tempExProp=exPropList.get(j);
				List<KeyWord> pWordList=tempExProp.getArgumentList();
						
				if (pWordList.size()>0) {
					for (int k = 0; k < pWordList.size(); k++) {
						KeyWord pWord=pWordList.get(k);
						Proposition pPro=tempExProp.getProposition();
						// Keyword proposition
						KeyWordProposition keywordPro=new KeyWordProposition(pWord, pPro);
						result.add(keywordPro);
					}
				}
			}
		}
		return result;
	}
	
	
	public void testing(){
		ClausIE clausIE = new ClausIE();
        clausIE.initParser();
        // input sentence
        String sentence ="The White House said President Bush has approved duty-free treatment for imports of certain types of watches that aren't produced in significant quantities in the U.S., the Virgin Islands and other U.S. possessions.";
        System.out.println("Input sentence   : " + sentence);
        // parse tree
        clausIE.parse(sentence);
        System.out.print("Dependency parse : ");
        System.out.println(clausIE.getDepTree().pennString()
                .replaceAll("\n", "\n                   ").trim());
        System.out.print("Semantic graph   : ");
        System.out.println(clausIE.getSemanticGraph().toFormattedString()
                .replaceAll("\n", "\n                   ").trim());
        clausIE.detectClauses();
        clausIE.generatePropositions();
        System.out.print("Clauses          : ");
        String sep = "";
        for (Clause clause : clausIE.getClauses()) {
            System.out.println(sep + clause.toString(clausIE.getOptions()));
            sep = "                   ";
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
        // generate propositions
        System.out.print("Propositions     : ");
        sep = "";
        for (Proposition prop : clausIE.getPropositions()) {
        	System.out.println(sep + prop.toString());
        	//testing
        	System.out.println(sep + prop.subject());
        	System.out.println(sep + prop.relation());
        	if (prop.noArguments()>0) {
        		System.out.println(sep + prop.noArguments());
            	System.out.println(sep + prop.argument(0));
			}
         	//System.out.println(sep + prop.argument(1));
        	// end testing
        	sep = "                   ";
        }

	}
	
	
	public static void outputFilesTemporal() throws IOException{
		
		String path="data//tdata//oie5//";
		String filetype="col";
		File dir = new File(path+"//raw//testing/");
		File[] filesLL = dir.listFiles(new WildCardFileFilter("*."+filetype));
		for (int k = 0; k < filesLL.length; k++) {
			File f = filesLL[k];
			String inputCOL=f.getName();
			System.out.println("Loading..."+f.getName());
			List<Sentence> sentenceList = new ArrayList<Sentence>();
			DataWriter 		Writer      = new DataWriter();
			sentenceList = Writer.writeSentenceList(path+"//raw//testing//"+inputCOL); 
			//Writer.printSentenceList(sentenceList);
			FileOutputStream fo = new FileOutputStream(path+"//original//testing//"+inputCOL);
			OutputStreamWriter out = new OutputStreamWriter(fo, "utf-8");
			for (int i = 0; i < sentenceList.size(); i++) {
				if (i==0) {
					System.out.println(sentenceList.get(i).sentenceContent());
					out.write("It is "+sentenceList.get(i).sentenceContent()+".");
					out.write("\n");
				}else{
					System.out.println(sentenceList.get(i).sentenceContent());
					out.write(sentenceList.get(i).sentenceContent());
					out.write("\n");
				}
			}
			out.close();
		}
	}
	
	
	public static void outputFilesCausal() throws IOException{
		
		String path="data//cdata//oie5//";
		String filetype="col";
		File dir = new File(path+"//raw//");
		File[] filesLL = dir.listFiles(new WildCardFileFilter("*."+filetype));
		for (int k = 0; k < filesLL.length; k++) {
			File f = filesLL[k];
			String inputCOL=f.getName();
			System.out.println("Loading..."+f.getName());
			List<Sentence> sentenceList = new ArrayList<Sentence>();
			DataWriter 		Writer      = new DataWriter();
			sentenceList = Writer.writeSentenceList(path+"//raw//"+inputCOL); 
			//Writer.printSentenceList(sentenceList);

			
			FileOutputStream fo = new FileOutputStream(path+"//original//"+inputCOL);
			OutputStreamWriter out = new OutputStreamWriter(fo, "utf-8");
			for (int i = 0; i < sentenceList.size(); i++) {
				if (i==0) {
					System.out.println(sentenceList.get(i).sentenceContent());
					out.write("It is "+sentenceList.get(i).sentenceContent()+".");
					out.write("\n");
				}else{
					System.out.println(sentenceList.get(i).sentenceContent());
					out.write(sentenceList.get(i).sentenceContent());
					out.write("\n");
				}
			}
			out.close();
		}
	}

	
	
	
	public static void main(String[] args) throws IOException {
		//outputFilesTemporal();
		outputFilesCausal();
		//outputFiles();
		//WSJ_20130318_731.col
		/*
		String inputFileName = "data/tdata/wsj_0026.col";
		// read data from file and write into List by sentence. 
		// Every line is a sentence and every word of sentence is a KeyWord
		List<Sentence> sentenceList = new ArrayList<Sentence>();
		DataWriter 		Writer      = new DataWriter();
		sentenceList = Writer.writeSentenceList(inputFileName); 

		Writer.printSentenceList(sentenceList);
		
		List<oieSentence> oieSentenceList=new ArrayList<>();

		for (int i = 0; i < sentenceList.size(); i++) {
			Sentence tempSentence=sentenceList.get(i);
			System.out.println(tempSentence.sentenceContent());
			oieSentence tempOieSentence=new oieSentence();
			tempOieSentence.setSentence(tempSentence);
			tempOieSentence.loadclausePattern();
			tempOieSentence.loadExtractedProposition();
			oieSentenceList.add(tempOieSentence);
		}
		
		
		for (int i = 0; i < oieSentenceList.size(); i++) {
			oieSentence temp=oieSentenceList.get(i);
			System.out.println(temp.getSentence().sentenceContent());
			ClausIE clause=temp.getClausePattern();
			
			for (int j = 0; j < clause.getPropositions().size(); j++) {
				System.out.println(j+". "+clause.getPropositions().get(j).toString());
			}
		
			System.out.println();
			System.out.println("---------Extracted propositions----------");
			List<extractedProposition> exPropList=temp.getExtractedProposition();
			for (int j = 0; j < exPropList.size(); j++) {
				extractedProposition tempExProp=exPropList.get(j);
				//if (tempExProp.getArgumentList().size()>1) {
					System.out.println(tempExProp.toString());
				//}
			}	
			    /*
				System.out.println(exPropList.get(j).proposition.toString());
				for (int j2 = 0; j2 < exPropList.get(j).argumentList.size(); j2++) {
					KeyWord pword=exPropList.get(j).argumentList.get(j2);
					if (pword.isEvent()==1) {
						System.out.println(pword.getWord0()+":"+pword.getEventName4());
					}
					if (pword.isTime()==1) {
						System.out.println(pword.getWord0()+":"+pword.getTimeName7());
					}
				}
				*/
		//	System.out.println("-------------------------------------------");
		//	System.out.println();
		//}
		
	}	
}

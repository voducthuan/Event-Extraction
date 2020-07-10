package com.ls3.oie.temporalmodel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ls3.oie.tools.txtNormalization;
import com.ls3.oie5.patternOIE5;

public class oie5Sentence {

	public Sentence sentence;
	public List<patternOIE5> patternList;
	public List<extractedPropositionOIE5> extractedPropositionOIE5List;
	
	//public ClausIE clausePattern;
	//public List<extractedProposition> extractedPropositionList;
	
	public oie5Sentence(){
		sentence= new Sentence();
		patternList=new ArrayList<>();
		extractedPropositionOIE5List=new ArrayList<>();
		//clausePattern=new ClausIE();
		//extractedPropositionList=new ArrayList<>();
	}
	
	public Sentence getSentence() {
		return sentence;
	}

	public void setSentence(Sentence _sentence) {
		this.sentence = _sentence;
	}
	
	public List<patternOIE5> getPatternList() {
		return patternList;
	}

	public void setPatternList(List<patternOIE5> pPatternList) {
		this.patternList = pPatternList;
	}

	public List<extractedPropositionOIE5> getExtractedPropositionOIE5List() {
		return extractedPropositionOIE5List;
	}

	public void setExtractedPropositionOIE5List(List<extractedPropositionOIE5> pExtractedPropositionOIE5List) {
		this.extractedPropositionOIE5List = pExtractedPropositionOIE5List;
	}

	/*
	public void loadclausePattern(){
		String pSentence=sentence.sentenceContent();
		clausePattern.initParser();
		clausePattern.parse(pSentence);
		clausePattern.detectClauses();
		clausePattern.generatePropositions();
	}
	*/
	public void loadTemporalPattern(String fileName, int senID) throws IOException{
		// Pattern from OIE 5
		//String inputFileName = "data/tdata/oie5/pattern/"+fileName; // phan nay co the thay doi, neu chi co' 1 file
		//String inputFileName = "data//tdata///oie5///pattern//training_pattern.txt"; // phan nay co the thay doi, neu chi co' 1 file
		String inputFileName = "data//tdata///oie5///pattern//testing_pattern.txt"; 
		List<String> listpattern=txtNormalization.readFile(inputFileName);
		
		for (int i = 0; i < listpattern.size(); i++) {
			System.out.println(listpattern.get(i));
			String[] pPattern=listpattern.get(i).split("\t");
			String pFile=pPattern[0];
			String pid=pPattern[1];
			String pContent=pPattern[2];
			if (pFile.equals(fileName) && pid.equals(Integer.toString(senID))) {
				String[] content=pContent.split(";");
				if (content.length>1) {
					
					String sub=content[0];
					String rel=content[1];
					String ojb="";
					if (content.length>2) {
						for (int j = 2; j < content.length; j++) {
							ojb=ojb+content[j]+" ";
						}
					}
					patternOIE5 pattern_oie=new patternOIE5(sub, rel, ojb.trim());
					this.patternList.add(pattern_oie);
				}
			}
		}
	}

	public void loadCausalPatternOIE5(String fileName, int senID) throws IOException{
		// Pattern from OIE 5
		//String inputFileName = "data//cdata//oie5//pattern//oie5pattern"; // phan nay co the thay doi, neu chi co' 1 file
		String inputFileName = "data//cdata//oie5//pattern//oie5_pattern_causal";
		List<String> listpattern=txtNormalization.readFile(inputFileName);
		
		for (int i = 0; i < listpattern.size(); i++) {
			String[] pPattern=listpattern.get(i).split("\t");
			String pFile=pPattern[0];
			String pid=pPattern[1];
			String pContent=pPattern[2];
			if (pFile.equals(fileName) && pid.equals(Integer.toString(senID))) {
				String[] content=pContent.split(";");
				if (content.length>1) {
					
					String sub=content[0];
					String rel=content[1];
					String ojb="";
					if (content.length>2) {
						for (int j = 2; j < content.length; j++) {
							ojb=ojb+content[j]+" ";
						}
					}
					patternOIE5 pattern_oie=new patternOIE5(sub, rel, ojb.trim());
					this.patternList.add(pattern_oie);
				}
			}
		}
	}

	
	public void loadPattern_test(){
		// Pattern from OIE 5 =>36
		patternOIE5 p1=new patternOIE5("The White House", "said", "President Bush has approved duty-free treatment for imports of certain types of watches");
		this.patternList.add(p1);
		patternOIE5 p2=new patternOIE5("President Bush", "has approved", "duty-free treatment for imports of certain types of watches");
		this.patternList.add(p2);
		patternOIE5 p3=new patternOIE5("President Bush", "has approved", "duty-free treatment");
		this.patternList.add(p3);
		patternOIE5 p4=new patternOIE5("watches", "are n't produced", "in significant quantities in the U.S. the Virgin Islands and other U.S. possessions");
		this.patternList.add(p4);
		patternOIE5 p5=new patternOIE5("watches", "are n't produced", "in significant quantities");
		this.patternList.add(p5);
		patternOIE5 p6=new patternOIE5("The action", "came", "in response to a petition filed by Timex Inc. for changes in the U.S. Generalized System of Preferences for imports from developing nations");
		this.patternList.add(p6);
		patternOIE5 p7=new patternOIE5("The action", "came", "in response");
		this.patternList.add(p7);
		patternOIE5 p8=new patternOIE5("a petition", "be filed", "by Timex Inc. for changes in the U.S. Generalized System of Preferences for imports from developing nations");
		this.patternList.add(p8);
		patternOIE5 p9=new patternOIE5("a petition", "be filed", "by Timex Inc. for changes in the U.S. Generalized System of Preferences");
		this.patternList.add(p9);
		patternOIE5 p10=new patternOIE5("watch imports", "were denied", "such duty-free treatment Previously");
		this.patternList.add(p10);
		patternOIE5 p11=new patternOIE5("watch imports", "were denied", "such duty-free treatment");
		this.patternList.add(p11);
		patternOIE5 p12=new patternOIE5("Timex", "had requested", "duty-free treatment for many types of watches covered by 58 different U.S. tariff classifications");
		this.patternList.add(p12);
		patternOIE5 p13=new patternOIE5("Timex", "had requested", "duty-free treatment");
		this.patternList.add(p13);
		patternOIE5 p14=new patternOIE5("watches", "be covered", "by 58 different U.S. tariff classifications");
		this.patternList.add(p14);
		patternOIE5 p15=new patternOIE5("The White House", "said", "Mr. Bush decided to grant duty-free status for 18 categories but turned down such treatment for other types of watches because of the potential for material injury to watch producers located in the U.S. and the Virgin Islands");
		this.patternList.add(p15);
		patternOIE5 p16=new patternOIE5("Mr. Bush", "decided", "to grant duty-free status for 18 categories");
		this.patternList.add(p16);
		patternOIE5 p17=new patternOIE5("Mr. Bush", "decided", "to grant duty-free status");
		this.patternList.add(p17);
		patternOIE5 p18=new patternOIE5("Mr. Bush", "turned down", "such treatment for other types of watches because of the potential for material injury to watch producers located in the U.S. and the Virgin Islands");
		this.patternList.add(p18);
		patternOIE5 p19=new patternOIE5("Mr. Bush", "turned down", "such treatment for other types of watches to watch producers located in the U.S. and the Virgin Islands");
		this.patternList.add(p19);
		patternOIE5 p20=new patternOIE5("producers", "be located", "in the U.S. and the Virgin Islands");
		this.patternList.add(p20);
		patternOIE5 p21=new patternOIE5("Timex", "is", "a major U.S. producer and seller of watches");
		this.patternList.add(p21);
		patternOIE5 p22=new patternOIE5("Timex", "is", "including low-priced battery-operated watches assembled in the Philippines and other developing nations covered by the U.S. tariff preferences");
		this.patternList.add(p22);
		patternOIE5 p23=new patternOIE5("Timex", "is", "including");
		this.patternList.add(p23);
		patternOIE5 p24=new patternOIE5("low-priced battery-operated watches and other developing nations covered by the U.S. tariff preferences", "be assembled", "in the Philippines");
		this.patternList.add(p24);
		patternOIE5 p25=new patternOIE5("other developing nations", "be covered", "by the U.S. tariff preferences");
		this.patternList.add(p25);
		patternOIE5 p26=new patternOIE5("U.S. trade officials", "said", "the Philippines and Thailand would be the main beneficiaries of the president 's action");
		this.patternList.add(p26);
		patternOIE5 p27=new patternOIE5("the Philippines and Thailand", "would be", "the main beneficiaries of the president 's action");
		this.patternList.add(p27);
		patternOIE5 p28=new patternOIE5("the Philippines and Thailand", "would be", "the main beneficiaries");
		this.patternList.add(p28);
		patternOIE5 p29=new patternOIE5("the president", "has", "action");
		this.patternList.add(p29);
		patternOIE5 p30=new patternOIE5("watches", "will be", "eligible now");
		this.patternList.add(p30);
		patternOIE5 p31=new patternOIE5("watches", "will be", "eligible for duty-free treatment");
		this.patternList.add(p31);
		patternOIE5 p32=new patternOIE5("watches", "will be", "eligible");
		this.patternList.add(p32);
		patternOIE5 p33=new patternOIE5("Imports of the types of watches", "totaled", "about $ 37.3 million in 1988");
		this.patternList.add(p33);
		patternOIE5 p34=new patternOIE5("Imports of the types of watches", "totaled", "about $ 37.3 million that year");
		this.patternList.add(p34);
		patternOIE5 p35=new patternOIE5("Imports of the types of watches", "totaled", "about $ 37.3 million according to an aide to U.S. Trade Representative Carla Hills");
		this.patternList.add(p35);
		patternOIE5 p36=new patternOIE5("Imports of the types of watches", "totaled", "about $ 37.3 million");
		this.patternList.add(p36);
		patternOIE5 p37=new patternOIE5("1988", "is", "a relatively small share of the $ 1.5 billion in U.S. watch imports");
		this.patternList.add(p37);
	}

	public void loadExtractedPropositionOIE5(){
		List<extractedPropositionOIE5> extrPropList=new ArrayList<>();
		if (sentence.isRelation()>0) {
			List<patternOIE5> listPatternOIE5=new ArrayList<>(); 
			listPatternOIE5=this.patternList;
			List<KeyWord> keyWordList=sentence.getSentence();
			for (int i = 0; i < listPatternOIE5.size(); i++) {  // load tung proposition, check keyword
				int condition=0;
				extractedPropositionOIE5 tempExtProp=new extractedPropositionOIE5();
				List<KeyWord> extractedKeyWord=new ArrayList<>();
				patternOIE5 pattern_temp=listPatternOIE5.get(i);
				String sub=pattern_temp.getSub();
				String rel=pattern_temp.getRel();
				String obj=pattern_temp.getObj();
				// Kiem tra va so sach tung keyword voi proposition
				for (int j = 0; j < keyWordList.size(); j++) {
					KeyWord pKeyWord=keyWordList.get(j);
					String event="";
					String time="";
					if (pKeyWord.isEvent()!=0||pKeyWord.isTime()!=0) {
						//Load keyword
						String content=pKeyWord.getWord0();
						// Kiem tra event time trong proposition
						if (sub.indexOf(content)>-1||rel.indexOf(content)>-1||obj.indexOf(content)>-1) {
							condition=1;
							extractedKeyWord.add(pKeyWord);
						}
					}
				}
				if (condition==1) {
					tempExtProp.setPattern(pattern_temp);
					tempExtProp.setArgumentList(extractedKeyWord);
					extractedPropositionOIE5List.add(tempExtProp);
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
	public List<KeyWord> loadAllKeyWordEventByOIE5Sentences(List<oie5Sentence> _oie5SentencesList){
	
		List<KeyWord> result=new ArrayList<>();
		
		for (int i = 0; i < _oie5SentencesList.size(); i++) {
			oie5Sentence oieSen=_oie5SentencesList.get(i);
			List<extractedPropositionOIE5> exPropList=oieSen.getExtractedPropositionOIE5List();
			for (int j = 0; j < exPropList.size(); j++) {
				extractedPropositionOIE5 tempExProp=exPropList.get(j);
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
	
	public List<KeyWordPropositionOIE5> loadAllKeyWordPropositionByOIESentences(List<oie5Sentence> _oie5SentencesList){
	
		List<KeyWordPropositionOIE5> result=new ArrayList<>();
		
		for (int i = 0; i < _oie5SentencesList.size(); i++) {
			oie5Sentence oieSen=_oie5SentencesList.get(i);
			List<extractedPropositionOIE5> exPropList=oieSen.getExtractedPropositionOIE5List();
			for (int j = 0; j < exPropList.size(); j++) {
				extractedPropositionOIE5 tempExProp=exPropList.get(j);
				List<KeyWord> pWordList=tempExProp.getArgumentList();
						
				if (pWordList.size()>0) {
					for (int k = 0; k < pWordList.size(); k++) {
						KeyWord pWord=pWordList.get(k);
						//Proposition pPro=tempExProp.getProposition();
						patternOIE5 pPro=tempExProp.getPattern();
						// Keyword proposition
						KeyWordPropositionOIE5 keywordPro=new KeyWordPropositionOIE5(pWord, pPro);
						result.add(keywordPro);
					}
				}
			}
		}
		return result;
	}
	
	/*
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
	*/
	
	public static void main(String[] args) throws IOException {
		//WSJ_20130318_731.col
		String path="data/tdata/";
		String FileName = "wsj_0026.col";
		// read data from file and write into List by sentence. 
		// Every line is a sentence and every word of sentence is a KeyWord
		List<Sentence> sentenceList = new ArrayList<Sentence>();
		DataWriter 		Writer      = new DataWriter();
		sentenceList = Writer.writeSentenceList(path+FileName); 
		Writer.printSentenceList(sentenceList);
		List<oie5Sentence> oie5SentenceList=new ArrayList<>();
		for (int i = 0; i < sentenceList.size(); i++) {
			Sentence tempSentence=sentenceList.get(i);
			System.out.println(tempSentence.sentenceContent());
			oie5Sentence tempOIE5Sen=new oie5Sentence();
			tempOIE5Sen.setSentence(tempSentence);
			//tempOIE5Sen.loadPattern_test();
			tempOIE5Sen.loadTemporalPattern(FileName, i);
			tempOIE5Sen.loadExtractedPropositionOIE5();
			oie5SentenceList.add(tempOIE5Sen);
		}

		for (int i = 0; i < oie5SentenceList.size(); i++) {
			oie5Sentence temp=oie5SentenceList.get(i);
			System.out.println(temp.getSentence().sentenceContent());
			
			System.out.println("---------Extracted propositions----------");
			List<extractedPropositionOIE5> exPropList=temp.getExtractedPropositionOIE5List();
			for (int j = 0; j < exPropList.size(); j++) {
				extractedPropositionOIE5 tempExProp=exPropList.get(j);
					System.out.println(tempExProp.toString());
			}	
			System.out.println("-------------------------------------------");
			System.out.println();
		}

				
		/*
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
					System.out.println(tempExProp.toString());
			}	
			System.out.println("-------------------------------------------");
			System.out.println();
		}
		*/
	}	
}

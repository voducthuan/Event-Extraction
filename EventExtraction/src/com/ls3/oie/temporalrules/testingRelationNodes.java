package com.ls3.oie.temporalrules;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Path;
import org.graphstream.graph.implementations.SingleGraph;

import com.ls3.oie.clause.ClausIE;
import com.ls3.oie.graph.graphUtil;
import com.ls3.oie.temporalmodel.DataWriter;
import com.ls3.oie.temporalmodel.KeyWord;
import com.ls3.oie.temporalmodel.KeyWordProposition;
import com.ls3.oie.temporalmodel.Sentence;
import com.ls3.oie.temporalmodel.extractedProposition;
import com.ls3.oie.temporalmodel.extractedRelationNodes;
import com.ls3.oie.temporalmodel.oieSentence;
import com.ls3.oie.temporalmodel.relationNodes;

public class testingRelationNodes {
	
	public static void predictIndirectRelNodes(List<relationNodes> relPath, List<relationNodes> originalList, List<String> tenseRankingList, List<String> timeRankingList){
		
		String result="None";
		// Vi du A-C (trong path A-B-C)
		// Tinh toan AB va BC
		int pathSize=relPath.size();
		relationNodes relPath1=relPath.get(0);
		relationNodes relPath2=relPath.get(pathSize-1);
		// Can kiem tra AB(BA) va` BC(CB) da kiem tra phan nay
		// Check tense and time ranking-> phan nay chua xu ly
		
		String tensePath1=relPath1.getTenseNode1().toLowerCase();
		String tensePath2=relPath2.getTenseNode2().toLowerCase();
		
		int tensePath1Score=0;
		int tensePath2Score=0;
		
		String timePath1=relPath1.getTimeNode1().toLowerCase();
		String timePath2=relPath2.getTimeNode2().toLowerCase();
		
		int timePath1Score=0;
		int timePath2Score=0;
		
		for (int i = 0; i < tenseRankingList.size(); i++) {
			
			String[] tempTenseArr=tenseRankingList.get(i).split("-");
			String tempTense=tempTenseArr[0].toLowerCase();
			int score=Integer.parseInt(tempTenseArr[1]);
			
			if (tempTense.contains(tensePath1)) {
				tensePath1Score=score;
			}
	
			if (tempTense.contains(tensePath2)) {
				tensePath2Score=score;
			}
		}
		
		for (int i = 0; i < timeRankingList.size(); i++) {
			String tempTime=timeRankingList.get(i);
			if (tempTime.contains(timePath1)) {
				timePath1Score=i;
			}
			if (tempTime.contains(timePath2)) {
				timePath2Score=i;
			}
		}
		
		// for checking TENSE
		if (tensePath1Score>tensePath2Score) { //order past->present->future
			result="AFTER";
		}else{
			if(tensePath1Score!=tensePath2Score){
			result="BEFORE";
			} 
		}

		// for checking TIME
		if (result.equals("None")&&timePath1Score>timePath2Score) { //order past->current
			result="AFTER";
		}else{
			if(timePath1Score!=timePath2Score){
			result="BEFORE";
			} 
		}
		
		// when no evidence on Tense and Time
		// For IDENTITY
		if (result.equals("None")&&relPath.size()==2) {
			if (relPath1.getRelaionType().equals("IDENTITY")&&relPath2.getRelaionType().equals("NO_IDENTITY")
				||relPath2.getRelaionType().equals("IDENTITY")&&relPath1.getRelaionType().equals("NO_IDENTITY")	
					) {
				
				result="IDENTITY";
			}
		}

		// For IS_INCLUDED
		if (result.equals("None")&&relPath.size()==2) {
			if (relPath1.getRelaionType().equals("IS_INCLUDED")&&relPath2.getRelaionType().equals("NO_IDENTITY")
				||relPath2.getRelaionType().equals("IS_INCLUDED")&&relPath1.getRelaionType().equals("NO_IDENTITY")	
					) {
				result="IS_INCLUDED";
			}
		}
		
		// For INCLUDES
		if (result.equals("None")&&relPath.size()==2) {
			if (relPath1.getRelaionType().equals("INCLUDES")&&relPath2.getRelaionType().equals("NO_IDENTITY")
				||relPath2.getRelaionType().equals("INCLUDES")&&relPath1.getRelaionType().equals("NO_IDENTITY")	
					) {
				result="INCLUDES";
			}
		}
		
		// For BEGINS
		if (result.equals("None")&&relPath.size()==2) {
			if (relPath1.getRelaionType().equals("BEGINS")&&relPath2.getRelaionType().equals("NO_IDENTITY")
				||relPath2.getRelaionType().equals("BEGINS")&&relPath1.getRelaionType().equals("NO_IDENTITY")	
					) {
				result="BEGINS";
			}
		}
		// For ENDS
		if (result.equals("None")&&relPath.size()==2) {
			if (relPath1.getRelaionType().equals("ENDS")&&relPath2.getRelaionType().equals("NO_IDENTITY")
				||relPath2.getRelaionType().equals("ENDS")&&relPath1.getRelaionType().equals("NO_IDENTITY")	
					) {
				result="ENDS";
			}
		}
		
		// For SIMULTANEOUS
		if (result.equals("None")&&relPath.size()==2) {
			if (relPath1.getRelaionType().equals("SIMULTANEOUS")&&relPath2.getRelaionType().equals("NO_IDENTITY")
				||relPath2.getRelaionType().equals("SIMULTANEOUS")&&relPath1.getRelaionType().equals("NO_IDENTITY")	
					) {
				result="SIMULTANEOUS";
			}
		}
		
		// For SIMULTANEOUS
		if (result.equals("None")&&relPath.size()==2) {
			if (relPath1.getRelaionType().equals("DURING")&&relPath2.getRelaionType().equals("NO_IDENTITY")
				||relPath2.getRelaionType().equals("DURING")&&relPath1.getRelaionType().equals("NO_IDENTITY")	
					) {
				result="DURING";
			}
		}
				
				
		System.out.println(result);
	}
	
	public static void process1() throws IOException{
    	// Tense and Time samples
		List<String> tenseRankingList=new ArrayList<>();
		tenseRankingList.add("PAST-0");
		tenseRankingList.add("PAST_PERFECT-0");
		tenseRankingList.add("PRESENT-0");
		tenseRankingList.add("PRESENT_PERFECT-0");
		tenseRankingList.add("FUTURE-0");
		
		List<String> timeRankingList=new ArrayList<>();
		timeRankingList.add("1988");
		timeRankingList.add("PRESENT_REF");
		
		//WSJ_20130318_731.col
		String inputFileName = "data/tdata/col_files/wsj_0026.col";
		String colID="wsj_0026.col";
		//String inputResouce="data//tdata//TempEval3.txt";
		// read data from file and write into List by sentence. 
		// Every line is a sentence and every word of sentence is a KeyWord
		List<Sentence> sentenceList = new ArrayList<Sentence>();
		DataWriter 		Writer      = new DataWriter();
		sentenceList = Writer.writeSentenceList(inputFileName); 

		List<oieSentence> oieSentenceList=new ArrayList<>();
		// Sentence 0 for tmx0 processing
		
		//Loading resource	
		String inputResouce="data//tdata//TempEval3_test.TLINK";
		BufferedReader br = new BufferedReader(new FileReader(inputResouce));
		String sentence = null;
		List<String> allSourceRecord=new ArrayList<>(); 
		while((sentence = br.readLine()) != null){
			allSourceRecord.add(sentence);
		}
		br.close();
		//--load recource
		
		for (int i = 1; i < sentenceList.size(); i++) { // Starting from sentence 1
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
			System.out.println("-------------------------------------------");
			System.out.println();
		}
		
		// start here
	
		extractedRelationNodes _relationNodes=new extractedRelationNodes();
		_relationNodes.setpOIESentence(oieSentenceList);
		_relationNodes.loadRelationNodeList(allSourceRecord, colID);
		// for updated relation list
		relationNodes initialNode=new relationNodes();
		List<relationNodes> relResourceList=initialNode.loadSource(allSourceRecord, colID);
		List<relationNodes> relOriginalList=_relationNodes.getRelNodesList();
		List<relationNodes> updateRelList=new ArrayList<>();
		updateRelList=_relationNodes.updateRelationNodeListByFoundResource(relResourceList, relOriginalList);
		_relationNodes.addRelList(updateRelList);
		
		_relationNodes.optimizeRelList();
		List<relationNodes> relList=_relationNodes.getRelNodesList();
		
		// for final list (update Tense and Time)
		for (int i = 0; i < relList.size(); i++) {
			relationNodes relNodes=relList.get(i);
			relNodes.setTenseTime(sentenceList);
		}
		
		System.out.println("Rules output...");
		System.out.println("e34-e123");
		relationNodes temp1=relList.get(11);
		System.out.println(temp1.toString());
		if (temp1.isEventNN_PP_EventNN(sentenceList)==1) {
			System.out.println("Yes");
		}
		System.out.println();

		System.out.println("e5-e6");
		relationNodes temp2=relList.get(4);
		System.out.println(temp2.toString());
		if (temp2.isEventVRel_EventVArgument()==1) {
			System.out.println("Yes");
		}
		System.out.println();
		
		System.out.println("e26-e5");
		relationNodes temp3=relList.get(2);
		System.out.println(temp3.toString());
		if (temp3.isEventNSub_EventVRel()==1) {
			System.out.println("Yes");
		}
		System.out.println();
		
		System.out.println("e26-e6");
		relationNodes temp4=relList.get(3);
		System.out.println(temp4.toString());
		if (temp4.isEventNSub_EventVArgument()==1) {
			System.out.println("Yes");
		}
		System.out.println();

		System.out.println("e10-e29");
		relationNodes temp5=relList.get(5);
		System.out.println(temp5.toString());
		if (temp5.isEventVRel_EventNArgument()==1) {
			System.out.println("Yes");
		}
		System.out.println();
		
		System.out.println("Equal Events");
		for (int i = 0; i < relList.size(); i++) {
			relationNodes pTemp=relList.get(i);
			System.out.println(i+": "+pTemp.toString());
			if (pTemp.isEqualEvents()==1) {
				System.out.println("Yes");
			}
		}	
		System.out.println();
		
		System.out.println("TMX38 e39");
		relationNodes temp6=relList.get(12);
		System.out.println(temp6.toString());
		System.out.println(temp6.isEvent_BeginTime(sentenceList));
		System.out.println();
		
		System.out.println("e23 TMX37");
		relationNodes temp7=relList.get(13);
		System.out.println(temp7.toString());
		System.out.println(temp7.isEvent_INTime(sentenceList));
		
		// Testing for indirected nodes
		// e2-e25, e11-e25 =>>e2-e11?
		System.out.println();
		System.out.println("---------------------------");
		System.out.println("Testing for indirected relations"); 
		System.out.println("e2-e25");
		List<relationNodes> path1=new ArrayList<>();
		path1.add(relList.get(1));
		System.out.println(relList.get(1).toString());
		System.out.println(relList.get(1).tenseNode1);
		path1.add(relList.get(16));
		System.out.println(relList.get(16).toString());
		System.out.println(relList.get(16).tenseNode1);
		predictIndirectRelNodes(path1, relList, tenseRankingList, timeRankingList);
		
		System.out.println();
		System.out.println("e26-e2");//e2-e34, e34-e26
		List<relationNodes> path2=new ArrayList<>();
		path2.add(relList.get(20));
		System.out.println(relList.get(20).toString());
		System.out.println(relList.get(20).tenseNode1);
		path2.add(relList.get(19));
		System.out.println(relList.get(19).toString());
		System.out.println(relList.get(19).tenseNode1);
		predictIndirectRelNodes(path2, relList, tenseRankingList, timeRankingList);
		System.out.println();
		
		// for tmx0
		System.out.println("---------------------------");
		System.out.println("Testing for tmx0 relations"); 
		Sentence sen0=sentenceList.get(0);
		List<KeyWordProposition> keyWordEventsList= new ArrayList<>();
		keyWordEventsList=oieSentenceList.get(0).loadAllKeyWordPropositionByOIESentences(oieSentenceList);
		List<relationNodes> tmx0List=_relationNodes.loadTMX0RelList(sen0, relResourceList, keyWordEventsList);
		
		// for final list (update Tense and Time)
		for (int i = 0; i < tmx0List.size(); i++) {
			relationNodes relNodesTMX0=tmx0List.get(i);
			relNodesTMX0.setTenseTime(sentenceList);
			System.out.println(relNodesTMX0.toString());
			System.out.println(relNodesTMX0.getPropositionNode1());
			System.out.println(relNodesTMX0.tenseNode1);
			int temp=relNodesTMX0.isRelTMX0(sentenceList);
			System.out.println(temp);
			System.out.println();
		}

	}
	
	public static void process2() throws IOException{
    	// Tense and Time samples
		List<String> tenseRankingList=new ArrayList<>();
		tenseRankingList.add("PAST-1");
		tenseRankingList.add("PAST_PERFECT-1");
		tenseRankingList.add("PRESENT-2");
		tenseRankingList.add("PRESENT_PERFECT-2");
		tenseRankingList.add("FUTURE-3");
		
		List<String> timeRankingList=new ArrayList<>();
		timeRankingList.add("1988");
		timeRankingList.add("PRESENT_REF");
		
		//WSJ_20130318_731.col
		String inputFileName = "data/tdata/col_files/wsj_0026.col";
		String colID="wsj_0026.col";
		//String inputResouce="data//tdata//TempEval3_wsj0026.txt";
		//String inputFileName = "data/tdata/wsj_0172.col";
		//String inputResouce="data//tdata//TempEval3_wsj0172.txt";
		// read data from file and write into List by sentence. 
		// Every line is a sentence and every word of sentence is a KeyWord
		List<Sentence> sentenceList = new ArrayList<Sentence>();
		DataWriter 		Writer      = new DataWriter();
		sentenceList = Writer.writeSentenceList(inputFileName); 

		//Loading resource	
		String inputResouce="data//tdata//TempEval3_test.TLINK";
		BufferedReader br = new BufferedReader(new FileReader(inputResouce));
		String sentence = null;
		List<String> allSourceRecord=new ArrayList<>(); 
		while((sentence = br.readLine()) != null){
			allSourceRecord.add(sentence);
		}
		br.close();
		//--load recource
		
		List<oieSentence> oieSentenceList=new ArrayList<>();
		// Sentence 0 for tmx0 processing
		
		
		for (int i = 1; i < sentenceList.size(); i++) { // Starting from sentence 1
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
			System.out.println("-------------------------------------------");
			System.out.println();
		}
		
		
		// start here
	
		extractedRelationNodes _relationNodes=new extractedRelationNodes();
		_relationNodes.setpOIESentence(oieSentenceList);
		_relationNodes.loadRelationNodeList(allSourceRecord, colID);
		// for updated relation list
		relationNodes initialNode=new relationNodes();
		List<relationNodes> relResourceList=initialNode.loadSource(allSourceRecord, colID);
		List<relationNodes> relOriginalList=_relationNodes.getRelNodesList();
		List<relationNodes> updateRelList=new ArrayList<>();
		updateRelList=_relationNodes.updateRelationNodeListByFoundResource(relResourceList, relOriginalList);
		_relationNodes.addRelList(updateRelList);
		
		// tmx0 list
		Sentence sen0=sentenceList.get(0);
		List<KeyWordProposition> keyWordEventsList= new ArrayList<>();
		keyWordEventsList=oieSentenceList.get(0).loadAllKeyWordPropositionByOIESentences(oieSentenceList);
		List<relationNodes> tmx0List=_relationNodes.loadTMX0RelList(sen0, relResourceList, keyWordEventsList);
		_relationNodes.addRelList(tmx0List);
		
		_relationNodes.optimizeRelList();
		List<relationNodes> relList=_relationNodes.getRelNodesList();
		
		// for final list (update Tense and Time)
		for (int i = 0; i < relList.size(); i++) {
			relationNodes relNodes=relList.get(i);
			relNodes.setTenseTime(sentenceList);
		}
		System.out.println();
		System.out.println("Final relations");
		for (int i = 0; i < relList.size(); i++) {
			System.out.println(relList.get(i).toString());
		}
		System.out.println();
		
		// Loading graph
		// Building graph
		Graph graph = graphUtil.graphInit(relList);
	    graph.display();
		// Print graph
		for (Node node : graph) {
            node.addAttribute("ui.label", node.getId());
        }
		
		System.out.println("Rules output...");
		System.out.println("e34-e123");
		relationNodes temp1=relList.get(11);
		System.out.println(temp1.toString());
		if (temp1.isEventNN_PP_EventNN(sentenceList)==1) {
			System.out.println("Yes");
		}
		System.out.println();
		
		
		System.out.println("e5-e6");
		relationNodes temp2=relList.get(4);
		System.out.println(temp2.toString());
		if (temp2.isEventVRel_EventVArgument()==1) {
			System.out.println("Yes");
		}
		System.out.println();
		
		System.out.println("e26-e5");
		relationNodes temp3=relList.get(2);
		System.out.println(temp3.toString());
		if (temp3.isEventNSub_EventVRel()==1) {
			System.out.println("Yes");
		}
		System.out.println();
		
		System.out.println("e26-e6");
		relationNodes temp4=relList.get(3);
		System.out.println(temp4.toString());
		if (temp4.isEventNSub_EventVArgument()==1) {
			System.out.println("Yes");
		}
		System.out.println();

		System.out.println("e10-e29");
		relationNodes temp5=relList.get(5);
		System.out.println(temp5.toString());
		if (temp5.isEventVRel_EventNArgument()==1) {
			System.out.println("Yes");
		}
		System.out.println();
		
		System.out.println("Equal Events");
		for (int i = 0; i < relList.size(); i++) {
			relationNodes pTemp=relList.get(i);
			System.out.println(i+": "+pTemp.toString());
			if (pTemp.isEqualEvents()==1) {
				System.out.println("Yes");
			}
		}	
		System.out.println();
		
		System.out.println("TMX38 e39");
		relationNodes temp6=relList.get(12);
		System.out.println(temp6.toString());
		System.out.println(temp6.isEvent_BeginTime(sentenceList));
		System.out.println();
		
		System.out.println("e23 TMX37");
		relationNodes temp7=relList.get(13);
		System.out.println(temp7.toString());
		System.out.println(temp7.isEvent_INTime(sentenceList));
		
		// for tmx0
		System.out.println();
		System.out.println("---------------------------");
		System.out.println("Testing for tmx0 relations"); 
		
		// for final list (update Tense and Time)
		for (int i = 0; i < tmx0List.size(); i++) {
			relationNodes relNodesTMX0=tmx0List.get(i);
			//relNodesTMX0.setTenseTime(sentenceList);
			System.out.println(relNodesTMX0.toString());
			System.out.println(relNodesTMX0.getPropositionNode1());
			System.out.println(relNodesTMX0.tenseNode1);
			int temp=relNodesTMX0.isRelTMX0(sentenceList);
			System.out.println(temp);
			System.out.println();
		}
		
		// Testing for indirected nodes
		// e2-e25, e11-e25 =>>e2-e11?
		System.out.println("---------------------------");
		System.out.println("Testing for indirected relations"); 
		System.out.println("e11-e2");

		//Shortest path 1
		String node1Label="e11";
		String node2Label="e2";
		Path shortestPath=graphUtil.shortestPath(graph, node1Label, node2Label);
		List<String> relPath=graphUtil.shortPathList(shortestPath);
		
		//  Can phai xu ly relation list cho path
		List<relationNodes> pathRelations=new ArrayList<>();
		for (int i = 0; i < relPath.size(); i++) {
			String[] relText=relPath.get(i).split("-");
			String node1Txt=relText[0].toLowerCase();
			String node2Txt=relText[1].toLowerCase();
			
			for (int j = 0; j < relList.size(); j++) {
				relationNodes pRelTemp=relList.get(j);
				if (pRelTemp.isFound(node1Txt, node2Txt)==1) {
					pathRelations.add(pRelTemp);
				}else if (pRelTemp.isReverse(node1Txt, node2Txt)==1) {
					pRelTemp.reverse();
					pathRelations.add(pRelTemp);
				}
			}
		
		}
		
		predictIndirectRelNodes(pathRelations, relList, tenseRankingList, timeRankingList);
		
		
		List<relationNodes> path1=new ArrayList<>();
		path1.add(relList.get(1));
		System.out.println(relList.get(1).toString());
		System.out.println(relList.get(1).tenseNode1);
		path1.add(relList.get(16));
		System.out.println(relList.get(16).toString());
		System.out.println(relList.get(16).tenseNode1);
		predictIndirectRelNodes(path1, relList, tenseRankingList, timeRankingList);
		
		
		System.out.println();
		System.out.println("e26-e2");//e2-e34, e34-e26
		
		//Shortest path 2
		
		String node11Label="e2";
		String node22Label="e26";
		Path shortestPath2=graphUtil.shortestPath(graph, node11Label, node22Label);
		List<String> relPath2=graphUtil.shortPathList(shortestPath2);
		//  Can phai xu ly relation list cho path
		List<relationNodes> pathRelations2=new ArrayList<>();
		for (int i = 0; i < relPath2.size(); i++) {
			String[] relText=relPath2.get(i).split("-");
			String node1Txt=relText[0].toLowerCase();
			String node2Txt=relText[1].toLowerCase();
			
			for (int j = 0; j < relList.size(); j++) {
				relationNodes pRelTemp=relList.get(j);
				if (pRelTemp.isFound(node1Txt, node2Txt)==1) {
					pathRelations2.add(pRelTemp);
				}else if (pRelTemp.isReverse(node1Txt, node2Txt)==1) {
					pRelTemp.reverse();
					pathRelations2.add(pRelTemp);
				}
			}
		
		}
		predictIndirectRelNodes(pathRelations2, relList, tenseRankingList, timeRankingList);
		System.out.println();
		
		List<relationNodes> path2=new ArrayList<>();
		path2.add(relList.get(20));
		System.out.println(relList.get(20).toString());
		System.out.println(relList.get(20).tenseNode1);
		path2.add(relList.get(19));
		System.out.println(relList.get(19).toString());
		System.out.println(relList.get(19).tenseNode1);
		predictIndirectRelNodes(path2, relList, tenseRankingList, timeRankingList);
		System.out.println();
		
	
	}
	
	public static void main(String args[]) throws IOException{
    	//process1();
    	process2();
		
	}
}

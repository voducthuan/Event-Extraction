package com.ls3.oie.graph;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.graphstream.algorithm.APSP;
import org.graphstream.algorithm.APSP.APSPInfo;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.stream.file.FileSourceDGS;
import org.graphstream.ui.view.Viewer;

import com.ls3.oie.clause.ClausIE;
import com.ls3.oie.temporalmodel.DataWriter;
import com.ls3.oie.temporalmodel.KeyWord;
import com.ls3.oie.temporalmodel.KeyWordProposition;
import com.ls3.oie.temporalmodel.Sentence;
import com.ls3.oie.temporalmodel.extractedProposition;
import com.ls3.oie.temporalmodel.extractedRelationNodes;
import com.ls3.oie.temporalmodel.oieSentence;
import com.ls3.oie.temporalmodel.relationNodes;


public class graph_test {
    public static void test1(){
    	System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        MultiGraph graph = new MultiGraph("Network");

        graph.addAttribute("ui.quality");
        graph.addAttribute("ui.antialias");
        graph.addAttribute("ui.stylesheet", "edge {text-alignment: along;}");

        Node A=graph.addNode("A");
        Node B=graph.addNode("B");
        
        A.addAttribute("Node A");
        A.setAttribute("ui.label", "Node A");
        
        Edge ab = graph.addEdge("AB", "A", "B", true);
        Edge ba = graph.addEdge("BA", "B", "A", true);

        //ab.setAttribute("ui.label", "1.2345");
        //ba.setAttribute("ui.label", "5.4321");
        
        ab.setAttribute("weight", 1);
        ba.setAttribute("weight", 2);
        
        graph.display();
        
        double no1=ab.getNumber("weight");
        double no2=ba.getNumber("weight");
        
        System.out.println(no1);
        System.out.println(no2);
        System.out.println(no1+no2);
    }
	
    public static void test2(){
    	//System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        MultiGraph graph = new MultiGraph("Network");
        graph.addAttribute("ui.quality");
        graph.addAttribute("ui.antialias");
        graph.addAttribute("ui.stylesheet", "edge {text-alignment: along;}");
        graph.addNode("A");
        graph.addNode("B");

        Edge ab = graph.addEdge("AB", "A", "B", true);
        Edge ba = graph.addEdge("BA", "B", "A", true);
        ab.setAttribute("ui.label", "1.2345");
        ab.setAttribute("ui.stylesheet", "text-offset: -100, -200;"); 
        //ab.setAttribute("ui.style", "text-offset: 100;"); 
        
        ba.setAttribute("ui.label", "5.4321");
        ab.setAttribute("ui.stylesheet", "text-offset: 1000, 1000;");
        
        graph.display();
    	
    }
    
    public static void test3(){
    	Graph graph = new MultiGraph("graph");

        String styleSheet="node {"+
   " fill-color: grey;"+
   " size: 10px;"+
   " stroke-mode: plain;"+
   " stroke-color: black;"+
   " stroke-width: 1px;"+
   "}"+
   "node.important {"+
   " fill-color: red;"+
   " size: 30px;"+
   "}";

        graph.addNode("A");
        graph.addNode("B");
        graph.addEdge("AB", "A", "B");
        Node e1=graph.getNode("A");
        graph.addAttribute("ui.stylesheet", styleSheet);

        e1.addAttribute("ui.class", "important"); 
        e1.addAttribute("ui.label", "A Node");
        //e1.addAttribute( "ui.hide" );

        graph.display();
    	
    }
    
    public static void test4(){
    	System.setProperty("org.graphstream.ui.renderer","org.graphstream.ui.j2dviewer.J2DGraphRenderer");
    	Graph graph = new MultiGraph("Test");

        graph.addNode("A");
        graph.addNode("B");


        graph.addEdge("1", "A", "B", true);
        graph.addEdge("2", "B", "A", true);

        Viewer viewer = graph.display();
    	
    }
    
    public static void test5() throws IOException{
    	String styleSheet =
    	        "node {" +
    	        "	fill-color: black;" +
    	        "}" +
    	        "node.marked {" +
    	        "	fill-color: red;" +
    	        "}";
    	//WSJ_20130318_731.col
    	String inputFileName = "data/tdata/col_files/wsj_0026.col";
    	String colID="wsj_0026.col";
    	//String inputResouce="data//tdata//TempEval3.txt";
		// read data from file and write into List by sentence. 
		// Every line is a sentence and every word of sentence is a KeyWord
		List<Sentence> sentenceList = new ArrayList<Sentence>();
		DataWriter Writer = new DataWriter();
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
		relationNodes initialNode=new relationNodes();
		List<relationNodes> relResourceList=initialNode.loadSource(allSourceRecord, colID);
		extractedRelationNodes _relationNodes=new extractedRelationNodes();
		_relationNodes.setpOIESentence(oieSentenceList);
		_relationNodes.loadRelationNodeList(allSourceRecord, colID);
		List<relationNodes> relOriginalList=_relationNodes.getRelNodesList();
		List<relationNodes> updateRelList=new ArrayList<>();
		updateRelList=_relationNodes.updateRelationNodeListByFoundResource(relResourceList, relOriginalList);
		//////////////////////////////////
		List<relationNodes> tempFinal=new ArrayList<>();
		for (int i = 0; i < updateRelList.size(); i++) {
			tempFinal.add(updateRelList.get(i));
		}
		//////////////////////////////////
		//_relationNodes.addRelList(updateRelList);
		_relationNodes.optimizeRelList();
		_relationNodes.addRelList(tempFinal);
		
		// Load TMX0 list
		Sentence sen0=sentenceList.get(0);
		List<KeyWordProposition> keyWordEventsList= new ArrayList<>();
		keyWordEventsList=oieSentenceList.get(0).loadAllKeyWordPropositionByOIESentences(oieSentenceList);
		List<relationNodes> tmx0List=_relationNodes.loadTMX0RelList(sen0, relResourceList, keyWordEventsList);
		_relationNodes.addRelList(tmx0List);
		List<relationNodes> finalRelList=_relationNodes.getRelNodesList();

		// Building graph
		Graph graph = graphUtil.graphInit(finalRelList);
	    graph.display();
		// Print graph
		for (Node node : graph) {
            node.addAttribute("ui.label", node.getId());
        }

		//Shortest path
		String node1Label="tmx0";
		String node2Label="e11";
		Path shortestPath=graphUtil.shortestPath(graph, node1Label, node2Label);
		List<String> relPaht=graphUtil.shortPathList(shortestPath);
		
		for (int i = 0; i < relPaht.size(); i++) {
			System.out.println(relPaht.get(i));
		}
    }
    
	public static void main(String args[]) throws IOException{
		//new graph_test();
		test5();
	}
	
    public graph_test() throws IOException{
    	//WSJ_20130318_731.col
    	String inputFileName = "data/tdata/col_files/wsj_0026.col";
    	String colID="wsj_0026.col";
    	//String inputResouce="data//tdata//TempEval3.txt";
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
		relationNodes initialNode=new relationNodes();
		List<relationNodes> relResourceList=initialNode.loadSource(allSourceRecord,colID);
		
		extractedRelationNodes _relationNodes=new extractedRelationNodes();
		_relationNodes.setpOIESentence(oieSentenceList);
		_relationNodes.loadRelationNodeList(allSourceRecord,colID);
		List<relationNodes> relOriginalList=_relationNodes.getRelNodesList();
		List<relationNodes> updateRelList=new ArrayList<>();
		updateRelList=_relationNodes.updateRelationNodeListByFoundResource(relResourceList, relOriginalList);
		_relationNodes.addRelList(updateRelList);
		_relationNodes.optimizeRelList();
		List<relationNodes> finalRelList=_relationNodes.getRelNodesList();
		/*
		System.out.println("Final relation output");
		for (int i = 0; i < relList.size(); i++) {
			relationNodes relNodes=relList.get(i);
			System.out.println(relNodes.toString());
		}
    	*/
		Graph graph = new SingleGraph("tutorial 1");
        graph.addAttribute("ui.stylesheet", styleSheet);
        graph.setAutoCreate(true);
        graph.setStrict(false);
        graph.display();
		System.out.println("Loading relation nodes:");
		
        for (int i = 0; i < finalRelList.size(); i++) {
		//for (int i = 13; i < 14; i++) {
			relationNodes relNodes=finalRelList.get(i);
			
			KeyWord node1=relNodes.getNode1();
			String node1Label;
			if (node1.isEvent()==1) {
				node1Label=node1.getEventName4();
			}else{ node1Label=node1.getTimeName7();
			     }     
			
			KeyWord node2=relNodes.getNode2();
			
			String node2Label;
			if (node2.isEvent()==1) {
				node2Label=node2.getEventName4();
			}else{ node2Label=node2.getTimeName7();
			     }
			
			graph.addNode(node1Label);
	        graph.addNode(node2Label);
	        String labelEdge=node1Label+node2Label;
	        
	        Edge pEdge = graph.addEdge(labelEdge, node1Label, node2Label);
	        pEdge.setAttribute("weight", 1);
	        //double no1=ab.getNumber("weight");
	        System.out.println(relNodes.toString());
		}
        System.out.println("End loading ...");
        APSP apsp = new APSP();
		apsp.init(graph); // registering apsp as a sink for the graph
		apsp.setDirected(false); // undirected graph
		apsp.setWeightAttributeName("weight"); // ensure that the attribute name used is "weight"
		apsp.compute(); // the method that actually computes shortest paths
		APSPInfo info = graph.getNode("e11").getAttribute(APSPInfo.ATTRIBUTE_NAME);		
		System.out.println(info.getShortestPathTo("e2"));
        
        // for tmx0 List
        //relationNodes initialNode=new relationNodes();
		//List<relationNodes> relResourceList=initialNode.loadSource();
        Sentence sen0=sentenceList.get(0);
		List<KeyWordProposition> keyWordEventsList= new ArrayList<>();
		keyWordEventsList=oieSentenceList.get(0).loadAllKeyWordPropositionByOIESentences(oieSentenceList);
		List<relationNodes> tmx0List=_relationNodes.loadTMX0RelList(sen0, relResourceList, keyWordEventsList);
		
		System.out.println();
		System.out.println("Loading tmx0 relation");
		for (int i = 0; i < tmx0List.size(); i++) {
			relationNodes relNodes=tmx0List.get(i);
			KeyWord node1=relNodes.getNode1();
			String node1Label;
			if (node1.isEvent()==1) {
				node1Label=node1.getEventName4();
			}else{ node1Label=node1.getTimeName7();
			     }     
			
			KeyWord node2=relNodes.getNode2();
			
			String node2Label;
			if (node2.isEvent()==1) {
				node2Label=node2.getEventName4();
			}else{ node2Label=node1.getTimeName7();
			     }
			graph.addNode(node1Label);
	        graph.addNode(node2Label);
	        graph.addEdge(node1Label+node2Label, node1Label, node2Label);
	        
	        System.out.println(relNodes.toString());
		}
		System.out.println("End loading...");
		
		
        for (Node node : graph) {
            node.addAttribute("ui.label", node.getId());
        }
        
        //Shortest path
  		String node1Label="tmx0";
  		String node2Label="e11";
  		Path shortestPath=graphUtil.shortestPath(graph, node1Label, node2Label);
  		List<String> relPaht=graphUtil.shortPathList(shortestPath);
  		
  		for (int i = 0; i < relPaht.size(); i++) {
  			System.out.println(relPaht.get(i));
  		}
        //explore(graph.getNode("e11"));
    }

    public void explore(Node source) {
        Iterator<? extends Node> k = source.getBreadthFirstIterator();

        while (k.hasNext()) {
            Node next = k.next();
            next.setAttribute("ui.class", "marked");
            sleep();
        }
    }

    protected void sleep() {
        try { Thread.sleep(1000); } catch (Exception e) {}
    }

    protected String styleSheet =
        "node {" +
        "	fill-color: black;" +
        "}" +
        "node.marked {" +
        "	fill-color: red;" +
        "}";
}
package com.ls3.oie.temporalmodel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import com.ls3.oie.clause.ClausIE;
import com.ls3.oie.clause.Proposition;
import com.ls3.oie5.patternOIE5;

import edu.stanford.nlp.io.EncodingPrintWriter.out;

public class extractedRelationNodesOIE5 {

	public List<oie5Sentence> pOIE5Sentences;
	public List<relationNodes> relNodesList;
	
	public extractedRelationNodesOIE5(){
		this.pOIE5Sentences=new ArrayList<>();
		this.relNodesList=new ArrayList<>();
	}
	
	public extractedRelationNodesOIE5(List<oie5Sentence> _oieSens, List<relationNodes> _relNodesList){
		this.pOIE5Sentences=_oieSens;
		this.relNodesList=_relNodesList;
	}

	public List<oie5Sentence> getpOIESentence() {
		return this.pOIE5Sentences;
	}

	public void setpOIESentence(List<oie5Sentence> _pOIESentences) {
		this.pOIE5Sentences = _pOIESentences;
	}

	public List<relationNodes> getRelNodesList() {
		return this.relNodesList;
	}

	public void setRelNodesList(List<relationNodes> _relNodesList) {
		this.relNodesList = relNodesList;
	}

	public void loadRelationNodeList(List<String> inputResouceRecorrds, String pColID) throws IOException{
		System.out.println("-----------------------Testing here------------------------------------");
		List<relationNodes> result=new ArrayList<>();
		// Load resources
		relationNodes initialNode=new relationNodes();
		List<relationNodes> relResourceList=initialNode.loadSource(inputResouceRecorrds, pColID);
		System.out.println("Resource information:");
		
		for (int i = 0; i < relResourceList.size(); i++) {
			System.out.println(relResourceList.get(i).toString());
		}
		System.out.println("----------------------------");
		System.out.println("All events in documents"); // chua xu ly ve time, da xu ly update 30 May
		// Load keyword event arguments
		List<KeyWord> keyWordEventsList=new ArrayList<>();
		keyWordEventsList=this.pOIE5Sentences.get(0).loadAllKeyWordEventByOIE5Sentences(pOIE5Sentences);
		for (int i = 0; i < keyWordEventsList.size(); i++) {
			System.out.println(keyWordEventsList.get(i).toString());
		}
		System.out.println("----------------------------");
		System.out.println("Two events in proposition");
		for (int i = 0; i < this.pOIE5Sentences.size(); i++) {
			oie5Sentence tempSentence=this.pOIE5Sentences.get(i); // load each sentence
			List<extractedPropositionOIE5> exPropList=tempSentence.getExtractedPropositionOIE5List();
			for (int j = 0; j < exPropList.size(); j++) {
				extractedPropositionOIE5 tempExProp=exPropList.get(j);
				// xu ly cho 2 events in proposition
				if (tempExProp.getArgumentList().size()==2) { // two event in proposition
					//System.out.println(tempExProp.toString());
					KeyWord pwordNode1=exPropList.get(j).argumentList.get(0);
					KeyWord pwordNode2=exPropList.get(j).argumentList.get(1);
					//relationNodes pRelNodes=new relationNodes(pwordNode1, pwordNode2);  
					// update adding propositions
					relationNodes pRelNodes=new relationNodes(pwordNode1, pwordNode2, tempExProp.getPattern(), tempExProp.getPattern()); // update on 30 May
					// check existing listing, if not add pRelNodes into relation nodes
					if (pRelNodes.isExist(result)!=1) {
						result.add(pRelNodes);
					}
				}	
				// Update on 19 May
				// xu ly 3 events trong proposition sub, rel, and predicate 
				if(tempExProp.getArgumentList().size()==3){
					KeyWord pwordNode1=exPropList.get(j).argumentList.get(0);
					KeyWord pwordNode2=exPropList.get(j).argumentList.get(1);
					KeyWord pwordNode3=exPropList.get(j).argumentList.get(2);
					if (tempExProp.isKeyWordInSub(pwordNode1)==1&&tempExProp.isKeyWordInRel(pwordNode2)==1&&tempExProp.isKeyWordInObj(pwordNode3)==1) {
						//relationNodes pRel1=new relationNodes(pwordNode1, pwordNode2);
						relationNodes pRel1=new relationNodes(pwordNode1, pwordNode2, tempExProp.getPattern(), tempExProp.getPattern()); // update on 30 May
						// check existing listing, if not add pRelNodes into relation nodes
						if (pRel1.isExist(result)!=1) {
							result.add(pRel1);
						}
						//relationNodes pRel2=new relationNodes(pwordNode1, pwordNode3);
						relationNodes pRel2=new relationNodes(pwordNode1, pwordNode3, tempExProp.getPattern(), tempExProp.getPattern()); // update on 30 May
						// check existing listing, if not add pRelNodes into relation nodes
						if (pRel2.isExist(result)!=1) {
							result.add(pRel2);
						}
						//relationNodes pRel3=new relationNodes(pwordNode2, pwordNode3);
						relationNodes pRel3=new relationNodes(pwordNode2, pwordNode3, tempExProp.getPattern(), tempExProp.getPattern()); // update on 30 May
						// check existing listing, if not add pRelNodes into relation nodes
						if (pRel3.isExist(result)!=1) {
							result.add(pRel3);
						}
					}else{
						//relationNodes pRel1=new relationNodes(pwordNode1, pwordNode2);
						relationNodes pRel4=new relationNodes(pwordNode1, pwordNode2, tempExProp.getPattern(), tempExProp.getPattern()); // update on 30 May
						// check existing listing, if not add pRelNodes into relation nodes
						if (pRel4.isExist(result)!=1) {
							result.add(pRel4);
						}
					}
					// can bo sung o phan nay trong truong hop co' den 3 event (e1, e2, e3) trong proposition
					// Trong truong hop nay, neu e1 nam trong subject, e2 nam trong relation and e3 trong predicate
					// => e1-e2; e1-e3; e2-e3
				}
				// xu ly >3 events trong proposition 
				if(tempExProp.getArgumentList().size()>3){
					KeyWord pwordNode1=exPropList.get(j).argumentList.get(0);
					KeyWord pwordNode2=exPropList.get(j).argumentList.get(1);
					//relationNodes pRelNodes=new relationNodes(pwordNode1, pwordNode2);
					relationNodes pRelNodes=new relationNodes(pwordNode1, pwordNode2, tempExProp.getPattern(), tempExProp.getPattern()); // update on 30 May
					// check existing listing, if not add pRelNodes into relation nodes
					if (pRelNodes.isExist(result)!=1) {
						result.add(pRelNodes);
					}
					// can bo sung o phan nay trong truong hop co' den 3 event (e1, e2, e3) trong proposition
					// Trong truong hop nay, neu e1 nam trong subject, e2 nam trong relation and e3 trong predicate
					// => e1-e2; e1-e3; e2-e3
				}
			}
		}
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).toString());
		}
		System.out.println("------------------------------");
		//---------------------------------------------
		//---------------------------------------------
		// for 1 events co trong proposition
		System.out.println("Updating event relations");
		for (int i = 0; i < this.pOIE5Sentences.size(); i++) {
			oie5Sentence tempSentence=this.pOIE5Sentences.get(i); // load each sentence
			List<extractedPropositionOIE5> exPropList=tempSentence.getExtractedPropositionOIE5List();
			// Load keyword, load 2event from results, load resource phai duoc load truoc
			for (int j = 0; j < exPropList.size(); j++) {
				extractedPropositionOIE5 tempExProp=exPropList.get(j);
				// xu ly 1 event trong proposition, can than o phan na`y
				// phan nay gom 2 dieu kien de co' duoc 1 relaiotn
				//("Timex", "had requested", "duty-free treatment (e25, e29, e31) for many types of watches covered by 58 different U.S. tariff classifications")  e11:requested 
				//("Timex", "had requested", "duty-free treatment")  e11:requested
				// Dau tien phai extract Time, had, duty-free, treatment, sau do kiem tra co event nao bieu dien cac tu nay khong e.g., treatment(e25, e26, e31)
				// e2-e25
				// e11-e2
				// can phai loai e29, e31
				String sub=tempExProp.getPattern().sub;
				String re=tempExProp.getPattern().rel;
				String arg=tempExProp.getPattern().obj;
				//if (tempExProp.getProposition().noArguments()>0) {
        			//arg=tempExProp.getProposition().argument(0);
				//}
				if(tempExProp.getArgumentList().size()==1){
					// update 10 June
					patternOIE5 ptempPro=tempExProp.getPattern();
					// end update 
					KeyWord pwordNode1=exPropList.get(j).argumentList.get(0); //for arg1 (e11), requested:e11 (phan xu ly cho Node1)
					System.out.println(pwordNode1.toString());
					System.out.println();
					List<KeyWord> Node2List=new ArrayList<>(); //su dung de adding (e25, e29, e31) for arg2
					// load all keyword Event in documents
					for (int k = 0; k < keyWordEventsList.size(); k++) {
						KeyWord _keyword=keyWordEventsList.get(k);
						// neu cac keyword xuat hien trong sub, re, arg=> thuc hien selecting cac keyword nay
						if (_keyword.isExistWord0(sub)==1||_keyword.isExistWord0(re)==1||_keyword.isExistWord0(arg)==1) {
							Node2List.add(_keyword);
						}
					}
					// ket thuc viec add e25, e29, e31
					/*
					for (int k = 0; k < Node2List.size(); k++) {
						System.out.println(Node2List.get(k).toString());
					}
					System.out.println("*********************");
					*/
					//-------------------------------
					// Kiem tra xem arg e11 co thuoc doi tuong trong annotation khong?
					// Lay cac context co chua e11. e.g e2
					List<String> _ExtnodesResource=new ArrayList<>();
					for (int k = 0; k < relResourceList.size(); k++) {
						relationNodes _relationSource=relResourceList.get(k);
						//************* extract de so sanh
						//thong tin pwordNode1
						String pwordNode1text="";
						if (pwordNode1.isEvent()==1) {
							pwordNode1text=pwordNode1.getEventName4();
						}else pwordNode1text=pwordNode1.getTimeName7();
						// Thong tin node1, node1 trong event source
						KeyWord pNode1=_relationSource.getNode1();
						String node1text="";
						if (pNode1.isEvent()==1) {
							node1text=pNode1.getEventName4();
						}else node1text=pNode1.getTimeName7();
						KeyWord pNode2=_relationSource.getNode2();
						String node2text="";
						if (pNode2.isEvent()==1) {
							node2text=pNode2.getEventName4();
						}else node2text=pNode2.getTimeName7();
						//--------Ket thuc viec extract
						if (node1text.equals(pwordNode1text)) {
							_ExtnodesResource.add(node2text);
						}
						if (node2text.equals(pwordNode1text)) {
							_ExtnodesResource.add(node1text);
						}
					}
					/*
					System.out.println("-------------Matching resourses---------------");
					for (int k = 0; k < _ExtnodesResource.size(); k++) {
						System.out.println(_ExtnodesResource.get(k));
					}
					System.out.println("-------------End matching resources------------");
					*/
					// Loc cac relation with 2 events
					List<relationNodes> _2EventNodeFilter=new ArrayList<>();
					for (int k = 0; k < result.size(); k++) {
						relationNodes tempRelNodes=result.get(k);
						for (int l = 0; l < _ExtnodesResource.size(); l++) {
							String _txt=_ExtnodesResource.get(l);
							if (tempRelNodes.isTextInRelationNode(_txt)==1) {
								_2EventNodeFilter.add(tempRelNodes);
							}
						}
					}
					// final step
					for (int k = 0; k < _2EventNodeFilter.size(); k++) {
						relationNodes _tempNode=_2EventNodeFilter.get(k);
						for (int l = 0; l < Node2List.size(); l++) {
							KeyWord _keyword=Node2List.get(l);
							if (_tempNode.isKeyWordInRelationNode(_keyword)==1) {
								// xu ly buoc cuoi cung
								//relationNodes pRelNodes1Event=new relationNodes(pwordNode1, _keyword);
								relationNodes pRelNodes1Event=new relationNodes(pwordNode1, _keyword, exPropList.get(j).getPattern(), exPropList.get(j).getPattern());
								if (pRelNodes1Event.isExist(result)!=1) {
									result.add(pRelNodes1Event);
								}
							}
						}
					}
				}
			}
		}
		//Adding equalRelEvents
		/*
		for (int i = 0; i < equalRelEvents.size(); i++) {
			result.add(equalRelEvents.get(i));
		}
		*/
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).toString());
		}
		this.relNodesList=result;
	}
	

	public void optimizeRelList(){
		List<relationNodes> newRelList=new ArrayList<>();
		List<relationNodes> result=this.relNodesList;
		for (int i = 0; i < result.size(); i++) {
			
			relationNodes pRelNode=result.get(i);
			
			KeyWord _node1=pRelNode.getNode1();
			KeyWord _node2=pRelNode.getNode2();
			
			String node1="";
			String node2="";
			
			if (_node1.isEvent()==1) {
				node1=_node1.getEventName4();
			}else{
				node1=_node1.getTimeName7();
			}
			
			if (_node2.isEvent()==1) {
				node2=_node2.getEventName4();
			}else{
				node2=_node2.getTimeName7();
			}
			
			if (!node1.toLowerCase().equals(node2.toLowerCase())) {
				newRelList.add(pRelNode);
			}
		}
		
		this.relNodesList=newRelList;
		
	}
	
	// Update on 22 May 
	public List<relationNodes> loadTMX0RelList_bk(Sentence _txm0Sentence, List<relationNodes> _relResouceList, List<KeyWord> _keyWordEventsList){
		List<relationNodes> result = new ArrayList<>();
		List<relationNodes> tmx0RelList=new ArrayList<>();
		for (int i = 0; i < _relResouceList.size(); i++) {
			relationNodes pRelNodes=_relResouceList.get(i);
			String node1txt=pRelNodes.getNode1().getTimeName7();
			String node2txt=pRelNodes.getNode2().getTimeName7();
			if (node1txt.equals("tmx0")||node2txt.equals("tmx0")) {
				tmx0RelList.add(pRelNodes);
			}
		}
		for (int i = 0; i < tmx0RelList.size(); i++) {
			relationNodes ptmx0odes=tmx0RelList.get(i);
			String node1txt="";
			String node2txt="";
			KeyWord node1=ptmx0odes.getNode1();
			KeyWord node2=ptmx0odes.getNode2();
			//String node1txt=ptmx0odes.getNode1().getTimeName7();
			if (node1.getTimeName7().equals("tmx0")) {
				if (node2.isEvent()==1) {
					node2txt=node2.getEventName4();
				}else{
					node2txt=node2.getTimeName7();
				}
				for (int j = 0; j < _keyWordEventsList.size(); j++) {
					KeyWord pKeyWordEvent=_keyWordEventsList.get(j);
					if (pKeyWordEvent.isExistInEventTime(node2txt)==1) {
						relationNodes pRel=new relationNodes();
						pRel.setNode1(node1);
						pRel.setNode2(pKeyWordEvent);
						result.add(pRel);
					}
				}
			}else{ // node1 is not tmx0
				if (node1.isEvent()==1) {
					node1txt=node1.getEventName4();
				}else{
					node1txt=node1.getTimeName7();
				}
				for (int j = 0; j < _keyWordEventsList.size(); j++) {
					KeyWord pKeyWordEvent=_keyWordEventsList.get(j);
					if (pKeyWordEvent.isExistInEventTime(node1txt)==1) {
						relationNodes pRel=new relationNodes();
						pRel.setNode1(node2);
						pRel.setNode2(pKeyWordEvent);
						result.add(pRel);
					}
				}
			}
		}
		return result;
	}

	// Update on 30 May 
	public List<relationNodes> loadTMX0RelList(Sentence _txm0Sentence, List<relationNodes> _relResouceList, List<KeyWordPropositionOIE5> _keyWordEventsList){
		List<relationNodes> result = new ArrayList<>();
		List<relationNodes> tmx0RelList=new ArrayList<>();
		// keyword Time0
		KeyWord keywordTMX0=_txm0Sentence.sentence.get(0);
		
		for (int i = 0; i < _relResouceList.size(); i++) {
			relationNodes pRelNodes=_relResouceList.get(i);
			String node1txt=pRelNodes.getNode1().getTimeName7();
			String node2txt=pRelNodes.getNode2().getTimeName7();
			if (node1txt.equals("tmx0")||node2txt.equals("tmx0")) {
				tmx0RelList.add(pRelNodes);
			}
		}

		for (int i = 0; i < tmx0RelList.size(); i++) {
			relationNodes ptmx0odes=tmx0RelList.get(i);
			String node1txt="";
			String node2txt="";
			KeyWord node1=ptmx0odes.getNode1();
			KeyWord node2=ptmx0odes.getNode2();
			//String node1txt=ptmx0odes.getNode1().getTimeName7();
			if (node1.getTimeName7().equals("tmx0")) {
				if (node2.isEvent()==1) {
					node2txt=node2.getEventName4();
				}else{
					node2txt=node2.getTimeName7();
				}
				
				for (int j = 0; j < _keyWordEventsList.size(); j++) {
					KeyWord pKeyWordEvent=_keyWordEventsList.get(j).getKeyword();
					if (pKeyWordEvent.isExistInEventTime(node2txt)==1) {
						relationNodes pRel=new relationNodes();
						//pRel.setNode1(node1);
						//pRel.setNode2(pKeyWordEvent);
						pRel.setNode1(pKeyWordEvent);
						//pRel.setNode2(node1); update on 13 July
						pRel.setNode2(keywordTMX0);
						pRel.setPropositionNode1WithOIE5(_keyWordEventsList.get(j).getProposition());
						result.add(pRel);
					}
				}
				
			}else{ // node1 is not tmx0
				
				if (node1.isEvent()==1) {
					node1txt=node1.getEventName4();
				}else{
					node1txt=node1.getTimeName7();
				}
				
				for (int j = 0; j < _keyWordEventsList.size(); j++) {
					KeyWord pKeyWordEvent=_keyWordEventsList.get(j).getKeyword();
					if (pKeyWordEvent.isExistInEventTime(node1txt)==1) {
						relationNodes pRel=new relationNodes();
						//pRel.setNode1(node2);
						//pRel.setNode2(pKeyWordEvent);
						pRel.setNode1(pKeyWordEvent);
						//pRel.setNode2(node1); update on 13 July
						pRel.setNode2(keywordTMX0);
						pRel.setPropositionNode1WithOIE5(_keyWordEventsList.get(j).getProposition());
						result.add(pRel);
					}
				}
			}
		}
		
		return result;
	}

	// Update on 8 June 2018
	// Last update relation list
	// ("President Bush", "has approved", "duty-free treatment")  e2:approved  e25:treatment 
	// ("the president", "has", "action")  e34:action 
	// ("the Philippines and Thailand", "would be", "the main beneficiaries of the president 's action")  e123:beneficiaries  e34:action
	public List<relationNodes> updateRelationNodeListByFoundResource(List<relationNodes> pRelResouceList, List<relationNodes> pOriginalRelList){
		List<relationNodes> result=new ArrayList<>();
		List<relationNodes> newRelResourceList=new ArrayList<>();
		// Xuat hien trong resource, nhung ko xuat hien trong Original List (e26-e2 -> moi quan he gian tiep)
		for (int i = 0; i < pRelResouceList.size() ; i++) {
			relationNodes pTempRel=pRelResouceList.get(i);
			if (pTempRel.isExistInList(pOriginalRelList)!=1) {
				newRelResourceList.add(pTempRel);
			}
		}
		// Da extract duoc (e2-e25), (e10-e15), gia su e10-e15 co the suy luan duoc tu*` danh sach da~ co', can reduce e10-e15
		// Lay tat cac cac e2, e25,... cac cac prepositions
		
		//List<extractedEventProposition> eventPropositionList=new ArrayList<>(); 
		List<KeyWordProposition> keywordProList1=new ArrayList<>();
		for (int i = 0; i < newRelResourceList.size(); i++) {
			relationNodes tempRelNodesA=newRelResourceList.get(i);
			KeyWord node1=tempRelNodesA.getNode1(); // cac node nay khong co gia tri (troi oi, mat thoi gian cho viec kiem tra la.i)
			KeyWord node2=tempRelNodesA.getNode2(); // cac node nay khong co gia tri
			for (int j = 0; j < pOriginalRelList.size(); j++) {
				relationNodes tempRelNodesB=pOriginalRelList.get(j);
				if (tempRelNodesB.isNodeIN(node1)==1) {
					Proposition pPro=tempRelNodesB.getPropositionNode1();
					//KeyWordProposition tempKeyword=new KeyWordProposition(tempRelNodesB.getNode1(), pPro);
					//KeyWordProposition tempKeyword=new KeyWordProposition(node1, pPro); // update on 16 June they the node1 = tempRelNodesB.nodes1
					//KeyWordProposition tempKeyword=new KeyWordProposition(node1, pPro);
					//keywordProList1.add(tempKeyword);
					KeyWordProposition tempKeyword;
					if (tempRelNodesB.getNode1().getEventName4().contains(node1.getEventName4())) {
						tempKeyword=new KeyWordProposition(tempRelNodesB.getNode1(), pPro);
					}else{
						tempKeyword=new KeyWordProposition(tempRelNodesB.getNode2(), pPro);
					}
					keywordProList1.add(tempKeyword);
					
				}
				if (tempRelNodesB.isNodeIN(node2)==1) {
					Proposition pPro=tempRelNodesB.getPropositionNode1();
					//KeyWordProposition tempKeyword=new KeyWordProposition(tempRelNodesB.getNode2(), pPro);
					KeyWordProposition tempKeyword;
					if (tempRelNodesB.getNode1().getEventName4().contains(node2.getEventName4())) {
						tempKeyword=new KeyWordProposition(tempRelNodesB.getNode1(), pPro);
					}else{
						tempKeyword=new KeyWordProposition(tempRelNodesB.getNode2(), pPro);
					}
					keywordProList1.add(tempKeyword);
				}
			}
		}
		// Return gia tri
		// e2 ("President Bush", "has approved", "duty-free treatment")  // phan nay se duoc lua cho.n sau
		// e25 ....
		// e2 The white house said ....
		// Lua chon cac identity relattion list, de ta.o reference (e26=e33) --> can lay e33
		List<relationNodes> identityRelList=new ArrayList<>();
		for (int i = 0; i < pOriginalRelList.size(); i++) {
			relationNodes tempRel=pOriginalRelList.get(i);
			if (tempRel.isIdentity()==1) {
				identityRelList.add(tempRel);
			}
		}
		List<KeyWordProposition> keywordProList2=new ArrayList<>();
		for (int i = 0; i < identityRelList.size(); i++) {
			relationNodes tempRel=identityRelList.get(i);
			KeyWordProposition pNode1=new KeyWordProposition(tempRel.getNode1(), tempRel.getPropositionNode1());
			keywordProList2.add(pNode1);
			KeyWordProposition pNode2=new KeyWordProposition(tempRel.getNode2(), tempRel.getPropositionNode1());
			keywordProList2.add(pNode2);
		}
		List<relationNodes> outputRelNodesList=new ArrayList<>();
		for (int i = 0; i < keywordProList1.size(); i++) {
			KeyWordProposition node1=keywordProList1.get(i);
			for (int j = 0; j < keywordProList2.size(); j++) {
				KeyWordProposition node2=keywordProList2.get(j);
				if (node1.getProposition().isContain(node2.getProposition())==1) { // can phai check voi isContain Full
					relationNodes relNodes= new relationNodes(node1.getKeyword(), node2.getKeyword(), node1.getProposition(), node2.getProposition());
					//outputRelNodesList.add(relNodes);					
					if (relNodes.isExistInList(pRelResouceList)!=1) {
						result.add(relNodes);
					}
				}
			}
		}

		return result;
	}

	public List<relationNodes> updateRelationNodeListByFoundResource_bk(List<relationNodes> pRelResouceList, List<relationNodes> pOriginalRelList){
		List<relationNodes> result=new ArrayList<>();
		List<relationNodes> newRelResourceList=new ArrayList<>();
		// Xuat hien trong resource, nhung ko xuat hien trong Original List (e26-e2 -> moi quan he gian tiep)
		for (int i = 0; i < pRelResouceList.size() ; i++) {
			relationNodes pTempRel=pRelResouceList.get(i);
			if (pTempRel.isExistInList(pOriginalRelList)!=1) {
				newRelResourceList.add(pTempRel);
			}
		}
		// Da extract duoc (e2-e25), (e10-e15), gia su e10-e15 co the suy luan duoc tu*` danh sach da~ co', can reduce e10-e15
		// Lay tat cac cac e2, e25,... cac cac prepositions
		
		//List<extractedEventProposition> eventPropositionList=new ArrayList<>(); 
		List<KeyWordProposition> keywordProList1=new ArrayList<>();
		for (int i = 0; i < newRelResourceList.size(); i++) {
			relationNodes tempRelNodesA=newRelResourceList.get(i);
			KeyWord node1=tempRelNodesA.getNode1(); // cac node nay khong co gia tri (troi oi, mat thoi gian cho viec kiem tra la.i)
			KeyWord node2=tempRelNodesA.getNode2(); // cac node nay khong co gia tri
			for (int j = 0; j < pOriginalRelList.size(); j++) {
				relationNodes tempRelNodesB=pOriginalRelList.get(j);
				if (tempRelNodesB.isNodeIN(node1)==1) {
					Proposition pPro=tempRelNodesB.getPropositionNode1();
					//KeyWordProposition tempKeyword=new KeyWordProposition(tempRelNodesB.getNode1(), pPro);
					//KeyWordProposition tempKeyword=new KeyWordProposition(node1, pPro); // update on 16 June they the node1 = tempRelNodesB.nodes1
					//KeyWordProposition tempKeyword=new KeyWordProposition(node1, pPro);
					//keywordProList1.add(tempKeyword);
					KeyWordProposition tempKeyword;
					if (tempRelNodesB.getNode1().getEventName4().contains(node1.getEventName4())) {
						tempKeyword=new KeyWordProposition(tempRelNodesB.getNode1(), pPro);
					}else{
						tempKeyword=new KeyWordProposition(tempRelNodesB.getNode2(), pPro);
					}
					keywordProList1.add(tempKeyword);
					
				}
				if (tempRelNodesB.isNodeIN(node2)==1) {
					Proposition pPro=tempRelNodesB.getPropositionNode1();
					//KeyWordProposition tempKeyword=new KeyWordProposition(tempRelNodesB.getNode2(), pPro);
					KeyWordProposition tempKeyword;
					if (tempRelNodesB.getNode1().getEventName4().contains(node2.getEventName4())) {
						tempKeyword=new KeyWordProposition(tempRelNodesB.getNode1(), pPro);
					}else{
						tempKeyword=new KeyWordProposition(tempRelNodesB.getNode2(), pPro);
					}
					keywordProList1.add(tempKeyword);
				}
			}
		}
		// Return gia tri
		// e2 ("President Bush", "has approved", "duty-free treatment")  // phan nay se duoc lua cho.n sau
		// e25 ....
		// e2 The white house said ....
		// Lua chon cac identity relattion list, de ta.o reference (e26=e33) --> can lay e33
		List<relationNodes> identityRelList=new ArrayList<>();
		for (int i = 0; i < pOriginalRelList.size(); i++) {
			relationNodes tempRel=pOriginalRelList.get(i);
			if (tempRel.isIdentity()==1) {
				identityRelList.add(tempRel);
			}
		}
		List<KeyWordProposition> keywordProList2=new ArrayList<>();
		for (int i = 0; i < identityRelList.size(); i++) {
			relationNodes tempRel=identityRelList.get(i);
			KeyWordProposition pNode1=new KeyWordProposition(tempRel.getNode1(), tempRel.getPropositionNode1());
			keywordProList2.add(pNode1);
			KeyWordProposition pNode2=new KeyWordProposition(tempRel.getNode2(), tempRel.getPropositionNode1());
			keywordProList2.add(pNode2);
		}
		List<relationNodes> outputRelNodesList=new ArrayList<>();
		for (int i = 0; i < keywordProList1.size(); i++) {
			KeyWordProposition node1=keywordProList1.get(i);
			for (int j = 0; j < keywordProList2.size(); j++) {
				KeyWordProposition node2=keywordProList2.get(j);
				if (node1.getProposition().isContain(node2.getProposition())==1) { // can phai check voi isContain Full
					relationNodes relNodes= new relationNodes(node1.getKeyword(), node2.getKeyword(), node1.getProposition(), node2.getProposition());
					outputRelNodesList.add(relNodes);
					//result.add(relNodes);
				}
			}
		}
		// Update on 27 June 2018
		for (int i = 0; i < outputRelNodesList.size(); i++) {
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@");
			System.out.println(outputRelNodesList.get(i).toString());
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@");
		}
		
		
		for (int i = 0; i < pRelResouceList.size(); i++) {
			relationNodes tempRelRe=pRelResouceList.get(i);
			
			String Label1="";
			if (tempRelRe.Node1.isEvent()==1) {
				Label1=tempRelRe.Node1.getEventName4();
			}else{
				Label1=tempRelRe.Node1.getTimeName7();
			}
			
			String Label2="";
			if (tempRelRe.Node2.isEvent()==1) {
				Label2=tempRelRe.Node2.getEventName4();
			}else{
				Label2=tempRelRe.Node2.getTimeName7();
			}
			
			for (int j = 0; j < outputRelNodesList.size(); j++) {
				relationNodes tempNodes=outputRelNodesList.get(j);
				if (tempNodes.isFound(Label1, Label2)==1) {
					result.add(tempNodes);
					System.out.println("-----------------------------------");
					System.out.println(tempNodes.toString());
					System.out.println("-----------------------------------");
				}
				
			}
			
		}
		for (int i = 0; i < result.size(); i++) {
			System.out.println("#############################");
			System.out.println(result.get(i).toString());
			System.out.println("#############################");
		}
		return result;
	}	

	
	public void addRelList(List<relationNodes> pRelList){
		for (int i = 0; i < pRelList.size(); i++) {
			relationNodes tempRel=pRelList.get(i);
			if (tempRel.isExistInList(this.relNodesList)!=1) {
				this.relNodesList.add(tempRel);
			}
		}
	} 
	
	// update on 5 July
	public void updateRelationNodeListByFoundResource_extra(List<relationNodes> pRelResouceList){
		
		List<relationNodes> result=new ArrayList<>();
		List<extractedPropositionOIE5> extractedPropositionOIE5List=new ArrayList<>();
		for (int i = 0; i < this.pOIE5Sentences.size(); i++) {
			oie5Sentence temp=pOIE5Sentences.get(i);
			List<patternOIE5> patternList=temp.getPatternList();
			List<extractedPropositionOIE5> exPropList=temp.getExtractedPropositionOIE5List();
			for (int j = 0; j < exPropList.size(); j++) {
				extractedPropositionOIE5 tempExProp=exPropList.get(j);
				extractedPropositionOIE5List.add(tempExProp);
			}	
		}

		for (int i = 0; i < pRelResouceList.size(); i++) {
			relationNodes pRelRes=pRelResouceList.get(i);
			String nodelabel1=pRelRes.getLabel1();
			String nodelabel2=pRelRes.getLabel2();
		
			for (int j = 0; j < extractedPropositionOIE5List.size(); j++) {
				extractedPropositionOIE5 tempExPro=extractedPropositionOIE5List.get(j);
				//String rel=tempExPro.getProposition().relation();
				String rel=tempExPro.getPattern().rel;
				//if (tempExPro.isExist(pRelRes)==1&&rel.indexOf("said")==-1) { // Can phai thay the boi 2 events extaction phu hop
				//remove &&rel.indexOf("said")==-1, 25 July 2018
				if (tempExPro.isExist(pRelRes)==1) {
					relationNodes temp=new relationNodes();
					for (int k = 0; k < tempExPro.getArgumentList().size(); k++) {
						KeyWord node=tempExPro.getArgumentList().get(k);
						String label=node.getLabel().toLowerCase();
						if (label.equals(nodelabel1)) {
							temp.setNode1(node);
						}
						if (label.equals(nodelabel2)) {
							temp.setNode2(node);
						}
					}
					
					temp.setPropositionNode1WithOIE5(tempExPro.getPattern());
					temp.setPropositionNode2WithOIE5(tempExPro.getPattern());
					if (temp.isExist(result)!=1) {
						result.add(temp);
					}
				
				}
			}
		}
		
		for (int i = 0; i < result.size(); i++) {
			relationNodes temp=result.get(i);
			if (temp.isExist(this.relNodesList)!=1) {
				this.relNodesList.add(temp);
			}
		}
	}	
	
	// update on 11 July
	// For Time-Time relation
	public List<relationNodes> loadTimeTimeRelList(List<relationNodes> pFinalRelList, List<relationNodes> _relResouceList, List<KeyWordPropositionOIE5> _keyWordEventsList){
		List<relationNodes> result = new ArrayList<>();
		List<relationNodes> twoTmxRelList=new ArrayList<>();
		for (int i = 0; i < _relResouceList.size(); i++) {
			relationNodes pRelNodes=_relResouceList.get(i);
			String node1txt=pRelNodes.getNode1().getTimeName7();
			String node2txt=pRelNodes.getNode2().getTimeName7();
			if ((node1txt.indexOf("tmx")!=-1&&!node1txt.contains("tmx0"))
				&&(node2txt.indexOf("tmx")!=-1&&!node2txt.contains("tmx0")) 	
				) {
				twoTmxRelList.add(pRelNodes);
				//System.out.println(pRelNodes.toString());
			}
		}
		for (int i = 0; i < twoTmxRelList.size(); i++) {
			relationNodes pTimeTimeNodes=twoTmxRelList.get(i);
			if (pTimeTimeNodes.isExistInList(pFinalRelList)!=1) {
				String node1txt="";
				String node2txt="";
				KeyWord node1=pTimeTimeNodes.getNode1();
				KeyWord node2=pTimeTimeNodes.getNode2();
				node1txt=node1.getTimeName7();
				node2txt=node2.getTimeName7();
				
				relationNodes pRel=new relationNodes();
				KeyWord pKeyWordTime1= new KeyWord();
				KeyWord pKeyWordTime2= new KeyWord();
				
				for (int j = 0; j < _keyWordEventsList.size(); j++) {
					
					KeyWord pKeyWordTemp=_keyWordEventsList.get(j).getKeyword();
					
					if (pKeyWordTemp.getLabel().contains(node1txt)) {
						pKeyWordTime1=pKeyWordTemp;
						pRel.setNode1(pKeyWordTemp);
						pRel.setPropositionNode1WithOIE5(_keyWordEventsList.get(j).getProposition());
					}
					if (pKeyWordTemp.getLabel().contains(node2txt)) {
						pKeyWordTime2=pKeyWordTemp;
						pRel.setNode2(pKeyWordTemp);
						pRel.setPropositionNode2WithOIE5(_keyWordEventsList.get(j).getProposition());
					}
					
				}
				result.add(pRel);
				//System.out.println(pRel.toString());
			}
		}
		return result;
	}
	// Update on 30 May 
	// Update on 16 July
	public List<relationNodes> loadEquaRelList(List<KeyWordProposition> _keyWordEventsList){
		List<relationNodes> result = new ArrayList<>();

		relationNodes initialNode=new relationNodes();
		
		System.out.println("----------------------------");
		System.out.println("Equal events in documents"); // xu ly cac equal events
		List<relationNodes> equalRelEvents=initialNode.relGeneraitionByKeyWordContent(_keyWordEventsList);
		System.out.println(equalRelEvents.size());
		for (int i = 0; i < equalRelEvents.size(); i++) {
			System.out.println(equalRelEvents.get(i).toString());
			result.add(equalRelEvents.get(i));
		}
		System.out.println("----------------------------");
		return result;
	}

	public List<relationNodes> loadEquaRelListOIE5(List<KeyWordPropositionOIE5> _keyWordEventsList){
		List<relationNodes> result = new ArrayList<>();

		relationNodes initialNode=new relationNodes();
		
		System.out.println("----------------------------");
		System.out.println("Equal events in documents"); // xu ly cac equal events
		List<relationNodes> equalRelEvents=initialNode.relGeneraitionByKeyWordContentOIE5(_keyWordEventsList);
		System.out.println(equalRelEvents.size());
		for (int i = 0; i < equalRelEvents.size(); i++) {
			System.out.println(equalRelEvents.get(i).toString());
			result.add(equalRelEvents.get(i));
		}
		System.out.println("----------------------------");
		return result;
	}
	
	// ---Update on 16 July
	
	public static void main(String args[]) throws IOException{
		//WSJ_20130318_731.col
		String inputFileName = "data/tdata/col_files/wsj_0144.col";
		String colID="wsj_0144.col";
		//String inputResouce="data//tdata//TempEval3_wsj0026.txt";
		//String inputFileName = "data/tdata/wsj_0321.col";
		//String inputResouce="data//tdata//TempEval3_wsj0321.txt";
		//String inputFileName = "data/tdata/col_files/wsj_0172.col";
		//String colID="wsj_0172.col";
		//String inputFileName = "data/tdata/wsj_0815.col";
		//String inputResouce="data//tdata//TempEval3_wsj0815.txt";
		// read data from file and write into List by sentence. 
		// Every line is a sentence and every word of sentence is a KeyWord
		
		// Load resource
		String inputResouce="data//tdata//TempEval3_test.TLINK";
		BufferedReader br = new BufferedReader(new FileReader(inputResouce));
		String sentence = null;
		List<String> allSourceRecord=new ArrayList<>(); 
		while((sentence = br.readLine()) != null){
			allSourceRecord.add(sentence);
		}
		br.close();
		//--load recource
		
		List<Sentence> sentenceList = new ArrayList<Sentence>();
		DataWriter 		Writer      = new DataWriter();
		sentenceList = Writer.writeSentenceList(inputFileName); 

		List<oieSentence> oieSentenceList=new ArrayList<>();

		// Sentence 0 for tmx0 processing
		for (int i = 1; i < sentenceList.size(); i++) { // Starting from sentence 1, sentence 0 la da`nh cho tmx0
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
		
		// for updated relation List
 		relationNodes initialNode=new relationNodes();
		List<relationNodes> relResourceList=initialNode.loadSource(allSourceRecord, colID);
		List<relationNodes> relOriginalList=_relationNodes.getRelNodesList();
		List<relationNodes> updateRelList=new ArrayList<>();
		updateRelList=_relationNodes.updateRelationNodeListByFoundResource(relResourceList, relOriginalList);
		_relationNodes.addRelList(updateRelList);
		
		// Load Time-Time event list
		Sentence sen0=sentenceList.get(0);
		//
		KeyWord keywordTMX0=sen0.sentence.get(0);
		System.out.println(keywordTMX0.getTimeValue9());
		//
		List<KeyWordProposition> keyWordEventsList= new ArrayList<>();
		keyWordEventsList=oieSentenceList.get(0).loadAllKeyWordPropositionByOIESentences(oieSentenceList);
		List<relationNodes> TimeTimeList=_relationNodes.loadTimeTimeRelList(_relationNodes.getRelNodesList(), relResourceList, keyWordEventsList);
		for (int i = 0; i < TimeTimeList.size(); i++) {
			System.out.println(TimeTimeList.get(i).toString());
		}
		System.out.println();
		//
		// update on 5 July
		_relationNodes.updateRelationNodeListByFoundResource_extra(relResourceList);
		//
		_relationNodes.optimizeRelList();
		
		List<relationNodes> finalRelList= new ArrayList<>();
		finalRelList=_relationNodes.getRelNodesList();
		System.out.println("--------------------Final relation nodes------------------------");
		for (int i = 0; i < finalRelList.size(); i++) {
			relationNodes relNodes=finalRelList.get(i);
			relNodes.setTenseTime(sentenceList);
			System.out.println(relNodes.toString());
			System.out.print(relNodes.tenseNode1);
			System.out.print("   ");
			System.out.print(relNodes.timeNode1);
			System.out.println();
		}
	}
	
	// Update on 2 March 2019
	// List extracted relations from OIE sentences, golden causal relations
	// Check OIE relations co' cac yeu to cua golden causal relations
	
	public List<relationNodes> updateRelationNodeListByCausalResource(List<relationNodes> pCausalList, List<relationNodes> pOriginalRelList){
		List<relationNodes> result=new ArrayList<>();
		List<relationNodes> newRelResourceList=new ArrayList<>();
		
		for (int i = 0; i < pOriginalRelList.size() ; i++) {
			relationNodes pTempRel=pOriginalRelList.get(i);
			if (pTempRel.isExistInListOnly1KeyWordCausal(pCausalList)==1) {
				newRelResourceList.add(pTempRel);
			}
		}
		return newRelResourceList;
	}
	
}

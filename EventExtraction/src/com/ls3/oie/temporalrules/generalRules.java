package com.ls3.oie.temporalrules;

import java.util.List;

import org.graphstream.graph.Path;

import com.ls3.oie.temporalmodel.KeyWord;
import com.ls3.oie.temporalmodel.Sentence;
import com.ls3.oie.temporalmodel.relationNodes;

import edu.stanford.nlp.util.TwoDimensionalMap;

public class generalRules {

	public static String twoPaths(String path1, String path2){
		String result="NONE";
		
		// for BEFORE
		if (path1.equals("BEFORE")&&path2.equals("BEFORE")) {
			result="BEFORE";
		}else if(path1.equals("BEFORE")&&path2.equals("BEGINS")){
			result="BEFORE";
		}else if(path1.equals("BEFORE")&&path2.equals("BEGUN_BY")){
			result="BEFORE";
		}else if(path1.equals("BEFORE")&&path2.equals("ENDS")){
			result="BEFORE";
		}else if(path1.equals("BEFORE")&&path2.equals("INCLUDES")){
			result="INCLUDES";//result="BEFORE";
		}else if(path1.equals("BEFORE")&&path2.equals("IS_INCLUDED")){
			result="IS_INCLUDED";//result="BEFORE";
		}else if(path1.equals("BEFORE")&&path2.equals("IDENTITY")){ 
			result="IDENTITY/BEFORE"; 
		}else if(path1.equals("BEFORE")&&path2.equals("SIMULTANEOUS")){ 
			result="BEFORE"; 
		}else if(path1.equals("BEFORE")&&path2.equals("DURING")){ 
			result="BEFORE"; 
		}else if(path1.equals("BEFORE")&&path2.equals("AFTER")){ 
			result="BEFORE/AFTER"; 
		}
		// end BEFORE
		
		// for AFTER
		else if (path1.equals("AFTER")&&path2.equals("AFTER")) {
			result="AFTER";
		}else if(path1.equals("AFTER")&&path2.equals("BEGINS")){
			result="AFTER";
		}else if(path1.equals("AFTER")&&path2.equals("BEGUN_BY")){
			result="AFTER";
		}else if(path1.equals("AFTER")&&path2.equals("ENDS")){
			result="AFTER";
		}else if(path1.equals("AFTER")&&path2.equals("INCLUDES")){
			result="INCLUDES";//result="AFTER";
		}else if(path1.equals("AFTER")&&path2.equals("IS_INCLUDED")){
			result="IS_INCLUDED";//result="AFTER";
		}else if(path1.equals("AFTER")&&path2.equals("IDENTITY")){
			result="IDENTITY/AFTER";
		}else if(path1.equals("AFTER")&&path2.equals("SIMULTANEOUS")){ 
			result="AFTER"; 
		}else if(path1.equals("AFTER")&&path2.equals("DURING")){ 
			result="AFTER"; 
		}else if(path1.equals("AFTER")&&path2.equals("BEFORE")){ 
			result="AFTER/BEFORE"; 
		}
		// end AFTER
		
		// for BEGINS
		else if (path1.equals("BEGINS")&&path2.equals("AFTER")) {
			result="AFTER";
		}else if(path1.equals("BEGINS")&&path2.equals("BEFORE")){
			result="BEFORE";
		}else if(path1.equals("BEGINS")&&path2.equals("BEGINS")){
			result="AFTER";
		}else if(path1.equals("BEGINS")&&path2.equals("BEGUN_BY")){
			result="BEGUN_BY";
		}else if(path1.equals("BEGINS")&&path2.equals("ENDS")){
			result="ENDS";
		}else if(path1.equals("BEGINS")&&path2.equals("INCLUDES")){
			result="BEGINS";
		}else if(path1.equals("BEGINS")&&path2.equals("IS_INCLUDED")){
			result="BEGINS";
		}else if(path1.equals("BEGINS")&&path2.equals("IDENTITY")){
			result="BEGINS";
		}else if(path1.equals("BEGINS")&&path2.equals("SIMULTANEOUS")){ 
			result="BEGINS"; 
		}else if(path1.equals("BEGINS")&&path2.equals("DURING")){ 
			result="AFTER"; 
		}// end BEGINS		
		
		// for BEGUN_BY
		else if (path1.equals("BEGUN_BY")&&path2.equals("AFTER")) {
			result="AFTER";
		}else if(path1.equals("BEGUN_BY")&&path2.equals("BEFORE")){
			result="BEFORE";
		}else if(path1.equals("BEGUN_BY")&&path2.equals("BEGINS")){
			result="BEGUN_BY";
		}else if(path1.equals("BEGUN_BY")&&path2.equals("BEGUN_BY")){
			result="BEGUN_BY";
		}else if(path1.equals("BEGUN_BY")&&path2.equals("ENDS")){
			result="ENDS";
		}else if(path1.equals("BEGUN_BY")&&path2.equals("INCLUDES")){
			result="BEGUN_BY";
		}else if(path1.equals("BEGUN_BY")&&path2.equals("IS_INCLUDED")){
			result="BEGUN_BY";
		}else if(path1.equals("BEGUN_BY")&&path2.equals("IDENTITY")){
			result="BEGUN_BY";
		}else if(path1.equals("BEGUN_BY")&&path2.equals("SIMULTANEOUS")){ 
			result="BEGUN_BY"; 
		}else if(path1.equals("BEGUN_BY")&&path2.equals("DURING")){ 
			result="BEGUN_BY"; 
		}// end BEGUN_BY		
		
		// for ENDS
		else if (path1.equals("ENDS")&&path2.equals("AFTER")) {
			result="AFTER";
		}else if(path1.equals("ENDS")&&path2.equals("BEFORE")){
			result="BEFORE";
		}else if(path1.equals("ENDS")&&path2.equals("BEGINS")){
			result="ENDS";
		}else if(path1.equals("ENDS")&&path2.equals("BEGUN_BY")){
			result="ENDS";
		}else if(path1.equals("ENDS")&&path2.equals("ENDS")){
			result="ENDS";
		}else if(path1.equals("ENDS")&&path2.equals("INCLUDES")){
			result="ENDS";
		}else if(path1.equals("ENDS")&&path2.equals("IS_INCLUDED")){
			result="ENDS";
		}else if(path1.equals("ENDS")&&path2.equals("IDENTITY")){
			result="ENDS";
		}else if(path1.equals("ENDS")&&path2.equals("SIMULTANEOUS")){ 
			result="ENDS"; 
		}else if(path1.equals("ENDS")&&path2.equals("DURING")){ 
			result="ENDS"; 
		}// end ENDS		

		// for INCLUDES
		else if (path1.equals("INCLUDES")&&path2.equals("AFTER")) {
			result="INCLUDES";//result="AFTER"; update 19 August
		}else if(path1.equals("INCLUDES")&&path2.equals("BEFORE")){
			result="INCLUDES";//result="BEFORE"; update 19 August
		}else if(path1.equals("INCLUDES")&&path2.equals("BEGINS")){
			result="BEGINS";
		}else if(path1.equals("INCLUDES")&&path2.equals("BEGUN_BY")){
			result="BEGUN_BY";
		}else if(path1.equals("INCLUDES")&&path2.equals("ENDS")){
			result="ENDS";
		}else if(path1.equals("INCLUDES")&&path2.equals("INCLUDES")){
			result="INCLUDES";
		}else if(path1.equals("INCLUDES")&&path2.equals("IS_INCLUDED")){
			result="IS_INCLUDED";
		}else if(path1.equals("INCLUDES")&&path2.equals("IDENTITY")){
			result="INCLUDES";
		}else if(path1.equals("INCLUDES")&&path2.equals("SIMULTANEOUS")){ 
			result="SIMULTANEOUS"; 
		}else if(path1.equals("INCLUDES")&&path2.equals("DURING")){ 
			result="DURING"; 
		}// end INCLUDES		

		// for IS_INCLUDED
		else if (path1.equals("IS_INCLUDED")&&path2.equals("AFTER")) {
			result="AFTER";
		}else if(path1.equals("IS_INCLUDED")&&path2.equals("BEFORE")){
			result="BEFORE";
		}else if(path1.equals("IS_INCLUDED")&&path2.equals("BEGINS")){
			result="BEGINS";
		}else if(path1.equals("IS_INCLUDED")&&path2.equals("BEGUN_BY")){
			result="BEGUN_BY";
		}else if(path1.equals("IS_INCLUDED")&&path2.equals("ENDS")){
			result="ENDS";
		}else if(path1.equals("IS_INCLUDED")&&path2.equals("INCLUDES")){
			result="INCLUDES";
		}else if(path1.equals("IS_INCLUDED")&&path2.equals("IS_INCLUDED")){
			result="IS_INCLUDED";
		}else if(path1.equals("IS_INCLUDED")&&path2.equals("IDENTITY")){
			result="IS_INCLUDED";
		}else if(path1.equals("IS_INCLUDED")&&path2.equals("SIMULTANEOUS")){ 
			result="SIMULTANEOUS"; 
		}else if(path1.equals("IS_INCLUDED")&&path2.equals("DURING")){ 
			result="DURING"; 
		}// end IS_INCLUDED		
		// for IDENTITY
		else if (path1.equals("IDENTITY")&&path2.equals("AFTER")) {
			result="AFTER";
		}else if(path1.equals("IDENTITY")&&path2.equals("BEFORE")){
			result="BEFORE";
		}else if(path1.equals("IDENTITY")&&path2.equals("BEGINS")){
			result="BEGINS";
		}else if(path1.equals("IDENTITY")&&path2.equals("BEGUN_BY")){
			result="BEGUN_BY";
		}else if(path1.equals("IDENTITY")&&path2.equals("ENDS")){
			result="ENDS";
		}else if(path1.equals("IDENTITY")&&path2.equals("INCLUDES")){
			result="INCLUDES";
		}else if(path1.equals("IDENTITY")&&path2.equals("IS_INCLUDED")){
			result="IS_INCLUDED";
		}else if(path1.equals("IDENTITY")&&path2.equals("IDENTITY")){
			result="IDENTITY";
		}else if(path1.equals("IDENTITY")&&path2.equals("SIMULTANEOUS")){ 
			result="SIMULTANEOUS"; 
		}else if(path1.equals("IDENTITY")&&path2.equals("DURING")){ 
			result="DURING"; 
		}else if(path1.equals("SIMULTANEOUS/IDENTITY")&&path2.equals("IS_INCLUDED")){ 
			result="IS_INCLUDED"; 
		}
		// end IDENTITY		
		
		return result;
	}
	
	// Update on 17 March
	public static String twoPathsCausal(String path1, String path2){
		String result="NONE";
		// for BEFORE
		if (path1.equals("IDENTITY")&&path2.equals("CLINK")) {
			result="CLINK";
		}else if(path1.equals("IS_INCLUDED")&&path2.equals("CLINK")){
			result="CLINK";
		}else if(path1.equals("INCLUDES")&&path2.equals("CLINK")){
			result="CLINK";
		}else if(path1.equals("SIMULTANEOUS")&&path2.equals("CLINK")){
			result="CLINK";
		}else if(path1.equals("BEGINS")&&path2.equals("CLINK")){
			result="CLINK";
		}else if(path1.equals("ENDS")&&path2.equals("CLINK")){
			result="CLINK";
		}else if(path1.equals("DURING")&&path2.equals("CLINK")){ 
			result="CLINK"; 
		}else if (path1.equals("IDENTITY")&&path2.equals("CLINK-C")) {
			result="CLINK-C";
		}else if(path1.equals("IS_INCLUDED")&&path2.equals("CLINK-C")){
			result="CLINK-C";
		}else if(path1.equals("INCLUDES")&&path2.equals("CLINK-C")){
			result="CLINK-C";
		}else if(path1.equals("SIMULTANEOUS")&&path2.equals("CLINK-C")){
			result="CLINK-C";
		}else if(path1.equals("BEGINS")&&path2.equals("CLINK-C")){
			result="CLINK-C";
		}else if(path1.equals("ENDS")&&path2.equals("CLINK-C")){
			result="CLINK-C";
		}else if(path1.equals("DURING")&&path2.equals("CLINK-C")){ 
			result="CLINK-C"; 
		}
		else if (path1.equals("CLINK")&&path2.equals("IDENTITY")) {
			result="CLINK";
		}else if(path1.equals("CLINK")&&path2.equals("IS_INCLUDED")){
			result="CLINK";
		}else if(path1.equals("INCLINKCLUDES")&&path2.equals("INCLUDES")){
			result="CLINK";
		}else if(path1.equals("CLINK")&&path2.equals("SIMULTANEOUS")){
			result="CLINK";
		}else if(path1.equals("CLINK")&&path2.equals("BEGINS")){
			result="CLINK";
		}else if(path1.equals("CLINK")&&path2.equals("ENDS")){
			result="CLINK";
		}else if(path1.equals("CLINK")&&path2.equals("DURING")){ 
			result="CLINK"; 
		}else if (path1.equals("CLINK-C")&&path2.equals("IDENTITY")) {
			result="CLINK-C";
		}else if(path1.equals("CLINK-C")&&path2.equals("IS_INCLUDED")){
			result="CLINK-C";
		}else if(path1.equals("CLINK-C")&&path2.equals("INCLUDES")){
			result="CLINK-C";
		}else if(path1.equals("CLINK-C")&&path2.equals("SIMULTANEOUS")){
			result="CLINK-C";
		}else if(path1.equals("CLINK-C")&&path2.equals("BEGINS")){
			result="CLINK-C";
		}else if(path1.equals("CLINK-C")&&path2.equals("ENDS")){
			result="CLINK-C";
		}else if(path1.equals("CLINK-C")&&path2.equals("CLINK-C")){ 
			result="CLINK-C"; 
		}else if(path1.equals("CLINK")&&path2.equals("CLINK")){ 
			result="CLINK"; 
		}
		
		// end BEFORE
		return result;
	}
	
	public static String twoNodesRelation(relationNodes twoNodeRelation, List<Sentence> _sentenceList){
		String result="NONE";
		if (result.equals("NONE")&&twoNodeRelation.isIdentity()==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 0");
			//result="IDENTIFY";
		}else if (result.equals("NONE")&&twoNodeRelation.isEventPriorityException(_sentenceList)==1) { // update on 24 September
			result=twoNodeRelation.getRelaionType();		
			System.out.println("No. 0a");
			//Event V as N ==> SIMULTANEOUS;
		}else if (result.equals("NONE")&&twoNodeRelation.isVerbToVerb(_sentenceList)==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 1a");
			//result="Verb to Verb";
		}else if (result.equals("NONE")&&twoNodeRelation.isEventNN_PP_EventNN(_sentenceList)==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 1b");
			//result="AFTER";
		}else if (result.equals("NONE")&&twoNodeRelation.isEventVRel_EventVArgument()==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 2");
			//result="AFTER";
		}else if (result.equals("NONE")&&twoNodeRelation.isEventNSub_EventVRel()==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 3");
			//result="BEGUN";
		}else if (result.equals("NONE")&&twoNodeRelation.isEventNSub_EventVArgument()==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 4");
			//result="AFTER";
		}else if (result.equals("NONE")&&twoNodeRelation.isEventVRel_EventNArgument()==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 5, noted: should be double checked");
			//result="AFTER";
		}else if (result.equals("NONE")&&twoNodeRelation.isEqualEvents()==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 6");
			//result="IDENTITY";
		}else if (result.equals("NONE")&&twoNodeRelation.isEventEdentityVerbRel_EventNArgument()==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 7");
			//result="BEFORE";
		}else if (result.equals("NONE")&&twoNodeRelation.isEventVArg_EventNArg(_sentenceList)==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 7.1");
			//result="BEFORE";
		}else if (result.equals("NONE")&&twoNodeRelation.isEventSub_EventV()==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 7.2");
			//result="BEFORE";
		}else if (result.equals("NONE")&&twoNodeRelation.isFirstEventwithBYsecondEvent(_sentenceList)==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 7.3");
			//result="AFTER";
		}else if (result.equals("NONE")&&twoNodeRelation.isEventS_EventFutureV()==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 7.4");
			//result="BEFORE/AFTER";
		}else if (result.equals("NONE")&&twoNodeRelation.isDeclaredEvent()==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 7.5");
			//DECLARED rules;
		}else if (result.equals("NONE")&&twoNodeRelation.isIssueEvent()==1) {
			result=twoNodeRelation.getRelaionType();		
			System.out.println("No. 7.6, noted: double check");
			//ISSUE rules;
		}else if (result.equals("NONE")&&twoNodeRelation.isAfterEvent(_sentenceList)==1) {
			result=twoNodeRelation.getRelaionType();		
			System.out.println("No. 7.7");
			//AFTER event;
		}else if (result.equals("NONE")&&twoNodeRelation.isBeforeEvent(_sentenceList)==1) {
			result=twoNodeRelation.getRelaionType();		
			System.out.println("No. 7.8");
			//BEFORE event;
		}else if (result.equals("NONE")&&twoNodeRelation.isPredictSIMEvent()==1) {
			result=twoNodeRelation.getRelaionType();		
			System.out.println("No. 7.9");
			//PREDICT event;
		}
		else if (result.equals("NONE")&&twoNodeRelation.isLEADEvent()==1) { // updated on 30 July
			result=twoNodeRelation.getRelaionType();		
			System.out.println("No. 7.91");
			//LEAD event;
		}else if (result.equals("NONE")&&twoNodeRelation.isASSOONASEvent()==1) {
			result=twoNodeRelation.getRelaionType();		
			System.out.println("7.92");
			//As soon as event;
		}else if (result.equals("NONE")&&twoNodeRelation.isApplyTime(_sentenceList)==1) {
			result=twoNodeRelation.getRelaionType();		
			System.out.println("No. 7.93, noted: move to top condition");
			//Apply Time;
		}else if (result.equals("NONE")&&twoNodeRelation.isPossibilityEvent(_sentenceList)==1) {
			result=twoNodeRelation.getRelaionType();		
			System.out.println("No. 7.94");
			//As soon as event;
		}else if (result.equals("NONE")&&twoNodeRelation.isEventAtEvent(_sentenceList)==1) { // update on 31 July
			result=twoNodeRelation.getRelaionType();		
			System.out.println("No. 7.95");
			//As event at other event;
		}else if (result.equals("NONE")&&twoNodeRelation.isVerbByVerb(_sentenceList)==1) { // update on 16 August
			result=twoNodeRelation.getRelaionType();		
			System.out.println("No. 7.96");
			//doing by watching ==>SIMULTANEOUS;
		}else if (result.equals("NONE")&&twoNodeRelation.isVerbWithVerb(_sentenceList)==1) { // update on 17 August
			result=twoNodeRelation.getRelaionType();		
			System.out.println("No. 7.97");
			//has denied accepting ==> BEFORE/AFTER;
		}else if (result.equals("NONE")&&twoNodeRelation.isVerbInfinitive(_sentenceList)==1) { // update on 17 August
			result=twoNodeRelation.getRelaionType();		
			System.out.println("No. 7.98");
			//V1 to V2 ==> BEFORE/AFTER;
		}else if (result.equals("NONE")&&twoNodeRelation.isEventForEvent(_sentenceList)==1) { // update on 20 August
			result=twoNodeRelation.getRelaionType();		
			System.out.println("No. 7.99");
			//N1 for N2 ==> SIMULTANEOUS;
		}else if (result.equals("NONE")&&twoNodeRelation.isEventPossisive(_sentenceList)==1) { // update on 22 August
			result=twoNodeRelation.getRelaionType();		
			System.out.println("No. 7.99a");
			//Event possesive ==> IS_INCLUDES;
		}else if (result.equals("NONE")&&twoNodeRelation.isEventWithAS(_sentenceList)==1) { // update on 22 August
			result=twoNodeRelation.getRelaionType();		
			System.out.println("No. 7.99b");
			//Event V as N ==> SIMULTANEOUS;
		}
		else if (result.equals("NONE")&&twoNodeRelation.isEventException(_sentenceList)==1) { // update on 22 August
			result=twoNodeRelation.getRelaionType();		
			System.out.println("No. 100a");
			//Event V as N ==> SIMULTANEOUS;
		}
		//isEventException
		else if (result.equals("NONE")&&twoNodeRelation.isEventV_EventVCausalAtFinal()==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 100b");
			//result="BEFORE";
			//result="AFTER";
		}
		//isPossibilityEvent
		return result;
	}
	
	// Update on 15 March 2019
	public static String twoNodesCausalRelation(relationNodes twoNodeRelation, List<Sentence> _sentenceList){
		String result="NONE";
		if (result.equals("NONE")&&twoNodeRelation.isThis_Causal(_sentenceList)==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 1c");
			//result="IDENTIFY";
		}else if (result.equals("NONE")&&twoNodeRelation.isBECAUSEOF_Causal(_sentenceList)==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 2c-a");
			//result="IDENTIFY";
		}else if (result.equals("NONE")&&twoNodeRelation.isBECAUSE_Causal(_sentenceList)==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 2c-b");
			//result="IDENTIFY";
		}else if (result.equals("NONE")&&twoNodeRelation.isCAUSE_Causal(_sentenceList)==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 3c");
			//result="IDENTIFY";
		}else if (result.equals("NONE")&&twoNodeRelation.isBY_Causal(_sentenceList)==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 4c");
			//result="IDENTIFY";
		}else if (result.equals("NONE")&&twoNodeRelation.isLEAD_Causal(_sentenceList)==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 5c");
			//result="IDENTIFY";
		}else if (result.equals("NONE")&&twoNodeRelation.isWillCAUSE_Causal(_sentenceList)==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 6c");
			//result="IDENTIFY";
		}else if (result.equals("NONE")&&twoNodeRelation.isAFTER_Causal(_sentenceList)==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 7c");
			//result="IDENTIFY";
		}else if (result.equals("NONE")&&twoNodeRelation.isFROM_Causal(_sentenceList)==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 8c");
			//result="IDENTIFY";
		}else if (result.equals("NONE")&&twoNodeRelation.isRESULT_Causal(_sentenceList)==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 9c");
			//result="IDENTIFY";
		}else if (result.equals("NONE")&&twoNodeRelation.isBEGIN_Causal(_sentenceList)==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 10c");
			//result="IDENTIFY";
		}else if (result.equals("NONE")&&twoNodeRelation.isRESPONSE_Causal(_sentenceList)==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 11c");
			//result="IDENTIFY";
		}else if (result.equals("NONE")&&twoNodeRelation.isLAUNCH_Causal(_sentenceList)==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 12c");
			//result="IDENTIFY";
		}else if (result.equals("NONE")&&twoNodeRelation.isLAST_Causal(_sentenceList)==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 13c");
		}else if (result.equals("NONE")&&twoNodeRelation.isSO_Causal(_sentenceList)==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 14c");
		}else if (result.equals("NONE")&&twoNodeRelation.isLOSS_Causal(_sentenceList)==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 15c");
		}else if (result.equals("NONE")&&twoNodeRelation.isFOLLOW_Causal(_sentenceList)==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 16c");
		}else if (result.equals("NONE")&&twoNodeRelation.isFOR_Causal(_sentenceList)==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 17c");
		}
		//isPossibilityEvent
		return result;
	}
	
	public static String twoNodesRelationEventTime(relationNodes twoNodeRelation, KeyWord pKeyWordTMX0, List<Sentence> _sentenceList){
		String result="NONE";
		//isfiscalYear
		
		if (result.equals("NONE")&&twoNodeRelation.isfiscalYear(_sentenceList)==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 8a");
			System.out.println(result);
			//Fiscal Year";
		}else if (result.equals("NONE")&&twoNodeRelation.isDurationTime(_sentenceList)==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 8b");
			System.out.println(result);
			//DURATION time -> IS_INCLUDED";
		}	
		//isEventBetweenTime	
		else if (result.equals("NONE")&&twoNodeRelation.isEventBetweenTime(_sentenceList)==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 8b-2");
			System.out.println(result);
			//Between time => BEGINS, END";
		}
		else if (result.equals("NONE")&&twoNodeRelation.isEvent_BeginTime(_sentenceList)==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 8c");
			System.out.println(result);
			//result="BEGUN";
		}else if (result.equals("NONE")&&twoNodeRelation.isEvent_ENDTime(_sentenceList)==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 9");
			System.out.println(result);
			//result="END/SIMULTANEOUS";
		}else if (result.equals("NONE")&&twoNodeRelation.isEvent_INTime(_sentenceList)==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 9.1");
			System.out.println(result);
			//result="IS_INCLUDED";
		}else if (result.equals("NONE")&&twoNodeRelation.isEventSetBeforeTime(_sentenceList)==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 11");
			System.out.println(result);
			//result="IS_INCLUDED";
		}else if (result.equals("NONE")&&twoNodeRelation.isEvent_NowTime()==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 12");
			System.out.println(result);
			//result="INCLUDES";
		}else if (result.equals("NONE")&&twoNodeRelation.isEventTimeSeg(_sentenceList)==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 13");
			System.out.println(result);
			//result="IS_INCLUDED";
		}else if (result.equals("NONE")&&twoNodeRelation.isEventRelTimeArg(_sentenceList)==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 14");
			System.out.println(result);
			//result="IS_INCLUDED";
		}else if (result.equals("NONE")&&twoNodeRelation.isTimeSubEventRel(_sentenceList)==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 15-luu y this rule");
			System.out.println(result);
			//result="IS_INCLUDED";
		}else if (result.equals("NONE")&&twoNodeRelation.isTimeWithIncludes(_sentenceList)==1) { // 30 Sept.
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 15b-luu y this rule");
			System.out.println(result);
			//result="INCLUDES";
		}
		//isTimeWithIncludes
		else if (result.equals("NONE")&&twoNodeRelation.isEventTimeWithINCVerb()==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 16");
			System.out.println(result);
			//result="IS_INCLUDED";
		}else if (result.equals("NONE")&&twoNodeRelation.isReportEvent()==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 17");
			System.out.println(result);
			//result="REPORTED Event";
		}else if (result.equals("NONE")&&twoNodeRelation.isDueToEvent()==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 18");
			System.out.println(result);
			//result="DUE TO Event";
		}else if (result.equals("NONE")&&twoNodeRelation.isTimePossisive(_sentenceList)==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 19");
			System.out.println(result);
			//result="Time Possive";
		}else if (result.equals("NONE")&&twoNodeRelation.isReferenceTimeTime(pKeyWordTMX0, _sentenceList)==1) {
			result=twoNodeRelation.getRelaionType();
			System.out.println("No. 20");
			System.out.println(result);
			//result="Time Possive";
		}
		//isReferenceTimeTime
		// Con thieu 1 so rule: BEFORE, ENDS, ....
		return result;
	}
	
	public static String twoNodesRelationTMX0(relationNodes twoNodeRelation, List<Sentence> _sentenceList){
		String result="NONE";
		if (result.equals("NONE")&&twoNodeRelation.isRelTMX0(_sentenceList)==1) {
			result=twoNodeRelation.getRelaionType();
		}
		return result;
	}

	public static String twoNodesRelationTimeTime(relationNodes twoNodeRelation, KeyWord pTMX0, List<Sentence> _sentenceList){
		String result="NONE";
		if (result.equals("NONE")&&twoNodeRelation.isfiscalYearTwoTime(_sentenceList)==1) {
			result=twoNodeRelation.getRelaionType();
			//Fiscal Year";
		}else if (result.equals("NONE")&&twoNodeRelation.isTimeTimeRel(pTMX0)==1) {
			result=twoNodeRelation.getRelaionType();
		}
		return result;
	}	
	
	
	public static String threeNodesRelation(List<String> originalPath, List<relationNodes> relPath, List<relationNodes> finalRelList, List<String> tenseRankingList, 
			                                List<Sentence> pSentenceList, KeyWord pKeyWordTMX0){
		String result="NONE";
		// Xu ly tense, time
		// then xu ly two path
		// Vi du A-C (trong path A-B-C)
		// Tinh toan AB va BC
		int pathSize=relPath.size();
		relationNodes relPath1=relPath.get(0);
		relationNodes relPath2=relPath.get(pathSize-1);
		// Can kiem tra AB(BA) va` BC(CB) da kiem tra phan nay
		// Check tense and time ranking-> phan nay chua xu ly
		
		// For fiscal year time
		if (relPath1.getLabel1().contains("tmx0")||relPath1.getLabel2().contains("tmx0")) {
			if (relPath1.isfiscalYear(pSentenceList)==1){
				System.out.println("FISCAL YEAR Found.");
				result="IS_INCLUDED";
			}
		}else if (relPath1.isfiscalYearTwoTime(pSentenceList)==1){
				System.out.println("FISCAL YEAR Found.");
				result="IS_INCLUDED";
		}
			
		if (relPath2.getLabel1().contains("tmx0")||relPath2.getLabel2().contains("tmx0")) {
			if (result.equals("NONE")&&relPath2.isfiscalYear(pSentenceList)==1){
				System.out.println("FISCAL YEAR Found.");
				result="IS_INCLUDED";
			}
		}else if (result.equals("NONE")&&relPath2.isfiscalYearTwoTime(pSentenceList)==1){
			System.out.println("FISCAL YEAR Found.");
			result="IS_INCLUDED";
		}
		////
		
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
		
		// for checking TENSE
		if (result.equals("NONE")&&tensePath1Score>tensePath2Score) { //order past->present->future
			System.out.println("Tense path 1 > Tense path 2");
			System.out.println(tensePath1Score);
			System.out.println(tensePath2Score);
			System.out.println("--------------");
			result="AFTER";
		}else if(result.equals("NONE")&&tensePath1Score!=tensePath2Score){
			System.out.println("Tense path 1 < Tense path 2");
			System.out.println(tensePath1Score);
			System.out.println(tensePath2Score);
			System.out.println("--------------");
			result="BEFORE";	 
		}

		// for checking TIME
		if (result.equals("NONE")&&timePath1Score>timePath2Score) { //order past->current
			System.out.println("c");
			result="AFTER";
		}else if(result.equals("NONE")&&timePath1Score!=timePath2Score){
			System.out.println("d");
			result="BEFORE";
		} 

		// Xu ly path AB, BC da co thong tin
		if (result.equals("NONE")) {
			//relationNodes relPath1=relPath.get(0); ----> can predict relation cho relPath1
			//relationNodes relPath2=relPath.get(pathSize-1); ----> can predict relation cho relPath2
			// path 1 (relation 1)
			String path1_node1Label=relPath1.getLabel1();
			String path1_node2Label=relPath1.getLabel2();
			String path1_outputResult="NONE";			
			if (path1_node1Label.indexOf("tmx")==-1&&path1_node2Label.indexOf("tmx")==-1) {
				System.out.println(relPath1.toString());
				System.out.println("No time");
				path1_outputResult=generalRules.twoNodesRelation(relPath1, pSentenceList);
				System.out.println(path1_outputResult);
				System.out.println();
			}else if (path1_node1Label.indexOf("tmx0")!=-1||path1_node2Label.indexOf("tmx0")!=-1) {
				System.out.println(relPath1.toString());
				System.out.println("tmx0");
				path1_outputResult=generalRules.twoNodesRelationTMX0(relPath1, pSentenceList);
				System.out.println(path1_outputResult);
				System.out.println();
			}else if ((path1_node1Label.indexOf("tmx")!=-1&&!path1_node1Label.contains("tmx0"))
					&&(path1_node2Label.indexOf("tmx")!=-1&&!path1_node2Label.contains("tmx0"))) {
				System.out.println(relPath1.toString());
				System.out.println("tmx-tmx");
				path1_outputResult=generalRules.twoNodesRelationTimeTime(relPath1, pKeyWordTMX0, pSentenceList);
				System.out.println(path1_outputResult);
				System.out.println();
			}else{
				System.out.println(relPath1.toString());
				System.out.println("with time");
				path1_outputResult=generalRules.twoNodesRelationEventTime(relPath1, pKeyWordTMX0, pSentenceList);
				System.out.println(path1_outputResult);
				System.out.println();
			}
			
			//////////////////////////////////////////////////////////////////
			// path 2 (relation 2)
			String path2_node1Label=relPath2.getLabel1();
			String path2_node2Label=relPath2.getLabel2();
			String path2_outputResult="NONE";
			
			if (path2_node1Label.indexOf("tmx")==-1&&path2_node2Label.indexOf("tmx")==-1) {
				System.out.println(relPath2.toString());
				System.out.println("No time");
				path2_outputResult=generalRules.twoNodesRelation(relPath2, pSentenceList);
				System.out.println(path2_outputResult);
				System.out.println();
			}else if (path2_node1Label.indexOf("tmx0")!=-1||path2_node2Label.indexOf("tmx0")!=-1) {
				System.out.println(relPath2.toString());
				System.out.println("tmx0");
				path2_outputResult=generalRules.twoNodesRelationTMX0(relPath2, pSentenceList);
				System.out.println(path2_outputResult);
				System.out.println();
			}else if ((path2_node1Label.indexOf("tmx")!=-1&&!path2_node1Label.contains("tmx0"))
					&&(path2_node2Label.indexOf("tmx")!=-1&&!path2_node2Label.contains("tmx0"))) {
				System.out.println(relPath2.toString());
				System.out.println("tmx-tmx");
				path2_outputResult=generalRules.twoNodesRelationTimeTime(relPath2, pKeyWordTMX0, pSentenceList);
				System.out.println(path2_outputResult);
				System.out.println();
			}else{
				System.out.println(relPath2.toString());
				System.out.println("with time");
				path2_outputResult=generalRules.twoNodesRelationEventTime(relPath2, pKeyWordTMX0, pSentenceList);
				System.out.println(path2_outputResult);
				System.out.println();
			}		
			
			// Reverse process
			String final_path1_outputResult=path1_outputResult;
			String final_path2_outputResult=path2_outputResult;

			String[] path1_relText=originalPath.get(0).split("-");
			String path1_node1Txt=path1_relText[0].toLowerCase();
			String path1_node2Txt=path1_relText[1].toLowerCase();
			if (relPath1.isReverse(path1_node1Txt, path1_node2Txt)==1) {
				//System.out.println("1@@@@@@@@@@@@@@@@@@@@@@@@@");
				//System.out.println(path1_outputResult);
				final_path1_outputResult=reverseRules(path1_outputResult);
				//System.out.println(final_path1_outputResult);
				//System.out.println("1@@@@@@@@@@@@@@@@@@@@@@@@@");	
			}
			
			String[] path2_relText=originalPath.get(1).split("-");
			String path2_node1Txt=path2_relText[0].toLowerCase();
			String path2_node2Txt=path2_relText[1].toLowerCase();
			if (relPath2.isReverse(path2_node1Txt, path2_node2Txt)==1) {
				//System.out.println("2@@@@@@@@@@@@@@@@@@@@@@@@@");
				//System.out.println(path2_outputResult);
				final_path2_outputResult=reverseRules(path2_outputResult);
				//System.out.println(final_path2_outputResult);
				//System.out.println("2@@@@@@@@@@@@@@@@@@@@@@@@@");	
			}
			result= twoPaths(final_path1_outputResult, final_path2_outputResult);
			System.out.println(result);
		}

		// when no evidence on Tense and Time
		// For IDENTITY
		// day la truong hop xu ly cho 1 path co gia tri., 1 path khong xac dinh duoc
		
		if (result.equals("NONE")&&relPath.size()==2) {
			if (relPath1.getRelaionType().equals("IDENTITY")&&relPath2.getRelaionType().equals("NONE")
				||relPath2.getRelaionType().equals("IDENTITY")&&relPath1.getRelaionType().equals("NONE")	
					) {
				result="IDENTITY";
			}
		}
		// For IS_INCLUDED
		if (result.equals("NONE")&&relPath.size()==2) {
			if (relPath1.getRelaionType().equals("IS_INCLUDED")&&relPath2.getRelaionType().equals("NONE")
				||relPath2.getRelaionType().equals("IS_INCLUDED")&&relPath1.getRelaionType().equals("NONE")	
					) {
				result="IS_INCLUDED";
			}
		}
		// For INCLUDES
		if (result.equals("NONE")&&relPath.size()==2) {
			if (relPath1.getRelaionType().equals("INCLUDES")&&relPath2.getRelaionType().equals("NONE")
				||relPath2.getRelaionType().equals("INCLUDES")&&relPath1.getRelaionType().equals("NONE")	
					) {
				result="INCLUDES";
			}
		}
		// For BEGINS
		if (result.equals("NONE")&&relPath.size()==2) {
			if (relPath1.getRelaionType().equals("BEGINS")&&relPath2.getRelaionType().equals("NONE")
				||relPath2.getRelaionType().equals("BEGINS")&&relPath1.getRelaionType().equals("NONE")	
					) {
				result="BEGINS";
			}
		}
		// For ENDS
		if (result.equals("NONE")&&relPath.size()==2) {
			if (relPath1.getRelaionType().equals("ENDS")&&relPath2.getRelaionType().equals("NONE")
				||relPath2.getRelaionType().equals("ENDS")&&relPath1.getRelaionType().equals("NONE")	
					) {
				result="ENDS";
			}
		}
		// For SIMULTANEOUS
		if (result.equals("NONE")&&relPath.size()==2) {
			if (relPath1.getRelaionType().equals("SIMULTANEOUS")&&relPath2.getRelaionType().equals("NONE")
				||relPath2.getRelaionType().equals("SIMULTANEOUS")&&relPath1.getRelaionType().equals("NONE")	
					) {
				result="SIMULTANEOUS";
			}
		}
		// For DURING
		if (result.equals("NONE")&&relPath.size()==2) {
			if (relPath1.getRelaionType().equals("DURING")&&relPath2.getRelaionType().equals("NONE")
				||relPath2.getRelaionType().equals("DURING")&&relPath1.getRelaionType().equals("NONE")	
					) {
				result="DURING";
			}
		}			
		return result;
	}
	
	// update on 17 March for three nodes causal relations
	public static String threeNodesCausalRelation(List<String> originalPath, List<relationNodes> pathRelation, List<relationNodes> finalRelList,  List<Sentence> pSentenceList,
												  List<relationNodes> causalRelList, List<relationNodes> temporalRelList){
		String result="NONE";
		int pathSize=pathRelation.size();
		relationNodes relPath1=pathRelation.get(0);
		relationNodes relPath2=pathRelation.get(pathSize-1);
		
		String final_path1_outputResult="";
		String final_path2_outputResult="";
		
		if (result.equals("NONE")) {
			if (relPath1.isExistInList(temporalRelList)==1) {
				System.out.println("Case 1");
				System.out.println(relPath1.toString());
				final_path1_outputResult=relPath1.getExistInList(temporalRelList).getRelaionType();
				final_path2_outputResult=generalRules.twoNodesCausalRelation(relPath2, pSentenceList);
				// Can reverse o vi. tri nay
				String[] path2_relText=originalPath.get(1).split("-");
				String path2_node1Txt=path2_relText[0].toLowerCase();
				String path2_node2Txt=path2_relText[1].toLowerCase();
				if (relPath2.isReverse(path2_node1Txt, path2_node2Txt)==1) {
					//System.out.println("1@@@@@@@@@@@@@@@@@@@@@@@@@");
					//System.out.println(path1_outputResult);
					final_path2_outputResult=reverseRulesCausal(final_path2_outputResult);
					//System.out.println(final_path1_outputResult);
					//System.out.println("1@@@@@@@@@@@@@@@@@@@@@@@@@");	
				}
				
				result= twoPathsCausal(final_path1_outputResult, final_path2_outputResult);
				System.out.println("----------------------------------");
				System.out.println(final_path1_outputResult);
				System.out.println(final_path2_outputResult);
				System.out.println("----------------------------------");
			}else if (relPath2.isExistInList(temporalRelList)==1) {
				System.out.println("Case 2");
				final_path2_outputResult=relPath2.getExistInList(temporalRelList).getRelaionType();
				final_path1_outputResult=generalRules.twoNodesCausalRelation(relPath1, pSentenceList);
				
				// Can reverse o vi. tri nay
				String[] path1_relText=originalPath.get(0).split("-");
				String path1_node1Txt=path1_relText[0].toLowerCase();
				String path1_node2Txt=path1_relText[1].toLowerCase();
				if (relPath1.isReverse(path1_node1Txt, path1_node2Txt)==1) {
					//System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@");
					//System.out.println(final_path1_outputResult);
					final_path1_outputResult=reverseRulesCausal(final_path1_outputResult);
					//System.out.println(final_path1_outputResult);
					//System.out.println("1@@@@@@@@@@@@@@@@@@@@@@@@@");	
				}

				result= twoPathsCausal(final_path1_outputResult, final_path2_outputResult);
				System.out.println("-----------------------------------");
				System.out.println(final_path1_outputResult);
				System.out.println(final_path2_outputResult);
				System.out.println("-----------------------------------");
			}
		}

		return result;
	}
	
	// Reverse rules
	public static String reverseRules(String inputResult){
		String result=inputResult;
		if (inputResult.equals("BEFORE")) {
			result="AFTER";
		}else if (inputResult.equals("AFTER")) {
			result="BEFORE";
		}
		return result;
	}

	// Reverse causal rules
	public static String reverseRulesCausal(String inputResult){
		String result=inputResult;
		if (inputResult.equals("CLINK")) {
			result="CLINK-C";
		}else if (inputResult.equals("CLINK-C")) {
			result="CLINK";
		}
		return result;
	}
	
	
	public static String fourNodesRelation(){
		String result="None";
		// Xu ly tense, time
		// then xu ly threeNodesRelation
		// then xu ly two not (threeNodesRelation, last node)
		return result;
	}
	
	public static void main(String args[]){
		// Can khai bao initiation variables
		// Main process
		// Check path
		// path with 2 nodes (directrules)
		// path
	}
	
}

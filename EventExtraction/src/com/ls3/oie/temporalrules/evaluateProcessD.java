package com.ls3.oie.temporalrules;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class evaluateProcessD {

	public static int labelEqual(String label1, String label2){		
		int result=0;
		String[] BEGIN_initiation = {"BEGUN_BY", "BEGINS", "BEGUN"};
		List<String> BEGIN_list = Arrays.asList(BEGIN_initiation);

		String[] INCLUDE_initiation = {"IS_INCLUDED", "INCLUDES", "INCLUDES/SIMULTANEOUS"};
		List<String> INCLUDE_list = Arrays.asList(INCLUDE_initiation);
		
		String[] END_initiation = {"ENDED_BY", "ENDS"};
		List<String> END_list = Arrays.asList(END_initiation);
		
		String[] BEFORE_initiation = {"BEFORE", "IBEFORE","BEFORE/AFTER"};
		List<String> BEFORE_list = Arrays.asList(BEFORE_initiation);
		
		String[] AFTER_initiation = {"AFTER", "IAFTER","BEFORE/AFTER"};
		List<String> AFTER_list = Arrays.asList(AFTER_initiation);
		
		String[] SIM_initiation = {"SIMULTANEOUS","INCLUDES/SIMULTANEOUS","SIMULTANEOUS/DURING", "SIMULTANEOUS/IDENTITY"};
		List<String> SIM_list = Arrays.asList(SIM_initiation);
		
		String[] IDENTIFY_initiation = {"IDENTIFY", "SIMULTANEOUS/IDENTIFY"};
		List<String> IDENTIFY_list = Arrays.asList(IDENTIFY_initiation);
		
		if (label1.equals(label2)) {
			result=1;
		}else if (BEGIN_list.contains(label1)&&BEGIN_list.contains(label2)) {
			result=1;
		}else if (INCLUDE_list.contains(label1)&&INCLUDE_list.contains(label2)) {
			result=1;
		}else if (END_list.contains(label1)&&END_list.contains(label2)) {
			result=1;
		}else if (BEFORE_list.contains(label1)&&BEFORE_list.contains(label2)) {
			result=1;
		}else if (AFTER_list.contains(label1)&&AFTER_list.contains(label2)) {
			result=1;
		}else if (SIM_list.contains(label1)&&SIM_list.contains(label2)) {
			result=1;
		}else if (SIM_list.contains(label1)&&IDENTIFY_list.contains(label2)) {
			result=1;
		}
		return result;
	}
	
	public static void main(String args[]) throws IOException{
	    int total=0;
		int trueRecord=0;
		int falseRecord=0;
		int missingRecord=0;
		double precision=0;
		double recall=0;
		
	    //For E-E
		int totalEE=0;
		int trueRecordEE=0;
		int falseRecordEE=0;
		int missingRecordEE=0;
		double precisionEE=0;
		double recallEE=0;

		//For E-T0
		int totalET0=0;
		int trueRecordET0=0;
		int falseRecordET0=0;
		int missingRecordET0=0;
		double precisionET0=0;
		double recallET0=0;
		
		//For E-T
		int totalET=0;
		int trueRecordET=0;
		int falseRecordET=0;
		int missingRecordET=0;
		double precisionET=0;
		double recallET=0;
		
		//For T-T
		int totalTT=0;
		int trueRecordTT=0;
		int falseRecordTT=0;
		int missingRecordTT=0;
		double precisionTT=0;
		double recallTT=0;
		
		List<String> resouceRecordList=new ArrayList<>();
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3_train-cnn");
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3_train-xie");
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3_train-nyt");
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3_train-voa");
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3_train-pri");
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3_train-ap");
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3_train-abc");
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3_wsj");
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3_bbc");
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3_cnn");
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3_nyt");
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3_test_wsj");
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3-train-all");
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3-test-all");
		
		////////////Training----------------------------------------------------------------------
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3-train-all-before");
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3-train-all-after.txt");
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3-train-all-begins");
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3-train-all-begun");
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3-train-all-during");
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3-train-all-endby.txt");
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3-train-all-end");
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3-train-all-iafter");
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3-train-all-ibeforeA.txt");
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3-train-all-iden");
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3-train-all-includes");
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3-train-all-included");
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3-train-all-sim");
		////////////Testing----------------------------------------------------------------------
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3-test-all-before");
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3-test-all-after.txt");
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3-test-all-begins");
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3-test-all-begun");
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3-test-all-during");
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3-test-all-endby");
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3-test-all-ends");
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3-test-all-iafter");
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3-test-all-ibefore");
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3-test-all-iden");
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3-test-all-includes");
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3-test-all-included");
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3-test-all-sim");
		// Causal eveluation
		FileInputStream fstream1 = new FileInputStream("data//cdata//CausalEval3_1_abc");
		//FileInputStream fstream1 = new FileInputStream("data//cdata//CausalEval3_1_ap.txt");
		//FileInputStream fstream1 = new FileInputStream("data//cdata//CausalEval3_1_apw");
		//FileInputStream fstream1 = new FileInputStream("data//cdata//CausalEval3_1_cnn.txt");
		
		DataInputStream in1 = new DataInputStream(fstream1);
		BufferedReader br1 = new BufferedReader(new InputStreamReader(in1));
		String strline1="";
		while ((strline1=br1.readLine())!=null){
			resouceRecordList.add(strline1);
			System.out.println(strline1);
		}
		System.out.println("------------------------------");
		//FileInputStream fstream2 = new FileInputStream("data//tdata//output_files/allOutput_Records_train-cnn");
		//FileInputStream fstream2 = new FileInputStream("data//tdata//output_files/allOutput_Records_train-xie");
		//FileInputStream fstream2 = new FileInputStream("data//tdata//output_files/allOutput_Records_train-nyt");
		//FileInputStream fstream2 = new FileInputStream("data//tdata//output_files/allOutput_Records_train-voa");
		//FileInputStream fstream2 = new FileInputStream("data//tdata//output_files/allOutput_Records_train-pri");
		//FileInputStream fstream2 = new FileInputStream("data//tdata//output_files/allOutput_Records_train-ap");
		//FileInputStream fstream2 = new FileInputStream("data//tdata//output_files/allOutput_Records_train-abc");
		//FileInputStream fstream2 = new FileInputStream("data//tdata//output_files/allOutput_Records");
		//FileInputStream fstream2 = new FileInputStream("data//tdata//output_files/allOutput_Records_bbc");
		//FileInputStream fstream2 = new FileInputStream("data//tdata//output_files/allOutput_Records_cnn");
		//FileInputStream fstream2 = new FileInputStream("data//tdata//output_files/allOutput_Records_nyt");
		//FileInputStream fstream2 = new FileInputStream("data//tdata//output_files/allOutput_Records_test_wsj");
		//FileInputStream fstream2 = new FileInputStream("data//tdata//output_files/finalOutput_train"); //LS3
		//FileInputStream fstream2 = new FileInputStream("data//tdata//output_files/finalOutput_test");//LS3
		//FileInputStream fstream2 = new FileInputStream("data//tdata//oie5//output//output_training_oie5.txt");//OIE5-training
		//FileInputStream fstream2 = new FileInputStream("data//tdata//oie5//output//output_test_oie5.txt");//OIE5-testing
		
		//Causal
		FileInputStream fstream2 = new FileInputStream("data//cdata//oie5//output_files//1_ouput_results_oie5_abc");
		//FileInputStream fstream2 = new FileInputStream("data//cdata//oie5//output_files//1_ouput_results_oie5_cnn");
		DataInputStream in2 = new DataInputStream(fstream2);
		BufferedReader br2 = new BufferedReader(new InputStreamReader(in2));
		String strline2="";
		List<String> resultRecordList=new ArrayList<String>();
		HashSet<String> IDList = new HashSet<String>();
		while ((strline2=br2.readLine())!=null){
			System.out.println(strline2);	
			resultRecordList.add(strline2);
			String[] record=strline2.split("\t");
			IDList.add(record[0]);
		}
		
		System.out.println("------------------------------");
		for (int i = 0; i < resouceRecordList.size(); i++) {
			String resourceRecord=resouceRecordList.get(i);
			String[] resourceRecordArr=resourceRecord.split("\t");
			String IDresouce=resourceRecordArr[0];
			String node1Resouce=resourceRecordArr[1];
			String node2Resouce=resourceRecordArr[2];
			String labelResouce=resourceRecordArr[3];
			if (IDList.contains(IDresouce)) {
				total++;

				// for EE
				if (node1Resouce.indexOf("e")!=-1&&node2Resouce.indexOf("e")!=-1) {
					totalEE++;
				}
				
				// for E-T
				if (node1Resouce.indexOf("e")!=-1&&node2Resouce.indexOf("tmx")!=-1
					||
					node2Resouce.indexOf("e")!=-1&&node1Resouce.indexOf("tmx")!=-1
					) {
					if (node1Resouce.indexOf("tmx0")==-1&&node2Resouce.indexOf("tmx0")==-1) {
						totalET++;
					}
				}
				
				// for E-TMX0
				if (node1Resouce.indexOf("e")!=-1&&node2Resouce.indexOf("tmx0")!=-1
					||
					node2Resouce.indexOf("e")!=-1&&node1Resouce.indexOf("tmx0")!=-1
					) {
						totalET0++;
				}
				
				// for T-T
				if (node1Resouce.indexOf("tmx")!=-1&&node2Resouce.indexOf("tmx")!=-1
					||
					node2Resouce.indexOf("tmx")!=-1&&node1Resouce.indexOf("tmx")!=-1
					) {
					if (node1Resouce.indexOf("tmx0")==-1&&node2Resouce.indexOf("tmx0")==-1) {
						totalTT++;
					}
				}
				
				for (int j = 0; j < resultRecordList.size(); j++) {
					String resultRecord=resultRecordList.get(j);
					String[] resultRecordArr=resultRecord.split("\t");
					String IDrecord=resultRecordArr[0];
					String node1Record=resultRecordArr[1];
					String node2Record=resultRecordArr[2];
					String labelRecord=resultRecordArr[3]; 
					if (IDrecord.equals(IDresouce)
						&&node1Resouce.equals(node1Record)
						&&node2Resouce.equals(node2Record)
						&&labelEqual(labelResouce, labelRecord)==1
						) {
						trueRecord++;
						System.out.println(resouceRecordList.get(i));
						
						// for EE
						if (node1Record.indexOf("e")!=-1&&node2Record.indexOf("e")!=-1) {
							trueRecordEE++;
						}
						
						// for E-T
						if (node1Record.indexOf("e")!=-1&&node2Record.indexOf("tmx")!=-1
							||
							node2Record.indexOf("e")!=-1&&node1Record.indexOf("tmx")!=-1
							) {
							if (node1Record.indexOf("tmx0")==-1&&node2Record.indexOf("tmx0")==-1) {
								trueRecordET++;
							}
						}
						
						// for E-T0
						if (node1Record.indexOf("e")!=-1&&node2Record.indexOf("tmx0")!=-1
							||
							node2Record.indexOf("e")!=-1&&node1Record.indexOf("tmx0")!=-1
							) {
								trueRecordET0++;
						}
						
						// for E-T
						if (node1Record.indexOf("tmx")!=-1&&node2Record.indexOf("tmx")!=-1
							||
							node2Record.indexOf("tmx")!=-1&&node1Record.indexOf("tmx")!=-1
							) {
							if (node1Record.indexOf("tmx0")==-1&&node2Record.indexOf("tmx0")==-1) {
								trueRecordTT++;
							}
						}
						
						break;
						
					}else if (IDrecord.equals(IDresouce)
							&&node1Resouce.equals(node1Record)
							&&node2Resouce.equals(node2Record)
							&&labelEqual(labelResouce, labelRecord)!=1
						) {
						falseRecord++;
						System.err.println(resouceRecordList.get(i));
						
						// for E-E
						if (node1Record.indexOf("e")!=-1&&node2Record.indexOf("e")!=-1) {
							falseRecordEE++;
						}

						// for E-T
						if (node1Record.indexOf("e")!=-1&&node2Record.indexOf("tmx")!=-1
							||
							node2Record.indexOf("e")!=-1&&node1Record.indexOf("tmx")!=-1
							) {
							if (node1Record.indexOf("tmx0")==-1&&node2Record.indexOf("tmx0")==-1) {
								falseRecordET++;
								//System.err.println(resouceRecordList.get(i));
							}
						}
						
						// for E-T0
						if (node1Record.indexOf("e")!=-1&&node2Record.indexOf("tmx0")!=-1
							||
							node2Record.indexOf("e")!=-1&&node1Record.indexOf("tmx0")!=-1
							) {
								falseRecordET0++;
							
						}

						// for T-T
						if (node1Record.indexOf("tmx")!=-1&&node2Record.indexOf("tmx")!=-1
							||
							node2Record.indexOf("tmx")!=-1&&node1Record.indexOf("tmx")!=-1
							) {
							if (node1Record.indexOf("tmx0")==-1&&node2Record.indexOf("tmx0")==-1) {
								falseRecordTT++;
								//System.err.println(resouceRecordList.get(i));
							}
						}

						
						
						break;
					}
					
				}
			}
		}
        missingRecord=total-trueRecord-falseRecord;
		missingRecordEE=totalEE-trueRecordEE-falseRecordEE;
		missingRecordET=totalET-trueRecordET-falseRecordET;
		missingRecordET0=totalET0-trueRecordET0-falseRecordET0;
		missingRecordTT=totalTT-trueRecordTT-falseRecordTT;
        
		System.out.println("Final result...");
        System.out.println();
        System.out.println("Total records: "+total);
        System.out.println("True records: "+trueRecord);
        System.out.println("False records: "+falseRecord);
        System.out.println("Missing records: "+missingRecord);
        System.out.println("--------------------------------");
        
        System.out.println("Precision: "+(double)trueRecord/(double)(total-missingRecord));
        System.out.println("Recall: "+(double)trueRecord/(double)total);
        System.out.println("F1: "+(double)trueRecord/(double)total);
        
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("Total EE records: "+totalEE);
        System.out.println("True EE records: "+trueRecordEE);
        System.out.println("False EE records: "+falseRecordEE);
        System.out.println("Missing EE records: "+missingRecordEE);
        System.out.println("--------------------------------");
        System.out.println("EE Precision: "+(double)trueRecordEE/(totalEE-missingRecordEE));
        System.out.println("EE Recall: "+(double)trueRecordEE/totalEE);
        
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("Total ET records: "+totalET);
        System.out.println("True ET records: "+trueRecordET);
        System.out.println("False ET records: "+falseRecordET);
        System.out.println("Missing ET records: "+missingRecordET);
        System.out.println("--------------------------------");
        System.out.println("ET Precision: "+(double)trueRecordET/(totalET-missingRecordET));
        System.out.println("ET Recall: "+(double)trueRecordET/totalET);
        
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("Total E-TMX0 records: "+totalET0);
        System.out.println("True E-TMX0 records: "+trueRecordET0);
        System.out.println("False E-TMX0 records: "+falseRecordET0);
        System.out.println("Missing E-TMX0 records: "+missingRecordET0);
        System.out.println("--------------------------------");
        System.out.println("E-TMX0 Precision: "+(double)trueRecordET0/(totalET0-missingRecordET0));
        System.out.println("E-TMX0 Recall: "+(double)trueRecordET0/totalET0);
        
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("Total TMX-TMX records: "+totalTT);
        System.out.println("True TMX-TMX records: "+trueRecordTT);
        System.out.println("False TMX-TMX records: "+falseRecordTT);
        System.out.println("Missing TMX-TMX records: "+missingRecordTT);
        System.out.println("--------------------------------");
        System.out.println("TMX-TMX Precision: "+(double)trueRecordTT/(totalTT-missingRecordTT));
        System.out.println("TMX-TMX Recall: "+(double)trueRecordTT/totalTT);
        System.out.println("-----------------------------------------------");
        
        
	}
}

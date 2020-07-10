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

public class evaluateProcess {

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
		
		List<String> resouceRecordList=new ArrayList<>();
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3_train-cnn");
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3_train-xie");
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3_train-nyt");
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3_train-voa");
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3_train-pri");
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3_train-ap");
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3_bbc");
		//FileInputStream fstream1 = new FileInputStream("data//tdata//TempEval3_train-ap");
		FileInputStream fstream1 = new FileInputStream("data//tdata//oie5//TempEval3-train-all");
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
		//FileInputStream fstream2 = new FileInputStream("data//tdata//output_files/allOutput_Records_bbc");
		//FileInputStream fstream2 = new FileInputStream("data//tdata//output_files/allOutput_Records_train-ap");
		FileInputStream fstream2 = new FileInputStream("data//tdata//oie5//output//output_training.txt");
		//FileInputStream fstream2 = new FileInputStream("data//tdata//oie5//output//output_training_oie5.txt");
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
						break;
						
					}else if (IDrecord.equals(IDresouce)
							&&node1Resouce.equals(node1Record)
							&&node2Resouce.equals(node2Record)
							&&labelEqual(labelResouce, labelRecord)!=1
						) {
						falseRecord++;
						System.err.println(resouceRecordList.get(i));
						break;
					}
					
				}
			}
		}
        missingRecord=total-trueRecord-falseRecord;
		System.out.println("Final result...");
        System.out.println();
        System.out.println("Total records: "+total);
        System.out.println("True records: "+trueRecord);
        System.out.println("False records: "+falseRecord);
        System.out.println("Missing records: "+missingRecord);
        System.out.println("--------------------------------");
        System.out.println("Precision: "+(double)trueRecord/(total-missingRecord));
        System.out.println("Recall: "+(double)trueRecord/total);
        
	}
}

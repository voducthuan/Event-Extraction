package com.ls3.oie.temporalmodel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class colProcess {
	
	public static void main(String args[]) throws IOException{
		// Load sentence truoc
		BufferedReader br = new BufferedReader(new FileReader("data//tdata//WSJ_20130318_731.col"));
		String sentence = null;
		List<String> allString=new ArrayList<>(); 
		while((sentence = br.readLine()) != null){
			allString.add(sentence);
		}

		List<String> sentences=new ArrayList<String>();
		
		for (int i = 0; i < allString.size(); i++) {
			String[] pLine=allString.get(i).split("\t");
			System.out.println(pLine[0]);
			String pSentence="";
			while (!pLine[0].equals(".")) {
				
			}
		
		}
		
	}

}

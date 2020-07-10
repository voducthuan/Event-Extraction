package com.ls3.oie.graph;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.graphstream.algorithm.APSP;
import org.graphstream.algorithm.APSP.APSPInfo;
import org.graphstream.algorithm.APSP.Progress;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Path;
import org.graphstream.graph.implementations.DefaultGraph;
import org.graphstream.stream.file.FileSourceDGS;

/**
 * 
 *     B-(1)-C
 *    /       \
 *  (1)       (10)
 *  /           \
 * A             F
 *  \           /
 *  (1)       (1)
 *    \       /
 *     D-(1)-E
 */
public class graph_path {

	static String my_graph = 
		"DGS004\n" 
		+ "my 0 0\n" 
		+ "an A \n" 
		+ "an B \n"
		+ "an C \n"
		+ "an D \n"
		+ "an E \n"
		+ "an F \n"
		+ "ae AB A B weight:1 \n"
		+ "ae AD A D weight:1 \n"
		+ "ae BC B C weight:1 \n"
		+ "ae CF C F weight:10 \n"
		+ "ae DE D E weight:1 \n"
		+ "ae EF E F weight:1 \n"
		;

	public static void main(String[] args) throws IOException {
		Graph graph = new DefaultGraph("APSP Test");
		ByteArrayInputStream bs = new ByteArrayInputStream(my_graph.getBytes());
		
		FileSourceDGS source = new FileSourceDGS();
		source.addSink(graph);
		source.readAll(bs);
		
		APSP apsp = new APSP();
		apsp.init(graph); // registering apsp as a sink for the graph
		apsp.setDirected(false); // undirected graph
		apsp.setWeightAttributeName("weight"); // ensure that the attribute name used is "weight"

		apsp.compute(); // the method that actually computes shortest paths
		
		APSPInfo info = graph.getNode("F").getAttribute(APSPInfo.ATTRIBUTE_NAME);		
		System.out.println(info.getShortestPathTo("A"));
		Path pPathlink=info.getShortestPathTo("A");
		
		Iterator iter=pPathlink.getNodeIterator();
		List<String> nodelist=new ArrayList<>();
		while (iter.hasNext()) {
			Node pnode=(Node)iter.next();
			nodelist.add(pnode.getId());
		}

		List<String> finalRelNodes=new ArrayList<>();
		for (int i = 0; i < nodelist.size(); i++) {
			if (i+1<nodelist.size()) {
				String output=nodelist.get(i)+"-"+nodelist.get(i+1);
				System.out.println(output);
				finalRelNodes.add(output);
			}
		}
	}
}
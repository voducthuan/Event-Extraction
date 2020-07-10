package com.ls3.oie.graph;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.graphstream.algorithm.APSP;
import org.graphstream.algorithm.APSP.APSPInfo;
import org.graphstream.algorithm.APSP.Progress;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Path;
import org.graphstream.graph.implementations.DefaultGraph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.stream.file.FileSourceDGS;

import com.ls3.oie.temporalmodel.KeyWord;
import com.ls3.oie.temporalmodel.relationNodes;

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
public class graphUtil {

	protected static String styleSheet =
	        "node {" +
	        "	fill-color: black;" +
	        "}" +
	        "node.marked {" +
	        "	fill-color: red;" +
	        "}";
	
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

	public static List<String> shortPathList(Path pPathSource){
		List<String> result= new ArrayList<String>();
		Iterator iter=pPathSource.getNodeIterator();
		List<String> nodelist=new ArrayList<>();
		while (iter.hasNext()) {
			Node pnode=(Node)iter.next();
			nodelist.add(pnode.getId());
		}
		
		for (int i = 0; i < nodelist.size(); i++) {
			if (i+1<nodelist.size()) {
				String output=nodelist.get(i)+"-"+nodelist.get(i+1);
				result.add(output);
			}
		}
		return result;
	}
	
	// graph with list of relation nodes
	public static Graph graphInit(List<relationNodes> pRelList) throws IOException{
		Graph graph = new SingleGraph("Main graph");
        graph.addAttribute("ui.stylesheet", styleSheet);
        graph.setAutoCreate(true);
        graph.setStrict(false);
         
		System.out.println("Loading relation nodes:");
		
        for (int i = 0; i < pRelList.size(); i++) {
			relationNodes relNodes=pRelList.get(i);
			
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
        
		return graph;
	}

	public static Path shortestPath(Graph pGraph, String firstNode, String lastNode){
		Path result=new Path();
        APSP apsp = new APSP();
		apsp.init(pGraph); // registering apsp as a sink for the graph
		apsp.setDirected(false); // undirected graph
		apsp.setWeightAttributeName("weight"); // ensure that the attribute name used is "weight"
		apsp.compute(); // the method that actually computes shortest paths
		APSPInfo info = pGraph.getNode(firstNode).getAttribute(APSPInfo.ATTRIBUTE_NAME);		
		result=info.getShortestPathTo(lastNode);
		//System.out.println(info.getShortestPathTo(lastNode));
		return result;
	}
	
	
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
		
		System.out.println(info.targets);
		
	}
}

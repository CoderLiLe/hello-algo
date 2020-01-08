package com.lile;

import java.util.List;

import com.lile.graph.Graph;
import com.lile.graph.Graph.VertexVisitor;
import com.lile.graph.ListGraph;

public class Main {

	public static void main(String[] args) {
//		testBFS();
//		testDFS();
		testTopo();
	}
	
	static void test() {
		Graph<String, Integer> graph = new ListGraph<>();
		graph.addEdge("V1", "V0", 9);
		graph.addEdge("V1", "V2", 3);
		graph.addEdge("V2", "V0", 2);
		graph.addEdge("V2", "V3", 5);
		graph.addEdge("V3", "V4", 1);
		graph.addEdge("V0", "V4", 6);
		
//		graph.removeEdge("V0", "V4");
//		graph.removeVertex("V0");
		
//		((ListGraph<String, Integer>)graph).print();
		
//		graph.bfs("V1");
	}
	
	static void testBFS() {
		Graph<Object, Double> graph = directedGraph(Data.BFS_02);
//		graph.bfs(7);
//		graph.bfs(0, new VertexVisitor<Object>() {
//			@Override
//			public boolean visit(Object v) {
//				System.out.println(v);
//				return v.equals(2);
//			}
//		});
		graph.bfs(0, (Object v) -> {
			System.out.println(v);
			return false;
		});
	}
	
	static void testDFS() {
		Graph<Object, Double> graph = directedGraph(Data.DFS_01);
//		graph.dfs(0);
		graph.dfs(0, (Object v) -> {
			System.out.println(v);
			return false;
		});
	}
	
	static void testTopo() {
		Graph<Object, Double> graph = directedGraph(Data.TOPO);
		List<Object> list = graph.topologicalSort();
		System.out.println(list);
	}
	
	/**
	 * 有向图
	 */
	private static Graph<Object, Double> directedGraph(Object[][] data) {
		Graph<Object, Double> graph = new ListGraph<>();
		for (Object[] edge : data) {
			if (edge.length == 1) {
				graph.addVertex(edge[0]);
			} else if (edge.length == 2) {
				graph.addEdge(edge[0], edge[1]);
			} else if (edge.length == 3) {
				double weight = Double.parseDouble(edge[2].toString());
				graph.addEdge(edge[0], edge[1], weight);
			}
		}
		return graph;
	}
	
	/**
	 * 无向图
	 * @param data
	 * @return
	 */
	private static Graph<Object, Double> undirectedGraph(Object[][] data) {
		Graph<Object, Double> graph = new ListGraph<>();
		for (Object[] edge : data) {
			if (edge.length == 1) {
				graph.addVertex(edge[0]);
			} else if (edge.length == 2) {
				graph.addEdge(edge[0], edge[1]);
				graph.addEdge(edge[1], edge[0]);
			} else if (edge.length == 3) {
				double weight = Double.parseDouble(edge[2].toString());
				graph.addEdge(edge[0], edge[1], weight);
				graph.addEdge(edge[1], edge[0], weight);
			}
		}
		return graph;
	}

}

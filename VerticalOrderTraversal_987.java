package medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.TreeMap;

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

class Obj implements Comparable<Obj> {
	TreeNode tr;
	int distance;
	int depth;

	public Obj(TreeNode tr, int distance, int depth) {
		this.tr = tr;
		this.distance = distance;
		this.depth = depth;
	}

	public int compareTo(Obj that) {
		if (this.distance != that.distance)
			return this.distance - that.distance;
		if (this.depth != that.depth)
			return this.depth - that.depth;
		else
			return this.tr.val - that.tr.val;
	}
}

public class VerticalOrderTraversal_987 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode root = new TreeNode(0);
		root.left = new TreeNode(2);
		root.right = new TreeNode(1);
		root.left.left = new TreeNode(3);
		root.left.left.left = new TreeNode(4);
		root.left.left.right = new TreeNode(5);
		root.left.left.left.right = new TreeNode(7);
		root.left.left.right.left = new TreeNode(6);
		root.left.left.left.right.left = new TreeNode(10);
		root.left.left.left.right.right = new TreeNode(8);
		root.left.left.right.left.left = new TreeNode(11);
		root.left.left.right.left.right = new TreeNode(9);

		List<List<Integer>> result = verticalTraversalWithQueue(root);

		for (int i = 0; i < result.size(); i++) {
			result.get(i);
		}

	}

	public static List<List<Integer>> verticalTraversalWithQueue(TreeNode root) {
		TreeMap<Integer, List<Obj>> ansMap = new TreeMap<Integer, List<Obj>>();
		List<List<Integer>> result = new ArrayList();
		Queue<Obj> q = new LinkedList<Obj>();
		q.add(new Obj(root, 0, 0));
		while (!q.isEmpty()) {
			// pop element
			Obj pop = q.poll();
			// insert children with appropriate distance
			if (pop.tr.left != null)
				q.add(new Obj(pop.tr.left, pop.distance - 1, pop.depth + 1));
			if (pop.tr.right != null)
				q.add(new Obj(pop.tr.right, pop.distance + 1, pop.depth + 1));
			// insert poped element in map
			List<Obj> aList;
			if (ansMap.containsKey(pop.distance)) {
				aList = ansMap.get(pop.distance);
			} else {
				aList = new ArrayList<Obj>();
			}
			aList.add(pop);
			ansMap.put(pop.distance, aList);
		}
		for (Entry<Integer, List<Obj>> entry : ansMap.entrySet()) {
			Collections.sort(entry.getValue());
			List<Integer> l = new ArrayList<Integer>();
			for (int i = 0; i < entry.getValue().size(); i++) {
				l.add(entry.getValue().get(i).tr.val);
			}
			result.add(l);
		}
		return result;
	}

	public static List<List<Integer>> verticalTraversal(TreeNode root) {
		TreeMap<Integer, List<Integer>> ansMap = new TreeMap<Integer, List<Integer>>();

		List<List<Integer>> result = new ArrayList();

		vo(root, 0, ansMap);
		for (Map.Entry<Integer, List<Integer>> entry : ansMap.entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
			Collections.sort(entry.getValue());
			result.add(entry.getValue());
		}
		return result;
	}

	public static void vo(TreeNode curNode, int distance, TreeMap<Integer, List<Integer>> ansMap) {
		if (curNode == null) {
			return;
		}
		List<Integer> aList;
		if (ansMap.containsKey(distance)) {
			aList = ansMap.get(distance);
		} else {
			aList = new ArrayList<Integer>();
		}
		aList.add(curNode.val);
		ansMap.put(distance, aList);

		vo(curNode.left, distance - 1, ansMap);
		vo(curNode.right, distance + 1, ansMap);
		return;
	}
}

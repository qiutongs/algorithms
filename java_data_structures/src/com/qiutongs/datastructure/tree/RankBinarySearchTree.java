package com.qiutongs.datastructure.tree;

import java.util.Arrays;
import java.util.List;

public class RankBinarySearchTree<I extends Comparable<I>> extends SimpleBinarySearchTree<I> {

	private int rank;

	public RankBinarySearchTree(List<I> items) {
		super(items);
	}

	@Override
	public void inorderTraversal() {
		rank = 1;
		super.inorderTraversal();
	}

	@Override
	protected void inorderFunc(BinaryNode<I> node) {
		node.properties.put("rank", rank);
		System.out.println(node + " " + rank);
		rank++;
	}

	public static void main(String[] args) {
		RankBinarySearchTree<Integer> tree = new RankBinarySearchTree<>(
				Arrays.asList(2, 4, 16, 18, 10, 12, null, 14, 6, 8, null, null, 0));

		tree.inorderTraversal();
	}
}

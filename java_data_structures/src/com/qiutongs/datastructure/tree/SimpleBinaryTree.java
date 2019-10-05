package com.qiutongs.datastructure.tree;

import java.util.Arrays;
import java.util.List;

public class SimpleBinaryTree<I> extends BinaryTree<I, BinaryNode<I>> {

	public SimpleBinaryTree() {
		super();
	}

	public SimpleBinaryTree(List<I> items) {
		super(items);
	}

	public static void main(String[] args) {
		SimpleBinaryTree<Integer> tree1 = new SimpleBinaryTree<>(
				Arrays.asList(1, 2, 3, 4, 5, 6, null, 7, 8, 9, null, null, 0));
		System.out.println(tree1);
	}
}

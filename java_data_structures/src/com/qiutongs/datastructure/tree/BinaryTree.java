package com.qiutongs.datastructure.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTree<I, N extends BinaryNode<I>> {

	// Utility method to help calculation
	static protected int powerOf2(int n) {
		return 1 << n;
	}

	protected BinaryNode<I> root;

	protected BinaryTree() {
		this.root = null;
	}

	// Construct the tree with a item array
	public BinaryTree(List<I> items) {
		if (items == null || items.size() < 1) {
			throw new IllegalArgumentException();
		}

		// Convert to array list
		ArrayList<I> arrayList = new ArrayList<>(items);

		// Set the root as first element in array
		root = new BinaryNode<I>(arrayList.get(0));

		// Recursively build the tree, starting from root
		buildNode(arrayList, 0, root);
	}

	public BinaryNode<I> getRoot() {
		return root;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		// Get height of tree to calculate required buffer size
		int height = getHeight();

		// Create a 2-dimension String buffer
		// height + 1: a tree with height has height + 1 layers
		// 2 ^ (height + 1): a tree with height has potential 2 ^ height leaves;
		// here we *double* the buffer to add interval
		String[][] rowsArray = new String[height + 1][powerOf2(height + 1)];

		// Initialize with empty string
		for (String[] row : rowsArray) {
			Arrays.fill(row, " ");
		}

		// Recursively draw the tree, string from root
		// root should be placed in a middle of a row, thus 2 ^ height
		draw(rowsArray, root, 0, powerOf2(height));

		// Append each string to the resulting string builder
		for (String[] row : rowsArray) {
			for (String s : row) {
				sb.append(s);
			}
			sb.append('\n');
		}

		return sb.toString();
	}

	void draw(String[][] rowsArray, BinaryNode<I> node, int depth, int position) {
		if (node == null) {
			return;
		}

		rowsArray[depth][position] = node.item == null ? " " : node.item.toString();

		int height = rowsArray.length - 1;

		draw(rowsArray, node.left, depth + 1, position - powerOf2(height - depth - 1));
		draw(rowsArray, node.right, depth + 1, position + powerOf2(height - depth - 1));
	}

	// Return height of the tree
	public int getHeight() {
		// The height of the tree is the height of the root node
		return getHeight(root);
	}

	protected ArrayList<I> toArray() {
		ArrayList<I> arrayList = new ArrayList<>();
		Queue<BinaryNode<I>> queue = new LinkedList<>();
		queue.add(root);

		while (!queue.isEmpty()) {
			BinaryNode<I> front = queue.poll();
			arrayList.add(front.item);

			if (front.item != null) {
				queue.add(front.left == null ? new BinaryNode<I>(null) : front.left);
				queue.add(front.right == null ? new BinaryNode<I>(null) : front.right);
			}
		}

		return arrayList;
	}

	protected boolean isLeaf(BinaryNode<I> node) {
		return node != null && node.left == null && node.right == null;
	}

	// Build the children of 'node' at 'index', similar to recursive DFS
	private void buildNode(ArrayList<I> items, int index, BinaryNode<I> node) {

		int leftIndex = index * 2 + 1;
		int rightIndex = index * 2 + 2;

		if (leftIndex < items.size()) {
			node.left = new BinaryNode<I>(items.get(leftIndex));
			node.left.parent = node;
			buildNode(items, leftIndex, node.left);
		}

		if (rightIndex < items.size()) {
			node.right = new BinaryNode<I>(items.get(rightIndex));
			node.right.parent = node;
			buildNode(items, rightIndex, node.right);
		}
	}

	// Return the height of the node: longest path to a leaf from this node
	private int getHeight(BinaryNode<I> node) {
		// By convention, the empty node has height -1
		if (node == null) {
			return -1;
		}

		// Return max of left path and right path, plus 1
		return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
	}
}

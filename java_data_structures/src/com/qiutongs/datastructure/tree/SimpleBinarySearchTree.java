package com.qiutongs.datastructure.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleBinarySearchTree<I extends Comparable<I>> extends SimpleBinaryTree<I> {

	public SimpleBinarySearchTree(List<I> items) {
		super();

		if (items == null || items.size() < 1) {
			throw new IllegalArgumentException();
		}

		items = items.stream().filter(item -> item != null).collect(Collectors.toList());

		ArrayList<I> itemsArrayList = new ArrayList<>(items);

		itemsArrayList.sort(null);

		this.root = build(itemsArrayList, 0, itemsArrayList.size() - 1);
	}

	public BinaryNode<I> getMinNode() {
		BinaryNode<I> result = root;

		while (result.left != null) {
			result = result.left;
		}

		return result;
	}

	public BinaryNode<I> getMaxNode() {
		BinaryNode<I> result = root;

		while (result.right != null) {
			result = result.right;
		}

		return result;
	}

	public void insert(I item) {
		insert(root, item);
	}

	public boolean delete(I item) {
		BinaryNode<I> node = search(item);

		if (node == null) {
			return false;
		} else {
			deleteNode(node);
			return true;
		}
	}

	public BinaryNode<I> search(I item) {
		return search(root, item);
	}

	public void inorderTraversal() {
		inorderTraversal(root);
	}

	public void inorderTraversal(BinaryNode<I> node) {
		if (node == null) {
			return;
		}

		inorderTraversal(node.left);
		inorderFunc(node);
		inorderTraversal(node.right);
	}

	protected void inorderFunc(BinaryNode<I> node) {
		System.out.print(node + " ");
	}

	private void insert(BinaryNode<I> node, I item) {
		int compareResult = node.item.compareTo(item);

		if (compareResult < 0) {
			if (node.right == null) {
				node.right = new BinaryNode<I>(item);
				node.right.parent = node;
			} else {
				insert(node.right, item);
			}
		} else if (compareResult > 0) {
			if (node.left == null) {
				node.left = new BinaryNode<I>(item);
				node.left.parent = node;
			} else {
				insert(node.left, item);
			}
		}
	}

	private void deleteNode(BinaryNode<I> node) {
		if (node.left == null) {
			BinaryNode.occupy(node, node.right);
			node.disconnect();
		} else if (node.right == null) {
			BinaryNode.occupy(node, node.left);
			node.disconnect();
		} else {
			BinaryNode<I> leftMost = node.right;

			while (leftMost.left != null) {
				leftMost = leftMost.left;
			}

			BinaryNode.occupy(leftMost, leftMost.right);
			node.item = leftMost.item;

			leftMost.disconnect();
		}
	}

	private BinaryNode<I> search(BinaryNode<I> node, I item) {
		if (node == null) {
			return null;
		}

		int compareResult = node.item.compareTo(item);

		if (compareResult > 0) {
			return search(node.left, item);
		} else if (compareResult < 0) {
			return search(node.right, item);
		} else {
			return node;
		}
	}

	private BinaryNode<I> build(ArrayList<I> items, int start, int end) {
		if (start > end) {
			return null;
		}

		int mid = (start + end) / 2;
		BinaryNode<I> parent = new BinaryNode<>(items.get(mid));

		parent.left = build(items, start, mid - 1);
		parent.right = build(items, mid + 1, end);

		if (parent.left != null) {
			parent.left.parent = parent;
		}

		if (parent.right != null) {
			parent.right.parent = parent;
		}

		return parent;
	}

	public static void main(String[] args) {
		SimpleBinarySearchTree<Integer> tree = new SimpleBinarySearchTree<>(
				Arrays.asList(2, 4, 16, 18, 10, 12, null, 14, 6, 8, null, null, 0));
		System.out.println("Original tree:");
		System.out.println(tree);

		System.out.println("Search an element:");
		System.out.println(tree.search(8));

		System.out.println("get min: " + tree.getMinNode().item);
		System.out.println("get max: " + tree.getMaxNode().item);

		System.out.println("Insert an element:");
		tree.insert(5);
		System.out.println(tree);

		System.out.println("Delete an element:");
		System.out.println(tree.delete(8));
		System.out.println(tree);

		tree.inorderTraversal();
	}

}

package com.qiutongs.datastructure.tree;

import java.util.HashMap;
import java.util.Map;

class BinaryNode<I> {

	public static <I> void occupy(BinaryNode<I> oldNode, BinaryNode<I> newNode) {
		if (oldNode == oldNode.parent.left) {
			oldNode.parent.left = newNode;
		} else {
			oldNode.parent.right = newNode;
		}

		if (newNode != null) {
			newNode.parent = oldNode.parent;
		}
	}

	I item;
	BinaryNode<I> parent = null;
	BinaryNode<I> left = null;
	BinaryNode<I> right = null;

	final Map<String, Object> properties;

	BinaryNode(I item) {
		this.item = item;
		this.properties = new HashMap<>();
	}

	void disconnect() {
		parent = null;
		left = null;
		right = null;
	}

	public String draw() {
		StringBuilder sb = new StringBuilder();

		if (parent != null) {
			if (parent.left == this) {
				sb.append(String.format("  %s\n", parent.item));
				sb.append(" / \n");
			} else {
				sb.append(String.format("%s  \n", parent.item));
				sb.append(" \\ \n");
			}
		}

		sb.append(String.format(" %s \n", item));
		sb.append("/ \\\n");
		sb.append(String.format("%s %s\n", left == null ? " " : left.item, right == null ? " " : right.item));

		return sb.toString();
	}

	@Override
	public String toString() {
		return item.toString();
	}
}

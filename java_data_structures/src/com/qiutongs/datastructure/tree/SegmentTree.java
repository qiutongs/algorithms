package com.qiutongs.datastructure.tree;

import java.util.Arrays;
import com.qiutongs.bit.Bit;

public abstract class SegmentTree<I> {

    protected final I[] nodes;
    protected final I[] items;

    @SuppressWarnings("unchecked")
    public SegmentTree(I[] items) {
	// Each element is initialized to 0
	this.items = items;
	int maxSize = Bit.roundUpToPowerOfTwo(items.length) * 2;
	this.nodes = (I[]) new Object[maxSize];

	buildTree(items, 0, 0, items.length - 1);
    }

    public I query(int startIndex, int endIndex) {
	return query(startIndex, endIndex, 0, 0, items.length - 1);
    }

    public void update(int index, I newItem) {
	update(index, newItem, 0, 0, items.length - 1);
	items[index] = newItem;
    }

    abstract protected I merge(I left, I right);

    abstract protected I update(I curNodeValue, I oldItem, I newItem);

    private I buildTree(I[] items, int curNodeIndex, int startIndex, int endIndex) {
	// This is a leaf
	if (startIndex == endIndex) {
	    nodes[curNodeIndex] = items[startIndex];
	    return nodes[curNodeIndex];
	}

	int leftChildIndex = curNodeIndex * 2 + 1;
	int rightChildIndex = curNodeIndex * 2 + 2;

	int midIndex = (startIndex + endIndex) / 2;
	nodes[curNodeIndex] = merge(buildTree(items, leftChildIndex, startIndex, midIndex),
		buildTree(items, rightChildIndex, midIndex + 1, endIndex));
	return nodes[curNodeIndex];
    }

    private I query(int queryStartIndex, int queryEndIndex, int nodeIndex, int rangeStartIndex, int rangeEndIndex) {
	// query contains the node range
	if (queryStartIndex <= rangeStartIndex && queryEndIndex >= rangeEndIndex) {
	    return nodes[nodeIndex];
	}

	// query outside the node range
	if (queryEndIndex < rangeStartIndex || queryStartIndex > rangeEndIndex) {
	    return null;
	}

	int leftChildIndex = nodeIndex * 2 + 1;
	int rightChildIndex = nodeIndex * 2 + 2;

	int rangeMidIndex = (rangeStartIndex + rangeEndIndex) / 2;
	I leftResult = query(queryStartIndex, queryEndIndex, leftChildIndex, rangeStartIndex, rangeMidIndex);
	I rightResult = query(queryStartIndex, queryEndIndex, rightChildIndex, rangeMidIndex + 1, rangeEndIndex);

	if (leftResult == null) {
	    return rightResult;
	} else if (rightResult == null) {
	    return leftResult;
	} else {
	    return merge(leftResult, rightResult);
	}
    }

    private void update(int updateIndex, I newItem, int nodeIndex, int rangeStartIndex, int rangeEndIndex) {
	if (updateIndex < rangeStartIndex || updateIndex > rangeEndIndex) {
	    return;
	}

	nodes[nodeIndex] = update(nodes[nodeIndex], items[updateIndex], newItem);

	if (rangeStartIndex == rangeEndIndex) {
	    return;
	}

	int leftChildIndex = nodeIndex * 2 + 1;
	int rightChildIndex = nodeIndex * 2 + 2;

	int rangeMidIndex = (rangeStartIndex + rangeEndIndex) / 2;

	if (updateIndex <= rangeMidIndex) {
	    update(updateIndex, newItem, leftChildIndex, rangeStartIndex, rangeMidIndex);
	} else {
	    update(updateIndex, newItem, rightChildIndex, rangeMidIndex + 1, rangeEndIndex);
	}
    }

    @Override
    public String toString() {
	return Arrays.toString(nodes);
    }
}

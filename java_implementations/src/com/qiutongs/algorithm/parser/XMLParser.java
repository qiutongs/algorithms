package com.qiutongs.algorithm.parser;

import java.util.ArrayList;
import java.util.List;

public class XMLParser {

    public Node parse(String xml) {
	Node doc = new Node(NodeType.Document);
	dfs(xml, new int[] { 0 }, doc);
	return doc;
    }

    void dfs(String xml, int[] offset, Node parent) {
	StringBuilder sb = new StringBuilder();
	for (; offset[0] < xml.length(); offset[0]++) {
	    char c = xml.charAt(offset[0]);
	    if (c == '<') {
		if (sb.length() > 0) {
		    parent.childNodes.add(new Node(NodeType.Text, null, sb.toString()));
		    sb.setLength(0);
		}
		// Closing tag
		if (xml.charAt(offset[0] + 1) == '/') {
		    while (xml.charAt(offset[0]) != '>') {
			offset[0]++;
		    }
		    break;
		} else { // Child element
		    int i = offset[0] + 1;
		    while (xml.charAt(i) != '>') {
			i++;
		    }
		    Node child = parseElement(xml.substring(offset[0] + 1, i));
		    parent.childNodes.add(child);
		    offset[0] = i + 1;
		    dfs(xml, offset, child);
		}
	    } else {
		if (c != ' ') {
		    sb.append(c);
		}
	    }
	}
    }
    
    private Node parseElement(String tag) {
	String[] strs = tag.split("\\s+");
	Node ret = new Node(NodeType.Element, strs[0]);
	for (int i = 1; i < strs.length; i++) {
	    String[] pair = strs[i].split("=");
	    pair[1] = pair[1].charAt(0) == '"' ? pair[1].substring(1, pair[1].length() - 1) : pair[1];
	    ret.attributes.add(new Node(NodeType.Attribute, pair[0], pair[1]));
	}
	return ret;
    }

    private enum NodeType {
	Element, Attribute, Text, Document
    }

    private class Node {
	final NodeType type;
	final String name;
	final String value;

	List<Node> childNodes = new ArrayList<>();
	List<Node> attributes = new ArrayList<>();

	private Node(NodeType type) {
	    this(type, null, null);
	}

	private Node(NodeType type, String name) {
	    this(type, name, null);
	}

	private Node(NodeType type, String name, String value) {
	    this.type = type;
	    this.name = name;
	    this.value = value;
	}

	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append('[');
	    sb.append("Type: ");
	    sb.append(type.toString());
	    sb.append(", Name: ");
	    sb.append(name == null ? "#" : name);
	    sb.append(", Value: ");
	    sb.append(value == null ? "null" : value);
	    sb.append(", ");
	    for (Node attr : attributes) {
		sb.append(attr.toString());
		sb.append(',');
	    }
	    for (Node child : childNodes) {
		sb.append(child.toString());
		sb.append(',');
	    }
	    sb.append(']');
	    return sb.toString();
	}
    }

    static String input = "<parent>" + "abc" + "<child attr=x>ctext</child>" + "efg" + "</parent>";

    public static void main(String[] args) {
	XMLParser parser = new XMLParser();
	Node node = parser.parse(input);
	System.out.println(node);
    }
}

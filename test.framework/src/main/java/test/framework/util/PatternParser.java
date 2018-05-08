package test.framework.util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import test.framework.util.ASTNode.NodeType;

public class PatternParser {
	private ASTNode root = new ASTNode(NodeType.GROUP);
	private Stack<ASTNode> nestStack = new Stack<>();
	private StringBuilder sb = new StringBuilder();
	private ASTNode currentTerm = null;

	public ASTNode parsePattern(String pattern) throws ParseException {
		nestStack.push(root);
		for (int i = 0; i < pattern.length(); i++) {
			ASTNode current = nestStack.peek();
			char ch = pattern.charAt(i);
			switch (ch) {
			case '(':
				ASTNode subNode =  new ASTNode(NodeType.GROUP);
				current.addSubNode(subNode);
				nestStack.push(subNode);
				endCurrentTerm();
				break;
			case ')':
				endCurrentTerm();
				nestStack.pop();
				break;
			case '{':
				endCurrentTerm();
				currentTerm = new ASTNode(NodeType.VAR);
				break;
			case '}':
				if (currentTerm == null || currentTerm.getType() != NodeType.VAR) {
					throw new ParseException("'}' has no opening '{'.", i);
				}
				endCurrentTerm();
				break;
			case ' ':
			case '\t':
				if (currentTerm == null || currentTerm.getType() == NodeType.TEXT) {
					endCurrentTerm();
					current.addSubNode(ASTNode.DELEMITER);
				}
				break;
			case '|':
				endCurrentTerm();
				current.addSubNode(ASTNode.PIPE);
				break;
			default:
				sb.append(ch);
			}
		}
		endCurrentTerm();
		groupPipedChildren(root);
		return root;
	}


	private void groupPipedChildren(ASTNode node) {
		List<ASTNode> newSubs = new ArrayList<>();
		ASTNode pipeTarget = null;
		boolean lastTokenPipe = false;
		for (ASTNode subNode : node.getSubNodes()) {
			if (subNode.getType() == NodeType.PIPE) {
				if (pipeTarget == null) {//first pipe
					ASTNode lastSub = newSubs.remove(newSubs.size() - 1);
					pipeTarget = new ASTNode(NodeType.CHOOSE);
					pipeTarget.addSubNode(lastSub);
					newSubs.add(pipeTarget);
				}
				lastTokenPipe = true;
			} else {
				if(lastTokenPipe) {
					pipeTarget.addSubNode(subNode);
				}
				else{
					newSubs.add(subNode);
				}
				pipeTarget = null;
				lastTokenPipe = false;
				groupPipedChildren(subNode);
			}
		}
		if (node.getSubNodes().size() != newSubs.size()) {
			node.getSubNodes().clear();
			node.getSubNodes().addAll(newSubs);
		}
	}

	private void endCurrentTerm() {
		if (sb.length() > 0) {
			if (currentTerm == null) {
				currentTerm = new ASTNode(NodeType.TEXT);
			}
			nestStack.peek().addSubNode(currentTerm);
			currentTerm.setText(sb.toString());
		}
		currentTerm = null;
		sb.setLength(0);
	}
}

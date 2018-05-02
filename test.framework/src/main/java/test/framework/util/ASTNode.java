package test.framework.util;

import java.util.ArrayList;
import java.util.List;

public class ASTNode {
	public enum NodeType {
		GROUP, TEXT, VAR, PIPE, DEL, CHOOSE
	}

	public static final ASTNode DELEMITER = new ASTNode(NodeType.DEL);
	public static final ASTNode PIPE = new ASTNode(NodeType.PIPE);
	
	private Class<?> componentClass;
	private int patternIndex = -1;
	
	private NodeType type;
	private String text;
	private List<ASTNode> subNodes = new ArrayList<>();
	
	
	
	public ASTNode(NodeType type){
		this.type = type;
	}
	
	public NodeType getType() {
		return type;
	}
	public void setType(NodeType type) {
		this.type = type;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<ASTNode> getSubNodes() {
		return subNodes;
	}
//	public void setSubNodes(List<ASTNode> subNodes) {
//		this.subNodes = subNodes;
//	}
	public void addSubNode(ASTNode subNode) {
		this.subNodes.add(subNode);
	}
	
	
	public Class<?> getComponentClass() {
		return componentClass;
	}

	public void setComponentClass(Class<?> componentClass) {
		this.componentClass = componentClass;
	}
	
	
	public int getPatternIndex() {
		return patternIndex;
	}

	public void setPatternIndex(int patternIndex) {
		this.patternIndex = patternIndex;
	}

	@Override
	public String toString() {
		String rslt = type.name();
		if(type==NodeType.TEXT || type==NodeType.VAR) {
			rslt += "(" + text + ")";
		}
		if(subNodes.size()>0) {
			rslt += subNodes;
		}
		return rslt;
	}
}

package test.framework.util;

import test.framework.command.TestCaseCommand;

public class TestCaseCommandMeta {
	private Class<? extends TestCaseCommand> clazz;
	private ASTNode astNode;
	private int indexOfPattern;
	public Class<? extends TestCaseCommand> getClazz() {
		return clazz;
	}
	public void setClazz(Class<? extends TestCaseCommand> clazz) {
		this.clazz = clazz;
	}
	public ASTNode getAstNode() {
		return astNode;
	}
	public void setAstNode(ASTNode astNode) {
		this.astNode = astNode;
	}
	public int getIndexOfPattern() {
		return indexOfPattern;
	}
	public void setIndexOfPattern(int indexOfPattern) {
		this.indexOfPattern = indexOfPattern;
	}
	
}

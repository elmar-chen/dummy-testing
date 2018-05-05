package test.framework.util;

public class UnresovledComponent {
	private Class<?> clazz;
	private String pattern;
	private String patternKey;
	private int patternIdx;
	private ASTNode astNode;
	public Class<?> getClazz() {
		return clazz;
	}
	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public String getPatternKey() {
		return patternKey;
	}
	public void setPatternKey(String patternKey) {
		this.patternKey = patternKey;
	}
	
	public int getPatternIdx() {
		return patternIdx;
	}
	public void setPatternIdx(int patternIdx) {
		this.patternIdx = patternIdx;
	}
	public ASTNode getAstNode() {
		return astNode;
	}
	public void setAstNode(ASTNode astNode) {
		this.astNode = astNode;
	}
	
	
}

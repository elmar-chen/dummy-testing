package test.framework.util;

import test.framework.util.ASTNode.NodeType;

public class PatternUtils {
	public static boolean isStatic(ASTNode.NodeType type) {
		return type == NodeType.DEL || type == NodeType.TEXT;
	}
}

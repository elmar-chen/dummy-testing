package test.framework;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.Test;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;

import test.framework.annotation.CommandToken;
import test.framework.annotation.Pattern;
import test.framework.command.TestCaseCommand;
import test.framework.model.ContextBasedTokenProvider;
import test.framework.model.PageElement;
import test.framework.model.TestSuit;
import test.framework.util.ASTNode;
import test.framework.util.ASTNode.NodeType;
import test.framework.util.PatternParser;

public class Main {

	@Test
	public void execute() throws Exception {

		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		PageParseHandler handler = new PageParseHandler();
		saxParser.parse(this.getClass().getResourceAsStream("/login.page.xml"), handler);
		PageElement page = handler.getLastElement();
		System.out.println(page);
	}

	@Test
	public void parseScript() throws Exception {

		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		ScriptParseHandler handler = new ScriptParseHandler();
		saxParser.parse(this.getClass().getResourceAsStream("/login.script.xml"), handler);
		TestSuit suit = handler.getSuit();
		System.out.println(suit);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void executeCase() throws IOException, ParseException, NoSuchFieldException, SecurityException {
		//TODO: replaced it with asm
		ClassPath cp = ClassPath.from(this.getClass().getClassLoader());
		ImmutableSet<ClassInfo> allClasses = cp.getTopLevelClasses(TestCaseCommand.class.getPackage().getName());
		
		List<Class<? extends TestCaseCommand>>commandClasses = new ArrayList<>();
		for (ClassInfo classInfo : allClasses) {
			Class<?> clazz = classInfo.load();
			if (TestCaseCommand.class.isAssignableFrom(clazz) && !clazz.isInterface()) {
				commandClasses.add((Class<? extends TestCaseCommand>) clazz);
			}
		}
		
		for (Class<? extends TestCaseCommand> commandClass : commandClasses) {
			Pattern anno = commandClass.getAnnotation(Pattern.class);
			for (int i = 0; i < anno.value().length; i++) {
				String patt = anno.value()[i]; 
				System.out.println(commandClass+"--" +patt);
				PatternParser parser = new PatternParser();
				ASTNode parsedPatt = parser.parsePattern(patt);
				
				parsedPatt.setComponentClass(commandClass);
				parsedPatt.setPatternIndex(i);
				
				List<ASTNode> varNodes = gatherVarComponent(parsedPatt);
				
				for (ASTNode varNode : varNodes) {
					String name = varNode.getText();
					Field field = commandClass.getDeclaredField(name);
					Pattern patternAnno = getFieldAnnotation(field, Pattern.class);
					CommandToken tokenAnno = getFieldAnnotation(field, CommandToken.class);
					
					if(patternAnno!=null) {
//						for(int i=0; i<patternAnno.value().length; i++) {
//							String patt=patternAnno.value()[i];
//							System.out.println();
//						}
					} else if(tokenAnno!=null){
						Class<? extends ContextBasedTokenProvider> providerClass = tokenAnno.value();
						System.out.println(providerClass);
					} else if(Enum.class.isAssignableFrom(field.getType())) {
						Object[] enumConstants = field.getType().getEnumConstants();
						for (Object object : enumConstants) {
							Enum<?> em = (Enum<?>) object;
							System.out.println(em.name());
						}
					} else {
						throw new RuntimeException("variable token must be either enum or anno'ed by Pattern or CommandToken.");
					}
				}
			}
		}

	}
	
	private List<ASTNode> gatherVarComponent(ASTNode parsedPatt) {
		List<ASTNode> compositeNodes = new ArrayList<>();
		compositeNodes.add(parsedPatt);
		List<ASTNode> varNodes = new ArrayList<>();
		while (compositeNodes.size() > 0) {
			for (ASTNode compositeNode : compositeNodes) {
				List<ASTNode> newCompositeNodes = new ArrayList<>();
				for (ASTNode astNode : compositeNode.getSubNodes()) {
					if (astNode.getType() == NodeType.VAR) {
						varNodes.add(astNode);
					}
					if (!astNode.getSubNodes().isEmpty()) {
						newCompositeNodes.add(astNode);
					}
				}
				compositeNodes = newCompositeNodes;
			}
		}
		return varNodes;
	}

	private <T extends Annotation> T getFieldAnnotation(Field field, Class<T> clazz) {
		Class<?> fieldClass = field.getType();
		T fieldTokenAnno = field.getAnnotation(clazz);
		if(fieldTokenAnno==null) {
			T[] fieldTokenAnnoArr = fieldClass.getAnnotationsByType(clazz);
			if(fieldTokenAnnoArr.length>0) fieldTokenAnno = fieldTokenAnnoArr[0];
		}
		return fieldTokenAnno;
	}

	// public static void main(String[] args) {
	// System.setProperty("webdriver.chrome.driver", new
	// File("webdrivers/chrome/chromedriver").getAbsolutePath());
	// WebDriver driver = new ChromeDriver();
	// //comment the above 2 lines and uncomment below 2 lines to use Chrome
	// //System.setProperty("webdriver.chrome.driver","G:\\chromedriver.exe");
	// //WebDriver driver = new ChromeDriver();
	//
	// String baseUrl = "http://baidu.com/";
	// String expectedTitle = "Welcome: Mercury Tours";
	// String actualTitle = "";
	//
	// // launch Fire fox and direct it to the Base URL
	// driver.get(baseUrl);
	//
	// // get the actual value of the title
	// WebElement ele = driver.findElement(By.xpath("//*[@id=\"kw\"]"));
	//
	// ele.sendKeys("中国");
	//
	// //close Fire fox
	// driver.close();
	//
	// }

}

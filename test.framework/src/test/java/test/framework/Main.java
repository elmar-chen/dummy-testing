package test.framework;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.lang3.ClassUtils;
import org.junit.Test;

import test.framework.annotation.Pattern;
import test.framework.annotation.PatternToken;
import test.framework.command.TestCaseCommand;
import test.framework.command.pattern.CompositePatternComponent;
import test.framework.command.pattern.ComputablePatternComponent;
import test.framework.command.pattern.EnumPatternComponent;
import test.framework.command.pattern.FixedPatternComponent;
import test.framework.command.pattern.PatternComponent;
import test.framework.command.pattern.RegExpPatternComponent;
import test.framework.model.ContextBasedTokenProvider;
import test.framework.model.PageElement;
import test.framework.model.TestSuit;
import test.framework.util.ASTNode;
import test.framework.util.PatternLoader;
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

	@Test
	public void executeCase() throws Exception {

		List<Class<? extends TestCaseCommand>> commandClasses = PatternLoader.loadCommandPrototypes();

		for (Class<? extends TestCaseCommand> commandClass : commandClasses) {
			PatternComponent comm = parseAndResolvePattern(commandClass, commandClass.getAnnotation(Pattern.class));
			System.out.println(comm);
		}

	}

	private PatternComponent parseAndResolvePattern(Class<?> commandClass, Pattern annoPattern)
			throws ParseException, Exception {
		CompositePatternComponent component = new CompositePatternComponent();
		for (int i = 0; i < annoPattern.value().length; i++) {
			String patt = annoPattern.value()[i];
			// String pattKey = null;
			// int idxSemicolon = patt.indexOf(':');
			// if(idxSemicolon>=0) {
			// pattKey = patt.substring(0, idxSemicolon).trim();
			// pattKey = patt.substring(idxSemicolon+1).trim();
			// }
			PatternParser parser = new PatternParser();
			ASTNode parsedPatt = parser.parsePattern(patt);
			PatternComponent resolvedComponent = resolveComponent(parsedPatt, commandClass);
			component.getSubComponents().add(resolvedComponent);
		}
		return component;
	}

	@SuppressWarnings("unchecked")
	private PatternComponent resolveComponent(ASTNode parsedPatt, Class<?> commandClass) throws Exception {
		switch (parsedPatt.getType()) {
		case DEL:
			return RegExpPatternComponent.delimiterComponent();
		case TEXT:
			return new FixedPatternComponent(parsedPatt.getText());
		case CHOOSE:
		case GROUP:
			CompositePatternComponent component = new CompositePatternComponent();
			for (ASTNode subNode : parsedPatt.getSubNodes()) {
				PatternComponent subComp = resolveComponent(subNode, commandClass);
				component.getSubComponents().add(subComp);
			}
			return component;
		case VAR:
			String name = parsedPatt.getText();
			Field field = commandClass.getDeclaredField(name);
			Pattern patternAnno = getFieldAnnotation(field, Pattern.class);
			PatternToken tokenAnno = getFieldAnnotation(field, PatternToken.class);

			if (patternAnno != null) {
				return parseAndResolvePattern(field.getType(), patternAnno);
			} else if (tokenAnno != null) {
				Class<? extends ContextBasedTokenProvider> providerClass = tokenAnno.value();
				return new ComputablePatternComponent(providerClass);
			} else if (Enum.class.isAssignableFrom(field.getType())) {
				return new EnumPatternComponent((Class<Enum<?>>) field.getType());
			} else if (ClassUtils.isPrimitiveOrWrapper(field.getType())) {
				return new EnumPatternComponent((Class<Enum<?>>) field.getType());
			} else {
				throw new RuntimeException("variable token must be either enum or anno'ed by Pattern or CommandToken.");
			}
		default:
			throw new RuntimeException("Unkown component type.");
		}
	}

	private <T extends Annotation> T getFieldAnnotation(Field field, Class<T> clazz) {
		Class<?> fieldClass = field.getType();
		T fieldTokenAnno = field.getAnnotation(clazz);
		if (fieldTokenAnno == null) {
			T[] fieldTokenAnnoArr = fieldClass.getAnnotationsByType(clazz);
			if (fieldTokenAnnoArr.length > 0)
				fieldTokenAnno = fieldTokenAnnoArr[0];
		}
		if (fieldTokenAnno == null) {
			fieldTokenAnno = field.getType().getAnnotation(clazz);
		}
		return fieldTokenAnno;
	}

}

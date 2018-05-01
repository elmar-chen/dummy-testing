package test.framework.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.asm.ClassReader;
import org.springframework.core.type.classreading.AnnotationMetadataReadingVisitor;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ResourceInfo;

import test.framework.annotation.Pattern;
import test.framework.command.TestCaseCommand;

public class PatternLoader {
	
	@SuppressWarnings("unchecked")
	public static List<Class<? extends TestCaseCommand>> loadCommandPrototypes() throws IOException, ClassNotFoundException {
		List<Class<? extends TestCaseCommand>> list = new ArrayList<>();
		
		ClassLoader ccl = PatternLoader.class.getClassLoader();
		
		ClassPath cp = ClassPath.from(ccl);
		ImmutableSet<ResourceInfo> resources = cp.getResources();
		for (ResourceInfo resourceInfo : resources) {
			String resourceName = resourceInfo.getResourceName();
			if(!resourceName.endsWith(".class"))continue;
			if(resourceName.startsWith("org/openqa/")
					||resourceName.startsWith("org/springframework/")
					||resourceName.startsWith("java")
					||resourceName.startsWith("org/apache/")
					||resourceName.startsWith("com/sun/")
					||resourceName.startsWith("org/eclipse/")
					||resourceName.startsWith("org/google/")
					||resourceName.startsWith("jdk/")
					||resourceName.startsWith("sun/"))continue;
			
			ClassReader cr = new ClassReader(resourceInfo.asByteSource().openStream());
			AnnotationMetadataReadingVisitor visitor = new AnnotationMetadataReadingVisitor(ccl);
			cr.accept(visitor, ClassReader.SKIP_DEBUG|ClassReader.SKIP_CODE);
			if(visitor.hasAnnotation(Pattern.class.getName())) {
				Class<?> clazz = ccl.loadClass(cr.getClassName().replace('/', '.').replace(".class", ""));
				if(TestCaseCommand.class.isAssignableFrom(clazz)) {
					list.add((Class<? extends TestCaseCommand>) clazz);
				}
			}
			
		}
		
		return list;
		
	}
}

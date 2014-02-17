package in.labulle.anycode.engine;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import in.labulle.anycode.uml.IClass;
import in.labulle.anycode.uml.Visibility;
import in.labulle.anycode.uml.impl.AAttribute;
import in.labulle.anycode.uml.impl.AClass;
import in.labulle.anycode.uml.impl.ADataTypePrimitive;
import in.labulle.anycode.uml.impl.APackage;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class GroovyTemplateTest {
	private IClass testClass;

	@Before
	public void initClass() {
		APackage p2 = new APackage();
		p2.setName("in");

		APackage p1 = new APackage();
		p1.setName("labulle");
		p1.setOwner(p2);

		APackage p = new APackage();
		p.setName("test");
		p.setOwner(p1);

		AClass cl = new AClass();
		cl.setName("MyClass");
		cl.setOwner(p);

		AAttribute a1 = new AAttribute();
		a1.setName("name");
		a1.setVisibility(Visibility.PRIVATE);
		a1.setDataType(ADataTypePrimitive.STRING);
		cl.addAttribute(a1);
		a1 = new AAttribute();
		a1.setDataType(ADataTypePrimitive.INTEGER);
		a1.setName("age");
		a1.setVisibility(Visibility.PRIVATE);
		cl.addAttribute(a1);
		this.testClass = cl;
	}

	@Test
	public void testTemplate_1() throws TemplateException,
			InstantiationException, IllegalAccessException, IOException {
		try {
			

			// let's call some method on an instance
			ITemplate t = (ITemplate) new CompositeTemplate();
			Map<String, Object> context = new HashMap<String, Object>();
			context.put(ITemplate.TARGET_DIR_PARAM, getClass().getResource("")
					.getFile());
			context.put("c", this.testClass);
			String namepath = getClass()
					.getResource("/tpl-java-class-name.mde").getFile();
			String contentpath = getClass().getResource(
					"/tpl-java-class-content.mde").getFile();
			t.setNameTemplate(namepath);
			t.setContentTemplate(contentpath);
			t.render(context);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}

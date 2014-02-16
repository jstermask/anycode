package in.labulle.anycode.engine;

import in.labulle.anycode.uml.IClass;
import in.labulle.anycode.uml.impl.AAttribute;
import in.labulle.anycode.uml.impl.AClass;
import in.labulle.anycode.uml.impl.APackage;

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
		cl.addAttribute(a1);
		a1 = new AAttribute();
		a1.setName("age");
		cl.addAttribute(a1);
		this.testClass = cl;
	}

	@Test
	public void testTemplate_1() throws TemplateException {
		Map<String, Object> context = new HashMap<String, Object>();
		context.put(ITemplate.TARGET_DIR_PARAM, getClass().getResource("/").getFile());
		context.put("c", this.testClass);	
		String nameTpl = getClass().getResource("/tpl-java-class-name.mde").getFile();
		String contentTpl = getClass().getResource("/tpl-java-class-content.mde").getFile();
		GroovyTemplate gv = new GroovyTemplate(nameTpl, contentTpl);
		gv.render(context);
	}
	
	

}

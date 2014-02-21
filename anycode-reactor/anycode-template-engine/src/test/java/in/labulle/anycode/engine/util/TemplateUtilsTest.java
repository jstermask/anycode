package in.labulle.anycode.engine.util;

import in.labulle.anycode.engine.exception.InvalidMacroNameException;
import in.labulle.anycode.engine.exception.TemplateRuntimeException;
import in.labulle.anycode.engine.util.TemplateUtils;
import junit.framework.Assert;

import org.junit.Test;


public class TemplateUtilsTest {

	@Test
	public void testTokenizeMacroNoExtension() {
		try {
			String fileName1 = "jpa-persistence.";
			TemplateUtils.tokenizeMacroName(fileName1);
			Assert.fail("We should get an exception");
		} catch (InvalidMacroNameException e) {

		}

	}

	@Test
	public void testTokenizeMacroNoExtension2() {
		String fileName1 = "jpa-persistence";
		try {
			TemplateUtils.tokenizeMacroName(fileName1);
			Assert.fail("We should get an exception");
		} catch (InvalidMacroNameException e) {

		}
	}

	@Test
	public void testTokenizeMacroNoExtension2Path() {
		String fileName1 = "/fr/jjb/jpa-persistence";
		try {
			TemplateUtils.tokenizeMacroName(fileName1);
			Assert.fail("We should get an exception");
		} catch (InvalidMacroNameException e) {

		}
	}

	@Test
	public void testTokenizeMacroNoExtensionPath() {
		try {
			String fileName1 = "/fr/jjb/jpa-persistence.";
			TemplateUtils.tokenizeMacroName(fileName1);
			Assert.fail("We should get an exception");
		} catch (InvalidMacroNameException e) {

		}

	}

	@Test
	public void testTokenizeMacroNoNamespace() {
		String fileName1 = "persistence.mdm";
		try {
			TemplateUtils.tokenizeMacroName(fileName1);
			Assert.fail("We should get an exception");
		} catch (InvalidMacroNameException e) {
		}

	}

	@Test
	public void testTokenizeMacroNoNamespacePath() {
		String fileName1 = "/fr/jjb/persistence.mdm";
		try {
			TemplateUtils.tokenizeMacroName(fileName1);
			Assert.fail("We should get an exception");
		} catch (InvalidMacroNameException e) {
		}

	}

	@Test
	public void testTokenizeMacroOK() throws InvalidMacroNameException {
		String fileName1 = "jpa-persistence-macro.mdm";
		String[] result = TemplateUtils.tokenizeMacroName(fileName1);
		Assert.assertEquals("jpa", result[0]);
		Assert.assertEquals("persistence-macro", result[1]);
		Assert.assertEquals("mdm", result[2]);
	}

	@Test
	public void testTokenizeMacroOKPath() throws InvalidMacroNameException {
		String fileName1 = "/fr/jjb/jpa-persistence-macro.mdm";
		String[] result = TemplateUtils.tokenizeMacroName(fileName1);
		Assert.assertEquals("jpa", result[0]);
		Assert.assertEquals("persistence-macro", result[1]);
		Assert.assertEquals("mdm", result[2]);
	}
	

}

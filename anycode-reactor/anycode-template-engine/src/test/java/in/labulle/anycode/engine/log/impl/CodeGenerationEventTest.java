package in.labulle.anycode.engine.log.impl;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class CodeGenerationEventTest {

	@Test
	public void testFileInfo() {
		File f = new File(getClass().getResource("/merge/custom-codes-01.txt").getFile());
		CodeGenerationEvent event = CodeGenerationEvent.success(f, "templateMock", "test.anycode.MockClass");
		assertEquals("[0KB] " + f.getAbsolutePath(), event.getDetails());
	}

}

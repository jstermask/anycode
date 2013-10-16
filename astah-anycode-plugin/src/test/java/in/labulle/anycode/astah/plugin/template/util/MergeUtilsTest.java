package in.labulle.anycode.astah.plugin.template.util;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.util.Map;

import org.junit.Test;

public class MergeUtilsTest {

	private String getTestContent(String file) throws IOException {
		StringWriter w = new StringWriter();
		URL u = getClass().getResource("/merge/"+file);
		BufferedReader bf = new BufferedReader(new FileReader(u.getFile()));
		try {
			while (bf.ready()) {
				w.append(bf.readLine()).append("\n");
			}
			return w.toString();
		} finally {
			if (bf != null) {
				bf.close();
			}
		}
	}
	
	
	@Test
	public void testCustomCode() throws IOException {
		Map<String, String> codes = MergeUtils.findCustomCodes(getTestContent("custom-codes-01.txt"));
		String value = codes.get("1");
		assertEquals("CUSTOM_CODE_1", value);
		value = codes.get("2");
		assertEquals("CUSTOM_CODE_2", value);
		value = codes.get("3");
		assertEquals("CUSTOM_CODE_3", value);
		value = codes.get("4");
		assertEquals("CUSTOM_CODE_4", value);
		value = codes.get("5");
		assertEquals("CUSTOM_CODE_5\n", value);
		value = codes.get("duplicate");
		assertNull(value);
		value = codes.get("6");
		assertEquals("", value);
		
	}

}

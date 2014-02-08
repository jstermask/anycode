package in.labulle.anycode.editor.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContextSingleton {
	private static final String CTX_FILE = "classpath:context.xml";

	private static ApplicationContext applicationContext;

	public static ApplicationContext getContext() {
		if (applicationContext == null) {
			applicationContext = new ClassPathXmlApplicationContext(CTX_FILE);
		}
		return applicationContext;
	}
}

package in.labulle.anycode.engine.groovy.core

import org.codehaus.groovy.tools.GroovyClass;

import in.labulle.anycode.engine.core.IMacro
import in.labulle.anycode.engine.core.ITemplate;

class GroovyUserClass implements IMacro {
	def File file

	def GroovyUserClass(File f) {
		this.file = f
	}

	def Object loadScript() {
		ClassLoader parent = getClass().getClassLoader()
		GroovyClassLoader loader = new GroovyClassLoader(parent)
		Class groovyClass = loader.parseClass(file)
		return GroovyClass.newInstance()
	}
	
	def String getVarName() {
		def idx = file.getName().indexOf(ITemplate.DIRECTIVE_SUFFIX)
		return file.getName().subSequence(0, idx - 1);
	}
	
	def String getName() {
		return file.getName();
	}

	public Object getInstance() {
		return loadScript()
	}

}

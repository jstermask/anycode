package in.labulle.anycode.engine


import groovy.text.SimpleTemplateEngine

class JpaDirective {
	def static attribute(a) {
		def script = """\
		${a.visibility.toString().toLowerCase()} $a.dataType.name $a.name;
		"""
		return script;
	}
}
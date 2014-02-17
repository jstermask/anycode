package in.labulle.anycode.engine


import groovy.text.SimpleTemplateEngine

class UtilDirective {
	def static datatype(a) {
		def script = """\
		if($a.datatype.simple
		"""
		return script;
	}
}
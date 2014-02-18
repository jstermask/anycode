package in.labulle.anycode.engine


import groovy.text.SimpleTemplateEngine

class UtilDirective extends AnycodeDirective {
	def static datatype(a) {
		def script = """\
		if($a.datatype.simple
		"""
		return script;
	}
}
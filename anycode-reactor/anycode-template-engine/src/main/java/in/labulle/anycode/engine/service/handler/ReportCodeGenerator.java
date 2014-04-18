package in.labulle.anycode.engine.service.handler;

import in.labulle.anycode.engine.config.Configuration;
import in.labulle.anycode.engine.log.ICodeGenerationLog;

public abstract class ReportCodeGenerator implements ICodeGenerator {
	private ICodeGenerationLog codeGenerationLog;

	private Configuration configuration;

	protected ReportCodeGenerator() {

	}

	public ICodeGenerationLog getCodeGenerationLog() {
		return codeGenerationLog;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setCodeGenerationLog(ICodeGenerationLog codeGenerationLog) {
		this.codeGenerationLog = codeGenerationLog;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

}

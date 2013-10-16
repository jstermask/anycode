package in.labulle.anycode.astah.plugin.report.impl;

import in.labulle.anycode.astah.plugin.report.ICodeGenerationEvent;
import in.labulle.anycode.astah.plugin.report.ICodeGenerationLog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class CodeGenerationLog extends Observable implements ICodeGenerationLog {
	private final List<ICodeGenerationEvent> entries = new ArrayList<ICodeGenerationEvent>();
	private Integer progress;
	
	public CodeGenerationLog() {
		this.progress = 0;
	}

	@Override
	public List<ICodeGenerationEvent> getContent() {
		return this.entries;
	}

	@Override
	public ICodeGenerationEvent success(File generatedFile, String templateName,
			String umlElementName) {
		ICodeGenerationEvent evt = CodeGenerationEvent.success(generatedFile, templateName, umlElementName);
		entries.add(evt);
		notifyEvent(evt);
		return evt;
	}

	@Override
	public ICodeGenerationEvent failure(String templateName,
			String umlElementName, Exception failureCause) {
		ICodeGenerationEvent evt = CodeGenerationEvent.failure(templateName, umlElementName, failureCause);
		entries.add(evt);
		notifyEvent(evt);
		return evt;
	}

	@Override
	public Integer getProgress() {
		return progress;
	}
	
	
	public void setProgress(Integer progress) {
		this.progress = progress;
	}
	
	private void notifyEvent(ICodeGenerationEvent evt) {
		setChanged();
		notifyObservers(evt);
	}
	
	public void reset() {
		this.progress = 0;
		this.entries.clear();
		setChanged();
		notifyObservers();
	}

	
}

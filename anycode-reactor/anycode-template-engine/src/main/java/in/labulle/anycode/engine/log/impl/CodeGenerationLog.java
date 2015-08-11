package in.labulle.anycode.engine.log.impl;

import in.labulle.anycode.engine.log.ICodeGenerationEvent;
import in.labulle.anycode.engine.log.ICodeGenerationLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class CodeGenerationLog extends Observable implements ICodeGenerationLog {
	private static final Logger LOG = LoggerFactory.getLogger(CodeGenerationLog.class);

	private final List<ICodeGenerationEvent> entries = new ArrayList<ICodeGenerationEvent>();
	private Integer numberOfachievedGenerations;

	private Integer numberOfGenerations;

	public CodeGenerationLog() {
		this.numberOfachievedGenerations = 0;
		this.numberOfGenerations = 0;
	}

	public List<ICodeGenerationEvent> getContent() {
		return this.entries;
	}

	public ICodeGenerationEvent success(File generatedFile,
			String templateName, String umlElementName) {
		ICodeGenerationEvent evt = CodeGenerationEvent.success(generatedFile,
				templateName, umlElementName);
		entries.add(evt);
		notifyEvent(evt);
		return evt;
	}

	public ICodeGenerationEvent failure(String templateName,
			String umlElementName, Exception failureCause) {
		ICodeGenerationEvent evt = CodeGenerationEvent.failure(templateName,
				umlElementName, failureCause);
		entries.add(evt);
		notifyEvent(evt);
		return evt;
	}

	public Integer getProgress() {
		Integer progress = 0;
		if(numberOfachievedGenerations >= numberOfGenerations && numberOfachievedGenerations > 0) {
			progress = 100;
		} else {
			progress = (int) Math.round((numberOfachievedGenerations * 1.0 / numberOfGenerations * 1.0) * 100.0);
		}
		if(LOG.isDebugEnabled()) {
			LOG.debug(numberOfachievedGenerations + "/" + numberOfGenerations + " => " + progress);
		}
		return progress;
	}

	public void progress() {
		++numberOfachievedGenerations;
	}

	private void notifyEvent(ICodeGenerationEvent evt) {
		setChanged();
		notifyObservers(evt);
	}

	public void reset() {
		this.numberOfachievedGenerations = 0;
		this.entries.clear();
		setChanged();
		notifyObservers();
	}

	public void setNumberOfGenerations(Integer numberOfGenerations) {
		this.numberOfGenerations = numberOfGenerations;
	}
	
	public Integer getNumberOfachievedGenerations() {
		return numberOfachievedGenerations;
	}
	
	public Integer getNumberOfGenerations() {
		return numberOfGenerations;
	}

}

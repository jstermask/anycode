package in.labulle.anycode.astah.plugin.report;

import java.io.File;
import java.util.List;

public interface ICodeGenerationLog {
	ICodeGenerationEvent success(final File outputFile,
			final String templateName, final String umlElementName);

	ICodeGenerationEvent failure(final String templateName,
			final String umlElementName, final Exception failureCause);

	List<ICodeGenerationEvent> getContent();

	void reset();

	public void setProgress(final Integer progress);

	Integer getProgress();
}

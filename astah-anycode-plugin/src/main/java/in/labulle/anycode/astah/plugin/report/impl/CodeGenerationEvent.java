package in.labulle.anycode.astah.plugin.report.impl;

import java.io.File;

import in.labulle.anycode.astah.plugin.osgi.util.BundleUtils;
import in.labulle.anycode.astah.plugin.report.GenerationStatus;
import in.labulle.anycode.astah.plugin.report.ICodeGenerationEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CodeGenerationEvent implements ICodeGenerationEvent {
    private static final Logger LOG = LoggerFactory.getLogger(CodeGenerationEvent.class);

    private final String templateName;
    private final String umlElementName;
    private GenerationStatus status;
    private Exception failureCause;
    private File generatedFile;

    private CodeGenerationEvent(final File generatedFile, final String templateName, final String umlElementName,
            GenerationStatus status) {
        this.generatedFile = generatedFile;
        this.templateName = templateName;
        this.umlElementName = umlElementName;
        this.status = status;
        if (LOG.isDebugEnabled()) {
            LOG.debug(this.toString());
        }
    }

    private CodeGenerationEvent(final File generatedFile, final String templateName, final String umlElementName,
            Exception failure) {
        this(generatedFile, templateName, umlElementName, GenerationStatus.FAILURE);
        this.failureCause = failure;
    }

    public static CodeGenerationEvent success(final File generatedFile, final String templateName,
            final String umlElementName) {
        return new CodeGenerationEvent(generatedFile, templateName, umlElementName, GenerationStatus.SUCCESS);
    }

    public static CodeGenerationEvent failure(final String templateName, final String umlElementName, Exception e) {
        return new CodeGenerationEvent(null, templateName, umlElementName, e);
    }

    @Override
    public GenerationStatus getStatus() {
        return this.status;
    }

    @Override
    public String getTemplateName() {
        return this.templateName;
    }

    @Override
    public String getUmlElementName() {
        return this.umlElementName;
    }

    @Override
    public Exception getFailureCause() {
        return this.failureCause;
    }

    public String toString() {
        return "[TPL|" + this.templateName + "] | [UML|" + this.umlElementName + "] => " + status;
    }

    @Override
    public File getGeneratedFile() {
        return this.generatedFile;
    }

    @Override
    public String getDetails() {
        switch (status) {
        case FAILURE:
            return getCause();
        case SUCCESS:
            return getFileInfo();
        default:
            return null;
        }
    }

    private String getCause() {
        Throwable cause = failureCause;
        String msg = "";
        while (cause != null) {
            msg = cause.getLocalizedMessage();
            cause = cause.getCause();
        }
        return msg;
    }

    private String getFileInfo() {
        if (generatedFile == null || !generatedFile.exists()) {
            return BundleUtils.getMessage("msg_no_file_generated");
        } else {
            return "[" + (generatedFile.length() / 1024) + BundleUtils.getMessage("label_kb") + "] "
                    + generatedFile.getAbsolutePath();
        }
    }

}

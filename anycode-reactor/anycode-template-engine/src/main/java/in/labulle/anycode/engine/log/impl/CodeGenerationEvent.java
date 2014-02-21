package in.labulle.anycode.engine.log.impl;

import in.labulle.anycode.engine.log.GenerationStatus;
import in.labulle.anycode.engine.log.ICodeGenerationEvent;

import java.io.File;
import java.util.ResourceBundle;

public class CodeGenerationEvent implements ICodeGenerationEvent {

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

    public GenerationStatus getStatus() {
        return this.status;
    }

    public String getTemplateName() {
        return this.templateName;
    }

    public String getUmlElementName() {
        return this.umlElementName;
    }

    public Exception getFailureCause() {
        return this.failureCause;
    }

    public String toString() {
        return "[TPL|" + this.templateName + "] | [UML|" + this.umlElementName + "] => " + status;
    }

    public File getGeneratedFile() {
        return this.generatedFile;
    }

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
        ResourceBundle bdl = ResourceBundle.getBundle("anycode-template-engine-messages");
        if (generatedFile == null || !generatedFile.exists()) {
            return bdl.getString("msg_no_file_generated");
        } else {
            return "[" + (generatedFile.length() / 1024) + bdl.getString("label_kb") + "] "
                    + generatedFile.getAbsolutePath();
        }
    }

}

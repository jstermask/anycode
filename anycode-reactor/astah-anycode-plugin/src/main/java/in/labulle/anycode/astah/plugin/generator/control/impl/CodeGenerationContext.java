package in.labulle.anycode.astah.plugin.generator.control.impl;

import in.labulle.anycode.astah.plugin.generator.control.ICodeGenerationContext;
import in.labulle.anycode.engine.log.ICodeGenerationLog;

/**
 * Created by JJB on 8/12/15.
 */
public class CodeGenerationContext implements ICodeGenerationContext {

    private ICodeGenerationLog log;

    private Integer macrosCount;
    private Integer templatesCount;

    private String templatePath;

    private String outputPath;

    private String defaultBrowsePath;



    @Override
    public ICodeGenerationLog getLog() {
        return log;
    }

    public void setLog(ICodeGenerationLog log) {
        this.log = log;
    }

    @Override
    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public void setMacrosCount(Integer macrosCount) {
        this.macrosCount = macrosCount;
    }

    public Integer getMacrosCount() {
        return macrosCount;
    }

    public void setTemplatesCount(Integer templatesCount) {
        this.templatesCount = templatesCount;
    }

    public Integer getTemplatesCount() {
        return templatesCount;
    }

    @Override
    public void setTemplatesAndMacrosCount(Integer macros, Integer templates) {
        setMacrosCount(macros);
        setTemplatesCount(templates);
    }

    @Override
    public void setDefaultBrowsePath(String defaultBrowsePath) {
        this.defaultBrowsePath = defaultBrowsePath;
    }

    public String getDefaultBrowsePath() {
        return defaultBrowsePath;
    }

    @Override
    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    @Override
    public void addError(String msg) {
        System.err.println(msg);
    }
}

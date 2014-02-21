package in.labulle.anycode.engine.freemarker.core;


import in.labulle.anycode.engine.config.Configuration;
import in.labulle.anycode.engine.core.ITemplate;
import in.labulle.anycode.engine.exception.TemplateException;
import in.labulle.anycode.engine.exception.TemplateRenderingException;
import in.labulle.anycode.engine.exception.TemplateRuntimeException;
import in.labulle.anycode.engine.util.MergeUtils;
import in.labulle.anycode.uml.IClass;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

/**
 * A generation template consist
 * 
 * @author A44447
 * 
 */
public class Template implements ITemplate {

	public static final String MDA_EXTENSION = "mdc";

	public static final String MDA_OVERWRITE_EXTENSION = "mda";

	public static final String MDM_EXTENSION = "mdm";

	public static final String OUTPUT_FILE_CONTEXT_PARAM = "fr.jjb.astah.plugin.template.Template.outputFile";

	/**
	 * Template file
	 */
	private final freemarker.template.Template template;

	public Template(freemarker.template.Template tpl) {
		this.template = tpl;
	}

	protected IClass getClassToRender(Map<String, Object> context) {
		return ((IClass) context.get(Configuration.CONTEXT_PARAM_CLASS_CURRENT));
	}


	public boolean overrides() {
		return template.getName().endsWith(MDA_OVERWRITE_EXTENSION);
	}

	/**
	 * @return file content
	 * @throws TemplateRuntimeException
	 */
	public String renderAsString(Map<String, Object> context)
			throws TemplateException {
		try {
			StringWriter strWriter = new StringWriter();
			this.template.process(context, strWriter);
			strWriter.flush();
			String result = strWriter.toString();
			if ("".equalsIgnoreCase(result.trim())) {
				return null;
			} else {
				return result;
			}
		} catch (Exception e) {
			TemplateRenderingException tpe = new TemplateRenderingException(e,
					getClassToRender(context) == null ? "null" : getClassToRender(context).getName(), this);
			throw tpe;

		}
	}

	public File renderToFile(Map<String, Object> context)
			throws TemplateException {
		String filePath = (String) context.get(OUTPUT_FILE_CONTEXT_PARAM);
		if (filePath == null || "".equals(filePath.trim())) {
			throw new TemplateRenderingException(new IllegalArgumentException(
					"Context param '" + OUTPUT_FILE_CONTEXT_PARAM
							+ "' hasn't been put in context"),
							getClassToRender(context) == null ? "null" : getClassToRender(context).getName(), this);
		}
		File outputFile = new File(filePath);
		if(outputFile.exists()) {
			addCustomCodesToContext(outputFile, context);
		}
		String result = renderAsString(context);
		if (result != null && !"".equals(result.trim())) {
			
			
			if (!outputFile.exists() || (outputFile.exists() && overrides())) {
				outputFile.getParentFile().mkdirs();
				FileWriter writer = null;
				try {
					writer = new FileWriter(outputFile);
					renderToWriter(result, writer);
				} catch (IOException e) {
					throw new TemplateRenderingException(e,
					        getClassToRender(context) == null ? "null" : getClassToRender(context).getName(), this);
				} finally {
					if (writer != null) {
						try {
							writer.close();
						} catch (IOException e) {
							
						}
					}
				}
			}
		}
		return outputFile;
	}
	
	protected void addCustomCodesToContext(File outputFile, Map<String, Object> context) {
		BufferedReader bf = null;
		try {
			StringWriter w = new StringWriter();
			bf = new BufferedReader(new FileReader(outputFile));
			while(bf.ready()) {
				w.append(bf.readLine()).append("\n");
			}
			Map<String, String> customCodes = MergeUtils.findCustomCodes(w.toString());
			context.put("customCodes", customCodes);
		} catch (IOException e) {
			
		} finally {
			if(bf != null) {
				try {
					bf.close();
				} catch (IOException e) {
					
				}
			}
		}
	}

	protected void renderToWriter(final String result, Writer writer)
			throws TemplateException {
		if (result != null && !"".equals(result.trim())) {
			try {
				writer.write(result);
				writer.flush();
			} catch (Exception e) {
				throw new TemplateException(e);
			}
		}

	}

	public String toString() {
		return template.getName();
	}

	public String getName() {
		String name = template.getName();
		return name.substring(0, name.indexOf("."));
	}
	

}

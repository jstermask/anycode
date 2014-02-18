package in.labulle.anycode.astah.plugin.template.freemarker.core;

import in.labulle.anycode.astah.plugin.template.api.ITemplate;
import in.labulle.anycode.astah.plugin.template.config.Configuration;
import in.labulle.anycode.astah.plugin.template.exception.TemplateException;
import in.labulle.anycode.astah.plugin.template.exception.TemplateRenderingException;
import in.labulle.anycode.astah.plugin.template.exception.TemplateRuntimeException;
import in.labulle.anycode.astah.plugin.template.util.MergeUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.change_vision.jude.api.inf.model.IClass;

/**
 * A generation template consist
 * 
 * @author A44447
 * 
 */
public class Template implements ITemplate {

	private static final Logger LOG = LoggerFactory.getLogger(Template.class);

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

	@Override
	public boolean overrides() {
		return template.getName().endsWith(MDA_OVERWRITE_EXTENSION);
	}

	/**
	 * @return file content
	 * @throws TemplateRuntimeException
	 */
	@Override
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
					getClassToRender(context), this);
			throw tpe;

		}
	}

	@Override
	public File renderToFile(Map<String, Object> context)
			throws TemplateException {
		String filePath = (String) context.get(OUTPUT_FILE_CONTEXT_PARAM);
		if (filePath == null || "".equals(filePath.trim())) {
			throw new TemplateRenderingException(new IllegalArgumentException(
					"Context param '" + OUTPUT_FILE_CONTEXT_PARAM
							+ "' hasn't been put in context"),
					getClassToRender(context), this);
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
							getClassToRender(context), this);
				} finally {
					if (writer != null) {
						try {
							writer.close();
						} catch (IOException e) {
							if (LOG.isWarnEnabled()) {
								LOG.warn("Writer for file " + filePath
										+ " could not be closed. : "
										+ e.getMessage());
							}
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
			if(LOG.isWarnEnabled()) {
				LOG.warn("addCustomCodesToContext(), map " + e.getMessage());
			}
		} finally {
			if(bf != null) {
				try {
					bf.close();
				} catch (IOException e) {
					LOG.warn("addCustomCodesToContext(), close " + e.getMessage());
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

	@Override
	public String toString() {
		return template.getName();
	}

	@Override
	public String getName() {
		String name = template.getName();
		return name.substring(0, name.indexOf("."));
	}
	

}

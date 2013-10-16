package in.labulle.anycode.astah.plugin.template.util;

import in.labulle.anycode.astah.plugin.template.exception.InvalidMacroNameException;

import java.util.StringTokenizer;

public class TemplateUtils {
    private TemplateUtils() {
    	
    }

    public static String[] tokenizeMacroName(String filePath) throws InvalidMacroNameException {
        String fileName = filePath;
        int sepIndex = fileName.lastIndexOf("/");
        if (sepIndex != -1 && sepIndex < fileName.length()) {
            fileName = fileName.substring(sepIndex + 1);
        }

        String namespace = null;
        String name = null;
        String extension = null;
        StringTokenizer tokenizer = new StringTokenizer(fileName, "-");
        if (tokenizer.countTokens() != 3) {
            throw new InvalidMacroNameException(fileName);
        } else {
            int dashIndex = fileName.indexOf("-");
            int extensionIndex = fileName.lastIndexOf(".");
            if (dashIndex > 0) {
                namespace = fileName.substring(0, dashIndex);
            }
            if (extensionIndex > dashIndex + 1) {
                name = fileName.substring(dashIndex + 1, extensionIndex);
            }
            if (extensionIndex > 0 && extensionIndex < fileName.length()) {
                extension = fileName.substring(extensionIndex + 1);
            }
            return new String[] {namespace, name, extension, fileName };
        }
    }
}

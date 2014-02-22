package in.labulle.anycode.engine.osgi;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImageBundle {

    private static ImageBundle me;

    private final String theme;

    private ImageBundle(final String theme) {
        this.theme = theme;
    }

    private Map<String, URL> images = new HashMap<String, URL>();

    public static ImageBundle initBundle(final String theme) {
        if (me == null || !me.theme.equals(theme)) {
            me = new ImageBundle(theme);
        }
        return me;
    }

    public static ImageBundle getBundle() {
        return me;
    }

    public void addImage(URL imageUrl) {
        String urlFile = imageUrl.getFile();
        String themePath = "/" + theme + "/";
        int index = urlFile.lastIndexOf(themePath);
        if (index != -1) {
            String fileName = urlFile.substring(index + themePath.length());
            images.put(fileName, imageUrl);
        }
    }

    public URL getImageURL(String filename) {
        return images.get(filename);
    }

    public void addImages(List<URL> images) {
        for (URL imageUrl : images) {
            addImage(imageUrl);
        }
    }
}

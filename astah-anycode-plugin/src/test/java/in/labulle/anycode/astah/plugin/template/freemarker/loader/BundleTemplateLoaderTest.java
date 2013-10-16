package in.labulle.anycode.astah.plugin.template.freemarker.loader;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

import freemarker.template.Configuration;

@RunWith(MockitoJUnitRunner.class)
public class BundleTemplateLoaderTest {
    @Mock
    private BundleContext ctx;

    @Mock
    private Bundle bdl;

    @Mock
    private Configuration config;

    private URL[] directives = new URL[] {getClass().getResource("../../lib/test-Testing-module.mdm"),
            getClass().getResource("../../lib/tpt-Templating-module.mdm"),
            getClass().getResource("../../lib/wrongName-module.mdm"),
            getClass().getResource("../../lib/any--module.mdm") };

    @Before
    public void init() {
        when(bdl.findEntries("/", "*.mdm", true)).thenReturn(Collections.enumeration(Arrays.asList(directives)));
        when(bdl.findEntries("/", "test-Testing-module.mdm", true)).thenReturn(
                Collections.enumeration(Arrays.asList(directives[0])));
        when(bdl.findEntries("/", "tpt-Templating-module.mdm", true)).thenReturn(
                Collections.enumeration(Arrays.asList(directives[1])));
        when(ctx.getBundle()).thenReturn(bdl);
    }

    @Test
    public void testLoad() throws MalformedURLException {
        BundleTemplateLoader ld = new BundleTemplateLoader(ctx);
        Assert.assertEquals(0, ld.load(config).size());
    }

    @Test
    public void testFindTemplateSource() throws IOException {
        BundleTemplateLoader ld = new BundleTemplateLoader(ctx);
        ld.load(config);
        Assert.assertNull(ld.findTemplateSource("pok"));
        Assert.assertNotNull(ld.findTemplateSource("test-Testing-module.mdm"));
    }

    @Test
    public void testCloseTemplateSource() throws IOException {
        BundleTemplateLoader ld = new BundleTemplateLoader(ctx);
        ld.load(config);
        Assert.assertNull(ld.findTemplateSource("pok"));
        InputStream is = (InputStream) ld.findTemplateSource("test-Testing-module.mdm");
        Assert.assertNotNull(is);
        ld.closeTemplateSource(is);
        ld.closeTemplateSource(null);
    }

    @Test
    public void testLastModified() throws IOException {
        BundleTemplateLoader ld = new BundleTemplateLoader(ctx);
        ld.load(config);
        Assert.assertNull(ld.findTemplateSource("pok"));
        InputStream is = (InputStream) ld.findTemplateSource("test-Testing-module.mdm");
        long lmd = ld.getLastModified(is);
        Assert.assertEquals(1375571931028L, lmd);
    }

    @Test
    public void testGetReader() throws IOException {
        BundleTemplateLoader ld = new BundleTemplateLoader(ctx);
        ld.load(config);
        InputStream is = (InputStream) ld.findTemplateSource("test-Testing-module.mdm");
        ld.getReader(is, "UTF-8");
    }

}

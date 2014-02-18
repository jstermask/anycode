<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fn="http://www.w3.org/2006/xpath-functions">
	<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes" omit-xml-declaration="no" />
	<xsl:strip-space elements="*" />
	<xsl:template match="/">
		<document xmlns="http://maven.apache.org/XDOC/2.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/XDOC/2.0 http://maven.apache.org/xsd/xdoc-2.0.xsd">
			<head>
				<title><xsl:value-of select="/directive/@id" /> - <xsl:value-of select="/directive/@name" /> Directive API</title>
			</head>
			<body>
				<xsl:apply-templates mode="summary" />
				<xsl:apply-templates mode="details" />
				<script>
					function toggleDisplay(divId) {
						var divObj = document.getElementById(divId);
						if(divObj) {
							if(divObj.style.display == "none") {
								divObj.style.display = "block";
							} else {
								divObj.style.display = "none";
							}
						}
					}
				</script>
			</body>
		</document>
	</xsl:template>
	<xsl:template match="directive" mode="summary">
		<section name="@{@id} : {@name} directive">
			<subsection name="Summary">
				<p><xsl:value-of select="description" /></p>
				<p>Using a <b>function</b> in a template can be done using <code>&lt;<xsl:value-of select="@id" />.functionName(att1, ..., atti, ..., attn) /&gt; </code>syntax.</p>
				<p>Using a <b>macro</b> in a template can be done using <code>&lt;@<xsl:value-of select="@id" />.macroName att1 atti attn /&gt; </code>syntax.</p>
				
				<table class="table table-bordered">
					<tr>
						<th colspan="2">API Overview</th>
					</tr>
					<xsl:apply-templates select="function" mode="summary">
						<xsl:sort select="@name" data-type="text" order="ascending"/>
					</xsl:apply-templates>
					<xsl:apply-templates select="macro" mode="summary">
						<xsl:sort select="@name" data-type="text" order="ascending"/>
					</xsl:apply-templates>
				</table>
			</subsection>
		</section>
	</xsl:template>
	
	<xsl:template match="directive" mode="details">
		<subsection name="Detailed documentation">	
			<xsl:apply-templates select="function" mode="details">
				<xsl:sort select="@name" data-type="text" order="ascending"/>
			</xsl:apply-templates>
			<xsl:apply-templates select="macro" mode="details">
				<xsl:sort select="@name" data-type="text" order="ascending"/>
			</xsl:apply-templates>
		</subsection>
	</xsl:template>
	
	<xsl:template match="macro" mode="summary">
		<tr>
			<td style="width:40px;">
				<p><img src="../images/macro-icon.png" alt="Macro" title="Macro"/></p>
			</td>
			<td>
				<p>
					<a href="#{@name}">
						<xsl:value-of select="@name" />
					</a>
				    <xsl:for-each select=".//param">&#160;<xsl:value-of select="name" /> </xsl:for-each>
				</p>
				<p class="ml20">
					<xsl:value-of select="description" />
				</p>
			</td>
		</tr>
	</xsl:template>
	
	
	<xsl:template match="function" mode="summary">
		<tr>
			<td style="width:40px;">
				<p><img src="../images/function-icon.png" alt="Function" title="Function"/></p>
			</td>
			<td>
				<p>
					<a href="#{@name}">
						<xsl:value-of select="@name" />
					</a>
					(<xsl:for-each select=".//param"><xsl:value-of select="name" /><xsl:if test="position() != last()">,</xsl:if></xsl:for-each>)
				</p>
				<p class="ml20">
					<xsl:value-of select="description" />
				</p>
			</td>
		</tr>
	</xsl:template>
	<xsl:template match="function" mode="details">
		<fieldset>
			<legend><img src="../images/function-icon.png" alt="Function" title="Function"/><xsl:value-of select="@name" /></legend>
			<div class="docdetail">
				<a name="{@name}"></a>
				<p><xsl:value-of select="description"/></p>
				<p>Parameters</p>
				<xsl:for-each select=".//param">
				<p class="ml20"><xsl:value-of select="name" /> : <xsl:value-of select="description" /></p>
				</xsl:for-each>
				<p>Return</p>
				<p class="ml20"><xsl:value-of select="return" /></p>
				<xsl:if test="snippet">
					<p>Code example</p>
					<source><xsl:value-of select="snippet" /></source>
				</xsl:if>
			</div>
		</fieldset>
	</xsl:template>
	<xsl:template match="macro" mode="details">
		<fieldset>
			<legend><img src="../images/macro-icon.png" alt="Macro" title="Macro"/><xsl:value-of select="@name" /></legend>
			<div class="docdetail">
				<a name="{@name}"></a>
				<p><xsl:value-of select="description"/></p>
				<p>Parameters</p>
				<xsl:for-each select=".//param">
				<p class="ml20"><xsl:value-of select="name" /> : <xsl:value-of select="description" /></p>
				</xsl:for-each>
				<xsl:if test="snippet">
					<p>Code example</p>
					<source><xsl:value-of select="snippet" /></source>
				</xsl:if>
			</div>
		</fieldset>
	</xsl:template>
</xsl:stylesheet>
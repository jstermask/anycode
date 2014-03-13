<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="text" version="1.0" encoding="UTF-8" indent="no" />
	<xsl:strip-space elements="*" />
	<xsl:template match="/">
		<xsl:apply-templates />
	</xsl:template>
	<xsl:template match="directive">
		<xsl:apply-templates select="function | macro">
			<xsl:sort select="@name"/> 
		</xsl:apply-templates>
	</xsl:template>
	<xsl:template match="function | macro">
	
&lt;#--<xsl:value-of select="description" />--&gt;
<xsl:value-of select="code" /></xsl:template>
</xsl:stylesheet>
<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
  <html>
  <body>
  <h2>Classroom:</h2>
  <xsl:apply-templates/>
  </body>
  </html>
</xsl:template>

<xsl:template match="student">
  <p>
  <xsl:apply-templates select="vezeteknev"/>
  <xsl:apply-templates select="keresztnev"/>
  <xsl:apply-templates select="becenev"/>
  <xsl:apply-templates select="fizetes"/>
  </p>
</xsl:template>

<xsl:template match="vezeteknev">
  Vezetéknév: <span style="color:#ff0000">
  <xsl:value-of select="."/></span>
  <br />
</xsl:template>

<xsl:template match="keresztnev">
  Keresztnév: <span style="color:#00ff00">
  <xsl:value-of select="."/></span>
  <br />
</xsl:template>

<xsl:template match="becenev">
  Becenév: <span style="color:#0000ff">
  <xsl:value-of select="."/></span>
  <br />
</xsl:template>

<xsl:template match="fizetes">
  Fizetés: <span style="color:#00ffff">
  <xsl:value-of select="."/></span>
  <br />
</xsl:template>

</xsl:stylesheet>
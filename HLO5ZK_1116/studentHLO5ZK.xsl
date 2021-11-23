<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
<html> 
<body>
  <h2>Hallgatok Adatai</h2>
  <table border="1">
    <tr bgcolor="#9acd32">
      <th style="text-align:left">Vezeteknev</th>
      <th style="text-align:left">Keresztnev</th>
      <th style="text-align:left">Becenev</th>
      <th style="text-align:left">Kor</th>
    </tr>
	    <xsl:for-each select="class/student">
		    <tr>
		      <td><xsl:value-of select="vezeteknev"/></td>
		      <td><xsl:value-of select="keresztnev"/></td>
		      <td><xsl:value-of select="becenev"/></td>
		      <td><xsl:value-of select="kor"/></td>
		    </tr>
	    </xsl:for-each>
  </table>
</body>
</html>
</xsl:template>
</xsl:stylesheet>

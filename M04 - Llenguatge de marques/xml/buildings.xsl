<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" indent="yes"/>
    <xsl:template match="/">
        <html>
            <head>
                <title>Buildings</title>
                <link rel="stylesheet" href="css/all.css"/>
                <xsl:text disable-output-escaping="yes">
                    &lt;script src="shadowPartial.js"&gt;&lt;/script&gt;
                </xsl:text>
            </head>
            <body>
                <xsl:text disable-output-escaping="yes">
                    &lt;shadow-partial data-html="menu.part.html" data-css="menu.part.css"&gt;&lt;/shadow-partial&gt;
                </xsl:text>
                <h1>Buildings</h1>
                <div class = 'container'>
                    <xsl:for-each select="buildings/building">
                        <div class = 'unit'>
                            <h2><xsl:value-of select="name"/></h2>
                            <div class = 'foto'>
                                <img src="{sprite}"></img>
                            </div>
                            <h3>Costs</h3>
                            <ul>
                                <li>Food Cost: <xsl:value-of select="costs/food_cost"/></li>
                                <li>Wood Cost: <xsl:value-of select="costs/wood_cost"/></li>
                                <li>Iron Cost: <xsl:value-of select="costs/iron_cost"/></li>
                            </ul>
                        </div>
                    </xsl:for-each>
                </div>
                <xsl:text disable-output-escaping="yes">
                    &lt;shadow-partial data-html="footer.part.html" data-css="footer.part.css"&gt;&lt;/shadow-partial&gt;
                </xsl:text>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>

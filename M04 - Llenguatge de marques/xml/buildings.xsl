<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" indent="yes"/>

    <xsl:template match="/">
        <html>
            <head>
                <title>Buildings</title>
                <link rel="stylesheet" href="css/buildings.css"/> 
            </head>
            <body>
                <h1>Buildings</h1>
                <xsl:for-each select="buildings/building">
                    <div>
                        <h2><xsl:value-of select="name"/></h2>
                        <img src="{sprite}"></img>
                        <h3>Costs</h3>
                        <ul>
                            <li>Food Cost: <xsl:value-of select="costs/food_cost"/></li>
                            <li>Wood Cost: <xsl:value-of select="costs/wood_cost"/></li>
                            <li>Iron Cost: <xsl:value-of select="costs/iron_cost"/></li>
                        </ul>
                    </div>
                </xsl:for-each>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>

<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" indent="yes"/>

    <xsl:template match="/">
        <html>
            <head>
                <title>Buildings</title>
                <script src="shadowpartial.js"></script>
                <link rel="stylesheet" href="css/all.css"/>
            </head>
            <body>
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
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>

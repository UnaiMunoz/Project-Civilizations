<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" indent="yes"/>
    <xsl:template match="/">
        <html>
            <head>
                <title>Attack Units</title>
                <link rel="stylesheet" href="css/all.css"/>
                <xsl:text disable-output-escaping="yes">
                    &lt;script src="shadowPartial.js"&gt;&lt;/script&gt;
                </xsl:text>
            </head>
            <body>
                <xsl:text disable-output-escaping="yes">
                    &lt;shadow-partial data-html="menu.part.html" data-css="menu.part.css"&gt;&lt;/shadow-partial&gt;
                </xsl:text>
                <h1>Attack Units</h1>
                <div class='container'>
                    <xsl:for-each select="attack_units/unit">
                        <div class='unit'>
                            <h2><xsl:value-of select="name"/></h2>
                            <div class='foto'>
                                <img src="{sprite}" alt="unit image"></img>
                            </div>
                            <p>Base Damage: <xsl:value-of select="base_damage"/></p>
                            <p>Armor: <xsl:value-of select="armour"/></p>
                            <p>Waste Chance: <xsl:value-of select="waste_chance"/>%</p>
                            <p>Attack Again Chance: <xsl:value-of select="attack_again_chance"/>%</p>
                            <h3>Plus Stats</h3>
                            <ul>
                                <li>Armor Technology: <xsl:value-of select="plus_stats/armour_technology"/></li>
                                <li>Attack Technology: <xsl:value-of select="plus_stats/attack_technology"/></li>
                                <li>Armor Experience: <xsl:value-of select="plus_stats/armour_experience"/></li>
                                <li>Attack Experience: <xsl:value-of select="plus_stats/attack_experience"/></li>
                                <li>Armor Sanctified: <xsl:value-of select="plus_stats/armour_sanctified"/></li>
                                <li>Attack Sanctified: <xsl:value-of select="plus_stats/attack_sanctified"/></li>
                            </ul>
                            <h3>Costs</h3>
                            <ul>
                                <li>Food Cost: <xsl:value-of select="costs/food_cost"/></li>
                                <li>Wood Cost: <xsl:value-of select="costs/wood_cost"/></li>
                                <li>Iron Cost: <xsl:value-of select="costs/iron_cost"/></li>
                                <li>Mana Cost: <xsl:value-of select="costs/mana_cost"/></li>
                            </ul>
                        </div>
                    </xsl:for-each>
                </div>
                

                <!-- Añadimos el segundo elemento shadow-partial al final del body -->
                <xsl:text disable-output-escaping="yes">
                    &lt;shadow-partial data-html="footer.part.html" data-css="footer.part.css"&gt;&lt;/shadow-partial&gt;
                </xsl:text>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>

<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" indent="yes"/>
    <xsl:template match="/">
        <html>
            <head>
                <title>Defense Units</title>
                <link rel="stylesheet" href="css/defences.css"/>
            </head>
            <body>
                <h1>Defense Units</h1>
                <xsl:for-each select="defence_units/unit">
                    <div>
                        <h2><xsl:value-of select="name"/></h2>
                        <img src="{sprite}"></img>
                        <p>Base Damage: <xsl:value-of select="base_damage"/></p>
                        <p>Armour: <xsl:value-of select="armour"/></p>
                        <p>Waste Chance: <xsl:value-of select="waste_chance"/>%</p>
                        <p>Attack Again Chance: <xsl:value-of select="attack_again_chance"/>%</p>
                        <h3>Plus Stats</h3>
                        <ul>
                            <li>Armour Technology: <xsl:value-of select="plus_stats/armour_technology"/></li>
                            <li>Attack Technology: <xsl:value-of select="plus_stats/attack_technology"/></li>
                            <li>Armour Experience: <xsl:value-of select="plus_stats/armour_experience"/></li>
                            <li>Attack Experience: <xsl:value-of select="plus_stats/attack_experience"/></li>
                            <li>Armour Sanctified: <xsl:value-of select="plus_stats/armour_sanctified"/></li>
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
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>

<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <!-- Plantilla principal que genera el índice -->
    <xsl:template match="/root">
        <html>
            <head>
                <title>Índice de Unidades y Edificios</title>
                <link rel="stylesheet" type="text/css" href="index.css"/>
            </head>
            <body>
                <h1>Índice de Unidades y Edificios</h1><br/>
                <ol>
                    <p>ATTACK UNITS:</p>
                    <xsl:apply-templates select="attack_units/unit"/><br/>
                    <p>DEFENSE UNITS:</p>
                    <xsl:apply-templates select="defence_units/unit"/><br/>
                    <p>SPECIAL UNITS:</p>
                    <xsl:apply-templates select="special_units/unit"/><br/>
                    <p>BUILDINGS:</p>
                    <xsl:apply-templates select="buildings/building"/>
                </ol>
            </body>
        </html>
    </xsl:template>

    <!-- Plantilla para unidades de ataque -->
    <xsl:template match="unit[parent::attack_units]">
        <li>
            <a href="{concat(name, '.html')}">
                <xsl:value-of select="name"/>
            </a>
        </li>
    </xsl:template>

    <!-- Plantilla para unidades de defensa -->
    <xsl:template match="unit[parent::defence_units]">
        <li>
            <a href="{concat(name, '.html')}">
                <xsl:value-of select="name"/>
            </a>
        </li>
    </xsl:template>

    <!-- Plantilla para unidades especiales -->
    <xsl:template match="unit[parent::special_units]">
        <li>
            <a href="{concat(name, '.html')}">
                <xsl:value-of select="name"/>
            </a>
        </li>
    </xsl:template>

    <!-- Plantilla para edificios -->
    <xsl:template match="building[parent::buildings]">
        <li>
            <a href="{concat(name, '.html')}">
                <xsl:value-of select="name"/>
            </a>
        </li>
    </xsl:template>
</xsl:stylesheet>

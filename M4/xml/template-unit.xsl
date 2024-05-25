<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:param name="paramName"/>

    <!-- Template principal que llama a los diferentes templates según la raíz del XML -->
    <xsl:template match="/">
        <xsl:choose>
            <xsl:when test="attack_units">
                <xsl:apply-templates select="attack_units"/>
            </xsl:when>
            <xsl:when test="buildings">
                <xsl:apply-templates select="buildings"/>
            </xsl:when>
            <xsl:when test="defence_units">
                <xsl:apply-templates select="defence_units"/>
            </xsl:when>
            <xsl:when test="special_units">
                <xsl:apply-templates select="special_units"/>
            </xsl:when>
        </xsl:choose>
    </xsl:template>

    <!-- Template para unidades de ataque -->
    <xsl:template match="attack_units">
        <html>
            <head>
                <title>Unidad de ataque: <xsl:value-of select="$paramName"/></title>
                <link rel="stylesheet" type="text/css" href="unit.css"/>
            </head>
            <body>
                <xsl:apply-templates select="unit[name=$paramName]"/>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="unit">
        <h1><xsl:value-of select="name"/></h1>
        <p>Daño base: <xsl:value-of select="base_damage"/></p><br/>
        <p>Armadura: <xsl:value-of select="armour"/></p><br/>
        <p>Probabilidad de desperdicio: <xsl:value-of select="waste_chance"/>%</p><br/>
        <p>Probabilidad de atacar de nuevo: <xsl:value-of select="attack_again_chance"/>%</p><br/>
        <p>Plus stats: </p>
        <ul>
            <xsl:apply-templates select="plus_stats/*"/>
        </ul>
        <p>Costes:</p>
        <ul>
            <xsl:apply-templates select="costs/*"/>
        </ul>
        <img src="{sprite}" alt="Imagen de la unidad" class="unit"/>
        <p class="description"><xsl:value-of select="description"/></p>
    </xsl:template>

    <xsl:template match="costs/*">
        <img src="{img/@href}" alt="Icono de {local-name()}" />
        <xsl:value-of select="concat(' ', substring-after(., ' '))"/>
        <xsl:text>&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;</xsl:text>
    </xsl:template>

    <xsl:template match="plus_stats/*">
        <p>
            <img src="{img/@href}" alt="Icono de {local-name()}" />
            <xsl:value-of select="concat(' ', substring-after(., ' '))"/>
        </p>
        <xsl:if test="position() mod 3 = 0">
        <br/><br/>
    </xsl:if>
    </xsl:template>

    <!-- Template para edificios -->
    <xsl:template match="buildings">
        <html>
            <head>
                <title>Edificio: <xsl:value-of select="$paramName"/></title>
                <link rel="stylesheet" type="text/css" href="unit.css"/>
            </head>
            <body>
                <xsl:apply-templates select="building[name=$paramName]"/>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="building">
        <h1><xsl:value-of select="name"/></h1>
        <p><xsl:value-of select="description"/></p><br/>
        <p>Costes:</p>
        <ul>
            <xsl:apply-templates select="costs/*"/>
        </ul>
        <img src="{sprite}" alt="Imagen del edificio" class="building"/>
    </xsl:template>

    <!-- Template para unidades de defensa -->
    <xsl:template match="defence_units">
        <html>
            <head>
                <title>Unidad de defensa: <xsl:value-of select="$paramName"/></title>
                <link rel="stylesheet" type="text/css" href="unit.css"/>
            </head>
            <body>
                <xsl:apply-templates select="unit[name=$paramName]"/>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="defence_units/unit">
        <h1><xsl:value-of select="name"/></h1>
        <p>Daño base: <xsl:value-of select="base_damage"/></p><br/>
        <p>Armadura: <xsl:value-of select="armour"/></p><br/>
        <p>Probabilidad de desperdicio: <xsl:value-of select="waste_chance"/>%</p><br/>
        <p>Probabilidad de atacar de nuevo: <xsl:value-of select="attack_again_chance"/>%</p><br/>
        <p>Plus stats: </p>
        <ul>
            <xsl:apply-templates select="plus_stats/*"/>
        </ul>
        <p>Costes:</p>
        <ul>
            <xsl:apply-templates select="costs/*"/>
        </ul>
        <img src="{sprite}" alt="Imagen de la unidad" class="unit"/>
        <p class="description"><xsl:value-of select="description"/></p>
    </xsl:template>

    <!-- Template para unidades especiales -->
    <xsl:template match="special_units">
        <html>
            <head>
                <title>Unidad Especial: <xsl:value-of select="$paramName"/></title>
                <link rel="stylesheet" type="text/css" href="unit.css"/>
            </head>
            <body>
                <xsl:apply-templates select="unit[name=$paramName]"/>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="special_units/unit">
        <h1><xsl:value-of select="name"/></h1>
        <p>Daño base: <xsl:value-of select="base_damage"/></p><br/>
        <p>Armadura: <xsl:value-of select="armour"/></p><br/>
        <p>Probabilidad de desperdicio: <xsl:value-of select="waste_chance"/>%</p><br/>
        <p>Probabilidad de atacar de nuevo: <xsl:value-of select="attack_again_chance"/>%</p><br/>
        <p>Plus stats: </p>
        <ul>
            <xsl:apply-templates select="plus_stats/*"/>
        </ul>
        <p>Costes:</p>
        <ul>
            <xsl:apply-templates select="costs/*"/>
        </ul>
        <img src="{sprite}" alt="Imagen de la unidad" class="unit"/>
        <p class="description"><xsl:value-of select="description"/></p>
    </xsl:template>
</xsl:stylesheet>

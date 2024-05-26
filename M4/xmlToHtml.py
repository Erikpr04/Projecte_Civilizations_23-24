from lxml import etree
import os

# Leer un archivo XML
def read_xml(path):
    with open(path, 'r', encoding='utf-8') as file:
        string = file.read()
    return bytes(bytearray(string, encoding='utf-8'))

# Escribir un archivo HTML
def write_html(path, html):
    with open(path, 'w', encoding='utf-8') as file:
        file.write(html)

# Transformar una unidad de XML a HTML
def transform_unit(xmlTree, name):
    # Crear el árbol XSL
    xslunit = read_xml('./xml/template-unit.xsl')
    xslTree = etree.XML(xslunit)

    # Transformar el archivo de datos según el archivo XSLT y guardarlo en un .html
    transform = etree.XSLT(xslTree)
    htmlDom = transform(xmlTree, paramName=etree.XSLT.strparam(name))

    if htmlDom is None:
        return

    htmlResult = etree.tostring(htmlDom, pretty_print=True).decode('utf-8')
    write_html(f"./html/{name}.html", htmlResult)


# Crear un índice
def transform_index_receptes():
    # Crear el árbol XSL para el índice
    xslIndex = read_xml('./xml/template.xsl')
    xslTreeIndex = etree.XML(xslIndex)

    # Leer y combinar los archivos XML especificados
    xml_files = [
        './xml/attack_units.xml',
        './xml/defences.xml',
        './xml/special_units.xml',
        './xml/buildings.xml'
    ]

    combined_root = etree.Element("root")

    for xml_file in xml_files:
        xml_data = read_xml(xml_file)
        xml_tree = etree.XML(xml_data)
        combined_root.append(xml_tree)

    combined_tree = etree.ElementTree(combined_root)

    # Transformar el árbol combinado según el template XSLT
    transform = etree.XSLT(xslTreeIndex)
    htmlDom = transform(combined_tree)
    htmlResult = etree.tostring(htmlDom, pretty_print=True).decode('utf-8')
    
    # Escribir el resultado HTML
    write_html("./html/index.html", htmlResult)



# Borrar los archivos .html creados anteriormente
for file in os.listdir("./html/"):
    if file.endswith(".html"):
        os.remove(os.path.join("./html/", file))


# ATTACK_UNITS:
xml = read_xml('./xml/attack_units.xml')
xmlTree = etree.XML(xml)

# Generar cada una de las unidades en archivos .html
for unit in xmlTree.findall('unit'):
    unit_name = unit.findtext('name')
    transform_unit(xmlTree, unit_name)
    
    

# DEFENSE_UNITS:
xml = read_xml('./xml/defences.xml')
xmlTree = etree.XML(xml)

# Generar cada una de las unidades en archivos .html
for unit in xmlTree.findall('unit'):
    unit_name = unit.findtext('name')
    transform_unit(xmlTree, unit_name)
    

# SPECIAL_UNITS:
xml = read_xml('./xml/special_units.xml')
xmlTree = etree.XML(xml)

# Generar cada una de las unidades en archivos .html
for unit in xmlTree.findall('unit'):
    unit_name = unit.findtext('name')
    transform_unit(xmlTree, unit_name)
    
    
    
# BUILDINGS:
xml = read_xml('./xml/buildings.xml')
xmlTree = etree.XML(xml)

# Generar cada una de las unidades en archivos .html
for unit in xmlTree.findall('building'):
    unit_name = unit.findtext('name')
    transform_unit(xmlTree, unit_name)


# Generar el índice HTML
transform_index_receptes()
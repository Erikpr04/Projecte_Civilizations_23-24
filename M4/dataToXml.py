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

# Crear un índice HTML con todos los XML especificados
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

# Generar el índice HTML
transform_index_receptes()

import lxml.etree as ET

def transform_xml_to_html(xml_file, xsl_file, output_html_file):
    # Parse the XML and XSL files
    xml = ET.parse(xml_file)
    xsl = ET.parse(xsl_file)
    
    # Create a transform object
    transform = ET.XSLT(xsl)
    
    # Apply the transform to the XML
    result = transform(xml)
    
    # Write the result to an HTML file
    with open(output_html_file, 'wb') as f:
        f.write(ET.tostring(result, pretty_print=True, method='html'))

if __name__ == '__main__':
    # Define the files
    files = [
        ("attack_units.xml", "attack_units.xsl", "attack_units.html"),
        ("buildings.xml", "buildings.xsl", "buildings.html"),
        ("defence_units.xml", "defence_units.xsl", "defence_units.html"),
        ("special_units.xml", "special_units.xsl", "special_units.html")
    ]
    
    # Transform each XML file to HTML
    for xml_file, xsl_file, output_html_file in files:
        transform_xml_to_html(xml_file, xsl_file, output_html_file)
        print(f'Transformed {xml_file} using {xsl_file} to {output_html_file}')

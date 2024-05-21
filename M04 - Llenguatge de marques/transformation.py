import os
from lxml import etree

# Directory containing XML and XSL files
xml_dir = 'M04 - Llenguatge de marques\\xml'

# Iterate through all files in the XML directory
for filename in os.listdir(xml_dir):
    if filename.endswith('.xml'):
        xml_file_path = os.path.join(xml_dir, filename)
        xsl_file_path = os.path.join(xml_dir, f"{os.path.splitext(filename)[0]}.xsl")
        
        # Check if the corresponding XSL file exists
        if os.path.exists(xsl_file_path):
            # Load the XSLT file
            xslt = etree.parse(xsl_file_path)
            transform = etree.XSLT(xslt)
            
            # Load the XML file
            try:
                xml = etree.parse(xml_file_path)
            except etree.XMLSyntaxError as e:
                print(f"Error parsing {xml_file_path}: {e}")
                continue
            
            # Perform the transformation
            try:
                result = transform(xml)
            except etree.XSLTApplyError as e:
                print(f"Error transforming {xml_file_path} with {xsl_file_path}: {e}")
                continue
            
            # Save the result to an HTML file
            output_filename = f"{os.path.splitext(filename)[0]}.html"
            output_file_path = os.path.join(xml_dir, output_filename)
            
            with open(output_file_path, 'wb') as f:
                f.write(etree.tostring(result, pretty_print=True, method="html"))

            print(f"Transformed {xml_file_path} to {output_file_path}")
        else:
            print(f"No corresponding XSL file found for {xml_file_path}")


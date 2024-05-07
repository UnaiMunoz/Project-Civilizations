import mysql.connector
import xml.etree.ElementTree as ET

def fetch_data_from_database():
    # Conexión a la base de datos
    connection = mysql.connector.connect(
        host="",
        user="",
        password="",
        database=""
    )
    cursor = connection.cursor()

    # Ejecutar consulta
    cursor.execute("SELECT * FROM tu_tabla")
    data = cursor.fetchall()

    # Cerrar conexión
    cursor.close()
    connection.close()

    return data

def convert_to_xml(data):
    root = ET.Element("data")

    for row in data:
        item = ET.SubElement(root, "item")
        for key, value in row.items():
            sub_item = ET.SubElement(item, key)
            sub_item.text = str(value)

    tree = ET.ElementTree(root)
    tree.write("datos.xml")

data = fetch_data_from_database()
convert_to_xml(data)
print("Se han generado los datos en formato XML.")

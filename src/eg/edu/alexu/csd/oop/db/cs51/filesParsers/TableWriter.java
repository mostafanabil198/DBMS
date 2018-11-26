package eg.edu.alexu.csd.oop.db.cs51.filesParsers;

import java.io.File;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class TableWriter {

	public void saveTable(File table, List<Map<String, String>> tableRows) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// Table elements
			Document doc = docBuilder.newDocument();
			Element tableElement = doc.createElement("Table");
			doc.appendChild(tableElement);

			// Rows
			for (Map<String, String> rowData : tableRows) {
				Element row = doc.createElement("Row");
				tableElement.appendChild(row);
				// Columns
				for (String columnName : rowData.keySet()) {
					Element columnValue = doc.createElement(columnName);
					columnValue.appendChild(doc.createTextNode(rowData.get(columnName)));
					row.appendChild(columnValue);
				}
			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(table);
			transformer.transform(source, result);

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

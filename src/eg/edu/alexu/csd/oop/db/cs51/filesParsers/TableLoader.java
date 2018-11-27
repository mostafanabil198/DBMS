package eg.edu.alexu.csd.oop.db.cs51.filesParsers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

import java.io.File;

import org.xml.sax.SAXException;

public class TableLoader {

	public List<Map<String, String>> load(File path) throws ParserConfigurationException, SAXException, IOException {
		List<Map<String, String>> rowsValues = new ArrayList<Map<String, String>>();
		File fXmlFile = path;
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
		//get all rows of the table 
		NodeList nList = doc.getElementsByTagName("Row");
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			Map<String, String> colValue = new HashMap<String, String>();
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				//load columns with its value in the list as row of map
				NodeList rowsContent = eElement.getChildNodes();
				for (int i = 0; i < rowsContent.getLength(); i++) {
					String key = rowsContent.item(i).getNodeName();
					String value = rowsContent.item(i).getTextContent();
					colValue.put(key, value);
				}
				rowsValues.add(colValue);
			}
		}
		return rowsValues;
	}
}

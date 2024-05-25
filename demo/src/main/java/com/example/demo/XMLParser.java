package com.example.demo;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;

public class XMLParser {
    public static String parseXmlFile(String filePath) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(filePath));

            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("Bill");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    // Assume each Bill element has a Summary child
                    String summary = element.getElementsByTagName("Summary").item(0).getTextContent();
                    // Now you have the summary text for each bill
                    // You can call your analysis method here
                    return summary;
                }
                return "never found ElementNode";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "error2";
    }

    // Dummy method for classification - to be implemented
    public static void main(String[] args){
        System.out.println(parseXmlFile("/Users/alexmiller/HackStuff/S-245.xml"));
    }
}
package co.marcin.logisim2mc.parser;

import org.bukkit.Location;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogisimFormatParser {
    private final File file;
    private List<LogisimElement> elements = new ArrayList<>();

    public LogisimFormatParser(File file) {
        this.file = file;
    }

    private int[] getLocation(Node n, String name) {
        int x, y;
        NamedNodeMap attr = n.getAttributes();
        String position = attr.getNamedItem(name).getNodeValue();
        position = position.substring(1, position.length() - 1);
        String[] position_split = position.split(",");
        x = Integer.parseInt(position_split[0]);
        y = Integer.parseInt(position_split[1]);
        int[] arr = new int[2];
        arr[0] = x / 10;
        arr[1] = y / 10;
        return arr;
    }

    public void parse() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(this.file);
        document.getDocumentElement().normalize();
        Node circuit = document.getElementsByTagName("circuit").item(0);
        NodeList wires = circuit.getChildNodes();

        for(int i = 0; i < wires.getLength(); i++) {
            Node wire = wires.item(i);

            switch(wire.getNodeName()) {
                case "wire": {
                    int[] from = getLocation(wire, "from");
                    int[] to = getLocation(wire, "to");
                    this.elements.add(new Wire(from[0], from[1], to[0], to[1]));
                    break;
                }
                case "comp": {
                    NamedNodeMap attr = wire.getAttributes();
                    int[] loc = getLocation(wire, "loc");
                    String comp_name = attr.getNamedItem("name").getNodeValue();

                    if(comp_name.equals("NAND Gate")) {
                        this.elements.add(new NAND(loc[0], loc[1]));
                    }
                    else if(comp_name.equals("Pin")) {
                        boolean output = false;
                        for(int ci = 0; ci < wire.getChildNodes().getLength(); ci++) {
                            Node child = wire.getChildNodes().item(ci);
                            if(!child.hasAttributes()) {
                                continue;
                            }

                            Node nameAttribute = child.getAttributes().getNamedItem("name");

                            if(nameAttribute == null) {
                                continue;
                            }

                            if(nameAttribute.getNodeValue().equals("output")
                                    && child.getAttributes().getNamedItem("val").getNodeValue().equals("true")) {
                                output = true;
                                break;
                            }
                        }

                        if(output) {
                            this.elements.add(new PinOut(loc[0], loc[1]));
                        }
                        else {
                            this.elements.add(new PinIn(loc[0], loc[1]));
                        }
                    }
                    break;
                }
            }
        }

        System.out.println("Elements:");
        for(LogisimElement e : this.elements) {
            System.out.println(e);
        }
        System.out.println("----------");
    }

    public void build(Location location) {
        for(LogisimElement element : elements) {
            element.build(location);
        }
    }
}

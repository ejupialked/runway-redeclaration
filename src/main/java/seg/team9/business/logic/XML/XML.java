package seg.team9.business.logic.XML;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import seg.team9.business.models.Airport;
import seg.team9.business.models.Obstacle;
import seg.team9.utils.MockData;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XML implements XMLExporter, XMLImporter{
    private static final Logger logger = LogManager.getLogger("XML");
    private final String airportTag = "airport";
    private final String obstacleTag = "obstacle";
    @Override
    public boolean exportAirport(Airport airport) {
        return false;
    }

    @Override
    public boolean importObstacles(List<Obstacle> obstacles) {
        return false;
    }

    @Override
    public Airport importAirport(File file) {
        return null;
    }

    @Override
    public List<Obstacle> importObstacles(File file) {
        List obstacles = new ArrayList();
        try {

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder =documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName(obstacleTag);
            for(int i = 0;i <nodeList.getLength();i++) {
                Node node = nodeList.item(i);
                logger.info("Reading object " + node.getNodeName());

                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;

                    String obsName = element.getElementsByTagName("name").item(0).getTextContent();
                    Double obsHeight = Double.parseDouble(element.getElementsByTagName("height").item(0).getTextContent());
                    Double obsWidth = Double.parseDouble(element.getElementsByTagName("width").item(0).getTextContent());
                    Double obsDistCenter = Double.parseDouble(element.getElementsByTagName("distancecenter").item(0).getTextContent());
                    Double obsDistR = Double.parseDouble(element.getElementsByTagName("distancerthreshold").item(0).getTextContent());
                    Double obsDistL = Double.parseDouble(element.getElementsByTagName("distancelthreshold").item(0).getTextContent());

                    obstacles.add(new Obstacle(obsName,obsHeight,obsWidth,obsDistCenter,obsDistR,obsDistL));
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return obstacles;
    }
}

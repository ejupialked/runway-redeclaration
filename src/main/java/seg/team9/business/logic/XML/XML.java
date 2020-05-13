package seg.team9.business.logic.XML;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import seg.team9.business.models.Airport;
import seg.team9.business.models.DirectedRunway;
import seg.team9.business.models.Obstacle;
import seg.team9.business.models.Runway;
import seg.team9.controllers.PrimaryWindowController;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XML implements XMLExporter, XMLImporter{
    private static final Logger logger = LogManager.getLogger("XML");
    private final String airportTag = "airport";
    private final String obstacleTag = "obstacle";
    @Override
    public boolean exportAirport(Airport airport,File file) {
        try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element rootElement = document.createElement(airportTag);
            document.appendChild(rootElement);

            Attr name = document.createAttribute("name");
            name.setValue(airport.getName());
            rootElement.setAttributeNode(name);

            Element city = document.createElement("city");
            city.appendChild(document.createTextNode(airport.getCity()));
            rootElement.appendChild(city);

            Element lat = document.createElement("lat");
            lat.appendChild(document.createTextNode(airport.getLatitude().toString()));
            rootElement.appendChild(lat);

            Element longitude = document.createElement("long");
            longitude.appendChild(document.createTextNode(airport.getLongitude().toString()));
            rootElement.appendChild(longitude);

            Element runways = document.createElement("runways");
            rootElement.appendChild(runways);



            for (Runway run :airport.getRunwayList()) {

                Element runway = document.createElement("runway");
                runways.appendChild(runway);

                Element left = document.createElement("left");
                runway.appendChild(left);

                Element designator = document.createElement("designator");
                designator.appendChild(document.createTextNode(run.getLRunway().getDesignator()));
                left.appendChild(designator);

                Element tora = document.createElement("tora");
                tora.appendChild(document.createTextNode(run.getLRunway().getTora().toString()));
                left.appendChild(tora);

                Element toda = document.createElement("toda");
                toda.appendChild(document.createTextNode(run.getLRunway().getToda().toString()));
                left.appendChild(toda);

                Element asda = document.createElement("asda");
                asda.appendChild(document.createTextNode(run.getLRunway().getAsda().toString()));
                left.appendChild(asda);

                Element lda = document.createElement("lda");
                lda.appendChild(document.createTextNode(run.getLRunway().getLda().toString()));
                left.appendChild(lda);

                Element threshold = document.createElement("threshold");
                threshold.appendChild(document.createTextNode(run.getLRunway().getThreshold().toString()));
                left.appendChild(threshold);

                Element clearway = document.createElement("clearway");
                clearway.appendChild(document.createTextNode(run.getLRunway().getClearway().toString()));
                left.appendChild(clearway);

                Element stopway = document.createElement("stopway");
                stopway.appendChild(document.createTextNode(run.getLRunway().getStopway().toString()));
                left.appendChild(stopway);

                Element right = document.createElement("right");
                runway.appendChild(right);

                Element designatorr = document.createElement("designator");
                designatorr.appendChild(document.createTextNode(run.getRRunway().getDesignator()));
                right.appendChild(designatorr);

                Element torar = document.createElement("tora");
                torar.appendChild(document.createTextNode(run.getRRunway().getTora().toString()));
                right.appendChild(torar);

                Element todar = document.createElement("toda");
                todar.appendChild(document.createTextNode(run.getRRunway().getToda().toString()));
                right.appendChild(todar);

                Element asdar = document.createElement("asda");
                asdar.appendChild(document.createTextNode(run.getRRunway().getAsda().toString()));
                right.appendChild(asdar);

                Element ldar = document.createElement("lda");
                ldar.appendChild(document.createTextNode(run.getRRunway().getLda().toString()));
                right.appendChild(ldar);

                Element thresholdr = document.createElement("threshold");
                thresholdr.appendChild(document.createTextNode(run.getRRunway().getThreshold().toString()));
                right.appendChild(thresholdr);

                Element clearwayr = document.createElement("clearway");
                clearwayr.appendChild(document.createTextNode(run.getRRunway().getClearway().toString()));
                right.appendChild(clearwayr);

                Element stopwayr = document.createElement("stopway");
                stopwayr.appendChild(document.createTextNode(run.getRRunway().getStopway().toString()));
                right.appendChild(stopwayr);

                Element length = document.createElement("length");
                length.appendChild(document.createTextNode(String.valueOf(run.getLength())));
                runway.appendChild(length);
            }

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(file);
            try {
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.transform(source,result);
                return true;
            }
            catch (TransformerException e) {
                e.printStackTrace();
                return false;
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean exportObstacles(Obstacle obstacle,File file) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element rootElement = document.createElement(airportTag);
            document.appendChild(rootElement);

            Element eObstacle = document.createElement(obstacleTag);
            rootElement.appendChild(eObstacle);

            Element name = document.createElement("name");
            name.appendChild(document.createTextNode(obstacle.getName()));
            eObstacle.appendChild(name);

            Element height = document.createElement("height");
            height.appendChild(document.createTextNode(obstacle.getHeight().toString()));
            eObstacle.appendChild(height);

            Element width = document.createElement("width");
            width.appendChild(document.createTextNode(obstacle.getWidth().toString()));
            eObstacle.appendChild(width);

            Element distancecenter = document.createElement("distancecenter");
            distancecenter.appendChild(document.createTextNode(obstacle.getDistanceCenter().toString()));
            eObstacle.appendChild(distancecenter);

            Element distancerthreshold = document.createElement("distancerthreshold");
            distancerthreshold.appendChild(document.createTextNode(obstacle.getDistanceRThreshold().toString()));
            eObstacle.appendChild(distancerthreshold);

            Element distancelthreshold = document.createElement("distancelthreshold");
            distancelthreshold.appendChild(document.createTextNode(obstacle.getDistanceLThreshold().toString()));
            eObstacle.appendChild(distancelthreshold);

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(file);
            try {
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.transform(source,result);
                return true;
            }
            catch (TransformerException e) {
                e.printStackTrace();
                return false;
            }
        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Airport importAirport(File file) {
        Airport airport = null;
        try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder =documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            document.getDocumentElement().normalize();
            NodeList airportList = document.getElementsByTagName(airportTag);
            if(airportList.getLength()>1)
                throw new IOException();
            if(airportList.item(0).getNodeType() == Node.ELEMENT_NODE) {
                Element elementAirport = (Element) airportList.item(0);
                String city = elementAirport.getElementsByTagName("city").item(0).getTextContent();
                Double latitude = Double.parseDouble(elementAirport.getElementsByTagName("lat").item(0).getTextContent());
                Double longitude = Double.parseDouble(elementAirport.getElementsByTagName("long").item(0).getTextContent());

                airport = new Airport(elementAirport.getAttribute("name"),city,latitude,longitude);

            }
            NodeList runwayList = document.getElementsByTagName("runway");
            for(int i = 0;i<runwayList.getLength();i++){
                Element runway = (Element) runwayList.item(i);
                String designatorL = runway.getElementsByTagName("designator").item(0).getTextContent();
                String designatorR = runway.getElementsByTagName("designator").item(1).getTextContent();
                Double toraL = Double.parseDouble(runway.getElementsByTagName("tora").item(0).getTextContent());
                Double toraR = Double.parseDouble(runway.getElementsByTagName("tora").item(1).getTextContent());
                Double todaL = Double.parseDouble(runway.getElementsByTagName("toda").item(0).getTextContent());
                Double todaR = Double.parseDouble(runway.getElementsByTagName("toda").item(1).getTextContent());
                Double asdaL = Double.parseDouble(runway.getElementsByTagName("asda").item(0).getTextContent());
                Double asdaR = Double.parseDouble(runway.getElementsByTagName("asda").item(1).getTextContent());
                Double ldaL = Double.parseDouble(runway.getElementsByTagName("lda").item(0).getTextContent());
                Double ldaR = Double.parseDouble(runway.getElementsByTagName("lda").item(1).getTextContent());
                Double thresholdL = Double.parseDouble(runway.getElementsByTagName("threshold").item(0).getTextContent());
                Double thresholdR = Double.parseDouble(runway.getElementsByTagName("threshold").item(1).getTextContent());
                Double clearwayL = Double.parseDouble(runway.getElementsByTagName("clearway").item(0).getTextContent());
                Double clearwayR = Double.parseDouble(runway.getElementsByTagName("clearway").item(1).getTextContent());
                Double stopwayL = Double.parseDouble(runway.getElementsByTagName("stopway").item(0).getTextContent());
                Double stopwayR = Double.parseDouble(runway.getElementsByTagName("stopway").item(1).getTextContent());

                Double length = Double.parseDouble(runway.getElementsByTagName("length").item(0).getTextContent());
                airport.addRunway(new Runway(new DirectedRunway(designatorR,toraR,todaR,asdaR,ldaR,thresholdR,clearwayR,stopwayR),
                        new DirectedRunway(designatorL,toraL,todaL,asdaL,ldaL,thresholdL,clearwayL,stopwayL), length));
            }

        } catch (ParserConfigurationException | SAXException | IOException e){
            e.printStackTrace();
        }
        return airport;
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

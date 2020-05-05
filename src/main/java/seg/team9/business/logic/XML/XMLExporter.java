package seg.team9.business.logic.XML;

import seg.team9.business.models.Airport;
import seg.team9.business.models.Obstacle;

import javax.xml.transform.dom.DOMSource;

public interface XMLExporter {
    /**
     * Export an Aiport to XML
     * @param airport to export
     * @return false if export fails, true otherwise
     */
    public DOMSource exportAirport(Airport airport);

    /**
     * Export a list of obstacles
     * @param obstacle to export
     * @return false if export fails, true otherwise
     */
    public DOMSource exportObstacles(Obstacle obstacle);
}

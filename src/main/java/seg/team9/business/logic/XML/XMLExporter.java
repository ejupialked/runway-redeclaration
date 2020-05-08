package seg.team9.business.logic.XML;

import seg.team9.business.models.Airport;
import seg.team9.business.models.Obstacle;

import javax.xml.transform.dom.DOMSource;
import java.io.File;

public interface XMLExporter {
    /**
     * Export an Aiport to XML
     * @param airport to export
     * @return false if export fails, true otherwise
     */
    public boolean exportAirport(Airport airport,File file);

    /**
     * Export a list of obstacles
     * @param obstacle to export
     * @return false if export fails, true otherwise
     */
    public boolean exportObstacles(Obstacle obstacle, File file);
}

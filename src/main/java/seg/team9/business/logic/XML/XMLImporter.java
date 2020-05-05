package seg.team9.business.logic.XML;

import seg.team9.business.models.Airport;
import seg.team9.business.models.Obstacle;

import java.io.File;
import java.util.List;

public interface XMLImporter {

    /**
     * Import an Airport from a file XML
     * @param file the XML file to import
     * @return the {@code Airport} parsed
     */
    public Airport importAirport(File file);

    /**
     * Import Obstacle(s) from a file XML
     * @param file XML file to import
     * @return the list of obstacles as List<Obstacle> (ArrayList)
     */
     public List<Obstacle> importObstacles(File file);

}

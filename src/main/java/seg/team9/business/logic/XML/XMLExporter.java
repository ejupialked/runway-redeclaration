package seg.team9.business.logic.XML;

import seg.team9.business.models.Airport;
import seg.team9.business.models.Obstacle;

import java.util.List;

public interface XMLExporter {
    /**
     * Export an Aiport to XML
     * @param airport to export
     * @return false if export fails, true otherwise
     */
    public boolean exportAirport(Airport airport);

    /**
     * Export a list of obstacles
     * @param obstacles to export
     * @return false if export fails, true otherwise
     */
    public boolean importObstacles(List<Obstacle> obstacles);
}

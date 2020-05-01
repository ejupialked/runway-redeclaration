package seg.team9.business.logic.XML;

import seg.team9.business.models.Airport;
import seg.team9.business.models.Obstacle;

import java.io.File;
import java.util.List;

public class XML implements XMLExporter, XMLImporter{
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
        return null;
    }
}

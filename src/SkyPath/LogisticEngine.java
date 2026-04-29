package SkyPath;

import edu.princeton.cs.algs4.*;

import java.util.ArrayList;

/**
* The core logic engine for the SkyPath application.
* Manages airport data and flight connections using an EdgeWeightedDigraph.
* Provides optimized pathfinding using Dijkstra's algorithm.
*
* @author Brayden Graham
*/
public class LogisticEngine {
    private final ST<String, Integer> airportLookup;
    private final EdgeWeightedDigraph flightGraph;
    private final Airport[] airports;
    private final ArrayList<Flight> allFlights;
    private int airportCount = 0;

    public LogisticEngine(int maxAirports) {
        this.airportLookup = new ST<>();
        this.flightGraph = new EdgeWeightedDigraph(maxAirports);
        this.airports = new Airport[maxAirports];
        this.allFlights = new ArrayList<>();
    }

    /**
    * Adds an Airport object to the engine's internal registry.
    * Maps the airport's unique String code to its Integer ID in the
    * Symbol Table and stores the object for coordinate retrieval.
    * @param airport The Airport object to be registered in the system.
    */
    public void addAirport(Airport airport) {
        airportLookup.put(airport.getCode(), airport.getID());
        airports[airport.getID()] = airport;
        airportCount++;
    }

    /**
    * Calculates the most efficient route between two airports.
    * @param startCode The three-letter IATA code of the departure airport (e.g., "SLC").
    * @param endCode   The three-letter IATA code of the destination airport (e.g., "LAX").
    * @param mode      The weight criteria used for optimization (PRICE or DURATION).
    * @return An Iterable of DirectedEdges representing the path, or null if no path exists.
    */
    public Iterable<DirectedEdge> findBestPath (String startCode, String endCode, WeightMode mode) {
        Integer start = airportLookup.get(startCode);
        Integer end = airportLookup.get(endCode);

        if(start == null || end == null) return null;

        EdgeWeightedDigraph weightedDigraph = new EdgeWeightedDigraph(airports.length);

        for (Flight flight : allFlights) {
            double weight = (mode == WeightMode.PRICE) ? flight.getPrice() : (double) flight.getDuration();
            weightedDigraph.addEdge(new DirectedEdge(flight.getStartID(), flight.getEndID(), weight));
        }

        DijkstraSP sp = new DijkstraSP(weightedDigraph, start);

        if (!sp.hasPathTo(end)) return null;

        return sp.pathTo(end);
    }

    /**
    * Populates the flight graph from a formatted text file.
    * @param filename The path to the data file containing flight information.
    */
    public void buildGraph(String filename) {
        In in = new In(filename);
        while (!in.isEmpty()) {
            int startAirportID = in.readInt();
            int endAirportID = in.readInt();
            double price = in.readDouble();
            int duration = in.readInt();
            String flightNum = in.readString();

            Flight flight = new Flight(startAirportID, endAirportID, price, duration, flightNum);
            allFlights.add(flight);

            flightGraph.addEdge(new DirectedEdge(startAirportID, endAirportID, flight.getPrice()));
        }
    }

    /**
     * Parses airport data from a formatted text file and populates the registry.
     * Expected format: ID Code City Latitude Longitude
     * @param filename The path to the airports data file.
     */
    public void loadAirports(String filename) {
        In in = new In(filename);
        while (!in.isEmpty()) {
            int id = in.readInt();
            String code = in.readString();
            String city = in.readString();
            double lat = in.readDouble();
            double lon = in.readDouble();

            Airport airport = new Airport(id, code, city, lat, lon);
            this.addAirport(airport);
            airportCount++;
        }
    }

    /**
    * Retrieves the full collection of registered airports.
    * Used primarily by the GUI to access geographical coordinates
    * (latitude and longitude) for rendering the airport network.
    * @return An array containing all Airport objects currently in the engine.
    */
    public Airport[] getAirports() {
        return airports;
    }
}
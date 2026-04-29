package SkyPath;
/**
* Represents a single flight connection between two airports.
* This class acts as a data carrier for the EdgeWeightedDigraph.
*
* @author Brayden Graham
*/
public class Flight {
    private final int startAirportId;
    private final int endAirportId;
    private final Double price;
    private final int duration;
    private final String flightNumber;

    public Flight(int startAirportId, int endAirportId, Double price, int duration, String flightNumber) {
        this.startAirportId = startAirportId;
        this.endAirportId = endAirportId;
        this.price = price;
        this.duration = duration;
        this.flightNumber = flightNumber;
    }

    /**
    * @return The cost of the flight in USD.
    */
    public double getPrice() {
        return price;
    }

    /**
    * @return The travel time in minutes.
    */
    public int getDuration() {
        return duration;
    }

    /**
    * @return The flight number of the flight.
    */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
    * @return The flight start airport ID.
    */
    public int getStartID() {
        return startAirportId;
    }

    /**
    * @return The flight start airport ID.
    */
    public int getEndID() {
        return endAirportId;
    }

    @Override
    public String toString() {
        return flightNumber + ": " + startAirportId + " -> " + endAirportId + " ($" + price + ")";
    }
}

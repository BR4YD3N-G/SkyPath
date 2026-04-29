package SkyPath;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
* JUnit 5 tests for the Flight data model.
* Verifies field initialization and getter accuracy.
* @author Brayden Graham
*/
class FlightTest {

    @Test
    void testFlightInitialization() {
        int startId = 0;
        int endId = 1;
        double price = 299.99;
        int duration = 180;
        String flightNum = "AA123";

        Flight flight = new Flight(startId, endId, price, duration, flightNum);

        assertEquals(startId, flight.getStartID(), "Start ID should match constructor input.");
        assertEquals(endId, flight.getEndID(), "End ID should match constructor input.");
        assertEquals(price, flight.getPrice(), 0.001, "Price should match constructor input.");
        assertEquals(duration, flight.getDuration(), "Duration should match constructor input.");
        assertEquals(flightNum, flight.getFlightNumber(), "Flight number should match constructor input.");
    }

    @Test
    void testPricePrecision() {
        Flight flight = new Flight(0, 1, 150.555, 60, "DL456");

        assertEquals(150.555, flight.getPrice(), 0.001, "Price getter failed on precise decimal.");
    }

    @Test
    void testNegativeValues() {
        Flight flight = new Flight(-1, -5, -10.0, -30, "ERR001");

        assertTrue(flight.getPrice() < 0, "Flight class should accurately store negative prices if provided.");
        assertTrue(flight.getDuration() < 0, "Flight class should accurately store negative durations if provided.");
    }
}
package SkyPath;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.princeton.cs.algs4.DirectedEdge;

/**
 * JUnit 5 tests for SkyPath's LogisticEngine.
 * Verifies that the WeightMode toggle correctly influences Dijkstra's algorithm.
 * @author Brayden Graham
 */
class LogisticEngineTest {
    private LogisticEngine engine;

    @BeforeEach
    void setUp() {
        // Initialize with capacity for the test set
        engine = new LogisticEngine(10);

        // Must load airports before flights so the Symbol Table is populated
        engine.loadAirports("test_data/test_airport_data.txt");
        engine.buildGraph("test_data/test_flight_data.txt");
    }

    @Test
    void testPriceOptimization() {
        // Expected: SLC -> ORD -> JFK ($100 + $100 = $200)
        Iterable<DirectedEdge> path = engine.findBestPath("SLC", "JFK", WeightMode.PRICE);

        assertNotNull(path, "Path should exist.");

        double totalPrice = 0;
        int edgeCount = 0;
        for (DirectedEdge edge : path) {
            totalPrice += edge.weight();
            edgeCount++;
        }

        assertEquals(200.00, totalPrice, 0.001, "Price mode failed to find the cheapest route.");
        assertEquals(2, edgeCount, "Price mode should have selected the 2-leg route via ORD.");
    }

    @Test
    void testDurationOptimization() {
        // Expected: SLC -> JFK (Direct, 240 mins)
        Iterable<DirectedEdge> path = engine.findBestPath("SLC", "JFK", WeightMode.DURATION);

        assertNotNull(path, "Path should exist.");

        double totalDuration = 0;
        int edgeCount = 0;
        for (DirectedEdge edge : path) {
            totalDuration += edge.weight();
            edgeCount++;
        }

        assertEquals(240.0, totalDuration, 0.001, "Duration mode failed to find the fastest route.");
        assertEquals(1, edgeCount, "Duration mode should have selected the direct 1-leg route.");
    }

    @Test
    void testInvalidAirportReturnsNull() {
        assertNull(engine.findBestPath("SLC", "ZZZ", WeightMode.PRICE),
                "Engine should return null when destination code is not in Symbol Table.");

        assertNull(engine.findBestPath("YYY", "JFK", WeightMode.PRICE),
                "Engine should return null when origin code is not in Symbol Table.");
    }

    @Test
    void testGetAirports() {
        Airport[] airports = engine.getAirports();

        // Check that the first airport (SLC, ID 0) was loaded correctly
        assertNotNull(airports[0], "Airport array at index 0 should be populated.");
        assertEquals("SLC", airports[0].getCode(), "Airport code should match the test data file.");
        assertEquals(0, airports[0].getID(), "Airport ID should match its index in the array.");
    }
}
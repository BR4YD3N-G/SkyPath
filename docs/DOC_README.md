# SkyPath - docs
## Technical Documentation & Design Specifications

This directory contains the underlying architectural details and design decisions for the SkyPath optimization engine.

### 1. Class Specifications
Detailed descriptions of the primary classes used in the implementation:

* **`AirportMapGUI`**: The presentation layer. It handles user input via `StdDraw`, manages the `MinPQ` for departure displays, and communicates user requests to the engine.
* **`LogisticEngine`**: The controller. It acts as the bridge between raw data and algorithms. It manages the `ST` (Symbol Table) for $O(\log N)$ airport lookups and the `EdgeWeightedDigraph`.
* **`Airport` & `Flight`**: The data models. These classes encapsulate geographical coordinates and edge weights (price/duration).
* **`WeightMode`**: An Enum used to toggle between `PRICE` and `DURATION` optimization.

### 2. Algorithm Analysis
To meet project requirements, we implemented the following:
* **Dijkstra's Algorithm:** Used to solve the single-source shortest path problem on an edge-weighted directed graph.
* **Dynamic Re-weighting:** The engine dynamically re-builds or re-weights graph edges based on the `WeightMode` enum before running the search.
* **Symbol Table Mapping:** Provides a mapping from `String` IATA codes to `Integer` vertex IDs required by the `algs4` library.

### 3. Data File Format
The engine expects `.txt` input files in the following format:

**Airports (`airports.txt`):**
`ID | Code | City | Latitude | Longitude`

**Flights (`flights.txt`):**
`StartID | EndID | Price | Duration | FlightNumber`

---

## API Reference (LogisticEngine)

| Method                                     | Description                                        |
|:-------------------------------------------|:---------------------------------------------------|
| `findBestPath(String, String, WeightMode)` | Returns `Iterable<DirectedEdge>` using Dijkstra's. |
| `addAirport(Airport)`                      | Registers airport in ST and Array.                 |
| `buildGraph(String)`                       | Parses flight data and populates the graph.        |

---
*Created for the SkyPath Project - A03 Multiplier Submission.*

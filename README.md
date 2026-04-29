# SkyPath: Global Aviation Logistics Optimizer

**SkyPath** is a high-performance application designed to compute optimal flight routes across a global network of airports. By leveraging advanced graph theory and real-time data processing, SkyPath streamlines the logistical challenges of modern travel, ensuring users find the most efficient path—whether they are prioritizing their budget or their time.

---

## Key Features

* **Dual-Optimization Engine:** Toggle between **Price** and **Duration** modes to instantly recalculate the most efficient route based on your current needs.
* **Intuitive Visual Interface:** A dynamic map rendering that transforms abstract data into a clear, interactive network of global hubs.
* **Intelligent Auto-Translation:** Seamlessly maps three-letter IATA airport codes (e.g., JFK, SLC) to internal graph nodes for a user-friendly search experience.
* **Real-Time Logistics:** Utilizes priority-based data structures to manage departure flows and network connectivity.

---

## The Tech Stack

SkyPath was engineered using **Java** and the **algs4** library, focusing on algorithmic efficiency and clean software architecture:

* **Graph Infrastructure:** The global network is modeled as an `EdgeWeightedDigraph`.
* **Pathfinding Algorithm:** Implementation of **Dijkstra’s Algorithm** to solve the single-source shortest path problem with mathematical precision.
* **Data Structures:** * **Symbol Tables (ST):** Used for $O(\log N)$ lookup of airport metadata.
    * **MinPQs:** Leveraged for departure scheduling and algorithmic prioritization.

---

## How to Use

1.  **Launch:** Run the `AirportMapGUI` to initialize the global map.
2.  **Search:** Enter your departure and destination airport codes in the search interface.
3.  **Optimize:** Select your preference (**Cheapest** vs. **Fastest**) via the toggle switch.
4.  **Visualize:** View your highlighted route on the interactive map, complete with flight numbers and total trip metrics.

---

## Project Structure

* `src/SkyPath`: Contains the source code for the Engine, GUI, and Data Models.
* `data/`: Stores the `.txt` database files for airports and flight connections.
* `docs/`: Contains the **Technical Manual**, UML diagrams, and Javadoc documentation.

> For a deep dive into the underlying algorithms, method signatures, and class hierarchies, please refer to the [Technical Documentation in the /docs folder](./docs/DOC_README.md).

---

### Developed By
* **Brayden Graham**
* **Nathan Cieslak**
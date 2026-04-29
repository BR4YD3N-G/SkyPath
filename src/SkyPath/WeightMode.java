package SkyPath;

    /**
    * Defines the optimization modes available for pathfinding.
    * Used by the LogisticEngine to determine edge weights in the graph.
    * PRICE: Minimizes total ticket cost.
    * DURATION: Minimizes total travel time.
    *
    * @author Brayden Graham
    */
public enum WeightMode {
    PRICE,
    DURATION
}

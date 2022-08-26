package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import bearmaps.proj2ab.ExtrinsicMinPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private SolverOutcome outcome;
    private double solutionWeight;
    private LinkedList<Vertex> solution;
    private double explorationTime;
    private int numStatesExplored;
    private ExtrinsicMinPQ<Vertex> fringe;
    private Map<Vertex, Double> distToStart;
    private Map<Vertex, Vertex> edgeTo;
    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout){
        fringe = new ArrayHeapMinPQ<>();
        Stopwatch sw = new Stopwatch();
        numStatesExplored = 0;
        solution = new LinkedList<>();
        distToStart = new HashMap<>();
        edgeTo = new HashMap<>();
        distToStart.put(start, 0.0);
        fringe.add(start, input.estimatedDistanceToGoal(start, end));

        while (fringe.size() != 0){
            Vertex v = fringe.removeSmallest();
            numStatesExplored += 1;
            explorationTime = sw.elapsedTime();

            if (v.equals(end)){
                outcome = SolverOutcome.SOLVED;
                solutionWeight = distToStart(end);

                for (Vertex curr = end; !curr.equals(start); ){
                    solution.addFirst(curr);
                    curr = edgeTo.get(curr);
                }
                solution.addFirst(start);
                return;
            }

            if (explorationTime > timeout){
                outcome = SolverOutcome.TIMEOUT;
                solution = new LinkedList<>();
                solutionWeight = 0;
                return;
            }

            for (WeightedEdge<Vertex> edge : input.neighbors(v)){
                relax(edge, input, end);
            }
        }

        outcome = SolverOutcome.UNSOLVABLE;
        solution = new LinkedList<>();
        numStatesExplored = 0;
        explorationTime = sw.elapsedTime();
    }


    private void relax(WeightedEdge<Vertex> e, AStarGraph<Vertex> input, Vertex goal){
        Vertex from = e.from();
        Vertex to = e.to();
        double potentialDist = distToStart(from) + e.weight();
        if (potentialDist < distToStart(to)){
            setDistToStart(to, potentialDist);
            edgeTo.put(to, from);

            if (fringe.contains(to)){
                fringe.changePriority(to, distToStart(to) + input.estimatedDistanceToGoal(to, goal));
            } else {
              fringe.add(to, distToStart(to) + input.estimatedDistanceToGoal(to, goal));
            }
        }
    }

    private double distToStart(Vertex v){
        if (distToStart.containsKey(v)){
            return distToStart.get(v);
        }
        return Double.POSITIVE_INFINITY;
    }

    private void setDistToStart(Vertex v, double dist){
        distToStart.put(v, dist);
    }


    public SolverOutcome outcome(){
        return outcome;
    }
    public List<Vertex> solution(){
        return solution;
    }
    public double solutionWeight(){
        return solutionWeight;
    }
    public int numStatesExplored(){
        return numStatesExplored;
    }
    public double explorationTime(){
        return explorationTime;
    }
}
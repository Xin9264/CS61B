package creatures;

import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import static huglife.HugLifeUtils.randomEntry;

public class Clorus extends Creature {

    /**
     * Creates a creature with the name N. The intention is that this
     * name should be shared between all creatures of the same type.
     *
     * @param n
     */
    /**
     * color r g b
     */
    private int r, g, b;

    public Clorus(double e) {
        super("cluros");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    public Clorus(){
        this(1);
    }
    @Override
    public void move() {
        energy -= 0.03;
        if(energy < 0){
            energy = 0;
        }
    }

    @Override
    public void attack(Creature c) {
        this.energy = this.energy + c.energy();
    }

    @Override
    public Clorus replicate() {
        energy = energy / 2;
        Clorus little_c = new Clorus(energy);
        return little_c;
    }

    @Override
    public void stay() {
        energy -= 0.01;
        if(energy < 0){
            energy = 0;
        }
    }

    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();
        boolean anyplips = false;

        for(Direction key : neighbors.keySet()){
            if(neighbors.get(key).name().equals("empty")){
                emptyNeighbors.add(key);
            }
            else if(neighbors.get(key).name().equals("plip")){
                anyplips = true;
                plipNeighbors.add(key);
            }
        }
        if(emptyNeighbors.size() == 0){
            return new Action(Action.ActionType.STAY);
        } else if (anyplips) {
            return new Action(Action.ActionType.ATTACK, randomEntry(plipNeighbors));
        }else if (energy >= 1){
            return new Action(Action.ActionType.REPLICATE, randomEntry(emptyNeighbors));
        }
        return new Action(Action.ActionType.MOVE, randomEntry(emptyNeighbors));

    }

    @Override
    public Color color() {
          r = 34;
          g = 0;
          b = 231;
        return color(r, g, b);
    }
}

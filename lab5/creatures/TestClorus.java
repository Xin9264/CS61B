package creatures;

import huglife.*;
import org.junit.Test;

import java.awt.*;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/** Test the Cluros class
 * @author WangXin
 */
public class TestClorus {
    @Test
    public void testBasics(){
        Clorus c = new Clorus(2);
        assertEquals(2, c.energy(), 0.01);
        assertEquals(new Color(34, 0, 231), c.color());
        c.move();
        assertEquals(1.97, c.energy(), 0.01);
        c.move();
        assertEquals(1.94, c.energy(), 0.01);
        c.stay();
        assertEquals(1.93, c.energy(), 0.01);
    }

    @Test
    public void testAttact(){
        Plip p = new Plip(1);
        Clorus c = new Clorus(1);
        c.attack(p);
        assertEquals(2, c.energy(), 0.01);
    }

    @Test
    public void testReplicate(){
        Clorus c = new Clorus(2);
        assertEquals(2, c.energy(), 0.01);
        Clorus little_c = (Clorus) c.replicate();
        assertEquals(1, c.energy(), 0.01);
        assertEquals(1, little_c.energy(), 0.01);
        assertEquals(c.energy(), little_c.energy(), 0.01);
    }

    @Test
    public void testChoose(){
        Clorus c = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());
        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);

//        Clorus c = new Clorus(1.2);
        HashMap<Direction, Occupant> eat_p = new HashMap<Direction, Occupant>();
        eat_p.put(Direction.TOP, new Plip());
        eat_p.put(Direction.BOTTOM, new Empty());
        eat_p.put(Direction.LEFT, new Impassible());
        eat_p.put(Direction.RIGHT, new Impassible());
        Action actual_eat = c.chooseAction(eat_p);
        Action expected_eat = new Action(Action.ActionType.ATTACK, Direction.TOP);
        assertEquals(expected_eat, actual_eat);
        /*
        energy >= 1 replicate to random empty space
         */
        c = new Clorus(1.2);
        HashMap<Direction, Occupant> topEmpty = new HashMap<Direction, Occupant>();
        topEmpty.put(Direction.TOP, new Empty());
        topEmpty.put(Direction.BOTTOM, new Impassible());
        topEmpty.put(Direction.LEFT, new Impassible());
        topEmpty.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.REPLICATE, Direction.TOP);

        assertEquals(expected, actual);

        /**
         * others Move
         */
        c = new Clorus(0.5);
        HashMap<Direction, Occupant> topMove = new HashMap<Direction, Occupant>();
        topMove.put(Direction.TOP, new Empty());
        topMove.put(Direction.BOTTOM, new Impassible());
        topMove.put(Direction.LEFT, new Impassible());
        topMove.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(topMove);
        expected = new Action(Action.ActionType.MOVE, Direction.TOP);
        assertEquals(expected, actual);
    }


}

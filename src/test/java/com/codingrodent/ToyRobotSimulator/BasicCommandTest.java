/*
 * MIT License
 * <p>
 * Copyright (c) 2018
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.codingrodent.ToyRobotSimulator;

import org.junit.*;

import java.util.Optional;

import static com.codingrodent.ToyRobotSimulator.ICommand.Command.*;
import static com.codingrodent.ToyRobotSimulator.ICommand.Facing.*;
import static org.junit.Assert.*;

public class BasicCommandTest {

    // Note: Tests not exhaustive

    private Optional<Robot> r;

    @Before
    public void setUp() throws Exception {
        r = Optional.of(new Robot(2, 2, NORTH));
    }

    @Test
    public void applyMove() {
        var move = new BasicCommand(MOVE);
        r = move.apply(r); // goto 2,3
        assertTrue(r.isPresent());
        assertEquals(2, r.get().getX());
        assertEquals(3, r.get().getY());
        assertEquals(NORTH, r.get().getFacing());
        //
        r = move.apply(r); // goto 2,4
        assertTrue(r.isPresent());
        assertEquals(2, r.get().getX());
        assertEquals(ICommand.BOARD_SIZE - 1, r.get().getY());
        assertEquals(NORTH, r.get().getFacing());
        //
        r = move.apply(r); // no effect
        assertTrue(r.isPresent());
        assertEquals(2, r.get().getX());
        assertEquals(ICommand.BOARD_SIZE - 1, r.get().getY());
        assertEquals(NORTH, r.get().getFacing());
    }

    @Test
    public void applyLeft() {
        var left = new BasicCommand(LEFT);
        r = left.apply(r); // rotate
        assertTrue(r.isPresent());
        assertEquals(2, r.get().getX());
        assertEquals(2, r.get().getY());
        assertEquals(WEST, r.get().getFacing());

    }

    @Test
    public void applyRight() {
        var right = new BasicCommand(RIGHT);
        r = right.apply(r); // rotate
        assertTrue(r.isPresent());
        assertEquals(2, r.get().getX());
        assertEquals(2, r.get().getY());
        assertEquals(EAST, r.get().getFacing());

    }

    @Test
    public void checkValidity() {
        var command = new BasicCommand(RIGHT);
        // valid
        assertTrue(command.isValid(0, 0));
        assertTrue(command.isValid(ICommand.BOARD_SIZE - 1, ICommand.BOARD_SIZE - 1));
        // invalid
        assertFalse(command.isValid(0, -1));
        assertFalse(command.isValid(-1, 0));
        assertFalse(command.isValid(ICommand.BOARD_SIZE, ICommand.BOARD_SIZE));
    }

    @Test
    public void checkRotate() {
        assertEquals(EAST, NORTH.rotateRight());
        assertEquals(SOUTH, EAST.rotateRight());
        assertEquals(WEST, SOUTH.rotateRight());
        assertEquals(NORTH, WEST.rotateRight());
    }

}
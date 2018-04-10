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

import org.junit.Test;

import java.util.Optional;

import static com.codingrodent.ToyRobotSimulator.ICommand.Facing.*;
import static org.junit.Assert.*;

public class PlaceCommandTest {

    // Note: Tests not exhaustive

    @Test
    public void applyValidTest() {
        var r = new Robot(2, 2, SOUTH);
        var ccommand = new PlaceCommand(1, 3, NORTH);
        r = ccommand.apply(Optional.of(r)).get();
        assertEquals(1, r.getX());
        assertEquals(3, r.getY());
        assertEquals(NORTH, r.getFacing());
    }

    @Test
    public void applyInvalidTest() {
        var r = new Robot(2, 4, SOUTH);
        var ccommand = new PlaceCommand(-1, 5, NORTH);
        r = ccommand.apply(Optional.of(r)).get();
        assertEquals(2, r.getX());
        assertEquals(4, r.getY());
        assertEquals(SOUTH, r.getFacing());
    }

    @Test
    public void applyEmptyTest() {
        var ccommand = new PlaceCommand(-1, 5, NORTH);
        assertFalse(ccommand.apply(Optional.empty()).isPresent());
    }

}
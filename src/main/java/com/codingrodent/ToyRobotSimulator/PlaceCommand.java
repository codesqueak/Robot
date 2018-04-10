/*
  MIT License

  Copyright (c) 2018

  Permission is hereby granted, free of charge, to any person obtaining a copy
  of this software and associated documentation files (the "Software"), to deal
  in the Software without restriction, including without limitation the rights
  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
  copies of the Software, and to permit persons to whom the Software is
  furnished to do so, subject to the following conditions:

  The above copyright notice and this permission notice shall be included in all
  copies or substantial portions of the Software.

  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
  SOFTWARE.
 */
package com.codingrodent.ToyRobotSimulator;

import java.util.Optional;

import static com.codingrodent.ToyRobotSimulator.ICommand.Command.PLACE;

public class PlaceCommand extends BasicCommand {

    private final int x;
    private final int y;
    private final Facing facing;

    PlaceCommand(int x, int y, Facing facing) {
        super(PLACE);
        this.x = x;
        this.y = y;
        this.facing = facing;
    }

    /**
     * Apply a PLACE command if valid, otherwise ignore
     *
     * @param robot Robot to have command applied to
     * @return Robot after function application
     */
    @Override
    public Optional<Robot> apply(final Optional<Robot> robot) {
        if (isValid(x, y))
            return Optional.of(new Robot(x, y, facing));
        else
            return robot;
    }
}

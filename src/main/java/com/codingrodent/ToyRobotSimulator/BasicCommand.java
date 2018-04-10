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

class BasicCommand implements ICommand {

    private final Command command;

    BasicCommand(final Command command) {
        this.command = command;
    }

    /**
     * Apply a basic command if valid, otherwise ignore
     *
     * @param robot Robot to have command applied to
     * @return Robot after function application
     */
    @Override
    public Optional<Robot> apply(final Optional<Robot> robot) {
        return robot.map(r -> {
            switch (command) {
                case MOVE: {
                    int x = r.getX() + r.getFacing().getX();
                    int y = r.getY() + r.getFacing().getY();
                    if (isValid(x, y))
                        return Optional.of(new Robot(x, y, r.getFacing()));
                    else
                        return Optional.of(r);
                }
                case LEFT:
                    return Optional.of(new Robot(r.getX(), r.getY(), r.getFacing().rotateRight().rotateRight().rotateRight()));
                case RIGHT:
                    return Optional.of(new Robot(r.getX(), r.getY(), r.getFacing().rotateRight()));
                case REPORT:
                    System.out.println(r.report());
                default:
                    return Optional.of(r);
            }
        }).orElse(Optional.empty());
    }

}

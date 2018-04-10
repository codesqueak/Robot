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

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;

import static com.codingrodent.ToyRobotSimulator.ICommand.Command.PLACE;

public class Simulator {

    // First parameter is file name
    public static void main(String[] args) {
        if (args.length > 0) {
            var simulator = new Simulator();
            simulator.exec(args[0]);
        } else
            System.out.println("No command file specified");
    }

    /**
     * Read in teh list of commands and execute
     *
     * @param filename File containing commands
     */
    private void exec(final String filename) {
        var commands = new LinkedList<ICommand>();
        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            stream.forEach(s -> processLine(s).ifPresent(commands::add));
        } catch (IOException e) {
            System.out.println("Problem with file: " + e.getMessage());
        }
        // Reduce all valid commands down to a single function - and run it!
        var reducedFunction = commands.stream().reduce(r -> r, (a, b) -> ((r) -> b.apply(a.apply(r))));
        reducedFunction.apply(Optional.empty());
    }

    /**
     * Turn a line into a command - some basic syntax checking and error recovery - room for improvement !
     *
     * @param line Line to process
     * @return Command or an empty optional if invalid
     */
    private Optional<ICommand> processLine(final String line) {
        try {
            var parts = line.trim().split(" ");
            var command = ICommand.Command.valueOf(parts[0]);
            if (command == PLACE) {
                var location = line.substring(5).replaceAll("\\s+", "").split(",");
                return Optional.of(new PlaceCommand(Integer.parseInt(location[0]), Integer.parseInt(location[1]), ICommand.Facing.valueOf(location[2])));
            } else
                return Optional.of(new BasicCommand(command));

        } catch (Exception e) {
            return Optional.empty();
        }
    }
}

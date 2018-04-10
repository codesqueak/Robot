[![License: MIT](https://img.shields.io/badge/license-MIT-brightgreen.svg)](https://opensource.org/licenses/MIT)

# Toy Robot Simulator in Java 10

A simple toy robot simulator using Java 10

# Build

(Windows)

*gradlew clean build test*

(Linux)

*./gradlew clean build test*


# Run

Once the code has compiled, an executable jar file can be found in /build/libs

The command to execute is:

*java -jar Robot-0.1.1.jar 'file to process here'*

For example, to use the commands from the example text, use the *commands.txt* file found in the project root directory

*java -jar Robot-0.1.1.jar commands.txt*

Which will give the output:

```
0,1,NORTH
0,0,WEST
3,3,NORTH
```


# Test Data

Various test files are available in the root of the project to verify certain aspects of functionality

## commands.txt

The set of commands from the requirement document

## corrupt.txt

As per commands.txt but with a lot of added garbage to demonstrate some resilience to bad input

## empty.txt

A file wih no valid commands

## report.txt

A file that attempts to REPORT when no valid location has been set

## fall.txt

An attempt to fall off the board








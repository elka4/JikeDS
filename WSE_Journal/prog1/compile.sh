#!/bin/bash

if [ -d "out" ]; then
    rm -R out
fi

mkdir out

javac -cp lib/*:src/* -d out src/*.java

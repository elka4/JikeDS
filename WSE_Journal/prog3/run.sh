#!/bin/bash

if [ -d "out" ]; then
    rm -R out
fi

mkdir out

cd src

javac -cp ../lib/*:. * -d ../out

cd ../out

java -cp ../lib/*:. PageRanker  -docs ../Prog3Example/ -f 0.7
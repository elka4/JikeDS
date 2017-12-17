#!/bin/bash

cd prog1

if [ -d "out" ]; then
    rm -R out
fi

mkdir out

cd src

javac -cp ../lib/*:. * -d ../out

cd ../out

java -cp ../lib/*:. Index ../index -docs ../Prog1ExampleDirectory/
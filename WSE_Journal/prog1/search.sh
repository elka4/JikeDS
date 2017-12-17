#!/bin/bash

cd out

java -cp ../lib/*:./* Search -index index -query machine

cd ..

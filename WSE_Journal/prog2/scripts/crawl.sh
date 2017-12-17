#!/bin/bash

cd ../out

java -cp . crawler -u http://cs.nyu.edu/courses/spring16/CSCI-GA.2580-001/MarineMammal/PolarBear.html -q ocean -docs ../download  -m 3 -t

#java -cp . crawler -u http://cs.nyu.edu/courses/spring16/CSCI-GA.2580-001/MarineMammal/Whale.html -q whale whales species -docs ../download -m 10 -t
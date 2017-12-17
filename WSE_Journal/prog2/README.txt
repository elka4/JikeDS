compile:

javac crawler.java

run:

java crawler -u <url> -q <query> - docs <destination folder> -m <max page number> -t<trace>

eg:

java crawler -u http://cs.nyu.edu/courses/spring16/CSCI-GA.2580-001/MarineMammal/PolarBear.html -q ocean -docs oceanTest\ -m 7 -t


java crawler -u http://cs.nyu.edu/courses/spring16/CSCI-GA.2580-001/MarineMammal/Whale.html -q whale whales species -docs /Users/tianhuizhu/IdeaProjects/WSE_Program/prog2/download/ -m 10 -t

running enviroment: java 1.8.0_131, mac


-u http://cs.nyu.edu/courses/spring16/CSCI-GA.2580-001/MarineMammal/PolarBear.html -q ocean -docs /Users/tianhuizhu/IdeaProjects/WSE_Program/prog2/download -m 7 -t



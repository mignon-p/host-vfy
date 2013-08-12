#!/bin/bash
cd src
find com -name "*.class" | xargs rm -f ../host-vfy.jar
find com -name "*.java" | xargs javac
find com -name "*.class" | xargs zip -9 ../host-vfy.jar

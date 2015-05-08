#!/bin/bash
javac time_Extraction/*.java
java time_Extraction/main $1 $2
echo $1 $2
exit 0
#!/bin/bash
nombre=EnsayosG4D
nombrepaquete=jcolonia.daw2019.g4d
rutapaquete=$(echo $nombrepaquete | sed 's/\./\//g')

CLASSPATH=bin
JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8




echo $nombre
echo ---
echo

javac -sourcepath src -d bin src/$rutapaquete/$nombre.java

#tree -f  $CLASSPATH/$rutapaquete
echo

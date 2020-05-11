#!/bin/bash
nombre=EnsayosG4D
nombrepaquete=jcolonia.daw2019.g4d
rutapaquete=$(echo $nombrepaquete | sed 's/\./\//g')
#args=""

salida=g4d.txt

export CLASSPATH=bin
export JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8

echo $nombrepaquete.$nombre
echo ---
echo

java $nombrepaquete.$nombre $args | tee $salida

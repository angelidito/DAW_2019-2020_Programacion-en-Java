#!/bin/bash
nombre=Casino
nombrepaquete=angelidito.laruleta
rutapaquete=$(echo $nombrepaquete | sed 's/\./\//g')
#args=""

salida=laruleta.txt

export CLASSPATH=bin
export JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8

echo $nombrepaquete.$nombre
echo ---
echo

java $nombrepaquete.$nombre $args | tee $salida

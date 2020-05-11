#!/bin/bash
nombre=Apuesta
nombrepaquete=angelidito.laruleta
rutapaquete=$(echo $nombrepaquete | sed 's/\./\//g')

CLASSPATH=bin
JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8

echo $nombre
echo ---
echo

# https://docs.oracle.com/en/java/javase/11/docs/api/element-list
if [[ ! -f element-list ]]; then
	wget -c https://docs.oracle.com/en/java/javase/11/docs/api/element-list
fi

javadoc -verbose \
	-private \
	-sourcepath src \
	-d doc \
	-linkoffline https://docs.oracle.com/en/java/javase/11/docs/api/java.base/ . \
	-locale es_ES \
	--frames \
	-linksource \
	--no-module-directories \
	-subpackages $nombrepaquete

#tree -f  doc/$rutapaquete
echo

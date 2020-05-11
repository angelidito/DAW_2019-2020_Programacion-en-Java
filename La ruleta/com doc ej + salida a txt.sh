#!/bin/bash

salida=salidaTerminal.txt

echo Compilando: |tee
./compilar.sh | tee -a $salida

echo Documentando: |tee -a
./documentar.sh | tee -a $salida

echo Ejecutando: |tee -a
./ejecutar.sh | tee -a $salida
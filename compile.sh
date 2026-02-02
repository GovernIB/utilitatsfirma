#!/bin/bash

echo off
cat help.txt

env mvn -DskipTests $@ clean install 

if [ $? == 0 ]; then
  if [ "UTILITATSFIRMA_DEPLOY_DIR" == "" ];  then

    echo  =================================================================
    echo    Definex la variable d\'entorn UTILITATSFIRMA_DEPLOY_DIR apuntant al
    echo    directori de deploy del JBOSS  i automaticament s\'hi copiara
    echo    l\'ear generat.
    echo  =================================================================
  
  else
  
    echo on
    echo --------- COPIANT EAR ---------
    cp ./utilitatsfirma-ear/target/utilitatsfirma.ear $UTILITATSFIRMA_DEPLOY_DIR

  fi
fi



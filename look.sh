#!/bin/bash

prog=$(basename "$0")

if [ $# -gt 0 ]
then
  rg \@WorthLooking
  echo -e "\n\n===\n"
fi

echo -e "\n\n===\n${prog} For interesting OR confusing stuff ... dig deep"
echo "      jdk8 brings 48 entries "

echo 'vi $(rg -lN -g '\''**.java'\'' -g '\''!**/jdk8/**'\'' \@WorthLooking) '
# WORKED  vi $(rg -lN -g '**/jdk8/**/*.java' \@WorthLooking )
# 46 jdk8> rg -lN -g '**/jdk8/**/*.java' \@WorthLooking

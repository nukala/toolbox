#!/bin/bash

prog=$(basename "$0")

rg \@WorthLooking

echo -e "\n\n===\n${prog} For interesting OR confusing stuff ... dig deep"

echo " vi $(rg -lN -g '**/*.java' -g '!**/jdk8/**' \\@WorthLooking) "

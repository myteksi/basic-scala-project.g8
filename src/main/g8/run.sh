#!/usr/bin/env bash

clear

set -e

sbt assembly

spark-submit \
    --master "local[*]" \
    --class $organization$.$name;format="lower"$.$name;format="Camel"$ \
    "./target/scala-2.10/$name;format="Camel"$-with-dependencies.jar"

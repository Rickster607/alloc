#!/bin/bash

echo "report1.i" > orig1.out
./sim -i 4000 0 10 20 30 40 50 60 70 80 90 < report1.i >> orig1.out
echo "report2.i" > orig2.out
./sim -i 1024 0 1 < report2.i >> orig2.out
echo "report3.i" > orig3.out
./sim -i 1024 0 1 < report3.i >> orig3.out
echo "report4.i" > orig4.out
./sim -i 1024 0 1 2 < report4.i >> orig4.out
echo "report5.i" > orig5.out
./sim -i 1024 1 < report5.i >> orig5.out
echo "report6.i" > orig6.out
./sim -i 1024 0 1 < report6.i >> orig6.out

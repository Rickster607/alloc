#!/bin/bash

echo "allocReport1.i" > alloc1.out
./sim -i 4000 0 10 20 30 40 50 60 70 80 90 < allocReport1.i >> alloc1.out
echo "allocReport2.i" > alloc2.out
./sim -i 1024 0 1 < allocReport2.i >> alloc2.out
echo "allocReport3.i" > alloc3.out
./sim -i 1024 0 1 < allocReport3.i >> alloc3.out
echo "allocReport4.i" > alloc4.out
./sim -i 1024 0 1 2 < allocReport4.i >> alloc4.out
echo "allocReport5.i" > alloc5.out
./sim -i 1024 1 < allocReport5.i >> alloc5.out
echo "allocReport6.i" > alloc6.out
./sim -i 1024 0 1 < allocReport6.i >> alloc6.out

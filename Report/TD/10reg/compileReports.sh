#!/bin/bash

echo "Enter number of physical registers."
read n
echo "Enter allocator type."
read t

for i in `seq 1 6`;
	do
		java -jar alloc.jar $n $t ../../report$i.i
		mv output1.txt allocReport$i.i
	done


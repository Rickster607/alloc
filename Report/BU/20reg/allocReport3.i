loadI 1024 => r0
loadI 1024 => r1
loadI 1028 => r2
loadI 4 => r3
load r1 => r4
load r2 => r1
loadI 2000 => r2
add r4, r1 => r5
add r5, r4 => r1
add r1, r5 => r6
add r6, r1 => r7
add r7, r6 => r8
add r8, r7 => r9
add r9, r8 => r10
add r10, r9 => r11
add r11, r10 => r12
add r12, r11 => r13
store r4 => r2
add r2, r3 => r4
store r5 => r4
add r4, r3 => r2
store r1 => r2
add r2, r3 => r1
store r6 => r1
add r1, r3 => r2
store r7 => r2
add r2, r3 => r1
store r8 => r1
add r1, r3 => r2
store r9 => r2
add r2, r3 => r1
store r10 => r1
add r1, r3 => r2
store r11 => r2
add r2, r3 => r1
store r12 => r1
add r1, r3 => r2
store r13 => r2
output 2000
output 2004
output 2008
output 2012
output 2016
output 2020
output 2024
output 2028
output 2032
output 2036
output 2040

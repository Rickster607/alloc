loadI 1024 => r0
loadI 1024 => r1
loadI 1028 => r2
loadI 4 => r3
load r1 => r4
load r2 => r1
loadI 2000 => r2
add r4, r1 => r5
add r5, r4 => r1
storeAI r3 => r0, -4
add r1, r5 => r3
storeAI r5 => r0, -84
add r3, r1 => r5
storeAI r1 => r0, -88
add r5, r3 => r1
storeAI r3 => r0, -24
add r1, r5 => r3
storeAI r5 => r0, -28
add r3, r1 => r5
storeAI r1 => r0, -32
add r5, r3 => r1
storeAI r3 => r0, -36
add r1, r5 => r3
storeAI r5 => r0, -40
add r3, r1 => r5
store r4 => r2
storeAI r5 => r0, -52
loadAI r0, -4 => r4
add r2, r4 => r5
loadAI r0, -84 => r2
store r2 => r5
add r5, r4 => r2
loadAI r0, -88 => r5
store r5 => r2
add r2, r4 => r5
loadAI r0, -24 => r2
store r2 => r5
add r5, r4 => r2
loadAI r0, -28 => r5
store r5 => r2
add r2, r4 => r5
loadAI r0, -32 => r2
store r2 => r5
add r5, r4 => r2
loadAI r0, -36 => r5
store r5 => r2
add r2, r4 => r5
loadAI r0, -40 => r2
store r2 => r5
add r5, r4 => r2
store r1 => r2
add r2, r4 => r1
store r3 => r1
add r1, r4 => r2
loadAI r0, -52 => r1
store r1 => r2
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

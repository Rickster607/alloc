loadI 1024 => r0
loadI 1028 => r4
storeAI r4 => r0, -4
loadI 1032 => r4
storeAI r4 => r0, -12
load r0 => r2
loadAI r0, -4 => r5
load r5 => r1
loadAI r0, -12 => r5
load r5 => r4
storeAI r4 => r0, -24
loadI 40 => r4
storeAI r4 => r0, -8
loadI 1024 => r3
loadAI r0, -24 => r5
store r5 => r3
add r2, r2 => r4
storeAI r4 => r0, -44
add r2, r1 => r4
storeAI r4 => r0, -48
loadAI r0, -48 => r4
loadAI r0, -44 => r5
add r5, r4 => r4
storeAI r4 => r0, -52
loadAI r0, -48 => r5
add r5, r1 => r4
storeAI r4 => r0, -56
loadAI r0, -56 => r4
loadAI r0, -52 => r5
add r5, r4 => r4
storeAI r4 => r0, -60
loadAI r0, -56 => r5
add r5, r1 => r4
storeAI r4 => r0, -64
loadAI r0, -64 => r4
loadAI r0, -60 => r5
add r5, r4 => r4
storeAI r4 => r0, -68
loadAI r0, -64 => r5
add r5, r1 => r4
storeAI r4 => r0, -72
loadAI r0, -72 => r4
loadAI r0, -68 => r5
add r5, r4 => r4
storeAI r4 => r0, -76
loadAI r0, -72 => r5
add r5, r1 => r4
storeAI r4 => r0, -80
loadAI r0, -80 => r4
loadAI r0, -76 => r5
add r5, r4 => r4
storeAI r4 => r0, -84
loadAI r0, -80 => r5
add r5, r1 => r4
storeAI r4 => r0, -88
loadAI r0, -88 => r4
loadAI r0, -84 => r5
add r5, r4 => r4
storeAI r4 => r0, -92
loadAI r0, -88 => r5
add r5, r1 => r4
storeAI r4 => r0, -96
loadAI r0, -96 => r4
loadAI r0, -92 => r5
add r5, r4 => r4
storeAI r4 => r0, -100
loadAI r0, -96 => r5
add r5, r1 => r4
storeAI r4 => r0, -104
loadAI r0, -104 => r4
loadAI r0, -100 => r5
add r5, r4 => r4
storeAI r4 => r0, -108
loadAI r0, -104 => r5
add r5, r1 => r4
storeAI r4 => r0, -112
loadAI r0, -112 => r4
loadAI r0, -108 => r5
add r5, r4 => r4
storeAI r4 => r0, -116
loadAI r0, -112 => r5
add r5, r1 => r4
storeAI r4 => r0, -120
loadAI r0, -116 => r5
loadAI r0, -120 => r4
add r5, r4 => r4
storeAI r4 => r0, -124
load r3 => r4
storeAI r4 => r0, -128
loadAI r0, -124 => r5
loadAI r0, -128 => r4
mult r5, r4 => r4
storeAI r4 => r0, -132
loadAI r0, -8 => r4
add r3, r4 => r4
storeAI r4 => r0, -136
loadAI r0, -132 => r5
loadAI r0, -136 => r4
store r5 => r4
storeAI r4 => r0, -136
output 1024
output 1064

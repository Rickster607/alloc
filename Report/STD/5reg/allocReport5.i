loadI 1024 => r0
loadI 1024 => r4
storeAI r4 => r0, -4
loadAI r0, -4 => r5
load r5 => r1
loadI 4 => r4
storeAI r4 => r0, -216
loadI 1024 => r4
storeAI r4 => r0, -200
lshift r1, r1 => r4
storeAI r4 => r0, -220
loadAI r0, -220 => r5
lshift r5, r1 => r3
lshift r3, r1 => r4
storeAI r4 => r0, -16
loadAI r0, -16 => r5
lshift r5, r1 => r2
lshift r2, r1 => r4
storeAI r4 => r0, -24
loadAI r0, -24 => r5
lshift r5, r1 => r4
storeAI r4 => r0, -28
loadAI r0, -28 => r5
lshift r5, r1 => r4
storeAI r4 => r0, -32
loadAI r0, -32 => r5
lshift r5, r1 => r4
storeAI r4 => r0, -36
loadAI r0, -36 => r5
lshift r5, r1 => r4
storeAI r4 => r0, -40
loadAI r0, -40 => r5
lshift r5, r1 => r4
storeAI r4 => r0, -44
loadAI r0, -44 => r5
lshift r5, r1 => r4
storeAI r4 => r0, -48
loadAI r0, -48 => r5
lshift r5, r1 => r4
storeAI r4 => r0, -52
loadAI r0, -52 => r5
lshift r5, r1 => r4
storeAI r4 => r0, -56
loadAI r0, -56 => r5
lshift r5, r1 => r4
storeAI r4 => r0, -60
loadAI r0, -60 => r5
lshift r5, r1 => r4
storeAI r4 => r0, -64
loadAI r0, -64 => r5
lshift r5, r1 => r4
storeAI r4 => r0, -68
loadAI r0, -220 => r4
add r1, r4 => r4
storeAI r4 => r0, -80
loadAI r0, -16 => r4
add r3, r4 => r4
storeAI r4 => r0, -84
loadAI r0, -24 => r4
add r2, r4 => r4
storeAI r4 => r0, -88
loadAI r0, -28 => r5
loadAI r0, -32 => r4
add r5, r4 => r4
storeAI r4 => r0, -92
loadAI r0, -36 => r5
loadAI r0, -40 => r4
add r5, r4 => r4
storeAI r4 => r0, -96
loadAI r0, -44 => r5
loadAI r0, -48 => r4
add r5, r4 => r4
storeAI r4 => r0, -100
loadAI r0, -52 => r5
loadAI r0, -56 => r4
add r5, r4 => r4
storeAI r4 => r0, -104
loadAI r0, -60 => r5
loadAI r0, -64 => r4
add r5, r4 => r4
storeAI r4 => r0, -108
loadAI r0, -80 => r5
loadAI r0, -84 => r4
add r5, r4 => r4
storeAI r4 => r0, -120
loadAI r0, -88 => r5
loadAI r0, -92 => r4
add r5, r4 => r4
storeAI r4 => r0, -124
loadAI r0, -96 => r5
loadAI r0, -100 => r4
add r5, r4 => r4
storeAI r4 => r0, -128
loadAI r0, -104 => r5
loadAI r0, -108 => r4
add r5, r4 => r4
storeAI r4 => r0, -132
loadAI r0, -120 => r5
loadAI r0, -124 => r4
add r5, r4 => r4
storeAI r4 => r0, -136
loadAI r0, -128 => r5
loadAI r0, -132 => r4
add r5, r4 => r4
storeAI r4 => r0, -140
loadAI r0, -136 => r4
loadAI r0, -140 => r5
add r5, r4 => r4
storeAI r4 => r0, -144
loadAI r0, -68 => r4
loadAI r0, -144 => r5
add r5, r4 => r4
storeAI r4 => r0, -148
loadAI r0, -200 => r4
loadAI r0, -148 => r5
store r5 => r4
storeAI r4 => r0, -200
add r2, r1 => r4
storeAI r4 => r0, -160
loadAI r0, -160 => r4
lshift r1, r4 => r4
storeAI r4 => r0, -164
loadAI r0, -164 => r5
sub r5, r1 => r4
storeAI r4 => r0, -168
loadAI r0, -200 => r5
loadAI r0, -216 => r4
add r5, r4 => r4
storeAI r4 => r0, -204
loadAI r0, -168 => r5
loadAI r0, -204 => r4
store r5 => r4
storeAI r4 => r0, -204
output 1024
output 1028

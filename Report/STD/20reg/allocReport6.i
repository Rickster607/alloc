loadI 1024 => r0
loadI 1028 => r18
load r0 => r19
storeAI r19 => r0, -8
load r18 => r1
loadI 4 => r19
storeAI r19 => r0, -216
loadI 1024 => r2
loadAI r0, -8 => r20
add r20, r1 => r19
storeAI r19 => r0, -220
lshift r1, r1 => r3
loadAI r0, -220 => r20
add r20, r3 => r19
storeAI r19 => r0, -228
lshift r3, r1 => r4
loadAI r0, -228 => r20
add r20, r4 => r19
storeAI r19 => r0, -236
lshift r4, r1 => r5
loadAI r0, -236 => r20
add r20, r5 => r19
storeAI r19 => r0, -244
lshift r5, r1 => r6
loadAI r0, -244 => r20
add r20, r6 => r19
storeAI r19 => r0, -252
lshift r6, r1 => r7
loadAI r0, -252 => r20
add r20, r7 => r19
storeAI r19 => r0, -260
lshift r7, r1 => r8
loadAI r0, -260 => r20
add r20, r8 => r19
storeAI r19 => r0, -268
lshift r8, r1 => r9
loadAI r0, -268 => r20
add r20, r9 => r19
storeAI r19 => r0, -276
lshift r9, r1 => r10
loadAI r0, -276 => r20
add r20, r10 => r19
storeAI r19 => r0, -284
lshift r10, r1 => r11
loadAI r0, -284 => r20
add r20, r11 => r19
storeAI r19 => r0, -292
lshift r11, r1 => r12
loadAI r0, -292 => r20
add r20, r12 => r19
storeAI r19 => r0, -300
lshift r12, r1 => r13
loadAI r0, -300 => r20
add r20, r13 => r19
storeAI r19 => r0, -308
lshift r13, r1 => r14
loadAI r0, -308 => r20
add r20, r14 => r19
storeAI r19 => r0, -316
lshift r14, r1 => r15
loadAI r0, -316 => r20
add r20, r15 => r19
storeAI r19 => r0, -324
lshift r15, r1 => r16
loadAI r0, -324 => r20
add r20, r16 => r19
storeAI r19 => r0, -332
lshift r16, r1 => r17
loadAI r0, -332 => r20
add r20, r17 => r19
storeAI r19 => r0, -340
lshift r17, r1 => r19
storeAI r19 => r0, -344
loadAI r0, -340 => r20
loadAI r0, -344 => r19
add r20, r19 => r19
storeAI r19 => r0, -348
loadAI r0, -348 => r20
store r20 => r2
loadI 17 => r19
storeAI r19 => r0, -68
loadAI r0, -68 => r19
lshift r1, r19 => r19
storeAI r19 => r0, -72
loadAI r0, -72 => r20
sub r20, r1 => r19
storeAI r19 => r0, -76
loadAI r0, -216 => r19
add r2, r19 => r19
storeAI r19 => r0, -204
loadAI r0, -76 => r20
loadAI r0, -204 => r19
store r20 => r19
storeAI r19 => r0, -204
output 1024
output 1028

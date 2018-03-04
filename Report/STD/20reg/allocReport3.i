loadI 1024 => r0
loadI 1024 => r19
storeAI r19 => r0, -8
loadI 1028 => r19
storeAI r19 => r0, -12
loadI 4 => r1
loadAI r0, -8 => r20
load r20 => r2
loadAI r0, -12 => r20
load r20 => r19
storeAI r19 => r0, -20
loadI 2000 => r12
loadAI r0, -20 => r19
add r2, r19 => r9
add r9, r2 => r10
add r10, r9 => r3
add r3, r10 => r4
add r4, r3 => r5
add r5, r4 => r6
add r6, r5 => r7
add r7, r6 => r8
add r8, r7 => r11
add r11, r8 => r19
storeAI r19 => r0, -52
store r2 => r12
add r12, r1 => r13
store r9 => r13
add r13, r1 => r14
store r10 => r14
add r14, r1 => r15
store r3 => r15
add r15, r1 => r16
store r4 => r16
add r16, r1 => r17
store r5 => r17
add r17, r1 => r18
store r6 => r18
add r18, r1 => r19
storeAI r19 => r0, -116
loadAI r0, -116 => r19
store r7 => r19
storeAI r19 => r0, -116
loadAI r0, -116 => r20
add r20, r1 => r19
storeAI r19 => r0, -120
loadAI r0, -120 => r19
store r8 => r19
storeAI r19 => r0, -120
loadAI r0, -120 => r20
add r20, r1 => r19
storeAI r19 => r0, -124
loadAI r0, -124 => r19
store r11 => r19
storeAI r19 => r0, -124
loadAI r0, -124 => r20
add r20, r1 => r19
storeAI r19 => r0, -128
loadAI r0, -52 => r20
loadAI r0, -128 => r19
store r20 => r19
storeAI r19 => r0, -128
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

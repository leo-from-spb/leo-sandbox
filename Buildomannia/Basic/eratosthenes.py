__author__ = 'Leonid.Bushuev'

import sys

def calculate_and_print():

    M = 8000000
    N = 9000000

    N1 = N + 1
    sieve = [False] * N1

    sieve[0] = True
    sieve[1] = True

    p = 2
    while p < N1/2:
        for k in range(p*2, N1, p):
            sieve[k] = True
        p = p + 1
        while p < N1 and sieve[p]:
            p = p + 1

    r = 0
    for i in range(M, N1):
        if (sieve[i] == False):
            r = r + 1
            if (r < 10):
                print(i, end = '\t')
            else:
                print(i)
                r = 0


###########################################

print("Sieve of Eratosthenes")
print("=====================")

calculate_and_print()


sys.exit(0)



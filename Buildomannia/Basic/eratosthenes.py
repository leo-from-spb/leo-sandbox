__author__ = 'Leonid.Bushuev'

from sys import exit

def calculate_and_print():

    M = 8000000
    N = 9000000

    N1 = N + 1
    sieve = [False] * N1

    calculate_prime_numbers(sieve, N1)

    print_results(sieve, M, N1)


def calculate_prime_numbers(sieve, N1):
    sieve[0] = True
    sieve[1] = True
    p = 2
    while p < N1 / 2:
        for k in range(p * 2, N1, p):
            sieve[k] = True
        p = p + 1
        while p < N1 and sieve[p]:
            p = p + 1


def print_results(sieve, M, N1):
    r = 0
    for i in range(M, N1):
        if (sieve[i] == False):
            r = r + 1
            if (r < 10):
                print(i, end='\t')
            else:
                print(i)
                r = 0




###########################################

print("Sieve of Eratosthenes")
print("=====================")

calculate_and_print()


# exit(0)


exit(-1)

exit(-1)


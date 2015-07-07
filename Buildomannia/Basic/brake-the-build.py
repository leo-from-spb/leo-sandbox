import random
from subprocess import call


def brake_the_build():


    fellowship = [
        "Frodo Baggins <frodo@x.x>",
        "Samwise Gamgee <sam@x.x>",
        "Meriadoc Brandybuck <merry@x.x>",
        "Peregrin Took <pippin@x.x>",
        "Aragorn <aragorn@x.x>",
        "Boromir <boromir@x.x>"
    ]


    N = 12
    M1 = 1
    M2 = 9
    P = 1.0

    print("Braking the build")
    print("=================")
    random.seed()


    broken = False
    comment_nr = 0
    f_prev = ''

    for k in range(1,N):

        f = random.choice(fellowship)
        while f == f_prev:
            f = random.choice(fellowship)

        print(f)

        p = 1.0 * P * k / N
        chN = random.randint(M1,M2)
        for ch in range(1,chN):
            # decide to brake right now
            filename = ""
            to_brake = not broken and random.random() < p
            if to_brake:
                # brake the build
                with open("eratosthenes.py", 'a') as file:
                    file.write("exit(-1)\n\n")
                filename = "eratosthenes.py"
                broken = True
            else:
                # change a file
                with open("x-file.txt", 'a') as file:
                    file.write("Hello\n")
                filename = "x-file.txt"
            call(["git", "add", filename])
            call(["git", "commit", "--author='%s'"%f, "-m", "evil"])

        f_prev = f





brake_the_build()


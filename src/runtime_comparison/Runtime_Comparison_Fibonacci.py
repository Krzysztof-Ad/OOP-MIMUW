import time
import sys

#naive recursive fibonacci
#to effectively measure real runtime difference
def fibonacci(x):
    if x < 2:
        return x
    return fibonacci(x - 1) + fibonacci(x - 2)

#argument validation
if len(sys.argv) != 2:
    print("One argument is required")
    sys.exit(1)

n = int(sys.argv[1])

#timer start
start = time.perf_counter()

result = fibonacci(n)

#timer stop
end = time.perf_counter()

print(f"Fibonacci number for n = {n} is: {result}")
print(f"Python runtime: {int((end - start) * 1000)} ms")
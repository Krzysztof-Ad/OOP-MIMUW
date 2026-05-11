import subprocess
import re
import sys

#environment check
environment = subprocess.check_output("uname -mrs", shell=True).decode("utf-8")

#java version check
javac_version = subprocess.check_output("javac --version", shell=True).decode("utf-8")

#python version check
python_version = subprocess.check_output("python --version", shell=True).decode("utf-8")

#printing system information
print("-" * 50)
print(f"Environment: {environment}"
      f"Javac version: {javac_version}"
      f"Python version: {python_version}")
print("-" * 50)

#java file compilation
print("Compiling Java file...")
subprocess.run(["javac", "Runtime_Comparison_Fibonacci.java"])
print("Compilation complete.")
print("-" * 50)

#values tested (n meaning n-th fibonacci number)
n_values = [34, 38, 42, 46]
print(f"Selected {len(n_values)} n values: {n_values}")
print("-" * 50)

#function to search the output of Runtime_Comparison_Fibonacci files
#for time given in ms
def extract_time(output):
    match = re.search(r"(\d+)\s*ms", output)
    if match:
        return match.group(1) + " ms"
    return "No Time Data"

#table look creation
print("")
print("=" * 90)
print(f"{'Data':<10} | {'java':<15} | {'java -Xcomp -Xdiag':<20} | {'java -Xint -Xdiag':<20} | {'python':<10}")
print("=" * 90)

for n in n_values:
    n_str = str(n)

    result_java = subprocess.run(["java", "Runtime_Comparison_Fibonacci", n_str], capture_output=True, text=True)
    time_java = extract_time(result_java.stdout)

    result_java_comp = subprocess.run(["java", "-Xcomp", "-Xdiag", "Runtime_Comparison_Fibonacci", n_str], capture_output=True, text=True)
    time_java_comp = extract_time(result_java_comp.stdout)

    result_java_int = subprocess.run(["java", "-Xint", "-Xdiag", "Runtime_Comparison_Fibonacci", n_str], capture_output=True, text=True)
    time_java_int = extract_time(result_java_int.stdout)

    result_python = subprocess.run([sys.executable, "Runtime_Comparison_Fibonacci.py", n_str], capture_output=True, text=True)
    time_python = extract_time(result_python.stdout)

    print(f"n = {n:<6} | {time_java:<15} | {time_java_comp:<20} | {time_java_int:<20} | {time_python:<10}")

print("=" * 90)
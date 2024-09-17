import main
import addQuest1
import addQuest2
import time

t1 = time.time()
for i in range(100):
    main.func()
t2 = time.time()

t11 = time.time()
for i in range(100):
    addQuest1.addTask1()
t22 = time.time()

t111 = time.time()
for i in range(100):
    addQuest2.task2()
t222 = time.time()

print("Time main: ", t2 - t1)
print("Time task1: ", t22 - t11)
print("Time task2: ", t222 - t111)

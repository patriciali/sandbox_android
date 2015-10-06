f1 = 1
f2 = 1

sum = 0
while f2 < 10**27:
    if not f2 % 10 or not f2 % 27:
        sum += f2
    
    temp = f1 + f2
    f1 = f2
    f2 = temp

print sum

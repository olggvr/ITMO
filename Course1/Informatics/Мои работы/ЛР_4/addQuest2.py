import time
import re


def task2():
    # read yml file to list
    file_yml = open("my.yml", "r", encoding="UTF-8")
    lst_yml = file_yml.readlines()
    lst_yml[len(lst_yml) - 1] += '\n'

    with open("res2.json", "w", encoding="utf-8") as out:
        out.write('{' + '\n')

        # set pattern
        pat = re.compile(r'(\b\w+\b):((\s*\S*)+)')

        # make new string and write it
        for i in range(len(lst_yml)):

            # replace string with new string on pattern
            s = re.sub(pat, r'"\1":\2', lst_yml[i])

            # put braces in new string
            if s[::-1][1] == ":":
                s = s[0:len(s) - 1] + '{' + '\n'
            elif s[::-1][1] == '"':
                a = ','
                if i == len(lst_yml) - 1: a = ''
                s = s[0:len(s) - 1] + '\n' + ' '*4 + '}' + f'{a}' + '\n'
            out.write(s)
        out.write(('}' + '\n')*3)

t1 = time.time()
task2()
t2 = time.time()
print("Time: ", t2 - t1)
import time

def func():
    # read yml file to list
    file_yml = open("my.yml", "r", encoding="UTF-8")
    lst_yml = file_yml.readlines()


    # function which is counting spaces in input string
    def count_spaces(s):
        space_count = 0
        for j in range(len(s)):
            if s[j] == ' ':
                space_count += 1
            else:
                break
        return space_count


    with open("res.json", "w", encoding="UTF-8") as res:

        # write open brace
        res.write('{\n')

        for i in range(len(lst_yml)):

            # count number of spaces before word
            spaces_number = count_spaces(lst_yml[i])

            # count symbols before ":"
            symbCount = 0
            for k in range(spaces_number, len(lst_yml[i])):
                if lst_yml[i][k] != ':': symbCount += 1
                else: break

            res.write(lst_yml[i][0:spaces_number] + '"' + lst_yml[i][spaces_number:spaces_number + symbCount] + '"' +
                      lst_yml[i][spaces_number + symbCount:len(lst_yml[i]) - 1])

            # count spaces from next element of list
            if i != len(lst_yml) - 1:
                next_spaces_count = count_spaces(lst_yml[i + 1])
                if spaces_number != 6:
                    res.write('{' + '\n')
                elif i != len(lst_yml) - 1 and next_spaces_count == 4:
                    res.write('\n' + next_spaces_count * ' ' + '},' + '\n')
                else:
                    res.write(',' + '\n')
            else:
                res.write('\n' + 4 * ' ' + '}' +
                          '\n' + 2 * ' ' + '}' +
                          ('\n' + '}') * 2)
t1 = time.time()
func()
t2 = time.time()
print("Time1: ", t2 - t1)
import yaml
import json
import time


def addTask1():

    # open yaml file and bring it to list of strings
    with open("my.yml", "r", encoding="UTF-8") as yaml_in:
        yaml_object = yaml.safe_load(yaml_in)

    # convert yaml list to json document
    with open("res1.json", "w", encoding="UTF-8") as json_out:
        json.dump(yaml_object, json_out, ensure_ascii=False, indent=2)


t1 = time.time()
addTask1()
t2 = time.time()
print("Time: ", t2 - t1)
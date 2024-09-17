import re

# (1) Find smiles with pattern given | 111
def findPat(s):
    pat = re.compile(r';<\)')
    match = len(pat.findall(s))
    return match

# (2) Correct words duplication | 1
def correctMist(s):
    pat = re.compile(r'\b([^\W_]+)(\s+\1)+\b')
    str = pat.sub(r'\1', s.strip())
    print(str)
    return str

# (3) Find 3 chars with fixed distance | 4
def findWords(s, mask, dist):
    pat = re.compile(rf'\b(?:\w*\s*)*{mask[0]}' + r'\w'*dist + rf'{mask[1]}' + r'\w'*dist + rf'{mask[2]}(?:\s*\w+)*\b')
    matches = pat.finditer(s)
    words = [match.group(0) for match in matches]
    str = ""
    for i in range(len(words)):
        str += words[i] + " "
    print(str)
    return str.strip()
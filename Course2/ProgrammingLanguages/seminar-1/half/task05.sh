#/bin/bash


file=test.txt

grep '^.$' $file
grep '\d' $file
grep -E '^0[xX][0-9a-fA-F]+$' $file
grep -P '\b\w{3}\b' $file
grep '^\s*$' $file
grep -v 'ttttttttttttttttttttttttt' $file

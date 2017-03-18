#!/usr/bin/env bash

#mid-2015

#Q1
I. file1
   file2
II. f*
III. g*
IV. file1
V. file1
VI.file1 file2
VII. trump
VIII. file1trumpfile2

#Q2
"files in the current directry tree"
find . -type f

"either owned by bsanders or is world writable"
-user bsanders -o -perm /o+w

"ends with .temp or .tmp"
-name '*.temp' -o -name '*.tmp'

"modified more than 60 days ago"
-mtime +60

"delete files"
-exec rm \{} \; OR | xargs rm

#Q2

putting it all together:

find . -type f \( -user kornj -o -perm /o+w \) \
\( -name '*.tmp' -o -name '*.temp' \) \
-mtime +60 | xargs rm

#Q3

I.a
II.a
   b
III.error

#Q4a
States with polls.

cut -d, -f3 polls.csv |
sort |
uniq

#Q4b

IA polling organizationgs.

cut -d, -f2,3 polls.csv |
grep ', IA$' |
cut -d, -f1 |
sort |
uniq

#Q4c

Questions 4c
Month with most polls.

cut -d, -f1 polls.csv
cut -d/ -f 1 |
sort |
uniq -c |
sort -rn |
head -l |
cut -c9-

Question 4c alternate
Month with most polls

cut-d/ -f1 polls.csv |
sort |
uniq -c |
sort -n |
tr ' ' '\n' |
tail -l

#Q4d

Tied polls

grep ',\([0-9]*\), \1$' polls.csv |
cut -d, -f1,2

#Q4e
Change delimiter to / and / to,

tr ,/ /, < polls.csv

#Q4f
Polling organizations in multipole states.

cut -d, -f 2,3 polls2.csv |
sort |
uniq |
cut -d, -f1 |
uniq -d

#Q5

Sed Option 1
/^10\//!s/^[^,]*,[^,]*/& (OLD)/
/TRUMP/d
s/^\(.*\),\(.*\),\(.*\),\(.*\),\(.
*\)$/\1,\3,\2/
p

Sed Option2

/^10\//!s/,/ (OLD),/2
/TRUMP/d
s/^\(.*\),\(.*\),\(.*\)$/\1,\3,\2/p

#Q6

Setup

BEGIN { FS = "," }
{
state[$3]++
sumT += $4
sumC += $5
gap = $5 - $4
if (gap < 0) gap = -gap
}

Track widest gap
gap > widestGap{
  widestGap = gap
  widestGappoll = $1 " " $2
}

Track state with most polls
state[$3] > maxNumPolls {
  maxNumPolls = state[$3]
  maxNumPollsState = $3
}

Printing the channel results

END{
  for (s in state) {
    states = states " " s
  }
  print "States with polls:" states
  print "States with most polls: " maxNumPollsState
  print "Widest gap: " widestGapPoll
  print "Average: Trump " (sumT/NR)
        ", Carson " (sumC/NR)
}






#!/usr/bin/env bash

#Q1

I. file1 file2
II. file1
III. none
IV. file2
V. file1 file2
VI. file1
VII. file1 file2
VIII. file1 file2

#Q2 A C D E

#Q3
a. Number of records of at least 1000 miles.

grep ';[0-9]\{4,\}$' dist.dat | wc -l

b. Origin cities that have destination in NY.

cut -d';' -f1,2 dist.dat | grep 'NY$' | cut -d';' -f1

c. Records with city names removed.

tr ',' ';' dist.dat |
cut -d';' 2,4,5

sed 's/^[^,]*,//' dist.dat |
sed 's/;[^,]*,/;/'

d.Records that have cities with same abbreviation as state.

grep \
    -e '^\(.\)[^, ]* \(.\)[^,]*, \1\2' \
    -e ';\(.\)[^, ]* \(.\)[^,]*, \1\2' \
    dist.dat

e. List all city names (origin or destination).

cut -d';' -f1,2 dist.dat |
tr ';' '\n' |
cut -d ',' -f1 |
sort |
uniq

f. Cities that appear as origins and destinations
cut -d';' -f1,2 dist.dat
    sed 's/;/;\n/' |
    sort -u |
    tr -d ';' |
    uniq -d

#Q4

Awk:First bullet

BEGIN {FS = ";" }
{ sum += $3 }
$3 > max { max = $3 }
$3 < min || min == "" { min = $3 }
END { print sum / NR, min, max }


Awk: Second bullet

BEGIN { FS = ";" }
{ sum += $3 }
$3 > max { max = $3 }
$3 < min || min == "" { min = $3 }
$3 < md[$1] || md[$1] == "" {
    md[$1] = $3; mc[$1] = $2
}
$3 < md[$2] || md[$2] == "" {
    md[$2] = $3; mc[$2] = $1
}
END { print sum / NR, min, max }


Awk: Second bullet

BEGIN { FS  =  ";" }
{ sum += $3 }
$3 > max { max = $3 }
$3 < min || min = "" { min == $3 }
$3 < md[$1] || md[$1] == "" {
 md[$1] $3; mc[$1] = $2
}
$3 < md[$2] || md[$2] == "" {
    md[$2] = $3; mc[$2] = $1
}
END {
    print sum / NR, min, max
    for (c in mc) { print c ": " mc[c] }
}

#Q5

a. Find:
 File
 Under/home/unixtool
 User NOT unixtool
 World writable(o=w)
 Modified in last 30 days (mtime -30)

 find /home/unixtool -type f \! -
 user unixtool -perm -002 -mtime
 -30 -exec rm \{} \;

 b. Find:
 File
 Under /home/unixtool
 user NOT unixtool
 World writable (o=w)
 Modifiedinlast30 days

 find /home/unixtool -type f \! -
 user unixtool -perm -002 -mtime
 -30 | xargs rm

#Q6
Shell： basic outline
#!/bin/sh

col=$1
delim=$2
shift 2
for file in "$@"; do
    cut -d$delim -f$col "$file" > /tmp/data
    cp /tmp/data "$file"
done


Shell: Check number of ars

#!/bin/sh

if [ $# -lt 3 ]; then
    exit 1
fi
col=$1
delim=$2
shift 2
for file in "$@"; do
    cut -d$delim -f$col "$file" > /tmp/data
    cp /tmp/data "$file"
done


Shell: Skip files that don't exist

#!/bin/sh

if [ $# -lt 3 ]; then
    exit 1
fi
col=$1
delim=$2
skip=0
shift 2
for file in "$@"; do
    if [ -f "$file" ]; then
      cut -d$delim -f$col "$file" > /tmp/data
      cp /tmp/data " $file"
    else
      skip=$(expr $skp + 1)
    fi
done

Shell: Skip files without delimiter
#!bin/sh

if [ $# -lt 3 ]; then
  exit 1
fi
col=$1
delim=$2
skip=0
shift 2
for file in "$@"; do
  if [ -f "$file" ]; then
    cut -d$delim -f$col "$file" > /tmp/data
    cp /tmp/data "$file"
  else
    skip=$(expr $skip + 1)
  fi
done

Shell: Skip files without delimiter

#!/bin/sh
if [ $# -lt 3 ]; then
  exit 1
fi
col=$1
delim=$2
skip=0
shift 2
for file in "$@"; do
  if [ -f "$file" ] && [ $(grep $delim "file" | wc -l) != 0]
  then
    cut -d$delim -f$col "$file" > /tmp/data
    cp /tmp/data "$file"
  else
    skip=$(expr $skip + 1)
  fi
done

Shell: Exiting
#!/bin/sh

[ $# -lt 3 ] && exit 1
col=$1
delim=$2
skip=0
shift 2
for file in "$@"; do
  if [ -f "$file" ] && [ $(grep $delim "$file" | wc -l) != 0]
  then
    cut -d$deim -f$col "$file" > /temp/data
    cp /tmp/data "$file"
  else
    skip=$(expr $skip + 1)
  fi
done
rm /tmp/data
exit $skip

Shell: better exiting
#!/bin/sh

[ $# -lt 3 ] && exit 1
col=$1
delim=$2
skip=0
shift 2
TMPFILE=/tmp/data$$; trap 'rm $TMPFILE' EXIT
for file in "$@"; do
  if [ -f "$file" ] && [ $(grep $delim "$file" | wc -l) != 0]
  then
    cut -d$delim -f$col "$file" > $TMPFILE
    cp $TMPFILE "$file"
  else
    skip=$(expr $skip + 1)
  fi
done
exit $skip






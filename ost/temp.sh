tz406@linax1[ost]$ cat lect5
Script started on Mon 27 Feb 2017 08:08:35 PM EST
$ vi example.awk l
2 files to edit
$ vi awk.input
$ awk -f example.awk ^^xxx
$ awk -f example.awk ^00e
$ set -o vi
$ cat awk.input | awk -f example.awk
awk: example.awk:3:   FS = ':'
awk: example.awk:3:        ^ invalid char ''' in expression
$ vi example.awk l
2 files to edit
$ vi example.awk l
2 files to edit
$ cat awk.input | awk -f example.awk
There are 3 fields on line 1
There are 1 fields on line 2
There are 1 fields on line 3
There are 1 fields on line 4
Total fields: 6
$ cat awk.input
a:b:c
def
Hello world
Bye
$ cat awk.input | awk -f example.awk
$
$
$ cat awk.input
a:b:c
def
Hello world
Bye
$ cat awk.input | head -3 | tail -1
Hello world
$ cat awk.input | awk 'NR==3'
Hello world
$ cat awk.input | awk -F: 'NF>=3'
a:b:c
$ cat awk.input | awk 'NF>=3'
$ cat awk.input | awk 'NF>1'
Hello world
$ cat awk.input | awk 'END {print NR}'
4
$ cat awk.input | awk 'END {print $0}'
Bye
$ cat awk.input | awk 'END {print $0}'
$ cat awk.input
a:b:c
def
Hello world
Bye
$ cat awk.input | awk '{line = $0} END {print line}'
Bye
$
$
$ s = max(maxfiles, NF)} END {print maxfields}'               <
awk: (FILENAME=- FNR=1) fatal: function `max' not defined
$ cat awk.input | awk '{maxfields = max(maxfiles, NF)} END {pr>
$ vi example.awk
$ cat awk.input | awk -f example.awk
3
$ cat awk.input | awk -f example.awk
$ vi example.awk
$ cat awk.input | awk -f example.awk
2
$ cat awk.input | awk -Fx -f example.awk
1
$ vi reverse.awk
$ cat awk.input | awk -f reverse.awk
Bye
Hello world
def
a:b:c
$
$ cat awk.input
a:b:c
def
Hello world
Bye
$ vi names.input
$ vi names.input
$ name[$1] = 1} END {for (n in name) print n }'               <
Jeff
Emily
Max
$ vi names.input
$ cat names.input | awk -F: ' { name[$1] = 1} END {for (n in n>
cat names.input | awk -F: ' { name[$1] += $2} END {for (n in name) print n, name[n] }'
Jeff 120
Emily 99
Max 109
$ vi greeting
$ chmod +x greeting
$ ./greeting
Hello. The time is
Mon Feb 27 20:58:05 EST 2017
Have a good one
$ greeting
ksh: greeting: not found [No such file or directory]
$ PATH=$PATH:.
$ greeting
Hello. The time is
Mon Feb 27 20:58:36 EST 2017
Have a good one
$ ls greeting
greeting
$ # mv greeting /bin
$ fg
ksh: fg: no such job
$ vi greeting
$ greeting
Hello, kornj
Mon Feb 27 20:59:44 EST 2017
Have a good one
$

Script done on Mon 27 Feb 2017 09:01:25 PM EST
tz406@linax1[ost]$ less lect5
tz406@linax1[ost]$ cat lect5
Script started on Mon 27 Feb 2017 08:08:35 PM EST
$ vi example.awk l
2 files to edit
$ vi awk.input
$ awk -f example.awk ^^xxx
$ awk -f example.awk ^00e
$ set -o vi
$ cat awk.input | awk -f example.awk
awk: example.awk:3:   FS = ':'
awk: example.awk:3:        ^ invalid char ''' in expression
$ vi example.awk l
2 files to edit
$ vi example.awk l
2 files to edit
$ cat awk.input | awk -f example.awk
There are 3 fields on line 1
There are 1 fields on line 2
There are 1 fields on line 3
There are 1 fields on line 4
Total fields: 6
$ cat awk.input
a:b:c
def
Hello world
Bye
$ cat awk.input | awk -f example.awk
$
$
$ cat awk.input
a:b:c
def
Hello world
Bye
$ cat awk.input | head -3 | tail -1
Hello world
$ cat awk.input | awk 'NR==3'
Hello world
$ cat awk.input | awk -F: 'NF>=3'
a:b:c
$ cat awk.input | awk 'NF>=3'
$ cat awk.input | awk 'NF>1'
Hello world
$ cat awk.input | awk 'END {print NR}'
4
$ cat awk.input | awk 'END {print $0}'
Bye
$ cat awk.input | awk 'END {print $0}'
$ cat awk.input
a:b:c
def
Hello world
Bye
$ cat awk.input | awk '{line = $0} END {print line}'
Bye
$
$
$ s = max(maxfiles, NF)} END {print maxfields}'               <
awk: (FILENAME=- FNR=1) fatal: function `max' not defined
$ cat awk.input | awk '{maxfields = max(maxfiles, NF)} END {pr>
$ vi example.awk
$ cat awk.input | awk -f example.awk
3
$ cat awk.input | awk -f example.awk
$ vi example.awk
$ cat awk.input | awk -f example.awk
2
$ cat awk.input | awk -Fx -f example.awk
1
$ vi reverse.awk
$ cat awk.input | awk -f reverse.awk
Bye
Hello world
def
a:b:c
$
$ cat awk.input
a:b:c
def
Hello world
Bye
$ vi names.input
$ vi names.input
$ name[$1] = 1} END {for (n in name) print n }'               <
Jeff
Emily
Max
$ vi names.input
$ cat names.input | awk -F: ' { name[$1] = 1} END {for (n in n>
cat names.input | awk -F: ' { name[$1] += $2} END {for (n in name) print n, name[n] }'
Jeff 120
Emily 99
Max 109
$ vi greeting
$ chmod +x greeting
$ ./greeting
Hello. The time is
Mon Feb 27 20:58:05 EST 2017
Have a good one
$ greeting
ksh: greeting: not found [No such file or directory]
$ PATH=$PATH:.
$ greeting
Hello. The time is
Mon Feb 27 20:58:36 EST 2017
Have a good one
$ ls greeting
greeting
$ # mv greeting /bin
$ fg
ksh: fg: no such job
$ vi greeting
$ greeting
Hello, kornj
Mon Feb 27 20:59:44 EST 2017
Have a good one
$

Script done on Mon 27 Feb 2017 09:01:25 PM EST
tz406@linax1[ost]$

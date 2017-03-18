drwxrwxr-x.   4 kornj kornj  18 Mar  6 19:07 .
-rw-rw-r--.   1 kornj kornj   0 Mar  6 19:07 typescript
drwxr-xr-x. 110 kornj kornj 315 Mar  6 19:04 ..
-rwxrwxr-x.   1 kornj kornj  79 Feb 27 21:02 greeting
-rw-rw-r--.   1 kornj kornj  39 Feb 27 20:33 names.input
-rw-rw-r--.   1 kornj kornj  67 Feb 27 20:32 reverse.awk
-rw-rw-r--.   1 kornj kornj  77 Feb 27 20:27 example.awk
-rw-rw-r--.   1 kornj kornj  26 Feb 27 20:09 awk.input
-rw-rw-r--.   1 kornj kornj  88 Feb 13 21:05 stuff
dr--r--r--.   2 kornj kornj   3 Feb  5 21:23 dir
drwxr-xr-x.   2 kornj kornj   4 Jan 30 20:57 subdir
-rw-r--r--.   1 kornj kornj  56 Jan 30 20:47 myfiles
-rw-r--r--.   1 kornj kornj  58 Jan 30 20:44 today
--w--w--w-.   2 kornj kornj   0 Jan 30 20:25 bar
--w--w--w-.   2 kornj kornj   0 Jan 30 20:25 foo
-r--r--r--.   1 kornj kornj   0 Jan 30 20:24 newnewfile
-rw-rw-r--.   1 kornj kornj   0 Jan 30 20:21 newfile
-rw-rw-r--.   1 kornj kornj   0 Jan 30 20:05 sdfjs
> vi greeting
> greeting
ksh: greeting: not found [No such file or directory]
> ./greeting
Hello, kornj.
> PATH=$PATH:.
Script done on Mon 06 Mar 2017 09:03:30 PM EST
tz406@linax1[ost]$
> greeting
Hello, kornj.
> cat greeting
#!/bin/sh

echo "Hello, $USER."
> vi greeting
> greeting
Hello, kornj.
You entered the argument
> greeting person
Hello, kornj.
You entered the argument person
> greeting person blah
Hello, kornj.
You entered the argument person
> greeting person
> vi person
> vi greeting
> greeting person blah
Hello, kornj.
You entered the argument greeting
> vi greeting
> greeting a b c d
Hello, kornj.
You passed in 4 arguments
You entered the argument 2 as b
> echo hello
hello
> echo $?
0
> ls sdfkjsdfsdfjsdf
ls: cannot access sdfkjsdfsdfjsdf: No such file or directory
> echo $?
2
> echo $$
5795
> echo $$
5795
> echo $USER
kornj
> echo ${USER}
kornj
> echo ${#USER}
5
> echo ${USER#x}
kornj
> echo ${USER#k}
ornj
> echo ${USER%n}
kornj
> echo ${USER%j}
korn
> /a/b/c
> echo ${abc}

> echo ${abc-blah}
blah
> abc=text
> echo ${abc-blah}
text
> echo ${USER#k}
> path=/a/b/c
> echo ${path#*/}
a/b/c
> echo ${path##*/}
c
> vi
> date
Mon Mar  6 20:25:06 EST 2017
> today='Mon Mar  6 20:25:06 EST 2017'
> echo $today
Mon Mar 6 20:25:06 EST 2017
> today=`date`
> echo $today
Mon Mar 6 20:25:36 EST 2017
> files=`ls``
> echo $files
> echo $today

> files=`ls`
> echo $files
awk.input bar dir example.awk foo greeting myfiles names.input newfile newnewfile reverse.awk sdfjs stuff subdir today typescript
> date +%Y
2017
> year=$(date +%Y)
> echo $year
2017
> # grep $(date +%Y) myfile
> ls
awk.input    foo	  newfile      stuff
bar	     greeting	  newnewfile   subdir
dir	     myfiles	  reverse.awk  today
example.awk  names.input  sdfjs        typescript
> grep $(date +%Y) awk.input
> vi awk.input
> grep $(date +%Y) awk.input
This is 2017
> mymatch=$(grep $(date +%Y) awk.input)
>
>
> echo $mymatch
This is 2017
> echo *
awk.input bar dir example.awk foo greeting myfiles names.input newfile newnewfile reverse.awk sdfjs stuff subdir today typescript
> echo s*
sdfjs stuff subdir
> /home/unixtool/bin/showargs s*
Arg 0: /home/unixtool/bin/showargs
Arg 1: sdfjs
Arg 2: stuff
Arg 3: subdir
> /home/unixtool/bin/showargs 's*'
Arg 0: /home/unixtool/bin/showargs
Arg 1: s*
> cat s*
computer:78
computer computer:34
sdfkjsdflkjsdf
phone:3
laptop:11
sdjsdfjsfj
tablet:100
cat: subdir: Is a directory
> /home/unixtool/bin/showargs s*
Arg 0: /home/unixtool/bin/showargs
Arg 1: sdfjs
Arg 2: stuff
Arg 3: subdir
> /home/unixtool/bin/showargs x*
Arg 0: /home/unixtool/bin/showargs
Arg 1: x*
> date ; echo hello
Mon Mar  6 20:36:31 EST 2017
hello
> ( date ; echo hello )
Mon Mar  6 20:36:36 EST 2017
hello
> ( date ; echo hello ) | wc
      2       7      35
> ( date ; echo hello ) | wc -l
2
> ( date ; echo hello ) > /tmp/myoutput
> cat /tmp/myoutput
Mon Mar  6 20:36:49 EST 2017
hello
> ls fdssdfjdsf
ls: cannot access fdssdfjdsf: No such file or directory
> echo $?
2
> ls sdfjsdfjsdfj && date
ls: cannot access sdfjsdfjsdfj: No such file or directory
> echo hello && date
hello
Mon Mar  6 20:38:56 EST 2017
> ls sdfjsdfjsdfj || date
ls: cannot access sdfjsdfjsdfj: No such file or directory
Mon Mar  6 20:39:05 EST 2017
> echo hello || date
hello
> test
> test a = b
> echo $?
1
> test a = a
> echo $?
0
> [ a = a ]
> echo $?
0
> [ a = b ]
> echo $?
1
> /home/unixtool/bin/showargs a = b ]
Arg 0: /home/unixtool/bin/showargs
Arg 1: a
Arg 2: =
Arg 3: b
Arg 4: ]
>
> if test a = b
> then
>   echo got a match
> else
>   echo no match
> fi
no match
> if a = b
> then
>   echo sdf
> else
>   echo sdfj
> fi
ksh: a: not found [No such file or directory]
sdfj
> if echo hello
> then
>   echo there
> fi
hello
there
> if [ a = b ]
> then
>   echo match
> fi
>
> ls
awk.input    foo	  newfile      stuff
bar	     greeting	  newnewfile   subdir
dir	     myfiles	  reverse.awk  today
example.awk  names.input  sdfjs        typescript
> vi greeting
> greeting
Hello, kornj.
You passed in 0 arguments
You entered the argument 2 as
You are cool
> fg
ksh: fg: no such job
> vi greeting
[1] + Stopped                  vi greeting
> [ $USER = kornj ]
> echo $?
0
> /home/unixtool/bin/showargs $USER = kornj ]
Arg 0: /home/unixtool/bin/showargs
Arg 1: kornj
Arg 2: =
Arg 3: kornj
Arg 4: ]
>
>
> test -f sdfjsdfj
> echo $?
1
> ls
awk.input    foo	  newfile      stuff
bar	     greeting	  newnewfile   subdir
dir	     myfiles	  reverse.awk  today
example.awk  names.input  sdfjs        typescript
> test -f foo
> echo $?
0
> if [ -f foo ]
> then
>   wc -l foo
> else
>   echo not found
> fi
wc: foo: Permission denied
> ls -l foo
--w--w--w-. 2 kornj kornj 0 Jan 30 20:25 foo
>
>
> vi script1
> chmod +x script1
> vi script2
> chmod +X script2
> script1 && script2
hello
ksh: script2: cannot execute [Permission denied]
> chmod +x script2
> script1 && script2
hello
Mon Mar  6 20:52:43 EST 2017
> script2 && script1
Mon Mar  6 20:52:49 EST 2017
> cat script2
date
exit 1
> if script1 && script2
> then
>   echo it worked
> fi
hello
Mon Mar  6 20:53:13 EST 2017
> test $(wc -l < awk.input) -gt 5
> echo $?
1
> wc -l < awk.input
5
> /home/unixtool/bin/showargs $(wc -l < awk.inp>
Arg 0: /home/unixtool/bin/showargs
Arg 1: 5
Arg 2: -gt
Arg 3: 5
> if [ $(wc -l < awk.input) -gt 5 ]
> then
>   echo worked
> fi
>
> if [ $(wc -l < awk.input) -gt 3 ]^Jthen^J  ec>
worked
> test 3 -lt 5
> echo $?
0
> [ 3 -lt 5 ]
> [ 3 < 5 ]
ksh: 5: cannot open [No such file or directory]
> [[ 3 < 5 ]]
> echo $?
0
> expr 2 + 2
4
> x=10
> x=$(expr $x + 2)
> echo $x
12
> x=$(expr $x '*' 2)
> echo $x
24
> You have stopped jobs
> exit
Vim: Caught deadly signal HUP
Vim: Finished.
variableName="value"

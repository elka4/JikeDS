TEE(1)                           User Commands                          TEE(1)



NAME
       tee - read from standard input and write to standard output and files

SYNOPSIS
       tee [OPTION]... [FILE]...

DESCRIPTION
       Copy standard input to each FILE, and also to standard output.

       -a, --append
              append to the given FILEs, do not overwrite

       -i, --ignore-interrupts
              ignore interrupt signals

       --help display this help and exit

       --version
              output version information and exit

       If a FILE is -, copy again to standard output.

AUTHOR
       Written by Mike Parker, Richard M. Stallman, and David MacKenzie.

REPORTING BUGS
       Report tee bugs to bug-coreutils@gnu.org
       GNU coreutils home page: <http://www.gnu.org/software/coreutils/>
       General help using GNU software: <http://www.gnu.org/gethelp/>
       Report tee translation bugs to <http://translationproject.org/team/>

COPYRIGHT
       Copyright  ©  2010  Free Software Foundation, Inc.  License GPLv3+: GNU
       GPL version 3 or later <http://gnu.org/licenses/gpl.html>.
       This is free software: you are free  to  change  and  redistribute  it.
       There is NO WARRANTY, to the extent permitted by law.

SEE ALSO
       The  full  documentation for tee is maintained as a Texinfo manual.  If
       the info and tee programs are properly installed at your site, the com-
       mand

              info coreutils 'tee invocation'

       should give you access to the complete manual.



GNU coreutils 8.4                 April 2016                            TEE(1)
tee.sh (END)
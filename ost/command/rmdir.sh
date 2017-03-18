RMDIR(1)                         User Commands                        RMDIR(1)



NAME
       rmdir - remove empty directories

SYNOPSIS
       rmdir [OPTION]... DIRECTORY...

DESCRIPTION
       Remove the DIRECTORY(ies), if they are empty.

       --ignore-fail-on-non-empty

              ignore each failure that is solely because a directory

              is non-empty

       -p, --parents
              remove  DIRECTORY  and  its ancestors; e.g., ‘rmdir -p a/b/c’ is
              similar to ‘rmdir a/b/c a/b a’

       -v, --verbose
              output a diagnostic for every directory processed

       --help display this help and exit

       --version
              output version information and exit

AUTHOR
       Written by David MacKenzie.

REPORTING BUGS
       Report rmdir bugs to bug-coreutils@gnu.org
       GNU coreutils home page: <http://www.gnu.org/software/coreutils/>
       General help using GNU software: <http://www.gnu.org/gethelp/>
       Report rmdir translation bugs to <http://translationproject.org/team/>

COPYRIGHT
       Copyright © 2010 Free Software Foundation, Inc.   License  GPLv3+:  GNU
       GPL version 3 or later <http://gnu.org/licenses/gpl.html>.
       This  is  free  software:  you  are free to change and redistribute it.
       There is NO WARRANTY, to the extent permitted by law.

SEE ALSO
       rmdir(2)

       The full documentation for rmdir is maintained as a Texinfo manual.  If
       the  info  and  rmdir programs are properly installed at your site, the
       command

              info coreutils 'rmdir invocation'

       should give you access to the complete manual.



GNU coreutils 8.4                 April 2016                          RMDIR(1)
rmdir.sh (END)
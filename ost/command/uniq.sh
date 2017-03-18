UNIQ(1)                          User Commands                         UNIQ(1)



NAME
       uniq - report or omit repeated lines

SYNOPSIS
       uniq [OPTION]... [INPUT [OUTPUT]]

DESCRIPTION
       Filter  adjacent matching lines from INPUT (or standard input), writing
       to OUTPUT (or standard output).

       With no options, matching lines are merged to the first occurrence.

       Mandatory arguments to long options are  mandatory  for  short  options
       too.

       -c, --count
              prefix lines by the number of occurrences

       -d, --repeated
              only print duplicate lines

       -D, --all-repeated[=delimit-method]
              print              all              duplicate              lines
              delimit-method={none(default),prepend,separate}  Delimiting   is
              done with blank lines.

       -f, --skip-fields=N
              avoid comparing the first N fields

       -i, --ignore-case
              ignore differences in case when comparing

       -s, --skip-chars=N
              avoid comparing the first N characters

       -u, --unique
              only print unique lines

       -z, --zero-terminated
              end lines with 0 byte, not newline

       -w, --check-chars=N
              compare no more than N characters in lines

       --help display this help and exit

       --version
              output version information and exit

       A field is a run of blanks (usually spaces and/or TABs), then non-blank
       characters.  Fields are skipped before chars.

       Note: ’uniq’ does not detect repeated lines unless they  are  adjacent.
       You  may want to sort the input first, or use ‘sort -u’ without ‘uniq’.
       Also, comparisons honor the rules specified by ‘LC_COLLATE’.

AUTHOR
       Written by Richard M. Stallman and David MacKenzie.

REPORTING BUGS
       Report uniq bugs to bug-coreutils@gnu.org
       GNU coreutils home page: <http://www.gnu.org/software/coreutils/>
       General help using GNU software: <http://www.gnu.org/gethelp/>
       Report uniq translation bugs to <http://translationproject.org/team/>

COPYRIGHT
       Copyright © 2010 Free Software Foundation, Inc.   License  GPLv3+:  GNU
       GPL version 3 or later <http://gnu.org/licenses/gpl.html>.
       This  is  free  software:  you  are free to change and redistribute it.
       There is NO WARRANTY, to the extent permitted by law.

SEE ALSO
       comm(1), join(1)

       The full documentation for uniq is maintained as a Texinfo manual.   If
       the  info  and  uniq  programs are properly installed at your site, the
       command

              info coreutils 'uniq invocation'

       should give you access to the complete manual.



GNU coreutils 8.4                 April 2016                           UNIQ(1)
uniq.sh (END)
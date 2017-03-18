CHMOD(1)                         User Commands                        CHMOD(1)



NAME
       chmod - change file mode bits

SYNOPSIS
       chmod [OPTION]... MODE[,MODE]... FILE...
       chmod [OPTION]... OCTAL-MODE FILE...
       chmod [OPTION]... --reference=RFILE FILE...

DESCRIPTION
       This manual page documents the GNU version of chmod.  chmod changes the
       file mode bits of each given file  according  to  mode,  which  can  be
       either a symbolic representation of changes to make, or an octal number
       representing the bit pattern for the new mode bits.

       The format of a symbolic mode is  [ugoa...][[+-=][perms...]...],  where
       perms  is  either zero or more letters from the set rwxXst, or a single
       letter from the set ugo.  Multiple symbolic modes can be  given,  sepa-
       rated by commas.

       A  combination  of the letters ugoa controls which users’ access to the
       file will be changed: the user who owns it  (u),  other  users  in  the
       file’s group (g), other users not in the file’s group (o), or all users
       (a).  If none of these are given, the effect is as if a were given, but
       bits that are set in the umask are not affected.

       The  operator  +  causes the selected file mode bits to be added to the
       existing file mode bits of each file; - causes them to be removed;  and
       =  causes  them  to  be added and causes unmentioned bits to be removed
       except that a directory’s unmentioned set user and group  ID  bits  are
       not affected.

       The  letters  rwxXst select file mode bits for the affected users: read
       (r), write (w), execute (or search for directories) (x), execute/search
       only  if  the file is a directory or already has execute permission for
       some user (X), set user or group ID on execution (s), restricted  dele-
       tion  flag or sticky bit (t).  Instead of one or more of these letters,
       you can specify exactly one of the letters ugo: the permissions granted
       to  the  user  who  owns the file (u), the permissions granted to other
       users who are members of the file’s  group  (g),  and  the  permissions
       granted  to  users  that are in neither of the two preceding categories
       (o).

       A numeric mode is from one to  four  octal  digits  (0-7),  derived  by
       adding up the bits with values 4, 2, and 1.  Omitted digits are assumed
       to be leading zeros.  The first digit selects the set user ID  (4)  and
       set group ID (2) and restricted deletion or sticky (1) attributes.  The
       second digit selects permissions for the user who owns the  file:  read
       (4),  write  (2),  and  execute  (1); the third selects permissions for
       other users in the file’s group, with the same values; and  the  fourth
       for other users not in the file’s group, with the same values.

       chmod never changes the permissions of symbolic links; the chmod system
       call cannot change their permissions.  This is not a problem since  the
       permissions  of  symbolic links are never used.  However, for each sym-
       bolic link listed on the command line, chmod changes the permissions of
       the pointed-to file.  In contrast, chmod ignores symbolic links encoun-
       tered during recursive directory traversals.

SETUID AND SETGID BITS
       chmod clears the set-group-ID bit of a regular file if the file’s group
       ID  does  not  match the user’s effective group ID or one of the user’s
       supplementary group IDs, unless the user  has  appropriate  privileges.
       Additional restrictions may cause the set-user-ID and set-group-ID bits
       of MODE or RFILE to be ignored.  This behavior depends  on  the  policy
       and  functionality of the underlying chmod system call.  When in doubt,
       check the underlying system behavior.

       chmod preserves a directory’s set-user-ID and set-group-ID bits  unless
       you  explicitly  specify otherwise.  You can set or clear the bits with
       symbolic modes like u+s and g-s, and you can set (but  not  clear)  the
       bits with a numeric mode.

RESTRICTED DELETION FLAG OR STICKY BIT
       The  restricted  deletion  flag  or  sticky  bit is a single bit, whose
       interpretation depends on the file type.  For directories, it  prevents
       unprivileged  users  from  removing or renaming a file in the directory
       unless they  own  the  file  or  the  directory;  this  is  called  the
       restricted  deletion  flag  for the directory, and is commonly found on
       world-writable directories like /tmp.  For regular files on some  older
       systems,  the  bit saves the program’s text image on the swap device so
       it will load more quickly when run; this is called the sticky bit.

OPTIONS
       Change the mode of each FILE to MODE.

       -c, --changes
              like verbose but report only when a change is made

       --no-preserve-root
              do not treat ‘/’ specially (the default)

       --preserve-root
              fail to operate recursively on ‘/’

       -f, --silent, --quiet
              suppress most error messages

       -v, --verbose
              output a diagnostic for every file processed

       --reference=RFILE
              use RFILE’s mode instead of MODE values

       -R, --recursive
              change files and directories recursively

       --help display this help and exit

       --version
              output version information and exit

       Each MODE is of the form ‘[ugoa]*([-+=]([rwxXst]*|[ugo]))+’.

AUTHOR
       Written by David MacKenzie and Jim Meyering.

REPORTING BUGS
       Report chmod bugs to bug-coreutils@gnu.org
       GNU coreutils home page: <http://www.gnu.org/software/coreutils/>
       General help using GNU software: <http://www.gnu.org/gethelp/>
       Report chmod translation bugs to <http://translationproject.org/team/>

COPYRIGHT
       Copyright © 2010 Free Software Foundation, Inc.   License  GPLv3+:  GNU
       GPL version 3 or later <http://gnu.org/licenses/gpl.html>.
       This  is  free  software:  you  are free to change and redistribute it.
       There is NO WARRANTY, to the extent permitted by law.

SEE ALSO
       chmod(2)

       The full documentation for chmod is maintained as a Texinfo manual.  If
       the  info  and  chmod programs are properly installed at your site, the
       command

              info coreutils 'chmod invocation'

       should give you access to the complete manual.



GNU coreutils 8.4                 April 2016                          CHMOD(1)
GAWK(1)                        Utility Commands                        GAWK(1)

p^Hpg^Hga^Haw^Hwk^Hk [ POSIX or GNU style options ] -^H-f^Hf _^Hp_^Hr_^Ho_^Hg_^Hr_^Ha_^Hm_^H-_^Hf_^Hi_^Hl_^He [ -^H--^H- ] file

NAME
       gawk - pattern scanning and processing language

SYNOPSIS
       gawk [ POSIX or GNU style options ] -f program-file [ -- ] file ...
       gawk [ POSIX or GNU style options ] [ -- ] program-text file ...

       pgawk [ POSIX or GNU style options ] -f program-file [ -- ] file ...
       pgawk [ POSIX or GNU style options ] [ -- ] program-text file ...

DESCRIPTION
       Gawk  is  the  GNU Project’s implementation of the AWK programming lan-
       guage.  It conforms to the definition of  the  language  in  the  POSIX
       1003.1  Standard.   This version in turn is based on the description in
       The AWK Programming Language, by Aho, Kernighan, and  Weinberger,  with
       the additional features found in the System V Release 4 version of UNIX
       awk.  Gawk also provides more recent Bell Laboratories awk  extensions,
       and a number of GNU-specific extensions.

       Pgawk  is  the profiling version of gawk.  It is identical in every way
       to gawk, except that programs run more  slowly,  and  it  automatically
       produces  an  execution profile in the file awkprof.out when done.  See
       the --profile option, below.

       The command line consists of options to gawk itself,  the  AWK  program
       text  (if  not supplied via the -f or --file options), and values to be
       made available in the ARGC and ARGV pre-defined AWK variables.

OPTION FORMAT
       Gawk options may be either traditional POSIX  one  letter  options,  or
       GNU-style  long  options.  POSIX options start with a single “-”, while
       long options start with “--”.  Long options are provided for both  GNU-
       specific features and for POSIX-mandated features.

       Following  the  POSIX  standard, gawk-specific options are supplied via
       arguments to the -W option.  Multiple -W options may be  supplied  Each
       -W  option  has  a corresponding long option, as detailed below.  Argu-
       ments to long options are either joined with the option by an  =  sign,
       with no intervening spaces, or they may be provided in the next command
       line argument.  Long options may be abbreviated, as long as the  abbre-
       viation remains unique.

OPTIONS
       Gawk accepts the following options, listed by frequency.

       -F fs
       --field-separator fs
              Use fs for the input field separator (the value of the FS prede-
              fined variable).

       -v var=val
       --assign var=val
              Assign the value val to the variable var,  before  execution  of
              the  program  begins.  Such variable values are available to the
              BEGIN block of an AWK program.

       -f program-file
       --file program-file
              Read the AWK program source from the file program-file,  instead
              of  from  the  first  command  line  argument.   Multiple -f (or
              --file) options may be used.

       -mf NNN
       -mr NNN
              Set various memory limits to the value NNN.  The f flag sets the
              maximum number of fields, and the r flag sets the maximum record
              size.  These two flags and the -m option  are  from  an  earlier
              version  of  the Bell Laboratories research version of UNIX awk.
              They are ignored by gawk, since gawk has no pre-defined  limits.
              (Current  versions of the Bell Laboratories awk no longer accept
              them.)

       -O
       --optimize
              Enable optimizations upon the  internal  representation  of  the
              program.  Currently, this includes just simple constant-folding.
              The gawk maintainer hopes to add additional  optimizations  over
              time.

       -W compat
       -W traditional
       --compat
       --traditional
              Run  in compatibility mode.  In compatibility mode, gawk behaves
              identically to UNIX awk; none of the GNU-specific extensions are
              recognized.   The  use  of  --traditional  is preferred over the
              other forms of this option.  See GNU EXTENSIONS, below, for more
              information.

       -W copyleft
       -W copyright
       --copyleft
       --copyright
              Print the short version of the GNU copyright information message
              on the standard output and exit successfully.

       -W dump-variables[=file]
       --dump-variables[=file]
              Print a sorted list of global variables, their types  and  final
              values  to file.  If no file is provided, gawk uses a file named
              awkvars.out in the current directory.
              Having a list of all the global variables is a good way to  look
              for  typographical  errors in your programs.  You would also use
              this option if you have a large program with a lot of functions,
              and  you want to be sure that your functions don’t inadvertently
              use global variables that you meant to be  local.   (This  is  a
              particularly  easy  mistake  to  make with simple variable names
              like i, j, and so on.)

       -W exec file
       --exec file
              Similar to -f, however, this is option  is  the  last  one  pro-
              cessed.   This should be used with #!  scripts, particularly for
              CGI applications, to avoid passing in options or source code (!)
              on  the  command line from a URL.  This option disables command-
              line variable assignments.
-W gen-po
       --gen-po
              Scan and parse the AWK program, and generate a  GNU  .po  format
              file on standard output with entries for all localizable strings
              in the program.  The program itself is not  executed.   See  the
              GNU gettext distribution for more information on .po files.

       -W help
       -W usage
       --help
       --usage
              Print a relatively short summary of the available options on the
              standard output.  (Per the GNU Coding Standards,  these  options
              cause an immediate, successful exit.)

       -W lint[=value]
       --lint[=value]
              Provide  warnings  about  constructs  that  are  dubious or non-
              portable to other AWK implementations.  With an  optional  argu-
              ment  of  fatal, lint warnings become fatal errors.  This may be
              drastic, but its use will certainly encourage the development of
              cleaner  AWK  programs.   With  an optional argument of invalid,
              only warnings about things that are actually invalid are issued.
              (This is not fully implemented yet.)

       -W lint-old
       --lint-old
              Provide  warnings  about constructs that are not portable to the
              original version of Unix awk.

       -W non-decimal-data
       --non-decimal-data
              Recognize octal and hexadecimal values in input data.  Use  this
              option with great caution!

       -W posix
       --posix
              This  turns on compatibility mode, with the following additional
              restrictions:

              · \x escape sequences are not recognized.

              · Only space and tab act as field separators when FS is set to a
                single space, newline does not.

              · You cannot continue lines after ?  and :.

              · The synonym func for the keyword function is not recognized.

              · The  operators ** and **= cannot be used in place of ^ and ^=.

              · The fflush() function is not available.

       -W profile[=prof_file]
       --profile[=prof_file]
              Send profiling data to prof_file.  The default  is  awkprof.out.
              When  run with gawk, the profile is just a “pretty printed” ver-
              sion of the program.  When run with pgawk, the profile  contains
              execution  counts  of  each statement in the program in the left
              margin and function call counts for each user-defined  function.

       -W re-interval
       --re-interval
              Enable  the  use  of  interval expressions in regular expression
              matching (see Regular Expressions, below).  Interval expressions
              were not traditionally available in the AWK language.  The POSIX
              standard added them, to make awk and egrep consistent with  each
              other.   However, their use is likely to break old AWK programs,
              so gawk only provides them  if  they  are  requested  with  this
              option, or when --posix is specified.

       -W source program-text
       --source program-text
              Use program-text as AWK program source code.  This option allows
              the easy intermixing of library functions (used via the  -f  and
              --file  options)  with  source code entered on the command line.
              It is intended primarily for medium to large AWK  programs  used
              in shell scripts.

       -W use-lc-numeric
       --use-lc-numeric
              This  forces  gawk  to  use the locale’s decimal point character
              when parsing input data.  Although the POSIX  standard  requires
              this  behavior,  and gawk does so when --posix is in effect, the
              default is to follow traditional behavior and use  a  period  as
              the  decimal  point, even in locales where the period is not the
              decimal point character.   This  option  overrides  the  default
              behavior,  without  the full draconian strictness of the --posix
              option.

       -W version
       --version
              Print version information for this particular copy  of  gawk  on
              the  standard  output.  This is useful mainly for knowing if the
              current copy of gawk on your system is up to date  with  respect
              to  whatever the Free Software Foundation is distributing.  This
              is also useful when reporting bugs.  (Per the GNU  Coding  Stan-
              dards, these options cause an immediate, successful exit.)

       --     Signal the end of options. This is useful to allow further argu-
              ments to the AWK program itself to start with a “-”.  This  pro-
              vides  consistency  with the argument parsing convention used by
              most other POSIX programs.

       In compatibility mode, any other options are flagged  as  invalid,  but
       are  otherwise  ignored.   In normal operation, as long as program text
       has been supplied, unknown options are passed on to the AWK program  in
       the ARGV array for processing.  This is particularly useful for running
       AWK programs via the “#!” executable interpreter mechanism.
AWK PROGRAM EXECUTION
       An AWK program consists of a sequence of pattern-action statements  and
       optional function definitions.

              pattern   { action statements }
              function name(parameter list) { statements }

       Gawk  first reads the program source from the program-file(s) if speci-
       fied, from arguments to --source, or from the first non-option argument
       on  the command line.  The -f and --source options may be used multiple
       times on the command line.  Gawk reads the program text as if  all  the
       program-files  and  command  line  source  texts  had been concatenated
       together.  This is useful for  building  libraries  of  AWK  functions,
       without  having to include them in each new AWK program that uses them.
       It also provides the ability to mix library functions with command line
       programs.

       The  environment  variable  AWKPATH specifies a search path to use when
       finding source files named with the -f option.  If this  variable  does
       not  exist,  the default path is ".:/usr/local/share/awk".  (The actual
       directory may vary, depending upon how gawk was built  and  installed.)
       If a file name given to the -f option contains a “/” character, no path
       search is performed.

       Gawk executes AWK programs in the following order.  First, all variable
       assignments specified via the -v option are performed.  Next, gawk com-
       piles the program into an internal form.  Then, gawk executes the  code
       in  the  BEGIN  block(s)  (if any), and then proceeds to read each file
       named in the ARGV array.  If there are no files named  on  the  command
       line, gawk reads the standard input.

       If a filename on the command line has the form var=val it is treated as
       a variable assignment.  The variable var will  be  assigned  the  value
       val.   (This  happens after any BEGIN block(s) have been run.)  Command
       line variable assignment is most useful for dynamically assigning  val-
       ues  to  the  variables  AWK  uses  to control how input is broken into
       fields and records.  It is also useful for controlling state if  multi-
       ple passes are needed over a single data file.

       If  the value of a particular element of ARGV is empty (""), gawk skips
       over it.

       For each record in the input, gawk tests  to  see  if  it  matches  any
       pattern  in the AWK program.  For each pattern that the record matches,
       the associated action is executed.  The  patterns  are  tested  in  the
       order they occur in the program.

       Finally,  after  all  the input is exhausted, gawk executes the code in
       the END block(s) (if any).

VARIABLES, RECORDS AND FIELDS
       AWK variables are dynamic; they come into existence when they are first
       used.   Their  values  are either floating-point numbers or strings, or
       both, depending upon how they are used.  AWK also has  one  dimensional
       arrays; arrays with multiple dimensions may be simulated.  Several pre-
       defined variables are set as a program runs;  these  are  described  as
       needed and summarized below.

   Records
       Normally, records are separated by newline characters.  You can control
       how records are separated by assigning values to the built-in  variable
       RS.   If  RS is any single character, that character separates records.
       Otherwise, RS is a regular expression.  Text in the input that  matches
       this  regular expression separates the record.  However, in compatibil-
       ity mode, only the first character of its string value is used for sep-
       arating  records.   If  RS  is set to the null string, then records are
       separated by blank lines.  When RS is set to the null string, the  new-
       line  character  always acts as a field separator, in addition to what-
       ever value FS may have.

   Fields
       As each input record is read, gawk splits the record into fields, using
       the value of the FS variable as the field separator.  If FS is a single
       character, fields are separated by that character.  If FS is  the  null
       string,  then each individual character becomes a separate field.  Oth-
       erwise, FS is expected to be a full regular expression.  In the special
       case  that FS is a single space, fields are separated by runs of spaces
       and/or tabs and/or newlines.  (But see the section POSIX COMPATIBILITY,
       below).   NOTE:  The  value  of IGNORECASE (see below) also affects how
       fields are split when FS is a regular expression, and how  records  are
       separated when RS is a regular expression.

       If  the  FIELDWIDTHS  variable is set to a space separated list of num-
       bers, each field is expected to have fixed width, and  gawk  splits  up
       the  record  using  the  specified widths.  The value of FS is ignored.
       Assigning a new value to FS  overrides  the  use  of  FIELDWIDTHS,  and
       restores the default behavior.

       Each  field  in the input record may be referenced by its position, $1,
       $2, and so on.  $0 is the whole record.  Fields need not be  referenced
       by constants:

              n = 5
              print $n

       prints the fifth field in the input record.

       The  variable  NF  is  set  to  the total number of fields in the input
       record.

       References to non-existent fields (i.e. fields after $NF)  produce  the
       null-string.  However, assigning to a non-existent field (e.g., $(NF+2)
       = 5) increases the value of NF, creates any intervening fields with the
       null  string  as  their  value, and causes the value of $0 to be recom-
       puted, with the fields being separated by the value of OFS.  References
       to  negative  numbered  fields  cause  a  fatal error.  Decrementing NF
       causes the values of fields past the new value  to  be  lost,  and  the
       value  of  $0  to be recomputed, with the fields being separated by the
       value of OFS.

       Assigning a value to an existing field causes the whole  record  to  be
       rebuilt  when  $0  is  referenced.   Similarly, assigning a value to $0
       causes the record to be resplit, creating new values for the fields.
Built-in Variables
       Gawk’s built-in variables are:


       ARGC        The number of command  line  arguments  (does  not  include
                   options to gawk, or the program source).

       ARGIND      The index in ARGV of the current file being processed.

       ARGV        Array of command line arguments.  The array is indexed from
                   0 to ARGC - 1.  Dynamically changing the contents  of  ARGV
                   can control the files used for data.

       BINMODE     On  non-POSIX  systems,  specifies use of “binary” mode for
                   all file I/O.  Numeric values of 1, 2, or 3,  specify  that
                   input  files,  output  files,  or  all files, respectively,
                   should use binary I/O.  String values of "r", or "w"  spec-
                   ify that input files, or output files, respectively, should
                   use binary I/O.  String values of "rw" or "wr" specify that
                   all files should use binary I/O.  Any other string value is
                   treated as "rw", but generates a warning message.

       CONVFMT     The conversion format for numbers, "%.6g", by default.

       ENVIRON     An array containing the values of the current  environment.
                   The  array  is  indexed  by the environment variables, each
                   element being the  value  of  that  variable  (e.g.,  ENVI-
                   RON["HOME"]  might  be  /home/arnold).  Changing this array
                   does not affect the environment seen by programs which gawk
                   spawns via redirection or the system() function.

       ERRNO       If  a  system  error  occurs either doing a redirection for
                   getline, during a read for getline, or  during  a  close(),
                   then ERRNO will contain a string describing the error.  The
                   value is subject to translation in non-English locales.

       FIELDWIDTHS A white-space separated list  of  fieldwidths.   When  set,
                   gawk  parses  the input into fields of fixed width, instead
                   of using the value of the FS variable as the field  separa-
                   tor.

       FILENAME    The name of the current input file.  If no files are speci-
                   fied on the command line, the value  of  FILENAME  is  “-”.
                   However,  FILENAME  is  undefined  inside  the  BEGIN block
                   (unless set by getline).

       FNR         The input record number in the current input file.

       FS          The input field separator, a space by default.  See Fields,
                   above.

       IGNORECASE  Controls the case-sensitivity of all regular expression and
                   string operations.  If IGNORECASE  has  a  non-zero  value,
                   then  string  comparisons  and  pattern  matching in rules,
                   field splitting with FS, record separating with RS, regular
                   expression  matching  with  ~  and  !~,  and  the gensub(),
                   gsub(), index(), match(), split(), and sub() built-in func-
                   tions  all ignore case when doing regular expression opera-
                   tions.  NOTE: Array subscripting is not affected.  However,
                   the asort() and asorti() functions are affected.
                   Thus,  if IGNORECASE is not equal to zero, /aB/ matches all
                   of the strings "ab", "aB", "Ab", and "AB".  As with all AWK
                   variables,  the initial value of IGNORECASE is zero, so all
                   regular expression and string operations are normally case-
                   sensitive.  Under Unix, the full ISO 8859-1 Latin-1 charac-
                   ter set is used when ignoring case.  As of gawk 3.1.4,  the
                   case  equivalencies  are fully locale-aware, based on the C
                   <ctype.h> facilities such as isalpha(), and toupper().

       LINT        Provides dynamic control of the --lint option  from  within
                   an AWK program.  When true, gawk prints lint warnings. When
                   false,  it  does  not.   When  assigned  the  string  value
                   "fatal",  lint  warnings  become fatal errors, exactly like
                   --lint=fatal.  Any other true value just prints warnings.

       NF          The number of fields in the current input record.

       NR          The total number of input records seen so far.

       OFMT        The output format for numbers, "%.6g", by default.

       OFS         The output field separator, a space by default.

       ORS         The output record separator, by default a newline.

       PROCINFO    The elements of this array provide  access  to  information
                   about  the running AWK program.  On some systems, there may
                   be elements in the array,  "group1"  through  "groupn"  for
                   some  n,  which  is the number of supplementary groups that
                   the process has.  Use the in operator  to  test  for  these
                   elements.   The  following  elements  are  guaranteed to be
                   available:

                   PROCINFO["egid"]   the value of the getegid(2) system call.

                   PROCINFO["euid"]   the value of the geteuid(2) system call.

                   PROCINFO["FS"]     "FS" if field splitting with  FS  is  in
                                      effect, or "FIELDWIDTHS" if field split-
                                      ting with FIELDWIDTHS is in effect.

                   PROCINFO["gid"]    the value of the getgid(2) system  call.

                   PROCINFO["pgrpid"] the process group ID of the current pro-
                                      cess.

                   PROCINFO["pid"]    the process ID of the current process.

                   PROCINFO["ppid"]   the parent process  ID  of  the  current
                                      process.

                   PROCINFO["uid"]    the  value of the getuid(2) system call.

                   PROCINFO["version"]
                                      The version of gawk.  This is  available
                                      from version 3.1.4 and later.

       RS          The input record separator, by default a newline.
 RT          The record terminator.  Gawk sets RT to the input text that
                   matched the character or regular  expression  specified  by
                   RS.

       RSTART      The  index  of the first character matched by match(); 0 if
                   no match.  (This implies that character  indices  start  at
                   one.)

       RLENGTH     The  length  of  the  string  matched  by match(); -1 if no
                   match.

       SUBSEP      The character used to separate multiple subscripts in array
                   elements, by default "\034".

       TEXTDOMAIN  The text domain of the AWK program; used to find the local-
                   ized translations for the program’s strings.

   Arrays
       Arrays are subscripted with an expression between  square  brackets  ([
       and ]).  If the expression is an expression list (expr, expr ...)  then
       the array subscript is a string consisting of the concatenation of  the
       (string) value of each expression, separated by the value of the SUBSEP
       variable.  This facility  is  used  to  simulate  multiply  dimensioned
       arrays.  For example:

              i = "A"; j = "B"; k = "C"
              x[i, j, k] = "hello, world\n"

       assigns the string "hello, world\n" to the element of the array x which
       is indexed by the string "A\034B\034C".  All arrays in AWK are associa-
       tive, i.e. indexed by string values.

       The  special  operator  in may be used to test if an array has an index
       consisting of a particular value.

              if (val in array)
                   print array[val]

       If the array has multiple subscripts, use (i, j) in array.

       The in construct may also be used in a for loop to iterate over all the
       elements of an array.

       An  element  may  be  deleted from an array using the delete statement.
       The delete statement may also be used to delete the entire contents  of
       an array, just by specifying the array name without a subscript.

   Variable Typing And Conversion
       Variables  and  fields  may be (floating point) numbers, or strings, or
       both.  How the value of a variable is interpreted depends upon its con-
       text.  If used in a numeric expression, it will be treated as a number;
       if used as a string it will be treated as a string.

       To force a variable to be treated as a number, add 0 to it; to force it
       to be treated as a string, concatenate it with the null string.

       When  a  string must be converted to a number, the conversion is accom-
       plished using strtod(3).  A number is converted to a  string  by  using
       the  value  of  CONVFMT  as  a  format  string for sprintf(3), with the
       numeric value of the variable as the argument.   However,  even  though
       all  numbers in AWK are floating-point, integral values are always con-
       verted as integers.  Thus, given

              CONVFMT = "%2.2f"
              a = 12
              b = a ""

       the variable b has a string value of "12" and not "12.00".

       When operating in POSIX mode (such as with  the  --posix  command  line
       option), beware that locale settings may interfere with the way decimal
       numbers are treated: the decimal separator of the numbers you are feed-
       ing  to  gawk  must  conform  to what your locale would expect, be it a
       comma (,) or a period (.).

       Gawk performs comparisons as follows: If  two  variables  are  numeric,
       they  are  compared numerically.  If one value is numeric and the other
       has a string value that is a “numeric  string,”  then  comparisons  are
       also  done numerically.  Otherwise, the numeric value is converted to a
       string and a string comparison is performed.  Two strings are compared,
       of course, as strings.

       Note that string constants, such as "57", are not numeric strings, they
       are string constants.  The idea of “numeric  string”  only  applies  to
       fields,  getline  input,  FILENAME, ARGV elements, ENVIRON elements and
       the elements of an array created by split() that are  numeric  strings.
       The  basic  idea  is  that  user input, and only user input, that looks
       numeric, should be treated that way.

       Uninitialized variables have the numeric value 0 and the  string  value
       "" (the null, or empty, string).

   Octal and Hexadecimal Constants
       Starting  with version 3.1 of gawk , you may use C-style octal and hex-
       adecimal constants in your AWK program source code.  For  example,  the
       octal  value  011 is equal to decimal 9, and the hexadecimal value 0x11
       is equal to decimal 17.

   String Constants
       String constants in AWK are sequences of  characters  enclosed  between
       double quotes (").  Within strings, certain escape sequences are recog-
       nized, as in C.  These are:


       \\   A literal backslash.

       \a   The “alert” character; usually the ASCII BEL character.

       \b   backspace.

       \f   form-feed.

       \n   newline.

       \r   carriage return.

       \t   horizontal tab.
 \v   vertical tab.

       \xhex digits
            The character represented by the string of hexadecimal digits fol-
            lowing the \x.  As in ANSI C, all following hexadecimal digits are
            considered part of the escape sequence.  (This feature should tell
            us something about language design by committee.)  E.g., "\x1B" is
            the ASCII ESC (escape) character.

       \ddd The character represented by the 1-, 2-, or  3-digit  sequence  of
            octal digits.  E.g., "\033" is the ASCII ESC (escape) character.

       \c   The literal character c.

       The  escape  sequences may also be used inside constant regular expres-
       sions (e.g., /[ \t\f\n\r\v]/ matches whitespace characters).

       In compatibility mode, the characters represented by octal and hexadec-
       imal  escape  sequences  are  treated  literally  when  used in regular
       expression constants.  Thus, /a\52b/ is equivalent to /a\*b/.

PATTERNS AND ACTIONS
       AWK is a line-oriented language.  The pattern comes first, and then the
       action.  Action statements are enclosed in { and }.  Either the pattern
       may be missing, or the action may be missing, but, of course, not both.
       If  the  pattern  is  missing,  the action is executed for every single
       record of input.  A missing action is equivalent to

              { print }

       which prints the entire record.

       Comments begin with the “#” character, and continue until  the  end  of
       the line.  Blank lines may be used to separate statements.  Normally, a
       statement ends with a newline, however, this is not the case for  lines
       ending  in  a “,”, {, ?, :, &&, or ||.  Lines ending in do or else also
       have their statements automatically continued on  the  following  line.
       In  other  cases,  a  line can be continued by ending it with a “\”, in
       which case the newline will be ignored.

       Multiple statements may be put on one line by separating  them  with  a
       “;”.   This  applies to both the statements within the action part of a
       pattern-action pair (the usual case), and to the pattern-action  state-
       ments themselves.

   Patterns
       AWK patterns may be one of the following:

              BEGIN
              END
              /regular expression/
              relational expression
              pattern && pattern
              pattern || pattern
              pattern ? pattern : pattern
              (pattern)
              ! pattern
              pattern1, pattern2

       BEGIN  and  END  are two special kinds of patterns which are not tested
       against the input.  The action parts of all BEGIN patterns  are  merged
       as  if  all  the  statements  had been written in a single BEGIN block.
       They are executed before any of the input is read.  Similarly, all  the
       END blocks are merged, and executed when all the input is exhausted (or
       when an exit statement is executed).  BEGIN and END patterns cannot  be
       combined  with  other  patterns  in pattern expressions.  BEGIN and END
       patterns cannot have missing action parts.

       For /regular expression/ patterns, the associated statement is executed
       for  each  input  record  that matches the regular expression.  Regular
       expressions are the same as  those  in  egrep(1),  and  are  summarized
       below.

       A  relational  expression may use any of the operators defined below in
       the section on actions.  These generally test  whether  certain  fields
       match certain regular expressions.

       The  &&,  ||, and !  operators are logical AND, logical OR, and logical
       NOT, respectively, as in C.  They do short-circuit evaluation, also  as
       in  C,  and  are used for combining more primitive pattern expressions.
       As in most languages, parentheses may be used to change  the  order  of
       evaluation.

       The  ?:  operator is like the same operator in C.  If the first pattern
       is true then the pattern used for testing is the second pattern, other-
       wise  it  is  the  third.  Only one of the second and third patterns is
       evaluated.

       The pattern1, pattern2 form of an expression is called a range pattern.
       It  matches  all input records starting with a record that matches pat-
       tern1, and continuing until a record that matches pattern2,  inclusive.
       It does not combine with any other sort of pattern expression.

   Regular Expressions
       Regular  expressions  are  the  extended kind found in egrep.  They are
       composed of characters as follows:

       c          matches the non-metacharacter c.

       \c         matches the literal character c.

       .          matches any character including newline.

       ^          matches the beginning of a string.

       $          matches the end of a string.

[abc...]   character list, matches any of the characters abc....

       [^abc...]  negated character list, matches any character except abc....

       r1|r2      alternation: matches either r1 or r2.

       r1r2       concatenation: matches r1, and then r2.

       r+         matches one or more r’s.

       r*         matches zero or more r’s.

       r?         matches zero or one r’s.

       (r)        grouping: matches r.

       r{n}
       r{n,}
       r{n,m}     One  or two numbers inside braces denote an interval expres-
                  sion.  If there is one number in the braces,  the  preceding
                  regular  expression r is repeated n times.  If there are two
                  numbers separated by a comma, r is repeated n  to  m  times.
                  If  there  is  one  number  followed  by  a comma, then r is
                  repeated at least n times.
                  Interval expressions are only available if either --posix or
                  --re-interval is specified on the command line.

       \y         matches  the empty string at either the beginning or the end
                  of a word.

       \B         matches the empty string within a word.

       \<         matches the empty string at the beginning of a word.

       \>         matches the empty string at the end of a word.

       \w         matches any word-constituent character  (letter,  digit,  or
                  underscore).

       \W         matches any character that is not word-constituent.

       \‘         matches  the  empty  string  at  the  beginning  of a buffer
                  (string).

       \’         matches the empty string at the end of a buffer.

       The escape sequences that are valid in string constants (see below) are
       also valid in regular expressions.

       Character  classes  are  a feature introduced in the POSIX standard.  A
       character class is a special notation for describing lists  of  charac-
       ters  that  have  a specific attribute, but where the actual characters
       themselves can vary from country to country and/or from  character  set
       to  character  set.   For  example, the notion of what is an alphabetic
       character differs in the USA and in France.

       A character class is only valid in  a  regular  expression  inside  the
       brackets  of a character list.  Character classes consist of [:, a key-
       word denoting the class, and :].  The character classes defined by  the
       POSIX standard are:

       [:alnum:]  Alphanumeric characters.

       [:alpha:]  Alphabetic characters.

       [:blank:]  Space or tab characters.

       [:cntrl:]  Control characters.

       [:digit:]  Numeric characters.

       [:graph:]  Characters that are both printable and visible.  (A space is
                  printable, but not visible, while an a is both.)

       [:lower:]  Lower-case alphabetic characters.

       [:print:]  Printable characters (characters that are not control  char-
                  acters.)

       [:punct:]  Punctuation characters (characters that are not letter, dig-
                  its, control characters, or space characters).

       [:space:]  Space characters (such as space, tab, and formfeed, to  name
                  a few).

       [:upper:]  Upper-case alphabetic characters.

       [:xdigit:] Characters that are hexadecimal digits.

       For  example,  before the POSIX standard, to match alphanumeric charac-
       ters, you would have had to write /[A-Za-z0-9]/.  If your character set
       had  other  alphabetic characters in it, this would not match them, and
       if your character set collated differently from ASCII, this  might  not
       even match the ASCII alphanumeric characters.  With the POSIX character
       classes, you can write /[[:alnum:]]/, and this matches  the  alphabetic
       and numeric characters in your character set, no matter what it is.

       Two  additional special sequences can appear in character lists.  These
       apply to non-ASCII  character  sets,  which  can  have  single  symbols
       (called  collating  elements)  that  are represented with more than one
       character, as well as several characters that are equivalent  for  col-
       lating,  or  sorting,  purposes.   (E.g.,  in French, a plain “e” and a
       grave-accented “`” are equivalent.)

       Collating Symbols
              A  collating  symbol  is  a  multi-character  collating  element
              enclosed  in [.  and .].  For example, if ch is a collating ele-
              ment, then [[.ch.]]  is a regular expression that  matches  this
              collating  element,  while  [ch]  is  a  regular expression that
              matches either c or h.

       Equivalence Classes
              An equivalence class is a locale-specific name  for  a  list  of
              characters  that are equivalent.  The name is enclosed in [= and
              =].  For example, the name e might be used to represent  all  of
...



       · The and(), asort(), asorti(), bindtextdomain(), compl(), dcgettext(),
         dcngettext(), gensub(), lshift(),  mktime(),  or(),  rshift(),  strf-
         time(), strtonum(), systime() and xor() functions.

       · Localizable strings.

       · Adding  new built-in functions dynamically with the extension() func-
         tion.

       The AWK book does not define the return value of the close()  function.
       Gawk’s  close()  returns  the  value from fclose(3), or pclose(3), when
       closing an output file or pipe, respectively.  It returns the process’s
       exit  status when closing an input pipe.  The return value is -1 if the
       named file, pipe or co-process was not opened with a redirection.

       When gawk is invoked with the --traditional option, if the fs  argument
       to  the  -F  option  is “t”, then FS is set to the tab character.  Note
       that typing gawk -F\t ...  simply causes the shell to  quote  the  “t,”
       and  does  not pass “\t” to the -F option.  Since this is a rather ugly
       special case, it is not the default behavior.  This behavior also  does
       not occur if --posix has been specified.  To really get a tab character
       as the field separator, it is best to use single  quotes:  gawk  -F’\t’
       ....

       If  gawk is configured with the --enable-switch option to the configure
       command, then it accepts an additional control-flow statement:
              switch (expression) {
              case value|regex : statement
              ...
              [ default: statement ]
              }

       If gawk is configured with the --disable-directories-fatal option, then
       it  will  silently  skip directories named on the command line.  Other-
       wise, it will do so only if invoked with the --traditional option.

ENVIRONMENT VARIABLES
       The AWKPATH environment variable can be  used  to  provide  a  list  of
       directories  that gawk searches when looking for files named via the -f
       and --file options.

       If POSIXLY_CORRECT exists in the environment, then gawk behaves exactly
       as  if  --posix  had been specified on the command line.  If --lint has
       been specified, gawk issues a warning message to this effect.

SEE ALSO
       egrep(1), getpid(2),  getppid(2),  getpgrp(2),  getuid(2),  geteuid(2),
       getgid(2), getegid(2), getgroups(2)

       The  AWK Programming Language, Alfred V. Aho, Brian W. Kernighan, Peter
       J. Weinberger, Addison-Wesley, 1988.  ISBN 0-201-07981-X.

       GAWK: Effective AWK Programming, Edition 3.0,  published  by  the  Free
       Software  Foundation,  2001.   The  current version of this document is
       available online at http://www.gnu.org/software/gawk/manual.

BUGS
       The -F option is not necessary given the command line variable  assign-
       ment feature; it remains only for backwards compatibility.

       Syntactically  invalid  single  character programs tend to overflow the
       parse stack, generating a rather unhelpful message.  Such programs  are
       surprisingly  difficult to diagnose in the completely general case, and
       the effort to do so really is not worth it.

AUTHORS
       The original version of UNIX awk was designed and implemented by Alfred
       Aho, Peter Weinberger, and Brian Kernighan of Bell Laboratories.  Brian
       Kernighan continues to maintain and enhance it.

       Paul Rubin and Jay Fenlason, of the  Free  Software  Foundation,  wrote
       gawk,  to be compatible with the original version of awk distributed in
       Seventh Edition UNIX.  John Woods contributed a number  of  bug  fixes.
       David  Trueman,  with contributions from Arnold Robbins, made gawk com-
       patible with the new version of UNIX awk.  Arnold Robbins is  the  cur-
       rent maintainer.

       The  initial  DOS  port  was  done  by Conrad Kwok and Scott Garfinkle.
       Scott Deifik is the current DOS maintainer.  Pat Rankin did the port to
       VMS,  and  Michal Jaegermann did the port to the Atari ST.  The port to
       OS/2 was done by Kai Uwe  Rommel,  with  contributions  and  help  from
       Darrel  Hankerson.   Andreas Buening now maintains the OS/2 port.  Fred
       Fish supplied support for the Amiga, and Martin Brown provided the BeOS
       port.   Stephen  Davies  provided the original Tandem port, and Matthew
       Woehlke provided changes for Tandem’s  POSIX-compliant  systems.   Ralf
       Wildenhues now maintains that port.

       See  the  README  file in the gawk distribution for current information
       about maintainers and which ports are currently supported.

VERSION INFORMATION
       This man page documents gawk, version 3.1.7.

BUG REPORTS
       If you find a  bug  in  gawk,  please  send  electronic  mail  to  bug-
       gawk@gnu.org.   Please  include your operating system and its revision,
       the version of gawk (from gawk --version), what C compiler you used  to
       compile  it,  and a test program and data that are as small as possible
       for reproducing the problem.

       Before sending a bug report, please do the  following  things.   First,
       verify  that  you  have the latest version of gawk.  Many bugs (usually
       subtle ones) are fixed at each release, and if yours is  out  of  date,
       the  problem  may already have been solved.  Second, please see if set-
       ting the environment variable  LC_ALL  to  LC_ALL=C  causes  things  to
       behave  as  you  expect. If so, it’s a locale issue, and may or may not
       really be a bug.  Finally, please read this man page and the  reference
       manual  carefully  to  be  sure that what you think is a bug really is,
       instead of just a quirk in the language.

       Whatever you do, do NOT post a bug report in comp.lang.awk.  While  the
       gawk  developers  occasionally read this newsgroup, posting bug reports
       there is an unreliable way to report bugs.   Instead,  please  use  the
       electronic mail addresses given above.

       If you’re using a GNU/Linux system or BSD-based system, you may wish to
       submit a bug report to the vendor of your distribution.   That’s  fine,
       but  please  send  a  copy to the official email address as well, since
       there’s no guarantee that the bug will be forwarded to the  gawk  main-
       tainer.

ACKNOWLEDGEMENTS
       Brian  Kernighan of Bell Laboratories provided valuable assistance dur-
       ing testing and debugging.  We thank him.

COPYING PERMISSIONS
       Copyright © 1989, 1991, 1992, 1993, 1994, 1995, 1996, 1997, 1998, 1999,
       2001, 2002, 2003, 2004, 2005, 2007, 2009 Free Software Foundation, Inc.

       Permission is granted to make and distribute verbatim  copies  of  this
       manual  page  provided  the copyright notice and this permission notice
       are preserved on all copies.

       Permission is granted to copy and distribute modified versions of  this
       manual  page  under  the conditions for verbatim copying, provided that
       the entire resulting derived work is distributed under the terms  of  a
       permission notice identical to this one.

       Permission  is granted to copy and distribute translations of this man-
       ual page into another language, under the above conditions for modified
       versions,  except that this permission notice may be stated in a trans-
       lation approved by the Foundation.



Free Software Foundation          Jul 10 2009                          GAWK(1)
ls /usr/share/dict/words
vi /usr/share/dict/words
grep nyu /usr/share/dict/words
grep 'nyu' /usr/share/dict/words
grep 'ny*u' /usr/share/dict/words
grep 'ny\{1\}u' /usr/share/dict/words
grep 'o\{3,\}' /usr/share/dict/words
grep 'u\{3,\}' /usr/share/dict/words
grep 'e\{3,\}' /usr/share/dict/words
grep 'e\{3,\}'
grep '^\(.\).*\1$' /usr/share/dict/words
grep '^\(.\).*\1.*\1$' /usr/share/dict/words
grep '^\(.\).*\1\{2\}.*\1$' /usr/share/dict/words
grep '^\(.\)\(.\).\2\1$' /usr/share/dict/words
grep '^\(.\)\(.\)\(.\)\3\2\1$' /usr/share/dict/words
grep '^\(.\)\(.\)\(.\)\4\2\1$' /usr/share/dict/words
grep '^\(.\)\(.\).\2\1$' /usr/share/dict/words | wc -l
grep '^\(.\)\(.\).\2\1$' /usr/share/dict/words
grep '^\(.\)\(.\).\2\1$' /usr/share/dict/words | grep '^[a-z]*$'
grep '^\([a-z]\)\([a-z]\).\2\1$' /usr/share/dict/words
vi stuff
cat stuff
cat stuff | sed d
cat stuff | sed 2d
cat stuff | sed '/pt/d'
cat stuff | sed '/[po]/d'
cat stuff | sed '/pt/d'
cat stuff | sed '/[po]/d'
cat stuff
cat stuff | sed '3,/[po]/d'
cat stuff | sed '3,/[po]/!d'
cat stuff | sed 'p'
cat stuff | sed -n 'p'
cat stuff | sed -n '3'
cat stuff | sed -n '3p'
cat stuff | sed -n '2,3p'
cat stuff | sed -n '2,/a/p'
cat stuff | sed -n '2,/x/p'
cat stuff | sed -n '2,$p'
cat stuff
cat stuff | sed 's/o/O/'
cat stuff | sed -n 's/o/O/'
cat stuff | sed -n 's/o/O/p'
cat stuff | sed 's/p/P/'
cat stuff | sed 's/p/P/2'
cat stuff | sed 's/p/P/g'
cat stuff | sed '2,3s/p/P/g'
cat stuff | sed '/.\{6,\}/s/p/P/g'
cat stuff | sed '/.\{6,\}/,/n/s/p/P/g'
cat stuff | sed '/.\{6,\}/,2s/p/P/g'
cat stuff | sed '/.\{6,\}/s/p/P/g'
cat stuff | sed '/.\{6,\}/s/o.*p/X/g'
cat stuff | sed 's/o.*p/X/g'
vi stuff
cat stuff | sed 's/o.*p/X/g'
cat stuff
cat stuff | sed 's/o[^ ]*p/X/g'
cat stuff | sed 's/[aeiou]/-&-/g'
cat stuff | sed 's/\(.\)./\1/g'
cat stuff | sed 's/\(.\)./\1/'
cat stuff
cat stuff | sed 's/\(.\)./\1\1\1\1/'
cat stuff | sed 's/\(.\)\(.\)/\1\2\2\1/'
cat stuff | sed 's/\(.\)\(.\)//'
vi stuff
cat stuff
cat stuff | sed 's/^\([^:]*\):\([^:]*\)$/\2:\1/'
vi stuff
cat stuff | sed 's/^\([^:]*\):\([^:]*\)$/\2:\1/'
cat stuff | sed -n 's/^\([^:]*\):\([^:]*\)$/\2:\1/p'

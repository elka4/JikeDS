Part I: Sed
1. The internet movie database (www.imdb.com) contains a list of the top 250 movies of all time in rank order. In this question, we will write a single sed script to convert the raw HTML into a simpler ASCII formatted "pipe"­delimited text file (to see what the raw HTML looks like, go here and "View Source" in your web browser). The ASCII file we create will have the following format:
Rank|Title|Year|Rating|Votes
For example:
1|The Shawshank Redemption|1994|9.2|1536989
The following will be used to generate the data using your sed script:
GET http://www.imdb.com/chart/top | sed ­n ­f part1.sed |
tr '\n' '|' | sed 's/||/\n/g'
Or, you can use a pre­existing version in /home/unixtool/data/top250.html and run: sed ­n ­f part1.sed < /home/unixtool/data/top250.html |
tr '\n' '|' | sed 's/||/\n/g'
Your sed script is generating intermediate output that is piped into tr and sed, so the script you write will produce output that looks like this:
  1
  The Shawshank Redemption
  1994
  9.2
  1536989
2
...
While the HTML may look daunting, our task mostly involves removing unnecessary information and doing some simple transformations. While there are many ways to solve the problem, below is an outline of the steps that need to be performed by your sed script for one proposed solution (please feel free to create your own!). Remember that this must all be covered in a sed script run with ­n.

a. Deletealllinesthroughtheonethatbeginsthetable(containschart­top250)
1,/chart-top250/d
b. Delete all the lines from the end of the table (</table>) to the end of the file
/<\/table>/,$d
c. Remove lines that contain a span element with a data­value field
/span.*data-value/d
d. Remove all commas (we don't want numbers to contian them)
s/,//g
e. Forlinesthatbeginwithtitle=,printthetextusedinthelinktext(moviename)
/^title=/s/.*>\([^<>]*\)<.*>$/\1/p
f. For lines that contain a span tag with class secondaryInfo, print extract and print the year.
/span.*secondaryInfo/s/.*\((\(.*\))\).*$/\2/p
g. For lines that contain only a number (and dot), remove the spaces and dot to extract the rank.
/^[[:space:]]*[[:digit:]]*\.$/s/\([[:space:]]*\)\([[:digit:]]*\)\.$/\2/p
h. For lines that contain a <strong> tag, extract out the rating and votes from the title text. Since votes will be the last field, make sure a blank line is printed after the votes which will be used later in the pipeline to separate out records.
/<strong.*$/s/^.*[^[:digit:]]\([[:digit:]]*\.[[:digit:]]*\).*[[:space:]]\([[:digit:]]*\)[[:space:]].*$/\1\n\2\n/p

##############################################################################
Part II: Grep
Write a single command or a pipeline of commands for each of the following questions. You may use everything you learned so far, except for sed and awk.
2. Listthetitlesofallthe1930'smoviesinthetop250.
cat part1.dat | grep '|193[0-9]|' | cut -d '|' -f 2

3. Print the number of movies that use the same word twice in the title. A word is any sequence of non­space characters.
cat part1.dat | cut -d '|' -f 2 | grep -i '\b\(\w\+\)\b.*\b\1\b' | wc -l

4. Print the rank of each movie that contains a non­alphabetic character in its title (excluding spaces). For example, "Sunset Blvd.", "81⁄2", and "Se7en" should be included but "Forrest Gump" should not. Sample output:
3
5
6
...
cat part1.dat | cut -d '|' -f 1,2 | grep '|\(.*\)\([^[:alpha:][:space:]]\)\+' | cut -d '|' -f 1

5. Printthenumberofmovieswithlessthan50,000votes.
cat part1.dat | cut -d '|' -f 5 | grep '^[1-4][0-9]\{0,4\}$'  | wc -l

Part III: Awk
Write awk scripts to do the following tasks with the movie database. Use a single awk
script per question, and no other tools.
6. Printthetotalnumberofvotesacrossallmoves.
awk 'BEGIN { FS = "|"} { total += $5 } END { print total }' part1.dat

7. Printtheyearthathadthegreatestnumberoftotalvotes.
awk 'BEGIN { FS = "|" } { votes[$3] += $5}
END { for (year in votes)
{ if (votes[year] > max) { max = votes[year]; result = year } }
print result}' part1.dat

8. Print the decade that had the greatest number of top250 movies.
awk 'BEGIN { FS = "|" } { decades[substr($3, 0, 3)]++ }
END { for (year in decades)
{ if (decades[year] > max) {max = decades[year]; result = year } }
print result"0s" }' part1.dat

9. Print theaveragenumberofvotesformoviesabovean8.5ratingandtheaverage number of votes for movies below an 8.5 rating on a single line.
awk 'BEGIN { FS = "|" } { if ($4 > 8.5)
{ aboveRating += $5; aboveCount++ } else if ($4 < 8.5) {belowRating += $5; belowCount++ } }
END { printf("%.1f %.1f\n", aboveRating/aboveCount, belowRating/belowCount) }' part1.dat


10. Print the average number of words in each title. A word is any string of non­whitespace characters. The answer should be a floating point number, e.g. 3.4
awk 'BEGIN { FS = "|" }  {split ($2, title, " "); len += length(title)}
END { printf("%.1f\n", len/NR) }' part1.dat

11. Print the most commonly used word in titles besides "The", "the", and "of".
awk 'BEGIN { FS = "|" }  { split ($2, title, " "); for (word in title) {words[title[word]]++} }
END {for (word in words) {
if(word != "The" && word != "the" && word != "of"){
if (mostfreq <words[word]){ mostfreq=words[word]; result = word } } }
print result }' part1.dat

12. Print the movies with the longest and shortest titles on two lines.
awk 'BEGIN { FS = "|"; minLength = 999 }
{ if (length($2) > maxLength) { maxLength = length($2); maxTitle = $2 };
if (length($2) < minLength) { minLength = length($2); minTitle = $2 } }
END { print maxTitle; print minTitle }' part1.dat
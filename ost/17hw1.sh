## Assignment 1 2017

Part I
Using find (and possibly other commands), write commands to do the following from /home/unixtool/data/babies. Do not use shell wildcards with any of the commands (patterns in quotes for the find command are ok). Do not use the .csv files.

1. List all .html files.
find . -type f -name "*.html"

2. Count the number of directories under /home/unixtool/data/babies that are at least writable by
the owner and readable by others.

find . -type d -perm -u+w,o+r | wc -l
find -type d -perm -204 | wc -l

3. List all the 2015 baby names from New York (NY). An example line of output is "Harper"
find 2015 -regex "2015\/NY\/\(F\|M\)\/.*" | cut -d/ -f4
find . -path "./2015/NY/*/*" -printf "%f\n"

4. List all the 2015 female baby names from New York (NY), New Jersey (NJ) and
Connecticut (CT).

find 2015 -regex "2015\/\(NY\|NJ\|CT\)\/\F\/.*" | cut -d/ -f4
find ./2015/NY/F ./2015/NJ/F ./2015/CT/F -type f -printf "%f\n"

5. List all the 2015 baby names from states other than New York.

find 2015 -type f ! -regex "2015\/\(NY\)\/.*" | cut -d/ -f4
find . -path "./2015/*/*/*" -a ! -path "./*/NY/*/*" -printf "%f\n"

6. Count the number of unique baby names from 2015.
find 2015 -regex "2015\/..\/\(F\|M\)\/.*" | cut -d/ -f 4 | sort | uniq | wc -l
find . -path "./2015/*/*/*" -printf "%f\n" | sort |uniq |wc -l
find ./2015 -type f -printf "%f\n" | sort | uniq | wc -w

7. List all the 2005 female baby names that begin with the letter C, without duplicates.
find 2005 -regex "2005\/..\/F\/C.*" | cut -d/ -f 4 | sort | uniq
find . -path "./2005/*/F/C*" -printf "%f\n" | sort | uniq
find ./2005 -type f -a -path "*/F/*" -a -name "C*" -printf "%f\n" | sort | uniq

8. Print the number of babies named with the most popular name in NY in 2015.
find 2015/NY/ -type f -regex "2015\/..\/.*"  -exec cat {} \; | sort -nr | head -1
find . -path "./2015/NY/*/*" -exec cat {} ";" | sort -n | tail -1
find ./2015/NY -type f -printf "%f " -exec cat "{}" ";" | sort -n -k 2 | tail -1 | cut -d ' ' -f2
#####################################################################################################################
Part II
This part deals with the file all_states.csv, which has the top 100 baby names and counts for all states
since 2000 in a single file. Each line contains the following fields:
1. Year
2. State: Two letter abbreviation
3. Gender: M or F
4. Name: Baby name
5. Count: Number of babies born with this name
as well as the file all_national.csv, which contains the same information but at the national level (the format
is the same as above, except the second column [state] is missing).
With these files, answer each of the following:

9. Print the number of unique names nationally (if name exists in both genders, count twice)
cat all_national.csv | cut -d, -f2,3 | sort | uniq | wc -l
cat all_national.csv | cut -d, -f2-3 | sort | uniq |wc -l
cat all_national.csv | cut -d ',' -f2,3 | sort | uniq | wc -w

10. Show the last 5 unique baby names nationally in alphabetical order.
cat all_national.csv | cut -d, -f3 | sort -d | uniq | tail -5
cat all_national.csv | cut -d, -f3 | sort | uniq | tail -5
cat all_national.csv | cut -d ',' -f3 | sort | uniq | tail -5

11. Findthestatethathadthebabythatwasthemostnamed(genderdependent).
cat all_states.csv | sort -t, -n -k5 -r | head -1 | cut -d, -f2
cat all_states.csv | sort -t, -k5 -n | tail -1 | cut -d, -f2
cat all_states.csv | sort -t, -n -k5 -r | head -1 | cut -d, -f2

12. Print the name and gender of baby that was named the greatest number of times in a single year.
cat all_national.csv | sort -t, -nrk4 | head -1 | cut -d, -f2,3
cat all_national.csv | sort -t ',' -n -k4 -r | head -1 | cut -d ',' -f3,2

13. Top 5 national baby names in the most recent year.
cat all_national.csv | sort -t, -nrk1.1,1.4 -nrk4 | head -5 | cut -d, -f3
cat all_national.csv | sort -t, -k1n -k4n | tail -5 | cut -d, -f3
cat all_national.csv | sort -t ',' -n -k1,1 -k4,4 -r | head -5 | cut -d ',' -f3

14. Find all baby names in all_states.csv that are both male and female.
cat all_states.csv | cut -d, -f3,4 | sort | uniq | cut -d, -f2 | sort | uniq -d
cat all_states.csv | cut -d, -f3,4 | sort | uniq | cut -d, -f2 | sort | uniq -d
cat all_states.csv | cut -d ',' -f3,4 | sort -t ',' -k2 | uniq | cut -d ',' -f2 | uniq -d

15. Show number of unique baby names for each state over all years. Output should be one number per state.
cat all_states.csv | cut -d, -f2,4 | sort -u | cut -d, -f1 | sort | uniq -c
cat all_states.csv | cut -d, -f1,2,4 | sort -t, -k2 -k1n | uniq | cut -d, -f2 | uniq -c

16. Find which name was used in the fewest number of states (if there are ties, any of the names will do).
cat all_states.csv | cut -d, -f2,4 | sort -u | cut -d, -f2 | sort | uniq -c | sort -n | head -1 | cut -d" " -f8
cat all_states.csv | cut -d, -f2,4 | sort -t, -k2 | uniq | cut -d, -f2 | uniq -c | sort -t" " -k1n | head -1 | cut -d" " -f8
cat all_states.csv | cut -d, -f2,4 | sort -t, -k2 | uniq | cut -d, -f2 | uniq -c | sort -n -k1 | head -1 |tr -s ' ' | cut -d ' ' -f3
#15hw1

1. Station Name
2. Entrance Latitude
3. Entrance Longitude

Part I
Using find (and possibly other commands), write commands to do the following from /home/unixtool/data/mta. Do not use shell wildcards with the ls command (patterns in quotes for the find command are ok). Do not use any .csv files.
1. Print the total number of stations.
find IRT IND BMT -type f | wc -l
find BMT IRT IND -type f | cut -d/ -f3 | sort | uniq | wc -l

2. Print the number of stations in BMT.
find BMT -type f | wc -l
find BMT -type f | cut -d/ -f3 | sort | uniq | wc -l

3. Print the number of non-BMT stations.
find IRT IND -type f | wc -l
find IRT IND -type f | cut -d/ -f3 | sort | uniq | wc -l

4. Print the station ids that are not readable by group.
find IRT IND BMT -type f ! -perm -g+r | cut -d '/' -f 3
find BMT IRT IND ! -perm -040 -type f | cut -d/ -f3

5. Print the names of all the lines alphabetically (noduplicates).
find IRT IND BMT -type f | cut -d '/' -f 2 | sort | uniq
find BMT IRT IND -mindepth 1 -type d | cut -d/ -f2 | sort -d | uniq

6. Print the name of the division that has the most stations.
find BMT IND IRT -type f | cut -d '/' -f 1 | sort | uniq -c | tr -s ' ' | sort -t ' ' -rnk2,2 | head -1 | cut -d ' ' -f 3
find BMT IRT IND -type f | cut -d/ -f1 | uniq -c | sort -nr | head -1 | cut -c9-

7. Print the station id that has the most entrances (or to impress, the station name).
find IRT IND BMT -type f -exec wc -l {} ';' | sort -t ' ' -nk1,1 | tail -1 | cut -d ' ' -f 2 | xargs cat | head -1 | cut -d ',' -f 1

find BMT IRT IND -type f -exec wc {} -l \; | sort -k1,1 -nr | head -1 | cut -d ' '  -f2 | cut -d/ -f3
find BMT IRT IND -type f -exec wc {} -l \; | sort -k1,1 -nr | head -1 | cut -d ' ' -f2 | xargs cat | cut -d, -f1 | sort | uniq

8. Print the station with northenmost entrance (that will be the one with the highest latitude).
find IRT IND BMT -type f -exec cat {} ';' | sort -t ',' -nk2,2 | tail -1 | cut -d ',' -f 1
Wakefield-241st St,40.903597,-73.85036
find BMT IRT IND -type f -exec cat {} \; | sort -k2,2 -nr | head -1 | cut -d, -f1
East 180th St,40.841453,-73.872892
###################################


Part II
This part deals with the file ridership.csv, which has the information about how many riders use each subway station for a given year, over various years (source). Each line contains the following fields:
1. Borough
2. Station Name
3-8. Ridership in 2009, 2010, 2011, 2012, 2013 and 2014
9. Change: Increase or decrease of ridership between 2013 and 2014
10. Services: Which services are at this station, each separated by : (e.g. N:R)

Manhattan,86 St,18891890,19147021,19425347,19686985,20528208,20735032,206824,4:5:6

With this file, answer each of the following:

9. Print the top 10 stations with the biggest increase in ridership.
cat ridership.csv | sort -t ',' -rnk9,9 | head -10 | cut -d ',' -f 2
cat ridership.csv | sort -t, -nr -k9,9 | head -10 | cut -d, -f2

10. Print the boroughs of the stations that have the lowest 10 ridership in 2014 (don't list any borough twice).
cat ridership.csv | sort -t ',' -nk8,8 | head -10  | sort -u -t ',' -k1,1 | sort -t ',' -nk8,8 | cut -d ',' -f 1
cat ridership.csv | sort -t, -n -k8,8 | head -10 | cut -d, -f1 | sort | uniq

11. Print the names of all the stations that appear twice or more.
cat ridership.csv | sort -t ',' -nk2,2 | cut -d ',' -f 2 | uniq -d （wrong）
cat ridership.csv | cut -d, -f2 | sort | uniq -d

12. Print the borough with most stations.
cat ridership.csv | cut -d ',' -f 1 | sort | uniq -c | tr -s ' ' | sort -t ' ' -rnk2,2 | head -1 | cut -d ' ' -f 3
cat ridership.csv | cut -d, -f1 | sort | uniq -c | sort -nr | head -1 | cut -c9-

13. Print the station with the least overall change of ridership (positive or negative).
cat ridership.csv | cut -d ',' -f 2,9 | tr -d '-' | sort -t ',' -nk2,2 | head -1 | cut -d ',' -f 1
cat ridership.csv | tr '-' ' ' | sort -t, -n -k9,9 | head -1 | cut -d, -f2

14. Print out the number/letters of all subway services that appear in the data (each line should be a single character)
cat ridership.csv | cut -d ',' -f 10 | tr : '\n' | sort | uniq
cat ridership.csv | cut -d, -f10 | tr ':' '\n' | sort | uniq

15. Print which subway service stops in the most stations.
cat ridership.csv | cut -d ',' -f 10 | tr : '\n' | sort | uniq -c | tr -s ' ' | sort -t ' ' -rnk2,2 | head -1 | cut -d ' ' -f 3
cat ridership.csv | cut -d, -f10 | tr ':' '\n' | sort | uniq -c | sort -nr | cut -c9- | head -1

16. Print stations that entered the top 10 highest ridership in 2014, comparing 2009 and 2014 (you can use two commands for this).
comm <(cat ridership.csv | sort -t ',' -nrk3,3 | head -10) <(cat ridership.csv | sort -t ',' -nrk8,8 | head -10) -13 | cut -d ',' -f 2
comm <( cat ridership.csv | sort -t, -k8,8 -nr | head -10 | cut -d, -f2 ) <( cat ridership.csv | sort -t, -k3,3 -nr | head -10 | cut -d, -f2 ) | cut -f1 | tail -1







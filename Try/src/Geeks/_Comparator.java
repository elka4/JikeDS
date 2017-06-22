package Geeks;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by tzh on 1/25/17.
 * Unlike Comparable, Comparator is external to the element type we are comparing.
 * It’s a separate class. We create multiple separate classes (that implement Comparator)
 * to compare by different members.

 Collections class has a second sort() method and it takes Comparator.
 The sort() method invokes the compare() to sort objects.

 To compare movies by Rating, we need to do 3 things :

 Create a class that implements Comparator (and thus the compare()
 method that does the work previously done by compareTo()).

 Make an instance of the Comparator class.

 Call the overloaded sort() method, giving it both the list and the instance
 of the class that implements Comparator.
 */
public class _Comparator {
    // A class 'Movie' that implements Comparable
    class Movie implements Comparable<Movie>
    {
        private double rating;
        private String name;
        private int year;

        // Used to sort movies by year
        public int compareTo(Movie m)
        {
            return this.year - m.year;
        }

        // Constructor
        public Movie(String nm, double rt, int yr)
        {
            this.name = nm;
            this.rating = rt;
            this.year = yr;
        }

        // Getter methods for accessing private data
        public double getRating() { return rating; }
        public String getName()   {  return name; }
        public int getYear()      {  return year;  }
    }

    // Class to compare Movies by ratings
    class RatingCompare implements Comparator<Movie>
    {
        public int compare(Movie m1, Movie m2)
        {
            if (m1.getRating() < m2.getRating()) return -1;
            if (m1.getRating() > m2.getRating()) return 1;
            else return 0;
        }
    }

    // Class to compare Movies by name
    class NameCompare implements Comparator<Movie>
    {
        public int compare(Movie m1, Movie m2)
        {
            return m1.getName().compareTo(m2.getName());
        }
    }

    // Driver class
    @Test

    public void test() {
        ArrayList<Movie> list = new ArrayList<Movie>();
        list.add(new Movie("Force Awakens", 8.3, 2015));
        list.add(new Movie("Star Wars", 8.7, 1977));
        list.add(new Movie("Empire Strikes Back", 8.8, 1980));
        list.add(new Movie("Return of the Jedi", 8.4, 1983));

        // Sort by rating : (1) Create an object of ratingCompare
        //                  (2) Call Collections.sort
        //                  (3) Print Sorted list
        System.out.println("Sorted by rating");
        RatingCompare ratingCompare = new RatingCompare();
        Collections.sort(list, ratingCompare);
        for (Movie movie: list)
            System.out.println(movie.getRating() + " " +
                    movie.getName() + " " +
                    movie.getYear());


        // Call overloaded sort method with RatingCompare
        // (Same three steps as above)
        System.out.println("\nSorted by name");
        NameCompare nameCompare = new NameCompare();
        Collections.sort(list, nameCompare);
        for (Movie movie: list)
            System.out.println(movie.getName() + " " +
                    movie.getRating() + " " +
                    movie.getYear());

        // Uses Comparable to sort by year
        System.out.println("\nSorted by year");
        Collections.sort(list);
        for (Movie movie: list)
            System.out.println(movie.getYear() + " " +
                    movie.getRating() + " " +
                    movie.getName()+" ");
    }
}

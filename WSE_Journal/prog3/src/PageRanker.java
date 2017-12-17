//Tianhui Zhu tz406@nyu.edu



import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.cli.*;

@SuppressWarnings("all")
public class PageRanker {

    private File[] files;
    private int numberOfPages;
    private static double epsilonFactor = 0.01;
    private double epsilon;
    private double F;
    private double[][] weight;
    //Since we want to get a result in alphabetical order
    // or we can use HashMap for a better performance
    private TreeMap<String, Page> pages;

    //only two parameters for PageRanker constructor
    private static String path;
    private static double f;

    public PageRanker(String path, double f) {
        this.files = FileSystems.getDefault().getPath(path).toFile().listFiles();
        this.numberOfPages = files.length;
        this.epsilon = epsilonFactor/numberOfPages;
        this.weight = new double[numberOfPages][numberOfPages];
        this.pages = new TreeMap<String, Page>();

        this.F = f;
    }

    //this apache commons-cli is soooo convinient for command line input options!
    // Much easier than my prog1 implementation.
    private static void init(String[] args){
        Options options = new Options();
        options.addOption("docs", "docs", true, "collection of document");
        options.addOption("f", "f", true, "F");
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        path = cmd.getOptionValue("docs");
        f = Double.parseDouble(cmd.getOptionValue("f"));
    }


    private void calculate() {
        //parse the page
        try{
            for(File file : files) {
                pages.put(file.getName(), new Page(file));
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        double sum = 0.0;
        for(Page page : pages.values()) {
            sum += page.getBase();
        }

        //calculate
        for(Page page : pages.values()) {
            double normalizedSum = page.getBase()/sum;
            page.setScore(normalizedSum);
            page.setBase(normalizedSum);
        }
        double qSum = 0.0;
        for(Page page : pages.values()) {
            int pId = page.getId();

            boolean hasOutLinks = page.getOutLinks().size() > 0;

            if(!hasOutLinks) {
                for(Page qPage : pages.values()) {
                    int qId = qPage.getId();
                    weight[qId][pId] = 1.0/numberOfPages;
                }
            } else {
                Set<String> outLinks = page.getOutLinks().keySet();

                for(String outLink : outLinks) {
                    Page qPage = pages.get(outLink);
                    int qId = qPage.getId();
                    weight[qId][pId] = calculatePageWeight(page, qPage);
                }

                qSum = 0.0;

                for(int i = 0; i < weight.length; i++) {
                    qSum += weight[i][pId];
                }

                for(String outLink : outLinks) {
                    Page qPage = pages.get(outLink);
                    int qId = qPage.getId();
                    weight[qId][pId] = weight[qId][pId]/qSum;
                }
            }
        }
    }

    // calculate weight for a page
    private double calculatePageWeight(Page p, Page q) {
        double score = 0.0;
        for(String link : p.getOutLinks().keySet()) {
            if(!link.equals(q.getPath())) {
                continue;
            }
            score += p.getOutLinks().get(link) == null ? 0: p.getOutLinks().get(link);
        }
        return score;
    }


    private void run() {
        boolean notNewScore = true;
        double normalizedScore = 0.0;
        while(notNewScore) {
            notNewScore = false;
            for(Page page : pages.values()) {
                normalizedScore = 0.0;
                for(Page qPage : pages.values()) {
                    normalizedScore += qPage.getScore() * weight[page.getId()][qPage.getId()];
                }
                double newScore = ((1 - F) * page.getBase()) + (F * normalizedScore);
                page.setNewScore(newScore);
                if(epsilon < Math.abs(page.getNewScore() - page.getScore())) {
                    notNewScore = true;
                }
            }

            for(Page page : pages.values()) {
                page.setScore(page.getNewScore());
            }
        }
    }


    private void print(){
        for(Page page : pages.values()) {
            System.out.println(page.getPath() + ":\t" + page.getScore());
        }
    }

    public static void main(String[] args) throws IOException{

        init(args);

        PageRanker pr = new PageRanker(path, f);

        pr.calculate();

        pr.run();

        pr.print();

    }

}

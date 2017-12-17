import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.HelpFormatter;

public class StackOverflowExample {
    public static final String JOB1 = "job1";
    public static final Option job1 =
            OptionBuilder.hasArg(false)
                    .isRequired(false)
                    .withDescription("description of job1")
                    .create(JOB1);

    public static final String JOB2 = "job2";
    public static final Option job2 =
            OptionBuilder.hasArg(false)
                    .isRequired(false)
                    .withDescription("description of job2")
                    .create(JOB2);

    public static final String JOB3 = "job3";
    public static final Option job3 =
            OptionBuilder.hasArg(false)
                    .isRequired(false)
                    .withDescription("description of job3")
                    .create(JOB3);

    public static final String MY_C = "C";
    public static final Option my_c =
            OptionBuilder.hasArg(true)
                    .withArgName("count")
                    .isRequired(false)
                    .withDescription("description of C")
                    .create(MY_C);

    public static final String MY_W = "W";
    public static final Option my_w =
            OptionBuilder.hasArg(true)
                    .withArgName("width")
                    .isRequired(false)
                    .withDescription("description of W")
                    .create(MY_W);


    public static void main(String[] args) {
        Options options = new Options();

        OptionGroup optgrp = new OptionGroup();
        optgrp.addOption(job1);
        optgrp.addOption(job2);
        optgrp.addOption(job3);

        options.addOptionGroup(optgrp);
        options.addOption(my_c);
        options.addOption(my_w);

        try {
            CommandLineParser parser = new GnuParser();
            CommandLine cmdline = parser.parse(options, args);

            if (((cmdline.hasOption(MY_C)) || (cmdline.hasOption(MY_W))) &&
                    (! cmdline.hasOption(JOB1))) {
                HelpFormatter help = new HelpFormatter();
                help.printHelp("cmdname", options);
                System.exit(-1);
            }

            System.out.println("OK");
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
    }
}

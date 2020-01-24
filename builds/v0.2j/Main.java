package manguitos.pleasureSaver;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {

    static final String version = "v0.2j";
    static Logger logger = Logger.getLogger("psLog-" + version);

    public static void main(String[] args) {

        ArrayList<String> songNamesMP3, songNames = new ArrayList<String>();
        Scanner scan = new Scanner(System.in);
        String inStr, hz, bitrate;
        int convertID = 0;
        hz = "48000";
        bitrate = "320k";

        checkNeededFolders();
        loggerInitialize();
        logger.info("Starting Logger. pleasureSaver " + version);


        System.out.println();
        System.out.println("[PleasureSaver " + version + "]\n");

        //Main download loop
        while(true)
        {
            System.out.print("Link or Q to exit: ");
            inStr = scan.nextLine();

            if(inStr.equals("q") || inStr.equals("Q"))
                break;

            System.out.print("Starting task..");
            launcher(new String[]{"programs\\youtube-dl.exe",
                    "--no-call-home",
                    "-o\"psDownloads\\%(title)s.%(ext)s\"",
                    "-f bestaudio",
                    "--no-playlist",
                    inStr});

            System.out.print(".   DONE");
            System.out.println();
        }

        //Converter part

        //Reads all files from the directory and saves their name on the arraylist songNames
        File folder = new File("psDownloads\\");
        File[] listOfFiles = folder.listFiles();
        for (File listOfFile : Objects.requireNonNull(listOfFiles))
            if (listOfFile.isFile())
                songNames.add(listOfFile.getName());

        //A copy of songNames but with the termination changed to .mp3. Used on calling FFmpeg
        songNamesMP3 = changeFileTerm(songNames);

        System.out.println();
        System.out.println("[Starting conversion to MP3 filetype using FFmpeg]");

        //Main convert loop
        for(int i = 0; i < songNames.size(); i++)
        {
            System.out.print("Converting " + (i+1) + " of " + songNames.size() + "..");
            String dl = "\"psDownloads\\" + songNames.get(i) + "\"";
            String out = "\"psConverted\\" + songNamesMP3.get(i) + "\"";

            launcher(new String[]{"programs\\ffmpeg.exe", "-i", dl, "-n", "-vn", "-ar", hz, "-ac", "2", "-b:a", bitrate, out});

            System.out.print(".   DONE");
            System.out.println();
        }

        System.out.println("Conversion DONE\n");
        System.out.println("[*See you soon*]");
        System.out.println("Made with love by GoodBoi\n\n");
    }

    //Launch a process and redirects it's output to the console
    public static void launcher(String[] data)
    {
        ProcessBuilder builder = new ProcessBuilder(data);
        builder.redirectErrorStream(true);
        try {
            Process process = builder.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while( (line = br.readLine()) != null)
            {
                logger.info(line);
            }

            process.waitFor();
        } catch (IOException | InterruptedException e) {
            System.err.println("Something went wrong :(\n" + e.getMessage());
            e.printStackTrace();
        }
    }

    public static ArrayList<String> changeFileTerm(ArrayList<String> data)
    {
        ArrayList<String> sol = new ArrayList<String>();
        String str, newTerm = ".mp3";
        int index;

        //If the file has no termination that should be an error, so Im ignoring that iteration
        for (String datum : data) {
            str = new String(datum);
            index = str.lastIndexOf(".");

            if (index == -1)
                continue;

            str = str.substring(0, index);
            str += newTerm;
            sol.add(str);
        }

        return sol;
    }

    //Checks if there exists the folders psConverted and psDownloads. If any of them doesn't, it proceeds to create them
    public static void checkNeededFolders()
    {
        boolean psConverted, psDownloads, logs;
        psConverted = psDownloads = logs = false;
        File createFolder;

        File dir = new File("./");
        File[] listOfFiles = dir.listFiles();
        for (File listOfFile : Objects.requireNonNull(listOfFiles))
            if (listOfFile.isDirectory()) {
                if (listOfFile.getName().equals("psDownloads"))
                    psDownloads = true;
                else if (listOfFile.getName().equals("psConverted"))
                    psConverted = true;
                else if (listOfFile.getName().equals("logs"))
                    logs = true;
            }

        if(!psConverted)
        {
            createFolder = new File("./psConverted");
            createFolder.mkdir();
        }

        if(!psDownloads)
        {
            createFolder = new File("./psDownloads");
            createFolder.mkdir();
        }

        if(!logs)
        {
            createFolder = new File("./logs");
            createFolder.mkdir();
        }
    }

    public static void loggerInitialize()
    {
        FileHandler fh;

        try {

            String timeStamp = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(Calendar.getInstance().getTime());

            // This block configure the logger with handler and formatter
            fh = new FileHandler("logs\\psLog " + timeStamp + ".log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            // the following statement is used to log any messages
            logger.setUseParentHandlers(false);

        } catch (IOException e) {
            System.err.println("Something went wrong :(\n" + e.getMessage());
            e.printStackTrace();
        }

    }
}

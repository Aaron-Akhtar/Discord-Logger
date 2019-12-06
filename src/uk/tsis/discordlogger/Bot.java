package uk.tsis.discordlogger;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import uk.tsis.discordlogger.events.Delete;
import uk.tsis.discordlogger.events.Edit;
import uk.tsis.discordlogger.events.Message;

import javax.security.auth.login.LoginException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 *
 * @author The Secret Intelligence Squadron | Aaron Akhtar
 *                  Follow us via our youtube -> http://youtube.tsis.uk
 *                  Our domain -> tsis.uk (Old: thesecretintelligence.org)
 * */

public class Bot {

    private static final String VERSION = "1.0.0";
    private static final Log logger = new Log();
    private static JDA client;
    private static String token;

    public static void main(String[] args) throws Exception{

        System.out.println("This program was developed by The Secret Intelligence:");
        System.out.println("http://discord.tsis.uk");
        System.out.println("http://youtube.tsis.uk");
        Thread.sleep(1500);
        System.out.println(" ");
        System.out.println("Checking for new updates...");
        checkBins();
        token = args[0];
        try{
            client = new JDABuilder(AccountType.CLIENT).setToken(token).build().awaitReady();
        }catch (Exception e){
            System.out.println("Could not login...");
            System.exit(0);
        }

        while(client.getStatus() != JDA.Status.CONNECTED){

        }

        System.out.println("Logger has started successfully, we will fetch information on the guilds you are in now, then we will enable the other features, please wait...");
        Thread.sleep(1200);
        for (Guild guild : client.getGuilds()){
            System.out.println("[Guild Info: "+guild.getName()+"] | (Logo URL: "+guild.getIconUrl()+") - (Region: "+guild.getRegion()+") {Total Members: "+guild.getMembers().size()+"}");
            Thread.sleep(500);
        }
        System.out.println("Starting Now...");
        Thread.sleep(1500);
        client.addEventListener(new Message());
        client.addEventListener(new Delete());
        client.addEventListener(new Edit());
        logger.start();
    }

    private static void checkBins(){
        try {
            URL url = new URL("http://bins.tsis.uk/discordlogger/release.txt");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            while(scanner.hasNextLine()){
                if (scanner.nextLine().equalsIgnoreCase(VERSION)){
                    System.out.println("Currently Running Latest Version...");
                    Thread.sleep(1500);
                }else{
                    System.out.println("Currently Running Outdated Version, Update Via - http://bin.tsis.uk/discordlogger/latest.jar");
                    Thread.sleep(1500);
                }
            }
        }catch (Exception e){
            System.out.println("Error Connecting to BINS server...");
        }
    }

}

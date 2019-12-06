package uk.tsis.discordlogger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import uk.tsis.discordlogger.events.Delete;
import uk.tsis.discordlogger.events.Edit;
import uk.tsis.discordlogger.events.Message;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Class will be used to run a thread that logs all information logged into a flat-file type of system. *
 * */
public class Log extends Thread{

    private final String datePattern = "MM-dd-yyyy";
    private final String timePattern = "HH-mm-ss";

    @Override
    public void run(){
        System.out.println("[CONSOLE] Logger Started, I will save all logs every 5 minutes....");
        while(true){
            try{
            Thread.sleep(1000*300);}catch (InterruptedException e){}
            String date = new SimpleDateFormat(datePattern).format(new Date());
            String time = new SimpleDateFormat(timePattern).format(new Date());
            final Gson gson = new GsonBuilder().setPrettyPrinting().create();
            final File dir = new File("./discordlogger/");
            if (!dir.isDirectory()){
                dir.mkdir();
            }
            final File dirDate = new File(dir + "/"+ date + "/");
            if (!dirDate.isDirectory()){
                dirDate.mkdir();
            }
            final File log = new File(dirDate +"/" +time+ ".txt");
            try {
                if (log.createNewFile()) {
                    PrintWriter writer = new PrintWriter(log);
                    writer.write(
                            "Message Logs -> \n" +
                                    "    Private Logs:\n" +
                                    "       " + gson.toJson(Message.privateMessages) + "\n" +
                                    "    \n"+
                                    "     \n"+
                                    "    Group Logs:\n" +
                                    "       " + gson.toJson(Message.groupMessages) + "\n"+
                                    "    \n"+
                                    "     \n"+
                                    "    Guild Logs:\n"+
                                    "       " + gson.toJson(Message.guildMessages) + "\n" +
                                    "    \n"+
                                    "     \n"+
                                    "    \n"+
                                    "     \n"+
                                    "Edit Logs -> \n"+
                                    "    Private Logs:\n" +
                                    "       " + gson.toJson(Edit.privateEdittedMessages) + "\n" +
                                    "    \n"+
                                    "     \n"+
                                    "    Group Logs:\n" +
                                    "       " + gson.toJson(Edit.groupEdittedMessages) + "\n"+
                                    "    \n"+
                                    "     \n"+
                                    "    Guild Logs:\n"+
                                    "       " + gson.toJson(Edit.guildEdittedMessages) + "\n" +
                                    "    \n"+
                                    "     \n"+
                                    "    \n"+
                                    "     \n"+
                                    "Delete Logs ->\n"+
                                    "    Private Logs:\n" +
                                    "       " + gson.toJson(Delete.privateDeletedMessages) + "\n" +
                                    "    \n"+
                                    "     \n"+
                                    "    Group Logs:\n" +
                                    "       " + gson.toJson(Delete.groupDeletedMessages) + "\n"+
                                    "    \n"+
                                    "     \n"+
                                    "    Guild Logs:\n"+
                                    "       " + gson.toJson(Delete.guildDeletedMessages) + "\n" +
                                    "    \n"+
                                    "     \n"+
                                    "    \n"+
                                    "     \n"
                    );
                    writer.flush();
                    writer.close();
                    System.out.println("[CONSOLE] [++] Created new log via -> " + log.getAbsolutePath());
                }else{
                    System.out.println("[CONSOLE] [!!] Error Creating New Log...");
                    System.out.println(" ");
                }
            }catch (IOException e){
                System.out.println("[CONSOLE] [!!] Error Creating New Log...");
                System.out.println("[CONSOLE] [OUTPUT ERROR] " + e);
                System.out.println(" ");
            }
        }
    }

}

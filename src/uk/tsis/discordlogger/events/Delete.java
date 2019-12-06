package uk.tsis.discordlogger.events;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageDeleteEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Delete extends ListenerAdapter {
    public static List<String> guildDeletedMessages = new ArrayList<>();
    public static List<String> privateDeletedMessages = new ArrayList<>();
    public static List<String> groupDeletedMessages = new ArrayList<>();

    @Override
    public void onMessageDelete(@Nonnull MessageDeleteEvent event) {
        switch (event.getChannel().getType()){
            case PRIVATE:{
                for (long x : uk.tsis.discordlogger.events.Message.privateMessagesX){
                    if (x == event.getMessageIdLong()){
                        Message messageObj = (Message) event.getChannel().retrieveMessageById(x);
                        String message = "[Message Delete Logs] [DM] " + messageObj.getAuthor().getName() + ": " + messageObj.getContentRaw();
                        System.out.println(message);
                        System.out.println(" ");
                        privateDeletedMessages.add(message);

                    }
                }
                break;
            }
            case TEXT:{
                for (long x : uk.tsis.discordlogger.events.Message.privateMessagesX){
                    if (x == event.getMessageIdLong()){
                        Message messageObj = (Message) event.getChannel().retrieveMessageById(x);
                        String message = "[Message Delete Logs] ["+ event.getGuild().getName()+"] " +  messageObj.getAuthor().getName()
                                + ": " + messageObj.getContentRaw();
                        System.out.println(message);
                        System.out.println(" ");
                        guildDeletedMessages.add(message);

                    }
                }
                break;
            }

            case GROUP:{
                for (long x : uk.tsis.discordlogger.events.Message.privateMessagesX){
                    if (x == event.getMessageIdLong()){
                        Message messageObj = (Message) event.getChannel().retrieveMessageById(x);
                        String message = "[Message Delete Logs] [Group] " + messageObj.getAuthor().getName() + ": " + messageObj.getContentRaw();
                        System.out.println(message);
                        System.out.println(" ");
                        groupDeletedMessages.add(message);

                    }
                }
                break;
            }

        }

    }
}

package uk.tsis.discordlogger.events;

import net.dv8tion.jda.api.events.message.MessageUpdateEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("All")
public class Edit extends ListenerAdapter {

    public static List<String> guildEdittedMessages = new ArrayList<>();
    public static List<String> privateEdittedMessages = new ArrayList<>();
    public static List<String> groupEdittedMessages = new ArrayList<>();

    public void onMessageUpdate(@Nonnull MessageUpdateEvent event) {
        switch (event.getChannel().getType()) {
            case PRIVATE: {
                for (Map.Entry<Long, String> messages : Message.allMessages.entrySet()) {
                    if (messages.getKey() == event.getMessageIdLong()) {
                        String message = "[Message Edit Logs] [" + event.getChannel().getName() + "] " + event.getAuthor().getName()
                                + ": " + messages.getValue();
                        System.out.println(message);
                        System.out.println("--------------------------------------------------");
                        System.out.println("[Message Edit Logs After] [" + event.getChannel().getName() + "] " + event.getAuthor().getName()
                                + ": " + event.getMessage().getContentRaw());
                        System.out.println(" ");
                        privateEdittedMessages.add(message + "\n" + "-----------------------------" + "\n" + "[Message Edit Logs After] [" + event.getChannel().getName() + "] " + event.getAuthor().getName()
                                + ": " + event.getMessage().getContentRaw());

                    }
                }
                break;
            }

            case GROUP: {

                for (Map.Entry<Long, String> messages : Message.allMessages.entrySet()) {
                    if (messages.getKey() == event.getMessageIdLong()) {
                        String message = "[Message Edit Logs] [" + event.getChannel().getName() + "] " + event.getAuthor().getName()
                                + ": " + messages.getValue();
                        System.out.println(message);
                        System.out.println("--------------------------------------------------");
                        System.out.println("[Message Edit Logs After] [" + event.getChannel().getName() + "] " + event.getAuthor().getName()
                                + ": " + event.getMessage().getContentRaw());
                        System.out.println(" ");
                        privateEdittedMessages.add(message + "\n" + "-----------------------------" + "\n" + "[Message Edit Logs After] [" + event.getChannel().getName() + "] " + event.getAuthor().getName()
                                + ": " + event.getMessage().getContentRaw());

                    }
                }
                break;
            }

            case TEXT: {
                for (Map.Entry<Long, String> messages : Message.allMessages.entrySet()) {
                    if (messages.getKey() == event.getMessageIdLong()) {
                        String message = "[Message Edit Logs] [" + event.getGuild().getName() + "] " + event.getAuthor().getName()
                                + ": " + messages.getValue();
                        System.out.println(message);
                        System.out.println("--------------------------------------------------");
                        System.out.println("[Message Edit Logs After] [" + event.getGuild().getName() + "] " + event.getAuthor().getName()
                                + ": " + event.getMessage().getContentRaw());
                        System.out.println(" ");
                        privateEdittedMessages.add(message + "\n" + "-----------------------------" + "\n" + "[Message Edit Logs After] [" + event.getGuild().getName() + "] " + event.getAuthor().getName()
                                + ": " + event.getMessage().getContentRaw());

                    }
                }
                break;
            }


        }
    }
}

package uk.tsis.discordlogger.events;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.*;

@SuppressWarnings("All")
public class Message extends ListenerAdapter {
    public static List<String> guildMessages = new ArrayList<>();
    public static List<String> privateMessages = new ArrayList<>();
    public static List<String> groupMessages = new ArrayList<>();

    public static List<Long> guildMessagesX = new ArrayList<>();
    public static List<Long> privateMessagesX = new ArrayList<>();
    public static List<Long> groupMessagesX = new ArrayList<>();

    public static Map<Long, String> allMessages = new HashMap<>();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        switch (event.getChannel().getType()) {
            case PRIVATE: {
                String message = "[DM Message Logger] " + "[" + event.getChannel().getName() + "] " +
                        event.getMember().getEffectiveName() + ": " + event.getMessage().getContentRaw();
                privateMessages.add(message);
                privateMessagesX.add(event.getMessageIdLong());
                System.out.println(message);
                System.out.println(" ");
                allMessages.put(event.getMessageIdLong(), event.getMessage().getContentRaw());
                break;
            }

            case GROUP: {
                String message = "[Group Message Logger] " + "[" + event.getChannel().getName() + "] " +
                        event.getMember().getEffectiveName() + ": " + event.getMessage().getContentRaw();
                groupMessages.add(message);
                groupMessagesX.add(event.getMessageIdLong());
                System.out.println(message);
                System.out.println(" ");
                allMessages.put(event.getMessageIdLong(), event.getMessage().getContentRaw());
                break;
            }

            case TEXT: {
                String message = "[Guild Message Logger] " + "[" + event.getGuild().getName() + "] " + event.getMember().getEffectiveName() + ": " + event.getMessage().getContentRaw();
                guildMessages.add(message);
                guildMessagesX.add(event.getMessageIdLong());
                System.out.println(message);
                System.out.println(" ");
                allMessages.put(event.getMessageIdLong(), event.getMessage().getContentRaw());
                break;
            }

        }

    }


}

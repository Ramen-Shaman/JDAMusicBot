package TVBot.Messenger.MusicStuff;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageCommands extends ListenerAdapter
{

    public PlayerManager manager = PlayerManager.getInstance();

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        String [] command = event.getMessage().getContentRaw().split(" ");
        User name = event.getAuthor();
        VoiceChannel voiceChannel = event.getGuild().getMember(name).getVoiceState().getChannel();


        if("~play".equals(command[0]) && command.length == 2)
        {
            if(command[1] == " ")
            {
                event.getChannel().sendMessage("Please provide a link!").queue();
            }
            else
            {
                manager.loadAndPlay(event.getChannel(), voiceChannel, command[1]);
            }
        }
        else if ("~skip".equals(command[0]))
        {
            manager.skipTrack(event.getChannel());
        }
        super.onGuildMessageReceived(event);
    }
}

import TVBot.Messenger.MusicStuff.PlayerManager;
import TVBot.Messenger.MusicStuff.TrackScheduler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayer;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import javax.security.auth.login.LoginException;


public class Startup extends ListenerAdapter
{

    public PlayerManager manager = PlayerManager.getInstance();

    public static void main(String[] args) throws LoginException
    {

        String token = "NjgwNTA5Mzc2OTg0OTA3Nzk2.XlA7vg.sqjZPkIycaLx9I319mwOuuKiZ2w";

        JDABuilder builder = JDABuilder.createDefault(token);
        builder.setActivity(Activity.watching("Three's Company"));
        builder.addEventListeners(new Startup());
        builder.build();
    }


    @Override
    public void onReady(ReadyEvent event)
    {
        System.out.println("Everything is working!");
    }


    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        String [] command = event.getMessage().getContentRaw().split(" ");
        VoiceChannel voiceChannel = event.getMember().getVoiceState().getChannel();

        if(!event.getAuthor().isBot() && "~play".equals(command[0]) && command.length == 2)
        {
            if(command[1].isEmpty())
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

        else if ("~leave".equals((command[0])))
        {
            event.getGuild().getAudioManager().closeAudioConnection();
        }

    }


}



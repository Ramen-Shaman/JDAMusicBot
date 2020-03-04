import TVBot.Messenger.MusicStuff.AudioPlayerSendHandler;
import TVBot.Messenger.MusicStuff.GuildMusicManager;
import TVBot.Messenger.MusicStuff.PlayerManager;
import TVBot.Messenger.MusicStuff.TrackScheduler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;

import javax.security.auth.login.LoginException;


public class Main extends ListenerAdapter
{
    public JDA jda;


    public static void main(String[] args) throws LoginException
    {
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        builder.setToken("NjgwNTA5Mzc2OTg0OTA3Nzk2.XlLVUA.OXc6USTNDn3CsFXBAYcG1zTN0aA");
        builder.addEventListeners(new Main());
        builder.setActivity(Activity.watching("Three's Company"));
        builder.build();


    }


    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event)
    {
        String prefix = "!";
        Guild guild = event.getGuild();
        VoiceChannel voiceChannel = guild.getVoiceChannelsByName("Music Room", true).get(0);
        AudioManager player = guild.getAudioManager();
        AudioPlayerManager lavaPlayer = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(lavaPlayer);
        AudioPlayer TVBot = lavaPlayer.createPlayer();
        AudioPlayerSendHandler MySendHandler = new AudioPlayerSendHandler(TVBot);
        PlayerManager playerManager = PlayerManager.getInstance();
        GuildMusicManager musicManager = playerManager.getGuildMusicManager(event.getGuild());
        TrackScheduler trackScheduler = new TrackScheduler(TVBot);


        if(event.getAuthor().isBot()) return;

        player.setSendingHandler(MySendHandler);

        player.openAudioConnection(voiceChannel);


        if(event.getMessage().getContentRaw().startsWith(prefix+ "play"))
        {
            String trackUrl = event.getMessage().getContentRaw().substring(6);
            PlayerManager manager = PlayerManager.getInstance();
            manager.loadandPlay(event.getChannel(), trackUrl);

        }
        if(event.getMessage().getContentRaw().equalsIgnoreCase(prefix+"stop"))
        {
            PlayerManager manager = PlayerManager.getInstance();
            musicManager.scheduler.getQueue().clear();
            manager.getGuildMusicManager(guild).player.destroy();
            player.closeAudioConnection();
        }
        if(event.getMessage().getContentRaw().equalsIgnoreCase(prefix+"skip"))
        {
            musicManager.scheduler.nextTrack();
        }




    }

}



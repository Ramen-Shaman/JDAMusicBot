import TVBot.Messenger.MusicStuff.AudioPlayerSendHandler;
import TVBot.Messenger.MusicStuff.GuildMusicManager;
import TVBot.Messenger.MusicStuff.PlayerManager;
import TVBot.Messenger.MusicStuff.TrackScheduler;
import com.sedmelluq.discord.lavaplayer.filter.PcmFilterFactory;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventListener;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.playback.AudioFrame;
import com.sedmelluq.discord.lavaplayer.track.playback.MutableAudioFrame;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;

import javax.security.auth.login.LoginException;
import javax.sound.midi.Track;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class Main extends ListenerAdapter
{
    public JDA jda;
    public PlayerManager manager = PlayerManager.getInstance();

    public static void main(String[] args) throws LoginException
    {
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        builder.setToken("NjgwNTA5Mzc2OTg0OTA3Nzk2.XxkYrw.A3wklOnNC6oD-d5SuXecfH39TNo");
        builder.addEventListeners(new Main());
        builder.setActivity(Activity.watching("Three's Company"));
        builder.build();

    }


    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String [] command = event.getMessage().getContentRaw().split(" ", 2);
        User name = event.getAuthor();
        VoiceChannel voiceChannel = event.getGuild().getMember(name).getVoiceState().getChannel();
        AudioManager audioManager = event.getGuild().getAudioManager();
        AudioPlayer player = new AudioPlayer() {
            @Override
            public AudioTrack getPlayingTrack() {
                return null;
            }

            @Override
            public void playTrack(AudioTrack audioTrack) {

            }

            @Override
            public boolean startTrack(AudioTrack audioTrack, boolean b) {
                return false;
            }

            @Override
            public void stopTrack() {

            }

            @Override
            public int getVolume() {
                return 0;
            }

            @Override
            public void setVolume(int i) {

            }

            @Override
            public void setFilterFactory(PcmFilterFactory pcmFilterFactory) {

            }

            @Override
            public void setFrameBufferDuration(Integer integer) {

            }

            @Override
            public boolean isPaused() {
                return false;
            }

            @Override
            public void setPaused(boolean b) {

            }

            @Override
            public void destroy() {

            }

            @Override
            public void addListener(AudioEventListener audioEventListener) {

            }

            @Override
            public void removeListener(AudioEventListener audioEventListener) {

            }

            @Override
            public void checkCleanup(long l) {

            }

            @Override
            public AudioFrame provide() {
                return null;
            }

            @Override
            public AudioFrame provide(long l, TimeUnit timeUnit) throws TimeoutException, InterruptedException {
                return null;
            }

            @Override
            public boolean provide(MutableAudioFrame mutableAudioFrame) {
                return false;
            }

            @Override
            public boolean provide(MutableAudioFrame mutableAudioFrame, long l, TimeUnit timeUnit) throws TimeoutException, InterruptedException {
                return false;
            }
        }
        TrackScheduler scheduler = new TrackScheduler(audioManager.)


        if("~play".equals(command[0]) && command.length == 2)
        {
            if(command[1] == " ")
            {
                event.getChannel().sendMessage("Please provide a link!").queue();
            }
            else
            {
                manager.loadAndPlay(event.getChannel(), voiceChannel,  command[1]);

                Timer timer = new Timer();
                // schedule to bot to leave after 5400 ms...
                timer.scheduleAtFixedRate(new java.util.TimerTask() {
                    @Override
                    public void run() {
                        audioManager.closeAudioConnection();
                    }
                }, 600000, 1);
            }
        }
        else if ("~skip".equals(command[0]))
        {
            manager.skipTrack(event.getChannel());
        }
        super.onGuildMessageReceived(event);
    }
}



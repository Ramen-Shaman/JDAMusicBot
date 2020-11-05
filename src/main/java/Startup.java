import TVBot.Messenger.MusicStuff.MessageCommands;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import javax.security.auth.login.LoginException;


public class Startup
{
    public static void main(String[] args) throws LoginException
    {
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        builder.setToken("NjgwNTA5Mzc2OTg0OTA3Nzk2.XlA7vg.pejJ7E6GR7HfA6eOiIx_0Y5mB1Y");
        builder.addEventListeners(new MessageCommands());
        builder.setActivity(Activity.watching("The Election"));
        builder.build();
    }

}



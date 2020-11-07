import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import javax.security.auth.login.LoginException;


public class Startup extends ListenerAdapter
{

    public static void main(String[] args) throws LoginException
    {

        String token = "NjgwNTA5Mzc2OTg0OTA3Nzk2.XlA7vg.pejJ7E6GR7HfA6eOiIx_0Y5mB1Y";

        JDABuilder builder = JDABuilder.createDefault(token);
        builder.setActivity(Activity.watching("Three's Company"));
        builder.build();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        String message =  event.getMessage().toString();
        String[] command = message.split(" ", 0);
        if(command[0].equals("~play") && command[1].isEmpty())
        {
            event.getChannel().sendMessage("Please enter an argument!").queue();
        }
        System.out.println(command[0]);
    }


}



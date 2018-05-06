package telebot;

import com.botticelli.bot.Bot;
import com.botticelli.messagereceiver.MessageReceiver;

public class Main
{

	public static void main(String[] args)
	{	    
	    String token = "192258720:AAGZrB4IM3bGwNVpKxbLDeVm5bm-A4U9mSs";
	    Ballbot friday_night_ball_bot = new Ballbot(token);
	    MessageReceiver mr = new MessageReceiver(friday_night_ball_bot, 250, 5);
	    mr.start();
	    /*
	    String token2 = "191814942:AAEcy6GqmSBEGG29oCYyra8F1RcnWwfKbEE";
	    Tutbot ckm1bot = new Tutbot(token2);
	    MessageReceiver mr2 = new MessageReceiver(ckm1bot, 250, 1);
	    mr2.start();
	    */
	}
}
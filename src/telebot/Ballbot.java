package telebot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.botticelli.bot.Bot;
import com.botticelli.bot.request.methods.MessageToSend;
import com.botticelli.bot.request.methods.StickerReferenceToSend;
import com.botticelli.bot.request.methods.types.CallbackQuery;
import com.botticelli.bot.request.methods.types.ChosenInlineResult;
import com.botticelli.bot.request.methods.types.InlineQuery;
import com.botticelli.bot.request.methods.types.Message;
import com.botticelli.bot.request.methods.types.PreCheckoutQuery;
import com.botticelli.bot.request.methods.types.ShippingQuery;
import com.botticelli.bot.request.methods.types.User;

public class Ballbot extends Bot
{
	private List<User> players;
	private List<User> plusOne;

	private int count;
	private int size;

	public Ballbot(String token)
	{
		super(token);
		players = new ArrayList<User>();
		plusOne = new ArrayList<User>();
		count=0;
		size=15;
	}

	@Override
	public void audioMessage(Message arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void chose_inline_result(ChosenInlineResult arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void contactMessage(Message arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void documentMessage(Message arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void groupChatCreatedMessage(Message arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void groupChatPhotoDeleteMessage(Message arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void inLineQuery(InlineQuery arg0)
	{
		// TODO Auto-generated method stub

	}


	@Override
	public void locationMessage(Message arg0)
	{
		// TODO Auto-generated method stub

	}


	@Override
	public void newChatPhotoMessage(Message arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void newChatTitleMessage(Message arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void photoMessage(Message arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void stickerMessage(Message arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void textMessage(Message arg0)
	{
	    //StickerReferenceToSend srs = new StickerReferenceToSend(arg0.getChat().getId(), "BQADBAADLQADysdxBFVpf9u-DtCqAg");
	    //sendStickerbyReference(srs);
		User u = new User();
		u= arg0.getFrom();

		switch(arg0.getText()){

		case "/in":
		//case "/in@friday_night_ball_bot":
			if(!inList(u)){
				addPlayer(u);
				sendMsg(arg0, "You have been added");
			}
			else{
				sendMsg(arg0, "You are already in the list");
			}
			break;

		case "/bail":
		//case "/bail@friday_night_ball_bot":

			if(removePlayer(u)){
				sendMsg(arg0, "You have been removed");
			}
			else{
				sendMsg(arg0, "You are not in the list");
			}
			break;

		case "/plus1":
			if(addPlusOne(u)){
				sendMsg(arg0, "+1 added");
			}
			break;

		case "/minus1":
			if(removePlusOne(u)){
				sendMsg(arg0, "+1 removed");
			}
			else{
				sendMsg(arg0, "You are not in the +1 list");
			}
			break;

		case "/help":
		//case "/help@friday_night_ball_bot":
			sendMsg(arg0, printCommands());
			break;

		case "/list":
		//case "/list@friday_night_ball_bot":

			sendMsg(arg0, printList());
			break;

		case "/size":
		//case "/size@friday_night_ball_bot":
			sendMsg(arg0, String.valueOf(getSize()));
			break;

		case "/reset":
			if(u.getUserName().equals("catalypse") || u.getUserName().equals("kinsonchik") || u.getUserName().equals("Timmm1")){
				reset();
				sendMsg(arg0, "List has been reset");
			}
			else{
				sendMsg(arg0, "You do not have privileges to reset the list");

			}
			break;

		case "/setsize":
			if(u.getUserName().equals("catalypse") || u.getUserName().equals("kinsonchik")){

				sendMsg(arg0, "Size is now: ");
			}
			else{
				sendMsg(arg0, "You do not have privileges to set the size");

			}
			break;
		}

	}

	@Override
	public void videoMessage(Message arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void voiceMessage(Message arg0)
	{
		// TODO Auto-generated method stub

	}

	//add a player
		public void addPlayer(User player)
		{
				players.add(player);

		}

		//checks if player is in list
		public boolean inList(User player)
		{
			if(players.isEmpty()){
				return false;
			}
			else{

				String playerName = player.getFirstName() + " " + player.getLastName();
				for(Iterator<User> iter = players.listIterator(); iter.hasNext(); ){
					User a= iter.next();
					String name= a.getFirstName() + " " + a.getLastName();
					if(name.equals(playerName)){
						return true;
					}
				}
			}
			return false;
		}

	//add plus 1
	public boolean addPlusOne(User player)
	{
		plusOne.add(player);
		return true;

	}

	//remove a player
	public boolean removePlayer(User player)
	{

		String playerName = player.getFirstName() + " " + player.getLastName();
		for(Iterator<User> iter = players.listIterator(); iter.hasNext(); ){
			User a= iter.next();
			String name= a.getFirstName() + " " + a.getLastName();
			if(name.equals(playerName)){
				players.remove(a);
				return true;
			}
		}
		return false;

	}

	//remove plus 1
	public boolean removePlusOne(User player)
	{
		String playerName = player.getFirstName() + " " + player.getLastName();
		for(Iterator<User> iter = plusOne.listIterator(); iter.hasNext(); ){
			User a= iter.next();
			String name= a.getFirstName() + " " + a.getLastName();
			if(name.equals(playerName)){
				plusOne.remove(a);
				return true;
			}
		}
		return false;
	}

	//prints list of players added
	public String printList()
	{
		String pList= "List: ";
		String rList= "+1: ";
		String total= "Total: ";
		String out= "";
		List<String> aList = new ArrayList<String>();
		List<String> bList = new ArrayList<String>();

		if(!players.isEmpty() || !plusOne.isEmpty()){
			for(User a: players){
				if(a.getLastName() == null){
					aList.add(a.getFirstName());
				}
				else{
					aList.add(a.getFirstName() + " " + a.getLastName());
				}
			}

			pList += String.join(", ", aList);

			for(User a: plusOne){
				if(a.getLastName() == null){
					bList.add(a.getFirstName());
				}
				else{
					bList.add(a.getFirstName() + " " + a.getLastName());
				}
			}
			rList += String.join(", ", bList);

			total += players.size() + plusOne.size();
			//pList += "\nTotal: " + players.size();
			out += pList + "\n" + rList + "\n" + total;
		}
		else{
			out += "Empty";
		}

		return out;
	}

	public String printCommands()
	{
		String commands= "Here are the commands: \n/add\n/remove\n/list\n/size";
		return commands;
	}

	public void sendMsg(Message arg0, String cmd)
	{
		MessageToSend mts = new MessageToSend(arg0.getChat().getId(), cmd);
		//mts.setReplyToMessageID(arg0.getMessageID());
		sendMessage(mts);
	}

	public void setSize(int size)
	{
		this.size=size;
	}

	public int getSize(){
		return size;
	}

	public void reset(){
		players.clear();
		plusOne.clear();
	}

	@Override
	public void venueMessage(Message m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void newChatMemberMessage(Message m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void newChatMembersMessage(Message m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void leftChatMemberMessage(Message m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void callback_query(CallbackQuery cq) {
		// TODO Auto-generated method stub

	}

	@Override
	public void gameMessage(Message m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void videoNoteMessage(Message m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pinnedMessage(Message m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preCheckOutQueryMessage(PreCheckoutQuery pcq) {
		// TODO Auto-generated method stub

	}

	@Override
	public void shippingQueryMessage(ShippingQuery sq) {
		// TODO Auto-generated method stub

	}

	@Override
	public void invoiceMessage(Message m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void successfulPaymentMessage(Message m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void routine() {
		// TODO Auto-generated method stub

	}
}

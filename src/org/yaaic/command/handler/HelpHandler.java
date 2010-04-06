package org.yaaic.command.handler;

import java.util.HashMap;

import org.yaaic.command.BaseHandler;
import org.yaaic.command.CommandParser;
import org.yaaic.exception.CommandException;
import org.yaaic.irc.IRCService;
import org.yaaic.model.Broadcast;
import org.yaaic.model.Conversation;
import org.yaaic.model.Message;
import org.yaaic.model.Server;

import android.content.Intent;

/**
 * Command: /help 
 * 
 * @author Karol Gliniecki <karol.gliniecki@googlemail.com>
 */
public class HelpHandler extends BaseHandler
{
	/**
	 * Execute /help
	 */
	@Override
	public void execute(String[] params, Server server, Conversation conversation, IRCService service) throws CommandException
	{
		CommandParser cp = CommandParser.getInstance();
		
		StringBuffer commandList = new StringBuffer("available commands: \n");
		HashMap<String, BaseHandler> commands = cp.getCommands();
		HashMap<String, String> aliases = cp.getAliases();
		
		Object[] commandKeys = commands.keySet().toArray();
		Object[] aliasesKeys = aliases.keySet().toArray();
		
		for (Object command: commandKeys) {
			String alias = "";
			for (Object aliasCommand: aliasesKeys) {
				System.out.println("alias: " + aliases.get(aliasCommand));
				if (command.equals(aliases.get(aliasCommand))) {
					alias = " or /" + aliasCommand;
					break;
				}
			}
			commandList.append("/" + command.toString() + alias + " - "+commands.get(command).getDescription() + "\n");
		}
		
		Message message = new Message(commandList.toString());
		message.setColor(Message.COLOR_YELLOW);
		conversation.addMessage(message);

		Intent intent = Broadcast.createConversationIntent(
			Broadcast.CONVERSATION_MESSAGE,
			server.getId(),
			conversation.getName()
		);
		service.sendBroadcast(intent);
	}

	/**
	 * 
	 *Usage of /help
	 */
	@Override
	public String getUsage()
	{
		return "/help";
	}

	/**
	 * Description of /help
	 */
	@Override
	public String getDescription()
	{
		return "lists all available commands";
	}
}
/*
 Yaaic - Yet Another Android IRC Client

Copyright 2009 Sebastian Kaspari

This file is part of Yaaic.

Yaaic is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Yaaic is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Yaaic.  If not, see <http://www.gnu.org/licenses/>.
*/
package org.yaaic.command;

import org.yaaic.irc.IRCService;
import org.yaaic.model.Channel;
import org.yaaic.model.Server;

/**
 * Command: /join
 * 
 * @author Sebastian Kaspari <sebastian@yaaic.org>
 */
public class JoinCommand extends BaseCommand
{
	/**
	 * Execute /join
	 */
	@Override
	public void execute(String[] params, Server server, Channel channel, IRCService service) throws CommandException
	{
		if (params.length == 2) {
			service.getConnection(server.getId()).joinChannel(params[1]);
		} else if (params.length == 3) {
			service.getConnection(server.getId()).joinChannel(params[1], params[2]);
		} else {
			throw new CommandException("Invalid number of params");
		}
	}
	
	/**
	 * Usage of /join
	 */
	@Override
	public String getUsage()
	{
		return "/join <channel> [<key>]";
	}
}
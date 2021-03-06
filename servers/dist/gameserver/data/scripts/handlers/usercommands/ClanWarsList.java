/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package handlers.usercommands;

import java.util.ArrayList;
import java.util.List;

import lineage2.gameserver.handlers.IUserCommandHandler;
import lineage2.gameserver.handlers.UserCommandHandler;
import lineage2.gameserver.model.Player;
import lineage2.gameserver.model.pledge.Alliance;
import lineage2.gameserver.model.pledge.Clan;
import lineage2.gameserver.network.serverpackets.SystemMessage;
import lineage2.gameserver.scripts.ScriptFile;

/**
 * @author Mobius
 * @version $Revision: 1.0 $
 */
public class ClanWarsList implements IUserCommandHandler, ScriptFile
{
	private static final int[] COMMAND_IDS =
	{
		88,
		89,
		90
	};
	
	/**
	 * Method useUserCommand.
	 * @param id int
	 * @param activeChar Player
	 * @return boolean
	 * @see lineage2.gameserver.handlers.IUserCommandHandler#useUserCommand(int, Player)
	 */
	@Override
	public boolean useUserCommand(int id, Player activeChar)
	{
		if ((id != COMMAND_IDS[0]) && (id != COMMAND_IDS[1]) && (id != COMMAND_IDS[2]))
		{
			return false;
		}
		
		Clan clan = activeChar.getClan();
		
		if (clan == null)
		{
			activeChar.sendPacket(new SystemMessage(SystemMessage.NOT_JOINED_IN_ANY_CLAN));
			return false;
		}
		
		SystemMessage sm;
		List<Clan> data = new ArrayList<>();
		
		if (id == 88)
		{
			activeChar.sendPacket(new SystemMessage(SystemMessage._ATTACK_LIST_));
			data = clan.getEnemyClans();
		}
		else if (id == 89)
		{
			activeChar.sendPacket(new SystemMessage(SystemMessage._UNDER_ATTACK_LIST_));
			data = clan.getAttackerClans();
		}
		else
		{
			activeChar.sendPacket(new SystemMessage(SystemMessage._WAR_LIST_));
			
			for (Clan c : clan.getEnemyClans())
			{
				if (clan.getAttackerClans().contains(c))
				{
					data.add(c);
				}
			}
		}
		
		for (Clan c : data)
		{
			String clanName = c.getName();
			Alliance alliance = c.getAlliance();
			
			if (alliance != null)
			{
				sm = new SystemMessage(SystemMessage.S1_S2_ALLIANCE).addString(clanName).addString(alliance.getAllyName());
			}
			else
			{
				sm = new SystemMessage(SystemMessage.S1_NO_ALLIANCE_EXISTS).addString(clanName);
			}
			
			activeChar.sendPacket(sm);
		}
		
		activeChar.sendPacket(new SystemMessage(SystemMessage.__EQUALS__));
		return true;
	}
	
	/**
	 * Method getUserCommandList.
	 * @return int[]
	 * @see lineage2.gameserver.handlers.IUserCommandHandler#getUserCommandList()
	 */
	@Override
	public int[] getUserCommandList()
	{
		return COMMAND_IDS;
	}
	
	/**
	 * Method onLoad.
	 * @see lineage2.gameserver.scripts.ScriptFile#onLoad()
	 */
	@Override
	public void onLoad()
	{
		UserCommandHandler.getInstance().registerUserCommandHandler(this);
	}
	
	/**
	 * Method onReload.
	 * @see lineage2.gameserver.scripts.ScriptFile#onReload()
	 */
	@Override
	public void onReload()
	{
	}
	
	/**
	 * Method onShutdown.
	 * @see lineage2.gameserver.scripts.ScriptFile#onShutdown()
	 */
	@Override
	public void onShutdown()
	{
	}
}

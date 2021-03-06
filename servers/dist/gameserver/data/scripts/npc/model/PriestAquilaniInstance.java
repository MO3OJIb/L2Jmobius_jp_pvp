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
package npc.model;

import lineage2.gameserver.model.Player;
import lineage2.gameserver.model.instances.NpcInstance;
import lineage2.gameserver.network.serverpackets.NpcHtmlMessage;
import lineage2.gameserver.templates.npc.NpcTemplate;
import lineage2.gameserver.utils.Location;
import quests.Q10288_SecretMission;

/**
 * @author Mobius
 * @version $Revision: 1.0 $
 */
public final class PriestAquilaniInstance extends NpcInstance
{
	
	/**
	 * Constructor for PriestAquilaniInstance.
	 * @param objectId int
	 * @param template NpcTemplate
	 */
	public PriestAquilaniInstance(int objectId, NpcTemplate template)
	{
		super(objectId, template);
	}
	
	/**
	 * Method showChatWindow.
	 * @param player Player
	 * @param val int
	 * @param arg Object[]
	 */
	@Override
	public void showChatWindow(Player player, int val, Object... arg)
	{
		if ((player.getQuestState(Q10288_SecretMission.class) != null) && player.getQuestState(Q10288_SecretMission.class).isCompleted())
		{
			player.sendPacket(new NpcHtmlMessage(player, this, "default/32780-1.htm", val));
			return;
		}
		
		player.sendPacket(new NpcHtmlMessage(player, this, "default/32780.htm", val));
		return;
	}
	
	/**
	 * Method onBypassFeedback.
	 * @param player Player
	 * @param command String
	 */
	@Override
	public void onBypassFeedback(Player player, String command)
	{
		if (!canBypassCheck(player, this))
		{
			return;
		}
		
		if (command.equals("teleport"))
		{
			player.teleToLocation(new Location(118833, -80589, -2688));
			return;
		}
		
		super.onBypassFeedback(player, command);
	}
}

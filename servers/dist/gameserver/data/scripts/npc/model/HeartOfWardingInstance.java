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
import lineage2.gameserver.templates.npc.NpcTemplate;
import bosses.AntharasManager;

/**
 * @author Mobius
 * @version $Revision: 1.0 $
 */
public final class HeartOfWardingInstance extends NpcInstance
{
	
	/**
	 * Constructor for HeartOfWardingInstance.
	 * @param objectId int
	 * @param template NpcTemplate
	 */
	public HeartOfWardingInstance(int objectId, NpcTemplate template)
	{
		super(objectId, template);
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
		
		if (command.equals("enter_lair"))
		{
			AntharasManager.enterTheLair(player);
			return;
		}
		
		super.onBypassFeedback(player, command);
	}
}

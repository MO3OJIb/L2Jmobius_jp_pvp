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
package ai.residences.clanhall;

import lineage2.gameserver.model.Skill;
import lineage2.gameserver.model.instances.NpcInstance;
import lineage2.gameserver.tables.SkillTable;

/**
 * @author Mobius
 * @version $Revision: 1.0 $
 */
public final class MatchTrief extends MatchFighter
{
	public static final Skill HOLD = SkillTable.getInstance().getInfo(4047, 6);
	
	/**
	 * Constructor for MatchTrief.
	 * @param actor NpcInstance
	 */
	public MatchTrief(NpcInstance actor)
	{
		super(actor);
	}
	
	/**
	 * Method hold.
	 */
	public void hold()
	{
		final NpcInstance actor = getActor();
		addTaskCast(actor, HOLD);
		doTask();
	}
}

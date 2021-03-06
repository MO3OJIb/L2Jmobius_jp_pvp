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
package services;

import java.util.List;

import lineage2.commons.threading.RunnableImpl;
import lineage2.gameserver.ThreadPoolManager;
import lineage2.gameserver.model.Player;
import lineage2.gameserver.model.Zone;
import lineage2.gameserver.network.serverpackets.ExStartScenePlayer;
import lineage2.gameserver.scripts.ScriptFile;
import lineage2.gameserver.utils.ReflectionUtils;

/**
 * @author Mobius
 * @version $Revision: 1.0 $
 */
public final class LindviorMovie implements ScriptFile
{
	private static final long movieDelay = 3 * 60 * 60 * 1000L;
	
	/**
	 * Method onLoad.
	 * @see lineage2.gameserver.scripts.ScriptFile#onLoad()
	 */
	@Override
	public void onLoad()
	{
		Zone zone = ReflectionUtils.getZone("[keucereus_alliance_base_town_peace]");
		zone.setActive(true);
		ThreadPoolManager.getInstance().scheduleAtFixedRate(new ShowLindviorMovie(zone), movieDelay, movieDelay);
	}
	
	/**
	 * @author Mobius
	 */
	private final class ShowLindviorMovie extends RunnableImpl
	{
		private final Zone _zone;
		
		/**
		 * Constructor for ShowLindviorMovie.
		 * @param zone Zone
		 */
		ShowLindviorMovie(Zone zone)
		{
			_zone = zone;
		}
		
		/**
		 * Method runImpl.
		 */
		@Override
		public void runImpl()
		{
			List<Player> insideZoners = _zone.getInsidePlayers();
			
			if ((insideZoners != null) && !insideZoners.isEmpty())
			{
				for (Player player : insideZoners)
				{
					if (!player.isInBoat() && !player.isInFlyingTransform())
					{
						player.showQuestMovie(ExStartScenePlayer.SCENE_LINDVIOR);
					}
				}
			}
		}
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

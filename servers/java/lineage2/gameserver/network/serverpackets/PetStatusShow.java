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
package lineage2.gameserver.network.serverpackets;

import lineage2.gameserver.model.Summon;

public class PetStatusShow extends L2GameServerPacket
{
	private final int _summonType;
	private final int _summonId;
	
	public PetStatusShow(Summon summon)
	{
		_summonType = summon.getSummonType();
		_summonId = summon.getObjectId();
	}
	
	@Override
	protected final void writeImpl()
	{
		writeC(0xb1);
		writeD(_summonType);
		writeD(_summonId);// L2WT GOD
	}
}
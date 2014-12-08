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

import lineage2.gameserver.network.serverpackets.components.NpcString;

public class ExShowScreenMessage extends NpcStringContainer
{
	public static enum ScreenMessageAlign
	{
		TOP_LEFT,
		TOP_CENTER,
		TOP_RIGHT,
		MIDDLE_LEFT,
		MIDDLE_CENTER,
		MIDDLE_RIGHT,
		BOTTOM_CENTER,
		BOTTOM_RIGHT,
	}
	
	public static final int SYSMSG_TYPE = 0;
	public static final int STRING_TYPE = 1;
	private final int _type;
	private final int _sysMessageId;
	private final boolean _big_font;
	private final boolean _effect;
	private final ScreenMessageAlign _text_align;
	private final int _time;
	private final int _unk1;
	private final int _unk2;
	private final int _unk3;
	private final boolean _fade;
	
	public ExShowScreenMessage(String text, int time, ScreenMessageAlign text_align, boolean big_font)
	{
		this(text, time, text_align, big_font, 1, -1, false);
	}
	
	public ExShowScreenMessage(String text, int time, ScreenMessageAlign text_align, boolean big_font, int type, int messageId, boolean showEffect)
	{
		super(NpcString.NONE, text);
		_type = type;
		_sysMessageId = messageId;
		_time = time;
		_text_align = text_align;
		_big_font = big_font;
		_effect = showEffect;
		_unk1 = 0x00;
		_unk2 = 0x00;
		_unk3 = 0x00;
		_fade = false;
	}
	
	public ExShowScreenMessage(NpcString t, int time, ScreenMessageAlign text_align, String... params)
	{
		this(t, time, text_align, true, STRING_TYPE, -1, false, false, 0, 0, 0, params);
	}
	
	public ExShowScreenMessage(NpcString npcString, int time, ScreenMessageAlign text_align, boolean big_font, String... params)
	{
		this(npcString, time, text_align, big_font, STRING_TYPE, -1, false, false, 0, 0, 0, params);
	}
	
	public ExShowScreenMessage(NpcString npcString, int time, ScreenMessageAlign text_align, boolean big_font, boolean showEffect, String... params)
	{
		this(npcString, time, text_align, big_font, STRING_TYPE, -1, showEffect, false, 0, 0, 0, params);
	}
	
	public ExShowScreenMessage(NpcString npcString, int time, ScreenMessageAlign text_align, boolean big_font, boolean showEffect, boolean fade, String... params)
	{
		this(npcString, time, text_align, big_font, STRING_TYPE, -1, showEffect, fade, 0, 0, 0, params);
	}
	
	public ExShowScreenMessage(NpcString npcString, int time, ScreenMessageAlign text_align, boolean big_font, int type, int systemMsg, boolean showEffect, String... params)
	{
		this(npcString, time, text_align, big_font, type, systemMsg, showEffect, false, 0, 0, 0, params);
	}
	
	public ExShowScreenMessage(NpcString npcString, int time, ScreenMessageAlign text_align, boolean big_font, int type, int systemMsg, boolean showEffect, int unk, String... params)
	{
		this(npcString, time, text_align, big_font, type, systemMsg, showEffect, false, unk, 0, 0, params);
	}
	
	public ExShowScreenMessage(NpcString npcString, int time, ScreenMessageAlign text_align, boolean big_font, int type, int systemMsg, boolean showEffect, boolean fade, String... params)
	{
		this(npcString, time, text_align, big_font, type, systemMsg, showEffect, fade, 0, 0, 0, params);
	}
	
	public ExShowScreenMessage(NpcString npcString, int time, ScreenMessageAlign text_align, boolean big_font, int type, int systemMsg, boolean fade, boolean showEffect, int unk1, int unk2, int unk3, String... params)
	{
		super(npcString, params);
		_type = type;
		_sysMessageId = systemMsg;
		_time = time;
		_text_align = text_align;
		_big_font = big_font;
		_effect = showEffect;
		_unk1 = unk1;
		_unk2 = unk2;
		_unk3 = unk3;
		_fade = fade;
	}
	
	@Override
	protected final void writeImpl()
	{
		writeEx(0x39);
		writeD(_type); // 0 - system messages, 1 - your defined text
		writeD(_sysMessageId); // system message id (_type must be 0 otherwise no effect)
		writeD(_text_align.ordinal() + 1);
		writeD(_unk1);
		writeD(_big_font ? 0 : 1);
		writeD(_unk2);
		writeD(_unk3);
		writeD(_effect ? 1 : 0); // upper effect (0 - disabled, 1 enabled) - _position must be 2 (center) otherwise no effect
		writeD(_time);
		writeD(_fade ? 0x01 : 0x00);
		writeElements();
	}
}
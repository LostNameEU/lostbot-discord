/*
 * IEmbed.java is part of the lostbot-discord project
 *
 * lostbot-discord is the Discord Support Bot of the LostNameEU Discord server.
 * Copyright (C) 2020 Henrik Steffens
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 * Last edit: 2020/11/10
 */

package de.th3ph4nt0m.lostbotdc.utils;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class IEmbed
{
    private final String title;
    private String description;
    private Color color;


    public IEmbed(String pTitle, Color pColor)
    {
        this.title = pTitle;
        this.color = pColor;
    }

    public IEmbed(String pTitle)
    {
        this.title = pTitle;
    }

    public IEmbed addLine(String text)
    {
        if (description != null)
            description = description + "\n" + text;
        else description = text;
        return this;
    }

    public MessageEmbed build()
    {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle(title);
        builder.setColor(color != null ? color : Color.decode("#77FFCE"));
        builder.setDescription(description);
        builder.setFooter("LostBot ©2020 Th3Ph4nt0m");
        return builder.build();
    }
}

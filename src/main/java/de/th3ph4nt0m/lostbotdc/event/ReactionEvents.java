/*
 * ReactionEvents.java is part of the lostbot-discord project
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
 * Last edit: 2020/11/9
 */

package de.th3ph4nt0m.lostbotdc.event;

import de.th3ph4nt0m.lostbotdc.Bot;
import de.th3ph4nt0m.lostbotdc.utils.IMember;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class ReactionEvents extends ListenerAdapter
{

    @Override
    public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event)
    {
        //listener for accepting rules and privacy by reaction
        if (!event.getUser().getId().equals(Bot.getInstance().getJda().getSelfUser().getId())) {
            //listening to every reaction in the "#rules"-channel
            if (event.getChannel().getId().equals("749566288296411167")) {
                event.getMember().getGuild().addRoleToMember(event.getUserId(), Bot.getInstance().getJda().getRoleById("749566605826195497")).queue();
                IMember iMember = new IMember(event.getMember(), event.getUserId());
                if (!iMember.existsInDB())
                    iMember.createInDB();
            }
        }
    }


    @Override
    public void onGuildMessageReactionRemove(GuildMessageReactionRemoveEvent event)
    {
        //listener for declining rules and privacy by reaction
        if (event.getChannel().getId().equals("749566288296411167")) {
            event.getMember().getGuild().removeRoleFromMember(event.getUserId(), Bot.getInstance().getJda().getRoleById("749566605826195497")).queue();
            IMember iMember = new IMember(event.getMember(), event.getUserId());
            if (iMember.existsInDB())
                iMember.removeFromDB();
        }
    }
}

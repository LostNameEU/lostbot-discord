/*
 * Bot.java is part of the lostbot-discord project
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

package de.th3ph4nt0m.lostbotdc;

import de.th3ph4nt0m.lostbotdc.event.ReactionEvents;
import de.th3ph4nt0m.lostbotdc.utils.MongoHandler;
import de.th3ph4nt0m.lostbotdc.utils.Property;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class Bot
{
    private static Bot instance;
    private final Property property;
    private final MongoHandler dbHandler;
    private JDA jda;

    public Bot()
    {
        instance = this;
        //JDA configuration
        this.property = new Property();
        this.dbHandler = new MongoHandler();
        property.setDefaults();
        try {
            this.jda = JDABuilder.createDefault(property.get("bot", "bot.token"))
                    .setAutoReconnect(true)
                    .setStatus(OnlineStatus.ONLINE)
                    .setActivity(Activity.streaming("the github repository", "https://github.com/LostNameEU/lostbot-discord/"))
                    .build();

            //register JDA-events
            jda.addEventListener(new ReactionEvents());

            jda.awaitReady(); //blocks until JDA has finished loading
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Bot getInstance()
    {
        return instance;
    }

    public JDA getJda()
    {
        return jda;
    }

    public MongoHandler getDbHandler()
    {
        return dbHandler;
    }

    public Property getProperty()
    {
        return property;
    }
}

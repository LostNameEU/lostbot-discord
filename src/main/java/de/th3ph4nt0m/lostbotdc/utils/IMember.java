/*
 * IMember.java is part of the lostbot-discord project
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

package de.th3ph4nt0m.lostbotdc.utils;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import de.th3ph4nt0m.lostbotdc.Bot;
import net.dv8tion.jda.api.entities.Member;
import org.bson.Document;

public class IMember
{
    private final Member member;
    private final String id;

    /**
     * @param pMember Discord Member to create an IMember from
     * @param pID     ID of a Discord Member/User to create an IMember from because of some NullPointers at Member#getID
     */
    public IMember(Member pMember, String pID)
    {
        this.member = pMember;
        this.id = pID;
    }

    private MongoCollection<Document> users()
    {
        return Bot.getInstance().getDbHandler().users();
    }

    /**
     * @return user specific document from DB
     */
    public Document getDocument()
    {
        return users().find(Filters.eq("_id", id)).first();
    }


    /**
     * @return if user exists in DB
     */
    public boolean existsInDB()
    {
        return getDocument() != null;
    }

    /**
     * insert user into DB
     */
    public void createInDB()
    {
        Document append = new Document("_id", member.getId()).append("nick", member.getEffectiveName());
        users().insertOne(append);
    }

    /**
     * delete the user specific document
     */
    public void removeFromDB()
    {
        users().deleteOne(Filters.eq("_id", id));
    }

    /**
     * @return all information stored about a user
     */
    public String getInfo()
    {

        return getDocument().toJson();
    }
}
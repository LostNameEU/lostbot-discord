/*
 * Property.java is part of the lostbot-discord project
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

import java.io.*;
import java.util.Properties;

public class Property
{

    /**
     * @param file name of the file
     * @param key  the key to look for in the config
     * @return value of the given key
     */
    public String get(String file, String key)
    {
        try (InputStream input = new FileInputStream("cfg/" + file + ".properties")) {

            Properties prop = new Properties();

            // load a properties file from InputStream
            prop.load(input);

            return prop.getProperty(key);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void setDefaults()
    {
        //create the file if not exists
        File dir = new File("cfg");
        if (!dir.exists()) {
            if (!dir.mkdirs())
                System.out.println("ERROR: properties directory could not be generated!");
            try (OutputStream output = new FileOutputStream("lostbot/discord/bot.properties")) {

                Properties prop = new Properties();

                prop.setProperty("bot.token", "token");
                // set the properties value

                // save properties to project folder
                prop.store(output, null);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try (OutputStream output = new FileOutputStream("lostbot/discord/database.properties")) {

                Properties prop = new Properties();

                // set the properties value
                prop.setProperty("db.port", "27017");
                prop.setProperty("db.host", "localhost");
                prop.setProperty("db.username", "root");
                prop.setProperty("db.password", "root");
                prop.setProperty("db.authDB", "admin");
                prop.setProperty("db.useDB", "root");

                // save properties to project folder
                prop.store(output, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
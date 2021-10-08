package me.pray.listeners;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.client.model.Filters;

import me.pray.Bot;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class JoinListener extends ListenerAdapter {

	private Bot bot;
	
	public JoinListener(Bot bot) {
		this.bot = bot;
	}
	
	@Override
	public void onGuildJoin(GuildJoinEvent event) {
		var query = Filters.eq("GuildId", event.getGuild().getIdLong());
		
		if(bot.sbw.find(query).first() == null) {
			Document doc = new Document();
			doc.append("GuildId", event.getGuild().getIdLong());
			ArrayList<String> bannedWordsList = new ArrayList<>();
			doc.append("BannedWords", bannedWordsList);
			doc.append("FilterType", "High");
			bot.sbw.insertOne(doc);
		}
	}
	
}

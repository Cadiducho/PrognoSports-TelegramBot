package com.prognosports.telegrambot;

import com.cadiducho.zincite.ZinciteBot;
import com.cadiducho.zincite.modules.json.JsonModule;
import org.apache.commons.cli.*;

public class PrognoTelegramBot {

    private static final String VERSION = "1.0";

    public static void main(String[] args) {
        Options options = new Options();

        Option token = new Option("t", "token", true, "telegram bot token");
        Option owner = new Option("o", "owner", true, "ID of the owner");

        token.setRequired(true);
        owner.setRequired(true);

        options.addOption(token);
        options.addOption(owner);

        CommandLine commandLine;
        Long ownerId;
        try {
            commandLine = new DefaultParser().parse(options, args);

            ownerId = Long.parseLong(commandLine.getOptionValue("owner"));
        } catch (ParseException | NumberFormatException e) {
            System.out.println(e.getMessage());
            new HelpFormatter().printHelp("prognotelegrambot", options);

            System.exit(1);
            return;
        }


        ZinciteBot prognoSportsBot = new ZinciteBot(commandLine.getOptionValue("token"), ownerId, VERSION);

        prognoSportsBot.getModuleManager().registerModule(new JsonModule());

        prognoSportsBot.startServer();
    }
}

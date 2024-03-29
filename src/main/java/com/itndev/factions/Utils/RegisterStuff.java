package com.itndev.factions.Utils;

import com.itndev.factions.AdminCommands.AdminMainCommand;
import com.itndev.factions.FactionCommands.FactionCommandTabComplete;
import com.itndev.factions.FactionCommands.FactionMainCommand;
import com.itndev.factions.FactionCommands.FactionsCommands.FactionTimeOut;
import com.itndev.factions.Config.StorageDir;
import com.itndev.factions.Listener.PlayerListener;
import com.itndev.factions.Main;
import com.itndev.factions.PlaceHolder.PlaceHolderManager;
import com.itndev.factions.Storage.StorageIO.FactionStorageIOManager;
import com.itndev.factions.Storage.StorageIO.UserInfoStorageIOManager;
import com.itndev.factions.Task.AsyncTasks.SyncMap;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class RegisterStuff {


    @Deprecated
    public static void onStartup() {
        FactionStorageIOManager.restoreFactionInfo();
        UserInfoStorageIOManager.restoreUserInfo();
        FactionTimeOut.TimeoutManager();
        ValidChecker.setvalid();
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            System.out.println("Hooking into PlaceHolderAPI...");
            new PlaceHolderManager().register();
        } else {
            System.out.println("PlaceHolderAPI not Found...");
        }

        new SyncMap().SyncStorageMap();
    }

    @Deprecated
    public static void onShutdown() {
        FactionStorageIOManager.SaveFactionInfo();
        UserInfoStorageIOManager.SaveUserInfo();
    }

    public static void RegisterFactionCommands() {
        ((PluginCommand) Objects.<PluginCommand>requireNonNull(Main.getInstance().getCommand("국가"))).setExecutor(new FactionMainCommand());
        ((PluginCommand) Objects.<PluginCommand>requireNonNull(Main.getInstance().getCommand("국가"))).setTabCompleter(new FactionCommandTabComplete());
        ((PluginCommand) Objects.<PluginCommand>requireNonNull(Main.getInstance().getCommand("factionadmin"))).setExecutor(new AdminMainCommand());
    }

    public static void RegisterListener() {
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), (Plugin)Main.getInstance());
    }
}

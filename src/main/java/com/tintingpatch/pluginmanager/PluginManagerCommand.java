package com.tintingpatch.pluginmanager;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PluginManagerCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 2){
            if(args[0].equalsIgnoreCase("info")){
                if(Bukkit.getPluginManager().getPlugin(args[1])==null){
                    TextComponent textComponent = new TextComponent();
                    textComponent.setText("§c"+args[1]);
                    textComponent.setStrikethrough(true);
                    sender.spigot().sendMessage(textComponent);
                    return true;
                }
                Plugin plugin = Bukkit.getPluginManager().getPlugin(args[1]);
                sender.sendMessage("§bPlugin info: \n-  §aName: §6" + plugin.getName() + "§b-   §aDescription: §6" + plugin.getDescription().getDescription() + "\n§b-   §aEnabled: §6" + plugin.isEnabled());
                return true;
            }
            if(args[0].equalsIgnoreCase("reload")){
                if(Bukkit.getPluginManager().getPlugin(args[1])==null){
                    TextComponent textComponent = new TextComponent();
                    textComponent.setText("§c"+args[1]);
                    textComponent.setStrikethrough(true);
                    sender.spigot().sendMessage(textComponent);
                    return true;
                }
                Plugin plugin = Bukkit.getPluginManager().getPlugin(args[1]);
                Bukkit.getPluginManager().disablePlugin(plugin);
                Bukkit.getPluginManager().enablePlugin(plugin);
                sender.sendMessage("§aReloaded.");
            }
            if(args[0].equalsIgnoreCase("enable")){
                if(Bukkit.getPluginManager().getPlugin(args[1])==null){
                    TextComponent textComponent = new TextComponent();
                    textComponent.setText("§c"+args[1]);
                    textComponent.setStrikethrough(true);
                    sender.spigot().sendMessage(textComponent);
                    return true;
                }
                Plugin plugin = Bukkit.getPluginManager().getPlugin(args[1]);
                Bukkit.getPluginManager().enablePlugin(plugin);
                sender.sendMessage("§aEnabled.");
            }
            if(args[0].equalsIgnoreCase("disable")){
                if(Bukkit.getPluginManager().getPlugin(args[1])==null){
                    TextComponent textComponent = new TextComponent();
                    textComponent.setText("§c"+args[1]);
                    textComponent.setStrikethrough(true);
                    sender.spigot().sendMessage(textComponent);
                    return true;
                }
                Plugin plugin = Bukkit.getPluginManager().getPlugin(args[1]);
                Bukkit.getPluginManager().disablePlugin(plugin);
                sender.sendMessage("§cDisabled.");
            }
            return true;
        }
        if(args[0].equalsIgnoreCase("list")){
           TextComponent textComponent = new TextComponent();
           textComponent.setText("§bPlugins: \n");
            for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
                TextComponent t = new TextComponent();
                t.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("§6Show plugin info")));
                t.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/pluginmanager info " + plugin.getName()));
                t.setText("§b   -§a" + plugin.getName() + "\n");
                textComponent.addExtra(t);
            }

           sender.spigot().sendMessage(textComponent);
           return true;
        }
        sender.sendMessage("§cNot enough arguments.");


        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> list = new ArrayList();
        if(args.length == 1){
            list.add("enable");
            list.add("disable");
            list.add("info");
            list.add("reload");
            list.add("list");
            Collections.sort(list);
        }
        if(args[0].equalsIgnoreCase("enable")){
            for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
                if(!plugin.isEnabled()){
                    list.add(plugin.getName());
                }
            }
        }
        if(args[0].equalsIgnoreCase("disable")){
            for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
                if(plugin.isEnabled()){
                    list.add(plugin.getName());
                }
            }
        }
        if(args[0].equalsIgnoreCase("info")){
            for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
                list.add(plugin.getName());
            }
        }
        if(args[0].equalsIgnoreCase("reload")){
            for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
                list.add(plugin.getName());
            }
        }

        ArrayList<String> completerList = new ArrayList();
        String currentArg = args[args.length-1].toLowerCase();
        for(String s : list){
            String s1 = s.toLowerCase();
            if(s1.startsWith(currentArg)){
                completerList.add(s);
            }
        }
        return completerList;
    }
}

package main;

import main.characters.NonPlayer;
import main.characters.Player;
import main.dialogue.*;
import main.event.StartEndFight;
import main.event.StartFight;
import main.items.Armor;
import main.items.HealingItem;
import main.items.Item;
import main.items.Weapon;

public class Map {

    /**
     *Generates entire map including items, NonPlayers, Room events
     */
    public static void generateMap(Player player, NonPlayer demonLord) {
        Room villageCenter, townHall, backAlley, adventurersGuild,
                gate, field, forrest, farmLand, demonForrest, demonCastle;

        // create rooms
        villageCenter = new Room("in the center of the village");
        townHall = new Room("in the town hall");
        backAlley = new Room("in a shady back alley in the back of the village");
        adventurersGuild = new Room("in the adventurers guild");
        gate = new Room("at the gate that leads to the outside");
        field = new Room("in the field near the village");
        forrest = new Room("in the forrest");
        farmLand = new Room("at in wide stretch of farm land");
        demonForrest = new Room("in a cursed forrest near the demon lords castle");
        demonCastle = new Room("in what looks like the castle of the demon lord");

        villageCenter.addExit("north", backAlley);
        villageCenter.addExit("east", townHall);
        villageCenter.addExit("south", gate);
        villageCenter.addExit("west", adventurersGuild);

        townHall.addExit("west", villageCenter);
        backAlley.addExit("south", villageCenter);

        adventurersGuild.addExit("east", villageCenter);

        gate.addExit("north", villageCenter);
        gate.addExit("south", field);

        field.addExit("north", gate);
        field.addExit("east", farmLand);
        field.addExit("south", forrest);

        forrest.addExit("north", field);

        farmLand.addExit("west", field);
        farmLand.addExit("north", demonForrest);
        demonForrest.addExit("south", farmLand);
        demonForrest.addExit("north", demonCastle);

        demonCastle.addExit("south", demonForrest);

        // spawn items
        townHall.addItem(new Weapon("sword", "stone sword", 2, 3));
        townHall.addItem(new Item("bookshelf", "wooden bookshelf", false));
        townHall.addItem(new HealingItem("potion", "healing potion", 60));

        DialogueDefault welcome, demonlord, thatsMe, hero, guild;
        DialogueEnd noHelp, end, endRefuse;
        DialogueGive name;

        //create NonPlayer demon
        NonPlayer demon = new NonPlayer("demon", "serves demon lord", true);
        demon.giveItem(new HealingItem("potion", "potion of healing", 60));
        demon.giveItem(new HealingItem("potion", "potion of healing", 60));
        demon.setWeapon(new Weapon("claw", "demon's claws", 6, 2));
        demonForrest.addCharacter(demon);
        demonForrest.setEvent(new StartFight("The demon defending the castle is attacking you!", demon));
        //

        //add demon lord

        demonCastle.addCharacter(demonLord);
        demonCastle.setEvent(new StartEndFight("The demon lord is attacking you!", demonLord));
        //

        //create NonPlayer thief
        NonPlayer thief = new NonPlayer("thief", "Potion thief");
        thief.giveItem(new HealingItem("potion", "potion of healing", 60));
        thief.giveItem(new HealingItem("potion", "potion of healing", 60));
        thief.giveItem(new HealingItem("potion", "potion of healing", 60));

        backAlley.setEvent(new StartFight("You are getting robbed!!!", thief));
        // create NonPlayer alan

        //dialogue
        welcome = new DialogueDefault("Hello there sir! Can I help you with something?");  // -> demonlord, noHelp
        demonlord = new DialogueDefault("""
                Shh.. don't say that name out loud, that demon has been terrorizing the town for years.
                The only hope we have left for the hero defeat him. Rumor is he showed up in town recently!"""); // -> thatsMe, hero
        noHelp = new DialogueEnd("Have a good day Sir!", welcome);
        thatsMe = new DialogueDefault(""" 
                Haha, surely you're joking..
                Wait a minute.. You look EXACTLY like they described him!! What's your name sir?
                """); // -> name
        hero = new DialogueDefault("""
                Yes, the hero the only one strong enough to defeat the demon lord! Wait a minute..
                you look an awful lot like him.."""); // -> name

        Item sword = new Weapon("sword", "iron sword", 3, 2);
        name = new DialogueGive("""
                It's really you __PLAYER_NAME__! I can't believe the hero finally showed up!
                Please, let met give you this __ITEM_NAME__ it's not much but I want to help out wherever I can!
                """, sword);
        guild = new DialogueDefault("You should check out the adventurers guild. I'm sure they can help you out!");
        end= new DialogueEnd("""
                You are our only hope __PLAYER_NAME__! You will need better equipment if you want to defeat the demon lord.
                I would take a look at the adventurers guild to the west im sure they could help you out!""", guild);
        endRefuse = new DialogueEnd("""
                No worries, I guess a real hero wouldn't need an iron sword. Although you do need better equipment,
                I would take a look at the adventurers guild to the west im sure they could help you out!""", guild);

        welcome.addOption("Have you heard about the demon lord...", demonlord);
        welcome.addOption("Not now!", noHelp);
        demonlord.addOption("That's me!!", thatsMe);
        demonlord.addOption("Hero?", hero);
        thatsMe.addOption("Give name.", name);
        hero.addOption("Really??", name);
        name.setTakeResponse(end);
        name.setRefuseResponse(endRefuse);

        //alan
        NonPlayer alan = new NonPlayer("Alan", "Commoner");
        alan.giveItem(sword);
        alan.setColor(96);
        alan.setCurrentDialogue(welcome);
        villageCenter.addCharacter(alan);
        //

        //create NonPlayer goblin
        Armor cloth = new Armor("cloth", "leather cloth", 1);
        NonPlayer goblin = new NonPlayer("goblin", "fighter", true);
        goblin.setArmor(cloth);
        //

        //create NonPlayer werewolf
        Weapon claw = new Weapon("claw", "werewolf claw", 4, 1);
        Armor werewolfSkin = new Armor("skin", "werewolf skin", 2);
        NonPlayer werewolf = new NonPlayer("werewolf", "fighter", true);
        werewolf.setWeapon(claw);
        werewolf.setArmor(werewolfSkin);

        // create NonPlayer giles
        NonPlayer giles = new NonPlayer("Giles", "Guild Master");
        giles.setColor(33);

        // first set of dialogue
        DialogueDefault greeting, goblinQuest, goblinQuest2;
        DialogueReceive goblinWait;

        greeting = new DialogueDefault("""
                Hi there sir how can I help you?!""");
        greeting.addOption("Not now!", new DialogueEnd("Alright, don't hesitate to talk to me if you need me!", greeting)); // goes back to greeting

        goblinQuest = new DialogueDefault("""
                I might be able to help you out but I will need something in return,
                if you bring me a leather cloth I could give you some armor in return.
                """);
        goblinQuest.addOption("Later..", new DialogueEnd("Oh, okay you can always come back to me!", goblinQuest));

        goblinQuest2 = new DialogueDefault("You can get cloth by slaying a goblin. You should be able to find one at the field near the village gate.");
        goblinQuest2.setSpawnAction(new SpawnAction(field, goblin)); // spawn goblin in field

        goblinWait = new DialogueReceive("""
                Did you manage to find the __ITEM_NAME__ for me?""", cloth);
        goblinWait.setNoItemResponse(new DialogueEnd("""
        It seems like you haven't found the cloth yet. You should be able to get it at the field by slaying a goblin!""", null));

        greeting.addOption("Better equipment..", goblinQuest);
        goblinQuest.addOption("Tell me more..", goblinQuest2);
        goblinQuest2.addOption("Alright I'll be back..", new DialogueEnd("Be careful out there sir!",goblinWait));
        //

        // second set of dialogue
        DialogueGive goblinGive, potionGive;
        DialogueDefault staredown, werewolfQuest, werewolfQuest2, werewolfQuest3;
        DialogueReceive werewolfWait;

        Armor armor = new Armor("armor", "iron armor", 5);
        goblinGive = new DialogueGive("""
        It seems like you managed to slay the goblin! You gave me a fine cloth, Here take this __ITEM_NAME__""", armor);
        goblinWait.setHasItemResponse(goblinGive); // link to previous dialogue

        goblinGive.setRefuseResponse(new DialogueEnd("Alright then, you can come pick it up any time!", goblinGive));

        Item potion = new HealingItem("potion", "potion of healing", 60);
        potionGive = new DialogueGive("Since you took some damage, here have this potion!", potion);
        staredown = new DialogueDefault("Oh okay, I guess you don't need it...");


        werewolfQuest = new DialogueDefault("""
        Since you brought me such fine cloth I wouldn't mind providing you with a better weapon for a small price, what do you say?""");
        werewolfQuest.addOption("Maybe later..", new DialogueEnd("Alright, don't hesitate to come back!", werewolfQuest));

        werewolfQuest2 = new DialogueDefault("If you can give me the claw of a werewolf, I will give you my best sword, What do you think?");
        werewolfQuest3 = new DialogueDefault("Alright! You should be able to find a werewolf in the forrest near the field.");
        werewolfQuest3.setSpawnAction(new SpawnAction(forrest, werewolf)); // spawn werewolf

        werewolfWait = new DialogueReceive("Have you found the __ITEM_NAME__??", claw);
        werewolfWait.setNoItemResponse(new DialogueEnd("Come back when you got it you should be able to find a werewolf in the forrest near the field.", werewolfWait));

        goblinGive.setTakeResponse(potionGive);
        potionGive.setTakeResponse(werewolfQuest);
        potionGive.setRefuseResponse(staredown);
        staredown.addOption("...", werewolfQuest);

        werewolfQuest.addOption("Sure!", werewolfQuest2);
        werewolfQuest2.addOption("Tell me more..", werewolfQuest3);
        werewolfQuest3.addOption("Ill be back.", new DialogueEnd("Success!!", werewolfWait));

        // third section
        DialogueGive werewolfGive;
        DialogueDefault good;

        Weapon sword2 = new Weapon("sword", "titanium sword", 8, 9);
        werewolfGive = new DialogueGive("That was fast! Here take this __ITEM_NAME__!", sword2);
        werewolfGive.setRefuseResponse(new DialogueEnd("You can always come pick it up later I guess..", werewolfGive));
        werewolfWait.setHasItemResponse(werewolfGive);

        good = new DialogueDefault("You got some really good equipment now it's almost like you could defeat the demon lord...");
        good.addOption("Really??", new DialogueEnd("Don't take what I said there too seriously, only the hero could defeat him after all..", good));
        werewolfGive.setTakeResponse(good);

        giles.setCurrentDialogue(greeting);
        adventurersGuild.addCharacter(giles);
        //
        giles.giveItem(armor);
        giles.giveItem(sword2);
        giles.giveItem(potion);
        //

        player.setCurrentRoom(villageCenter);
    }

    public static NonPlayer generateEndBoss() {
        NonPlayer demonLord = new NonPlayer("demonLord", "lord of demons", true, 200);
        demonLord.setWeapon(new Weapon("sword", "demon lord's sword", 6, 4));
        demonLord.setArmor(new Armor("armor", "demon lord's armor", 2));
        demonLord.setColor(31);
        return demonLord;
    }
}
package main;

import Commands.Command;
import main.characters.NonPlayer;
import main.characters.Player;
import main.dialogue.*;
import main.event.StartFight;
import main.items.Armor;
import main.items.HealingItem;
import main.items.Item;
import main.items.Weapon;

public class Game {
    private final CommandParse parser;
    private final Player player;

    public Game() {
        parser = new CommandParse(CommandParse.Type.WALK);
        player = new Player(askName());
        player.setColor(36);
        generateMap();
        printWelcome();
    }

    private String askName() {
        boolean nameRight = false;
        String name = "";

        while(!nameRight) {
            System.out.println("What do you want to be called?");
            name = parser.getInput();
            if(name.contains(" ")) {
                System.out.println("Your name cannot contain spaces!");
                continue;
            }
            nameRight = OptionParse.twoChoice(parser, "Are you sure you want to be called " + name + "?"
                                        , "yes", "no");
        }
        return name; // name will always be printed in color
    }

    private void generateMap() {
        Room villageCenter, townHall, backAlley, adventurersGuild,
                gate, field, forrest, farmLand, demonForrest, demonCastle;

        // create rooms
        villageCenter = new Room("in the center of the village");
        townHall = new Room("in the town hall");
        backAlley = new Room("in a shady back alley in the back of the village");
        adventurersGuild = new Room("in he adventurers guild");
        gate = new Room("at the gate that leads to the outside");
        field = new Room("in the field near the village");
        forrest = new Room("in the forrest");
        farmLand = new Room("at a wide stretch of farm land");
        demonForrest = new Room("in a cursed forrest near the demon lords castle");
        demonCastle = new Room("in what looks like the castle of the demon lord");

        villageCenter.addExit("north", backAlley);
        villageCenter.addExit("east", townHall);
        villageCenter.addExit("south", gate);
        villageCenter.addExit("west", adventurersGuild);

        townHall.addExit("west", villageCenter);
        backAlley.addExit("south", villageCenter);
        NonPlayer bob = new NonPlayer("Bob", "Thief");
        bob.giveItem(new HealingItem("potion", "healing potion", 60));

        backAlley.setEvent(new StartFight("A man wants to fight you!", bob));
        adventurersGuild.addExit("east", villageCenter);

        gate.addExit("north", villageCenter);
        gate.addExit("south", field);

        field.addExit("north", gate);
        field.addExit("east", farmLand);
        field.addExit("south", forrest);

        forrest.addExit("north", field);

        farmLand.addExit("west", field);
        farmLand.addExit("north", demonForrest);

        demonCastle.addExit("south", farmLand);

        // spawn items
        townHall.addItem(new Weapon("sword", "stone sword", 2, 3));
        townHall.addItem(new Item("bookshelf", "wooden bookshelf", false));

        DialogueDefault welcome, demonlord, thatsMe, hero, guild;
        DialogueEnd noHelp, end, endRefuse;
        DialogueGive name;

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

        Item sword = new Weapon("sword", "iron sword", 3, 1);
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
        Armor cloth = new Armor("axe", "stone axe", 1);
        NonPlayer goblin = new NonPlayer("goblin", "fighter", true);
        goblin.giveItem(cloth);
        //

        // create NonPlayer giles
        NonPlayer giles = new NonPlayer("Giles", "Guild Master");
        giles.setColor(33);
        // first set of dialogue
        DialogueDefault greeting, goblinQuest, goblinQuest2;
        DialogueReceive goblinAsk;

        greeting = new DialogueDefault("""
                Hi there sir how can I help you?!""");
        greeting.addOption("Not now!", new DialogueEnd("Alright, don't hesitate to talk to me if you need me!", greeting)); // goes back to greeting

        goblinQuest = new DialogueDefault("""
                I might be able to help you out but I will need something in return, if you bring me some a leather I could give you some armor in return.
                """);
        goblinQuest2 = new DialogueDefault("You can get cloth by slaying a goblin. You should be able to find one at the field near the village gate.");
        goblinQuest2.addOption("Later..", new DialogueEnd("Oh, okay you can always come back to me!", goblinQuest));
        goblinQuest2.setSpawnAction(new SpawnAction(field, goblin)); // spawn goblin in field

        goblinAsk = new DialogueReceive("""
                Did you manage to find the __ITEM_NAME__ for me? You should be able to get one at the field by slaying a goblin!""", cloth);
        goblinAsk.setNoItemResponse(new DialogueEnd("It seems like you haven't found the item yet..", null));
        //

        giles.setCurrentDialogue(greeting);
        adventurersGuild.addCharacter(giles);

        player.setCurrentRoom(villageCenter);
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul, " + player.getDisplayName() + "!");
        System.out.println();
        System.out.println("For hundreds of years an evil demon lord has been terrorising the people.");
        System.out.println("But don't worry, someone by the name of " + player.getName() + " can stop him. Or so the legend goes...");
        System.out.println("There is only one problem, you have no clue how to defeat a demon lord.");
        System.out.println("But im sure you will figure it out soon enough!, you might find some leads in the town.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();
    }

    public void play() {
        boolean finished = false;

        while(!finished) {
            Command c = parser.getCommand();
            finished = c.execute(player);
        }

        if(player.isDead()) { // game stopped because player died
            playerDied();
        }
    }

    private void printLocationInfo() {
        System.out.println(player.getInfo());
        System.out.println();
    }

    private void playerDied() {
        System.out.println("You died!!");
        if(OptionParse.twoChoice(parser, "Do you want to restart?", "restart", "quit")) {
            Game g = new Game();
            g.play(); // run game again
        }
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.play();
    }
}

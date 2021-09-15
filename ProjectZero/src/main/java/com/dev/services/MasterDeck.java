package com.dev.services;

import com.dev.models.Cards;
import com.dev.models.CreateDeck;
import com.dev.models.Deck;
import com.dev.models.User;
import com.dev.repositories.*;

import java.util.List;
import java.util.Scanner;

public class MasterDeck {

    static CardsRepo card = new CardsRepo();
    static UserDeckRepo deck = new UserDeckRepo();
    static CreateDeckRepo create_deck = new CreateDeckRepo();
    static UserRepo user = new UserRepo();
    static MasterDeckRepo master = new MasterDeckRepo();


    public void deckMaster(String username){

        Scanner scanner = new Scanner(System.in);
        Scanner option = new Scanner(System.in);
        boolean running = true;

        while (running){

            System.out.println("\nWhat would you like to do " + username + "?");
            System.out.println("(1) View Card Database.");
            System.out.println("(2) Add a New Card to the Database.");
            System.out.println("(3) Remove a Card from the Database.");
            System.out.println("(4) View Decks.");
            System.out.println("(5) Create Deck.");
            System.out.println("(6) View Selected Deck.");
            System.out.println("(7) Add Card to Deck.");
            System.out.println("(8) Remove Card From Deck.");
            System.out.println("(9) Empty Deck.");
            System.out.println("(10) Drop Deck.");
            System.out.println("(0) Exit Duel Room");

            String result = scanner.nextLine();

            switch (result){
                case "1":

                    // View Card Database

                    List<Cards> c = card.getAll();
                    for (Cards card : c) {
                        System.out.println(card);
                    }
                    break;
                case "2":

                    // Add card to database

                    System.out.println("\nInput data of the card.\n");
                    System.out.println("Card name: ");
                    String name = option.nextLine();
                    System.out.println("Card Race: ");
                    String race = option.nextLine();
                    System.out.println("Card Type: ");
                    String type = option.nextLine();

                    Cards newCard = new Cards(name, race, type);
                    card.add(newCard);
                    System.out.println("\nYou have added a card, Master Kaiba.");
                    break;

                case "3":

                    // Remove card from Database
                    List<Cards> ListCard = card.getAll();
                    for (Cards card : ListCard){
                        System.out.println(card);
                    }

                    System.out.println("Select an ID of a card you want to remove from the DB:");
                    int removeCard = option.nextInt();
                    System.out.println("You have removed " + card.getById(removeCard));
                    card.delete(removeCard);
                    break;

                case "4":

                    // View User Decks
                    // Statement to print out all the user's decks
                    List<CreateDeck> ud = create_deck.getAll(username);
                    for (CreateDeck create_deck : ud){
                        System.out.println(create_deck);
                    }
                    break;

                case "5":

                    // Create a new deck

                    System.out.println("Create a Deck Name: ");
                    String deck_name = option.nextLine();
                    create_deck.addDeck(username, deck_name);
                    create_deck.createDeck(deck_name);


                    break;

                case "6":

                    // Select and view the contents of a deck

                    System.out.println("Pick a deck by name.\n");
                    List<CreateDeck> allDs = create_deck.getAll(username);

                    for (CreateDeck create_deck : allDs) {
                        System.out.println(create_deck);
                    }

                    String deckList = option.nextLine();

                    List<Deck> ListDeck = deck.getDeckList(deckList);

                    System.out.println("\nHere is your Deck List.\n");
                    for (Deck deck : ListDeck){
                        System.out.println(deck);
                    }
                    break;

                case "7":

                    // Add card to a selected Deck

                    List<CreateDeck> allDecks = create_deck.getAll(username);
                    for (CreateDeck create_deck : allDecks) {
                        System.out.println(create_deck);
                    }

                    System.out.println("\nPick a deck by name that you want to add cards to: ");

                    String nameOfDeck = option.nextLine();

                    List<Cards> allC = card.getAll();
                    for (Cards card : allC) {
                        System.out.println(card);
                    }

                    System.out.println("\nBy ID, select the card you want to add: ");

                    Integer cardId = option.nextInt();

                    create_deck.addCardToDeck(nameOfDeck, cardId);
                    System.out.println("You have added " + card.getById(cardId) + " to your " + nameOfDeck + " deck.");
                    break;

                case "8":

                    // Remove cards from a selected Deck
                    List<CreateDeck> allDecks2 = create_deck.getAll(username);
                    for (CreateDeck create_deck : allDecks2) {
                        System.out.println(create_deck);
                    }

                    System.out.println("\nSelect the deck you want to remove a card from:");
                    String userD = option.nextLine();

                    List<Deck> ListD = deck.getDeckList(userD);

                    try {
                        for (Deck deck : ListD) {
                            System.out.println(deck);
                        }
                        System.out.println("\nSelect the ID of the card you want to remove:");
                        int deleteId = Integer.parseInt(option.nextLine());
                        create_deck.removeCardFromDeck(userD, deleteId);
                        System.out.println("You have removed " + card.getById(deleteId) + " to your " + deleteId + " deck.");
                    } catch (NumberFormatException e){
                        System.out.println("That's not a number you 3rd Rate Duelist!");
                    }
                    break;

                case "9":

                    // Clear a selected Deck of its contents.

                    System.out.println("Select a deck you want to clear?\n");
                    List<CreateDeck> alDecks = create_deck.getAll(username);

                    for (CreateDeck create_deck : alDecks) {
                        System.out.println(create_deck);
                    }

                    String selectDeck = option.nextLine();

                    System.out.println("Are you sure you want to remove all of your cards? (Y/N)\n");
                    String choice = option.nextLine().toLowerCase();
                    if (choice.equals("y")){
                        create_deck.removeAllCards(selectDeck);
                    }
                    System.out.println("This deck...is clear. (Ace Ventura Impression");
                    break;

                case "10":

                    // Drop a user's deck
                    System.out.println("\nSelect a user whose decks you want to view by username:");
                    List<User> allUsers = user.getAll();

                    for (User user : allUsers){
                        System.out.println(user);
                    }
                    String scanUser = option.nextLine();

                    List<CreateDeck> userRepo = create_deck.getAll(scanUser);
                    for (CreateDeck create_deck : userRepo){
                        System.out.println(create_deck);
                    }

                    String deckSelect = option.nextLine();

                    master.dropDeck(deckSelect);
                    master.deleteDeck(scanUser, deckSelect);

                    System.out.println("You have drop " + deckSelect + " from user " + scanUser);

                    break;

                case "0":
                    System.out.println("\nNext Time!");
                    running = false;
                    break;

                default:
                    System.out.println("INVALID!");
            }
        }

        scanner.close();

    }

}

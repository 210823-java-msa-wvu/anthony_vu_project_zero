package com.dev.services;

import com.dev.models.Cards;
import com.dev.models.CreateDeck;
import com.dev.models.Deck;
import com.dev.repositories.CardsRepo;
import com.dev.repositories.CreateDeckRepo;
import com.dev.repositories.UserDeckRepo;

import java.util.List;
import java.util.Scanner;

public class UserDeck {

    static CardsRepo card = new CardsRepo();
    static UserDeckRepo deck = new UserDeckRepo();
    static CreateDeckRepo create_deck = new CreateDeckRepo();


    public void deck(String username){

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running){

            System.out.println("\nWhat would you like to do " + username + "?\n");
            System.out.println("(1) View Card Database.");
            System.out.println("(2) View Decks.");
            System.out.println("(3) Create Deck.");
            System.out.println("(4) View Selected Deck.");
            System.out.println("(5) Add Card to Deck.");
            System.out.println("(6) Remove Card From Deck.");
            System.out.println("(7) Empty Deck.");
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

                    // View User Decks

                    System.out.println(username);
                    // Statement to print out all the user's decks
                    List<CreateDeck> ud = create_deck.getAll(username);
                    for (CreateDeck create_deck : ud){
                        System.out.println(create_deck);
                    }
                    break;

                case "3":

                    // Create a new deck

                    System.out.println("Create a Deck Name: ");
                    Scanner deckName = new Scanner(System.in);
                    String deck_name = deckName.nextLine();
                    create_deck.addDeck(username, deck_name);
                    create_deck.createDeck(deck_name);


                    break;

                case "4":

                    // Select and view the contents of a deck

                    System.out.println("Pick a deck by name.\n");
                    List<CreateDeck> allDs = create_deck.getAll(username);

                    for (CreateDeck create_deck : allDs) {
                        System.out.println(create_deck);
                    }

                    Scanner nameD = new Scanner(System.in);
                    String deckList = nameD.nextLine();

                    List<Deck> ListDeck = deck.getDeckList(deckList);

                    System.out.println("\nHere is your Deck List.\n");

                    for (Deck deck : ListDeck){
                        System.out.println(deck);
                    }

                    break;
                case "5":

                    // Add card to a selected Deck

                    List<CreateDeck> allDecks = create_deck.getAll(username);
                    for (CreateDeck create_deck : allDecks) {
                        System.out.println(create_deck);
                    }

                    System.out.println("\nPick a deck by name that you want to add cards to: ");

                    Scanner nameOfDeck = new Scanner(System.in);
                    String name_deck = nameOfDeck.nextLine();

                    List<Cards> allC = card.getAll();
                    for (Cards card : allC) {
                        System.out.println(card);
                    }

                    System.out.println("\nBy ID, select the card you want to add: ");

                    Scanner pickId = new Scanner(System.in);
                    Integer cardId = pickId.nextInt();

                    create_deck.addCardToDeck(name_deck, cardId);
                    System.out.println("You have added " + card.getById(cardId) + " to your " + name_deck + " deck.");
                    break;

                case "6":

                    // Remove cards from a selected Deck
                    List<CreateDeck> allDecks2 = create_deck.getAll(username);
                    for (CreateDeck create_deck : allDecks2) {
                        System.out.println(create_deck);
                    }

                    System.out.println("\nSelect the deck you want to remove a card from:");
                    Scanner uD = new Scanner(System.in);
                    String userD = uD.nextLine();

                    List<Deck> ListD = deck.getDeckList(userD);

                    try {
                        for (Deck deck : ListD) {
                            System.out.println(deck);
                        }
                        System.out.println("\nSelect the ID of the card you want to remove:");
                        Scanner dCard = new Scanner(System.in);
                        int deleteId = Integer.parseInt(dCard.nextLine());
                        create_deck.removeCardFromDeck(userD, deleteId);
                        System.out.println("You have removed " + card.getById(deleteId) + " to your " + uD + " deck.");
                    } catch (NumberFormatException e){
                        System.out.println("That's not a number you 3rd Rate Duelist!");
                    }
                    break;

                case "7":

                    // Clear a selected Deck of its contents.

                    System.out.println("Select a deck you want to clear?\n");
                    List<CreateDeck> alDecks = create_deck.getAll(username);

                    for (CreateDeck create_deck : alDecks) {
                        System.out.println(create_deck);
                    }

                    Scanner sDeck = new Scanner((System.in));
                    String selectDeck = sDeck.nextLine();

                    System.out.println("Are you sure you want to remove all of your cards? (Y/N)\n");
                    Scanner yesNo = new Scanner(System.in);
                    String choice = yesNo.nextLine().toLowerCase();
                    if (choice.equals("y")){
                        create_deck.removeAllCards(selectDeck);
                    }
                    System.out.println("This deck...is clear. (Ace Ventura Impression");
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

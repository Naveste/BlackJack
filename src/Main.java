import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Main {

    public static int dealCard(){

        ArrayList<Integer> cards = new ArrayList<>() {{
            add(11);
            add(2);
            add(3);
            add(4);
            add(5);
            add(6);
            add(7);
            add(8);
            add(9);
            add(10);
            add(10);
            add(10);
            add(10);
        }};

        return cards.get(new Random().nextInt(cards.size()));
    }

    public static void printDeck(ArrayList<Integer> card){
        for (int i: card){
            System.out.print(i + " ");
        }
        System.out.println("");
    }

    public static int deckSum(ArrayList<Integer> card){
        int cardSum = 0;

        for (int i: card){
            cardSum += i;
        }

        if (card.contains(11) && card.contains(10) && card.size() == 2){
            System.out.println("Blackjack!");
            return 0;
        }

        int sum = 0;
        for (int number : card){
            sum += number;
        }

        if (card.contains(11) && sum > 21 ){
            card.remove(Integer.valueOf(11));
            card.add(1);
            System.out.println("Ace switched to 1!");
        }

        return cardSum;
    }

    public static void chooseWinner(ArrayList<Integer> player, ArrayList<Integer> comp)
    {

        if (deckSum(player) == deckSum(comp))
        {
            System.out.println("It's a draw!");
        }
        else if (deckSum(comp) == 0)
        {
            System.out.println("You lose. Computer has blackjack.");
        }
        else if (deckSum(player) == 0)
        {
            System.out.println("You win with a blackjack.");
        }
        else if (deckSum(player) > 21)
        {
            System.out.println("You went over 21. You lose!");
        }
        else if (deckSum(comp) > 21)
        {
            System.out.println("Comp went over 21. You win!");
        }
        else if (deckSum(player) > deckSum(comp))
        {
            System.out.println("You win with highest score.");
        }
        else
        {
            System.out.println("Comp wins with highest score.");
        }

    }

    public static void dealTwoCards(ArrayList<Integer> entity){
        for (int i = 0; i < 2; i++){
            entity.add(dealCard());
        }
    }

    public static void startingDeck(ArrayList<Integer> player, ArrayList<Integer> comp){
        dealTwoCards(player);
        dealTwoCards(comp);
    }

    public static void game(){
        ArrayList<Integer> player = new ArrayList<>();
        ArrayList<Integer> dealer = new ArrayList<>();

        startingDeck(player, dealer);

        System.out.println("Comp sum: " + deckSum(dealer));

        System.out.println("");

        System.out.println("Player sum: " + deckSum(player));

        while(true){

            System.out.println("");

            printDeck(dealer);

            System.out.println("");

            printDeck(player);

            if (deckSum(dealer) == 0 || deckSum(player) == 0 || deckSum(player) > 21){
                break;
            } else {
                System.out.println("Do you want to draw another card?" + deckSum(player));
                Scanner scanner = new Scanner(System.in);
                char choice = scanner.next().charAt(0);
                if (choice == 'y'){
                    player.add(dealCard());
                    System.out.println("Player sum " + deckSum(player));
                } else if (choice == 'n') {
                    break;
                }

            }
        }

        while(deckSum(dealer) != 0 && deckSum(dealer) < 17){
            dealer.add(dealCard());
        }
        System.out.println("Results:");
        System.out.println("Player cards: ");
        printDeck(player);

        if (deckSum(player) == 0){
            System.out.println("Player sum: blackjack!");
        } else {
            System.out.println("Player sum: " + deckSum(player));
        }
        System.out.println("\nComp cards: ");
        printDeck(dealer);

        if (deckSum(dealer) == 0){
            System.out.println("Comp sum: blackjack!");
        } else {
            System.out.println("Comp sum: " + deckSum(dealer));
        }

        System.out.println("");
        chooseWinner(player, dealer);
    }
    public static void main(String[] args) {

        game();

        while(true){
            System.out.println("Do you want to play again?");
            Scanner scanner = new Scanner(System.in);
            char choice = scanner.next().charAt(0);
            if (choice == 'y'){
                game();
            } else if (choice == 'n') {
                System.out.println("Bye");
                break;
            }
        }

    }

}
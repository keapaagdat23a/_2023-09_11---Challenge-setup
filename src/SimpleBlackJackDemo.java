import java.util.Random;
import java.util.Scanner;

public class SimpleBlackJackDemo {
  private boolean playerStillAlive;
  private boolean dealerStillAlive;
  private int playerSum;
  private int dealerSum;

  public void initVariables() {
    playerStillAlive = true;
    dealerStillAlive = true;
    playerSum = 21;
    dealerSum = 0;
  }

  public static void main(String[] args) {
    new SimpleBlackJackDemo().run();
  }

  private void run() {
    printWelcomeMessage();
    initVariables();
    do {
      userPlays();
      if (askDealertoProceed())
        dealerPlays();
    } while (playerStillAlive && dealerStillAlive);
    printResult();
  }

  private boolean askDealertoProceed() {
    // NOTE: This is a bit different than the description to better reflect actual rules
    if ((dealerSum < playerSum) && playerStillAlive) {
      System.out.println("Dealer says HIT ME!");
      return true;
    }
    else {
      System.out.println("Dealer says STAND!");
      return false;
    }
  }

  private void printWelcomeMessage() {
    System.out.println("\nWELCOME TO SIMPLE BLACK JACK\n");
  }

  private void dealerPlays() {
    int card = drawCard();
    dealerSum += card;
    if (dealerSum > 21) {
      dealerStillAlive = false;
      System.out.println("Dealer lost.");
    } else
      System.out.println("Dealer sum: " + dealerSum);
  }

  private void printResult() {
    if (playerSum > dealerSum)
      System.out.printf("Player won by %d-%d.", playerSum, dealerSum);
    else if (dealerSum > playerSum)
      System.out.printf("Dealer won by %d-%d.", dealerSum, playerSum);
    else
      System.out.printf("It's a tie by %d-%d.", dealerSum, playerSum);
  }

  public char askUserHitOrStand() {
    Scanner in = new Scanner(System.in);
    System.out.print("HIT or STAND (h/s)? ");
    char reply = in.nextLine().toUpperCase().charAt(0);
    return reply;
  }

  private void userPlays() {
    if (askUserHitOrStand() == 'H') {
      int card = drawCard();
      playerSum += card;
      if (playerSum > 21) {
        System.out.println("Player above 21: " + playerSum);
        playerStillAlive = false;
      } else
        System.out.println("Sum: " + playerSum);
    } else
      System.out.println("User stands.");
  }

  private int drawCard() {
    Random random = new Random();
    int card = random.nextInt(13) + 1;
    System.out.println("You drew a " + card);
    if (card > 10) {
      card = 10;
      System.out.println("The value is " + card);
    }
    return card;
  }
}
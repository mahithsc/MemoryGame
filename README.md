# MemoryGame
-We are going to remove the images from the ArrayList so we can run this all in a loop until there are no Strings left in the ArrayList using the size() method
-We are going to have a count variable and going to be checking the number of button presses each turn in order to tell when to flip the next card over
-The start button wilk simply add all of the images back into random order by calling the method again
-We can just count the matches by using .equals() and by checking to see if the strings are the same or match up after every two button presses
-A lot of this will be written under the ActionPerformed method allowing us to check the button presses everytime the button is pressed than we can set the variable to zero so we can start checking the number of button presses again
-We can have a greyed out image which sets a certain image icon to a simplistic grey image which is the background or just remove the card as  awhole and set it to the background color of the page or the button
-The stop button could potentially just remove all of the images from the ArrayList, so that they can be reset again using the same method or similar code to the start button allowing us to easily reset the game and start again once again with the start button


Problems That We Had and How We Solved Them
1. Issue: You can click the same button twice, and the cimputer thinks that there is a math 
  - We thoght about using recursion by creating an if statement when the user ckicks the same card twice, we call the methid for the user to pick          a card(we didnt go with this solution)
  - Solution: 
  
2. figuring out the logic issue with flipping the cards: once you clicked the card the 

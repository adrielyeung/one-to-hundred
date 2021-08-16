# one-to-hundred
Android game app where each player (of 2-9 players) takes turns to guess a "bomb" number in the range 1-100. Built with Java and XML layout files on Android Studio.

## What's new - 

## How to use
1. Clone the repository into a local directory.
2. Open the project directory in Android Studio.

## Description
The app consists of 5 Activities and 3 Fragments, namely:

**Activities**
1. MainActivity
2. InstructionActivity
3. StartActivity
4. GameActivity
5. EndActivity

(And a NoTitleActivity base class for all above activities, which removes the app title bar on screen)

**Fragments**
1. ConfirmFragment
2. EnterNameFragment
3. EnterNumberFragment

### 1. MainActivity
<img src="https://github.com/adrielyeung/one-to-hundred/blob/main/images/main.PNG" alt="MainActivity" width="70%" height="70%">

First point of call when user opens the app. Links to StartActivity to start game, and InstructionActivity to display instructions.

### 2. InstructionActivity
<img src="https://github.com/adrielyeung/one-to-hundred/blob/main/images/instruction.PNG" alt="InstructionActivity" width="70%" height="70%">

A FrameLayout where users can use back and forward buttons to scroll through the instructions.
The "back" button may be used to return to the MainActivity.

### 3. StartActivity
Here the 3 Fragments are used in a ViewPager for the setting up of the game:
1. EnterNumberFragment is displayed first, to set up the number of players.
<img src="https://github.com/adrielyeung/one-to-hundred/blob/main/images/start.PNG" alt="StartActivity - EnterNumberFragment" width="70%" height="70%">
2. ConfirmFragment is displayed to prompt confirmation.
<img src="https://github.com/adrielyeung/one-to-hundred/blob/main/images/confirm.PNG" alt="StartActivity - ConfirmFragment" width="70%" height="70%">
3. EnterNameFragment is displayed to allow each player to input their names, with a dialog to confirm afterwards.
<img src="https://github.com/adrielyeung/one-to-hundred/blob/main/images/name.PNG" alt="StartActivity - EnterNameFragment" width="70%" height="70%">
<img src="https://github.com/adrielyeung/one-to-hundred/blob/main/images/confirm_name_prompt.PNG" alt="StartActivity - EnterNameFragment Prompt" width="70%" height="70%">
The "back" button may be used to return to the MainActivity.

### 4. GameActivity
2 Fragments are used in a ViewPager:
1. EnterNumberFragment is displayed for the "Judge" to select the "bomb" number, and for each player to take turns guessing.
<img src="https://github.com/adrielyeung/one-to-hundred/blob/main/images/set_bomb.PNG" alt="GameActivity - EnterNumberFragment (Set up bomb)" width="70%" height="70%">
<img src="https://github.com/adrielyeung/one-to-hundred/blob/main/images/game.PNG" alt="GameActivity - EnterNumberFragment (In Game)" width="70%" height="70%">
2. ConfirmFragment is displayed to prompt confirmation.

The "back" button may be used to return to the MainActivity.

### 5. EndActivity
<img src="https://github.com/adrielyeung/one-to-hundred/blob/main/images/end.PNG" alt="EndActivity" width="70%" height="70%">
This is triggered when a player guesses the "bomb" number.

## Future developments
1. Change behaviour of back button during setup, to allow returning to last step.
2. Automatic trigger of bomb when only 1 number left for guessing.
3. Audio when bomb triggered.
4. Use a ViewPager for InstructionActivity, where user can scroll through instruction instead of using buttons.

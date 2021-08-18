# one-to-hundred
Android game app where each player (of 2-9 players) takes turns to guess a "bomb" number in the range 1-100. Built with Java and XML layout files on Android Studio.

## What's new - 18/8/2021
1. Use a ViewPager for InstructionActivity, where user can scroll through instruction instead of using buttons.

## How to use
1. Clone the repository into a local directory.
2. Open the project directory in Android Studio.

## Description
The app consists of 8 Activities and 4 Fragments, namely:

**Activities**
1. NoTitleActivity (base class)
2. DisplayActivity (base class, abstract)
3. InGameActivity (base class, abstract)
4. MainActivity
5. InstructionActivity
6. StartActivity
7. GameActivity
8. EndActivity

**Fragments**
1. ConfirmFragment
2. DisplayFragment
3. EnterNameFragment
4. EnterNumberFragment

### 1. NoTitleActivity extends androidx.appcompat.app.AppCompatActivity
Base class for all above activities, which removes the app title bar on screen.

### 2. DisplayActivity extends NoTitleActivity
Provides a template for scrolling through pages of text (DisplayFragments), with a title bar on top.

Abstract methods: ```setDisplayTitle()``` sets text on title bar, ```insertDisplay()``` inserts the list of text to display in sequence.

### 3. InGameActivity extends NoTitleActivity
Provides a blank template for switching between different fragments (EnterNameFragment, EnterNumberFragment and ConfirmFragment).

Abstract methods: ```setupViewPager()``` sets which Fragments to use.

### 4. MainActivity extends NoTitleActivity
<img src="https://github.com/adrielyeung/one-to-hundred/blob/main/images/main.PNG" alt="MainActivity" width="25%" height="25%">

First point of call when user opens the app. Links to StartActivity to start game, and InstructionActivity to display instructions.

### 5. InstructionActivity extends DisplayActivity
<img src="https://github.com/adrielyeung/one-to-hundred/blob/main/images/instruction.PNG" alt="InstructionActivity" width="25%" height="25%">

A FrameLayout where users can use back and forward buttons to scroll through the instructions.
The "back" button may be used to return to the MainActivity.

### 6. StartActivity extends InGameActivity
Here the 3 Fragments are used in a ViewPager for the setting up of the game:
1. EnterNumberFragment is displayed first, to set up the number of players.
<img src="https://github.com/adrielyeung/one-to-hundred/blob/main/images/start.PNG" alt="StartActivity - EnterNumberFragment" width="25%" height="25%">
2. ConfirmFragment is displayed to prompt confirmation.
<img src="https://github.com/adrielyeung/one-to-hundred/blob/main/images/confirm.PNG" alt="StartActivity - ConfirmFragment" width="25%" height="25%">
3. EnterNameFragment is displayed to allow each player to input their names, with a dialog to confirm afterwards.
<img src="https://github.com/adrielyeung/one-to-hundred/blob/main/images/name.PNG" alt="StartActivity - EnterNameFragment" width="25%" height="25%">
<img src="https://github.com/adrielyeung/one-to-hundred/blob/main/images/confirm_name_prompt.PNG" alt="StartActivity - EnterNameFragment Prompt" width="25%" height="25%">
The "back" button may be used to return to the MainActivity.

### 7. GameActivity extends InGameActivity
2 Fragments are used in a ViewPager:
1. EnterNumberFragment is displayed for the "Judge" to select the "bomb" number, and for each player to take turns guessing.
<img src="https://github.com/adrielyeung/one-to-hundred/blob/main/images/set_bomb.PNG" alt="GameActivity - EnterNumberFragment (Set up bomb)" width="25%" height="25%">
<img src="https://github.com/adrielyeung/one-to-hundred/blob/main/images/game.PNG" alt="GameActivity - EnterNumberFragment (In Game)" width="25%" height="25%">
2. ConfirmFragment is displayed to prompt confirmation.

The "back" button may be used to return to the MainActivity.

### 8. EndActivity extends NoTitleActivity
<img src="https://github.com/adrielyeung/one-to-hundred/blob/main/images/end.PNG" alt="EndActivity" width="25%" height="25%">
This is triggered when a player guesses the "bomb" number.

## Future developments
1. Change behaviour of back button during setup, to allow returning to last step.
2. Automatic trigger of bomb when only 1 number left for guessing.
3. Audio when bomb triggered.

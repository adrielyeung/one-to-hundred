package com.adriel.onetohundred;

public class InstructionActivity extends DisplayActivity {

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void setDisplayTitle() {
        displayTitle = getString(R.string.instruction);
    }

    @Override
    protected void insertDisplay() {
        displayContentSequence.add(getString(R.string.instruction_1));
        displayContentSequence.add(getString(R.string.instruction_2));
        displayContentSequence.add(getString(R.string.instruction_3));
        displayContentSequence.add(getString(R.string.instruction_4));
        displayContentSequence.add(getString(R.string.instruction_5));
        displayContentSequence.add(getString(R.string.instruction_6));
        displayContentSequence.add(getString(R.string.instruction_7));
        displayContentSequence.add(getString(R.string.instruction_8));
        displayContentSequence.add(getString(R.string.instruction_9));
        displayContentSequence.add(getString(R.string.instruction_10));
    }
}

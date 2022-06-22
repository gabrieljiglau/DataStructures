package com.example.mypackage.stack;

public class ThreeInOne {

    private int numberOfStacks = 3;
    private int stackCapacity;
    private int[] arrayValues;
    private int[] sizeOfStack;

    public ThreeInOne(int stackCapacity){
        this.stackCapacity = stackCapacity;
        this.arrayValues = new int[stackCapacity * 3];
        this.sizeOfStack = new int[numberOfStacks];
    }

    public int pop(int stackIndex){
        if(!isEmpty(stackIndex)){
            int top = getTopOfStack(stackIndex);
            int returnValue = arrayValues[top];
            arrayValues[top] = 0;
            sizeOfStack[stackIndex]--;

            return returnValue;
        } else {
            System.out.println("Empty stack,returning -1");
            return -1;
        }
    }

    public int peek(int stackIndex){
        if(!isEmpty(stackIndex)){
            int top = getTopOfStack(stackIndex);
            int returnValue = arrayValues[top];

            return returnValue;
        } else {
            System.out.println("Empty stack,returning -1");
            return -1;
        }
    }

    public void push(int value,int stackIndex){
        if(!isFull(stackIndex)){
            sizeOfStack[stackIndex]++;
            int top = getTopOfStack(stackIndex);
            arrayValues[top] = value;
        } else {
            System.out.println("Full stack");
        }
    }

    private int getTopOfStack(int stackIndex){
        int offset = stackCapacity * stackIndex;
        int size = sizeOfStack[stackIndex];

        return offset + size -1;
    }

    public boolean isEmpty(int stackIndex){
        if(sizeOfStack[stackIndex] == 0){
            return true;
        } else {
            return false;
        }
    }

    public boolean isFull(int stackIndex){
        if(sizeOfStack[stackIndex] == sizeOfStack.length -1){
            return true;
        } else {
            return false;
        }
    }

    public int getNumberOfStacks() {
        return numberOfStacks;
    }

    public void setNumberOfStacks(int numberOfStacks) {
        this.numberOfStacks = numberOfStacks;
    }

    public int getStackCapacity() {
        return stackCapacity;
    }

    public void setStackCapacity(int stackCapacity) {
        this.stackCapacity = stackCapacity;
    }

    public int[] getArrayValues() {
        return arrayValues;
    }

    public void setArrayValues(int[] arrayValues) {
        this.arrayValues = arrayValues;
    }

    public int[] getSizeOfStack() {
        return sizeOfStack;
    }

    public void setSizeOfStack(int[] sizeOfStack) {
        this.sizeOfStack = sizeOfStack;
    }
}

package com.zipcodewilmington.looplabs;

import java.util.Arrays;

/**
 * Created by leon on 1/25/18.
 */
public abstract class DuplicateDeleter<T> implements DuplicateDeleterInterface<T> {
    private T[] array;
    private T[] uniques;
    private Integer countUniques;
    private Integer[] counts;

    public DuplicateDeleter(T[] intArray) {
        this.array = intArray;
        this.uniques = array.clone();
        this.countUniques=0;
        this.counts=new Integer[this.array.length];
        Arrays.fill(counts,0);
        this.makeUniqueList();
    }

    public T[] removeDuplicates(int maxNumberOfDuplications){
        Integer numToRemove=0;
        for(int i=0;i<this.countUniques;i++) if(this.counts[i]>=maxNumberOfDuplications)numToRemove+=this.counts[i];
        T[] newArray = Arrays.copyOf(this.array,this.array.length-numToRemove);
        Integer newCount=0;
        for (T t : this.array) {
            if (this.counts[findIndex(t)] < maxNumberOfDuplications)
                newArray[newCount++] = t;
        }
        return newArray;
    }

    public T[] removeDuplicatesExactly(int exactNumberOfDuplications) {
        Integer numToRemove=0;
        for(int i=0;i<this.countUniques;i++) if(this.counts[i]==exactNumberOfDuplications)numToRemove+=this.counts[i];
        T[] newArray = Arrays.copyOf(this.array,this.array.length-numToRemove);
        Integer newCount=0;
        for (T t : this.array) {
            if (this.counts[findIndex(t)] != exactNumberOfDuplications)
                newArray[newCount++] = t;
        }
        return newArray;
    }

    private void makeUniqueList(){
        for (T t : this.array) {
            Integer checker = checkIfDuplicate(t);
            if (checker < 0) {
                this.uniques[this.countUniques] = t;
                this.counts[this.countUniques++] = 1;
            } else {
                this.counts[checker]++;
            }
        }
    }

    private Integer checkIfDuplicate(T toCheck){
        for(int i=0;i<this.countUniques;i++)if(uniques[i].equals(toCheck))return i;
        return-1;
    }

    private Integer findIndex(T toFind){
        for(int i=0; i<this.countUniques;i++) if(this.uniques[i].equals(toFind))return i;
        return -1;
    }
}

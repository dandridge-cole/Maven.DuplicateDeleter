package com.zipcodewilmington.looplabs;

import java.util.Arrays;

/**
 * Created by leon on 1/25/18.
 */
public abstract class DuplicateDeleter<T> implements DuplicateDeleterInterface<T> {
    protected T[] array;
    protected T[] uniques;
    protected Integer countUniques;
    protected Integer[] counts;

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
        for(int i=0; i<this.array.length;i++) {
            if(this.counts[findIndex(this.array[i])]<maxNumberOfDuplications)
                newArray[newCount++]=this.array[i];
        }
        return newArray;
    }

    public T[] removeDuplicatesExactly(int exactNumberOfDuplications) {
        Integer numToRemove=0;
        for(int i=0;i<this.countUniques;i++) if(this.counts[i]==exactNumberOfDuplications)numToRemove+=this.counts[i];
        T[] newArray = Arrays.copyOf(this.array,this.array.length-numToRemove);
        Integer newCount=0;
        for(int i=0; i<this.array.length;i++) {
            if(this.counts[findIndex(this.array[i])]!=exactNumberOfDuplications)
                newArray[newCount++]=this.array[i];
        }
        return newArray;
    }

    public void makeUniqueList(){
        for(int i=0;i<this.array.length;i++){
            Integer checker = checkIfDuplicate(this.array[i]);
            if(checker<0){
                this.uniques[this.countUniques]=this.array[i];
                this.counts[this.countUniques++]=1;
            } else {
                this.counts[checker]++;
            }
        }
    }

    public Integer checkIfDuplicate(T toCheck){
        for(int i=0;i<this.countUniques;i++)if(uniques[i].equals(toCheck))return i;
        return-1;
    }

    public Integer findIndex(T toFind){
        for(int i=0; i<this.countUniques;i++) if(this.uniques[i].equals(toFind))return i;
        return -1;
    }

}

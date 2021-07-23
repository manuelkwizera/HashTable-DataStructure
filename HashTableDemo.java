/*
 *  Define the following methods: get(key), put(key,value), remove(key), containsKey(key), and size(). 
 */

package journalUnit5;

import java.util.Scanner;

//import HashTable.ListNode;

public class HashTableDemo {
	//ListNode class will hold Key/value pair
	 static private class ListNode{
		 Object key;
		 Object value;
		 ListNode nextNode;
	 }
	 
	 private ListNode[] listArray;
	 private int counter;
	 
	 public HashTableDemo(){
		 listArray = new ListNode[50];
		 
	 }
	 public HashTableDemo(int newSize){
		 listArray = new ListNode[newSize];
		 
	 }
	 
     void display() {
      System.out.println();
      for (int i = 0; i < listArray.length; i++) {
         System.out.print(i + ":");
         ListNode list = listArray[i]; 
         while (list != null) {
            System.out.print("  (" + list.key + "," + list.value + ")");
            list = list.nextNode;
         }
         System.out.println();
      }
   }
	 
	 
	 public void put(Object key, Object value) {
		 int index = hash(key);
		 ListNode list = listArray[index];
		 while (list != null) {
         if (list.key.equals(key)) {
             break;
         }
          list = list.nextNode;
	 }
	 
     if (list != null) {
       list.value = value;
    }
    else {
       if (counter >= 0.75*listArray.length) {
          resize();
       }
       ListNode newNode = new ListNode();
       newNode.key = key;
       newNode.value = value;
       newNode.nextNode = listArray[index];
       listArray[index] = newNode;
       counter++; 
    }
} 
	 
     public Object get(Object key) {
      int index = hash(key);  
      ListNode list = listArray[index];  
      while (list != null) {
         if (list.key.equals(key)) {
        	 return list.value; 
         }
         list = list.nextNode;  
      }
      return null;  
   }
     
     public void remove(Object key) {  
      int index = hash(key);  // At what location should the key be?
      if (listArray[index] == null) {
         return; 
      }
      if (listArray[index].key.equals(key)) {
         listArray[index] = listArray[index].nextNode;
         counter--; 
         return;
      }
      ListNode prevNode = listArray[index];
      ListNode currentNode = prevNode.nextNode; 
      while (currentNode != null && ! currentNode.key.equals(key)) {
         currentNode = currentNode.nextNode;
         prevNode = currentNode;
      }
      if (currentNode != null) {
         prevNode.nextNode = currentNode.nextNode;
         counter--;
      }
   }
     
     public boolean containsKey(Object key) {
      int index = hash(key);  
      ListNode list = listArray[index];  
      while (list != null) {
         if (list.key.equals(key)) {
        	 return true; 
         }
         list = list.nextNode;
      }
      return false;
   }
     
     public int size() {
      return counter;
   }
     
     private int hash(Object key) {
      return (Math.abs(key.hashCode())) % listArray.length;
   }
     
     private void resize() {
      ListNode[] newListArray = new ListNode[listArray.length*2];
      for (int i = 0; i < listArray.length; i++) {
         ListNode list = listArray[i]; 
         while (list != null) {
            ListNode nextNode = list.nextNode;
            int hash = (Math.abs(list.key.hashCode())) % newListArray.length;
            list.nextNode = newListArray[hash];
            newListArray[hash] = list;
            list = nextNode;  
         }
      }
      listArray = newListArray;  
   }
		 

	public static void main(String[] args) {
        HashTableDemo table = new HashTableDemo(2);
        Scanner scan =  new Scanner(System.in);
        String key,value;
        while (true) {
           System.out.println("\nENTER THE CHOICE:");
           System.out.println("   1. put(key,value)");
           System.out.println("   2. get(key)");
           System.out.println("   3. containsKey(key)");
           System.out.println("   4. remove(key)");
           System.out.println("   5. Display contents of hash table.");
           System.out.println("   6. EXIT");
           System.out.print("Enter your choice:  ");
           int choice = scan.nextInt();
       
           
           
           if(choice == 1) {
        	   System.out.print("\n   Key = ");
        	   key = scan.next();
        	   System.out.print("   Value = ");
        	   value = scan.next();
        	   table.put(key,value);
           }
           else if(choice == 2) {
        	   System.out.print("\n   Key = ");
        	   key = scan.next();
        	   System.out.println("   Value is " + table.get(key));
           }
           else if(choice == 3) {
        	   System.out.print("\n   Key = ");
        	   key = scan.next();
               System.out.println("   containsKey(" + key + ") is " 
                       + table.containsKey(key));
           }
           else if(choice == 4){
        	   System.out.print("\n   Key = ");
        	   key = scan.next();
        	   table.remove(key);
           }
           else if(choice == 5) {
               table.display();
           }
           else if(choice == 6){
        	   System.exit(0);
           }
           else {
        	   System.out.println("   Invalid Choice.");
           }
           
           System.out.println("\nHash table size is " + table.size());
        }

	}

}

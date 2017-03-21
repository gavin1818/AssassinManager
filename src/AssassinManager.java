import java.lang.*;
import java.util.*;

public class AssassinManager {
    private AssassinNode headKillRing;
    private AssassinNode headGraveyard;
    private AssassinNode currGraveyard;
    public AssassinManager(List<String> players) throws IllegalArgumentException{
        if(players.size()==0||players==null) throw new IllegalArgumentException("The array is incorrect!");
        headKillRing = new AssassinNode(players.get(0));
        headGraveyard = new AssassinNode("dummy");
        currGraveyard = headGraveyard;
        AssassinNode curr = headKillRing;
        for(int i=1;i<players.size();i++){
            curr.next=new AssassinNode(players.get(i));
            curr=curr.next;
        }
        if(players.size()>1){
            curr.next=headKillRing;
        }
    }
    public void printKillRing(){
        AssassinNode curr=headKillRing;
        while (curr.next!=headKillRing) {    
            System.out.println("    "+curr.name+" is stalking "+curr.next.name);
            curr=curr.next;
        }
        if(curr.next!=null){
            System.out.println("    "+curr.name+" is stalking "+curr.next.name);
        }
    }
    public void printGraveyard(){
        AssassinNode curr=headGraveyard;
        while(curr.next!=null) {    
            System.out.println("    "+curr.next.name+" was killed by "+curr.next.killer);
            curr=curr.next;
        }
    }
    public boolean killRingContains(String name){
        AssassinNode curr = headKillRing;
        while(curr.next!=headKillRing){//in order to make sure the loop will not check forever, when curr is the last element in the linkedlist, the loop quite.
            if(curr.name.equals(name)) return true;
            curr=curr.next;
        }
        if(curr.name.equals(name)) return true; //check the last element which is ignored by the while loop

        return false;
    }
    public boolean graveyardContains(String name){
        for(AssassinNode curr = headGraveyard;curr!=null;curr=curr.next){
            if(curr.name.equals(name)) return true;
        }
        return false;
    }
    public boolean isGameOver(){
        return headKillRing.next==headKillRing?true:false; 
    }
    public String winner(){
        return headKillRing.name;
    }
    public void kill(String name){
        if(headKillRing.next==null) return;
        if(!killRingContains(name)) return;
        AssassinNode curr = headKillRing;
        //check the headKillRing.next first, beacuse it is a cycle, headKillRing will be checked as the last.
        while(true){
            if(curr.next.name.equals(name)){
                if(curr.next==headKillRing){
                    headKillRing=headKillRing.next;
                }
                curr.next.killer=curr.name;
                currGraveyard.next=curr.next;
                currGraveyard = currGraveyard.next;
                AssassinNode temp = curr.next.next;
                curr.next.next=null;
                curr.next=temp;
                return;
            }
            curr=curr.next;
        }
    }
    private static class AssassinNode {
        public final String name;  // this person's name
        public String killer;      // name of who killed this person (null if alive)
        public AssassinNode next;  // next node in the list (null if none)
        /**
         * Constructs a new node to store the given name and no next node.
         */
        public AssassinNode(String name) {
            this(name, null);
        }

        /**
         * Constructs a new node to store the given name and a reference
         * to the given next node.
         */
        public AssassinNode(String name, AssassinNode next) {
            this.name = name;
            this.killer = null;
            this.next = next;
        }
    }
}

public class AssassinManager {
    private AssassinNode headKillRing;
    private AssassinNode headGraveyard;
    public AssassinManager(List<String> players) throws IllegalArgumentException{
        if(players.size()==0||players==null) throw IllegalArgumentException("The array is incorrect!");
        headKillRing = new AssassinNode(players.get(0));
        headGraveyard = new AssassinNode("dummy");
        AssassinNode curr = headKillRing.next;
        for(int i=1;i<players.size();i++){
            curr=new AssassinNode(players.get(i));
            curr=curr.next;
        }
        playerArray.length==1?curr=null:curr=headKillRing;
    }
    public void printKillRing(){
        int indent=4;
        AssassinNode curr=headKillRing;
        for (curr ;curr.next!=headKillRing&&curr.next!=null;curr=curr.next) {    
            System.out.print('\t' for each indent);
            System.out.println(curr.name+" is stalking "+curr.next.name);
        }
        if(curr.next!=null){
            System.out.print('\t' for each indent);
            System.out.println(curr.name+" is stalking "+curr.next.name);
        }

    }
    public void printGraveyard(){
        int indent=4;
        AssassinNode curr=headGraveyard;
        for (curr.next ;curr.next!=null;curr=curr.next) {    
            System.out.print('\t' for each indent);
            System.out.println(curr.next.name+" was killed by "+curr.next.killer);
        }
    }
    public boolean killRingContains(String name){
        for(AssassinNode curr = headKillRing;curr.next!=headKillRing;curr=curr.next){
            if(curr.next.name==name) return true;
        }
        return false;
    }
    public boolean graveyardContains(String name){
        for(AssassinNode curr = headGraveyard;curr!=null;curr=curr.next){
            if(curr.name==name) return true;
        }
        return false;
    }
    public boolean isGameOver(){
        return headKillRing.next==null?true:false; 
    }
    public String winner(){
        return headKillRing.name;
    }
    public void kill(String name){
        if(headKillRing.next==null) return;
        if(!killRingContains()) return;
        AssassinNode curr = headKillRing;
        //check the headKillRing.next first, beacuse it is a cycle, headKillRing will be checked as the last.
        while(true){
            if(curr.next.name==name){
                if(curr.next==headKillRing){
                    headKillRing=headKillRing.next;
                }
                curr.next.killer=curr.name;
                headGraveyard.next=curr.next;
                curr.next=curr.next.next;
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

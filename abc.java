

class LRUCache {

    class node{
        int key=0;
        int val=0;
        node left=null;
        node right=null;
        node(int key,int val,node left,node right){
            this.key=key;
            this.val=val;
            this.left=left;
            this.right=right;
        }
        node(){
            
        }
    }
    int cap=0;
    int check=0;
    HashMap<Integer,node>map= new HashMap<>();
    node head=new node(-1,-1,null,null);
    node tail=new node(-1,-1,null,null);
    public LRUCache(int capacity) {
         this.head.right=tail;
         this.tail.left=head;
          this.cap= capacity;
    }
    public int get(int key) {
        if(map.containsKey(key)==false)
        return -1;
        node w= map.get(key);
        deleteNode(w);
        addBeforeTail(w);
        return w.val;
    }
    public void put(int key, int value) {
        if(map.containsKey(key)==true){
            deleteNode(map.get(key));
            node w= new node(key,value,null,null);
             map.put(key,w);
            addBeforeTail(w);
        }
        else if(check==cap){
            map.remove(head.right.key);
            deleteNode(head.right);
            --check;
             ++check;
             node w= new node(key,value,null,null);
             map.put(key,w);
             addBeforeTail(w);
        }
        else{
             ++check;
             node w= new node(key,value,null,null);
             map.put(key,w);
             addBeforeTail(w);
        }
    }
    public void deleteNode(node w){
        node p= w.left;
       node q= w.right;
        p.right=q;
        q.left=p;
        w.left=w.right=null;
    }
    public void addBeforeTail(node w){
        tail.left.right=w;
        w.left=tail.left;
        w.right=tail;
        tail.left=w;
    }
    
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */
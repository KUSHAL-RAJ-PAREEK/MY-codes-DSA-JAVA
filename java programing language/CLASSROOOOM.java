import java.util.*;
public class CLASSROOOOM{
    static class Node{
        int data;
        Node left,right;
        public Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
    // public static int height(Node root){
    //     if(root == null){
    //         return 0;
    //     }
    //     int lh = height(root.left);
    //     int rh = height(root.right);
    //     return Math.max(lh,rh)+1;
    // }
    // public static int count(Node root){
    //     if(root == null){
    //         return 0;
    //     }
    //     int leftCount = count(root.left);
    //     int rightCount = count(root.right);
    //     return leftCount  +rightCount +1;
    // }
    // public static int sum(Node root){
    //     if(root == null){
    //         return 0;
    //     }
    //     int leftSum = sum(root.left);
    //     int rightSum = sum(root.right);
    //     return leftSum + rightSum + root.data;
    // }
    // public static int diameter2(Node root){
    //     if(root == null){
    //         return 0;
    //     }
    //     int leftdiam = diameter2(root.left);
    //     int leftHt = height(root.left);
    //     int rightdiam = diameter2(root.right);
    //     int rightHt = height(root.right);

    //     int selfDiam = leftHt + rightHt+1;
    //     return Math.max(selfDiam,Math.max(leftdiam,rightdiam));
    // }
    // static class Info{
    //     int diam;
    //     int ht;
    //     public Info(int diam,int ht){
    //         this.diam = diam;
    //         this.ht = ht;
    //     }
    // }
    // public static Info daimeter(Node root){
    //     if(root == null){
    //         return new Info(0,0);
    //     }
    //     Info leftInfo = daimeter(root.left);
    //     Info rightInfo = daimeter(root.right);
    //     int diam = Math.max(Math.max(leftInfo.diam,rightInfo.diam),leftInfo.ht+rightInfo.ht+1);
    //     int ht = Math.max(leftInfo.ht,rightInfo.ht)+1;
    //     return new Info(diam,ht);
    // }
    // public static boolean isIdentlcal(Node node,Node subRoot){
    //     if(node == null && subRoot == null){
    //         return true;
    //     } else if(node == null || subRoot == null || node.data != subRoot.data){
    //         return false;
    //     }
    //     if(!isIdentlcal(node.left,subRoot.left)){
    //         return false;
    //     }
    //     if(!isIdentlcal(node.right,subRoot.right)){
    //         return false;
    //     }
    //     return true;
    // }
    // public static boolean isSubtree(Node root,Node subRoot){
    //     if(root == null){
    //         return false;
    //     }
    //     if(root.data == subRoot.data){
    //         if(isIdentlcal(root,subRoot)){
    //         return true;
    //     }
    //     }
    //     return isSubtree(root.left,subRoot) || isSubtree(root.right,subRoot);
    // }
    // static class Info{
    //     Node node;
    //     int hd;
    //     public Info(Node node,int hd){
    //         this.node = node;
    //         this.hd = hd;
    //     }
    
    // public static void topview(Node root){
    //     // level order
    //     Queue<Info> q = new LinkedList<>();
    //     HashMap<Integer,Node> map = new HashMap<>();

    //     int min = 0, max = 0;
    //     q.add(new Info(root,0));
    //     q.add(null);

    //     while(!q.isEmpty()){
    //         Info curr = q.remove();
    //         if(curr == null){
    //             if(q.isEmpty()){
    //                 break;
    //             }else{
    //                 q.add(null);
    //             }
    //             } else{
    //                 if(!map.ContainKey(curr.hd)){ // first time my hd is occuring
    //                 map.put(curr.hd,curr.node);
    //                 }
    //                 if(curr.node.left != null){
    //                     q.add(new Info(curr.node.left,curr.hd-1));
    //                     min = Math.max(max,curr.hd-1);
    //                 }
    //                 if(curr.node.right != null){
    //                     q.add(new Info(curr.node.right,curr.hd+1));
    //                     max = Math.max(max,curr.hd+1);
    //                 }
    //             }
            
            
    //         for(int i = min; i <max; i++){
    //             System.out.print(map.get(i).data+" ");
            
    //     }
    //     }


    
    public static void kLevel(Node root,int level,int k){
        if(root == null){
            return;
        }
        if(level == k){
            System.out.print(root.data+ " ");
            return;
        }
        kLevel(root.left,level+1,k);
        kLevel(root.right,level+1,k);
    }
    public static boolean getpath(Node root ,int n ,ArrayList<Node>path){
        if(root == null){
            return false;
        }
        path.add(root);
        if(root.data == n){
            return true;
        }
        boolean foundleft = getpath(root.left,n,path);
        boolean foundright = getpath(root.right,n,path);
        if(foundleft || foundright){
            return true;
        }
        path.remove(path.size()-1);
        return false;
    }
    public static Node lca(Node root,int n1,int n2){
        ArrayList<Node> path1 = new ArrayList<>();
        ArrayList<Node> path2 = new ArrayList<>();

        getpath(root,n1,path1);
        getpath(root,n2,path2);
        // last common Ancestors
        int i = 0;
        for(; i <path1.size() && i<path2.size();i++){
            if(path1.get(i) != path2.get(i)){
                break;
            }
        }
        // last equal node -> i-1th
        Node lca = path1.get(i-1);
        return lca;
    }
    public static Node lca2(Node root,int n1,int n2){
        if(root == null|| root.data == n1 || root.data == n2){
            return root;
        }
        Node leftLca = lca2(root.left,n1,n2);
        Node rightLca = lca2(root.right,n1,n2);

        // leftLca = val rightLca = null
        if(rightLca == null){
            return leftLca;
        }
        if(leftLca == null){
            return rightLca;
        }
        return root;
    }
    
    // Public static int lcaDist(Node root ,int n){
    //     if(root == null){
    //         return -1;
    //     }
    //     if(root.data == n){
    //         return 0;
    //     }
    //     int leftDist = lcaDist(root.left,n);
    //     int rightDist = lcaDist(root.right,n);

    //     if(leftDist == -1 && rightDist == -1){
    //         return -1;
    //     } else if(leftDist == -1){
    //         return rightDist+1;
    //     } else{
    //         return leftDist+1;
    //     }
    // }
    // public static int minDist(Node root,int n1, int n2){
    //     Node lca = lca2(root,n1,n2);
    //     int Dist1 = lcaDist(lca,n1);
    //     int Dist2 = lcaDist(lca,n2);

    //     return dist1+dist2;
    // }
    public static int KAncestor(Node root,int n,int k){
        if(root == null){
            return -1;
        }
        if(root.data == n){
            return 0;
        }
        int leftDist = KAncestor(root.left,n,k);
        int rightDist = KAncestor(root.right,n,k);

        if(leftDist == -1 && rightDist == -1){
            return -1;
        }
        int max = Math.max(leftDist,rightDist);
        if(max+1 == k){
            System.out.println(root.data);
        }
        return max+1;
    }
    public static int transform(Node root){
        if(root == null){
            return 0;
        }
        int leftchid = transform(root.left);
        int rightchild = transform(root.right);
        int data = root.data;

        int newleft = root.left == null ? 0 : root.left.data;
        int newright = root.right == null ? 0 : root.right.data;
        root.data = newleft + leftchid + newright + rightchild;
        return data;
    }
    public static void preorder(Node root){
        if(root == null){
            return;
        }
        System.out.print(root.data+" ");
        preorder(root.left);
        preorder(root.right);
    }
    public static void main(String args[]){
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        int k = 2;
        kLevel(root,1,k);
    //   transform(root);
    //   preorder(root);
    }
 }


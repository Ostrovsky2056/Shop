package com.qfedu.shop.test;

public class Test {
    public static void main(String[] args) {
        int [] arr=new int[]{5,6,8,7,2,9,1,3,4};
        Node tree=new Node();
        tree.value=arr[0];
        for (int i = 1; i < arr.length; i++) {
            insertToTree(tree,arr[i]);
        }

        //System.out.println(tree.left.right.right.value);
        printTree(tree);


    }
    public static void printTree(Node tree) {
        System.out.print(tree.value + " ");
        if(tree.left!=null){
            printTree(tree.left);
        }

        if(tree.right!=null){
            printTree(tree.right);
        }
    }


    public static void insertToTree(Node tree,int value){
        if(value>tree.value){
            //放在右边
            if(tree.right==null){
                //如果右边没有东西，这个值直接就是右孩子的值
                tree.right=new Node();
                tree.right.value=value;
            }else{
                //如果右边有东西
                //就把这个值放到右子树中。
                insertToTree(tree.right,value);
            }
        }else{
            //放在左边
            if(tree.left==null){
                //如果左边没有东西，这个值直接就是左孩子的值
                tree.left=new Node();
                tree.left.value=value;
            }else{
                //如果左边有东西
                //就把这个值放到左子树中。
                insertToTree(tree.left,value);
            }
        }
    }
}
class Node{
    int value;
    Node left;
    Node right;
}

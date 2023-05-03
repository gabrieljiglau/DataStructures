package trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLinkedList extends BinaryNode {
    private BinaryNode rootNode;

    public BinaryTreeLinkedList(){
        this.rootNode = null;
    }

    public BinaryTreeLinkedList(int data){
        super(data);
    }

    public BinaryNode getRootNode() {
        return rootNode;
    }



    public void setRootNode(BinaryNode rootNode) {
        this.rootNode = rootNode;
    }

    public void preOrder(BinaryNode node){
        if(node == null){
            return;
        }

        System.out.print(node.getData() + " ");

        preOrder(node.getLeftChild());
        preOrder(node.getRightChild());
    }

    public List<Integer> getBranchSums(BinaryNode root){
        List<Integer> list = new ArrayList<>();
        calculateBranchSums(root,0,list);

        return list;
    }

    private void calculateBranchSums(BinaryNode node,int sum,List<Integer> list){
        if(node == null){
            return;
        }

        int newSum = sum + node.getData();

        if(node.getLeftChild() == null && node.getRightChild() == null){
            list.add(newSum);
            return;
        }

        calculateBranchSums(node.getLeftChild(),newSum,list);
        calculateBranchSums(node.getRightChild(),newSum,list);

        for(Integer i : list){
            System.out.println(list.get(i));
        }
    }

    public void inOrder(BinaryNode node){
        if(node == null){
            return;
        }

        preOrder(node.getLeftChild());
        System.out.print(node.getData() + " ");
        preOrder(node.getRightChild());
    }

    public void postOrder(BinaryNode node){
        if(node == null){
            return;
        }

        postOrder(node.getLeftChild());
        postOrder(node.getRightChild());
        System.out.print(node.getData() + " ");
    }

   public void levelOrder(BinaryNode node){
        Queue<BinaryNode> queue = new LinkedList<>();
        queue.add(node);

        while(!queue.isEmpty()){
            BinaryNode presentNode = queue.remove();
            System.out.print(presentNode.getData() + " ");

            if(presentNode.getLeftChild() != null){
                queue.add(presentNode.getLeftChild());
            }

            if(presentNode.getRightChild() != null){
                queue.add(presentNode.getRightChild());
            }
        }
   }

   public void insert(int data){
        if(rootNode != null){
            Queue<BinaryNode> queue = new LinkedList<>();
            queue.add(rootNode);

            BinaryNode nodeToInsert = new BinaryNode(data);

            while(!queue.isEmpty()){
                BinaryNode presentNode = queue.remove();

                if(presentNode.getLeftChild() != null){
                    queue.add(presentNode.getLeftChild());
                } else {
                    presentNode.setLeftChild(nodeToInsert);
                  //  System.out.println("Inserted value of  " + nodeToInsert.getData() +
                    //        " to the left of the " + presentNode.getData());
                    return;
                }

                if(presentNode.getRightChild() != null){
                    queue.add(presentNode.getRightChild());
                } else {
                    presentNode.setRightChild(nodeToInsert);
                   // System.out.println("Inserted value of " + nodeToInsert.getData() +
                     //       " to the right of the " + presentNode.getData());
                }
            }
        } else {
            this.rootNode = new BinaryNode(data);
        }
   }

   public List<Integer> maxPathSum(BinaryNode node){

        List<Integer> returnList = new ArrayList<>();

        if(node == null){
            returnList.add(0);
            returnList.add(0);

            return returnList;
        }

        List<Integer> listLeft = maxPathSum(node.getLeftChild());
        int leftMaxSumBranch = listLeft.get(0);
        int leftMaxPathSum = listLeft.get(1);

        List<Integer> listRight = maxPathSum(node.getRightChild());
        int rightMaxSumAsBranch = listRight.get(0);
        int rightMaxPathSum = listRight.get(1);

        int maxChildSumAsBranch = Math.max(leftMaxSumBranch,rightMaxSumAsBranch);

        int value = node.getData();
        int maxSumAsBranch = Math.max(maxChildSumAsBranch + value,value);

        int maxSumAsRoot = Math.max(maxSumAsBranch,leftMaxSumBranch + value + rightMaxSumAsBranch);
        int runningMaxPathSum = Math.max(maxSumAsRoot,Math.max(leftMaxPathSum,rightMaxPathSum));

        List<Integer> newReturn = new ArrayList<>();
        newReturn.add(maxSumAsBranch);
        newReturn.add(runningMaxPathSum);

        return newReturn;

   }

   public void deleteTree(){
        rootNode = null;
   }

   public void deleteNode(int data){
        Queue<BinaryNode> queue = new LinkedList<>();
        queue.add(rootNode);
        BinaryNode current = new BinaryNode();

        while(!queue.isEmpty()){
            current = queue.remove();

            if(current.getData() == data){
                current.setData(getDeepestNode().getData());
                deleteDeepest();
                return;
            } else {
                if(current.getLeftChild() != null){
                    queue.add(current.getLeftChild());
                }

                if(current.getRightChild() != null){
                    queue.add(current.getRightChild());
                }
            }
        }

       System.out.println("Node with the value of " + data +
               " is not in the tree");
   }

   public void deleteDeepest(){
        Queue<BinaryNode> queue = new LinkedList<>();
        queue.add(rootNode);
        BinaryNode previous = new BinaryNode();
        BinaryNode current = new BinaryNode();
        BinaryNode deepest = getDeepestNode();

        while(!queue.isEmpty()){
            previous = current;
            current = queue.remove();

            if(current.getLeftChild() == null){
                previous.setRightChild(null);
                return;
            }

            if(current.getRightChild() == null){
                current.setLeftChild(null);
                return;
            }

            queue.add(current.getLeftChild());
            queue.add(current.getRightChild());
        }
   }

   public BinaryNode getDeepestNode(){
        Queue<BinaryNode> queue = new LinkedList<>();
        queue.add(rootNode);
        BinaryNode current = new BinaryNode();

        while(!queue.isEmpty()){
            current = queue.remove();
            BinaryNode deepest = current;

            if(current.getLeftChild() != null) {
                queue.add(current.getLeftChild());
            }

            if(current.getRightChild() != null) {
                queue.add(current.getRightChild());
            }
        }
        return current;
   }


   public BinaryNode search(int nodeData){
        Queue<BinaryNode> queue = new LinkedList<>();
        queue.add(rootNode);

        while(!queue.isEmpty()){
            BinaryNode valueToCheck = queue.remove();

            if(valueToCheck.getData() == nodeData){
                System.out.println("Found value of " + nodeData);

                return valueToCheck;
            } else {
                queue.add(valueToCheck);

                if(valueToCheck.getLeftChild() != null){
                    queue.add(valueToCheck.getLeftChild());
                }

                if(valueToCheck.getRightChild() != null){
                    queue.add(valueToCheck.getRightChild());
                }
            }
        }
        System.out.println("Value " + nodeData + " not found");
        return null;
   }


}



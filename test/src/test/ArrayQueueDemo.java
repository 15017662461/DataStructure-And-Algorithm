package test;

public class ArrayQueueDemo {
    public static void main(String[] args) throws Exception {
        ArrayQueue arrayQueue = new ArrayQueue(5);
        arrayQueue.addQueue(1);
        arrayQueue.addQueue(2);
        arrayQueue.addQueue(3);
        arrayQueue.addQueue(4);
        arrayQueue.addQueue(5);
        System.out.println(arrayQueue.getFirst());
        System.out.println(arrayQueue.getFirst());
        System.out.println(arrayQueue.checkFirst());
        System.out.println(arrayQueue.checkFirst());
    }
    static class ArrayQueue{
        private int maxSize;
        private int rear;//队列尾
        private int front;//队列头
        private int[] arr;

        public ArrayQueue(int size){
            maxSize = size;
            arr = new int[size];
            front = -1;
            rear = -1;
        }

        public boolean isFull(){
            return  rear == maxSize - 1;
        }

        public boolean isEmpty(){
            return rear == front;
        }

        public void addQueue(int a) throws Exception {
            if(isFull()){
                throw new Exception("队列满了，无法添加数据");
            }
            rear ++;
            arr[rear] = a;
        }

        public int getFirst() throws Exception {
            if(isEmpty()){
                throw new Exception("队列是空的，无法取出数据");
            }
            front ++;
            return arr[front];
        }

        public int checkFirst() throws Exception {
            if (isEmpty()){
                throw new Exception("队列是空的，无法取出数据");
            }
            return arr[front + 1];
        }
    }
}

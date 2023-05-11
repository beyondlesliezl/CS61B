public class ArrayDeque<T>implements Deque<T> {
    private int size;
    private T[] arr;
    private double factor = (double)size / arr.length;
    private static double constant = 0.25;
    //factor must greater than 0.25
    public ArrayDeque(){
        arr = (T[]) new Object[8];
        size = 0;
    }

    @Override
    public void addFirst(T item){
        if(size == arr.length){
            T[] temp = (T[]) new Object[size * 2];
            for(int i = 0; i < size; i++){
                temp[i+1] = arr[i];
            }
            temp[0] = item;
            arr = temp;
            size++;
        }else{
            for(int i = size; i > 0; i--){
                arr[i] = arr[i-1];
            }
            arr[0] = item;
            size++;
        }
    }  //添加一个类型的项目T到双端队列的前面。

    @Override
    public void addLast(T item){
        if(size == arr.length){
            T[]temp = (T[]) new Object[size * 2];
            for(int i = 0; i < size; i++){
                temp[i] = arr[i];
            }
            temp[size] = item;
            arr = temp;
            size++;
        }else{
            arr[size] = item;
            size++;
        }
    } //添加一个类型的项目T到双端队列的后面。

    @Override
    public boolean isEmpty(){
       return (size == 0);
    }//如果双端队列为空，则返回 true，否则返回 false。

    @Override
    public int size(){
        return size;
    }
    //返回双端队列中的项目数。

    @Override
    public void printDeque(){
        if(size == 0){
            return;
        }
        for(int i = 0; i < size; i++){
            System.out.print(arr[i] + " ");
        }
    }
     //从头到尾打印双端队列中的项目，以空格分隔。

    @Override
    public T removeFirst(){
        if(size == 0){
            return null;
        }
        T target = arr[0];
        size -= 1;
        if(factor < constant){
            T[] temp =  (T[]) new Object[size * 4];
            for(int i = 0; i < size; i++){
                temp[i] = arr[i+1];
            }
            arr = temp;
            return target;
        }else{
           for(int i = 0; i < size; i++) {
            arr[i] = arr[i+1];
           }
           return target;
        }

    }//删除并返回双端队列前面的项目。如果不存在这样的项目，则返回 null。

    @Override
    public T removeLast(){
        if(arr == null){
            return null;
        }
        T target = arr[size];
        size -= 1;
        if(factor < constant){
            T[] temp = (T[]) new Object[size * 4];
            for(int i = 0; i < size; i++){
                temp[i] = arr[i];
            }
            arr = temp;
            return target;
        }else{
            return target;
        }
    } //删除并返回双端队列后面的项目。如果不存在这样的项目，则返回 null。

    @Override
    public T get(int index){
        if(arr == null){
            return null;
        }
        return arr[index];
    } //获取给定索引处的项目，其中 0 是前面的项目，1 是下一个项目，依此类推。如果不存在这样的项目，则返回 null。不能改变双端队列！

}

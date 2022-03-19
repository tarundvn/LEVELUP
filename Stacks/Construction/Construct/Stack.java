public class Stack{

    private int[]arr;
    private int tos;
    private int NoOfElements;
    private int MaxCapacity;

    Stack(int size)
    {
        initialize(15);
    }

    Stack()
    {
        this(10);
    }

    protected void initialize(int size)
    {
        this.NoOfElements = 0;
        this.MaxCapacity = size;
        this.tos = -1;
        this.arr = new int[size];
    }

    private void overflowException() throws Exception{
        if(this.NoOfElements == this.MaxCapacity)
        throw new Exception("StackIsOverflow");
    }

    private void underflowException() throws Exception{
        if(this.NoOfElements == 0)
        throw new Exception("StackIsUnderflow");
    }

    public void push(int data) throws Exception
    {
        overflowException();
        this.arr[++tos] = data;
        this.NoOfElements++;
    }

    public int peek() throws Exception
    {
        underflowException();
        return this.arr[tos];
    }

    public int pop() throws Exception
    {
        underflowException();
        int rv = this.arr[this.tos--];
        this.NoOfElements--;
        return rv;
    }

    public void display()
    {
        for (int i = this.tos; i >= 0; i--) {
            System.out.print(this.arr[i] + " ");
        }
    }
}
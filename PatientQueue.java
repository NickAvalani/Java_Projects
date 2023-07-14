/*
 * Author: Nishith Avalani
 * File: PatientQueue.java
 * Course: CSc 210, Fall 2021, Professor Claveau
 * Purpose: This program represents a hospital queue 
 *          using a priority queue implemented as a 
 *          binary minimum heap.  
 */

 public class PatientQueue{
	/*
	 * This class implements a priority queue as an abstract data 
	 * type. This class has various methods that perform several 
	 * functions on the queue. 
	 * The methods include:
	 * constructor
	 * enqueue()
	 * dequeue()
	 * peek()
	 * peekPriority()
	 * changePriority()
	 * isEmpty()
	 * size()
	 * clear()
	 * toString()
	 */
	private Heap h;
	private int size;
	
	public PatientQueue() {
		// This constructor initializes an empty
		// priority queue as a heap. 
		h = new Heap();
		size = 0;
	}
	
	public void enqueue(String name, int priority) {
		/*
		 *  This method enqueues a patient		 
		 *	into the priority queue by creating
		 *	a Patient object with name and the
		 *	priority. If the queue is filled, 
		 *  the queue is doubled in capacity.
		 *  @Param name: the name of the patient
		 *  @Param priority: the priority of patient. 
		 *                   the lower the integer, 
		 *                   the higher the priority. 
		 */ 
		if((size % 10) == 0) {
			h.resize();
		}
		Patient p1 = new Patient(name, priority);
		h.add(p1);
		size++;
	}
	
	public void enqueue(Patient patient) {
		/*
		 *  This method enqueues the patient		 
		 *	into the priority queue, and the
		 *  patient bubbles up based on its 
		 *  priority. If the queue is filled,
		 *  the queue is resized to double its
		 *  capacity.
		 *  @Param patient: the Patient object. 
		 */ 
		if(size == 10) {
			h.resize();
		}
		h.add(patient);
		size++;
	}
	
	public String dequeue() throws Exception{
		/*
		 * This function dequeues the highest 
		 * priority patient from the queue. If
		 * the queue is empty, an exception is
		 * thrown.The name of the removed patient
		 * is returned. 
		 * @return String: the name of the patient 
		 */
		if(isEmpty()) 
			throw new Exception();
		else {
		Patient p1 = h.remove();
		size--;
		return p1.name;
		}
	}
	
	public String peek() throws Exception{
		/*
		 * This method returns the name of the
		 * highest priority patient.If the queue 
		 * is empty, an exception is thrown.
		 * @return String: the name of the patient
		 */
		if(isEmpty())
			throw new Exception();
		Patient p1 = h.peek();
		return p1.name;
	}
	
	public int peekPriority() throws Exception{
		/*
		 * This method returns the priority of the
		 * highest priority patient. If the queue 
		 * is empty, an exception is thrown.
		 * @return int: the priority of the patient
		 */
		if(isEmpty())
			throw new Exception();
		Patient p1 = h.peek();
		return p1.priority;
	}
	
	public void changePriority(String name, int newPriority) {
		/*
		 * This method changes the priority of 
		 * the patient with the same name as the
		 * parameter.
		 * @Param String: name of the patient
		 * @Param newPriority: the new priority of the 
		 *                     patient. 
		 */
		h.changePriority(name, newPriority);
	}
	
	public boolean isEmpty() {
		/*
		 * This method returns
		 * true if queue is empty
		 * and false otherwise.
		 */
		return size == 0;
	}
	
	public int size() {
		/*
		 * This method returns
		 * the size of the queue.
		 */
		return size;
	}
	
	public void clear() {
		/*
		 * This method removes
		 * all the patients from
		 * the queue.
		 */
		size = 0;
		h.clear();
	}
	
	public String toString() {
		/* 
		 * This method returns
		 * a string representation
		 * of the queue.
		 */
		return h.toString();
	}
	
public class Heap{
	/*
	 * This class is responsible for 
	 * implementing the priority queue
	 * as a binary min heap. The various 
	 * methods in the class include:
	 * constructor          bubbleDown()
	 * parent()             resize()
	 * left()               add()
	 * right()              remove()
	 * compare()            changePriority()
	 * bubbleUp()           peek()
	 * isEmpty()            clear()
	 * toString()
	 */
    private int DEFAULT_CAPACITY = 11;
    // The default capacity is not final static as 
    // it changes when you resize the array.
    private Patient[] heap;
    private int size;

    public Heap() {
    	// This constructor initializes
    	// the heap and its size. 
    	heap = new Patient[DEFAULT_CAPACITY];
        size = 0;
    }
      
    private int parent(int i){
    	// This function returns the 
    	// index of the parent when 
    	// provided the index of the
    	// left or right child.
    	return i/2;
    }
    
    private int left(int i){
    	// This function returns the 
    	// index of the left child
    	// when provided with index of
    	// parent.
    	return 2*i;
    }
    
    private int right(int i){
    	// This function returns the 
    	// index of the right child
    	// when provided with index of
    	// parent.
    	return 2*i + 1;
    }
    
    private boolean compare(Patient p1, Patient p2) {
    	/*
    	 * This method compares two patients and 
    	 * returns true if the first patient has
    	 * higher priority over the second patient
    	 * and false otherwise. 
    	 * @Param Patient object p1
    	 * @Param Patient object p2 
    	 */
    	if(p1.priority == p2.priority) {
    		return(p1.name).compareTo(p2.name) < 0;
    	}
    	else {
    		return (p1.priority) < (p2.priority);
    	}
    }
    
    private void bubbleUp(int i) {
    	/*
    	 * This method moves a patient 
    	 * with a high priority to a 
    	 * higher level within the heap.
    	 * @Param int: index of the patient
    	 */
    	if(i != 1)
    		if(compare(heap[i], heap[parent(i)])) {
    		//swap with parent
    			Patient p1 = heap[parent(i)];
    			heap[parent(i)] = heap[i];
    			heap[i] = p1;
    			bubbleUp(parent(i));
    	}
    }

    private void bubbleDown(int i) { 
    	/*
    	 * This function moves a patient with
    	 * a low priority to a lower level to a
    	 * lower level within the heap. 
    	 * @Param int: index of the patient. 
    	 */
    	if(i > size) {
    		return;
    	}
    	if(left(i) <= size) {
	    	int higherpriorityChild = left(i);
	    	if(right(i) <= size && compare(heap[right(i)], heap[left(i)]))
	    		higherpriorityChild = right(i);
	    	if(compare(heap[higherpriorityChild], heap[i])) {
	    		Patient p1 = heap[higherpriorityChild];
	    		heap[higherpriorityChild] = heap[i];
	    		heap[i] = p1;
	    		bubbleDown(higherpriorityChild);
	    	}
	    }
    }
    
    public void resize() {
    	/*
    	 * This method doubles the capacity of the
    	 * priority queue.
    	 */
    	Patient[] new_heap = new Patient[DEFAULT_CAPACITY * 2];
    	DEFAULT_CAPACITY *= 2;
    	for(int i = 1; i <= size; i++) {
    		new_heap[i] = heap[i];
    	}
    	Patient[] copy = heap;
    	heap = new_heap;
    }
    
    public void add(Patient p1) {
    	/*
    	 * This method adds a patient to the
    	 * priority queue, and places it 
    	 * appropriately within the heap.
    	 * @Param patient object to be added. 
    	 */
        if (size == heap.length-1)
            return;
        size++;
        heap[size] = p1;
        bubbleUp(size);
    }

    public Patient remove(){
    	/*
    	 * This method removes the highest
    	 * priority element from the queue
    	 * and returns its name. Throws an
    	 * exception if the queue is empty.
    	 */
        if (isEmpty())
        	return null;

        Patient p1 = heap[1];
        heap[1] = heap[size];
        size--;
        bubbleDown(1);
        return p1;
    }
    
    
    public void changePriority(String name, int p) {
    	/*
    	 * Changes the priority of an existing
    	 * patient and reorganizes the heap
    	 * accordingly.
    	 */
    	for(int i = 1; i <= size; i++) {
    		if(heap[i].name.equals(name)) {
    			if(heap[i].priority == p) {
    				return;
    			}
    			else {
    				if(heap[i].priority > p) {
    					heap[i].priority = p;
    					bubbleUp(i);
    				}
    				else {
    					heap[i].priority = p;
    					bubbleDown(i);
    				}
    			}
    		}
    	}
    }
    
    public Patient peek() {
    	// This method returns the 
    	// highest priority patient
    	// in the queue. 
    	return heap[1];
    }
    

    public boolean isEmpty() {
    	/* This method returns true if 
    	 * true if the heap is null or 
    	 * the size of the heap is 0.
    	*/ 
        if(size == 0 || heap == null)
        	return true;
        return false;
    }

    public void clear() {
    	/*
    	 * This method clears the heap 
    	 * of all its elements.
    	 */
        size = 0;
        DEFAULT_CAPACITY = 0;
        Heap heap = new Heap();
    }

    public String toString() {
    	/*
    	 * This method returns a string
    	 * representation of the heap. 
    	 */
        String s = "{";

        for (int i = 1; i <= size; i++)
            s += (i == 1 ? "" : ", ") + heap[i];

        return s + "}";
    	}
	}
}

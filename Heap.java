import java.util.*;
/**
 * Write a description of class Heap here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Heap<T extends Comparable<T>> implements PriorityQueue<T>
{
    private List<T> elements;

    public Heap()
    {
        elements = new ArrayList<T>();
    }

    public int size()
    {
        return elements.size();
    }   

    // tests if this priority queue is empty
    public boolean empty()
    {
        return size() == 0;
    }

    // adds an item to the priority queue
    public T add(T element)
    {
        elements.add(element);
        reheapUp(element);
        return element;
    }

    public void reheapUp(T element)
    {
        int hole = size()-1;
        int parent = (hole-1)/2;

        while((hole > 0) && (element.compareTo(elements.get((hole-1)/2)) > 0))
        {
            elements.set(hole, elements.get((hole-1)/2));
            hole = (hole-1)/2;
            //parent = (hole-1)/2;
        }
        elements.set(hole, element);
    }

    // looks at the object at the front of this priority queue
    // without removing it from the priority queue
    public T peek() throws PQUnderflowException
    {
        if(empty())
        {
            throw new PQUnderflowException("Priority Queue is empty");
        }
        else
        {
            return elements.get(0);
        }
    }

    // removes the object at the front of this priority queue 
    // and returns that object as the value of this function
    public T remove() throws PQUnderflowException
    {
        T root;
        T last;
        if(empty())
        {
            throw new PQUnderflowException("Priority Queue is empty");
        }
        else{
            root = elements.get(0);
            last = elements.remove(size()-1);
            if (!empty())
            {
                reheapDown(last);
            }
            return root;
        }

    }

    public void reheapDown(T element)
    {
        int root = 0;
        int newH = newHole(root, element);
        while(newH != root)
        {
            elements.set(root, elements.get(newH));
            root = newH;
            newH = newHole(root, element);
        }
        elements.set(root, element);
    }

    public int newHole(int hole, T element)
    {
        int left = (hole * 2) + 1;
        int right = (hole *2) + 2;
        if(left >= size())
        {
            return hole;
        }
        else if (left == size() - 1)
        {
            if(elements.get(left).compareTo(element) > 0)
            {
                return left;
            }
            else
            {
                return hole;
            }
        }
        else
        {
            if(elements.get(left).compareTo(elements.get(right)) < 0) 
            {  
                if(elements.get(right).compareTo(element) <= 0)
                {

                    return hole;
                } 
                else
                {                    
                    return right;
                }
            }
            else 
            {                       
                if(elements.get(left).compareTo(element) <= 0) 
                {     
                    return hole;
                }
                else                    
                {
                    return left;
                }
            }
        }
    }

    // removes all of the elements from this priority queue
    public void clear()
    {
        elements.clear();//commit
    }
}

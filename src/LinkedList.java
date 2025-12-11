import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LinkedList {
    public Node head;
    public Node tail;

    public LinkedList() {
        head = null;
        tail = null;
    }
    public Node getHead(){
        return head;
    }

    public String remove_from_index(int index) {
        Node curr = head;

        if (head == null) {
            return null;
        }
        int indexCounter = 0;
        while (curr.next != null && indexCounter < index) {
            curr = curr.next;
            indexCounter++;
        }

        if (curr == head) {
            return head.data;
        } else if (curr.next == null) {
            return curr.data;
        } else {
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
            curr.next = null;
            curr.prev = null;
        }

        return curr.data;
    }








    // remove a card from the beginning of the list
    public String returnTail (Node head) {
        Node curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        return curr.data;

    }

    public Node sortedAppend(String x) {
        Node newNode = new Node(x);

        // If the list is empty, set the new node as the head
        if (head == null) {
            head = newNode;
            return head;  // Return the new head
        }

        // If the new node should be the first node (before the head)
        if (compareDates(x, head.data) <= 0) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
            return head;  // Return the updated head
        }

        // Traverse the list to find the correct insertion point
        Node curr = head;
        Node previous = null;
        while (curr != null && compareDates(x, curr.data) > 0) {  // Fix condition here to find proper spot
            previous = curr;
            curr = curr.next;
        }

        // Insert the new node in the correct position
        previous.next = newNode;
        if (curr != null) {
            curr.prev = newNode;
        }
        newNode.prev = previous;
        newNode.next = curr;

        return head;  // Return the updated head after insertion
    }

    public static int compareDates(String x, String y) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        try{
            Date date1 = sdf.parse(x);
            Date date2 = sdf.parse(y);
            return date1.compareTo(date2);
        }
        catch(ParseException e){
            System.out.println ("error sorting the dates");
        }
        return 0;
    }
    public void print(Node head) {
        Node curr = head;
        int i = 1;
        while(curr != null) {
            System.out.println(curr.data);
            if(curr.next != null)
                System.out.print(" -->  ");
            else
                System.out.println(" X");

            if (i % 7 == 0) System.out.println("");
            i = i + 1;
            curr = curr.next;
        }
        System.out.println("");
    }
}


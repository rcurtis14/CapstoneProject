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

    public String remove_from_index(int index) {
        // FIXME
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
            return remove_from_head();
        } else if (curr == tail) {
            tail.prev.next = null;
            tail = tail.prev;

        } else {
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
            curr.next = null;
            curr.prev = null;
        }

        return curr.data;
    }


    // insert a card at a specific index
    public void insert_at_index(String x, int index) {
        // FIXME
        Node curr = head;
        Node previous = null;
        Node insertValue = new Node(x);
        int countIndex = 0;

        if (head == null) {
            head = insertValue;

        }
        while (curr.next != null) {
            if (countIndex == index) {
                break;
            }
            previous = curr;
            countIndex++;
            curr = curr.next;
        }

        if (curr == head) {

            insertValue.next = head;
            head.prev = insertValue;
            head = insertValue;

        } else if (curr == tail) {

            add_at_tail(x);
        } else {

            curr.prev.next = insertValue;
            curr.prev = insertValue;
            insertValue.next = curr;
            insertValue.prev = previous;

        }


    }


    public void swap(int index1, int index2) {
        // FIXME
        Node curr = head;
        Node first = head;
        int count2 = 0;
        int count1 = 0;
        while (curr.next != null && count2 < index2) {
            if (count2 < index1) {
                first = first.next;
                curr = curr.next;
                count1++;
                count2++;
            } else {
                curr = curr.next;
                count2++;
            }
        }
        Node temp2 = new Node(curr.data);//saves the data from index 2 node
        Node temp1 = new Node(first.data);
        insert_at_index(remove_from_index(count1), count2); // takes the node from count 1 and removes it appending it at count 2

        curr = head;
        int countFinal = 0;
        while (curr.data != temp2.data) {
            countFinal++;
            curr = curr.next;
        }
        insert_at_index(remove_from_index(countFinal), count1);


    }

    // add card at the end of the list
    public void add_at_tail(String data) {
        // FIXME
        Node insertValue = new Node(data);
        if (head == null) {
            head = insertValue;
            tail = insertValue;


        } else {
            tail.next = insertValue;
            insertValue.prev = tail;
            tail = insertValue;
        }
        ;
    }

    // remove a card from the beginning of the list
    public String remove_from_head() {
        // FIXME
        Node curr = head;

        if (head == null) {
            return null;
        }
        if (head == tail) { //in the case that there is one element in the list
            head = null;
            tail = null;
            return curr.data;

        } else {
            head = head.next;
            head.prev.next = null;
            head.prev = null;
        }

        return curr.data;
    }

    public void sortedAppend(Node head, String x) {
        Node newNode = new Node(x);

        if (head == null) {
            head = newNode;

        }
        if (compareDates(x, head.data) <= 0){
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        Node curr = head;
        Node previous = null;
        while (curr.next != null && compareDates(x, curr.data) <= 0) {
            previous = curr;
            curr = curr.next;
        }
        curr.prev.next = newNode;
        curr.prev = newNode;
        newNode.next = curr;
        newNode.prev = previous;

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
}


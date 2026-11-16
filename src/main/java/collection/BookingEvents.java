package collection;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // static methods only, never instantiated
public class BookingEvents {
    public static void main(String[] args) {

        List<Booking> bookings = List.of(
        new Booking("B1" , "U1" , 100L),
        new Booking("B2", "U2",200L),
        new Booking("B3","U3", 250L),
        new Booking("B4","U1", 150L),
        new Booking("B5","U4",120L),
        new Booking("B6","U2",100L),
        new Booking("B7","U4",100L)
        );

        int k = 3;

        System.out.println(topKUsers(bookings, k));
    }

    /**
     * Returns the k users with the highest total booking amount, highest first.
     * Users with equal totals are ordered by user id, and that order also decides who makes the cut.
     */
    static List<String> topKUsers(List<Booking> bookings, int k) {
       if(bookings == null){
           throw new IllegalArgumentException("bookings must not be null");
       }
       if(k < 0){
           throw new IllegalArgumentException("k must not be negative but was " + k);
       }

       Map<String, Long> map = new HashMap<>();
       for(Booking booking: bookings){
           if(booking == null || booking.getUserId() == null || booking.getAmount() == null){
               throw new IllegalArgumentException("every booking needs a user id and an amount but found " + booking);
           }
           String userId = booking.getUserId();
           map.put(userId, map.getOrDefault(userId, 0L) + booking.getAmount());
       }

       // smallest total at the head; among equal totals the larger user id is at the head,
       // so when the heap overflows the smaller user id is the one that is kept
       PriorityQueue<Map.Entry<String, Long>> minHeap = new PriorityQueue<>((a, b) -> {
           int amountComparison = Long.compare(a.getValue(), b.getValue());
           if(amountComparison != 0){
               return amountComparison;
           }

           return b.getKey().compareTo(a.getKey());
       });

      for(Map.Entry<String, Long> entry: map.entrySet()){
          minHeap.offer(entry);
          if(minHeap.size() > k){
              minHeap.poll();
          }
      }

      // the heap gives up its entries lowest first, so reverse to put the top user first
      List<String> result = new ArrayList<>();
      while(!minHeap.isEmpty()){
          result.add(minHeap.poll().getKey());
      }
      Collections.reverse(result);
      return result;
    }
}

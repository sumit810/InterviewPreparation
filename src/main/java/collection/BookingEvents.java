package collection;

import java.util.*;

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

    private static List<String> topKUsers(List<Booking> bookings, int k) {
       Map<String, Long> map = new HashMap<>();
       for(Booking booking: bookings){
           String userId = booking.userId;;
           map.put(userId, map.getOrDefault(userId, 0L) + booking.getAmount());
       }

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

      List<String> result = new ArrayList<>();
      while(!minHeap.isEmpty()){
          result.add(minHeap.poll().getKey());
      }
      return result;
    }
}

package com.breedish.test.leetcode.tasks;

import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import static java.time.Instant.now;

public class TinyURL {

    private final Map<String, String> db = new ConcurrentHashMap<>();
    private final IdGenerator idGenerator = new IdGenerator(1);
    private final Codec codec = new Codec();

    public String shorten(String url) {
        var id = idGenerator.next();
        var shortid = codec.encode(id);

        db.put(shortid, url);

        return shortid;
    }

    public String lookup(String shortid) {
        return db.get(shortid);
    }

    public static class IdGenerator {
        private static final int UNUSED_BITS = 1;
        private static final int EPOCH_BITS = 41;
        private static final int NODE_ID_BITS = 10;
        private static final int SEQUENCE_BITS = 12;


        private static final long DEFAULT_EPOCH = 1420070400000L;
        private static final long MAX_NODE_ID = (1L << NODE_ID_BITS) - 1;
        private static final long MAX_SEQUENCE_ID = (1L << SEQUENCE_BITS) - 1;

        private final long nodeId;
        private final long epoch;
        private long sequence = 0L;
        private final AtomicLong sequence2 = new AtomicLong(0L);
        private long lastTs = -1L;

        public IdGenerator(long nodeId) {
            this.nodeId = nodeId & MAX_NODE_ID;
            this.epoch = DEFAULT_EPOCH;
        }

        public synchronized long next() {
            var ts = timestamp();
            if (ts < lastTs) throw new IllegalStateException("invalid clock");

            if (ts == lastTs) {
                sequence = (sequence + 1) & MAX_SEQUENCE_ID;
                if (sequence == 0) ts = waitNextMillis(ts);
            } else {
                sequence = 0;
            }

            lastTs = ts;

            return ts << (NODE_ID_BITS + SEQUENCE_BITS)
                    | (nodeId << SEQUENCE_BITS)
                    | sequence;
        }

        public long next2() {
            var ts = timestamp();
            sequence = sequence2.incrementAndGet() % MAX_SEQUENCE_ID;
            return ts << (NODE_ID_BITS + SEQUENCE_BITS)
                    | (nodeId << SEQUENCE_BITS)
                    | sequence;
        }

        public long[] parse(long id) {
            long timestamp = (id >> (NODE_ID_BITS + SEQUENCE_BITS)) + epoch;
            long nodeId = (id & (MAX_NODE_ID << SEQUENCE_BITS)) >> SEQUENCE_BITS;
            long sequence = id & MAX_SEQUENCE_ID;

            return new long[]{timestamp, nodeId, sequence};
        }

        private long waitNextMillis(long currentTimestamp) {
            while (currentTimestamp == lastTs) currentTimestamp = timestamp();
            return currentTimestamp;
        }

        private long timestamp() {
            return now().toEpochMilli() - epoch;
        }

    }

    public static class Codec {

        private static final int BASE = 58;
        private static final char[] table = new char[]{
                '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
        };

        public String encode(long value) {
            var s = new Stack<Integer>();
            var from = value;
            do {
                s.add((int) (from % BASE));
                from = from / BASE;
            } while (from != 0);

            var builder = new StringBuilder();

            while (!s.isEmpty()) builder.append(table[s.pop()]);

            return builder.toString();
        }

    }

    public static void main(String[] args) {
//        var generator = new IdGenerator(1);

//        System.out.println(Arrays.toString(generator.parse(generator.next())));
//        System.out.println(generator.next());
//        System.out.println(generator.next());
//        System.out.println(generator.next());
//
//        System.out.println(Arrays.toString(generator.parse(generator.next2())));
//        System.out.println(generator.next2());
//        System.out.println(generator.next2());
//        System.out.println(generator.next2());

        var t = new TinyURL();
        System.out.println(t.shorten("abc"));
        System.out.println(t.shorten("how-long-is-this"));

        System.out.println(new Codec().encode(2468135791013L));

    }
}

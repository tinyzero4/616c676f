package c2.w3;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.util.Comparator.comparingLong;

public class JobQueue {
    private int numWorkers;
    private int[] jobs;

    private int[] assignedWorker;
    private long[] startTime;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }

    class TaskStartEvent {
        int workerId;
        long nextStartTime;

        TaskStartEvent(int workerId, long nextStartTime) {
            this.workerId = workerId;
            this.nextStartTime = nextStartTime;
        }

        public long getNextStartTime() {
            return nextStartTime;
        }

        public int getWorkerId() {
            return workerId;
        }
    }

    private TaskStartEvent assignWorker(int workerId, int jobId, long time) {
        assignedWorker[jobId] = workerId;
        startTime[jobId] = time;
        return new TaskStartEvent(workerId, jobs[jobId] + time);
    }

    private void assignJobs() {
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];

        PriorityQueue<TaskStartEvent> queue = new PriorityQueue<>(
            jobs.length, comparingLong(TaskStartEvent::getNextStartTime).thenComparingInt(TaskStartEvent::getWorkerId)
        );

        int jobIndex = 0;
        for (int i = 0; i < numWorkers; i++) {
            if (jobIndex >= jobs.length) {
                break;
            }
            queue.add(assignWorker(i, jobIndex++, 0));
        }

        while (jobIndex < jobs.length) {
            TaskStartEvent processed = queue.poll();
            queue.add(assignWorker(processed.getWorkerId(), jobIndex++, processed.getNextStartTime()));
        }
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        writeResponse();
        out.close();
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}

import java.util.*;

class Packet {
    int source, destination, timestamp;
    public Packet(int source, int destination, int timestamp) {
        this.source = source;
        this.destination = destination;
        this.timestamp = timestamp;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Packet)) return false;
        Packet p = (Packet) o;
        return source == p.source && destination == p.destination && timestamp == p.timestamp;
    }
    @Override
    public int hashCode() {
        return Objects.hash(source, destination, timestamp);
    }
}

class Router {
    private final int memoryLimit;
    private final Queue<Packet> packetQueue = new LinkedList<>();
    private final Set<Packet> uniquePackets = new HashSet<>();
    private final Map<Integer, List<Integer>> destinationTimestamps = new HashMap<>();
    private final Map<Integer, Integer> processedPacketIndex = new HashMap<>();

    public Router(int memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public boolean addPacket(int source, int destination, int timestamp) {
        Packet packet = new Packet(source, destination, timestamp);
        if (uniquePackets.contains(packet)) return false;

        if (packetQueue.size() == memoryLimit) forwardPacket();

        packetQueue.add(packet);
        uniquePackets.add(packet);

        destinationTimestamps.computeIfAbsent(destination, k -> new ArrayList<>()).add(timestamp);

        return true;
    }

    public int[] forwardPacket() {
        if (packetQueue.isEmpty()) return new int[0];

        Packet pkt = packetQueue.poll();
        uniquePackets.remove(pkt);

        processedPacketIndex.put(pkt.destination, processedPacketIndex.getOrDefault(pkt.destination, 0) + 1);
        return new int[]{pkt.source, pkt.destination, pkt.timestamp};
    }

    public int getCount(int destination, int startTime, int endTime) {
        if (!destinationTimestamps.containsKey(destination)) return 0;
        List<Integer> timestamps = destinationTimestamps.get(destination);
        int startIdx = processedPacketIndex.getOrDefault(destination, 0);
        int l = lowerBound(timestamps, startIdx, startTime);
        int r = upperBound(timestamps, startIdx, endTime);
        return r - l;
    }

    // binary search for first >= val
    private int lowerBound(List<Integer> list, int from, int val) {
        int l = from, r = list.size();
        while (l < r) {
            int m = (l + r) / 2;
            if (list.get(m) >= val) r = m;
            else l = m + 1;
        }
        return l;
    }
    // binary search for first > val
    private int upperBound(List<Integer> list, int from, int val) {
        int l = from, r = list.size();
        while (l < r) {
            int m = (l + r) / 2;
            if (list.get(m) > val) r = m;
            else l = m + 1;
        }
        return l;
    }
}

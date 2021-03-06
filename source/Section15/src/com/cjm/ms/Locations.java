package com.cjm.ms;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Locations implements Map<Integer, Location> {
    private Map<Integer, Location> locations = new LinkedHashMap<>();
    private Map<Integer, IndexRecord> index = new LinkedHashMap<>();
    private RandomAccessFile ra;

    public Locations() throws IOException {
//        // Locations and directions data.
//        Map<String, Integer> tempExit;
//        locations.put(0, new Location(0, "You are sitting in front of a computer learning Java.", null));
//
//        tempExit = new HashMap<>();
//        tempExit.put("W", 2);
//        tempExit.put("E", 3);
//        tempExit.put("S", 4);
//        tempExit.put("N", 5);
//        locations.put(1, new Location(1, "You are standing at the end of a road before a small brick building.", tempExit));
//
//        tempExit = new HashMap<>();
//        tempExit.put("N", 5);
//        locations.put(2, new Location(2, "You are at the top of a hill.", tempExit));
//
//        tempExit = new HashMap<>();
//        tempExit.put("W", 1);
//        locations.put(3, new Location(3, "You are inside a building, a well house for a small spring.", tempExit));
//
//        tempExit = new HashMap<>();
//        tempExit.put("N", 1);
//        tempExit.put("W", 2);
//        locations.put(4, new Location(4, "You are in a valley beside a stream.", tempExit));
//
//        tempExit = new HashMap<>();
//        tempExit.put("S", 1);
//        tempExit.put("W", 2);
//        locations.put(5, new Location(5, "You are in the forest.", tempExit));

//        // Original.
//        FileWriter locFile = null;
//        try {
//            // Throws a checked exception which must be handled to avoid compilation errors.
//            // Will throw an IOException if 'locations.txt' exists as a directory.
//            locFile = new FileWriter("locations.txt");
//            for(Location location: locations.values()) {
//                locFile.write(location.getLocationID() + "," + location.getDescription() + "\n");
//            }
//            // Bad idea to close resources in try block. Might not run if exception is thrown.
//            // locFile.close();
//        } catch(IOException e) {
//            System.out.println("In catch block.");
//            e.printStackTrace();
//        } finally {
//            // Can have try-catch blocks within try-catch blocks.
//            try {
//                if (locFile != null) {
//                    System.out.println("Attempting to close locFile.");
//                    locFile.close();
//                }
//            } catch(IOException e) {
//                e.printStackTrace();
//            }
//        }

//        // After propagating IOException.
//        FileWriter locFile = null;
//        try {
//            locFile = new FileWriter("locations.txt");
//            for(Location location: locations.values()) {
//                locFile.write(location.getLocationID() + "," + location.getDescription() + "\n");
//            }
//        } finally {
//            if (locFile != null) {
//                System.out.println("Attempting to close locFile.");
//                locFile.close();
//            }
//        }

        // Loads locations and directions data.
        // Scanner automatically closes any data source it was using as long as the
        // source implements Closeable - a subinterface of AutoCloseable.
//        try(Scanner scanner = new Scanner(new BufferedReader(new FileReader("locations_big.txt")))) {
//            scanner.useDelimiter(",");
//            while(scanner.hasNext()) {
//                int loc = scanner.nextInt();
//                scanner.skip(scanner.delimiter());
//                String description = scanner.nextLine();
//                System.out.println("Imported loc: " + loc + ": " + description);
//
//                Map<String, Integer> tempExit = new HashMap<>();
//                locations.put(loc, new Location(loc, description, tempExit));
//            }
//        } catch(IOException e) {
//            e.printStackTrace();
//        }
//        try(BufferedReader dirFile = new BufferedReader(new FileReader("directions_big.txt"))) {
//            String input;
//            while((input = dirFile.readLine()) != null) {
//                String[] data = input.split(",");
//                int loc = Integer.parseInt(data[0]);
//                String direction = data[1];
//                int destination = Integer.parseInt(data[2]);
//                System.out.println(loc + ": " + direction + ": " + destination);
//
//                Location location = locations.get(loc);
//                location.addExit(direction, destination);
//            }
//        } catch(IOException e) {
//            e.printStackTrace();
//        }

//        // Saves locations and directions data using BufferedWriter.
//        // After using try-with-resources statement.
//        // After propagating IOException, and using try-with-resources statement.
//        // Classes must implement AutoCloseable to do this.
//        // Multiple resources can be listed, use semicolons on all but the last.
//        // Java automatically calls the close method on each resource to release it.
//        try(BufferedWriter locFile = new BufferedWriter(new FileWriter("locations.txt"));
//            BufferedWriter dirFile = new BufferedWriter(new FileWriter("directions.txt"))) {
//            for(Location location: locations.values()) {
//                locFile.write(location.getLocationID() + "," + location.getDescription() + "\n");
//                for(String direction: location.getExits().keySet()) {
//                    if(!direction.equalsIgnoreCase("Q")) {
//                        dirFile.write(location.getLocationID() + "," +
//                                direction + "," +
//                                location.getExits().get(direction) + '\n');
//                    }
//                }
//            }
//        }

//        // Loads locations and directions data using byte streams.
//        try(DataInputStream locFile = new DataInputStream(
//                new BufferedInputStream(new FileInputStream("locations.dat")))) {
////            // Execution exits the loop when an exception is thrown.
////            while(true) {
//            boolean eof = false;
//            while(!eof) {
//                try {
//                    Map<String, Integer> exits = new LinkedHashMap<>();
//                    int locID = locFile.readInt();
//                    String description = locFile.readUTF();
//                    int numExits = locFile.readInt();
//                    System.out.println("Read location " + locID + " : " + description + ".");
//                    System.out.println("Found " + numExits + " exits.");
//                    for(int i = 0; i < numExits; i++) {
//                        String direction = locFile.readUTF();
//                        int destination = locFile.readInt();
//                        exits.put(direction, destination);
//                        System.out.println("\t\t" + direction + "," + description + ".");
//                    }
//                    locations.put(locID, new Location(locID, description, exits));
//                    // Exit loop when data source is empty.
//                } catch(EOFException e) {
//                    eof = true;
//                }
//            }
//        } catch(IOException e) {
//            System.out.println("IO Exception");
//        }

//        // Saves locations and directions data using byte streams.
//        // Using .dat extension to make it clear that it's not a text file.
//        try(DataOutputStream locFile = new DataOutputStream(
//                new BufferedOutputStream(new FileOutputStream("locations.dat")))) {
//            for(Location location: locations.values()) {
//                locFile.writeInt(location.getLocationID());
//                locFile.writeUTF(location.getDescription());
//                locFile.writeInt(location.getExits().size() - 1);
//                // Should be logged rather than output to console so that the log file can
//                // be used for debugging purposes.
//                System.out.println("Writing location " + location.getLocationID() + " : " +
//                                   location.getDescription());
//                System.out.println("Writing " + (location.getExits().size()-1) + " exits.");
//                for(String direction : location.getExits().keySet()) {
//                    if(!direction.equalsIgnoreCase("Q")) {
//                        System.out.println("\t\t" + direction + "," +
//                                           location.getExits().get(direction));
//                        locFile.writeUTF(direction);
//                        locFile.writeInt(location.getExits().get(direction));
//                    }
//                }
//            }
//        }

        // Loads locations and directions data using object streams / serialization.
//        try(ObjectInputStream locFile = new ObjectInputStream(
//                new BufferedInputStream(new FileInputStream("locations.dat")))) {
//            boolean eof = false;
//            while(!eof) {
//                try {
//                    Location location = (Location) locFile.readObject();
//                    System.out.println("Read location " + location.getLocationID() +
//                            " : " + location.getDescription());
//                    System.out.println("Found " + location.getExits().size() + " exits.");
//
//                    locations.put(location.getLocationID(), location);
//                } catch(EOFException e) {
//                    eof = true;
//                }
//            }
//            // ICE is a subclass of IOE, so it must be handled first.
//        } catch(InvalidClassException e) {
//            System.out.println("InvalidClassException: " + e.getMessage() + ".");
//        } catch(IOException e) {
//            System.out.println("IOException: " + e.getMessage() + ".");
//        } catch(ClassNotFoundException e) {
//            System.out.println("ClassNotFoundException: " + e.getMessage() + ".");
//        }

//        // Saves locations and directions data using object streams / serialization.
//        try(ObjectOutputStream locFile = new ObjectOutputStream(
//                new BufferedOutputStream(new FileOutputStream("locations.dat")))) {
//            for(Location location: locations.values()) {
//                locFile.writeObject(location);
//            }
//        }

//        // 1. Bytes 0-3 will contain the number of locations.
//        // 2. Bytes 4-7 will contain the start offset of the locations section.
//        // 3. Bytes 8-1699 will contain the the index.
//        // 4. Bytes 1700-end will contain the location records.
//
//        // Loads locations and directions data using random access.
//        try {
//            ra = new RandomAccessFile("locations_rand.dat", "rwd");
//            int numLocations = ra.readInt();
//            long locationStartPoint = ra.readInt();
//
//            while(ra.getFilePointer() < locationStartPoint) {
//                int locationID = ra.readInt();
//                int locationStart = ra.readInt();
//                int locationLength = ra.readInt();
//
//                IndexRecord record = new IndexRecord(locationStart, locationLength);
//                index.put(locationID, record);
//            }
//        } catch(IOException e) {
//            System.out.println("IOException: " + e.getMessage() + ".");
//        }

//        // Saves locations and directions data using random access.
//        // File will stay open as long as file is open to load locations as needed.
//        // 'rwd' allows reads and writes, and ensures that writes are synchronous.
//        try(RandomAccessFile rao = new RandomAccessFile("locations_rand.dat", "rwd")) {
//            rao.writeInt(locations.size());
//            int indexSize = locations.size() * 3 * Integer.BYTES;
//            int locationStart = (int) (indexSize + rao.getFilePointer() + Integer.BYTES);
//            rao.writeInt(locationStart);
//
//            long indexStart = rao.getFilePointer();
//
//            int startPointer = locationStart;
//            rao.seek(startPointer);
//
//            for(Location location: locations.values()) {
//                rao.writeInt(location.getLocationID());
//                rao.writeUTF(location.getDescription());
//                StringBuilder builder = new StringBuilder();
//                for(String direction: location.getExits().keySet()) {
//                    if(!direction.equalsIgnoreCase("Q")) {
//                        builder.append(direction);
//                        builder.append(",");
//                        builder.append(location.getExits().get(direction));
//                        builder.append(",");
//                        // direction,locationID,direction,locationID
//                        // N,1,U,2
//                    }
//                }
//                rao.writeUTF(builder.toString());
//
//                IndexRecord record = new IndexRecord(startPointer,
//                        (int) (rao.getFilePointer() - startPointer));
//                index.put(location.getLocationID(), record);
//                startPointer = (int) rao.getFilePointer();
//            }
//
//            rao.seek(indexStart);
//            for(Integer locationID: index.keySet()) {
//                rao.writeInt(locationID);
//                rao.writeInt(index.get(locationID).getStartByte());
//                rao.writeInt(index.get(locationID).getLength());
//            }
//        }

        // Loads locations and directions data using NIO. Everything above used IO.
//        Path locPath = FileSystems.getDefault().getPath("locations_big.txt");
//        Path dirPath = FileSystems.getDefault().getPath("directions_big.txt");
//        try (Scanner scanner = new Scanner(Files.newBufferedReader(locPath))) {
//            scanner.useDelimiter(",");
//            while(scanner.hasNextLine()) {
//                int loc = scanner.nextInt();
//                scanner.skip(scanner.delimiter());
//                String description = scanner.nextLine();
//                System.out.println("Imported loc: " + loc + ": " + description + ".");
//                locations.put(loc, new Location(loc, description, null));
//            }
//        } catch(IOException e) {
//            System.out.println("IOException: " + e.getMessage() + ".");
//        }
//        try(BufferedReader dirFile = Files.newBufferedReader(dirPath)) {
//            String input;
//            while((input = dirFile.readLine()) != null) {
//                String[] data = input.split(",");
//                int loc = Integer.parseInt(data[0]);
//                String direction = data[1];
//                int destination = Integer.parseInt(data[2]);
//                System.out.println(loc + ": " + direction + ": " + destination + ".");
//                Location location = locations.get(loc);
//                location.addExit(direction, destination);
//            }
//        } catch(IOException e) {
//            System.out.println("IOException: " + e.getMessage() + ".");
//        }
//        // Save locations and directions data using NIO.
//        try (BufferedWriter locFile = Files.newBufferedWriter(locPath);
//             BufferedWriter dirFile = Files.newBufferedWriter(dirPath)) {
//            for(Location location: locations.values()) {
//                locFile.write(location.getLocationID() + "," +
//                        location.getDescription() + "\n");
//                for(String direction: location.getExits().keySet()) {
//                    if(!direction.equalsIgnoreCase("Q")) {
//                        dirFile.write(location.getLocationID() + "," + direction + "," +
//                        location.getExits().get(direction) + "\n");
//                    }
//                }
//            }
//        } catch(IOException e) {
//            System.out.println("IO Exception: " + e.getMessage() + ".");
//        }

        // Load locations and directions data using NIO serialisation.
        Path locPath = FileSystems.getDefault().getPath("locations.dat");
        try(ObjectInputStream locFile = new ObjectInputStream(
                new BufferedInputStream(Files.newInputStream(locPath)))) {
            boolean eof = false;
            while(!eof) {
                try {
                    Location location = (Location) locFile.readObject();
                    locations.put(location.getLocationID(), location);
                } catch(EOFException e) {
                    eof = true;
                } catch(ClassNotFoundException e) {
                    System.out.println("ClassNotFoundException: " + e.getMessage() + ".");
                }
            }
        }
        // Save locations and directions data using NIO serialisation.
        try(ObjectOutputStream locFile = new ObjectOutputStream(
                new BufferedOutputStream(Files.newOutputStream(locPath)))) {
            // Can save and load the Map implementations directly since they implement Serializable.
            for(Location location: locations.values()) {
                locFile.writeObject(location);
            }
        }
    }

    // Uses random access to load just the required location data.
    public Location getLocation(int locationID) throws IOException {
        IndexRecord record = index.get(locationID);
        ra.seek(record.getStartByte());
        int id = ra.readInt();
        String description = ra.readUTF();
        String exits = ra.readUTF();
        String[] exitPart = exits.split(",");

        Location location = new Location(locationID, description, null);

        if(locationID != 0) {
            for(int i = 0; i < exitPart.length; i++) {
                System.out.println("exitPart = " + exitPart[i] + ".");
                System.out.println("exitPart[+1] = " + exitPart[i+1] + ".");
                String direction = exitPart[i];
                int destination = Integer.parseInt(exitPart[++i]);
                location.addExit(direction ,destination);
            }
        }

        return location;
    }

    // Override methods by redirecting them to the HashMap implementation.

    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {

    }

    @Override
    public void clear() {
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }

    public void close() throws IOException {
        if (ra != null) {
            ra.close();
        }
    }
}

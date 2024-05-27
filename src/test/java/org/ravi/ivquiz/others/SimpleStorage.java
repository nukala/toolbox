package org.ravi.ivquiz.others;

/**
 * Level 1: The simple storage system should support adding files as well as deleting files.
 * <p>
 * _Operations_:
 * <p>
 * ```ADD_FILE <file_name>```
 * - Should add a new file with name `file_name` to the storage.
 * - If a file with the same name `file_name` already exists, the current operation fails.
 * - Returns "true" if the file was added successfully, or "false" otherwise.
 * <p>
 * ```DELETE_FILE <file_name>```
 * - Should delete the file with the `file_name`.
 * - Returns "true" if the file was deleted successfully.
 * - Returns "false" if the file doesn't exist.
 * <p>
 * *Example:** These inputs are provided as an example of how the input data can be structured, you may select
 * any of these formats or come up with your own one.
 * <p>
 * #### Array
 * ```
 * operations = [
 * ["ADD_FILE", "file_1"],
 * ["ADD_FILE", "file_1"],
 * ["ADD_FILE", "file_2"],
 * ["ADD_FILE", "dir_1/file_2"],
 * ["DELETE_FILE", "file_2"],
 * ["DELETE_FILE", "file_2"]
 * ]
 * ```
 * <p>
 * The output should be
 * ```
 * true
 * false
 * true
 * true
 * true
 * false
 * ```
 */


/**
 Implement a new functionality that would support the copying and pasting of a file from one location to another. This operation is defined below:

 _Operation_:

 ```COPY <fromFilePath> <toDirectory>```
 - Should copy the identified file from the `fromFilePath` to the `toDirectory`. Note that the original file remains in place
 - Returns `true` if the operation was successful, `false` otherwise.
 - Returns `false` if a file with same name already exists in `toDirectory`
 - Returns `false` if `toDirectory` does not end with a slash (`/`)
 **Example:** These inputs are provided as an example of how the input data can be structured, you may select any of these formats or come up with your own one. Note: these operations below are in addition to those in Level 2

 #### Array
 ```
 operations = [
 ...
 ["COPY", "/dir_1/file_2", "/"],
 ["COPY", "/file_2", "/"],
 ["COPY", "/file_3", "/dir_1/"],
 ["COPY", "/file_3", "/dir_3"]
 ]
 ```

 The output should be
 ```
 true
 false
 true
 true
 true
 false

 true
 false
 false
 false
 ```
 */


import java.util.*;


class SimpleStorage {
    private Map<String, Boolean> map = new HashMap<>();
    private boolean verbose = false;

    public SimpleStorage() {
    }

    private String keysStr() {
        StringBuilder sb = new StringBuilder(256);

        for (String k : map.keySet()) {
            if (sb.isEmpty()) {
                sb.append(k);
            } else {
                sb.append(", ").append(k);
            }
        }

        return sb.toString();
    }

    public boolean addFile(String name) {
        if (map.containsKey(name)) {
            System.out.printf(">> %s exists %n", name);
            return false;
        }

        map.put(name, Boolean.TRUE);
        return true;
    }

    public boolean deleteFile(String name) {
        if (map.containsKey(name)) {
            map.remove(name);
            return true;
        }

        System.out.printf(">> %s does not exist %n", name);
        return false;
    }

    public boolean copyFile(String file, String directory) {
        if (!directory.endsWith("/")) {
            //System.out.printf(">>> COPY [%s] bad format%n", directory);

            return false;
        }
        if (!map.containsKey(file)) {
            //System.out.printf(">>> COPY [%s] already exists %n", file);
            return false;
        }

        String parts[] = file.split("/");
        String filePart = parts[parts.length - 1];
        if (filePart.startsWith("/")) {
            filePart = filePart.substring(1);
        }
        String dest = directory + filePart;
        if (map.containsKey(dest)) {
            return false;
        }
        if (isVerbose()) {
            System.out.printf(">>> COPY(%s/%s), dest(%s) keys[%s]%n", directory, filePart, dest, keysStr());
        }
        map.put(directory + filePart, Boolean.TRUE);
        //map.remove(file);
        return true;
    }

    protected void setVerbose(boolean verboseFlag) {
        this.verbose = verboseFlag;
    }

    protected boolean isVerbose() {
        return verbose;
    }

    public static void main(String[] args) {
        System.out.println("Hello, World");

        String[][] operations = new String[][]{
                {"ADD_FILE", "/file_1", null},
                {"ADD_FILE", "/file_1", null},
                {"ADD_FILE", "/file_2", null},
                {"ADD_FILE", "/dir_1/file_2", null}, // RN prepended /
                {"DELETE_FILE", "/file_2", null},
                {"DELETE_FILE", "/file_2", null},

                {"COPY", "/dir_1/file_2", "/"},
                {"COPY", "/file_2", "/"},
                {"COPY", "/file_3", "/dir_1/"},
                {"COPY", "/file_3", "/dir_3"}
        };
        SimpleStorage storage = new SimpleStorage();
        storage.setVerbose(true);
        for (String[] op : operations) {
            String work = op[0];
            String file = op[1];

            switch (work) {
                case "ADD_FILE": {
                    System.out.printf("%b %n", storage.addFile(file));
                    break;
                }
                case "DELETE_FILE": {
                    System.out.printf("%b %n", storage.deleteFile(file));
                    break;
                }
                case "COPY": {
                    String dir = op[2];
                    System.out.printf("%b %n", storage.copyFile(file, dir));
                    break;
                }
                default: {
                    System.err.printf("Unknown operation=[%s] file=[%s]%n", work, file);
                }
            }
        }
    }
}

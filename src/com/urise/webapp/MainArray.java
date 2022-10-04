package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.MapStorage;
import com.urise.webapp.storage.Storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Interactive test for com.u_rise.webapp.storage.ArrayStorage implementation
 * (just run, no need to understand)
 */
public class MainArray {
    private final static Storage ARRAY_STORAGE = new MapStorage();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Resume r;
        while (true) {
            System.out.print("Press of command for execution - " + "(list | size | update uuid | save uuid | delete uuid | get uuid | clear | exit): ");
            String[] params = reader.readLine().trim().toLowerCase().split(" ");
            if (params.length < 1 || params.length > 2) {
                System.out.println("Incorrect input");
                continue;
            }
            String uuid = null;
            if (params.length == 2) {
                uuid = params[1].intern();
            }
            switch (params[0]) {
                case "list":
                    printAll();
                    break;
                case "size":
                    System.out.println(ARRAY_STORAGE.size());
                    break;
                case "update":
                    if (uuid == null) {
                        System.out.println("Incorrect input \n" + "example: update uuid ");
                        break;
                    }
                    r = new Resume(uuid);
                    ARRAY_STORAGE.update(r);
                    printAll();
                    break;
                case "save":
                    if (uuid == null) {
                        System.out.println("Incorrect input \n" + "example: save uuid ");
                        break;
                    }
                    r = new Resume(uuid);
                    ARRAY_STORAGE.save(r);
                    printAll();
                    break;
                case "delete":
                    if (uuid == null) {
                        System.out.println("Incorrect input \n" + "example: delete uuid ");
                        break;
                    }
                    try {
                        ARRAY_STORAGE.delete(uuid);
                        System.out.println("Resume with " + uuid + " is deleted");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Delete isn't possible - " + e.getMessage());
                    }
                    printAll();
                    break;
                case "get":
                    if (uuid == null) {
                        System.out.println("Incorrect input \n" + "example: get uuid ");
                        break;
                    }
                    Resume resume = ARRAY_STORAGE.get(uuid);
                    if (resume != null) {
                        System.out.println(resume);
                        break;
                    }
                    System.out.println("Resume isn't found");
                    break;
                case "clear":
                    ARRAY_STORAGE.clear();
                    printAll();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Incorrect input");
                    break;
            }
        }
    }

    static void printAll() {
        List<Resume> all = ARRAY_STORAGE.getAll();
        System.out.println("----------------------------");
        if (all.size() == 0) {
            System.out.println("Empty");
        } else {
            for (Resume r : all) {
                System.out.println(r);
            }
        }
        System.out.println("----------------------------");
    }
}
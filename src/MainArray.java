import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Interactive test for ArrayStorage implementation
 * (just run, no need to understand)
 */
public class MainArray {
    private final static ArrayStorage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print("Press of command for execution - (list | size | save uuid | delete uuid | get uuid | clear | exit): ");
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
                case "save":
                        if (uuid == null) {
                            System.out.println("Incorrect input \n" + "example: save uuid ");
                            break;
                        }
                        ARRAY_STORAGE.save(new Resume(uuid));
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
        Resume[] all = ARRAY_STORAGE.getAll();
        System.out.println("----------------------------");
        if (all.length == 0) {
            System.out.println("Empty");
        } else {
            for (Resume r : all) {
                System.out.println(r);
            }
        }
        System.out.println("----------------------------");
    }
}
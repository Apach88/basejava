import java.util.Objects;

/**
 * Initial resume class
 */
public class Resume implements Comparable  {

    // Unique identifier
    String uuid;

    @Override
    public String toString() {
        return uuid;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }


    @Override
    public int compareTo(Object o) {
        return 0;
    }
}

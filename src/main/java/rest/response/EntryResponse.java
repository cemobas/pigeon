package rest.response;

import java.util.Set;

public class EntryResponse {

    private String welcome;
    private Set<Option> options;

    public EntryResponse() {}

    public EntryResponse(String welcome, Set<Option> options) {
        this.welcome = welcome;
        this.options = options;
    }

    public String getWelcome() {
        return welcome;
    }

    public void setWelcome(String welcome) {
        this.welcome = welcome;
    }

    public Set<Option> getOptions() {
        return options;
    }

    public void setOptions(Set<Option> options) {
        this.options = options;
    }
}

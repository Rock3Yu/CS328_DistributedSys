import myrmi.Remote;

import java.util.HashMap;

class test {
    public static void main(String[] args) {
        HashMap<String, String> bindings = new HashMap<>();
        bindings.put("a", "asdf");
        bindings.put("a", "asdfd");
        System.out.println(bindings.get("a"));
    }
}
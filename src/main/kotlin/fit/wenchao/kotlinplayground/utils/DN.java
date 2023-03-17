package fit.wenchao.kotlinplayground.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DN {
    String dn;

    public DN(String dn) {
        this.dn = dn;
    }

    // 获取dn列表，键值对形式，有序，例如：uid=xxx,ou=xxx,dc=xxx,dc=xxx
    public List<Pair<String, String>> resolveDn2List() {
        List<Pair<String, String>> dnList = new ArrayList<>();
        String[] split = dn.split(",");
        for (String dnEntry : split) {
            dnList.add(Pair.resolve(dnEntry, "="));
        }
        return dnList;
    }

    public String getPathString(String root, String tail) {
        if (root == null) {
            root = "";
        }
        if (tail == null) {
            tail = "";
        }
        return doGetPathString(root, tail);
    }

    private String doGetPathString(String root, String tail) {
        List<Pair<String, String>> pairs = this.resolveDn2List();
        Collections.reverse(pairs);
        Integer startIdx = findStart(root, pairs);
        Integer tailIdx = findTail(tail, pairs);

        StringBuilder sb = new StringBuilder();
        for (int i = startIdx; i < tailIdx; i++) {
            sb.append("/").append(pairs.get(i).getValue());
        }

        return sb.toString();
    }

    // 没找到tail则返回pairs的长度，表示返回整个dn path
    private Integer findTail(String tail, List<Pair<String, String>> pairs) {
        for (int i = 0; i < pairs.size(); i++) {
            if (pairs.get(i).getKey().equals(tail)) {
                return i;
            }
        }

        return pairs.size();
    }

    // 没找到start返回0，表示返回整个dn path
    private Integer findStart(String root, List<Pair<String, String>> pairs) {
        for (int i = 0; i < pairs.size(); i++) {
            if (pairs.get(i).getKey().equals(root)) {
                return i;
            }
        }
        return 0;
    }

    public List<String> getValueList(String key) {
        List<Pair<String, String>> pairs = resolveDn2List();
        return pairs.stream()
                .filter((pair -> {
                    return pair.getKey().equals(key);
                })).map((pair) -> {
                    return pair.getValue();
                }).collect(Collectors.toList());
    }

}

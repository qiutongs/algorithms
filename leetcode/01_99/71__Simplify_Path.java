class Solution {
    public String simplifyPath(String path) {
        if (path == null || path.isEmpty()) {
            return path;
        }
        String[] dirs = path.split("/");
        Stack<String> stack = new Stack<>();
        
        for (int i = 0; i < dirs.length; i++) {
            if (dirs[i].isEmpty() || dirs[i].equals(".")) {
                continue;
            }
            if (dirs[i].equals("..")) {
                if (stack.isEmpty() == false) {
                    stack.pop();
                }
            } else {
                stack.push(dirs[i]);
            }
        }
        
        return toString(stack);
    }
    
    private String toString(Stack<String> stack) {
        if (stack.isEmpty()) {
            return "/";
        }
        Stack<String> tmp = new Stack<>();
        while(stack.isEmpty() == false) {
            tmp.push(stack.pop());
        }
        
        StringBuilder sb = new StringBuilder();
        while(tmp.isEmpty() == false) {
            sb.append("/");
            sb.append(tmp.pop());
        }
        return sb.toString();
    }
}
class Solution {
    public List<String> removeComments(String[] source) {
        State state = State.None;
        StringBuilder sb = new StringBuilder();
        char[] c = {'\0', '\0'};
        
        for (String line : source) {
            for (int i = 0; i <= line.length(); i++) {
                c[0] = c[1];
                c[1] = i < line.length() ? line.charAt(i) : '\n';
                state = state.next(c, sb);
            }
        }
        
        String[] lines = sb.toString().split("\\n");
        List<String> ret = new ArrayList<>();
        for (String line : lines) {
            if (line.isEmpty() == false) {
                ret.add(line);
            }
        }
        return ret;
    }
    
    private enum State {
        None {
            @Override
            protected State next(char[] c, StringBuilder sb) {
                if (c[0] == '/' && c[1]== '*') {
                    sb.deleteCharAt(sb.length() - 1);
                    c[1] = '\0';
                    return Block;
                } else if (c[0] == '/' && c[1] == '/') {
                    sb.deleteCharAt(sb.length() - 1);
                    c[1] = '\0';
                    return Line;
                } else if (c[1] == '"') {
                    sb.append(c[1]);
                    return Quote;
                } else {
                    sb.append(c[1]);
                    return None;
                }
            }
        },
        Line {
            @Override
            protected State next(char[] c, StringBuilder sb) {
                if (c[1] == '\n') {
                    sb.append('\n');
                    return None;
                } else {
                    return Line;
                }
            }
        },
        Block {
            @Override
            protected State next(char[] c, StringBuilder sb) {
                if (c[0] == '*' && c[1] == '/') {
                    c[1] = '\0';
                    return None;
                } else {
                    return Block;
                }
            }
        },
        Quote {
            @Override
            protected State next(char[] c, StringBuilder sb) {
                return c[1] == '"' ? None : Quote;
            }
        };
        
        abstract protected State next(char[] c, StringBuilder sb);
    }
}
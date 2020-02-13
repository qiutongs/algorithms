class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<List<String>> lines = buildLines(words, maxWidth);
        List<String> ret = new ArrayList<>();
        for (int i = 0; i < lines.size() - 1; i++) {
            ret.add(output(lines.get(i), maxWidth));
        }
        ret.add(outputLast(lines.get(lines.size() - 1), maxWidth));
        return ret;
    }
    
    private String output(List<String> line, int maxWidth) {
        StringBuilder sb = new StringBuilder();
        if (line.size() == 1) {
            sb.append(line.get(0));
            while(sb.length() < maxWidth) {
                sb.append(' ');
            }
            return sb.toString();
        }
        int totalLen = 0;
        for (String word : line) {
            totalLen += word.length();
        }
        int minSpace = (maxWidth - totalLen) / (line.size() - 1);
        int emptySlot = (maxWidth - totalLen) % (line.size() - 1);
        for (int i = 0; i < line.size() - 1; i++) {
            sb.append(line.get(i));
            for (int j = 0; j < minSpace; j++) {
                sb.append(' ');
            }
            if (emptySlot > 0) {
                sb.append(' ');
                emptySlot--;
            }
        }
        sb.append(line.get(line.size() - 1));
        return sb.toString();
    }
    
    private String outputLast(List<String> line, int maxWidth) {
        StringBuilder sb = new StringBuilder();
        for (String word : line) {
            sb.append(word);
            sb.append(' ');
        }
        sb.deleteCharAt(sb.length() - 1);
        
        while(sb.length() < maxWidth) {
            sb.append(' ');
        }
        return sb.toString();
    }
    
    private List<List<String>> buildLines(String[] words, int maxWidth) {
        List<List<String>> ret = new ArrayList<>();
        List<String> curLine = new ArrayList<>();
        int curWidth = maxWidth;
        for (String word : words) {
            if (curLine.isEmpty()) {
                curLine.add(word);
                curWidth -= word.length();
            } else if (word.length() + 1 <= curWidth) {
                curLine.add(word);
                curWidth -= word.length() + 1;
            } else {
                ret.add(curLine);
                curLine = new ArrayList<>();
                curLine.add(word);
                curWidth = maxWidth - word.length();
            }
        }
        ret.add(curLine);
        return ret;
    }
}
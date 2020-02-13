package com.qiutongs.algorithm.parser;

import java.util.ArrayList;
import java.util.List;

public class RegularExpresser {

    public static List<Matcher> parse(String str) {
	List<Matcher> ret = new ArrayList<>();
	for (int i = 0; i < str.length(); i++) {
	    char c = str.charAt(i);
	    if (c == '+' || c == '*') {
	        Matcher preMat = ret.get(ret.size() - 1);
	        preMat.modifier = c == '+' ? Modifier.ONE_OR_MORE : Modifier.ZERO_OR_MORE;
	    } else {
		Matcher matcher = null;
		if (c == '\\') {
		    char next = i + 1 < str.length() ? str.charAt(i + 1) : '\0';
		    if (next == '.') {
			matcher = new Matcher(Type.Period);
		    } else if (next == 's') {
			matcher = new Matcher(Type.Space);
		    } else if (next == '\\') {
			matcher = new Matcher(Type.BackSlash);
		    }
		    i++;
		} else {
		    if (c == '.') {
			matcher = new Matcher(Type.AnyCharacter);
		    } else {
			matcher = new Matcher(Type.Character, c);
		    }
		}
		ret.add(matcher);
	    }
	}
	return ret;
    }
    
    public static void main(String[] args) {
	System.out.println(parse("..+, Hello World!\\s*"));
    }

    private enum Type {
	AnyCharacter(),
	Space(),
	Period(),
	BackSlash(),
	Character();	
    }
    private enum Modifier {
	ONCE,
	ONE_OR_MORE,
	ZERO_OR_MORE;
    }
    static class Matcher {
	Type type;
	Modifier modifier;
	char val;
	Matcher(Type type) {
	    this.type = type;
	    this.modifier = Modifier.ONCE;
	}
	
	Matcher(Type type, char val) {
	    this(type);
	    this.val = val;
	}
	
	public String toString() {
	    return "[ " + type + " " + val + " " + modifier + " ]";
	}
    }
}

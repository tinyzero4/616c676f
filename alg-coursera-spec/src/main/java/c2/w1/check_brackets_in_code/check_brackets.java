package c2.w1.check_brackets_in_code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Bracket {
    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    boolean Match(char c) {
        return type == c;
    }

    static boolean isBracket(char c) {
        return c == '[' || c == ']' || c == '{' || c == '}' || c == '(' || c == ')';
    }

    char type;
    int position;
}

class check_brackets {
    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();

        Stack<Bracket> toClose = new Stack<>();
        int position = 0;
        for (; position < text.length(); ++position) {
            char next = text.charAt(position);

            if (!Bracket.isBracket(next)) {
                continue;
            }

            if (next == '[') {
                toClose.push(new Bracket(']', position + 1));
            } else if (next == '(') {
                toClose.push(new Bracket(')', position + 1));
            } else if (next == '{') {
                toClose.push(new Bracket('}', position + 1));
            } else if (toClose.isEmpty()) {
                System.out.println(position + 1);
                return;
            } else if (!toClose.peek().Match(next)) {
                System.out.println(position + 1);
                return;
            } else {
                toClose.pop();
            }
        }

        if (!toClose.isEmpty()) {
            System.out.println(toClose.peek().position);
        } else {
            System.out.println("Success");
        }
    }
}

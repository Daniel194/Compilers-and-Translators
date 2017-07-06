from lexer import Lexer
from interpreter import Interpreter

INPUT_FILE = "input/test.txt"

if __name__ == '__main__':
    with open(INPUT_FILE) as f:
        for line in f:
            line = line.replace('\n', '')

            lexer = Lexer(line)
            interpreter = Interpreter(lexer)
            result = interpreter.expr()

            print(line, " = ", result)

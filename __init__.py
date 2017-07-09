from lexer import Lexer
from parser import Parser
from interpreter import Interpreter

INPUT_FILE = "input/test.txt"

if __name__ == '__main__':
    with open(INPUT_FILE) as f:
        for line in f:
            line = line.replace('\n', '')

            lexer = Lexer(line)
            parser = Parser(lexer)
            interpreter = Interpreter(parser)
            result = interpreter.interpret()

            print(line, " = ", result)

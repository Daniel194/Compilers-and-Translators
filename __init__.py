from lexer import Lexer
from parser import Parser
from interpreter import Interpreter

INPUT_FILE = "input/test.txt"

if __name__ == '__main__':
    with open(INPUT_FILE) as f:
        lexer = Lexer(f.read())
        parser = Parser(lexer)
        interpreter = Interpreter(parser)
        result = interpreter.interpret()
